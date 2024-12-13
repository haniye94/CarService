package ir.tehranOil.model.dbModel;

import ir.tehranOil.app.Constants.PLAK_TYPE;

public class ModelCustomer {

    int id;
    int car_id;
    String first_name;
    String last_name;
    String gender;
    String date_birthday;
    String phone;
    String plak;

    PLAK_TYPE plak_type = PLAK_TYPE.PLAK_GENERAL;
    String name_car;
    String type_car;
    String type_fuel;
    String date_save_customer;
    int car_name_id = 0,car_tip_id = 0,car_model_id = 0,fuel_type_id = 0,car_company_id=0;
    public ModelCustomer() {
    }

    public ModelCustomer(int id, String first_name, String last_name, String gender, String date_birthday, String phone, String plak, String name_car, String type_car, String type_fuel, String date_save_customer) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.date_birthday = date_birthday;
        this.phone = phone;
        this.plak = plak;
        this.name_car = name_car;
        this.type_car = type_car;
        this.type_fuel = type_fuel;
        this.date_save_customer = date_save_customer;
    }

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

    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public PLAK_TYPE getPlak_type() {
        return plak_type;
    }

    public void setPlak_type(PLAK_TYPE plak_type) {
        this.plak_type = plak_type;
    }

    public String getName_car() {
        return name_car;
    }

    public void setName_car(String name_car) {
        this.name_car = name_car;
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

    public int getCar_company_id() {
        return car_company_id;
    }

    public void setCar_company_id(int car_company_id) {
        this.car_company_id = car_company_id;
    }
}
