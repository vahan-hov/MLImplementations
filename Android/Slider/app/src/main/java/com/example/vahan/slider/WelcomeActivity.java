package com.example.vahan.slider;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {

    private int[] layouts = {R.layout.first_slide, R.layout.second_slide, R.layout.third_slide};
    private LinearLayout Dots_Layout;
    private Button BtnSkip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (new PreferenceManager(this).checkPreference()) {
            loadHome();
        }
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        setContentView(R.layout.activity_welcome);

        ViewPager mPager = findViewById(R.id.viewPager);
        MPagerAdapter mPagerAdapter = new MPagerAdapter(layouts, this);
        mPager.setAdapter(mPagerAdapter);

        Dots_Layout = findViewById(R.id.dotsLayout);

        BtnSkip = findViewById(R.id.btnskip);
        BtnSkip.setOnClickListener(this);
        createDots(0);


        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                createDots(position);
                if (position == layouts.length - 1) {
                    BtnSkip.setText(R.string.start);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void createDots(int current_position) {
        if (Dots_Layout != null) {
            Dots_Layout.removeAllViews();

            ImageView[] dots = new ImageView[layouts.length];

            for (int i = 0; i < layouts.length; ++i) {
                dots[i] = new ImageView(this);
                if (i == current_position) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.activedots));
                } else {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.default_dots));
                }
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.setMargins(4, 0, 4, 0);
                Dots_Layout.addView(dots[i], params);
            }
        }
    }

    @Override
    public void onClick(View view) {
        loadHome();
        new PreferenceManager(this).writePrefernce();
    }

    private void loadHome() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
