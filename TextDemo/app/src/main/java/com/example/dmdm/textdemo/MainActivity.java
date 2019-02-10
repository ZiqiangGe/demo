package com.example.dmdm.textdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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



//        ExpandableTextView expandableTextView = (ExpandableTextView) findViewById(R.id.lorem_ipsum);
//        expandableTextView.setText(yourText);
//
//        FoldTextView foldTextView = (FoldTextView)findViewById(R.id.folder_text);
//        foldTextView.setText(yourText);

        FoldTextView1 foldTextView1 = (FoldTextView1)findViewById(R.id.folder_text1);
        foldTextView1.setText(yourText);

    }
}
