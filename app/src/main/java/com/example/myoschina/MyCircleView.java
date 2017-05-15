package com.example.myoschina;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by 若希 on 2017/5/12.
 */

public class MyCircleView extends View {
    Context context;
    int radiams[]=new int[]{90,90,90,90,90};
    int radiam=90;//初始角度
    public MyCircleView(Context context) {
        this(context,null);
    }

    public MyCircleView(Context context, AttributeSet attrs) {
        //第二个参数 属性集 内部包含所有的属性
        this(context, attrs,0);
        timeThread.start();
    }

    public MyCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
    }


    private Thread timeThread=new Thread(){
        @Override
        public void run() {

                try {
                    while (true){
                        Thread.sleep(50);
                        for (int i = 0; i < 5; i++) {
                            if (i==2||i==3){
                                radiams[i]-=i+1;
                            }
                            else {
                                radiams[i]+=i+1;
                            }

                        }
                        updateHandler.sendEmptyMessage(0);
                    }


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


        }
    };

    private Handler updateHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //刷新
            invalidate();
        }
    };

    //用来绘制自定义view
    //canvas 画布
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int r=100;
        for (int i = 0; i < 5; i++) {
            //空心圆
            Paint paint=new Paint();//画笔
            paint.setStyle(Paint.Style.STROKE);//规定画笔画一个空心的内容
            paint.setColor(getResources().getColor(R.color.colorWhite));
            paint.setStrokeWidth(0.8f);//宽度
            paint.setAntiAlias(true);//抗锯齿
            //画
            canvas.drawCircle(getWidth()/2,getHeight()/2,r,paint);//圆心横纵坐标 半径  画笔


            //实心圆
            Paint paintPoint=new Paint();
            paintPoint.setAntiAlias(true);
            paintPoint.setColor(getResources().getColor(R.color.colorGrey));

            int x=(int)(getWidth()/2+r*Math.sin(Math.PI*radiams[i]/180));
            int y=(int)(getHeight()/2+r*Math.cos(Math.PI*radiams[i]/180));


            canvas.drawCircle(x,y,8,paintPoint);
            //每隔一定时间增加角度 刷新view
            r=r+(i+1)*30;

            }



    }

    //用来测量该自定义view的大小
    //三种测量模式
    //EXACTLY——100dp
    //AT_MOST——wrap_content
    //UN......(很少用）
    @Override               //01100 前面是测量模式 后是值
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(myMeasure(widthMeasureSpec),myMeasure(heightMeasureSpec));
    }

    //自定义测量方式
    private int myMeasure(int origin) {
        int result=200;//默认宽高
        //测量宽或者高
        //获取测量模式
        int specMode=MeasureSpec.getMode(origin);
        //获取具体值
        int specSize=MeasureSpec.getSize(origin);
        if (specMode==MeasureSpec.EXACTLY){
            //精准测量模式
            result=specSize;
        }
        else if (specMode==MeasureSpec.AT_MOST){
            result=Math.min(result,specSize);
        }
        return result;
    }
}
