package root.Models;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.sql.Statement;

public class Person {
    @Pattern(regexp = "[A-Z][a-zA-Z0-9\\s]*", message = "This field should be filled like 'Somename'")
    private String name;
    @Min(value = 5, message = "Your age should be more then 5")
    private int age;
    @Email(message = "Email is incorrect format")
    private String email;
    private String status = "owner";
    private int id;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Person(){

    }

    public Person(int id, String name, int age, String email, String status, String password) {
        this.id= id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.status = status;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
