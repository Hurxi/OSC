package com.example.myoschina.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myoschina.R;
import com.example.myoschina.adapter.MainVPAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 若希 on 2017/5/3.
 */

public class TweetListFragment extends Fragment {
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TabLayout tabLayout=(TabLayout)view.findViewById(R.id.tablelayout_tweet);
        ViewPager vp=(ViewPager)view.findViewById(R.id.vp_tweet);
        //设置adapter
        List<Fragment> fragmentList=new ArrayList<>();
        fragmentList.add(new NewestFragment());
        fragmentList.add(new HostFragment());
        fragmentList.add(new NewsFragment());
        fragmentList.add(new MyTweetFragment());
        List<String> titles = new ArrayList<>();
        titles.add("最新动弹");
        titles.add("热门动弹");
        titles.add("每日乱弹");
        titles.add("我的动弹");
        MainVPAdapter adapter=new MainVPAdapter(getFragmentManager(),fragmentList,titles);
        vp.setAdapter(adapter);
        //与view pager关联
        tabLayout.setupWithViewPager(vp);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_tweet_list,container,false);
        return view;
    }
}
