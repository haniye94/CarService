package ir.tehranOil.model;

public class ModelSveInfoGroup {

    int id;
    String time_start;
    String time_end;
    int count_place;
    String enteza;
    String paziraee;
    String khedmat;

    public ModelSveInfoGroup(int id, String time_start, String time_end, int count_place, String enteza, String paziraee, String khedmat) {
        this.id = id;
        this.time_start = time_start;
        this.time_end = time_end;
        this.count_place = count_place;
        this.enteza = enteza;
        this.paziraee = paziraee;
        this.khedmat = khedmat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime_start() {
        return time_start;
    }

    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }

    public int getCount_place() {
        return count_place;
    }

    public void setCount_place(int count_place) {
        this.count_place = count_place;
    }

    public String getEnteza() {
        return enteza;
    }

    public void setEnteza(String enteza) {
        this.enteza = enteza;
    }

    public String getPaziraee() {
        return paziraee;
    }

    public void setPaziraee(String paziraee) {
        this.paziraee = paziraee;
    }

    public String getKhedmat() {
        return khedmat;
    }

    public void setKhedmat(String khedmat) {
        this.khedmat = khedmat;
    }
}
