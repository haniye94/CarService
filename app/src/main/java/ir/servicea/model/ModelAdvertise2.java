package ir.servicea.model;

public class ModelAdvertise2 {
    String descriptionAdvertise;
    String button;
    int imageAdvertise;

    public ModelAdvertise2(String descriptionAdvertise) {
        this.descriptionAdvertise = descriptionAdvertise;
    }

    public ModelAdvertise2(String descriptionAdvertise, int imageAdvertise) {
        this.descriptionAdvertise = descriptionAdvertise;
        this.imageAdvertise = imageAdvertise;
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

    public int getImageAdvertise() {
        return imageAdvertise;
    }

    public void setImageAdvertise(int imageAdvertise) {
        this.imageAdvertise = imageAdvertise;
    }
}
