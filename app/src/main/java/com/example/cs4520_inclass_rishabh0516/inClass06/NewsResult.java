package com.example.cs4520_inclass_rishabh0516.inClass06;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.cs4520_inclass_rishabh0516.R;
import com.example.cs4520_inclass_rishabh0516.inClass05.InClass05;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

// Rishabh Sahu
// Assignment #6

public class NewsResult extends Fragment {
    private String country, category;
    private final OkHttpClient client = new OkHttpClient();
    private String baseUrlSearch;
    private APIResponse apiResponse;
    private ArrayList<Article> articles;
    private ImageButton prev;
    private ImageButton forward;
    private TextView news_text;
    private ImageView news_image;
    private ArrayList<String> images;
    private int counter = 0;


    private ArrayList<FormatArticle> validArticles;

    public NewsResult() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            country = getArguments().getString("country");
            category = getArguments().getString("category");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_result, container, false);
        prev = rootView.findViewById(R.id.prev);
        forward = rootView.findViewById(R.id.forward);
        news_text = rootView.findViewById(R.id.news_text);
        news_image = rootView.findViewById(R.id.news_image);

        this.baseUrlSearch = "https://newsapi.org/v2/top-headlines?apiKey=a10ca305082748f58574035dc287cca1";

        getNews();

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(counter == 0) {
                    counter = validArticles.size();
                }
                counter -= 1;
                prev.setClickable(false);
                forward.setClickable(false);
                getNews();
                prev.setClickable(true);
                forward.setClickable(true);
            }
        });

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(counter == validArticles.size() - 1) {
                    counter = -1;
                }
                counter += 1;
                prev.setClickable(false);
                forward.setClickable(false);
                getNews();
                prev.setClickable(true);
                forward.setClickable(true);
            }
        });
        return rootView;
    }

    private void getNews(){
        HttpUrl url = HttpUrl.parse(baseUrlSearch)
                .newBuilder()
                .addQueryParameter("country", country)
                .addQueryParameter("category", category).build();
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
                if(response.isSuccessful()){
                    String jsonData = response.body().string();
                    Gson gsonData = new Gson();
                    apiResponse =  gsonData.fromJson(jsonData, APIResponse.class);
                    articles = apiResponse.getArticles();
                    response.body().close();
                    validArticles = new ArrayList<FormatArticle>();
                    images = new ArrayList<String>();
                    for(Article article: articles) {
                        FormatArticle validArticle = new FormatArticle(article.getAuthor(), article.getTitle(), article.getDescription(), article.getUrlToImage(), article.getPublishedAt());
                        validArticles.add(validArticle);
                        images.add(validArticle.getUrlToImage());
                    }

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            news_text.setText(validArticles.get(counter).toString());
                            updateImage(images.get(counter));

                        }
                    });



                }else{

                }
            }
        });
    }

    private void updateImage(String imageLink) {
        Glide.with(getActivity())
                .load(imageLink)
                .into(news_image);
    }




}