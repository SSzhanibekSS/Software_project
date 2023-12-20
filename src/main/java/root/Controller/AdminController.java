package root.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import root.DAO.BookDAO;
import root.DAO.NotificationDAO;
import root.DAO.PersonDAO;
import root.Models.RealBook;
import root.Models.Sort;
import root.Models.reserve;
import root.Patterns.Observer.Library;
import root.Patterns.Proxy.ProxyBook;
import root.util.Methods;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private BookDAO bookDAO;
    private PersonDAO personDAO;
    private NotificationDAO notificationDAO;
    @Autowired
    public AdminController(BookDAO bookDAO, PersonDAO personDAO, NotificationDAO notificationDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
        this.notificationDAO = notificationDAO;
    }

    @GetMapping("/main/{name}")
    public String main(@PathVariable("name") String name, Model model){
        model.addAttribute("person", personDAO.getOne(name));
        model.addAttribute("books", bookDAO.getAll());
        return "people/admin";
    }

    @GetMapping("/add/{name}")
    public String addBook(Model model, @PathVariable("name") String name){
        model.addAttribute("book", new RealBook());
        model.addAttribute("name", name);
        return "books/add";
    }
    @PostMapping("/add/{name}")
    public String postBook(@ModelAttribute("book") RealBook book,
                           @PathVariable("name") String name){
        Library library = new Library();
        library.addBook(book);
        return "redirect:/admin/main/" + name;
    }
    @GetMapping("/check/{name}")
    public String check(@PathVariable("name") String name){
        System.out.println("I'm here");
        notificationDAO.Chek();
        return "redirect:/admin/main/" + name;
    }

    @GetMapping("/books/{name}")
    public String allBooks(@PathVariable("name") String name, Model model){
        model.addAttribute("books", bookDAO.getProxyBook());
        model.addAttribute("name", name);
        model.addAttribute("sort", new Sort());
        model.addAttribute("orders", Methods.orders());
        model.addAttribute("types", Methods.types());
        return "books/admin_books";
    }

    @GetMapping("/one/{id}/{name}")
    public String getOne(@PathVariable("id") int id,
                         @PathVariable("name") String name,
                         @ModelAttribute("book") ProxyBook book,
                         Model model){
        book.setBook_title(bookDAO.getOne(id).getBook_title());
        book.setBook_id(bookDAO.getOne(id).getBook_id());
        model.addAttribute("book", book.getFullInfo());
        model.addAttribute("people", personDAO.getAll());
        model.addAttribute("name", name);
       reserve reserve = new reserve();
       reserve.setBook_id(id);
        model.addAttribute("res", reserve);
        return "books/admin_one";
    }
    @GetMapping("/stud/{name}")
    public String allStudent(@PathVariable("name") String name,Model model){
        model.addAttribute("name", name);
        model.addAttribute("people", personDAO.getAll());
        return "people/admin_students";
    }
    @GetMapping("/one/stud/{s_name}/{name}")
    public String getOneStudent(@PathVariable("s_name") String sname,
                                @PathVariable("name") String name,
                                Model model){
        int person_id = personDAO.getOne(sname).getId();
        List<reserve> list = bookDAO.getReseves(person_id);
        List<RealBook> books = new ArrayList<>();
        for(reserve r:list){
            books.add(bookDAO.getOne(r.getBook_id()));
        }
        model.addAttribute("books",books);
        model.addAttribute("person_id",person_id);
        model.addAttribute("name",name);
        return "people/admin_one";
    }
}
