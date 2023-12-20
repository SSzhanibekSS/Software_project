package root;

import root.Patterns.Facade.SortFacade;
import root.Patterns.Proxy.ProxyBook;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        SortFacade fasade = new SortFacade("asc","author");
        List<ProxyBook> books = fasade.sortBooks();

        for (ProxyBook b: books){
            System.out.println(b.getTitle());
        }
    }
}
