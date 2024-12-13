package ir.tehranOil.model;

public class ModelListCustomer {

    int id;
    String nameCustomer;
    String plak;
    String phone;
    String  nameCar;

    public ModelListCustomer(String nameCustomer, String plak, String phone, String nameCar) {
        this.nameCustomer = nameCustomer;
        this.plak = plak;
        this.phone = phone;
        this.nameCar = nameCar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public String getPlak() {
        return plak;
    }

    public void setPlak(String plak) {
        this.plak = plak;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNameCar() {
        return nameCar;
    }

    public void setNameCar(String nameCar) {
        this.nameCar = nameCar;
    }
}
