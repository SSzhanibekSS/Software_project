package root.Patterns.Strategy;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import root.Patterns.Proxy.ProxyBook;
import root.Patterns.Singletone.DatebaseConnection;

import java.util.List;

public class YearSortTypeStrategy implements SortTypeStrategy {
    @Override
    public List<ProxyBook> sort(String OrderType) {
        JdbcTemplate jdbcTemplate = DatebaseConnection.getConnetion();

        return jdbcTemplate.query("select * from available_books order by year_of_production " + OrderType, new BeanPropertyRowMapper<>(ProxyBook.class));

    }
}
