package com.example.myoschina.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.example.myoschina.R;
import com.example.myoschina.bean.CommentListResponse;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 若希 on 2017/5/8.
 */

public class ComentNewsRVAdapter extends RecyclerView.Adapter<ComentNewsRVAdapter.ViewHolder> {

    Context context;
    List<CommentListResponse.CommentListBean> commentList;

    public ComentNewsRVAdapter(Context context, List<CommentListResponse.CommentListBean> commentList) {
        this.context=context;
        this.commentList=commentList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_news_commnet,parent,false);
        ViewHolder vh=new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CommentListResponse.CommentListBean comments=commentList.get(position);
        holder.tvName.setText(comments.getCommentAuthor());
        String pubTime=comments.getPubDate();
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
            holder.tvPubTime.setText(years+"年前");
        }
        else {
            if (months>0){
                holder.tvPubTime.setText(months+"月前");
            }
            else {
                if (days>0){
                    holder.tvPubTime.setText(days+"日前");
                }
                else {
                    if (hours>0){
                        holder.tvPubTime.setText(hours+"小时前");
                    }
                    else {
                        if (minutes>0){
                            holder.tvPubTime.setText(minutes+"分前");
                        }
                        else
                            holder.tvPubTime.setText("刚刚");
                    }
                }
            }
        }
        if (comments.getCommentPortrait().equals("https://www.oschina.net/img/portrait.gif")) {
            ColorGenerator colorGenerator = ColorGenerator.MATERIAL;
            int color1 = colorGenerator.getRandomColor();
            Drawable textDrawanle = TextDrawable.builder()
                    .beginConfig()
                    .width(120)
                    .height(120)
                    .toUpperCase()
                    .endConfig()
                    .buildRound(comments.getCommentAuthor().substring(0, 1), color1);
            holder.civ.setImageDrawable(textDrawanle);
        }
        else {
            Picasso.with(context).load(comments.getCommentPortrait())
                    .placeholder(R.color.colorPrimary)
                    .into(holder.civ);
        }
//
//        String portrait=comments.getCommentPortrait();
//        Log.d("portrait",portrait);
        String content=comments.getContent();

        holder.tvContent.setText(Html.fromHtml(content));
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView civ;
        TextView tvName,tvPubTime,tvFighting,tvContent;
        LinearLayout item;
        public ViewHolder(View itemView) {
            super(itemView);
            civ=(CircleImageView)itemView.findViewById(R.id.civ_item_news_comment);
            tvName=(TextView)itemView.findViewById(R.id.tv_item_name_news_comment);
            tvPubTime=(TextView)itemView.findViewById(R.id.tv_item_pubtime_news_comment);
            tvFighting=(TextView)itemView.findViewById(R.id.tv_item_fighting_news_comment);
            tvContent=(TextView)itemView.findViewById(R.id.tv_item_content_news_comment);
            item=(LinearLayout)itemView.findViewById(R.id.item_comment_news);
        }
    }
}
