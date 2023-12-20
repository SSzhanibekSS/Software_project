package root.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import root.DAO.PersonDAO;
import root.Models.Login;
import root.Models.Person;
import root.util.LoginValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/authen")
public class LoginController {
    private PersonDAO personDAO;
    private LoginValidator validator;
    @Autowired
    public LoginController(PersonDAO personDAO, LoginValidator validator) {
        this.personDAO = personDAO;
        this.validator = validator;
    }




    @GetMapping()
    public String login(Model model){
        model.addAttribute("login", new Login());
        return "login/login";
    }
    @GetMapping("/nap")
    public String nap(@ModelAttribute("login") @Valid Login login,
                      BindingResult bindingResult ){
        validator.validate(login,bindingResult);
        if(bindingResult.hasErrors()){
            return "login/login";
        }
        Person person = personDAO.getOne(login.getName());

        if(person.getStatus().equals("admin"))
        return "redirect:/admin/main/" + person.getName();

        return "redirect:/stud/main/" + person.getName();
    }

    @GetMapping("/reg")
    public String regsint(@ModelAttribute("person")  Person person){
        return "login/reg";
    }
    @PostMapping("/reg")
    public String regPost(@ModelAttribute("person") @Valid Person person
    , BindingResult result){
        if(result.hasErrors()){
            return "login/reg";
        }
        personDAO.addPerson(person);
        return "redirect:/authen";
    }

}
