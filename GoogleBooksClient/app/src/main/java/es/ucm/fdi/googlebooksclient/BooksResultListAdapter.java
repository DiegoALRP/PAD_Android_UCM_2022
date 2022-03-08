package es.ucm.fdi.googlebooksclient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import org.jetbrains.annotations.NotNull;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;

public class BooksResultListAdapter extends RecyclerView.Adapter<BooksResultListAdapter.ViewHolder> implements View.OnClickListener {

    //private View wordItemView;
    private LayoutInflater mInflater;
    private ArrayList<BookInfo> mBooksData;

    public BooksResultListAdapter(Context context, List<BookInfo> bookList) {
        mInflater = LayoutInflater.from(context);
        setBooksData(bookList);
    }

    public void setBooksData(List<BookInfo> data) {

        mBooksData = (ArrayList<BookInfo>) data;
    }


    @NonNull
    @NotNull
    @Override
    public BooksResultListAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View mItemView = mInflater.inflate(R.layout.layout_for_recycler_view, parent, false);
        return new ViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull BooksResultListAdapter.ViewHolder holder, int position) {

        // Retrieve the data for that position
        String mCurrent = mBooksData.get(position).toString();
        // Add the data to the view
        holder.wordItemView.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return mBooksData.size();
    }

    @Override
    public void onClick(View v) {

    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        public TextView wordItemView;
        private BooksResultListAdapter mAdapter;

        public ViewHolder(View itemView, BooksResultListAdapter adapter) {
            super(itemView);
            // Get the layout
            //wordItemView = itemView.findViewById(R.id.word);
            // Associate with this adapter
            this.mAdapter = adapter;
            // Add click listener, if desired
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}