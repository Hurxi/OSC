package com.example.myoschina.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myoschina.R;
import com.example.myoschina.adapter.ChinaProRVAdapter;
import com.example.myoschina.bean.ChinaProjectResponse;
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
 * Created by 若希 on 2017/5/11.
 */

public class RecommendProjectFragment extends Fragment {
    List<ChinaProjectResponse.ProjectlistBean> projectList;
    int pageIndex=1;
    ChinaProRVAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_project,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        projectList=new ArrayList<>();

        SpringView springView=(SpringView)view.findViewById(R.id.spring_project_china);
        springView.setHeader(new DefaultHeader(getContext()));//设置头布局
        springView.setFooter(new DefaultFooter(getContext()));//设置尾布局
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                //刷新数据
                projectList.clear();
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
        RecyclerView rv= (RecyclerView) view.findViewById(R.id.rv_project_china);


        adapter=new ChinaProRVAdapter(getContext(),projectList);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);
        getData();
    }

    private void getData() {
        OkGo.get(OSChinaApi.PROJECT_LIST)
                .tag(this)
                .params("access_token", PreferencesUtils.getString("access_token"))
                .params("type","recommend")
                .params("dataType","json")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        Log.d("s=",s);
                        Gson gson=new Gson();
                        ChinaProjectResponse chinaProjectResponse=gson.fromJson(s,ChinaProjectResponse.class);
                        List<ChinaProjectResponse.ProjectlistBean> a=chinaProjectResponse.getProjectlist();
                        projectList.addAll(a);
                        adapter.notifyDataSetChanged();
                    }
                });
    }

}
