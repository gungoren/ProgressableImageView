package com.gungoren.photoslider;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;

import com.gungoren.view.ProgressableImageView;

public class MainActivity extends AppCompatActivity {

    private ProgressableImageView progressableImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressableImageView = findViewById(R.id.top);
        SeekBar progress = findViewById(R.id.seekbar);
        progress.setProgress(30);
        SeekBar seekBarDividerWidth = findViewById(R.id.seekbar_dividerWidth);
        seekBarDividerWidth.setProgress(2);

        SeekBarChangeListener seekBarChangeListener = new SeekBarChangeListener();
        progress.setOnSeekBarChangeListener(seekBarChangeListener);
        seekBarDividerWidth.setOnSeekBarChangeListener(seekBarChangeListener);




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
        firstProgress.setDuration(2500);
        firstProgress.setRepeatMode(ValueAnimator.REVERSE);
        firstProgress.setRepeatCount(ValueAnimator.INFINITE);

        firstProgress.start();

    }

    class SeekBarChangeListener implements SeekBar.OnSeekBarChangeListener{
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {

                switch (seekBar.getId()) {
                    case R.id.seekbar:
                        float value = (float)progress / seekBar.getMax();
                        progressableImageView.setProgress(value);
                        break;
                    case R.id.seekbar_dividerWidth:
                        progressableImageView.setDividerWidthAsDp(progress);
                        break;
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
    }
}
