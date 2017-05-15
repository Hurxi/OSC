package com.example.myoschina.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myoschina.R;
import com.example.myoschina.adapter.CompreBlogRVAdapter;
import com.example.myoschina.adapter.CompreQuestionRVAdapter;
import com.example.myoschina.bean.BlogRecListResponse;
import com.example.myoschina.bean.QuestionListResponse;
import com.example.myoschina.utils.OSChinaApi;
import com.example.myoschina.utils.PreferencesUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by 若希 on 2017/5/2.
 */

public class QuestionFragment extends Fragment {
    List<QuestionListResponse.PostListBean> questionList;
    CompreQuestionRVAdapter adapter;
    int pageIndex=1;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SpringView springView=(SpringView)view.findViewById(R.id.spring_question);
        springView.setHeader(new DefaultHeader(getContext()));//设置头布局
        springView.setFooter(new DefaultFooter(getContext()));//设置尾布局
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                //刷新数据
                questionList.clear();
                pageIndex=1;
                getData();
            }

            @Override
            public void onLoadmore() {
                //上拉加载更多
                pageIndex++;
                getData();
            }
        });

        //处理控件
        questionList=new ArrayList<>();
        RecyclerView rv= (RecyclerView) view.findViewById(R.id.rv_comprehensive_question);


        adapter=new CompreQuestionRVAdapter(getContext(),questionList);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);
        getData();
    }

    private void getData() {
        String token= PreferencesUtils.getString("access_token");
//        String token=getContext().getSharedPreferences("oschina", Context.MODE_PRIVATE).getString("access_token","");
        OkGo.get(OSChinaApi.POST_LIST)
                .tag(this)
                .params("access_token",token)
                .params("catalog",1)
                .params("page",pageIndex)
                .params("pageSize",20)
                .params("dataType","json")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.d("s:",s);
                        s=s.replaceAll("\"answer\":\"\"","\"answer\":"+"{\"name\":\"default\""+","+"\"time\":\"1970-01-01 00:00:01\"}");
                        s=s.replaceAll("https://static.oschina.net","http://www.oschina.net/");
                        Gson gson=new Gson();




//                        NewsListResponse newsListResponse=gson.fromJson(s,NewsListResponse.class);
//                        List<NewsListResponse.NewslistBean> a=newsListResponse.getNewslist();
//                        newsList.addAll(a);
//                        adapter.notifyDataSetChanged();


//                        Gson gson=new GsonBuilder()
//                                .registerTypeHierarchyAdapter(QuestionListResponse.PostListBean.AnswerBean.class,
//                                        new JsonDeserializer<QuestionListResponse.PostListBean.AnswerBean>() {
//                                            @Override
//                                            public QuestionListResponse.PostListBean.AnswerBean deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
//                                                //逻辑
//                                                Gson newGson=new Gson();
//                                                QuestionListResponse.PostListBean.AnswerBean answer;
//                                                try{
//
//
//                                                answer=newGson.fromJson(json, QuestionListResponse.PostListBean.AnswerBean.class);
//                                            }
//                                                catch (Exception e){
//                                                    answer=new QuestionListResponse.PostListBean.AnswerBean();
//                                                    answer.setName("");
//                                                    answer.setTime("");
//                                                }
//
//                                                return answer;
//                                            }
//                                        })
//                                .create();



                        QuestionListResponse questionListResponse=gson.fromJson(s,QuestionListResponse.class);
                        List<QuestionListResponse.PostListBean> a=questionListResponse.getPost_list();


                        questionList.addAll(a);
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_question,container,false);
        return view;
    }
}
