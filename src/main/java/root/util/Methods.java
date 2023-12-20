package root.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Methods {
    public static List<String> orders(){
        List<String> orders = new ArrayList<>();
        orders.add("asc");
        orders.add("desc");
        return orders;
    }
    public static List<String> types(){
        List<String> types = new ArrayList<>();
        types.add("title");
        types.add("author");
        types.add("year");
        return types;
    }
}
