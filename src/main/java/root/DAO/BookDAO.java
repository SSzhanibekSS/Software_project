package root.DAO;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import root.Models.RealBook;
import root.Models.reserve;
import root.Patterns.Proxy.ProxyBook;
import root.Patterns.Singletone.DatebaseConnection;

import java.time.LocalDate;
import java.util.List;

@Component
public class BookDAO {
    private JdbcTemplate jdbcTemplate;
    public BookDAO(){
        jdbcTemplate = DatebaseConnection.getConnetion();
    }
    public List<RealBook> getAll(){

      return  jdbcTemplate.query("select * from available_books where book_id != 0", new BeanPropertyRowMapper<>(RealBook.class));


    }
    public RealBook getOne(int id){
        return jdbcTemplate.query("select * from available_books where book_id=?",new Object[]{id}, new BeanPropertyRowMapper<>(RealBook.class)).
                stream().findAny().orElse(null);

    }

    public List<RealBook> getByPeronName(String name){

        return jdbcTemplate.query("select\n" +
                "    b.book_title,\n" +
                "    b.count_of_pages,\n" +
                "    b.year_of_production,\n" +
                "    b.book_author\n" +
                "from books b\n" +
                "join booked_books bb on b.book_id = bb.book_id\n" +
                "join person p on bb.perosn_id = p.id\n" +
                "where p.name = ?\n",new Object[]{name},new  BeanPropertyRowMapper<>(RealBook.class));
    }
    public void reseveBook(int book_id, int person_id){
        LocalDate localDate = LocalDate.now().plusDays(7);
        jdbcTemplate.update("insert into booked_books(perosn_id,book_id, until_date)values (?,?,?)",person_id,book_id,localDate);

    }
    public List<reserve> provRes(int book_id){
        return jdbcTemplate.query("select * from booked_books where book_id = ?", new Object[]{book_id},new BeanPropertyRowMapper<>(reserve.class));
    }
    public void AddBook(RealBook book){
        jdbcTemplate.update("insert into books(book_title, book_author," +
                " count_of_pages, count_of_books, year_of_production) values (?,?,?,?,?)",
                book.getBook_title(),book.getBook_author(), book.getCount_of_pages(),book.getTotal_quantity(),
                book.getYear_of_production());
    }
    public List<reserve> getReseves(int id){
        return jdbcTemplate.query("select * from booked_books where perosn_id=?",new Object[]{id},new BeanPropertyRowMapper<>(reserve.class));
    }
    public void deleteRes(int book_id, int person_id){
        jdbcTemplate.update("delete from booked_books where perosn_id = ? and book_id=?",person_id,book_id);
    }
    public List<ProxyBook> getProxyBook(){
        return jdbcTemplate.query("select * from available_books where book_id != 0", new BeanPropertyRowMapper<>(ProxyBook.class));
    }


}
