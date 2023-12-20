package root.Patterns.Facade;

import root.Patterns.Proxy.ProxyBook;
import root.Patterns.Strategy.*;

import java.util.List;

public class SortFacade {
    private SortOrderStrategy sortOrderStrategy;
    private SortTypeStrategy sortTypeStrategy;
    public SortFacade(){}

    public SortFacade(String sortOrder, String sortType) {
        this.sortOrderStrategy = createSortOrderStrategy(sortOrder);
        this.sortTypeStrategy = createSortTypeStrategy(sortType);
    }

    public List<ProxyBook> sortBooks(){
        return sortTypeStrategy.sort(sortOrderStrategy.sort());

   }
    private SortOrderStrategy createSortOrderStrategy(String sortOrder) {
        if ("asc".equalsIgnoreCase(sortOrder)) {
            return new AscendingSortOrderStrategy();
        } else if ("desc".equalsIgnoreCase(sortOrder)) {
            return new DescendingSortOrderStrategy();
        }
        return null;
    }

    private SortTypeStrategy createSortTypeStrategy(String sortType) {
        switch (sortType.toLowerCase()) {
            case "year":
                return new YearSortTypeStrategy();
            case "author":
                return new AuthorSortTypeStrategy();
            case "title":
                return new TitleSortTypeStrategy();
           }
           return null;
    }
}
