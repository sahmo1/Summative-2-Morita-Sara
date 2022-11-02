package com.company.Summative2MoritaSara.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "book")
public class Books implements Serializable {
    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String isbn;
    private LocalDate publishDate;

    @NotNull
    @Column(name = "author_id")
    private int authorID;

    @NotNull
    @Column(name = "publisher_id")
    private int publisherID;

    @NotNull
    private String title;
    private int price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public int getAuthorID() {
        return authorID;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    public int getPublisherID() {
        return publisherID;
    }

    public void setPublisherID(int publisherID) {
        this.publisherID = publisherID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean equals(Object object){
        Books books = (Books) object;

        if (object == null || getClass() != object.getClass()){
            return false;
        }

        if (this == object){
            return true;
        }

        return Objects.equals(id, books.id) &&
                Objects.equals(isbn, books.isbn) &&
                Objects.equals(publishDate, books.publishDate) &&
                Objects.equals(authorID, books.authorID) &&
                Objects.equals(publisherID, books.publisherID) &&
                Objects.equals(title, books.title) &&
                Objects.equals(price, books.price);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, isbn, publishDate, authorID, publishDate, title, price);
    }


}
