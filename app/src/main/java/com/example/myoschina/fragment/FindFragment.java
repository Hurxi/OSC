package com.example.myoschina.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.myoschina.ProjectActivity;
import com.example.myoschina.R;
import com.example.myoschina.Test5Activity;
import com.example.myoschina.YaoActivity;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

/**
 * Created by 若希 on 2017/5/11.
 */

public class FindFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_find,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        Button btnYao=(Button)view.findViewById(R.id.btn_yao_find);
//        Button btnSao=(Button)view.findViewById(R.id.btn_sao_find);
//        Button btnSoft=(Button)view.findViewById(R.id.btn_software_find);
        LinearLayout llSoft=(LinearLayout) view.findViewById(R.id.ll_soft_find);
        LinearLayout llShake=(LinearLayout) view.findViewById(R.id.ll_yao_find);
        LinearLayout llScan=(LinearLayout) view.findViewById(R.id.ll_scan_find);

        llShake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), YaoActivity.class);
                startActivity(intent);
            }
        });
        llScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), CaptureActivity.class);
                startActivityForResult(intent,1);
            }
        });
        llSoft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent intent=new Intent(getContext(), ProjectActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:
                if (data!=null){
                    Bundle bundle=data.getExtras();
                    if (bundle==null){
                        return;
                    }
                    if (bundle.getInt(CodeUtils.RESULT_TYPE)==CodeUtils.RESULT_SUCCESS){
                        //成功
                        String resule=bundle.getString(CodeUtils.RESULT_STRING);
                        Toast.makeText(getContext(), "解析结果"+resule, Toast.LENGTH_SHORT).show();
                    }
                    else {
                        //失败
                        Toast.makeText(getContext(), "解析失败", Toast.LENGTH_SHORT).show();

                    }
                }
                break;
        }
    }
}
