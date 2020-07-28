package com.mudimbasoftware.books;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private TextView error;
    RecyclerView rv_view;
    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        pb = findViewById(R.id.pb_loading);
        rv_view = findViewById(R.id.rv_book);

        try {
             URL BookURL = ApiUtil.buidURL("Cooking");
//            String jsonResult = ApiUtil.getJson(BookURL);
          new BookQueryTask().execute(BookURL);

        }catch (Exception e){
            Log.d("Error" ,e.toString());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.book_list_menu,menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener((SearchView.OnQueryTextListener) this);
        return true;

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        try{
            URL BookURL = ApiUtil.buidURL(query);
            new BookQueryTask().execute(BookURL);
        }catch (Exception e){
            Log.d("Error" ,e.toString());
        }
        return false;
    }


    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }

    public  class BookQueryTask extends AsyncTask<URL,Void,String>{

        @Override
        protected String doInBackground(URL[] urls) {
            URL searchURL = urls[0];
            String result = null;
            try{
                result = ApiUtil.getJson(searchURL);
            }catch (Exception e){
                Log.e("Error",e.toString());
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {

            error = findViewById(R.id.tv_error);
            pb.setVisibility(View.INVISIBLE);
            if (result==null){
                rv_view.setVisibility(View.INVISIBLE);
                error.setVisibility(View.VISIBLE);
            }else{
               error.setVisibility(View.INVISIBLE);
                rv_view.setVisibility(View.VISIBLE);
                ArrayList<Book> books = ApiUtil.getBookFromJson(result);
                String resultString = "";
                for (Book book : books){
                    resultString = resultString + book.title+ "\n" + book.publishedDate + "\n\n";
                }
                BookAdapter adapter = new BookAdapter(MainActivity.this,books);
                rv_view.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL,false));
                rv_view.setAdapter(adapter);
            }


        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pb.setVisibility(View.VISIBLE);
        }
    }
}