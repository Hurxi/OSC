package com.example.myoschina;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Test5Activity extends AppCompatActivity {
    boolean isStart=false;
    //创建传感器管理器的对象
    SensorManager sensorManager;//传感器管理器 负责注册相关传感器 监听对应的动作
    TextView tv1;
    Animation mAnimation,mAnimation2;//描述运动轨迹
    ImageView ivTop,ivBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test5);

        ivTop=(ImageView)findViewById(R.id.iv_top);
        ivBottom=(ImageView)findViewById(R.id.iv_bottom);

//        tv1=(TextView)findViewById(R.id.tv1);
        //直接通过系统拿到
        sensorManager= (SensorManager) getSystemService(SENSOR_SERVICE);
        //通过sensorManager注册相关传感器
        sensorManager.registerListener(sensorEventListener,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        //参数：监听器 传感器类型 传感器接收的频率(此例子中是加速度传感器）

    }

    private SensorEventListener sensorEventListener=new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            //参数值 event包含一些传感器的信息 此例中包含一些加速度的值
            float[] values=event.values;
            float x=values[0];//x轴方向的加速度值
            float y=values[1];//y轴方向的加速度值
            float z=values[2];//z轴方向的加速度值
            int medumValue=15;
            if (Math.abs(x)>medumValue||Math.abs(y)>medumValue||Math.abs(z)>medumValue){
//                Toast.makeText(Test5Activity.this, "摇一摇", Toast.LENGTH_SHORT).show();
                if (isStart==false) {
                    yaoyiyao();
                }
            }
//            Log.e("values:","x="+x+",y="+y+",z="+z);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    private void yaoyiyao() {
       mAnimation= AnimationUtils.loadAnimation(this,R.anim.translate_1);
       mAnimation2= AnimationUtils.loadAnimation(this,R.anim.translate_2);

//        tv1.startAnimation(mAnimation);
        ivTop.startAnimation(mAnimation);
        ivBottom.startAnimation(mAnimation2);
        isStart=true;
        Toast.makeText(Test5Activity.this, "摇一摇", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                isStart=false;
            }
        },5000);
    }
}
