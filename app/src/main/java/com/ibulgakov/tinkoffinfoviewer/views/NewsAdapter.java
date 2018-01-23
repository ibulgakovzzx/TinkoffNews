package com.ibulgakov.tinkoffinfoviewer.views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ibulgakov.tinkoffinfoviewer.R;
import com.ibulgakov.tinkoffinfoviewer.listeners.NewsClickListener;
import com.ibulgakov.tinkoffinfoviewer.models.eniity.News;

import java.util.List;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<News> newsList;
    private LayoutInflater inflater;
    private NewsClickListener listener;

    public NewsAdapter(Context context, List<News> newsList) {
        this.newsList = newsList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final NewsViewHolder viewHolder =  new NewsViewHolder(inflater.inflate(R.layout.simple_text_row,parent,false));
        viewHolder.rlContent.setOnClickListener(v -> {
            int position = viewHolder.getAdapterPosition();
            if(position != RecyclerView.NO_POSITION) {
                if (listener != null) {
                    listener.onClick(v,position);
                }
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        News news = newsList.get(position);
        holder.tvText.setText(news.getText());
    }

    @Override
    public int getItemCount() {
        return newsList == null ? 0 : newsList.size();
    }

    @Override
    public long getItemId(int position) {
        if(newsList == null){
            return super.getItemId(position);
        } else {
            return newsList.get(position).hashCode();
        }
    }

    public void setListener(NewsClickListener listener) {
        this.listener = listener;
    }

    static class NewsViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout rlContent;
        TextView tvText;

        NewsViewHolder(View view) {
            super(view);
            rlContent = (RelativeLayout)view.findViewById(R.id.rl_content);
            tvText = (TextView) view.findViewById(R.id.tv_text);
        }
    }
}
