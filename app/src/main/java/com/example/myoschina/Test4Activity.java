package com.example.myoschina;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.myoschina.bean.TweetNewestResponse;
import com.example.myoschina.utils.OSChinaApi;
import com.example.myoschina.utils.PreferencesUtils;
import com.example.myoschina.widget.ImageLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class Test4Activity extends AppCompatActivity {
    //九宫格图图像
    Button btnImage;

    LinearLayout ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test4);
        ll=(LinearLayout)findViewById(R.id.activity_test4);
        btnImage=(Button)findViewById(R.id.btn_image);
//        btnImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                loadImage();
//            }
//        });
//        ImageLayout imageLayout=(ImageLayout)findViewById(R.id.il_test4);
//        String url="https://oschina.net/uploads/space/2017/0505/130325_kjTh_1993928_thumb.jpg,/2017/0505/130325_kjTh_1993928_thumb.jpg,/2017/0505/130325_kjTh_1993928_thumb.jpg";
//        String[] urlImage=url.split(",");
////        imageLayout.setImage(urlImage[0],null);

        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Test4Activity.this, CaptureActivity.class);
                startActivityForResult(intent,1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
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
                        Toast.makeText(this, "解析结果"+resule, Toast.LENGTH_SHORT).show();
                    }
                    else {
                        //失败
                        Toast.makeText(this, "解析失败", Toast.LENGTH_SHORT).show();

                    }
                }
                break;
        }
    }

    private void loadImage() {
        //1，请求数据
        //2.加载图片
        String token= PreferencesUtils.getString("access_token");
        OkGo.get(OSChinaApi.TWEET_LIST)
                .tag(this)
                .params("access_token",token)
                .params("user",0)
                .params("page",1)
                .params("pageSize",20)
                .params("dataType","json")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.d("s:", s);
                        s=s.replaceAll("https://static.oschina.net","http://www.oschina.net/");

//                        SimpleDraweeView simpleDreeView=new SimpleDraweeView(Test4Activity.this);
//                        simpleDreeView.setImageURI("https://www.oschina.net/uploads/space/2017/0505/130325_kjTh_1993928_thumb.jpg");
//                        simpleDreeView.setBackgroundColor(Color.RED);


                        ImageLayout imageLoad=new ImageLayout(Test4Activity.this);
                        imageLoad.setImage(Test4Activity.this,"https://www.oschina.net/uploads/space/2017/0505/130325_kjTh_1993928_thumb.jpg,2017/0505/130325_kjTh_1993928_thumb.jpg,2017/0505/130325_kjTh_1993928_thumb.jpg,2017/0505/130325_kjTh_1993928_thumb.jpg");
                        ll.addView(imageLoad);
                    }
                });
    }
}
