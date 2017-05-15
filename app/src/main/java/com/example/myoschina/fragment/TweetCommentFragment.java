package com.example.myoschina.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myoschina.NewsCommentActivity;
import com.example.myoschina.R;
import com.example.myoschina.TweetDetailActivity;
import com.example.myoschina.adapter.ComentNewsRVAdapter;
import com.example.myoschina.adapter.TweetCommentRVAdapter;
import com.example.myoschina.adapter.TweetCommentRVAdapter2;
import com.example.myoschina.adapter.TweetNewestRVAdapter;
import com.example.myoschina.bean.CommentListResponse;
import com.example.myoschina.bean.TweetDetailResponse;
import com.example.myoschina.bean.TweetNewestResponse;
import com.example.myoschina.bean.TwwetCommentResponse;
import com.example.myoschina.utils.OSChinaApi;
import com.example.myoschina.utils.PreferencesUtils;
import com.google.gson.Gson;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by 若希 on 2017/5/9.
 */

public class TweetCommentFragment extends Fragment {
    int pageIndex=1;
    private int count;
    List<TwwetCommentResponse.CommentListBean> tweetComment;
    private int id;
    TweetCommentRVAdapter adapter;
    TweetCommentRVAdapter2 adapter2;
    //    int commentCount;
//    int tweetId= TweetDetailActivity.id;
//    private int commentCount= TweetDetailActivity.commend;

    public static TweetCommentFragment newInstance(int  id, int count) {
        TweetCommentFragment fragment = new TweetCommentFragment();
        Bundle args = new Bundle();
        args.putInt("id", id);
        args.putInt("count", count);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        View view=inflater.inflate(R.layout.fragment_comment_tweet,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
         Bundle b    = getArguments();
        id = (int) b.get("id");
        count = (int) b.get("count");

        final SpringView springView=(SpringView)view.findViewById(R.id.spring_tweet_comment);
        springView.setHeader(new DefaultHeader(getContext()));//设置头布局
        springView.setFooter(new DefaultFooter(getContext()));//设置尾布局
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                //刷新数据
                if (count!=0){
                    tweetComment.clear();

                    pageIndex=1;
                    getData();
                }
               else {
                    Toast.makeText(getContext(), "没有数据", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onLoadmore() {
                //上拉加载更多
                if (count!=0) {
                    if (count > pageIndex * 20) {
                        pageIndex++;
                        getData();
                    } else {
                        Toast.makeText(getContext(), "没有更多了", Toast.LENGTH_SHORT).show();
                        springView.clearAnimation();
                    }
                }
                else {
                    Toast.makeText(getContext(), "没有更多了", Toast.LENGTH_SHORT).show();
                    springView.clearAnimation();

                }

            }
        });

        //处理控件
        tweetComment=new ArrayList<>();
        RecyclerView rv= (RecyclerView) view.findViewById(R.id.rv_tweet_comment);


        adapter=new TweetCommentRVAdapter(getContext(),tweetComment);
        adapter2=new TweetCommentRVAdapter2(getContext());
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        rv.setLayoutManager(layoutManager);
        if (count==0){
            rv.setAdapter(adapter2);
        }
        else{
            rv.setAdapter(adapter);
            getData();
        }


    }



    private void getData() {
            int pageSize =20;
            if (count>20){
                pageSize=20;
            }
            else {
                pageSize=count;
            }
            OkGo.get(OSChinaApi.COMMENT_LIST)
                    .params("id",id)
                    .params("catalog",3)
                    .params("access_token", PreferencesUtils.getString("access_token"))
                    .params("page",pageIndex)
                    .params("pageSize",pageSize)
                    .params("dataType","json")
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {
//                        Log.d("s=",s);
                            s=s.replaceAll("https://static.oschina.net","http://www.oschina.net/");
                            Gson gson=new Gson();
                            TwwetCommentResponse tweetNewestResponse=gson.fromJson(s,TwwetCommentResponse.class);
                            List<TwwetCommentResponse.CommentListBean> a=tweetNewestResponse.getCommentList();
                                tweetComment.addAll(a);
                                adapter.notifyDataSetChanged();


                        }
                    });

    }
}
