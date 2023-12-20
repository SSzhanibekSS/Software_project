package root.Patterns.Observer;

public interface Subject {
    void addSubscriber(int person_id);
    void removeSubscriber(int person_id);
    void notifySubscriber(String message);
}
