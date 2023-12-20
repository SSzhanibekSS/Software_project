package root.Patterns.Observer;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import root.Models.RealBook;
import root.Patterns.Singletone.DatebaseConnection;

import java.util.Date;
import java.util.List;

public class Library implements Subject{

    private JdbcTemplate jdbcTemplate;
    public Library(){
        jdbcTemplate = DatebaseConnection.getConnetion();
    }
    @Override
    public void addSubscriber(int person_id) {
        jdbcTemplate.update("insert into subscribers(person_id) values (?)",person_id);
    }

    @Override
    public void removeSubscriber(int person_id) {
        jdbcTemplate.update("delete from subscribers where person_id = ?", person_id);
    }

    @Override
    public void notifySubscriber(String message) {
        List<Subscriber> subscribers = jdbcTemplate.query("select * from subscribers",new BeanPropertyRowMapper<>(Subscriber.class));
        for(Subscriber s: subscribers){
            s.update(message);
        }
    }
    public void addBook(RealBook book){
        jdbcTemplate.update("insert into books(book_title, book_author," +
                        " count_of_pages, count_of_books, year_of_production) values (?,?,?,?,?)",
                book.getBook_title(),book.getBook_author(), book.getCount_of_pages(),book.getTotal_quantity(),
                book.getYear_of_production());
        String message = "We have added a new book called " + book.getTitle() + " and you can book it";
        notifySubscriber(message);
    }

}
