package com.example.myoschina;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.myoschina.adapter.ComentNewsRVAdapter;
import com.example.myoschina.adapter.TweetNewestRVAdapter;
import com.example.myoschina.bean.CommentListResponse;
import com.example.myoschina.utils.OSChinaApi;
import com.example.myoschina.utils.PreferencesUtils;
import com.google.gson.Gson;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class NewsCommentActivity extends AppCompatActivity {
    RecyclerView rvCommentNews;
    List<CommentListResponse.CommentListBean> commentList;
    int pageIndex=1;
    ComentNewsRVAdapter adapter;
    public  int commentCount;
    private int newsType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        commentCount = getIntent().getIntExtra("commentCount",0);
        if (commentCount ==0){
            setContentView(R.layout.cativity_news_comment0);
        }
        else {
            setContentView(R.layout.activity_news_comment);
            rvCommentNews=(RecyclerView)findViewById(R.id.rv_comment_news);
            final int id=getIntent().getIntExtra("id",0);
            newsType=getIntent().getIntExtra("news_type",0);
            commentList=new ArrayList<>();
            SpringView springView=(SpringView)findViewById(R.id.spring_comment_news);
            springView.setHeader(new DefaultHeader(this));//设置头布局
            springView.setFooter(new DefaultFooter(this));//设置尾布局
            springView.setListener(new SpringView.OnFreshListener() {
                @Override
                public void onRefresh() {
                    //刷新数据
                    commentList.clear();
                    pageIndex=1;
                    getData(id,newsType);
                }

                @Override
                public void onLoadmore() {
                    //上拉加载更多
                    if (commentCount>20){
                        pageIndex++;
                        getData(id,newsType);
                    }
                    else {
                        Toast.makeText(NewsCommentActivity.this,"没有更多了",Toast.LENGTH_SHORT).show();
                    }


                }
            });

            adapter=new ComentNewsRVAdapter(this,commentList);
            LinearLayoutManager layoutManager=new LinearLayoutManager(this);
            rvCommentNews.setLayoutManager(layoutManager);
            rvCommentNews.setAdapter(adapter);

            getData(id,newsType);
        }

    }
    private void getData(int id,int newsType) {
        int pageSize=0;
        if (commentCount>20){
            pageSize=20;
        }
        else {
            pageSize=commentCount;
        }

            OkGo.get(OSChinaApi.COMMENT_LIST)
                    .params("id",id)
                    .params("access_token", PreferencesUtils.getString("access_token"))
                    .params("catalog",newsType)
//                .params("catalog",2)
                    .params("page",pageIndex)
                    .params("pageSize",pageSize)
                    .params("dataType","json")
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {
//                        Log.d("s=",s);
                            s=s.replaceAll("https://static.oschina.net","http://www.oschina.net/");
                            Gson gson=new Gson();
                            CommentListResponse commentListResponse=gson.fromJson(
                                    s, CommentListResponse.class
                            );

                            List<CommentListResponse.CommentListBean> a=commentListResponse.getCommentList();

                            commentList.addAll(a);
                            adapter.notifyDataSetChanged();
                        }
                    });

        }




}
