package ir.tehranOil.model;

public class ModelOnvanMssg {
    int id ;
    String onvan;

    public ModelOnvanMssg(int id, String onvan) {
        this.id = id;
        this.onvan = onvan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOnvan() {
        return onvan;
    }

    public void setOnvan(String onvan) {
        this.onvan = onvan;
    }
}
