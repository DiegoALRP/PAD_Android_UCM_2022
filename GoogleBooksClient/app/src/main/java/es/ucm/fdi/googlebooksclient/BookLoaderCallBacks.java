package es.ucm.fdi.googlebooksclient;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BookLoaderCallBacks implements LoaderManager.LoaderCallbacks<List<BookInfo>> {

    public static final String EXTRA_QUERY = "queryString";
    public static final String EXTRA_PRINT_TYPE = "printType";
    private Context context;

    public BookLoaderCallBacks(Context context) {
        this.context = context;
    }

    @NotNull
    @Override
    public Loader<List<BookInfo>> onCreateLoader(int id, @Nullable @org.jetbrains.annotations.Nullable Bundle args) {
        return new BookLoader(context, args.getString(EXTRA_QUERY), args.getString(EXTRA_PRINT_TYPE));
    }

    @Override
    public void onLoadFinished(@NonNull @NotNull Loader<List<BookInfo>> loader, List<BookInfo> data) {

    }

    @Override
    public void onLoaderReset(@NonNull @NotNull Loader<List<BookInfo>> loader) {

    }
}
