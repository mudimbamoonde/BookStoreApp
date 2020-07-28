package com.mudimbasoftware.books;

public class Book {
    public String id;
    public String title;
    public String subtitle;
    public String [] authors;
    public String publisher;
    public String publishedDate;
    public String description;

    public Book(String id, String title, String subtitle, String[] authors, String publisher, String publishedDate,String description) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.authors = authors;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.description = description;
    }


}
