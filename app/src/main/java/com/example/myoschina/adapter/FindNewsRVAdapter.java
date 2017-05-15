package com.example.myoschina.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myoschina.NewsDetailctivity;
import com.example.myoschina.R;
import com.example.myoschina.bean.NewsListResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by 若希 on 2017/4/27.
 */

public class FindNewsRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //1.数据源 2.item布局
    Context context;
    List<NewsListResponse.NewslistBean> newsList;

    public FindNewsRVAdapter(Context context, List<NewsListResponse.NewslistBean> newsList){
        this.context=context;
        this.newsList=newsList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //创建view holder

            View view= LayoutInflater.from(context).inflate(R.layout.item_find,parent,false);
            NewsViewHolder vh=new NewsViewHolder(view);
            return vh;


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //绑定数据

        final NewsListResponse.NewslistBean news = newsList.get(position);
        NewsViewHolder newsViewHolder = (NewsViewHolder) holder;
        String pubTime = news.getPubDate();//转换成小时

//        String time1=pubTime.split(" ")[1];
        SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        Date dateNow = new Date();
        try {
            date = a.parse(pubTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar calendar = Calendar.getInstance();
        Calendar calendarNow = Calendar.getInstance();
        calendar.setTime(date);
        calendarNow.setTime(dateNow);
        int years = calendarNow.get(Calendar.YEAR) - calendar.get(Calendar.YEAR);
        int months = calendarNow.get(Calendar.MONTH) - calendar.get(Calendar.MONTH);
        int days = calendarNow.get(Calendar.DAY_OF_MONTH) - calendar.get(Calendar.DAY_OF_MONTH);
        int hours = calendarNow.get(Calendar.HOUR_OF_DAY) - calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendarNow.get(Calendar.MINUTE) - calendar.get(Calendar.MINUTE);
        int seconds = calendarNow.get(Calendar.SECOND) - calendar.get(Calendar.SECOND);
        if (years > 0) {
            newsViewHolder.time.setText(years + "年前");
        } else {
            if (months > 0) {
                newsViewHolder.time.setText(months + "月前");
            } else {
                if (days > 0) {
                    newsViewHolder.time.setText(days + "日前");
                } else {
                    if (hours > 0) {
                        newsViewHolder.time.setText(hours + "小时前");
                    } else {
                        if (minutes > 0) {
                            newsViewHolder.time.setText(minutes + "秒前");
                        } else
                            newsViewHolder.time.setText("刚刚");
                    }
                }
            }
        }

        newsViewHolder.title.setText(news.getTitle());


        newsViewHolder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转对应的详情页
//                    Toast.makeText(context, "点击了"+news.getId(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, NewsDetailctivity.class);
                intent.putExtra("news_id", news.getId());
                intent.putExtra("commentCount", news.getCommentCount());
                intent.putExtra("news_type", news.getType());
//                    String url=news.getUrl();
//                    Log.d("news_url",url);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView time;
        LinearLayout item;
        public NewsViewHolder(View itemView) {
            super(itemView);
            title= (TextView) itemView.findViewById(R.id.tv_title_find);
            time= (TextView)itemView.findViewById(R.id.tv_time_find);
            item=(LinearLayout)itemView.findViewById(R.id.item_find);
        }
    }

}
