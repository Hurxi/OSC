package com.example.myoschina;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.example.myoschina.adapter.MainVPAdapter;
import com.example.myoschina.bean.TweetDetailResponse;
import com.example.myoschina.fragment.NewestFragment;
import com.example.myoschina.fragment.TweetCommentFragment;
import com.example.myoschina.utils.OSChinaApi;
import com.example.myoschina.utils.PreferencesUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Response;

public class TweetDetailActivity extends AppCompatActivity implements BlankFragment.OnFragmentInteractionListener {
    CircleImageView civHead;
    TextView tvName,tvContent,tvPubtime;
    TabLayout tabLayout;
    ViewPager viewPager;
    List<Fragment> fragmentList;
    private int count;

    MainVPAdapter adapter;
    public static int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_detail);
        civHead=(CircleImageView)findViewById(R.id.civ_head_tweet_detail);
        tvName=(TextView) findViewById(R.id.tv_name_tweet_detail);
        tvContent=(TextView) findViewById(R.id.tv_content_tweet_detail);
        tvPubtime=(TextView)findViewById(R.id.tv_pubtime_tweet_detail);
        tabLayout=(TabLayout)findViewById(R.id.tab_tweet_detail) ;
        viewPager=(ViewPager)findViewById(R.id.vp_twet_detail);

        id =getIntent().getIntExtra("tweet_id",0) ;
        count=getIntent().getIntExtra("count",0);
        getData();




//        Toast.makeText(this, "id="+id, Toast.LENGTH_SHORT).show();

    }

    private void getData() {
        OkGo.get(OSChinaApi.TWEET_DETAIL)
                .params("access_token", PreferencesUtils.getString("access_token"))
                .params("id",id)
                .params("dataType","json")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        Log.d("s=",s);
                        s=s.replaceAll("https://static.oschina.net","http://www.oschina.net/");
                        Gson gson=new Gson();
                        TweetDetailResponse tweetDetailResponse=gson.fromJson(s,TweetDetailResponse.class);
                        String url = tweetDetailResponse.getPortrait();
                        if (url.equals("https://www.oschina.net/img/portrait.gif")) {
                            ColorGenerator colorGenerator = ColorGenerator.MATERIAL;
                            int color1 = colorGenerator.getRandomColor();
                            Drawable textDrawanle = TextDrawable.builder()
                                    .beginConfig()
                                    .width(120)
                                    .height(120)
                                    .toUpperCase()
                                    .endConfig()
                                    .buildRound(tweetDetailResponse.getAuthor().substring(0, 1), color1);
                            civHead.setImageDrawable(textDrawanle);
                        }
                        else {

                            Picasso.with(TweetDetailActivity.this).load(url)
                                    .placeholder(R.color.colorPrimary)
                                    .into(civHead);
                        }
                        tvName.setText(tweetDetailResponse.getAuthor());
                        String content=tweetDetailResponse.getBody();
                        tvContent.setText(Html.fromHtml(content));
                        //count=tweetDetailResponse.getCommentCount();
                        Toast.makeText(TweetDetailActivity.this, ""+count, Toast.LENGTH_SHORT).show();
                        String pubTime=tweetDetailResponse.getPubDate();//转换成小时
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
                            tvPubtime.setText(years+"年前");
                        }
                        else {
                            if (months>0){
                                tvPubtime.setText(months+"月前");
                            }
                            else {
                                if (days>0){
                                    tvPubtime.setText(days+"日前");
                                }
                                else {
                                    if (hours>0){
                                        tvPubtime.setText(hours+"小时前");
                                    }
                                    else {
                                        if (minutes>0){
                                            tvPubtime.setText(minutes+"分前");
                                        }
                                        else
                                            tvPubtime.setText("刚刚");
                                    }
                                }
                            }
                        }



                    }
                });
        List<String> titles = new ArrayList<>();
        titles.add("赞");
        titles.add("评论");
        fragmentList=new ArrayList<>();
        fragmentList.add(new NewestFragment());

        fragmentList.add(TweetCommentFragment.newInstance(id,count));
         adapter=new MainVPAdapter(getSupportFragmentManager(),fragmentList,titles);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
//        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
