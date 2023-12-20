package root.Patterns.Observer;

import org.springframework.jdbc.core.JdbcTemplate;
import root.Patterns.Singletone.DatebaseConnection;

public class Subscriber {
    int subscriber_id;
    int person_id;
    void update(String message){
        JdbcTemplate jdbcTemplate = DatebaseConnection.getConnetion();
        System.out.println(message);
        System.out.println(person_id);
        jdbcTemplate.update("insert into notification(person_id, book_id, notification) values (?,?,?)",this.person_id,0,message );
    }
    public Subscriber(int subscriber_id, int person_id) {
        this.subscriber_id = subscriber_id;
        this.person_id = person_id;
    }
    public Subscriber(){}

    public int getSubscriber_id() {
        return subscriber_id;
    }

    public void setSubscriber_id(int subscriber_id) {
        this.subscriber_id = subscriber_id;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }
}
