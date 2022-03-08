package es.ucm.fdi.googlebooksclient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int BOOK_LOADER_ID = 0;
    private BookLoaderCallBacks bookLoaderCallbacks;
    private EditText authorText;
    private EditText titleText;
    private RadioGroup radioGroup;

    private BooksResultListAdapter booksResultListAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bookLoaderCallbacks = new BookLoaderCallBacks(this);

        LoaderManager loaderManager = LoaderManager.getInstance(this);
        if (loaderManager.getLoader(BOOK_LOADER_ID) != null) {
            loaderManager.initLoader(BOOK_LOADER_ID,null, bookLoaderCallbacks);
        }

        authorText = findViewById(R.id.bookauthors_text);
        titleText = findViewById(R.id.booktitle_text);
        radioGroup = findViewById(R.id.radioGroup);

        /*ArrayList<BookInfo> array = new ArrayList<BookInfo>();
        try {
            array.add(new BookInfo("Don Quijote", "Cervantes", new URL("https://www.googleapis.com/books/v1/volumes?")));
            array.add(new BookInfo("Don Quijote", "Cervantes", new URL("https://www.googleapis.com/books/v1/volumes?")));
            array.add(new BookInfo("Don Quijote", "Cervantes", new URL("https://www.googleapis.com/books/v1/volumes?")));
            array.add(new BookInfo("Don Quijote", "Cervantes", new URL("https://www.googleapis.com/books/v1/volumes?")));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }*/

        booksResultListAdapter = new BooksResultListAdapter(this, array);

        recyclerView = findViewById(R.id.recyclerView_bookList);
        booksResultListAdapter = new BooksResultListAdapter(this, array);
        recyclerView.setAdapter(booksResultListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    void updateBooksResultList(List<BookInfo> bookInfos) {

        booksResultListAdapter.setBooksData(bookInfos);
        booksResultListAdapter.notifyDataSetChanged();
    }

    public void searchBooks(View view) {

        String author = authorText.getText().toString();
        String title = titleText.getText().toString();

        String queryString = author + " " + title;

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
}