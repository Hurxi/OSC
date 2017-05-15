package com.example.myoschina;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.myoschina.adapter.MainVPAdapter;
import com.example.myoschina.fragment.ChinaProjectFragment;
import com.example.myoschina.fragment.ComprehensiveFragment;
import com.example.myoschina.fragment.FindFragment;
import com.example.myoschina.fragment.HostFragment;
import com.example.myoschina.fragment.HostProjectFragment;
import com.example.myoschina.fragment.NewestFragment;
import com.example.myoschina.fragment.NewsFragment;
import com.example.myoschina.fragment.RecommendProjectFragment;
import com.example.myoschina.fragment.TimeProjectFragment;
import com.example.myoschina.fragment.TweetListFragment;
import com.example.myoschina.widget.UnScrollViewPager;

import java.util.ArrayList;
import java.util.List;

public class ProjectActivity extends AppCompatActivity {

    ViewPager viewPager;
    MainVPAdapter mainVPAdapter;
    List<Fragment> fragmentList=new ArrayList<>();
    TabLayout tabLayout;
    Toolbar toolBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        tabLayout=(TabLayout)findViewById(R.id.tablelayout_project);
        toolBar=(Toolbar)findViewById(R.id.toolbar_project);
        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle("开源软件");

        fragmentList.add(new NewestFragment());
        fragmentList.add(new RecommendProjectFragment());
        fragmentList.add(new TimeProjectFragment());
        fragmentList.add(new HostProjectFragment());
        fragmentList.add(new ChinaProjectFragment());
        List<String> titles = new ArrayList<>();
        titles.add("分类");
        titles.add("推荐");
        titles.add("最新");
        titles.add("热门");
        titles.add("国产");
        mainVPAdapter=new MainVPAdapter(getSupportFragmentManager(),fragmentList,titles);
        viewPager=(ViewPager) findViewById(R.id.view_pager_project);
        viewPager.setAdapter(mainVPAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
