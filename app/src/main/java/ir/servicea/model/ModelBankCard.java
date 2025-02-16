package ir.servicea.model;

import com.anychart.scales.DateTime;

/**
 * @author haniye94 .
 * @since on 2/13/2025.
 */
public class ModelBankCard {
    private int id;
    private int user_id;
    private int service_center_id;
    private int national_code;
    private String card_no;
    private String sheba_no;
    private String bank_name;
    private int default_bank_account;
    private int status;
    private DateTime create_at;
    private DateTime update_at;
    private DateTime delete_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getService_center_id() {
        return service_center_id;
    }

    public void setService_center_id(int service_center_id) {
        this.service_center_id = service_center_id;
    }

    public String getCard_no() {
        return card_no;
    }

    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }

    public String getSheba_no() {
        return sheba_no;
    }

    public void setSheba_no(String sheba_no) {
        this.sheba_no = sheba_no;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public int getDefault_bank_account() {
        return default_bank_account;
    }

    public void setDefault_bank_account(int default_bank_account) {
        this.default_bank_account = default_bank_account;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DateTime getCreate_at() {
        return create_at;
    }

    public void setCreate_at(DateTime create_at) {
        this.create_at = create_at;
    }

    public DateTime getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(DateTime update_at) {
        this.update_at = update_at;
    }

    public DateTime getDelete_at() {
        return delete_at;
    }

    public void setDelete_at(DateTime delete_at) {
        this.delete_at = delete_at;
    }

    public int getNational_code() {
        return national_code;
    }

    public void setNational_code(int national_code) {
        this.national_code = national_code;
    }
}
