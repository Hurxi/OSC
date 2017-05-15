package com.example.myoschina.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myoschina.R;
import com.example.myoschina.TweetDetailActivity;
import com.example.myoschina.bean.TweetNewestResponse;
import com.example.myoschina.bean.TwwetCommentResponse;
import com.example.myoschina.widget.ImageLayout;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 若希 on 2017/5/3.
 */

public class TweetCommentRVAdapter extends RecyclerView.Adapter<TweetCommentRVAdapter.ViewHolder> {
    Context context;
    List<TwwetCommentResponse.CommentListBean> tweetCommentList;

    public TweetCommentRVAdapter(Context context, List<TwwetCommentResponse.CommentListBean> tweetCommentList) {
        this.context=context;
        this.tweetCommentList=tweetCommentList;
    }

    @Override
    public TweetCommentRVAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_tweet_comment                                     ,parent,false);
        ViewHolder vh=new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


//        lastPosition=position;
//        TweetNewestResponse.TweetlistBean newestsLast=tweetNewestList.get(position);
//        newestsLast.;
        final TwwetCommentResponse.CommentListBean comments=tweetCommentList.get(position);
        String pubTime=comments.getPubDate();//转换成小时

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
        holder.author.setText(comments.getCommentAuthor());
        String content=comments.getContent();
        holder.body.setText(Html.fromHtml(content));
//        holder.commentCount.setText(comments.getCommentCount()+"");
        Picasso.with(context).load(comments.getCommentPortrait())
                .placeholder(R.color.colorPrimary)
                .into(holder.bigImage);


    }

    @Override
    public int getItemCount() {
        return tweetCommentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView bigImage;
        TextView author;
        TextView time;
        TextView body;

        LinearLayout item;
        public ViewHolder(View itemView) {
            super(itemView);

            bigImage= (CircleImageView) itemView.findViewById(R.id.civ_item_tweet_comment);
            author= (TextView) itemView.findViewById(R.id.tv_item_author_tweet_comment);
            body=(TextView)itemView.findViewById(R.id.tv_item_body_tweet_comment);
            time= (TextView)itemView.findViewById(R.id.tv_item_time_tweet_comment);
            item=(LinearLayout)itemView.findViewById(R.id.item_tweet_comment);
        }
    }
}
