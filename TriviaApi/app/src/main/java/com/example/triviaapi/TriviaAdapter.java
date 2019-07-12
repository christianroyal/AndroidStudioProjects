package com.example.triviaapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.triviaapi.model.Question;

import java.util.List;


public class TriviaAdapter extends RecyclerView.Adapter<TriviaAdapter.TrivaViewholder> {
    private List<Question> questions;
    private Context context;
    private OnQuestionClicked listener;

    public TriviaAdapter(List<Question> questions, OnQuestionClicked onQuestionClicked) {
        this.questions = questions;
        this.listener= onQuestionClicked;
    }

    @NonNull
    @Override
    public TrivaViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.triviaactivity_tf,parent,false);
        return new TrivaViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrivaViewholder holder, int position) {
        Question question = questions.get(position);
        holder.tvText.setText(question.getQuestion());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.questionClicked(question);


            }
        });



    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    class TrivaViewholder extends RecyclerView.ViewHolder{
        // ImageView ivPhoto;
        TextView tvText;

        TrivaViewholder(@NonNull View itemView) {
            super(itemView);
            // ivPhoto = itemView.findViewById(R.id.iv_photo);
            tvText = itemView.findViewById(R.id.container);
        }
    }
    public interface OnQuestionClicked {
        void questionClicked(Question question);
    }
}

