package root.Patterns.Proxy;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import root.DAO.BookDAO;
import root.Models.RealBook;
import root.Patterns.Singletone.DatebaseConnection;

import java.util.ArrayList;
import java.util.List;

public class ProxyBook implements Book{

    private RealBook realBook;
    private String book_title;
    int book_id;

    private JdbcTemplate jdbcTemplate;
    public ProxyBook(int id, String book_title) {
        this.book_id = id;
        this.book_title = book_title;
        this.jdbcTemplate = DatebaseConnection.getConnetion();
        this.realBook = new RealBook();
    }
    public ProxyBook(){
        jdbcTemplate = DatebaseConnection.getConnetion();
        realBook = new RealBook();
    }

    public String getBook_title() {
        return book_title;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public void CreateRealBook(){
       this.realBook =  jdbcTemplate.query("select * from available_books where book_id=?",new Object[]{book_id}, new BeanPropertyRowMapper<>(RealBook.class)).
                stream().findAny().orElse(null);}

    public RealBook getRealBook(){
        return this.realBook;
    }
    @Override
    public int getId() {
        return 0;
    }

    @Override
    public String getTitle() {
        return book_title;
    }

    @Override
    public List<String> getFullInfo() {


        CreateRealBook();

        return realBook.getFullInfo();
    }
}
