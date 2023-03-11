// Rishabh Sahu
// Assignment 5
package com.example.cs4520_inclass_rishabh0516.inClass05;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.cs4520_inclass_rishabh0516.R;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class InClass05 extends AppCompatActivity {
    private ImageView images;
    private String baseUrlSearch;
    private ArrayList<String> responseList;
    private EditText keyword;
    private Button button_go;
    private int counter = 0;
    private ProgressBar loading;
    private ImageButton previous;
    private ImageButton next;
    private TextView loadingText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_class05);
        setTitle("Image Search");
        images = findViewById(R.id.images);
        keyword = findViewById(R.id.keyword);
        button_go = findViewById(R.id.button_go);
        loading = findViewById(R.id.loading);
        previous = findViewById(R.id.previous);
        next = findViewById(R.id.next);
        loadingText = findViewById(R.id.loadingText);
        this.baseUrlSearch = "http://ec2-54-164-201-39.compute-1.amazonaws.com/apis/images/retrieve";
        previous.setClickable(false);
        next.setClickable(false);
        button_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!internetConnectionAvailable(1000)) {
                    Toast.makeText(InClass05.this, "No Internet Connection",Toast.LENGTH_LONG).show();
                }
                loading.setVisibility(View.VISIBLE);
                loadingText.setVisibility(View.VISIBLE);
                loadImage();
                loadingText.setVisibility(View.INVISIBLE);
                loading.setVisibility(View.INVISIBLE);
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!internetConnectionAvailable(1000)) {
                    Toast.makeText(InClass05.this, "No Internet Connection",Toast.LENGTH_LONG).show();
                }
                if(counter == 0) {
                    counter = responseList.size();
                }
                counter -= 1;
                loading.setVisibility(View.VISIBLE);
                previous.setClickable(false);
                next.setClickable(false);
                loadingText.setText("Loading previous...");
                loadingText.setVisibility(View.VISIBLE);
                loadImage();
                loadingText.setVisibility(View.INVISIBLE);
                previous.setClickable(true);
                next.setClickable(true);
                loading.setVisibility(View.INVISIBLE);
            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!internetConnectionAvailable(1000)) {
                    Toast.makeText(InClass05.this, "No Internet Connection",Toast.LENGTH_LONG).show();
                }
                if(counter == responseList.size() - 1) {
                    counter = -1;
                }
                counter += 1;
                loading.setVisibility(View.VISIBLE);
                previous.setClickable(false);
                next.setClickable(false);
                loadingText.setText("Loading next...");
                loadingText.setVisibility(View.VISIBLE);
                loadImage();
                loadingText.setVisibility(View.INVISIBLE);
                previous.setClickable(true);
                next.setClickable(true);
                loading.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void updateImage(String imageLink) {
        Glide.with(InClass05.this)
                .load(imageLink)
                .into(images);
    }

    private void loadImage() {
        OkHttpClient client = new OkHttpClient();
        HttpUrl url = HttpUrl.parse(baseUrlSearch)
                .newBuilder()
                .addQueryParameter("keyword", keyword.getText().toString()).build();
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()) {
                    ResponseBody responseBody = response.body();
                    String body = responseBody.string();
                    String[] responseArray = body.split("\n");


                    responseList = new ArrayList<>();


                    for (String s : responseArray) {
                        responseList.add(s);
                    }


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            images.setVisibility(View.VISIBLE);
                            updateImage(responseList.get(counter));

                        }
                    });
                }else{

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            images.setVisibility(View.INVISIBLE);
                            Toast.makeText(InClass05.this, "No images found",Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
    }

    private boolean internetConnectionAvailable(int timeOut) {
        InetAddress inetAddress = null;
        try {
            Future<InetAddress> future = Executors.newSingleThreadExecutor().submit(new Callable<InetAddress>() {
                @Override
                public InetAddress call() {
                    try {
                        return InetAddress.getByName("google.com");
                    } catch (UnknownHostException e) {
                        return null;
                    }
                }
            });
            inetAddress = future.get(timeOut, TimeUnit.MILLISECONDS);
            future.cancel(true);
        } catch (InterruptedException e) {
        } catch (ExecutionException e) {
        } catch (TimeoutException e) {
        }
        return inetAddress!=null && !inetAddress.equals("");
    }
}