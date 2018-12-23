package reagodjj.example.com.homeworkfifth.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import reagodjj.example.com.homeworkfifth.R;
import reagodjj.example.com.homeworkfifth.adapter.GridViewAdapter;
import reagodjj.example.com.homeworkfifth.entity.GridViewCard;

public class MainActivity extends AppCompatActivity {
    private GridView gvFirstMenu;
    private List<GridViewCard> gridViewCardList;
    private int cardList[] = {R.drawable.fly1, R.drawable.car, R.drawable.autombile1,
            R.drawable.cake, R.drawable.food, R.drawable.watch, R.drawable.cp, R.drawable.phone};

//    private String titleList[] = {"飞机", "车票", "汽车", "蛋糕", "美食", "手表", "电脑", "手机"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gvFirstMenu = findViewById(R.id.gv_first_menu);
        gridViewCardList = new ArrayList<>();
        String titleList[] = getResources().getStringArray(R.array.card_title);
        GridViewCard gridViewCard;
        for (int i = 0; i < cardList.length; i++) {
            gridViewCard = new GridViewCard(cardList[i], titleList[i]);
            gridViewCardList.add(gridViewCard);
        }
        GridViewAdapter gridViewAdapter = new GridViewAdapter(this, gridViewCardList);
        gvFirstMenu.setAdapter(gridViewAdapter);
    }
}
