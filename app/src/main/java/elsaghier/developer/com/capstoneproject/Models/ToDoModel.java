package elsaghier.developer.com.capstoneproject.Models;

/**
 * Created by ELSaghier on 2/3/2018.
 */

public class ToDoModel {
    private String item;

    public ToDoModel() {
    }

    public ToDoModel(String item) {
        this.item = item;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
