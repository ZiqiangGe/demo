package com.example.dmdm.textdemo;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity {


    private static final String TAG = "SplashActivity";

    List<String> urls;

    private ViewPager mViewPager;
    private TextView mIndexText;
    private List<String> mTitles;
    private List<View> mViews;
    private ShowImagesAdapter mAdapter;

    private void initView() {
//        mView = View.inflate(mContext, R.layout.dialog_images_brower, null);
        mViewPager = findViewById(R.id.vp_images);
        mIndexText = (TextView) findViewById(R.id.tv_image_index);
        mTitles = new ArrayList<>();
        mViews = new ArrayList<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_images_brower);

//        getDeviceDensity();
        urls = new ArrayList<>();
//        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1549655758434&di=07b7e8336e899f628c0236e5d7832aa7&imgtype=0&src=http%3A%2F%2Fimg1a.xgo-img.com.cn%2Fpics%2F1538%2F1537501.jpg");
//        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1549655758626&di=87c09b7d33e227235e272a39070aa9fb&imgtype=0&src=http%3A%2F%2Fg-search4.alicdn.com%2Fbao%2Fuploaded%2Fi2%2FTB1G3xTHVXXXXaDXVXXXXXXXXXX_%2521%25212-item_pic.png");
//        urls.add("https://img-blog.csdn.net/20170710093914048?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvZ2l0aHViXzIwMTE=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center");
//        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1549655758434&di=07b7e8336e899f628c0236e5d7832aa7&imgtype=0&src=http%3A%2F%2Fimg1a.xgo-img.com.cn%2Fpics%2F1538%2F1537501.jpg");
//        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1549655758626&di=87c09b7d33e227235e272a39070aa9fb&imgtype=0&src=http%3A%2F%2Fg-search4.alicdn.com%2Fbao%2Fuploaded%2Fi2%2FTB1G3xTHVXXXXaDXVXXXXXXXXXX_%2521%25212-item_pic.png");
//        urls.add("https://avatars0.githubusercontent.com/u/18515372?s=400&u=2e235d7c67700346479cfbd2c4b930c44528bb4e&v=4");

        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1549655758626&di=87c09b7d33e227235e272a39070aa9fb&imgtype=0&src=http%3A%2F%2Fg-search4.alicdn.com%2Fbao%2Fuploaded%2Fi2%2FTB1G3xTHVXXXXaDXVXXXXXXXXXX_%2521%25212-item_pic.png");
        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1549655758626&di=87c09b7d33e227235e272a39070aa9fb&imgtype=0&src=http%3A%2F%2Fg-search4.alicdn.com%2Fbao%2Fuploaded%2Fi2%2FTB1G3xTHVXXXXaDXVXXXXXXXXXX_%2521%25212-item_pic.png");
        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1549655758626&di=87c09b7d33e227235e272a39070aa9fb&imgtype=0&src=http%3A%2F%2Fg-search4.alicdn.com%2Fbao%2Fuploaded%2Fi2%2FTB1G3xTHVXXXXaDXVXXXXXXXXXX_%2521%25212-item_pic.png");
        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1549655758626&di=87c09b7d33e227235e272a39070aa9fb&imgtype=0&src=http%3A%2F%2Fg-search4.alicdn.com%2Fbao%2Fuploaded%2Fi2%2FTB1G3xTHVXXXXaDXVXXXXXXXXXX_%2521%25212-item_pic.png");
//        findViewById(R.id.btn_show_imgs).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new ShowImagesDialog(SplashActivity.this, urls).show();
//            }
//        });


//        final ImageView imageView = findViewById(R.id.image);
//        Glide.with(this)
//                .load(urls.get(2))
//                .placeholder(R.mipmap.ic_launcher)
//                .error(R.mipmap.ic_launcher)
//                .into(new SimpleTarget<GlideDrawable>() {
//                    @Override
//                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
//                        Log.i(TAG, "onResourceReady: ");
//                        imageView.setImageDrawable(resource);
//                    }
//                });

        initView();

        initData();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            getWindow().setNavigationBarColor(Color.parseColor("#000000"));
            //getWindow().setNavigationBarColor(getResources().getColor(R.color.black));
            //getWindow().setNavigationBarColor(Color.BLUE);
        }
    }

    private void initData() {
//        PhotoViewAttacher.OnPhotoTapListener listener = new PhotoViewAttacher.OnPhotoTapListener() {
//            @Override
//            public void onPhotoTap(View view, float x, float y) {
//                dismiss();
//            }
//        };
        for (int i = 0; i < urls.size(); i++)
        {
            final ImageView photoView = new ImageView(this);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            photoView.setLayoutParams(layoutParams);
            photoView.setImageBitmap(BitmapFactory.decodeResource(this.getResources(),R.drawable.aaaa));
//            photoView.setOnPhotoTapListener(listener);
            Glide.with(this)
                    .load(urls.get(i))
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(new SimpleTarget<GlideDrawable>() {
                        @Override
                        public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                            photoView.setImageDrawable(resource);
                        }
                    });
            mViews.add(photoView);
            mTitles.add(i + "");
        }


        mAdapter = new ShowImagesAdapter(mViews, mTitles);
        mViewPager.setAdapter(mAdapter);
        mIndexText.setText(1 + "/" + urls.size());
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mIndexText.setText(position + 1 + "/" + urls.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    public static class ShowImagesAdapter extends PagerAdapter {

        private List<View> views;
        private List<String> titles;

        public ShowImagesAdapter(List<View> views, List<String> titles) {
            this.views = views;
            this.titles = titles;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView(views.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            ((ViewPager) container).addView(views.get(position));
            return views.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles == null ? "" : titles.get(position);
        }

    }

    /**
     * 获取当前设备的屏幕密度等基本参数
     */
    protected void getDeviceDensity() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        Config.EXACT_SCREEN_HEIGHT = metrics.heightPixels;
        Config.EXACT_SCREEN_WIDTH = metrics.widthPixels;
    }
}
