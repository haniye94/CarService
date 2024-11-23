package ir.servicea.model;

public class ModelItemMain {
    int id;
    String name;
    int image;
    int img_bg;

    public ModelItemMain(int id, String name, int image, int img_bg) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.img_bg = img_bg;
    }

    public int getImg_bg() {
        return img_bg;
    }

    public void setImg_bg(int img_bg) {
        this.img_bg = img_bg;
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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
