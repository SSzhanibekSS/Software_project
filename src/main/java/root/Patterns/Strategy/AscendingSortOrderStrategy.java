package root.Patterns.Strategy;

public class AscendingSortOrderStrategy implements SortOrderStrategy {
    @Override
    public String sort() {
        return "asc";
    }
}
