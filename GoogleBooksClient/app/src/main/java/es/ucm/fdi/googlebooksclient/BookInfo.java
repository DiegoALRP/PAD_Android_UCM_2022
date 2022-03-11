package es.ucm.fdi.googlebooksclient;

import android.util.Log;

import java.net.URL;
import java.util.List;

public class BookInfo {

    private String title;
    private String authors;
    private URL infoLink;

    public BookInfo(String title, String authors, URL infoLink) {

        this.title = title;
        this.authors = authors;
        this.infoLink = infoLink;
    }

    @Override
    public String toString() {
        return "title='" + title + '\'' +
                ", authors='" + authors + '\'' +
                ", infoLink=" + infoLink +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public String getAuthors() {

        if (authors.equals("No Authors")) return authors;
        String aux1;
        aux1 = authors.substring(1, authors.length() - 2);
        aux1 = aux1.replace("\"", "").replace(",", ", ");
        return aux1;
    }

    public URL getInfoLink() {
        return infoLink;
    }
}
