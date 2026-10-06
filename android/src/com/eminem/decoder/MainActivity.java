package com.eminem.decoder;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.*;
import android.view.View;
import android.view.WindowInsets;
import java.io.*;
import java.nio.charset.StandardCharsets;
import org.json.JSONObject;

public class MainActivity extends Activity {
    private static final String HOST = "appassets.androidplatform.net";
    private WebView web;
    private static byte[] readBytes(InputStream input) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[8192];
        int count;
        while ((count = input.read(buffer)) != -1) output.write(buffer, 0, count);
        return output.toByteArray();
    }
    @Override public void onCreate(Bundle state) {
        super.onCreate(state);
        web = new WebView(this);
        web.setBackgroundColor(0xff09090b);
        web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().setDomStorageEnabled(true);
        web.getSettings().setSupportZoom(false);
        web.getSettings().setBuiltInZoomControls(false);
        web.getSettings().setDisplayZoomControls(false);
        web.getSettings().setAllowFileAccess(false);
        web.getSettings().setAllowContentAccess(false);
        WebView.setWebContentsDebuggingEnabled(true);
        web.setWebChromeClient(new WebChromeClient() {
            @Override public boolean onJsAlert(WebView v, String url, String message, JsResult result) {
                new AlertDialog.Builder(MainActivity.this).setMessage(message).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) { result.confirm(); }
                }).setOnCancelListener(new DialogInterface.OnCancelListener() {
                    public void onCancel(DialogInterface dialog) { result.cancel(); }
                }).show();
                return true;
            }
        });
        web.setWebViewClient(new WebViewClient() {
            @Override public boolean shouldOverrideUrlLoading(WebView v, WebResourceRequest request) {
                Uri uri=request.getUrl();
                if (HOST.equals(uri.getHost())) return false;
                if ("https".equals(uri.getScheme()) || "http".equals(uri.getScheme())) {
                    try { startActivity(new Intent(Intent.ACTION_VIEW, uri)); } catch(Exception ignored) {}
                }
                return true;
            }
            @Override public WebResourceResponse shouldInterceptRequest(WebView v, WebResourceRequest request) {
                Uri uri=request.getUrl();
                if (!HOST.equals(uri.getHost())) return null;
                String file=uri.getPath();
                if (file==null || !file.startsWith("/assets/") || file.contains("..")) return new WebResourceResponse("text/plain","UTF-8",new ByteArrayInputStream(new byte[0]));
                file=file.substring(8);
                try {
                    InputStream input=getAssets().open(file);
                    String mime=file.endsWith(".js")?"application/javascript":file.endsWith(".json")?"application/json":"text/html";
                    if(file.equals("index.html")) {
                        String html=new String(readBytes(input),StandardCharsets.UTF_8); input.close();
                        File secret=new File(getFilesDir(),"gemini.key");
                        if(secret.exists()) {
                            String key=new String(java.nio.file.Files.readAllBytes(secret.toPath()),StandardCharsets.UTF_8).trim();
                            JSONObject config=new JSONObject();config.put("provider","gemini");config.put("apiKey",key);config.put("model","gemini-3.8-flash");config.put("baseUrl","");
                            String bootstrap="<script>if(!localStorage.getItem('eminem_llm_config'))localStorage.setItem('eminem_llm_config',"+JSONObject.quote(config.toString())+");</script>";
                            html=html.replace("</body>",bootstrap+"</body>");
                        }
                        input=new ByteArrayInputStream(html.getBytes(StandardCharsets.UTF_8));
                    }
                    return new WebResourceResponse(mime,"UTF-8",input);
                } catch(Exception e) { return new WebResourceResponse("text/plain","UTF-8",new ByteArrayInputStream(new byte[0])); }
            }
        });
        setContentView(web);
        web.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
            public WindowInsets onApplyWindowInsets(View v, WindowInsets insets) {
                v.setPadding(insets.getSystemWindowInsetLeft(),insets.getSystemWindowInsetTop(),insets.getSystemWindowInsetRight(),insets.getSystemWindowInsetBottom());
                return insets;
            }
        });
        web.loadUrl("https://"+HOST+"/assets/index.html");
    }
    @Override public void onBackPressed() {
        web.evaluateJavascript("(()=>{if(!document.getElementById('wordLookupPopover').classList.contains('hidden')){closeWordPopover();return true}if(!document.getElementById('viewAnalysis').classList.contains('hidden')){backToAlbumTracks();return true}if(!document.getElementById('viewAlbumTracks').classList.contains('hidden')){showHomeView();return true}return false})()", new ValueCallback<String>() {
            public void onReceiveValue(String result) { if(!"true".equals(result)) MainActivity.super.onBackPressed(); }
        });
    }
    @Override protected void onDestroy(){web.destroy();super.onDestroy();}
}
