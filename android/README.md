# Android tablet debug app

The app bundles the web interface, catalog, and Tailwind runtime in an Android WebView. Online artwork, lyrics, and AI analysis require an internet connection. Pinch zoom is disabled. The UI is English; AI explanations remain Chinese.

## Build on Windows

Requires JDK 17+, Android SDK platform 35, and build-tools 35.0.0. Run from the repository root:

```powershell
./android/build.ps1 -SdkRoot 'C:\path\to\android-sdk' -JavaRoot 'C:\path\to\jdk'
```

The script also reads `ANDROID_HOME` and `JAVA_HOME`. It downloads the Tailwind runtime on the first build, creates a local debug signing key, and verifies the APK signature. Build outputs and local signing material are ignored by Git.

Output: `android/build/Eminem-Decoder-debug.apk`.

## Install and configure

```powershell
adb -s DEVICE_SERIAL install -r android/build/Eminem-Decoder-debug.apk
```

Enter your API key in the app settings. The APK contains no API key. For a local USB debug setup, an existing `.debug/gemini.key` can be provisioned into the app private directory:

```powershell
adb -s DEVICE_SERIAL push .debug/gemini.key /data/local/tmp/eminem-gemini.key
adb -s DEVICE_SERIAL shell run-as com.eminem.decoder cp /data/local/tmp/eminem-gemini.key files/gemini.key
adb -s DEVICE_SERIAL shell rm /data/local/tmp/eminem-gemini.key
adb -s DEVICE_SERIAL shell am start -n com.eminem.decoder/.MainActivity
```

On launch, the app uses the provisioned key only if no settings have already been saved. This APK is a debug build with WebView inspection enabled.
