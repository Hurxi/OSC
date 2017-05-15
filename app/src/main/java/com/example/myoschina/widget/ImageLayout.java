package com.example.myoschina.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.myoschina.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by 若希 on 2017/5/5.
 */

public class ImageLayout extends LinearLayout {
    Context context;
    String[] urlImage;

    public ImageLayout(Context context) {
        this(context, null);
    }

    public ImageLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
    }

    public ImageLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public void setImage(Context context, String urlImage) {
        this.urlImage = urlImage.split(",");
        init();
    }

    private void init() {
//        ImageView image = new ImageView(context);
//        LayoutParams params = new LayoutParams(0, 150, 1);
//        if (urlImage != null) {
//            image.setVisibility(View.VISIBLE);
//            Picasso.with(context).load(urlImage[0])
//                    .placeholder(R.color.colorPrimary)
//                    .into(image);
//            addView(image, params);
//        }
        setOrientation(VERTICAL);
        int count=urlImage.length;
        int lines;
//        lines=(count-1)/3+1;
        if (count<=3){
            lines=1;
        }
        else if (count>3&&count<=6){
                lines=2;
            }
        else {
              lines=3;
            }

        for (int i=0;i<lines;i++){
            LinearLayout linearLayout=new LinearLayout(context);
            linearLayout.setOrientation(HORIZONTAL);
            for (int j = i*3; j <(i+1)*3; j++) {
                SimpleDraweeView simpleDreeView=new SimpleDraweeView(context);
//                ImageView imageView=new ImageView(context);
                String url="";//https://www.oschina.net/uploads/space/2017/0505/130325_kjTh_1993928_thumb.jpg
//                if (urlImage[j]!=null){
//
//                }
//                else {
//
//                }


                    try {
                    url=urlImage[j];
                    if (j>=1){
                        url="https://www.oschina.net/uploads/space/"+urlImage[j];
                    }
                }
                catch (Exception e){
                    url="";
                }


                simpleDreeView.setImageURI(url);
//                Picasso.with(context).load(url)
//                    .placeholder(R.color.colorPrimary)
//                    .into(imageView);
                LayoutParams params=new LayoutParams(0,360,1);
                linearLayout.addView(simpleDreeView,params);
            }
            addView(linearLayout);//将每一行 添加到本布局
        }
    }
}
