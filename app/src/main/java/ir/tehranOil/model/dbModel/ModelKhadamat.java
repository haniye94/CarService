package ir.tehranOil.model.dbModel;

public class ModelKhadamat {

    int id;
    String title;
    int status;
    int type_id;
    String type;
    String value;
    String selectT;
    String selectB;
    int id_service;
    int id_customer;
    int km_usage;
    boolean send_msg;

    public ModelKhadamat(int id, String title, int status, String type, String selectT, String selectB, int id_service,int id_customer) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.type = type;
        this.selectT = selectT;
        this.selectB = selectB;
        this.id_service = id_service;
        this.id_customer = id_customer;
    }

    public ModelKhadamat(int id, String title, String selectT, String selectB,String type,int type_id,String value,int km_usage,boolean send_msg) {
        this.id = id;
        this.title = title;
        this.selectT = selectT;
        this.selectB = selectB;
        this.type = type;
        this.type_id = type_id;
        this.value = value;
        this.km_usage = km_usage;
        this.status = 0;
        this.send_msg=send_msg;
    }

    public boolean isSend_msg() {
        return send_msg;
    }

    public void setSend_msg(boolean send_msg) {
        this.send_msg = send_msg;
    }

    public int getKm_usage() {
        return km_usage;
    }

    public void setKm_usage(int km_usage) {
        this.km_usage = km_usage;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSelectT() {
        return selectT;
    }

    public void setSelectT(String selectT) {
        this.selectT = selectT;
    }

    public String getSelectB() {
        return selectB;
    }

    public void setSelectB(String selectB) {
        this.selectB = selectB;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId_service() {
        return id_service;
    }

    public void setId_service(int id_service) {
        this.id_service = id_service;
    }

    public int getId_customer() {
        return id_customer;
    }

    public void setId_customer(int id_customer) {
        this.id_customer = id_customer;
    }
}
