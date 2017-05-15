package com.example.myoschina;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myoschina.adapter.MainVPAdapter;
import com.example.myoschina.bean.TabBean;
import com.example.myoschina.fragment.ComprehensiveFragment;
import com.example.myoschina.fragment.FindFragment;
import com.example.myoschina.fragment.NewsFragment;
import com.example.myoschina.fragment.PersonalFragment;
import com.example.myoschina.fragment.TweetListFragment;
import com.example.myoschina.widget.BottomLayout;
import com.example.myoschina.widget.UnScrollViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
//    Button btn1,btn2,btn3,btn4;
    UnScrollViewPager viewPager;
    MainVPAdapter mainVPAdapter;
    List<Fragment> fragmentList=new ArrayList<>();
    Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolBar=(Toolbar)findViewById(R.id.tool_bar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle("综合");
//        btn1= (Button) findViewById(R.id.btn1);
//        btn2= (Button) findViewById(R.id.btn2);
//        btn3= (Button) findViewById(R.id.btn3);
//        btn4= (Button) findViewById(R.id.btn4);

        //四大模块  fragment
//        fragmentList.add(new ComprehensiveFragment());
        fragmentList.add(new ComprehensiveFragment());
        fragmentList.add(new TweetListFragment());
        fragmentList.add(new FindFragment());
        fragmentList.add(new PersonalFragment());
        List<String> titles = new ArrayList<>();
        titles.add("1");
        titles.add("2");
        titles.add("3");
        titles.add("4");
        mainVPAdapter=new MainVPAdapter(getSupportFragmentManager(),fragmentList,titles);
        viewPager=(UnScrollViewPager) findViewById(R.id.view_pager);

        viewPager.setAdapter(mainVPAdapter);
        viewPager.setOffscreenPageLimit(4);


        BottomLayout bottomLayout= (BottomLayout) findViewById(R.id.bottom_layout);
        final List<TabBean> tabs=new ArrayList<>();
        tabs.add(new TabBean("综合",R.mipmap.ic_nav_news_actived,R.mipmap.ic_nav_news_normal,1));
        tabs.add(new TabBean("动弹",R.mipmap.ic_nav_tweet_actived,R.mipmap.ic_nav_tweet_normal,1));
        tabs.add(new TabBean("",R.mipmap.ic_add,R.mipmap.ic_nav_add_actived,0));
        tabs.add(new TabBean("发现",R.mipmap.ic_nav_discover_actived,R.mipmap.ic_nav_discover_normal,1));
        tabs.add(new TabBean("我的",R.mipmap.ic_nav_my_pressed,R.mipmap.ic_nav_my_normal,1));
        //先设置点击监听 再初始化
        bottomLayout.setMidClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "您点击了中间", Toast.LENGTH_SHORT).show();
            }
        });//中间按钮点击回调事件

        bottomLayout.setBottom(this,tabs,viewPager);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                switch (position){
                    case 0:
                        getSupportActionBar().setTitle("综合");
                        toolBar.setVisibility(View.VISIBLE);
//                        setTitle("综合");
                        break;
                    case 1:
                        getSupportActionBar().setTitle("动弹");
                        toolBar.setVisibility(View.VISIBLE);
//                        setTitle("动弹");
                        break;
                    case 2:
                        getSupportActionBar().setTitle("发现");
                        toolBar.setVisibility(View.VISIBLE);
//                        setTitle("发现");
                        break;
                    case 3:
                        toolBar.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
        }
    }
}



//    private void getNewsList() {
//        String token=getSharedPreferences("oschina",MODE_PRIVATE).getString("access_token","");
//        OkGo.get(OSChinaApi.NEWS_LIST)
//                .tag(this)
//                .params("access_token",token)
//                .params("catalog",1)
//                .params("page",1)
//                .params("pageSize",20)
//                .params("dataType","json")
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(String s, Call call, Response response) {
//                        Log.d("s:",s);
//                        Gson gson=new Gson();
//                        NewsListResponse newsListResponse=gson.fromJson(s,NewsListResponse.class);
//                        List<NewsListResponse.NewslistBean> a=newsListResponse.getNewslist();
//                    }
//                });
//    }
