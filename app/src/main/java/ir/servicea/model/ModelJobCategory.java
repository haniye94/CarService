package ir.servicea.model;

public class ModelJobCategory {

    int id;
    String title;
    int status;


    public ModelJobCategory() {
    }

    public ModelJobCategory(int id, String title, int status) {
        this.id = id;
        this.title = title;
        this.status = status;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
