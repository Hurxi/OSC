package com.example.myoschina.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myoschina.R;
import com.example.myoschina.bean.TwwetCommentResponse;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 若希 on 2017/5/3.
 */

public class TweetCommentRVAdapter2 extends RecyclerView.Adapter<TweetCommentRVAdapter2.ViewHolder> {
    Context context;


    public TweetCommentRVAdapter2(Context context) {
        this.context=context;

    }

    @Override
    public TweetCommentRVAdapter2.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_tweet_coment2,parent,false);
        ViewHolder vh=new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv.setText("当前没有评论");

//        lastPosition=position;
//        TweetNewestResponse.TweetlistBean newestsLast=tweetNewestList.get(position);
//        newestsLast.;



    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv;
        public ViewHolder(View itemView) {
            super(itemView);
            tv=(TextView)itemView.findViewById(R.id.tv_item_tweet_comment2);

        }
    }
}
