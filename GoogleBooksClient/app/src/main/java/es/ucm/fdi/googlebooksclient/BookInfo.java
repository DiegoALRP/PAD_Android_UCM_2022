package es.ucm.fdi.googlebooksclient;

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
}
