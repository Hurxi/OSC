package com.example.myoschina.widget;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myoschina.R;
import com.example.myoschina.Test2Activity;
import com.example.myoschina.bean.ScrollImageBean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by 若希 on 2017/5/4.
 */

public class ScrollImageLayout extends LinearLayout {
    private ViewPager mViewPager;
    private List<ImageView> images;//轮播的图片
    private List<ImageView> dots;//小圆点

//            R.drawable.a,
//            R.drawable.b,
//            R.drawable.c,
//            R.drawable.fruit,
//            R.drawable.fruit1}
    ;
    private TextView tvTitle;
    private ScheduledExecutorService scheduledExecutorService;//线程池 开启定时循环任务
    private MyVPAdapter adapter;
    private int cuurentItem;//当前页
    private int oldPosition=0;//上一页
    Context context;
    List<ScrollImageBean> imageList;
    private int[] imagesIds;
    private String[] titles;

    public ScrollImageLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ScrollImageLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public void setImage(Context context, List<ScrollImageBean> imageList){
        this.context=context;
        this.imageList=imageList;
        init();

    }

    private void init() {
        //初始化控件以及数据
        View view= LayoutInflater.from(context).inflate(R.layout.layout_scroll_iamge,this,false);
        addView(view);
        LinearLayout ll_dot= (LinearLayout) view.findViewById(R.id.ll_dots);
        titles = new String[imageList.size()];
        imagesIds = new int[imageList.size()];
        dots=new ArrayList<>();

        for (int i = 0; i < imageList.size(); i++) {
            titles[i]=imageList.get(i).getTitle();
            imagesIds[i]=imageList.get(i).getImageId();
            ImageView imageView=new ImageView(context);
            imageView.setImageResource(R.drawable.greentag);
            LayoutParams params=new LayoutParams(40,40);
            dots.add(imageView);//小圆点加入集合 方便管理（变色）
            ll_dot.addView(imageView,params);
        }

        mViewPager=(ViewPager)findViewById(R.id.vp_scroll);
        images=new ArrayList<>();

        for (int i = 0; i < imagesIds.length; i++) {
            //循环添加Image View控件到集合
            ImageView imageView=new ImageView(context);
            imageView.setImageResource(imagesIds[i]);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            images.add(imageView);
        }

//        dots.add((ImageView) findViewById(R.id.dot0));
//        dots.add((ImageView) findViewById(R.id.dot1));
//        dots.add((ImageView) findViewById(R.id.dot2));
//        dots.add((ImageView) findViewById(R.id.dot3));
//        dots.add((ImageView) findViewById(R.id.dot4));
        dots.get(0).setImageResource(R.drawable.redtag);
        tvTitle= (TextView) view.findViewById(R.id.tv_title_scroll);
        tvTitle.setText(titles[0]);
        adapter = new MyVPAdapter();

        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(images.size());
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tvTitle.setText(titles[position]);//改成对应的标题
                dots.get(position).setImageResource(R.drawable.redtag);
                dots.get(oldPosition).setImageResource(R.drawable.greentag);
                oldPosition=position;
                cuurentItem=position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //滑动状态变化
                switch (state){
                    case 1:
                        //停止轮播
                        stop();
                        break;
                    case 2:
                        if (scheduledExecutorService==null) {
                            run();
                        }
                        break;
                }
            }
        });
    }
    private class MyVPAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return images.size();
        }

        //判断是否是同一页
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }
        //当要显示的图片可以进行缓存的时候 会调用这个方法进行图片初始化
        //将要显示的imageview加入到view group中 然后当作返回值即可
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(images.get(position));
//            return super.instantiateItem(container, position);
            return images.get(position);
        }

        //如果滑动的图片超出缓存范围 会调用这个方法 将图片销毁（默认缓存3项）

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(images.get(position));
        }
    }
    public void run(){
        //用来开启循环任务
        scheduledExecutorService= Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleWithFixedDelay(new ViewPageTask(),2,2, TimeUnit.SECONDS);
        //1.任务 描述翻页的任务 2.延迟两秒 3.间隔两秒 4.时间单位

    }

    private class ViewPageTask implements Runnable{

        @Override
        public void run() {
            //切页面
            //1.线程池对象开启循环任务在子线程中执行  即在子线程中修改主线程的ui 用handler
            //2.切到哪一页
            cuurentItem=(cuurentItem+1)%imagesIds.length;
//            mViewPager.setCurrentItem(1);
            mHandler.sendEmptyMessage(0);

        }
    }

    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            mViewPager.setCurrentItem(cuurentItem);
        }
    };


    public void stop(){
        if (scheduledExecutorService!=null){
            scheduledExecutorService.shutdown();
            scheduledExecutorService=null;
        }
    }
}
