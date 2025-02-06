package ir.servicea.model.dbModel;

public class ModelProduceGroup {
    String product_group_id;
    String title;
    private String change_wage;
    int job_category_id;
    private String service_center_product_group_id;
    boolean exist;

    public ModelProduceGroup(String product_group_id, String title, boolean exist, String service_center_product_group_id, String change_wage) {
        this.product_group_id = product_group_id;
        this.title = title;
        this.exist = exist;
        this.setService_center_product_group_id(service_center_product_group_id);
        this.change_wage = change_wage;
    }

    public String getProductGroupId() {
        return product_group_id;
    }

    public void setProductGroupId(String id) {
        this.product_group_id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getJob_category_id() {
        return job_category_id;
    }

    public void setJob_category_id(int job_category_id) {
        this.job_category_id = job_category_id;
    }

    public boolean isExist() {
        return exist;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }

    public String getChange_wage() {
        return change_wage;
    }

    public void setChange_wage(String change_wage) {
        this.change_wage = change_wage;
    }

    public String getService_center_product_group_id() {
        return service_center_product_group_id;
    }

    public void setService_center_product_group_id(String service_center_product_group_id) {
        this.service_center_product_group_id = service_center_product_group_id;
    }
}
