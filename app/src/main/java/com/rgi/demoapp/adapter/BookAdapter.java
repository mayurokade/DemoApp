package com.rgi.demoapp.adapter;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.rgi.demoapp.R;
import com.rgi.demoapp.databinding.ItemBookListBinding;
import com.rgi.demoapp.model.BookModel;
import com.rgi.demoapp.utils.IOUtils;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyViewHolder> {
    public static final String TAG = BookAdapter.class.getName();
    private LayoutInflater layoutInflater;
    private Context mcontext;
    List<BookModel.Result> list;

    public BookAdapter(Activity bookListActivity, List<BookModel.Result> results) {
        this.mcontext = bookListActivity;
        this.list = results;
    }

    @NonNull
    @Override
    public BookAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ItemBookListBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_book_list, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BookAdapter.MyViewHolder holder, int position) {
        if (list.size() > 0) {
            holder.itemBookListBinding.setBookdata(list.get(position));
            try {
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(list.get(position).getMediaType()));
                mcontext.startActivity(myIntent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(mcontext, "No application can handle this request."
                        + " Please install a webbrowser",  Toast.LENGTH_LONG).show();
                e.printStackTrace();
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(list.get(position).getMediaType()));
                mcontext.startActivity(myIntent);
            }
        }
    }


    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ItemBookListBinding itemBookListBinding;

        public MyViewHolder(@NonNull ItemBookListBinding itemView) {
            super(itemView.getRoot());
            this.itemBookListBinding = itemView;
        }
    }
}
