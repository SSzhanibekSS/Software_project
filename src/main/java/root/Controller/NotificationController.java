package root.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import root.DAO.NotificationDAO;
import root.DAO.PersonDAO;
import root.Patterns.Observer.Library;

import javax.management.NotificationFilter;

@Controller
@RequestMapping("/notifi")
public class NotificationController {
    private NotificationDAO notificationDAO;
    private PersonDAO personDAO;
    @Autowired
    public NotificationController(PersonDAO personDAO,NotificationDAO notificationDAO) {
        this.notificationDAO = notificationDAO;
        this.personDAO = personDAO;
    }
    @DeleteMapping("/delete/{id}/{per_id}")
    public String deleteNot(@PathVariable("id") int id,
                            @PathVariable("per_id") int per_id){
    notificationDAO.deleteNots(id);
    return "redirect:/notifi/snot/" + per_id;
    }

    @GetMapping("/snot/{id}")
    public String student_not(@PathVariable("id") int id , Model model){
       boolean b = notificationDAO.prov(id);
       int status = 0;

       if(b)status = 1;

       model.addAttribute("status", status);
        model.addAttribute("notific", notificationDAO.getNotificationById(id));
        model.addAttribute("name", personDAO.getOne(id).getName());

        return "notific/student_notific";
    }
    @GetMapping("/sub/{name}")
    public String sub(@PathVariable("name") String name,
                      Model model){
        int id = personDAO.getOne(name).getId();
        Library library = new Library();
        library.addSubscriber(id);
        model.addAttribute("status",1);
        model.addAttribute("name",name);
        model.addAttribute("notific", notificationDAO.getNotificationById(id));

        return "notific/student_notific";
    }

    @GetMapping("/unsub/{name}")
    public String unsub(@PathVariable("name") String name,
                      Model model){
        int id = personDAO.getOne(name).getId();
        Library library = new Library();
        library.removeSubscriber(id);
        model.addAttribute("status",0);
        model.addAttribute("name",name);
        model.addAttribute("notific", notificationDAO.getNotificationById(id));

        return "notific/student_notific";
    }


}
