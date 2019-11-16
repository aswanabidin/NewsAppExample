package com.mumtaazstudio.newsappexample.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.mumtaazstudio.newsappexample.R;
import com.mumtaazstudio.newsappexample.model.Article;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private TextView textTitle, textPublished, textDescription;
    private String image, title, published, description;
    private ImageView imageNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setTitle("Detail Berita");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageNews = (ImageView) findViewById(R.id.img_details_news);
        textTitle = (TextView) findViewById(R.id.text_title_detail);
        textPublished = (TextView) findViewById(R.id.text_published_detail);
        textDescription = (TextView) findViewById(R.id.text_description_detail);

        getExtras();

    }

    private void getExtras() {
        image = getIntent().getStringExtra("IMAGE");
        title = getIntent().getStringExtra("TITLE");
        published = getIntent().getStringExtra("PUBLISHED");
        description = getIntent().getStringExtra("CONTENT");
        Picasso.get().load(image).fit().into(imageNews);

        textTitle.setText(title);
        textPublished.setText(published);
        textDescription.setText(description);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
