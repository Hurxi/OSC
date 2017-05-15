package com.example.myoschina.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myoschina.R;
import com.example.myoschina.bean.BlogRecListResponse;
import com.example.myoschina.bean.NewsListResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by 若希 on 2017/5/2.
 */

public class CompreBlogRVAdapter extends RecyclerView.Adapter<CompreBlogRVAdapter.ViewHolder> {

    Context context;
    List<BlogRecListResponse.BloglistBean> blogList;
    public CompreBlogRVAdapter(Context context,List<BlogRecListResponse.BloglistBean> blogList) {
        this.context=context;
        this.blogList=blogList;
    }

    @Override
    public CompreBlogRVAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_blog_list,parent,false);
        ViewHolder vh=new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(CompreBlogRVAdapter.ViewHolder holder, int position) {
        BlogRecListResponse.BloglistBean blogs=blogList.get(position);
        String pubTime=blogs.getPubDate();//转换成小时

//        String time1=pubTime.split(" ")[1];
        SimpleDateFormat a=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date();
        Date dateNow=new Date();
        try {
            date=a.parse(pubTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar calendar=Calendar.getInstance();
        Calendar calendarNow=Calendar.getInstance();
        calendar.setTime(date);
        calendarNow.setTime(dateNow);
        int years=calendarNow.get(Calendar.YEAR)-calendar.get(Calendar.YEAR);
        int months=calendarNow.get(Calendar.MONTH)-calendar.get(Calendar.MONTH);
        int days=calendarNow.get(Calendar.DAY_OF_MONTH)-calendar.get(Calendar.DAY_OF_MONTH);
        int hours=calendarNow.get(Calendar.HOUR_OF_DAY)-calendar.get(Calendar.HOUR_OF_DAY);
        int minutes=calendarNow.get(Calendar.MINUTE)-calendar.get(Calendar.MINUTE);
        int seconds=calendarNow.get(Calendar.SECOND)-calendar.get(Calendar.SECOND);
        if (years>0){
            holder.time.setText(years+"年前");
        }
        else {
            if (months>0){
                holder.time.setText(months+"月前");
            }
            else {
                if (days>0){
                    holder.time.setText(days+"日前");
                }
                else {
                    if (hours>0){
                        holder.time.setText(hours+"小时前");
                    }
                    else {
                        if (minutes>0){
                            holder.time.setText(minutes+"分前");
                        }
                        else
                            holder.time.setText("刚刚");
                    }
                }
            }
        }

        holder.title.setText(blogs.getTitle());
        holder.comment.setText(blogs.getCommentCount()+"");

        if (pubTime.equals("昨天")){//判断是今天以前的 让tag隐藏
            holder.tag.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return blogList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView tag;
        TextView title;
        TextView content;
        TextView time;
        TextView comment;
        LinearLayout item;
        public ViewHolder(View itemView) {
                super(itemView);
                tag= (ImageView) itemView.findViewWithTag(R.id.iv_item_tag_blog);
                title= (TextView) itemView.findViewById(R.id.tv_item_title_blog);
                time= (TextView)itemView.findViewById(R.id.tv_item_time_blog);
                comment= (TextView)itemView.findViewById(R.id.tv_item_comment_count_blog);
                item=(LinearLayout)itemView.findViewById(R.id.item_blog_list);
            }
        }
    }

