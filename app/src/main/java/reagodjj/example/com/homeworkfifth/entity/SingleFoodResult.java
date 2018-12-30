package reagodjj.example.com.homeworkfifth.entity;

public class SingleFoodResult {
    private int status;
    private String message;
    private SingleFoodInfo singleFoodInfo;

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

    public SingleFoodInfo getSingleFoodInfo() {
        return singleFoodInfo;
    }

    public void setSingleFoodInfo(SingleFoodInfo singleFoodInfo) {
        this.singleFoodInfo = singleFoodInfo;
    }
}
