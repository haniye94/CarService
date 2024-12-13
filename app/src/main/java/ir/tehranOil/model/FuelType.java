package ir.tehranOil.model;

import kr.ry4nkim.objectspinner.ObjectSpinner;

public class FuelType implements ObjectSpinner.Delegate{
    public int id;
    public String name;
    @Override
    public String getSpinnerDelegate() {
        return name;
    }

    public FuelType() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FuelType(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
