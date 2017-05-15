package com.example.myoschina;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.myoschina.bean.ScrollImageBean;
import com.example.myoschina.widget.ScrollImageLayout;

import java.util.ArrayList;
import java.util.List;

public class Test3Activity extends AppCompatActivity {
    ScrollImageLayout scrollImageLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test3);
        scrollImageLayout=
                (ScrollImageLayout) findViewById(R.id.scroll_test3);
        List<ScrollImageBean> scrollImageBeanList=new ArrayList<>();
        scrollImageBeanList.add(new ScrollImageBean("aaaaa",R.drawable.a));
        scrollImageBeanList.add(new ScrollImageBean("bbbbb",R.drawable.b));
        scrollImageBeanList.add(new ScrollImageBean("ccccc",R.drawable.c));
        scrollImageBeanList.add(new ScrollImageBean("ddddd",R.drawable.fruit));
        scrollImageBeanList.add(new ScrollImageBean("eeeee",R.drawable.fruit1));
        scrollImageLayout.setImage(this,scrollImageBeanList);
    }

    @Override
    protected void onStart() {
        super.onStart();
        scrollImageLayout.run();
    }

    @Override
    protected void onStop() {
        super.onStop();
        scrollImageLayout.stop();
    }
}
