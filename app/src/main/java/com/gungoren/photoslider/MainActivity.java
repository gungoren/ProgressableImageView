package com.gungoren.photoslider;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gungoren.view.ProgressDirection;
import com.gungoren.view.ProgressableImageView;

public class MainActivity extends AppCompatActivity {

    private ProgressableImageView progressableImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressableImageView = findViewById(R.id.top);

        ValueAnimator firstProgress = ValueAnimator.ofInt(0, 100);
        firstProgress.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();

                /*int value = animatedValue / 20;
                int[] colors = getResources().getIntArray(R.array.rainbow);
                if (value >= colors.length)
                    return;
                int color = colors[value];
                progressableImageView.setDividerColor(color);*/

                //float value = (float)animatedValue / 5;
                //progressableImageView.setDividerWidthAsDp(animatedValue);

                float value = (float)animatedValue / 100;
                progressableImageView.setProgress(value);

            }
        });
        progressableImageView.setDirection(ProgressDirection.bottom_to_top);
        firstProgress.setDuration(2500);
        firstProgress.setRepeatMode(ValueAnimator.REVERSE);
        firstProgress.setRepeatCount(ValueAnimator.INFINITE);

        firstProgress.start();
    }
}
