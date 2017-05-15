package com.example.myoschina.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myoschina.R;
import com.example.myoschina.adapter.CompreQuestionRVAdapter;
import com.example.myoschina.adapter.TweetNewestRVAdapter;
import com.example.myoschina.bean.QuestionListResponse;
import com.example.myoschina.bean.TweetNewestResponse;
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

/**
 * Created by 若希 on 2017/5/3.
 */

public class NewestFragment extends Fragment {
    List<TweetNewestResponse.TweetlistBean> tweetNewestList;
    int pageIndex=1;
    TweetNewestRVAdapter adapter;
    public String[] urlImage;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SpringView springView=(SpringView)view.findViewById(R.id.spring_newest);
        springView.setHeader(new DefaultHeader(getContext()));//设置头布局
        springView.setFooter(new DefaultFooter(getContext()));//设置尾布局
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                //刷新数据
                tweetNewestList.clear();
                pageIndex=1;
                getData();
            }

            @Override
            public void onLoadmore() {
                //上拉加载更多
                pageIndex++;
                getData();
            }
        });

        //处理控件
        tweetNewestList=new ArrayList<>();
        RecyclerView rv= (RecyclerView) view.findViewById(R.id.rv_tweet_newest);


        adapter=new TweetNewestRVAdapter(getContext(),tweetNewestList);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);
        getData();
    }
    private void getData() {
        String token= PreferencesUtils.getString("access_token");
        OkGo.get(OSChinaApi.TWEET_LIST)
                .tag(this)
                .params("access_token",token)
                .params("user",0)
                .params("page",pageIndex)
                .params("pageSize",20)
                .params("dataType","json")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.d("s:", s);
                        s=s.replaceAll("https://static.oschina.net","http://www.oschina.net/");


                        Gson gson=new Gson();
                        TweetNewestResponse tweetlistResponse=gson.fromJson(s,TweetNewestResponse.class);
                        List<TweetNewestResponse.TweetlistBean> a=tweetlistResponse.getTweetlist();
//                        tweetNewestList.clear();
                        tweetNewestList.addAll(a);
                        adapter.notifyDataSetChanged();
//                        String url=a.get(0).getPortrait();
//                        urlImage = url.split(",");

                    }
                });
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_newest,container,false);
        return view;
    }
}
