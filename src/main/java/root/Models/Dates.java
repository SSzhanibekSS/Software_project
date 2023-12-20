package root.Models;

import java.time.LocalDate;

public class Dates {

    int book_id;
    int perosn_id;
    LocalDate until_date;

    public Dates(int book_id, int perosn_id, LocalDate until_date) {
        this.book_id = book_id;
        this.perosn_id = perosn_id;
        this.until_date = until_date;
    }
    public Dates(){}

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

    public LocalDate getUntil_date() {
        return until_date;
    }

    public void setUntil_date(LocalDate until_date) {
        this.until_date = until_date;
    }


}
