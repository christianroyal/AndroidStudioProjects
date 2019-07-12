package com.example.triviaapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class TriviaAdapter extends RecyclerView.Adapter<TriviaAdapter.TriviaViewHolder> {

    private List<String> triviaQuestions;
    private Context context;
    private OnTrivaAnswerClicked listener;

    TriviaAdapter(List<String> triviaQuestions, OnTrivaAnswerClicked onTrivaAnswerClicked) {
        this.triviaQuestions = triviaQuestions;
        this.listener= onTrivaAnswerClicked;
    }

    @NonNull
    @Override
    public TriviaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.trivia_item, parent, false);
        context = parent.getContext();
        return new TriviaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TriviaViewHolder holder, int position) {
        String url = triviaQuestions.get(position);
        Glide.with(context).load(url).into(holder.ivPhoto);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.triviaclicked(url);

            }
        });

    }

    @Override
    public int getItemCount() {
        return triviaQuestions.size();
    }

    class TriviaViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPhoto;

        TriviaViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPhoto = itemView.findViewById(R.id.iv_photo);
        }
    }
    public interface OnTrivaAnswerClicked{
        void triviaclicked(String url);
    }
}


