package com.example.viewpagertest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;

import me.relex.circleindicator.CircleIndicator3;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 mPager;
    private FragmentStateAdapter pagerAdapter;
    private ImageView imageView;
    //private int num_page = 4;
    private int num_page = 15; // 크롤링해서 받은 가게 개수
    private CircleIndicator3 mIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        imageView = (ImageView) findViewById(R.id.Image);
        String url = "https://search.pstatic.net/common/?autoRotate=true&quality=95&type=w750&src=https%3A%2F%2Fmyplace-phinf.pstatic.net%2F20220207_57%2F1644207829379txVEu_JPEG%2Fupload_6676b8d172333905dcfe7232cabe1d5c.jpg";
        ImageLoadTask task = new ImageLoadTask(url,imageView);
        task.execute();


        // Bundle 실습
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        String str = "넘겨진 정보";
        Bundle bundle = new Bundle();
        bundle.putString("data2", str);
        Fragment_1 fragment = new Fragment_1(0);
        fragment.setArguments(bundle);
        transaction.commitNow();
        transaction.replace(R.id.FrameLayout, fragment);
        transaction.commit();



/**
 * 가로 슬라이드 뷰 Fragment
 */
//ViewPager2
        mPager = findViewById(R.id.viewpager);
//Adapter
        pagerAdapter = new com.example.viewpagertest.MyAdapter(this, num_page);
        mPager.setAdapter(pagerAdapter);
//Indicator
        mIndicator = findViewById(R.id.indicator);
        mIndicator.setViewPager(mPager);
        mIndicator.createIndicators(num_page,0);
//ViewPager Setting
        mPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
/**
 * 이 부분 조정하여 처음 시작하는 이미지 설정.
 * 2000장 생성하였으니 현재위치 1002로 설정하여
 * 좌 우로 슬라이딩 할 수 있게 함. 거의 무한대로
 */
        mPager.setCurrentItem(0); //시작 지점
        //mPager.setOffscreenPageLimit(4); //최대 이미지 수
        mPager.setOffscreenPageLimit(100); //최대 이미지 수
        mPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if (positionOffsetPixels == 0) {
                    mPager.setCurrentItem(position);
                }
            }
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mIndicator.animatePageSelected(position%num_page);
            }
        });
    }



    public class shop_info {
        String shop_name;
        String image_url;
        String Info;
    }

}