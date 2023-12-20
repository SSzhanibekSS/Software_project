package root.DAO;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import root.Models.RealBook;
import root.Models.Dates;
import root.Models.Notification;
import root.Patterns.Observer.Subscriber;
import root.Patterns.Singletone.DatebaseConnection;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class NotificationDAO {
    private JdbcTemplate jdbcTemplate;

    public NotificationDAO() {
        this.jdbcTemplate = DatebaseConnection.getConnetion();
    }
    public void Chek(){
        List<Dates> list = jdbcTemplate.query("select * from booked_books", new Object[]{},new BeanPropertyRowMapper<>(Dates.class));
        List<Dates> list2 = new ArrayList<>();
        LocalDate date = LocalDate.now();
        for(Dates d: list){
            if(date.compareTo(d.getUntil_date()) > 0){
                list2.add(d);
            }
        }

        List<RealBook> bookList = jdbcTemplate.query("select * from books where book_id != 0",new Object[]{},new BeanPropertyRowMapper<>(RealBook.class));
        System.out.println("I'm in check method");
        for(Dates d:list2){
            addNotification(d.getPerosn_id(),d.getBook_id(),bookList);
        }
    }
    private void addNotification(int person_id, int book_id, List<RealBook> books){
        String str = "";
        for(int i = 0;i < books.size();i++){
            if(books.get(i).getBook_id() == book_id){
                str = books.get(i).getBook_title();
            }
        }
        String message = "you must return this book " + str +  " because the deadline has passed before which you took it, please return it as soon as possible";
        jdbcTemplate.update("insert into notification(person_id, book_id, notification) values(?,?,?)",person_id,book_id,message);

    }
    public List<Notification> getNotificationById(int person_id){
        return jdbcTemplate.query("select * from notification where person_id = ?", new Object[]{person_id},new BeanPropertyRowMapper<>(Notification.class));
    }
    public void deleteNots(int id){
        jdbcTemplate.update("delete from notification where notification_id=?", id);
    }
    public boolean prov(int id){
        Subscriber subscriber = jdbcTemplate.query("select * from subscribers where person_id = ?",new Object[]{id}, new BeanPropertyRowMapper<>(Subscriber.class)).stream().findAny().orElse(null);
        if(subscriber==null)return false;

        return true;
    }


}
