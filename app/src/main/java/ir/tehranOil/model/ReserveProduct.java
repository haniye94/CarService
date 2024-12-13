package ir.tehranOil.model;

public class ReserveProduct {

    private int id;
    private  String name;
    private  String price;
    private  String brand;
    private  String type;
    private  String description;
    private int imageResId;

    public ReserveProduct(){
    }

    public ReserveProduct(String name, String price, String brand, String type, String description, int imageResId) {
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.type = type;
        this.description = description;
        this.imageResId = imageResId;


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }
}

