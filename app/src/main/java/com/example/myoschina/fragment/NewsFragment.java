package com.example.myoschina.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myoschina.R;
import com.example.myoschina.adapter.CompreNewsRVAdapter;
import com.example.myoschina.bean.NewsListResponse;
import com.example.myoschina.bean.ScrollImageBean;
import com.example.myoschina.utils.OSChinaApi;
import com.example.myoschina.widget.ScrollImageLayout;
import com.google.gson.Gson;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by 若希 on 2017/4/27.
 */

public class NewsFragment extends Fragment {
    List<NewsListResponse.NewslistBean> newsList;
    CompreNewsRVAdapter adapter;
    private ScrollImageLayout scrollImageLayout;
    int pageIndex=1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //根据对应的布局文件生成view
        View view=inflater.inflate(R.layout.fragment_news,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SpringView springView=(SpringView)view.findViewById(R.id.spring_news);
        springView.setHeader(new DefaultHeader(getContext()));//设置头布局
        springView.setFooter(new DefaultFooter(getContext()));//设置尾布局
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                //刷新数据
                newsList.clear();
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
//        scrollImageLayout = (ScrollImageLayout) view.findViewById(R.id.scroll_news);
//        List<ScrollImageBean> scrollImageBeanList=new ArrayList<>();
//        scrollImageBeanList.add(new ScrollImageBean("高手问答|人工智能/机器学习在电商场景下的应用",R.drawable.a));
//        scrollImageBeanList.add(new ScrollImageBean("源创会|上海南京站开始报名啦",R.drawable.b));
//        scrollImageBeanList.add(new ScrollImageBean("混程序员的江湖",R.drawable.c));
//        scrollImageBeanList.add(new ScrollImageBean("我为什么不在乎人工智能",R.drawable.fruit));
//        scrollImageBeanList.add(new ScrollImageBean("维护VS Code开源项目背后的那些事情",R.drawable.fruit1));
//        scrollImageLayout.setImage(getContext(),scrollImageBeanList);
        newsList=new ArrayList<>();
        RecyclerView rv= (RecyclerView) view.findViewById(R.id.rv_comprehensive_news);


        adapter=new CompreNewsRVAdapter(getContext(),newsList);
//        TextView tvHead=new TextView(getContext());
//        tvHead.setText("我是头布局");
//        adapter.setHead(tvHead);
        scrollImageLayout = new ScrollImageLayout(getContext(), null);
        List<ScrollImageBean> scrollImageBeanList=new ArrayList<>();
        scrollImageBeanList.add(new ScrollImageBean("高手问答|人工智能/机器学习在电商场景下的应用",R.drawable.a));
        scrollImageBeanList.add(new ScrollImageBean("源创会|上海南京站开始报名啦",R.drawable.b));
        scrollImageBeanList.add(new ScrollImageBean("混程序员的江湖",R.drawable.c));
        scrollImageBeanList.add(new ScrollImageBean("我为什么不在乎人工智能",R.drawable.fruit));
        scrollImageBeanList.add(new ScrollImageBean("维护VS Code开源项目背后的那些事情",R.drawable.fruit1));
        scrollImageLayout.setImage(getContext(),scrollImageBeanList);
        adapter.setHead(scrollImageLayout);

        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);
        getData();
    }

    private void getData() {
        String token=getContext().getSharedPreferences("oschina", Context.MODE_PRIVATE).getString("access_token","");
        OkGo.get(OSChinaApi.NEWS_LIST)
                .tag(this)
                .params("access_token",token)
                .params("catalog",1)
                .params("page",1)
                .params("pageSize",20)
                .params("dataType","json")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.d("s:",s);
                        Gson gson=new Gson();
                        NewsListResponse newsListResponse=gson.fromJson(s,NewsListResponse.class);
                        List<NewsListResponse.NewslistBean> a=newsListResponse.getNewslist();
                        newsList.addAll(a);
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
        scrollImageLayout.run();
    }

    @Override
    public void onStop() {
        super.onStop();
        scrollImageLayout.stop();
    }
}
