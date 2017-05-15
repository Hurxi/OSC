package com.example.myoschina.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myoschina.R;
import com.example.myoschina.bean.BlogRecListResponse;
import com.example.myoschina.bean.QuestionListResponse;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 若希 on 2017/5/2.
 */

public class CompreQuestionRVAdapter extends RecyclerView.Adapter<CompreQuestionRVAdapter.ViewHolder> {

    Context context;
    List<QuestionListResponse.PostListBean> questionList;
    public CompreQuestionRVAdapter(Context context, List<QuestionListResponse.PostListBean> questionList) {
        this.context=context;
        this.questionList=questionList;
    }


    @Override
    public CompreQuestionRVAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_question_list,parent,false);
        ViewHolder vh=new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(CompreQuestionRVAdapter.ViewHolder holder, int position) {
        QuestionListResponse.PostListBean questions=questionList.get(position);
        String pubTime=questions.getPubDate();//转换成小时

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

        holder.title.setText(questions.getTitle());
        holder.comment.setText(questions.getViewCount()+"");
        Picasso.with(context).load(questions.getPortrait())
                .placeholder(R.color.colorPrimary)
                .into(holder.tagImage);

//        holder.tagImage.setImageURI(questions.getPortrait());
//        Uri uri= Uri.parse(questions.getPortrait());
//        holder.tagImage.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CircleImageView tagImage;
        TextView title;
        TextView content;
        TextView time;
        TextView comment;
        LinearLayout item;
        public ViewHolder(View itemView) {
                super(itemView);
                tagImage= (CircleImageView) itemView.findViewById(R.id.civ_item_tag_question);
                title= (TextView) itemView.findViewById(R.id.tv_item_title_question);
                time= (TextView)itemView.findViewById(R.id.tv_item_time_question);
                comment= (TextView)itemView.findViewById(R.id.tv_item_comment_count_question);
                item=(LinearLayout)itemView.findViewById(R.id.item_question_list);
            }
        }
    }

