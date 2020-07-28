package com.mudimbasoftware.books;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    Context ct;
    ArrayList<Book> books;
    public BookAdapter(Context cx, ArrayList<Book> books) {
        this.ct = cx;
        this.books = books;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item,parent,false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = books.get(position);
        holder.bind(book);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public static class BookViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        TextView tvAuthor;
        TextView tvDate;
        TextView tvPublisher;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvAuthor = itemView.findViewById(R.id.tvauthor);
            tvDate = itemView.findViewById(R.id.tvPublisherDate);
            tvPublisher = itemView.findViewById(R.id.tvPublisher);
        }

        public void bind (Book book){
            tvTitle.setText(book.title);
            String authors = "";
            int i=0;
            for(String author:book.authors){
                authors+=author;
                if (i<book.authors.length) {
                    authors += ", ";
                }
            }
            tvAuthor.setText(authors);
            tvDate.setText(book.publishedDate);
            tvPublisher.setText(book.publisher);

        }
    }
}
