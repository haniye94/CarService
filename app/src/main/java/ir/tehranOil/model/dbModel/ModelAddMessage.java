package ir.tehranOil.model.dbModel;

public class ModelAddMessage {
    int id;
    String title;
    String text;
    int status;
    String key;

    public ModelAddMessage(int id, String title, String text, int status, String key) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.status = status;
        this.key = key;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
