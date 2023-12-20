package root.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import root.DAO.BookDAO;
import root.DAO.NotificationDAO;
import root.DAO.PersonDAO;
import root.Models.Notification;
import root.Models.Person;

@Controller
@RequestMapping("/stud")
public class StudentController {
    private NotificationDAO notificationDAO;

    private PersonDAO personDAO;
    private BookDAO bookDAO;
    @Autowired
    public StudentController(NotificationDAO notificationDAO, PersonDAO personDAO, BookDAO bookDAO) {
        this.notificationDAO = notificationDAO;
        this.personDAO = personDAO;
        this.bookDAO = bookDAO;
    }



    @GetMapping("/main/{name}")
    public String main(@PathVariable("name") String name,Model model){
        model.addAttribute("person", personDAO.getOne(name));
        model.addAttribute("books",bookDAO.getByPeronName(name));
        model.addAttribute("notifi",notificationDAO.getNotificationById(personDAO.getOne(name).getId()));



        return "people/student";
    }
}
