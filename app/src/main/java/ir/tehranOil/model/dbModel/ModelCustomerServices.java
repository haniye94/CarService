package ir.tehranOil.model.dbModel;

public class ModelCustomerServices {
    int id;
    String first_name;
    String last_name;
    String gender;
    String date_birthday;
    String phone;
    String plak;
    String name_car;
    String type_car;
    String type_fuel;
    String date_save_customer;
    int id_service;
    String date_services;
    String km_now;
    String km_nex;
    String avg_function;
    String all_services_price;
    String description;
    String first_name_service;
    String last_name_service;
    String name_car_service;
    String phone_service;
    String plak_service;
    int id_customer;

    public ModelCustomerServices(int id, String first_name, String last_name, String gender, String date_birthday, String phone, String plak, String name_car, String type_car, String type_fuel, String date_save_customer, int id_service, String date_services, String km_now, String km_nex, String avg_function, String all_services_price, String description, String first_name_service, String last_name_service, String name_car_service, String phone_service, String plak_service, int id_customer) {
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
        this.id_service = id_service;
        this.date_services = date_services;
        this.km_now = km_now;
        this.km_nex = km_nex;
        this.avg_function = avg_function;
        this.all_services_price = all_services_price;
        this.description = description;
        this.first_name_service = first_name_service;
        this.last_name_service = last_name_service;
        this.name_car_service = name_car_service;
        this.phone_service = phone_service;
        this.plak_service = plak_service;
        this.id_customer = id_customer;
    }


}
