package com.example.vahan.searchexample;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.squareup.picasso.Picasso;

public class ScrollingActivity extends AppCompatActivity {
    private VideoView video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setImage();
        setTitle();
        sendMail();
        makeCall();
        video = findViewById(R.id.video);

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(video);
        video.setVideoPath(getString(R.string.video));

        video.setMediaController(mediaController);


        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.BOTTOM;
        lp.setMargins(100, 0, 0, 0);
        lp.width = 770;
        lp.height = 100;
        mediaController.setLayoutParams(lp);

        ((ViewGroup) mediaController.getParent()).removeView(mediaController);

        ((FrameLayout) findViewById(R.id.frameWrapperVideo)).addView(mediaController);

        mediaController.hide();


    }

    private void makeCall() {
        ImageView phoneImageFloating = findViewById(R.id.phoneBtnFloating);
        phoneImageFloating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = getIntent().getStringExtra(String.valueOf(MyAdapter.MY_KEY4));
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + phoneNumber));
                startActivity(intent);
            }
        });
    }

    private void sendMail() {
        ImageView emailImageFloating = findViewById(R.id.emailBtnFloating);
        emailImageFloating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = getIntent().getStringExtra(String.valueOf(MyAdapter.MY_KEY3));
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", email, null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject"); // Whatever subject
                emailIntent.putExtra(Intent.EXTRA_TEXT, email);
                startActivity(Intent.createChooser(emailIntent, "Send email"));
            }
        });
    }

    private void setTitle() {
        String title = getIntent().getStringExtra(String.valueOf(MyAdapter.MY_KEY2));
        CollapsingToolbarLayout toolbarLayout = findViewById(R.id.toolbar_layout);
        toolbarLayout.setTitle(title);
    }

    private void setImage() {
        ImageView imageUser = findViewById(R.id.img);
        String url = getIntent().getStringExtra(String.valueOf(MyAdapter.MY_KEY));
        Picasso.get().load(url).into(imageUser);
    }
}
