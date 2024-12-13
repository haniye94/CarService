package ir.tehranOil.model.dbModel;

public class ModelSaveKhadamat {

    int id;
    String title;
    int status;
    int id_product;
    int id_service;
    boolean selb;
    boolean selt;
    String type;
    String value;

    public ModelSaveKhadamat() {
    }

    public ModelSaveKhadamat(int id, String title, int status, int id_product, int id_service, String type,String value) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.id_product = id_product;
        this.id_service = id_service;
        this.type = type;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean getSelb() {
        return selb;
    }

    public void setSelb(boolean selb) {
        this.selb = selb;
    }

    public boolean getSelt() {
        return selt;
    }

    public void setSelt(boolean selt) {
        this.selt = selt;
    }

    public int getId_service() {
        return id_service;
    }

    public void setId_service(int id_service) {
        this.id_service = id_service;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

}
