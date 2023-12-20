package root.Patterns.Proxy;

import root.Models.RealBook;

import java.util.List;

public interface Book {
    int getId();
    String getTitle();
    List<String> getFullInfo();
}
