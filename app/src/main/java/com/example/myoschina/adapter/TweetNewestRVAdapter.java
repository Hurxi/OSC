package com.example.myoschina.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.example.myoschina.R;
import com.example.myoschina.TweetDetailActivity;
import com.example.myoschina.bean.QuestionListResponse;
import com.example.myoschina.bean.TweetNewestResponse;
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

public class TweetNewestRVAdapter extends RecyclerView.Adapter<TweetNewestRVAdapter.ViewHolder> {
    Context context;
    List<TweetNewestResponse.TweetlistBean> tweetNewestList;

    public TweetNewestRVAdapter(Context context, List<TweetNewestResponse.TweetlistBean> tweetNewestList) {
        this.context=context;
        this.tweetNewestList=tweetNewestList;
    }

    @Override
    public TweetNewestRVAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_newest_list,parent,false);
        ViewHolder vh=new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


//        lastPosition=position;
//        TweetNewestResponse.TweetlistBean newestsLast=tweetNewestList.get(position);
//        newestsLast.;
        final TweetNewestResponse.TweetlistBean newests=tweetNewestList.get(position);
        String pubTime=newests.getPubDate();//转换成小时

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
        holder.author.setText(newests.getAuthor());
        String body=newests.getBody();
        holder.body.setText(Html.fromHtml(body));
        holder.commentCount.setText(newests.getCommentCount()+"");
        if (newests.getPortrait().equals("https://www.oschina.net/img/portrait.gif"))
        {
        ColorGenerator colorGenerator=ColorGenerator.MATERIAL;
        int color1=colorGenerator.getRandomColor();
        Drawable textDrawanle= TextDrawable.builder()
                .beginConfig()
                .width(120)
                .height(120)
                .toUpperCase()
                .endConfig()
                .buildRound(newests.getAuthor().substring(0,1),color1);
        holder.bigImage.setImageDrawable(textDrawanle);
        String portrait=newests.getPortrait();
        Log.d("portrait",portrait);}
        else {
            Picasso.with(context).load(newests.getPortrait())
                .placeholder(R.color.colorPrimary)
                .into(holder.bigImage);
        }
//

//        holder.imageLayout.setImage(context,"");
//        holder.imageLayout.setVisibility(View.GONE);
        int visibility=holder.imageLayout.getVisibility();
//        if (visibility!=View.VISIBLE) {
        for (int i=0;i<holder.imageLayout.getChildCount();i++){
            holder.imageLayout.removeView(holder.imageLayout.getChildAt(i));
        }
            if (newests.getImgSmall() != null) {
                holder.imageLayout.setVisibility(View.VISIBLE);
//            Picasso.with(context).load(newests.getImgBig())
//                    .placeholder(R.color.colorPrimary)
//                    .into(holder.smaImage);
                holder.imageLayout.setImage(context, newests.getImgSmall());


            } else {
                holder.imageLayout.setVisibility(View.GONE);
            }
//        }
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, TweetDetailActivity.class);
                intent.putExtra("count",newests.getCommentCount());
                intent.putExtra("tweet_id",newests.getId());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return tweetNewestList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageLayout imageLayout;
        CircleImageView bigImage;
        TextView author;
        TextView time;
        TextView body;
        TextView commentCount;
        LinearLayout item;
        public ViewHolder(View itemView) {
            super(itemView);
            imageLayout=(ImageLayout)itemView.findViewById(R.id.image_content_newest);
            bigImage= (CircleImageView) itemView.findViewById(R.id.civ_item_big_newest);
            author= (TextView) itemView.findViewById(R.id.tv_item_author_newest);
            body=(TextView)itemView.findViewById(R.id.tv_item_body_newest);
            time= (TextView)itemView.findViewById(R.id.tv_item_time_newest);
            commentCount= (TextView)itemView.findViewById(R.id.tv_item_comment_count_newest);
            item=(LinearLayout)itemView.findViewById(R.id.item_newest_list);
        }
    }
}
