package ir.tehranOil.model;

public class ModelgetCount {
    int status;
    int count;

    public ModelgetCount(int status, int count) {
        this.status = status;
        this.count = count;
    }

    public int getId() {
        return status;
    }

    public void setId(int status) {
        this.status = status;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
