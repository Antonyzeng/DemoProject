package com.example.rock;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class ToastActivity extends AppCompatActivity {

    private static final String TAG = "ToastActivity";
    WindowManager windowManager;
    private View view;
    private SettingClickItem scv_setting_changedbg;

    //TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);
        scv_setting_changedbg = (SettingClickItem) findViewById(R.id.scv_setting_changedbg);
        setTouch();
    }

    private void setTouch() {
        scv_setting_changedbg.setOnTouchListener(new View.OnTouchListener() {

            private int newY1;
            private int newY;
            private int newX;
            private int startY;
            private int startX;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //获取控制执行的事件
                switch(event.getAction()){

                    case MotionEvent.ACTION_DOWN:
                        startX = (int) event.getRawX();
                        startY = (int) event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        newX = (int) event.getRawX();
                        newY1 = (int) event.getRawY();

                        int dX=newX-startX;
                        int dY=newY-startY;

                        //l代表左边的距离 t代表距离上面的距离   r代表右边框距离屏幕左边框的距离 b代表底边框距离屏幕顶边框的距离
                        int l=scv_setting_changedbg.getLeft();
                        int t=scv_setting_changedbg.getTop();

                        l+=dX;
                        t+=dY;
                        int r= l+scv_setting_changedbg.getWidth();
                        int b=t+scv_setting_changedbg.getHeight();

                        scv_setting_changedbg.layout(l,t,r,b);
                        startX=newX;
                        startY=newY;


                        break;
                    case MotionEvent.ACTION_UP:


                        break;

                }

                return true;
            }
        });

    }

    public void buttonshowToast(View v){
        showToast("德国慕尼黑"+392832348383D);
    }

    public void showToast(String queryAddress){

        Log.i(TAG,"showToast");

        //1.获取windowManager
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);



        view = View.inflate(getApplicationContext(), R.layout.item_toast, null);

        //初始化控件
        TextView tv_toastcustom_address = (TextView) view.findViewById(R.id.tv_toastcustom_address);
        tv_toastcustom_address.setText("傻逼曾来电话了");

        //3.设置Toast属性
        //LayoutParams是Toast扽属性，控件要添加到哪个父控件中，
        WindowManager.LayoutParams params =   new WindowManager.LayoutParams();
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;// 高度包裹内容
        params.width = WindowManager.LayoutParams.WRAP_CONTENT; // 宽度包裹内容
        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE // 没有焦点
                |
                // WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE // 不可触摸
                // |
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON; // 保持当前屏幕
        params.format = PixelFormat.TRANSLUCENT; // 透明
        params.type = WindowManager.LayoutParams.TYPE_PRIORITY_PHONE; // 使用TYPE_PHONE//

        //效果冲突，以默认的为主
        params.gravity = Gravity.LEFT|Gravity.TOP;

        params.x=100;//表示距离边框的一个距离，根据gravity来设置的，如果gravity是left表示距离左边框的距离，
                    // 如果是right表示距离右边框
        params.y=100;


        // 执行toast的类型,toast天生是没有获取见到和触摸事件

        //将view对象添加到windowManager中
        //params layoutparams 控件属性
        //将params属性设置给view对象，并添加到windowManager中
        windowManager.addView(view,params);

    }
    public void hideToast(){
        if(windowManager!=null&&view!=null){
            windowManager.removeView(view);
            windowManager=null;
        }
    }
}
