package com.rgi.demoapp.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.AbsListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.rgi.demoapp.R;
import com.rgi.demoapp.adapter.BookAdapter;
import com.rgi.demoapp.databinding.ActivityBookListBinding;
import com.rgi.demoapp.model.BookModel;
import com.rgi.demoapp.utils.IOUtils;

import java.util.ArrayList;

public class BookListActivity extends AppCompatActivity {
    String key;
    ActivityBookListBinding binding;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private String baseUrl = "http://skunkworks.ignitesol.com:8000/";
    BookModel bookModel = new BookModel();
    ArrayList<BookModel.Result> result = new ArrayList<>();
    Boolean isScrolling = false;
    int currentItems, totalItems, scrollOutItems;
    String token = "";
    GridLayoutManager manager;
    BookAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  setContentView(R.layout.activity_book_list);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_book_list);
        initAndCalls();

    }

    private void initAndCalls() {
        if (getIntent().hasExtra("key")) {
            key = getIntent().getStringExtra("key");
            binding.tvTitle.setText(key);
        }

        callRecycler();
        clickListerns();
        binding.rvBooks.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItems = manager.getChildCount();
                totalItems = manager.getItemCount();
                scrollOutItems = manager.findFirstVisibleItemPosition();
                Log.e("test", "onScrolled: currentItems " + currentItems);
                Log.e("test", "onScrolled: totalItems " + totalItems);
                Log.e("test", "onScrolled: scrollOutItems " + scrollOutItems);

                if (isScrolling && (currentItems + scrollOutItems == totalItems)) {
                    isScrolling = false;
                        if (result != null &&bookModel.getNext() != null && !bookModel.getNext().isEmpty())
                            callClickListern(bookModel.getNext(), false);
                }

            }
        });

        callClickListern(baseUrl + "books?topic=" + key, true);

        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (result.size() > 0) {
                    result.clear();
                }
                callClickListern(baseUrl + "books?search=" + query, false);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                final int sdk = android.os.Build.VERSION.SDK_INT;
                if (newText.length() > 0) {
                    Log.e("Test", "onQueryTextSubmit: len " + newText.length());
                    if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                        binding.searchView.setBackgroundDrawable(ContextCompat.getDrawable(BookListActivity.this, R.drawable.rounded_corner_selected));
                    } else {
                        binding.searchView.setBackground(ContextCompat.getDrawable(BookListActivity.this, R.drawable.rounded_corner_selected));
                    }

                } else {
                    if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                        binding.searchView.setBackgroundDrawable(ContextCompat.getDrawable(BookListActivity.this, R.drawable.rounded_corner));
                    } else {
                        binding.searchView.setBackground(ContextCompat.getDrawable(BookListActivity.this, R.drawable.rounded_corner));
                    }
                }
                return false;
            }
        });

    }

    private void callRecycler() {
        manager = new GridLayoutManager(this, 3);
        adapter = new BookAdapter(this, result);
        binding.rvBooks.setLayoutManager(manager);
        binding.rvBooks.setAdapter(adapter);
    }

    private void callClickListern(String url, boolean notifi) {
        //RequestQueue initialized
        if (IOUtils.isConnected(this)) {
            IOUtils.hideKeyBoard(this);
            mRequestQueue = Volley.newRequestQueue(this);
            //show progress dialog
            IOUtils.startLoadingView(this);

            //String Request initialized
            mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //stop Loading
                    IOUtils.stopLoading();
                    Gson gson = new Gson();
                    BookModel bModel = new BookModel();
                    bModel = gson.fromJson(response, BookModel.class);
                    bookModel = bModel;
                    for (int i = 0; i < bModel.getResults().size(); i++)
                        result.add(bModel.getResults().get(i));

                    adapter.notifyDataSetChanged();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //stop Loading
                    IOUtils.stopLoading();
                    Log.e("Errir", "Error :" + error.toString());
                }
            });

            mRequestQueue.add(mStringRequest);
        }
    }


    private void clickListerns() {
        binding.ivBack.setOnClickListener(v -> {
            onBackPressed();
        });
    }


}