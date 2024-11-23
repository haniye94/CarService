package ir.servicea.model;

public class ModelML {
    int id ;
    int user_id;
    String content;
    String create_at;
    String send_at;
    String user_fullname;
    String user_phone;
    int char_count;
    int total_price;
    boolean expanded;

    public ModelML() {
    }

    public ModelML(int id, int user_id, String content, String create_at, String send_at, int char_count, int total_price, String user_fullname, String user_phone) {
        this.id = id;
        this.user_id = user_id;
        this.content = content;
        this.create_at = create_at;
        this.send_at = send_at;

        this.char_count = char_count;
        this.total_price = total_price;
        this.user_fullname = user_fullname;
        this.user_phone = user_phone;
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

    public String getSend_at() {
        return send_at;
    }

    public void setSend_at(String send_at) {
        this.send_at = send_at;
    }

    public String getUser_fullname() {
        return user_fullname;
    }

    public void setUser_fullname(String user_fullname) {
        this.user_fullname = user_fullname;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public int getChar_count() {
        return char_count;
    }

    public void setChar_count(int char_count) {
        this.char_count = char_count;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }
}
