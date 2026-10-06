param(
  [string]$SdkRoot = $env:ANDROID_HOME,
  [string]$JavaRoot = $env:JAVA_HOME
)
$ErrorActionPreference = 'Stop'
$projectRoot = Split-Path $PSScriptRoot -Parent
if (!$SdkRoot) { $SdkRoot = 'D:\AndroidToolchain\android-sdk' }
if (!$JavaRoot) { $JavaRoot = 'D:\AndroidToolchain\jdk' }
$buildTools = Join-Path $sdkRoot 'build-tools\35.0.0'
$androidJar = Join-Path $sdkRoot 'platforms\android-35\android.jar'
$buildRoot = Join-Path $PSScriptRoot 'build'
foreach ($subdir in @('assets','classes','dex')) { New-Item -ItemType Directory -Force -Path (Join-Path $buildRoot $subdir) | Out-Null }
Copy-Item -LiteralPath (Join-Path $projectRoot 'catalog.json') -Destination (Join-Path $buildRoot 'assets\catalog.json')
$html = [IO.File]::ReadAllText((Join-Path $projectRoot 'index.html'))
$html = $html.Replace('https://www.gstatic.com/antigravity/web/dev/tailwindcss.min.js','tailwindcss.min.js')
[IO.File]::WriteAllText((Join-Path $buildRoot 'assets\index.html'),$html)
$tailwind = Join-Path $buildRoot 'assets\tailwindcss.min.js'
if (!(Test-Path $tailwind)) { Invoke-WebRequest -Uri 'https://www.gstatic.com/antigravity/web/dev/tailwindcss.min.js' -OutFile $tailwind }
function Check-Exit { if ($LASTEXITCODE -ne 0) { throw "Build command failed: $LASTEXITCODE" } }
& "$javaRoot\bin\javac.exe" -source 8 -target 8 -bootclasspath $androidJar -classpath $androidJar -d "$buildRoot\classes" "$PSScriptRoot\src\com\eminem\decoder\MainActivity.java"
Check-Exit
$classFiles = @(Get-ChildItem "$buildRoot\classes" -Recurse -Filter '*.class' | ForEach-Object FullName)
& "$javaRoot\bin\java.exe" -cp "$buildTools\lib\d8.jar" com.android.tools.r8.D8 --lib $androidJar --min-api 26 --output "$buildRoot\dex" @classFiles
Check-Exit
& "$buildTools\aapt.exe" package -f -M "$PSScriptRoot\AndroidManifest.xml" -S "$PSScriptRoot\res" -A "$buildRoot\assets" -I $androidJar -F "$buildRoot\unsigned.apk"
Check-Exit
Push-Location "$buildRoot\dex"
try { & "$buildTools\aapt.exe" add "$buildRoot\unsigned.apk" classes.dex; Check-Exit } finally { Pop-Location }
& "$buildTools\zipalign.exe" -f 4 "$buildRoot\unsigned.apk" "$buildRoot\aligned.apk"
Check-Exit
$keyStore = Join-Path $projectRoot '.debug\android-debug.keystore'
if (!(Test-Path $keyStore)) {
  New-Item -ItemType Directory -Force -Path (Split-Path $keyStore -Parent) | Out-Null
  & "$javaRoot\bin\keytool.exe" -genkeypair -keystore $keyStore -storepass android -keypass android -alias androiddebugkey -dname 'CN=Eminem Local Debug' -keyalg RSA -validity 10000
  Check-Exit
}
$apk = Join-Path $buildRoot 'Eminem-Decoder-debug.apk'
& "$javaRoot\bin\java.exe" -jar "$buildTools\lib\apksigner.jar" sign --ks $keyStore --ks-pass pass:android --key-pass pass:android --out $apk "$buildRoot\aligned.apk"
Check-Exit
& "$javaRoot\bin\java.exe" -jar "$buildTools\lib\apksigner.jar" verify $apk
Check-Exit
Write-Output "APK ready: $apk"
