package com.example.recyclerview;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter{
    private static final String TAG = "RecyclerAdapter";
    List<String> moviesList;


    public RecyclerAdapter(List<String> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public int getItemViewType(int position) {
        if (moviesList.get(position).toLowerCase().contains("iron")){
            return 0;
        }
        return 1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view;
        if (viewType == 0){
            view=layoutInflater.inflate(R.layout.row_item,parent,false);
            return new ViewHolderOne(view);
        }
        view = layoutInflater.inflate(R.layout.another_row_item,parent,false);
        return new ViewHolderTwo(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (moviesList.get(position).toLowerCase().contains("iron"))
        {
            //bind viewHolderOne
            ViewHolderOne viewHolderOne = (ViewHolderOne) holder;
            viewHolderOne.textView.setText(moviesList.get(position));
            viewHolderOne.rowCountTextView.setText(String.valueOf(position));

        } else {
            //bind viewHolderTwo
            ViewHolderTwo viewHolderTwo = (ViewHolderTwo) holder;
            viewHolderTwo.textView.setText(moviesList.get(position));
            viewHolderTwo.rowCountTextView.setText(String.valueOf(position));

        }
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    class ViewHolderOne extends RecyclerView.ViewHolder{

        TextView textView, rowCountTextView;
        ImageView imageView;

        public ViewHolderOne(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textView);
            rowCountTextView = itemView.findViewById(R.id.rowCountTextView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

    class ViewHolderTwo extends RecyclerView.ViewHolder{

        TextView textView, rowCountTextView;
        public ViewHolderTwo(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textView);
            rowCountTextView = itemView.findViewById(R.id.rowCountTextView);
        }
    }
}
