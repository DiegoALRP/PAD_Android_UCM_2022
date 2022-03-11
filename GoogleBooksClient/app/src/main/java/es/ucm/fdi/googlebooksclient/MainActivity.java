package es.ucm.fdi.googlebooksclient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int BOOK_LOADER_ID = 0;
    private BookLoaderCallBacks bookLoaderCallbacks;
    private EditText authorText;
    private EditText titleText;
    private TextView txtv_result;
    private RadioGroup radioGroup;

    private BooksResultListAdapter booksResultListAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        authorText = findViewById(R.id.bookauthors_text);
        titleText = findViewById(R.id.booktitle_text);
        txtv_result = findViewById(R.id.txtv_result);
        radioGroup = findViewById(R.id.radioGroup);

        recyclerView = findViewById(R.id.recyclerView_bookList);

        bookLoaderCallbacks = new BookLoaderCallBacks(this);

        booksResultListAdapter = new BooksResultListAdapter(this, new ArrayList<BookInfo>());
        recyclerView.setAdapter(booksResultListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void updateBooksResultList(List<BookInfo> bookInfos) {

        booksResultListAdapter.setBooksData(bookInfos);
        booksResultListAdapter.notifyDataSetChanged();
    }

    public void searchBooks(View view) {

        String author = authorText.getText().toString();
        String title = titleText.getText().toString();

        String queryString = author + " " + title;

        txtv_result.setText("Loading...");

        int radioID = radioGroup.getCheckedRadioButtonId();
        String printType = "";
        // Check which radio button was clicked
        switch(radioID) {
            case R.id.radioButton_all:
                printType = "all";
                break;
            case R.id.radioButton_book:
                printType = "books";
                break;
            case R.id.radioButton_magazine:
                printType = "magazines";
                break;
        }

        Bundle queryBundle = new Bundle();
        queryBundle.putString(BookLoaderCallBacks.EXTRA_QUERY, queryString);
        queryBundle.putString(BookLoaderCallBacks.EXTRA_PRINT_TYPE, printType);
        LoaderManager.getInstance(this).restartLoader(BOOK_LOADER_ID, queryBundle, bookLoaderCallbacks);



    }

        public class BookLoaderCallBacks implements LoaderManager.LoaderCallbacks<List<BookInfo>> {

            public static final String EXTRA_QUERY = "queryString";
            public static final String EXTRA_PRINT_TYPE = "printType";
            private Context context;

            public BookLoaderCallBacks(Context context) {
                this.context = context;
            }

            @NotNull
            @Override
            public BookLoader onCreateLoader(int id, @Nullable @org.jetbrains.annotations.Nullable Bundle args) {

                BookLoader bookLoader = new BookLoader(context, args.getString(EXTRA_QUERY), args.getString(EXTRA_PRINT_TYPE));

                return bookLoader;
            }

            @Override
            public void onLoadFinished(@NonNull @NotNull Loader<List<BookInfo>> loader, List<BookInfo> data) {
                if(data.size() == 0) txtv_result.setText("No Results Found");
                else txtv_result.setText("Results");

                updateBooksResultList(data);
            }

            @Override
            public void onLoaderReset(@NonNull @NotNull Loader<List<BookInfo>> loader) {

            }
        }
}