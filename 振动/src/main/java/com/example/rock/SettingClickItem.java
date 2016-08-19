package com.example.rock;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by acer on 2016/8/5.
 */
public class SettingClickItem extends RelativeLayout{

    private static final String TAG = "SettingItem";
    private Context ctx;
    private TextView tv_setting_description;
    private TextView tv_setting_title;
    private String settingonString;
    private String settingoffString;
    private String settingSpKey;
    private String settingtitle ;


    public SettingClickItem(Context context) {
        super(context);
        ctx = context;
        init(null);
    }



    public SettingClickItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        ctx =context;
        init(attrs);
    }



    //以下代码是在一个方法里调用自己想要实现的一个接口里面的全部方法方法
    //定义一个函数传入一自定义监听器参数  实现一个自定义监听器接口(里面有想要其他方法实现的方法)


    //如果是一个类当然可以直接继承，但是如果是一个方法想要同时实现几个自定义方法显然只有设置接口这种方法，并且传入接口参数
    private MySettingItemOnCheckStateChangeListener  onCheckStateChangeListener;
    public interface  MySettingItemOnCheckStateChangeListener{
        void onChecked();
        void onUnChecked();
    }

    public void SetMySettingItemOnCheckStateChangeListener(MySettingItemOnCheckStateChangeListener l){
        onCheckStateChangeListener =l;
    }





    private void init(AttributeSet attrs) {

        Log.i(TAG,"init start");

        final SharedPreferences config =ctx.getSharedPreferences("config",Context.MODE_PRIVATE);


        if(attrs!=null){


            settingtitle = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "settingtitle");
            settingonString = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "settingonString");
            settingoffString = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "settingoffString");
            settingSpKey = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "settingSpKey");
        }

        View inflate = View.inflate(ctx,R.layout.item_setting, null);
        addView(inflate);

         tv_setting_title = (TextView) inflate.findViewById(R.id.tv_setting_title);

         tv_setting_description = (TextView) inflate.findViewById(R.id.tv_setting_description);


        //根据sp里保存的用户设置，去在页面初始化的时候 回显用户设置
        boolean state = config.getBoolean(settingSpKey,false);

        tv_setting_description.setText(state?settingonString:settingoffString);
        tv_setting_title.setText(settingtitle);



        //怎么就直接设置了一个监听
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i(TAG,"onclick");

              //  boolean checked = cb_settting_checkbox.isChecked();

                boolean stateFromSp = MyApplication.getStateFromSp(settingSpKey);

                MyApplication.saveStateToSp(settingSpKey,!stateFromSp);
                Log.i(TAG,""+stateFromSp);

               /* SharedPreferences.Editor edit = config.edit();
                edit.putBoolean(settingSpKey,checked);
                edit.commit();*/

                Log.i(TAG,stateFromSp+"");



            }
        });




    }
}


