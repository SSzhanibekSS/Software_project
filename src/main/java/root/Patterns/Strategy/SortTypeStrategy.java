package root.Patterns.Strategy;

import root.Patterns.Proxy.ProxyBook;

import java.util.List;

public interface SortTypeStrategy {
    List<ProxyBook> sort(String OrderType);
}