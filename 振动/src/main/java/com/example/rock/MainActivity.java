package com.example.rock;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    private SeekBar seekBar_red;
    private SeekBar seekBar_green;
    private SeekBar seekBar_blue;
    private SeekBar seekBar_all;
    private static final String TAG ="MainActivity" ;
    private long[] arrays;
    private Button btn_mainactivity_beginvibrate;
    private Button btn_mainactivity_endvibrate;
    private CheckBox cb_mainactivity_repeat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar_red = (SeekBar) findViewById(R.id.seekBar_red);
        seekBar_green = (SeekBar) findViewById(R.id.seekBar_green);
        seekBar_blue = (SeekBar) findViewById(R.id.seekBar_blue);
        seekBar_all = (SeekBar) findViewById(R.id.seekBar_all);

        seekBar_red.setMax(1000);
        seekBar_green.setMax(1000);
        seekBar_blue.setMax(1000);
        seekBar_all.setMax(1000);

        arrays = new long[]{0,0,0,0};

        SeekBar.OnSeekBarChangeListener listener=  new SeekBar.OnSeekBarChangeListener() {

            //当用户改变进度的时候调用
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                switch (seekBar.getId()){
                    case  R.id.seekBar_red:
                        arrays[0]=progress;
                        break;
                    case  R.id.seekBar_green:
                        arrays[1]=progress;
                        break;
                    case  R.id.seekBar_blue:
                        arrays[2]=progress;
                        break;
                    case  R.id.seekBar_all:
                        arrays[3]=progress;
                        break;
                }

                Log.i(TAG,"onProgressChanged"+progress);
            }

            //开始拖动进度条
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.i(TAG,"onProgressChanged");
            }


            //停止拖动进度条
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.i(TAG,"onProgressChanged");
            }
        };

        seekBar_red.setOnSeekBarChangeListener(listener);
        seekBar_green.setOnSeekBarChangeListener(listener);
        seekBar_blue.setOnSeekBarChangeListener(listener);
        seekBar_all.setOnSeekBarChangeListener(listener);

        btn_mainactivity_beginvibrate = (Button) findViewById(R.id.btn_mainactivity_beginvibrate);
        cb_mainactivity_repeat = (CheckBox) findViewById(R.id.cb_mainactivity_repeat);
        btn_mainactivity_endvibrate = (Button) findViewById(R.id.btn_mainactivity_endvibrate);
    }

    public void begin(View v){
        vibrator(arrays);
    }

    public void end(View v){
        vibrator(new long[]{0,0,0,0}); //怎么结束？
    }

    private void vibrator(long[] longs) {

        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        boolean checked = cb_mainactivity_repeat.isChecked();
        if(checked){

            //振动的频率
            //是否重复振动
            //-1不重复，非-1重复  如果是非-1表示从振动频率的那个元素开始振动
            vibrator.vibrate(longs,1);//振动的持续时间

        }else{

            vibrator.vibrate(longs,-1);//振动的持续时间
        }
    }


    public void share(View v) {
        //短信分享
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,arrays+"是我喜欢的节奏，你也来体验一下吧");
        startActivity(intent);
    }
    public void nextactivity(View v) {

        startActivity(new Intent(getApplicationContext(),ToastActivity.class));

    }
}
