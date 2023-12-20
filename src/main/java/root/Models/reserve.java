package root.Models;

import java.util.Date;

public class reserve {

    private int book_id;
    private int perosn_id;

    public reserve(int book_id, int person_id) {
        this.book_id = book_id;
        this.perosn_id = person_id;
    }
    public reserve(){

    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getPerosn_id() {
        return perosn_id;
    }

    public void setPerosn_id(int perosn_id) {
        this.perosn_id = perosn_id;
    }
}
