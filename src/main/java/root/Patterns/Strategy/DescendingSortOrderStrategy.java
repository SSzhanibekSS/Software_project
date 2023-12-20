package root.Patterns.Strategy;

public class DescendingSortOrderStrategy implements SortOrderStrategy {
    @Override
    public String sort() {
        return "desc";
    }
}
