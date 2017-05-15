package com.example.myoschina.fragment;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.example.myoschina.MessageEvent;
import com.example.myoschina.R;
import com.example.myoschina.bean.PersonalResponse;
import com.example.myoschina.utils.OSChinaApi;
import com.example.myoschina.utils.PreferencesUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by 若希 on 2017/5/12.
 */

public class PersonalFragment extends Fragment {
    String url;
    private CircleImageView civ;
    TextView tvName,tvFav;
    LinearLayout llTweet,llFav,llIdol,llFans;
    CircleImageView civTweet,civFav,civIdol,civFans;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=LayoutInflater.from(getContext()).inflate(R.layout.fragment_personal,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getData();
        civ = (CircleImageView)view.findViewById(R.id.civ_person);
        tvName=(TextView)view.findViewById(R.id.tv_name_personal);
        tvFav=(TextView)view.findViewById(R.id.tv_favorite_personal);
        llTweet=(LinearLayout)view.findViewById(R.id.ll_tweet_personal);
        llFav=(LinearLayout)view.findViewById(R.id.ll_favorite_personal);
        llIdol=(LinearLayout)view.findViewById(R.id.ll_idol_personal);
        llFans=(LinearLayout)view.findViewById(R.id.ll_fans_personal);
        civTweet=(CircleImageView)view.findViewById(R.id.civ_tweet_personal);
        civFav=(CircleImageView)view.findViewById(R.id.civ_favorite_personal);
        civIdol=(CircleImageView)view.findViewById(R.id.civ_idol_personal);
        civFans=(CircleImageView)view.findViewById(R.id.civ_fans_personal);
        llTweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                civTweet.setVisibility(View.GONE);
            }
        });
        llFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                civFav.setVisibility(View.GONE);
            }
        });
        llIdol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                civIdol.setVisibility(View.GONE);
            }
        });
        llFans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                civFans.setVisibility(View.GONE);
            }
        });
    }

    private void getData() {
        OkGo.get(OSChinaApi.USER)
                .tag(this)
                .params("access_token", PreferencesUtils.getString("access_token"))
                .params("dataType","json")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        Log.d("s=",s);
                        s=s.replaceAll("https://static.oschina.net","http://www.oschina.net/");
                        Gson gson=new Gson();
                        PersonalResponse personalResponse=gson.fromJson(s,PersonalResponse.class);
                        url=personalResponse.getAvatar();
                        //https://www.oschina.net/img/portrait.gif


                        if (url.equals("https://www.oschina.net/img/portrait.gif")) {
                            ColorGenerator colorGenerator = ColorGenerator.MATERIAL;
                            int color1 = colorGenerator.getRandomColor();
                            Drawable textDrawanle = TextDrawable.builder()
                                    .beginConfig()
                                    .width(120)
                                    .height(120)
                                    .toUpperCase()
                                    .endConfig()
                                    .buildRound(personalResponse.getName().substring(0, 1), color1);
                            civ.setImageDrawable(textDrawanle);
                        }
                        else {
                            Picasso.with(getContext()).load(url)
                                    .placeholder(R.color.colorPrimary)
                                    .into(civ);
                        }
                        tvName.setText(personalResponse.getName());

                    }
                });
    }

    @Subscribe
    public void onMessageEvent(MessageEvent event){

        Toast.makeText(getContext(), "接收到了消息", Toast.LENGTH_SHORT).show();
        int a=Integer.parseInt(tvFav.getText().toString());
        int b=a+1;
        tvFav.setText(b+"");
        civFav.setVisibility(View.VISIBLE);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EventBus.getDefault().register(this);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
    // This method will be called when a SomeOtherEvent is posted
//    @Subscribe
//    public void handleSomethingElse(SomeOtherEvent event){
//        doSomethingWith(event);
//    }
}
