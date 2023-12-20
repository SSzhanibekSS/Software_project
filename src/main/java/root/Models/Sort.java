package root.Models;

public class Sort {
    private String order;
    private String type;

    public Sort(String order, String type) {
        this.order = order;
        this.type = type;
    }

    public Sort(){}

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
