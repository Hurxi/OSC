package com.example.myoschina;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myoschina.bean.CommentPubResponse;
import com.example.myoschina.bean.NewsDetailsResponse;
import com.example.myoschina.bean.NewsListResponse;
import com.example.myoschina.utils.OSChinaApi;
import com.example.myoschina.utils.PreferencesUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMWeb;

import org.greenrobot.eventbus.EventBus;

import okhttp3.Call;
import okhttp3.Response;

public class NewsDetailctivity extends AppCompatActivity {
    WebView wvNewsDetail;
    EditText etComment;
    Button btnSend, btnShare,btnFav;
    Toolbar toolBar;
    private int id, commentCount, newsType;
    TextView tvCommentCount;
    private String newsUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_news_detailctivity);
        wvNewsDetail = (WebView) findViewById(R.id.wv_news_detail);
        toolBar = (Toolbar) findViewById(R.id.tool_bar_newspub);
        tvCommentCount = (TextView) findViewById(R.id.tv_comment_count_news);
        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle("咨询详情");
        etComment = (EditText) findViewById(R.id.et_comment);
        btnSend = (Button) findViewById(R.id.btn_send);
        btnShare = (Button) findViewById(R.id.btn_share);
        btnFav = (Button) findViewById(R.id.btn_favorite);
        //配置web View
        WebSettings webSettings = wvNewsDetail.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setAppCacheEnabled(false);
        webSettings.setBuiltInZoomControls(false);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        wvNewsDetail.setWebViewClient(new WebViewClient());//在本应用跳转


        id = getIntent().getIntExtra("news_id", 0);
        commentCount = getIntent().getIntExtra("commentCount", 0);
        newsType = getIntent().getIntExtra("news_type", 0);


        if (newsType == 3) {
            newsType = 5;
        } else if (newsType == 2) {
            newsType = 2;
        } else if (newsType == 0 || newsType == 1 || newsType == 7 || newsType == 4) {
            newsType = 1;
        }


        tvCommentCount.setText(commentCount + "");
//        Toast.makeText(this, "id="+id, Toast.LENGTH_SHORT).show();
        getData(id);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mComment = etComment.getText().toString();
//                Toast.makeText(this,"mComment="+mComment,Toast.LENGTH_SHORT).show();
//                Toast.makeText(NewsDetailctivity.this, "mComment="+mComment, Toast.LENGTH_SHORT).show();
                senData(mComment, id, newsType);
            }
        });
        btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new MessageEvent("Hello everyone!"));
            }
        });

//        String s=wvNewsDetail.getUrl();
//        s=s.replaceAll("https://static.oschina.net","http://www.oschina.net/");
//        "http://www.baidu.com"

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.comment_news:
//                getDataComment(id);
                Intent intent = new Intent(NewsDetailctivity.this, NewsCommentActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("commentCount", commentCount);
                intent.putExtra("news_type", newsType);
                startActivity(intent);
        }
        return true;
    }


    private void senData(String mComment, int id, int newsType) {

        OkGo.get(OSChinaApi.COMMENT_PUB)
                .tag(this)
                .params("access_token", PreferencesUtils.getString("access_token"))
                .params("catalog", newsType)
                .params("id", id)
                .params("content", mComment)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        Log.d("s=", "onSuccess: "+s);
                        Gson gson = new Gson();
                        CommentPubResponse commentPubResponse = gson.fromJson(s, CommentPubResponse.class);
                        if (commentPubResponse.getError_description().equals("操作成功完成")) {
                            Toast.makeText(NewsDetailctivity.this, commentPubResponse.getError_description(), Toast.LENGTH_SHORT).show();

                        }

                    }
                });
    }


    private void getData(int id) {
        String token = PreferencesUtils.getString("access_token");
        OkGo.get(OSChinaApi.NEWS_DETAIL)
                .tag(this)
                .params("id", id)
                .params("access_token", token)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        Log.d("newsDetails",s);
                        Gson gson = new Gson();
                        NewsDetailsResponse newsDetailsResponse = gson.fromJson(s, NewsDetailsResponse.class);
                        String url = newsDetailsResponse.getUrl();
                        wvNewsDetail.loadUrl(url);

                        final UMWeb web = new UMWeb(url);
                        web.setTitle(newsDetailsResponse.getTitle());
                        web.setDescription("分享自OSChina");
                        btnShare.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                new ShareAction(NewsDetailctivity.this)
                                        .withMedia(web)
                                        .setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN)
                                        .setCallback(new UMShareListener() {
                                            @Override
                                            public void onStart(SHARE_MEDIA share_media) {

                                            }

                                            @Override
                                            public void onResult(SHARE_MEDIA share_media) {

                                            }

                                            @Override
                                            public void onError(SHARE_MEDIA share_media, Throwable throwable) {

                                            }

                                            @Override
                                            public void onCancel(SHARE_MEDIA share_media) {
                                                Toast.makeText(NewsDetailctivity.this, "取消分享", Toast.LENGTH_SHORT).show();
                                            }
                                        }).open();
                            }
                        });
                    }
                });
    }

}