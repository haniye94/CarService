package ir.servicea.model;

/**
 * @author haniye94 .
 * @since on 1/6/2025.
 */
public class ModelAddedProductCenter {
    private int id;
    private int service_center_id;
    private String packaging;
    private String amount;
    private int real_price;
    private int amount_discount;
    private int customer_price;

    private int inventory;
    private String name;
    private int brand_id;
    private int grade_id;
    private int quality_type_id;
    private int product_group_id;
    private String imageUrl;
    private int product_name_id;
    private int status;



    public ModelAddedProductCenter() {
    }

    public int getService_center_id() {
        return service_center_id;
    }

    public void setService_center_id(int service_center_id) {
        this.service_center_id = service_center_id;
    }

    public String getPackaging() {
        return packaging;
    }

    public void setPackaging(String packaging) {
        this.packaging = packaging;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public int getReal_price() {
        return real_price;
    }

    public void setReal_price(int real_price) {
        this.real_price = real_price;
    }

    public int getAmount_discount() {
        return amount_discount;
    }

    public void setAmount_discount(int amount_discount) {
        this.amount_discount = amount_discount;
    }

    public int getCustomer_price() {
        return customer_price;
    }

    public void setCustomer_price(int customer_price) {
        this.customer_price = customer_price;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(int brand_id) {
        this.brand_id = brand_id;
    }

    public int getGrade_id() {
        return grade_id;
    }

    public void setGrade_id(int grade_id) {
        this.grade_id = grade_id;
    }

    public int getQuality_type_id() {
        return quality_type_id;
    }

    public void setQuality_type_id(int quality_type_id) {
        this.quality_type_id = quality_type_id;
    }

    public int getProduct_group_id() {
        return product_group_id;
    }

    public void setProduct_group_id(int product_group_id) {
        this.product_group_id = product_group_id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getProduct_name_id() {
        return product_name_id;
    }

    public void setProduct_name_id(int product_name_id) {
        this.product_name_id = product_name_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}