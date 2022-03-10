package es.ucm.fdi.googlebooksclient;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BookLoader extends AsyncTaskLoader<List<BookInfo>> {
//public class BookLoader extends AsyncTaskLoader<String> {


    private String queryString;
    private String printType;
    private final int MAX_RESULT = 40;
    private final int MAX_LENGTH = 999999;
    private ArrayList<BookInfo> array;

    public BookLoader(@NonNull Context context, String queryString, String printType) {
        super(context);
        this.queryString = queryString;
        this.printType = printType;
    }

    @Nullable
    //@org.jetbrains.annotations.Nullable
    @Override
    public List<BookInfo> loadInBackground() {
        List<BookInfo> bookInfos;
        bookInfos = getBookInfoJson(queryString, printType);
        array = (ArrayList) bookInfos;
        return bookInfos;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    public List<BookInfo> getBookInfoJson(String queryString, String printType) {

        // Base URL for the Books API.
        final String BOOK_BASE_URL =
                "https://www.googleapis.com/books/v1/volumes?";

        // Parameter for the search string
        final String QUERY_PARAM = "q";
        // Parameter to limit search results.
        final String MAX_RESULTS = "maxResults";
        // Parameter to filter by print type
        final String PRINT_TYPE = "printType";

        // Build up the query URI, limiting results to 5 printed books.
        Uri builtURI = Uri.parse(BOOK_BASE_URL).buildUpon()
                .appendQueryParameter(QUERY_PARAM, queryString)
                .appendQueryParameter(MAX_RESULTS, String.valueOf(MAX_RESULT))
                .appendQueryParameter(PRINT_TYPE, printType)
                .build();

        String myurl = builtURI.toString();

        URL url = null;
        InputStream inputStream = null;
        HttpURLConnection conn = null;
        String contentAsString = "";
        try {

            url = new URL(myurl);
            conn = (HttpURLConnection) url.openConnection();
            //conn.setRequestMethod("GET");
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.connect();

            int response = conn.getResponseCode();
            Log.d("debug", "The response is: " + response);
            inputStream = conn.getInputStream();

            contentAsString = convertInputToString(inputStream);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            conn.disconnect();
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //Log.i("json", contentAsString);
        //System.out.println(contentAsString);
        //Log.i("json", "hi");

        return fromJsonResponse(contentAsString);
    }

    // Reads an InputStream and converts it to a String.
    public String convertInputToString (InputStream stream) throws IOException, UnsupportedEncodingException {
        Reader read = new InputStreamReader(stream);
        BufferedReader reader = new BufferedReader(read);
        StringBuilder stringBuilder = new StringBuilder();

        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }

    public static List<BookInfo> fromJsonResponse(String s) {

        List<BookInfo> list = new ArrayList<>();
        try {

            JSONObject jsonObject = new JSONObject(s);
            if(jsonObject.getInt("totalItems") == 0) return list;
            JSONArray jsonArray = jsonObject.getJSONArray("items");

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject aux = new JSONObject(String.valueOf(jsonArray.getJSONObject(i)));

                String stringURL = aux.getString("selfLink");
                URL url = new URL(stringURL);

                JSONObject volumeInfo = aux.getJSONObject("volumeInfo");
                String title;
                try {
                    title = volumeInfo.getString("title");
                }
                catch (JSONException e) {
                    title = "No Title";
                }

                String authors;
                try {
                    authors = volumeInfo.getString("authors");
                }
                catch (JSONException e) {
                    authors = "No Authors";
                }

                BookInfo bookInfo = new BookInfo(title, authors, url);
                list.add(bookInfo);

                System.out.println("Title: " + title + " Author: " + authors + " URL: " + stringURL);
            }


        } catch (JSONException | MalformedURLException e) {
            e.printStackTrace();

        }


        return list;
    }

    public ArrayList<BookInfo> getArray() {
        return array;
    }
}
