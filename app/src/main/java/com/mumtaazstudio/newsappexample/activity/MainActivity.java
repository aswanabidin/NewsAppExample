package com.mumtaazstudio.newsappexample.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.mumtaazstudio.newsappexample.R;

import com.mumtaazstudio.newsappexample.adapter.NewsAdapter;
import com.mumtaazstudio.newsappexample.model.Article;
import com.mumtaazstudio.newsappexample.model.Response;
import com.mumtaazstudio.newsappexample.service.ApiClient;
import com.mumtaazstudio.newsappexample.service.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    private static final String API_KEY = "d5d789420e964caaa79fc15a662208c9";
    private RecyclerView listItem;
    private LinearLayoutManager lin;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Berita Terkini");
        initResource();
        getResponseApi();
    }

    public void initResource() {
        progressBar = findViewById(R.id.progress_circular);
        listItem = findViewById(R.id.rv_news);
        lin = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        progressBar.setVisibility(View.VISIBLE);

        listItem.setLayoutManager(lin);

    }

    public void getResponseApi() {

        // Initialize api service
        final ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        // Call function getLatestNews with country and apikey paramaters
        Call<Response> call = apiService.getLatestNews("us", API_KEY);

        // Get response from api
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.body().getStatus().equals("ok")) {
                    progressBar.setVisibility(View.GONE);
                    List<Article> articleList = response.body().getArticles();
                    final NewsAdapter newsAdapter = new NewsAdapter(articleList);
                    listItem.setAdapter(newsAdapter);
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Log.e("onFailure", t.toString());

            }
        });
    }
}
