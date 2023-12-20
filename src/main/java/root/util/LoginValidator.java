package root.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import root.DAO.PersonDAO;
import root.Models.Login;

@Component
public class LoginValidator implements Validator {
    private final PersonDAO personDAO;
    @Autowired
    public LoginValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Login.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Login login = (Login)target;

        if(!personDAO.prov(login.getName())){
            errors.rejectValue("name","","People with this name doesn't exist");

        }
        if(!personDAO.provP(login.getPassword())){
            errors.rejectValue("password","","Password isn't correct");
        }
    }
}
