package com.gungoren.photoslider;

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
