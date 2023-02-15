package com.example.cs4520_inclass_rishabh0516.inClass04;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cs4520_inclass_rishabh0516.R;
import com.example.cs4520_inclass_rishabh0516.inClass01.InClass01;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InClass04 extends AppCompatActivity {
    private Button button_generate;
    private SeekBar seekBar;
    private TextView input_text;
    private ProgressBar progressBar;
    private TextView min_text;
    private TextView max_text;
    private TextView average_text;
    private Handler messageQueue;
    private ExecutorService threadPool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_class04);
        button_generate = findViewById(R.id.button_generate);
        seekBar = findViewById(R.id.seekBar);
        input_text = findViewById(R.id.input_text);
        progressBar = findViewById(R.id.progressBar);
        min_text = findViewById(R.id.min_text);
        max_text = findViewById(R.id.max_text);
        average_text = findViewById(R.id.average_text);

        threadPool = Executors.newFixedThreadPool(1);
        setTitle("Number Generator");


        messageQueue = new Handler(Looper.getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message message) {
                switch(message.what){
                    case HeavyWork.STATUS_PROGRESS:
                        Bundle receivedData = message.getData();
                        int progress = receivedData.getInt(HeavyWork.KEY_PROGRESS);
                        progressBar.setProgress(progress);
                        break;
                    case HeavyWork.STATUS_ARRAY:
                        Bundle receivedArray = message.getData();
                        double [] numbers = receivedArray.getDoubleArray(HeavyWork.KEY_ARRAY);
                        double min = getMinValue(numbers);
                        double max = getMaxValue(numbers);
                        double average = getAverageValue(numbers);
                        min_text.setText("Minimum: " + min);
                        max_text.setText("Maximum: " + max);
                        average_text.setText("Average: " + average);
                        break;
                }
                return false;

            }
        });


        button_generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                threadPool.execute(
                        new HeavyWork(Integer.parseInt(input_text.getText().toString()),
                                messageQueue)
                );


            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                input_text.setText("" + i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public static double getMinValue(double[] arr) {
        if (arr.length == 0) {
            throw new IllegalArgumentException("Array must not be empty.");
        }

        double min = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }

        return min;
    }

    public static double getMaxValue(double[] arr) {
        if (arr.length == 0) {
            throw new IllegalArgumentException("Array must not be empty.");
        }
        double max = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        return max;
    }

    public static double getAverageValue(double[] arr) {
        if (arr.length == 0) {
            throw new IllegalArgumentException("Array must not be empty.");
        }

        double sum = 0.0;

        for (double val : arr) {
            sum += val;
        }

        double avg = sum / arr.length;

        return avg;
    }

}