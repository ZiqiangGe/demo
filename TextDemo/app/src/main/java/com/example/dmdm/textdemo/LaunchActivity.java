package com.example.dmdm.textdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LaunchActivity extends AppCompatActivity implements View.OnClickListener
{

    FoldTextView1 foldTextView1;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        initialViews();


    }

    private void initialViews()
    {
        Button open = findViewById(R.id.open);
        open.setOnClickListener(this);

        Button close = findViewById(R.id.close);
        close.setOnClickListener(this);

        foldTextView1 = (FoldTextView1)findViewById(R.id.folder_text);
        foldTextView1.setText(getString1());

    }


    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.open:
                foldTextView1.startOpen();
                break;

            case R.id.close:
                foldTextView1.startClose();
                break;

        }
    }


    private String getString()
    {
        String yourText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. "
                + "Ut volutpat interdum interdum. Nulla laoreet lacus diam, vitae "
                + "sodales sapien commodo faucibus. Vestibulum et feugiat enim. Donec "
                + "semper mi et euismod tempor. Sed sodales eleifend mi id varius. Nam "
                + "et ornare enim, sit amet gravida sapien. Quisque gravida et enim vel "
                + "volutpat. Vivamus egestas ut felis a blandit. Vivamus fringilla "
                + "dignissim mollis. Maecenas imperdiet interdum hendrerit. Aliquam"
                + " dictum hendrerit ultrices. Ut vitae vestibulum dolor. Donec auctor ante";
//                + " eget libero molestie porta. Nam tempor fringilla ultricies. Nam sem "
//                + "lectus, feugiat eget ullamcorper vitae, ornare et sem. Fusce dapibus ipsum"
//                + " sed laoreet suscipit. ";
        return yourText;
    }

    private String getString1()
    {
        String yourText = "阿斯顿发  放发送大法师  的法师 爱傻傻的噶  啥风格是卡好打  发斯  蒂芬地方撒手动阀 "
                + "费闪电发货  发发美女合  法化水电费卡戴珊;啦   是否金卡戴珊接  口返回爱打架司  法局开始的发挥"
                + "啊啊啊啊多发所  多付的  说法收到法师打发  就爱大圣伏妖  非奥德赛看风  景奥德赛尽快  发货傻傻的积分 "
                + "妈妈们大叔大妈爱的色放卡掉了身份卡收代理费;;按时非是对方发送砥砺奋进卡死的开了房阿斯蒂芬"
                + "啊啊啊啊多发   所多付的说法  收到法师打发就爱大圣伏妖非奥德赛看风景奥德赛尽快发货傻傻的积分"
                + "volutpat. Vivamus egestas ut felis a blandit. Vivamus fringilla "
                + "dignissim mollis. Maecenas imperdiet interdum hendrerit. Aliquam"
                + " dictum hendrerit ultrices. Ut vitae vestibulum dolor. Donec auctor ante";
//                + " eget libero molestie porta. Nam tempor fringilla ultricies. Nam sem "
//                + "lectus, feugiat eget ullamcorper vitae, ornare et sem. Fusce dapibus ipsum"
//                + " sed laoreet suscipit. ";
        return yourText;
    }
}
