package ir.tehranOil.model;

public class ModelAdvertise {
    String descriptionAdvertise;
    String button;
    String imageAdvertise;
    String slug;

    public ModelAdvertise(String descriptionAdvertise) {
        this.descriptionAdvertise = descriptionAdvertise;
    }

    public ModelAdvertise(String descriptionAdvertise, String imageAdvertise,String slug) {
        this.descriptionAdvertise = descriptionAdvertise;
        this.imageAdvertise = imageAdvertise;
        this.slug = slug;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDescriptionAdvertise() {
        return descriptionAdvertise;
    }

    public void setDescriptionAdvertise(String descriptionAdvertise) {
        this.descriptionAdvertise = descriptionAdvertise;
    }

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }

    public String getImageAdvertise() {
        return imageAdvertise;
    }

    public void setImageAdvertise(String imageAdvertise) {
        this.imageAdvertise = imageAdvertise;
    }
}
