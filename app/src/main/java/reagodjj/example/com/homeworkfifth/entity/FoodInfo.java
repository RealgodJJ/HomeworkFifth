package reagodjj.example.com.homeworkfifth.entity;

import android.graphics.Bitmap;

public class FoodInfo {
    private int id;
    private String name;
    private String image;
    private Bitmap bitmap;
    private int count;
    private String price;
    private String description;
    private String action;

    public FoodInfo(int id, String name, String image, int count, String price, String description, String action) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.count = count;
        this.price = price;
        this.description = description;
        this.action = action;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
