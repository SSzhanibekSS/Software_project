package root.Patterns.Strategy;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import root.Patterns.Proxy.ProxyBook;
import root.Patterns.Singletone.DatebaseConnection;

import java.util.List;
import java.util.jar.JarEntry;

public class TitleSortTypeStrategy implements SortTypeStrategy {
    @Override
    public List<ProxyBook> sort(String OrderType) {
        JdbcTemplate jdbcTemplate = DatebaseConnection.getConnetion();

        return jdbcTemplate.query("select * from available_books order by book_title " + OrderType, new BeanPropertyRowMapper<>(ProxyBook.class));

    }
}
