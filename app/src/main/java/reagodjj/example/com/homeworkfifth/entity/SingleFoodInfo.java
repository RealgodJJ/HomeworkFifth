package reagodjj.example.com.homeworkfifth.entity;

import android.graphics.Bitmap;

public class SingleFoodInfo {
    private int id;
    private String name;
    private String image;
    private Bitmap bitmap;
    private double originalPrice;
    private double tPrice;
    private String price;
    private String description;

    public SingleFoodInfo(int id, String name, String image, double originalPrice, double tPrice, String price, String description) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.originalPrice = originalPrice;
        this.tPrice = tPrice;
        this.price = price;
        this.description = description;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public double gettPrice() {
        return tPrice;
    }

    public void settPrice(double tPrice) {
        this.tPrice = tPrice;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
