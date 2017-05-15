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

import com.example.myoschina.R;
import com.example.myoschina.adapter.CompreBlogRVAdapter;
import com.example.myoschina.adapter.CompreNewsRVAdapter;
import com.example.myoschina.bean.BlogRecListResponse;
import com.example.myoschina.bean.NewsListResponse;
import com.example.myoschina.utils.OSChinaApi;
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
 * Created by 若希 on 2017/5/2.
 */

public class BlogFragment extends Fragment {
    List<BlogRecListResponse.BloglistBean> blogList;
    CompreBlogRVAdapter adapter;
    int pageIndex=1;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SpringView springView=(SpringView)view.findViewById(R.id.spring_blogs);
        springView.setHeader(new DefaultHeader(getContext()));//设置头布局
        springView.setFooter(new DefaultFooter(getContext()));//设置尾布局
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                //刷新数据
                blogList.clear();
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

        blogList=new ArrayList<>();
        RecyclerView rv= (RecyclerView) view.findViewById(R.id.rv_comprehensive_blog);


        adapter=new CompreBlogRVAdapter(getContext(),blogList);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);
        getData();
    }

    private void getData() {
        String token=getContext().getSharedPreferences("oschina", Context.MODE_PRIVATE).getString("access_token","");
        OkGo.get(OSChinaApi.BLOG_RECOMMEND_LIST)
                .tag(this)
                .params("access_token",token)
                .params("page",1)
                .params("pageSize",20)
                .params("dataType","json")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.d("s:",s);
                        Gson gson=new Gson();
//                        NewsListResponse newsListResponse=gson.fromJson(s,NewsListResponse.class);
//                        List<NewsListResponse.NewslistBean> a=newsListResponse.getNewslist();
//                        newsList.addAll(a);
//                        adapter.notifyDataSetChanged();
                        BlogRecListResponse blogRecListResponse=gson.fromJson(s,BlogRecListResponse.class);
                        List<BlogRecListResponse.BloglistBean> a=blogRecListResponse.getBloglist();
                        blogList.addAll(a);
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_blogs,container,false);
        return view;
    }
}
