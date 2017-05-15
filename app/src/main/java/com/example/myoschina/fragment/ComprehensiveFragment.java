package com.example.myoschina.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myoschina.R;
import com.example.myoschina.adapter.MainVPAdapter;
import com.example.myoschina.bean.BlogRecListResponse;
import com.example.myoschina.bean.NewsListResponse;
import com.example.myoschina.utils.OSChinaApi;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by 若希 on 2017/5/2.
 */

public class ComprehensiveFragment extends Fragment {
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //找到控件  并赋值

        super.onViewCreated(view, savedInstanceState);

        TabLayout tabLayout=(TabLayout)view.findViewById(R.id.tablelayout_compare);
        ViewPager vp=(ViewPager)view.findViewById(R.id.vp_compare);
        //设置adapter
        List<Fragment> fragmentList=new ArrayList<>();
        fragmentList.add(new NewsFragment());
        fragmentList.add(new BlogFragment());
        fragmentList.add(new QuestionFragment());
        fragmentList.add(new NewsFragment());
        List<String> titles = new ArrayList<>();
        titles.add("开源资讯");
        titles.add("推荐博客");
        titles.add("技术问答");
        titles.add("每日一博");
        MainVPAdapter adapter=new MainVPAdapter(getFragmentManager(),fragmentList,titles);
        vp.setAdapter(adapter);
        //与view pager关联
        tabLayout.setupWithViewPager(vp);
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //通过布局文件生成view
        View view=inflater.inflate(R.layout.fragment_comprehsnsive,container,false);

        return view;
    }


}
