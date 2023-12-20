package root.Models;

import root.Patterns.Proxy.Book;

import java.util.ArrayList;
import java.util.List;

public class RealBook implements Book {
    private int book_id;
     String book_title;
    private String book_author;
    private int year_of_production;
    private int count_of_pages;
    private int total_quantity;
    private int available_quantity;

    public RealBook(int book_id, String book_title, String book_author, int year_of_production, int count_of_pages, int total_quantity, int available_quantity) {
        this.book_id = book_id;
        this.book_title = book_title;
        this.book_author = book_author;
        this.year_of_production = year_of_production;
        this.count_of_pages = count_of_pages;
        this.total_quantity = total_quantity;
        this.available_quantity = available_quantity;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }



    public RealBook(){}

    public int getTotal_quantity() {
        return total_quantity;
    }

    public void setTotal_quantity(int total_quantity) {
        this.total_quantity = total_quantity;
    }

    public int getAvailable_quantity() {
        return available_quantity;
    }

    public void setAvailable_quantity(int available_quantity) {
        this.available_quantity = available_quantity;
    }

    public String getBook_title() {
        return book_title;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }

    public String getBook_author() {
        return book_author;
    }

    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }

    public int getYear_of_production() {
        return year_of_production;
    }

    public void setYear_of_production(int year_of_production) {
        this.year_of_production = year_of_production;
    }

    public int getCount_of_pages() {
        return count_of_pages;
    }

    public void setCount_of_pages(int count_of_pages) {
        this.count_of_pages = count_of_pages;
    }


    @Override
    public int getId() {
        return book_id;
    }

    @Override
    public String getTitle() {
        return book_title;
    }

    @Override
    public List<String> getFullInfo() {
       List<String> list = new ArrayList<>();
        list.add(String.valueOf(this.getBook_id()));//0
        list.add(String.valueOf(this.getBook_title()));//1
        list.add(String.valueOf(this.getBook_author()));//2
        list.add(String.valueOf(this.getCount_of_pages()));//3
        list.add(String.valueOf(this.getYear_of_production()));//4
        list.add(String.valueOf(this.getAvailable_quantity()));//5

        return list;
    }
}