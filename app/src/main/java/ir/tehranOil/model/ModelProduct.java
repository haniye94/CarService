package ir.tehranOil.model;

public class ModelProduct {
    String id;
    String nameKala;
    boolean check;

    public ModelProduct(String id,String nameKala, boolean check) {
        this.id = id;
        this.nameKala = nameKala;
        this.check = check;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameKala() {
        return nameKala;
    }

    public void setNameKala(String nameKala) {
        this.nameKala = nameKala;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
