package ir.tehranOil.model;

import kr.ry4nkim.objectspinner.ObjectSpinner;

public class Product implements ObjectSpinner.Delegate{
    public int id;
    public String name;
    @Override
    public String getSpinnerDelegate() {
        return name;
    }

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Product(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
