package com.mumtaazstudio.newsappexample.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mumtaazstudio.newsappexample.R;
import com.mumtaazstudio.newsappexample.activity.DetailActivity;
import com.mumtaazstudio.newsappexample.model.Article;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private List<Article> articleArrayList;
    private Context context;
    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;

    public NewsAdapter(Context context) {
        this.context = context;
    }

    public NewsAdapter(List<Article> articleArrayList) {
        this.articleArrayList = articleArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Article article = articleArrayList.get(position);

        holder.title.setText(article.getTitle());
        holder.published.setText(article.getPublished());
        holder.description.setText(article.getDescription());
        Picasso.get().load(article.getImage()).fit().into(holder.image);
        holder.newsAdapterParentLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailActivity.class);
                intent.putExtra("IMAGE", article.getImage());
                intent.putExtra("TITLE", article.getTitle());
                intent.putExtra("PUBLISHED", article.getPublished());
                intent.putExtra("CONTENT", article.getContent());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return articleArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView published;
        private TextView description;
        private ImageView image;
        private LinearLayout newsAdapterParentLinear;

        public ViewHolder(@NonNull View view) {
            super(view);

            image = view.findViewById(R.id.img_news);
            title = view.findViewById(R.id.text_title_news);
            published = view.findViewById(R.id.text_published_news);
            description = view.findViewById(R.id.text_description_news);
            newsAdapterParentLinear = view.findViewById(R.id.news_adapter_ll_parent);

        }
    }

}


