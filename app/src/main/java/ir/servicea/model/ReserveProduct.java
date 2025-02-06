package ir.servicea.model;

public class ReserveProduct {

    private int product_id;
    private String product_name;
    private String price;
    private int brandId;
    private int productGroupId;
    private String productGroupTitle;
    private String brandName;
    private String gradeName;
    private String qualityTypeName;
    private String description;
    private String imageUrl;
    private Boolean exist;

    public ReserveProduct() {
    }

    public ReserveProduct(String product_name, String price, int brandId, int productGroupId, String productGroupTitle, String brandName, String gradeName, String qualityTypeName, String description, String imageUrl, Boolean exist) {
        this.product_name = product_name;
        this.setPrice(price);
        this.setBrandId(brandId);
        this.setProductGroupId(productGroupId);
        this.setProductGroupTitle(productGroupTitle);
        this.setBrandName(brandName);
        this.setGradeName(gradeName);
        this.setQualityTypeName(qualityTypeName);
        this.setDescription(description);
        this.setImageUrl(imageUrl);
        this.setExist(exist);


    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    public String getProductGroupTitle() {
        return productGroupTitle;
    }

    public void setProductGroupTitle(String productGroupTitle) {
        this.productGroupTitle = productGroupTitle;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getQualityTypeName() {
        return qualityTypeName;
    }

    public void setQualityTypeName(String qualityTypeName) {
        this.qualityTypeName = qualityTypeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public int getProductGroupId() {
        return productGroupId;
    }

    public void setProductGroupId(int productGroupId) {
        this.productGroupId = productGroupId;
    }

    public Boolean getExist() {
        return exist;
    }

    public void setExist(Boolean exist) {
        this.exist = exist;
    }
}