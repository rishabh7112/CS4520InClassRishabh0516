package com.example.cs4520_inclass_rishabh0516.inClass06;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.cs4520_inclass_rishabh0516.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewsResult extends Fragment {
    private String country, category;
    private ListView newsList;
    private final OkHttpClient client = new OkHttpClient();
    private String baseUrlSearch;
    private APIResponse apiResponse;
    private ArrayList<Article> articles;
    private ArrayAdapter<Article> adapter;

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
        newsList = rootView.findViewById(R.id.newsList);
        this.baseUrlSearch = "https://newsapi.org/v2/top-headlines?apiKey=a10ca305082748f58574035dc287cca1";

        getNews();
        /**
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, articles);
        newsList.setAdapter(adapter);
         **/
        return rootView;
    }

    private void getNews(){
        Log.d("demo", country);
        Log.d("demo", category);

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
                    Log.d("demo", response.body().string());
                    Gson gsonData = new Gson();
                    apiResponse =  gsonData.fromJson(response.body().charStream(), APIResponse.class);
                    articles = apiResponse.getArticles();

                }else{
                    Log.d("demo", "onResponse: "+response.body().string());

                }
            }
        });
    }
}