package com.epicodus.discussionforum.ui;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.epicodus.discussionforum.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CommentActivity extends AppCompatActivity {
    private AnimationDrawable frameAnimation;

    @Bind(R.id.birds) ImageView mBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        ButterKnife.bind(this);

        mBackground.setBackgroundResource(R.drawable.background_animated);
        frameAnimation = (AnimationDrawable) mBackground.getBackground();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

        frameAnimation.start();
        super.onWindowFocusChanged(hasFocus);
    }
}
