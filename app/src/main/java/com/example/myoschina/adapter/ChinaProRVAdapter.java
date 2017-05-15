package com.example.myoschina.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myoschina.R;
import com.example.myoschina.bean.ChinaProjectResponse;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by 若希 on 2017/5/11.
 */

public class ChinaProRVAdapter extends RecyclerView.Adapter<ChinaProRVAdapter.ViewHolder> {

    Context context;
    List<ChinaProjectResponse.ProjectlistBean> projectlist;
    public ChinaProRVAdapter(Context context, List<ChinaProjectResponse.ProjectlistBean> projectlist) {
        this.context=context;
        this.projectlist=projectlist;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_china_project,parent,false);
        ViewHolder vh=new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ChinaProjectResponse.ProjectlistBean projects=projectlist.get(position);
        holder.tvTitle.setText(projects.getName());
        holder.tvContent.setText(projects.getDescription());

    }

    @Override
    public int getItemCount() {
        return projectlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle,tvContent;
        public ViewHolder(View itemView) {

            super(itemView);
            tvTitle=(TextView)itemView.findViewById(R.id.tv_title_item_china);
            tvContent=(TextView)itemView.findViewById(R.id.tv_content_item_china);
        }
    }
}
