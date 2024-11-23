package ir.servicea.model;

public class Ticket {
    int id ;
    int user_id;
    String content;
    String create_at;

    String title;

    boolean expanded;

    public Ticket() {
    }

    public Ticket(int id, int user_id, String content, String create_at, String title) {
        this.id = id;
        this.user_id = user_id;
        this.content = content;
        this.create_at = create_at;
        this.title = title;


    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
