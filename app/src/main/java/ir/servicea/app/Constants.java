package ir.servicea.app;

public class Constants {
    public final static String PROV_ADD_SERVICE = "prov_add_service";
    public final static long SEND_AGAIN_OTP_CODE_TIME = 76000;

    public static final String CAR_PLATE_TYPE = "car_plate_type";

    public static final String PLAK_AZAD_OLD_CITY_INDEX = "plk_azad_old_city_index";
    public static final String reserve_description = "reserve_description";
    public static final String reserve_service_accept = "application/json";
    public static final String reserve_service_content = "application/json";
    public static final String reserve_service_payment_base_url = "https://payment.zarinpal.com/pg/StartPay/";
    public static final String CAR_PLATE = "car_plate";
    public static final String CAR_ID = "id_car";
    public static final String IS_EDIT_SERVICE = "is_edit_Service";

    public enum PLAK_TYPE{
        PLAK_GENERAL(1),
        PLAK_TAXI(2),
        PLAK_EDARI(3),
        PLAK_ENTEZAMI(4),
        PLAK_MAOLOIN(5),
        PLAK_AZAD_NEW(6),
        PLAK_AZAD_OLD(7);

        public int id;
        PLAK_TYPE(int id) {
            this.id = id;
        }
    }

    public static PLAK_TYPE findById(int id){
        for(PLAK_TYPE type : PLAK_TYPE.values()){
            if( type.id == id){
                return type;
            }
        }
        return null;
    }
}
