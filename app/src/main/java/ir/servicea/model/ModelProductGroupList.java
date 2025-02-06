package ir.servicea.model;

/**
 * @author haniye94 .
 * @since on 1/21/2025.
 */
public class ModelProductGroupList {

    private int id;
    private int job_category_id;
    private String title;
    private String km_usage;
    private String change_wage;
    private boolean send_msg;
    private boolean status;
    private boolean is_default;
    private String image_url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getJob_category_id() {
        return job_category_id;
    }

    public void setJob_category_id(int job_category_id) {
        this.job_category_id = job_category_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKm_usage() {
        return km_usage;
    }

    public void setKm_usage(String km_usage) {
        this.km_usage = km_usage;
    }

    public String getChange_wage() {
        return change_wage;
    }

    public void setChange_wage(String change_wage) {
        this.change_wage = change_wage;
    }

    public boolean isSend_msg() {
        return send_msg;
    }

    public void setSend_msg(boolean send_msg) {
        this.send_msg = send_msg;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isIs_default() {
        return is_default;
    }

    public void setIs_default(boolean is_default) {
        this.is_default = is_default;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
