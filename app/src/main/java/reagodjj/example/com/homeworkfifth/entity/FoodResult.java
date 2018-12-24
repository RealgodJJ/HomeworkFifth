package reagodjj.example.com.homeworkfifth.entity;

import java.util.List;

public class FoodResult {
    private int status;
    private String message;
    private List<FoodInfo> foodInfoList;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<FoodInfo> getFoodInfoList() {
        return foodInfoList;
    }

    public void setFoodInfoList(List<FoodInfo> foodInfoList) {
        this.foodInfoList = foodInfoList;
    }
}
