package reagodjj.example.com.homeworkfifth.entity;

public class GridViewCard {
    private int cardId;
    private String title;

    public GridViewCard(int iconId, String title) {
        this.cardId = iconId;
        this.title = title;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
