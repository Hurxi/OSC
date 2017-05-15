package com.example.myoschina;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.myoschina.bean.TokenResponse;
import com.example.myoschina.utils.OSChinaApi;
import com.example.myoschina.utils.PreferencesUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;

public class AuthorActivity extends AppCompatActivity {
    WebView webView;
    String client_id="lDw7iXjYk5eiyaSR1gpI";
    String app_key="QLuhSrxcQ7dS7fZsfGZASZ1QJQiZCOv7";
    String redirect_uri="http://www.oschina.net";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);
        //(A)：应用通过 浏览器 或 Webview 将用户引导到 OSChina 三方认证页面 上
//        https://www.oschina.net/action/oauth2/authorize?response_type=code&client_id={client_id}①&redirect_uri={redirect_uri}②
//        (B)：用户对应用进行授权
//                (C)：OSChina 认证服务器 通过 回调地址（redirect_uri）将 用户授权码 传递给 应用服务器 或者直接在 Webview 中跳转到携带 用户授权码的回调地址上，Webview 直接获取code即可（redirect_uri?code=abc&state=xyz）
//        (D)：应用服务器 或 Webview 使用 oauth2_token API 向 OSChina 认证服务器发送 用户授权码 以及 回调地址
//        (E)： OSChina 认证服务器返回 AccessToken
        webView=(WebView)findViewById(R.id.web_view);
        String url="https://www.oschina.net/action/oauth2/authorize?response_type=code&client_id="
                +client_id+"&redirect_uri="+redirect_uri;
        webView.loadUrl(url);
        //配置web View
        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setAppCacheEnabled(false);
        webSettings.setBuiltInZoomControls(false);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                //http://www.oschina.net/?code=rS4vNF&state=
                //截取code=后的内容 rS4vNF
                if (url.contains("http://www.oschina.net/?code=")){
                    String code=url.replace("http://www.oschina.net/?code=","");
                    code=code.split("&")[0];
                    Log.d("onPageFinished",code);
                    //将code授权码 传给服务器 请求token码
                    getToken(code);
                }
                Log.d("onPageFinished",url);
                super.onPageFinished(view, url);
            }
        });
    }

    private void getToken(String code) {
        OkGo.get(OSChinaApi.TOKEN)
                .tag(this)
                .params("client_id",client_id)
                .params("client_secret",app_key)
                .params("grant_type","authorization_code")
                .params("redirect_uri",redirect_uri)
                .params("code",code)
                .params("dataType","json")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                       Log.d("s",s) ;
                        Gson gson=new Gson();
                        TokenResponse tokenResponse=gson.fromJson(s,TokenResponse.class);
                        Log.d("t",tokenResponse.getAccess_token());
                        //将token存到本地  sharedpref
//                        SharedPreferences pref=getSharedPreferences("oschina",MODE_PRIVATE);
//                        //文件名称  文件存储模式
//                        SharedPreferences.Editor edit=pref.edit();
                        PreferencesUtils.putString("access_token",tokenResponse.getAccess_token());
                        PreferencesUtils.putString("refresh_token",tokenResponse.getRefresh_token());

                        PreferencesUtils.putInt("uid",tokenResponse.getUid());
                        PreferencesUtils.putString("token_type",tokenResponse.getToken_type());
                        PreferencesUtils.putInt("expires_in",tokenResponse.getExpires_in());
//                        edit.apply();
                        Intent intent=new Intent(AuthorActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }

                });
    }
}
