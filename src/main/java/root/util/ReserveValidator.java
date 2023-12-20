package root.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import root.DAO.BookDAO;
import root.Models.reserve;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class ReserveValidator implements Validator {
    BookDAO bookDAO;
    @Autowired
    public ReserveValidator(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(reserve.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        reserve reserve = (reserve)target;
        boolean b = true;
        List<reserve> list = bookDAO.provRes(reserve.getBook_id());
        for (reserve r : list){
            if(r.getPerosn_id() == reserve.getPerosn_id()) b = false;
        }
        if(!b){
            errors.rejectValue("perosn_id", "","This book already taken");
        }


            }
}
