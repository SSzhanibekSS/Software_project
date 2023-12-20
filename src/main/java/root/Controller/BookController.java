package root.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import root.DAO.BookDAO;
import root.DAO.PersonDAO;
import root.Models.Sort;
import root.Models.reserve;
import root.Patterns.Facade.SortFacade;
import root.Patterns.Proxy.ProxyBook;
import root.util.Methods;
import root.util.ReserveValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {
    private BookDAO bookDAO;
    private ReserveValidator validator;
    private PersonDAO personDAO;

    @Autowired
    public BookController(BookDAO bookDAO, ReserveValidator validator, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.validator = validator;
        this.personDAO = personDAO;
    }

    @GetMapping("/all/{name}")
    public String getAll(@PathVariable("name") String name, Model model) {
        model.addAttribute("books", bookDAO.getProxyBook());
        model.addAttribute("name", name);
        model.addAttribute("sort", new Sort());
        model.addAttribute("orders", Methods.orders());
        model.addAttribute("types", Methods.types());


        return "books/books";
    }

    @GetMapping("/one/{id}/{name}")
    public String getOne(@PathVariable("id") int id,
                         @PathVariable("name") String name, Model model) {
        System.out.println(id);
        ProxyBook proxyBook = new ProxyBook();
        proxyBook.setBook_id(bookDAO.getOne(id).getBook_id());
        proxyBook.setBook_title(bookDAO.getOne(id).getBook_title());
        ///
        model.addAttribute("book", proxyBook.getFullInfo());
        model.addAttribute("name", name);
        model.addAttribute("reserve", new reserve(id, personDAO.getOne(name).getId()));
        return "books/book";
    }


    @PostMapping("/res/{id}/{name}")
    public String reserve(@PathVariable("id") int id,
                          @PathVariable("name") String name,
                          @ModelAttribute("reserve") @Valid reserve reserve,
                          BindingResult bindingResult, Model model) {

        reserve.setBook_id(id);
        reserve.setPerosn_id(personDAO.getOne(name).getId());

        validator.validate(reserve, bindingResult);

        if (bindingResult.hasErrors()) {
            ProxyBook proxyBook = new ProxyBook();
            proxyBook.setBook_id(bookDAO.getOne(id).getBook_id());
            proxyBook.setBook_title(bookDAO.getOne(id).getBook_title());

            model.addAttribute("book", proxyBook.getFullInfo());
            model.addAttribute("name", name);

            return "books/book";
        }
        bookDAO.reseveBook(id, personDAO.getOne(name).getId());
        return "redirect:/stud/main/" + name;
    }

    @PostMapping("/res/admin/{id}/{name}")
    public String resByAdmin(@PathVariable("id") int id,
                             @PathVariable("name") String name,
                             @ModelAttribute("res") @Valid reserve reserve,
                             BindingResult bindingResult,
                             Model model) {
        reserve.setBook_id(id);
        validator.validate(reserve, bindingResult);
        if (bindingResult.hasErrors()) {
            ProxyBook proxyBook = new ProxyBook();
            proxyBook.setBook_title(bookDAO.getOne(reserve.getBook_id()).getBook_title());
            proxyBook.setBook_id(bookDAO.getOne(reserve.getBook_id()).getBook_id());

            model.addAttribute("book", proxyBook.getFullInfo());
            model.addAttribute("people", personDAO.getAll());
            model.addAttribute("name", name);
            return "books/admin_one";
        }
        bookDAO.reseveBook(reserve.getBook_id(), reserve.getPerosn_id());
        return "redirect:/admin/main/" + name;
    }

    @DeleteMapping("/delete/{id}/{person_id}/{name}")
    public String delete(@PathVariable("id") int id,
                         @PathVariable("person_id") int person_id,
                         @PathVariable("name") String name,
                         Model model) {
        String person_name = personDAO.getOne(person_id).getName();
        System.out.println("asl;dkf");
        bookDAO.deleteRes(id, person_id);
        return "redirect:/admin/one/stud/" + person_name + "/" + name;
    }

    @GetMapping("/sort/stud/{name}")
    public String sort(@ModelAttribute("sort") Sort sort,
                       @PathVariable("name") String name,
                       Model model) {
        SortFacade facade = new SortFacade(sort.getOrder(),sort.getType());
        model.addAttribute("books",facade.sortBooks());
        model.addAttribute("name",name);
        model.addAttribute("orders", Methods.orders());
        model.addAttribute("types", Methods.types());
        return "books/books";
    }

    @GetMapping("/sort/{name}")
    public String sortStud(@ModelAttribute("sort") Sort sort,
                       @PathVariable("name") String name,
                       Model model) {
        SortFacade facade = new SortFacade(sort.getOrder(),sort.getType());
        model.addAttribute("books",facade.sortBooks());
        model.addAttribute("name",name);
        model.addAttribute("orders", Methods.orders());
        model.addAttribute("types", Methods.types());
        return "books/admin_books";
    }

}
//-/admin/one/stud/Joni/Nurba
//-/admin/one/stud/Joni/Nurba


