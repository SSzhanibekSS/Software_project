package root.DAO;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import root.Models.Person;
import root.Patterns.Singletone.DatebaseConnection;

import java.util.List;

@Component
public class PersonDAO {
    private JdbcTemplate jdbcTemplate;

    public PersonDAO() {
        this.jdbcTemplate = DatebaseConnection.getConnetion();
    }
    public List<Person> getAll(){
       return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
    }
    public Person getOne(int id){
        return jdbcTemplate.query("select * from person where id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }
    public Person getOne(String name){
        return jdbcTemplate.query("SELECT * FROM person WHERE name=?",
                new Object[]{name}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }
    public boolean  prov(String name){
        List<Person> list = getAll();
        for(Person p:list){
            if(p.getName().equals(name))return true;
        }
        return false;
    }
    public boolean  provP(String password){
        List<Person> list = getAll();
        for(Person p:list){
            if(p.getPassword().equals(password))return true;
        }
        return false;
    }
    public void addPerson(Person person){
        jdbcTemplate.update("INSERT INTO person(name, email,password, status, age) values " +
                "(?,?,?,?,?)", person.getName(), person.getEmail(),person.getPassword(),person.getStatus(), person.getAge());
    }


}
