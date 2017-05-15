package com.example.myoschina;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myoschina.bean.TabBean;
import com.example.myoschina.widget.BottomLayout;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        BottomLayout bottomLayout=(BottomLayout)findViewById(R.id.bottom_layout);
        List<TabBean> tabs=new ArrayList<>();
        tabs.add(new TabBean("综合",R.mipmap.ic_nav_news_actived,R.mipmap.ic_nav_news_normal,1));
        tabs.add(new TabBean("动弹",R.mipmap.ic_nav_tweet_actived,R.mipmap.ic_nav_tweet_normal,1));
        tabs.add(new TabBean("+",R.mipmap.ic_add,R.mipmap.ic_add,0));
        tabs.add(new TabBean("发现",R.mipmap.ic_nav_discover_actived,R.mipmap.ic_nav_discover_normal,1));
        tabs.add(new TabBean("我的",R.mipmap.ic_nav_my_pressed,R.mipmap.ic_nav_my_normal,1));
//        bottomLayout.setBottom(this,tabs);






//        LinearLayout linearLayout= (LinearLayout) findViewById(R.id.activity_test);
//        /**
//         * 1.布局（viewGroup）中可以调用add View方法添加控件（View）
//         * 2.可通过layoutParams对象设置宽高等属性*/
//
//        for (int i = 0; i < 5; i++) {
//            if (i==2){
//                ImageView imageView=new ImageView(this);
//                imageView.setImageResource(R.mipmap.ic_launcher);
//                LinearLayout.LayoutParams ivParams=new LinearLayout.LayoutParams(
//                        ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT
//                );
//                //用来描述线性布局子控件的属性
//                linearLayout.addView(imageView,ivParams);
//            }
//            else {
//
//
//            LinearLayout linearLayout1=new LinearLayout(this);
//            linearLayout1.setOrientation(LinearLayout.VERTICAL);
//            TextView tv=new TextView(this);
//            ImageView imageView=new ImageView(this);
//            imageView.setImageResource(R.mipmap.ic_launcher);
//            LinearLayout.LayoutParams ivParams=new LinearLayout.LayoutParams(
//                    ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT
//            );
//            //用来描述线性布局子控件的属性
//            ivParams.gravity= Gravity.CENTER;
//            linearLayout1.addView(imageView,ivParams);
//            tv.setText("综合");
//                LinearLayout.LayoutParams tvParams=new LinearLayout.LayoutParams(
//                        ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT
//                );
//                tvParams.gravity=Gravity.CENTER;
//            linearLayout1.addView(tv,tvParams);
//            LinearLayout.LayoutParams lnParams=new LinearLayout.LayoutParams(
//                    ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT,1
//            );
//
//            linearLayout.addView(linearLayout1,lnParams);
//        }}

    }
}
