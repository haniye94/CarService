package ir.servicea.model.dbModel;

public class ModelServices {

    int id;
    String date_services;
    String km_now;
    String km_next;
    String avg_function;
    String all_services_price;
    String description;
    String first_name;
    String last_name;
    String name_car;
    String phone;
    String plak;
    int id_customer;
    int id_khadamat;

    public ModelServices(int id, String date_services, String km_now, String km_next, String avg_function, String all_services_price, String description, String first_name, String last_name, String name_car, String phone, String plak, int id_customer) {
        this.id = id;
        this.date_services = date_services;
        this.km_now = km_now;
        this.km_next = km_next;
        this.avg_function = avg_function;
        this.all_services_price = all_services_price;
        this.description = description;
        this.first_name = first_name;
        this.last_name = last_name;
        this.name_car = name_car;
        this.phone = phone;
        this.plak = plak;
        this.id_customer = id_customer;
       // this.id_khadamat = id_khadamat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate_services() {
        return date_services;
    }

    public void setDate_services(String date_services) {
        this.date_services = date_services;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getKm_now() {
        return km_now;
    }

    public void setKm_now(String km_now) {
        this.km_now = km_now;
    }

    public String getKm_next() {
        return km_next;
    }

    public void setKm_next(String km_next) {
        this.km_next = km_next;
    }

    public String getName_car() {
        return name_car;
    }

    public void setName_car(String name_car) {
        this.name_car = name_car;
    }

    public String getAvg_function() {
        return avg_function;
    }

    public void setAvg_function(String avg_function) {
        this.avg_function = avg_function;
    }

    public String getAll_services_price() {
        return all_services_price;
    }

    public void setAll_services_price(String all_services_price) {
        this.all_services_price = all_services_price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPlak() {
        return plak;
    }

    public void setPlak(String plak) {
        this.plak = plak;
    }

    public int getId_customer() {
        return id_customer;
    }

    public void setId_customer(int id_customer) {
        this.id_customer = id_customer;
    }

    public int getId_khadamat() {
        return id_khadamat;
    }

    public void setId_khadamat(int id_khadamat) {
        this.id_khadamat = id_khadamat;
    }
}