package root.Models;

public class Notification {
    int notification_id;
    int person_id;
    String notification;

    public Notification(int notification_id, int person_id, String notification) {
        this.notification_id = notification_id;
        this.person_id = person_id;
        this.notification = notification;
    }
    public Notification(){

    }

    public int getNotification_id() {
        return notification_id;
    }

    public void setNotification_id(int notification_id) {
        this.notification_id = notification_id;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }
}
