package ir.servicea.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author haniye94 .
 * @since on 1/11/2025.
 */
public class ModelProductGroup {

    public static class ProductGroup {
        private String productGroupId;
        private String productGroupTitle;
        private List<Detail> details;

        public String getProductGroupId() {
            return productGroupId;
        }

        public void setProductGroupId(String productGroupId) {
            this.productGroupId = productGroupId;
        }

        public String getProductGroupTitle() {
            return productGroupTitle;
        }

        public void setProductGroupTitle(String productGroupTitle) {
            this.productGroupTitle = productGroupTitle;
        }

        public List<Detail> getDetails() {
            return details;
        }

        public void setDetails(List<Detail> details) {
            this.details = details;
        }

        // Getters and Setters
    }

    public static class Detail {
        private String gradeId;
        private String gradeName;
        @SerializedName("quality_type_id")
        private String qualityTypeId;
        @SerializedName("quality_type_name")
        private String qualityTypeName;

        public String getGradeId() {
            return gradeId;
        }

        public void setGradeId(String gradeId) {
            this.gradeId = gradeId;
        }

        public String getGradeName() {
            return gradeName;
        }

        public void setGradeName(String gradeName) {
            this.gradeName = gradeName;
        }

        public String getQualityTypeId() {
            return qualityTypeId;
        }

        public void setQualityTypeId(String qualityTypeId) {
            this.qualityTypeId = qualityTypeId;
        }

        public String getQualityTypeName() {
            return qualityTypeName;
        }

        public void setQualityTypeName(String qualityTypeName) {
            this.qualityTypeName = qualityTypeName;
        }

        // Getters and Setters
    }

    public static class Brand {
        private String brandId;
        private String brandName;

        public String getBrandId() {
            return brandId;
        }

        public void setBrandId(String brandId) {
            this.brandId = brandId;
        }

        public String getBrandName() {
            return brandName;
        }

        public void setBrandName(String brandName) {
            this.brandName = brandName;
        }

        // Getters and Setters
    }

    public static class ProductAdd {
        private String productGroupId;
        private String productGroupTitle;
        private String gradeId;
        private String gradeName;
        private String qualityTypeId;
        private String qualityTypeName;
        private String brandId;
        private String brandName;

        public String getProductGroupId() {
            return productGroupId;
        }

        public void setProductGroupId(String productGroupId) {
            this.productGroupId = productGroupId;
        }

        public String getProductGroupTitle() {
            return productGroupTitle;
        }

        public void setProductGroupTitle(String productGroupTitle) {
            this.productGroupTitle = productGroupTitle;
        }

        public String getGradeId() {
            return gradeId;
        }

        public void setGradeId(String gradeId) {
            this.gradeId = gradeId;
        }

        public String getGradeName() {
            return gradeName;
        }

        public void setGradeName(String gradeName) {
            this.gradeName = gradeName;
        }

        public String getQualityTypeId() {
            return qualityTypeId;
        }

        public void setQualityTypeId(String qualityTypeId) {
            this.qualityTypeId = qualityTypeId;
        }

        public String getQualityTypeName() {
            return qualityTypeName;
        }

        public void setQualityTypeName(String qualityTypeName) {
            this.qualityTypeName = qualityTypeName;
        }

        public String getBrandId() {
            return brandId;
        }

        public void setBrandId(String brandId) {
            this.brandId = brandId;
        }

        public String getBrandName() {
            return brandName;
        }

        public void setBrandName(String brandName) {
            this.brandName = brandName;
        }
    }
}
