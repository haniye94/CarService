package ir.servicea.model;

import ir.servicea.app.Constants;

public class ModelServicesCustomer {


    int id;
    String date_services;
    String time_services;
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
    Constants.PLAK_TYPE plak_type;
    int id_customer;
    int id_khadamat;
    int car_id;
    int idc;
    String first_namec;
    String last_namec;
    String gender;
    String date_birthday;
    String phonec;
    String plakc;
    String name_carc;
    String type_car;
    String type_fuel;
    String date_save_customer;
    String detail_service;
    String center_name;
    String center_id;
    int rate_state;

    String job_category_id;
    enum RateState {
        NOT_RATE,
        RATE,
        RATE_LATER
    }
    public String getTime_services() {
        return time_services;
    }

    public void setTime_services(String time_services) {
        this.time_services = time_services;
    }
    public String getCenter_name() {
        return center_name;
    }

    public void setCenter_name(String center_name) {
        this.center_name = center_name;
    }

    public String getCenter_id() {
        return center_id;
    }

    public void setCenter_id(String center_id) {
        this.center_id = center_id;
    }

    int car_name_id = 0,car_tip_id = 0,car_model_id = 0,fuel_type_id = 0;

    public int getCar_name_id() {
        return car_name_id;
    }

    public void setCar_name_id(int car_name_id) {
        this.car_name_id = car_name_id;
    }

    public int getCar_tip_id() {
        return car_tip_id;
    }

    public void setCar_tip_id(int car_tip_id) {
        this.car_tip_id = car_tip_id;
    }

    public int getCar_model_id() {
        return car_model_id;
    }

    public void setCar_model_id(int car_model_id) {
        this.car_model_id = car_model_id;
    }

    public int getFuel_type_id() {
        return fuel_type_id;
    }

    public void setFuel_type_id(int fuel_type_id) {
        this.fuel_type_id = fuel_type_id;
    }

    public ModelServicesCustomer() {
    }

    public ModelServicesCustomer(int id, String date_services, String km_now, String km_next, String avg_function, String all_services_price, String description, String first_name, String last_name, String name_car, String phone, String plak, int id_customer, int id_khadamat, int idc, String first_namec, String last_namec, String gender, String date_birthday, String phonec, String plakc, String name_carc, String type_car, String type_fuel, String date_save_customer) {
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
        this.id_khadamat = id_khadamat;
        this.idc = idc;
        this.first_namec = first_namec;
        this.last_namec = last_namec;
        this.gender = gender;
        this.date_birthday = date_birthday;
        this.phonec = phonec;
        this.plakc = plakc;
        this.name_carc = name_carc;
        this.type_car = type_car;
        this.type_fuel = type_fuel;
        this.date_save_customer = date_save_customer;
    }

    public String getDetail_service() {
        return detail_service;
    }

    public void setDetail_service(String detail_service) {
        this.detail_service = detail_service;
    }

    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }

    public int getId_khadamat() {
        return id_khadamat;
    }

    public void setId_khadamat(int id_khadamat) {
        this.id_khadamat = id_khadamat;
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

    public String getName_car() {
        return name_car;
    }

    public void setName_car(String name_car) {
        this.name_car = name_car;
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

    public Constants.PLAK_TYPE getPlak_type() {
        return plak_type;
    }

    public void setPlak_type(Constants.PLAK_TYPE plak_type) {
        this.plak_type = plak_type;
    }

    public int getId_customer() {
        return id_customer;
    }

    public void setId_customer(int id_customer) {
        this.id_customer = id_customer;
    }

    public int getIdc() {
        return idc;
    }

    public void setIdc(int idc) {
        this.idc = idc;
    }

    public String getFirst_namec() {
        return first_namec;
    }

    public void setFirst_namec(String first_namec) {
        this.first_namec = first_namec;
    }

    public String getLast_namec() {
        return last_namec;
    }

    public void setLast_namec(String last_namec) {
        this.last_namec = last_namec;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDate_birthday() {
        return date_birthday;
    }

    public void setDate_birthday(String date_birthday) {
        this.date_birthday = date_birthday;
    }

    public String getPhonec() {
        return phonec;
    }

    public void setPhonec(String phonec) {
        this.phonec = phonec;
    }

    public String getPlakc() {
        return plakc;
    }

    public void setPlakc(String plakc) {
        this.plakc = plakc;
    }

    public String getName_carc() {
        return name_carc;
    }

    public void setName_carc(String name_carc) {
        this.name_carc = name_carc;
    }

    public String getType_car() {
        return type_car;
    }

    public void setType_car(String type_car) {
        this.type_car = type_car;
    }

    public String getType_fuel() {
        return type_fuel;
    }

    public void setType_fuel(String type_fuel) {
        this.type_fuel = type_fuel;
    }

    public String getDate_save_customer() {
        return date_save_customer;
    }

    public void setDate_save_customer(String date_save_customer) {
        this.date_save_customer = date_save_customer;
    }

    public int getRate_state() {
        return rate_state;
    }

    public void setRate_state(int rate_state) {
        this.rate_state = rate_state;
    }

    public String getJob_category_id() {
        return job_category_id;
    }

    public void setJob_category_id(String job_category_id) {
        this.job_category_id = job_category_id;
    }
}
