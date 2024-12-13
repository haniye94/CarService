package ir.tehranOil.model;

import kr.ry4nkim.objectspinner.ObjectSpinner;

public class CarModel implements ObjectSpinner.Delegate{
    public int id;
    public String name;
    @Override
    public String getSpinnerDelegate() {
        return name;
    }

    public CarModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CarModel(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
