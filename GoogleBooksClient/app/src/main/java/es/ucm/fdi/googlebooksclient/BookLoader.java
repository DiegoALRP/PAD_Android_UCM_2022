package es.ucm.fdi.googlebooksclient;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

public class BookLoader extends AsyncTaskLoader<String> {

    public BookLoader(@NonNull @org.jetbrains.annotations.NotNull Context context) {
        super(context);
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public String loadInBackground() {
        return null;
    }
}
