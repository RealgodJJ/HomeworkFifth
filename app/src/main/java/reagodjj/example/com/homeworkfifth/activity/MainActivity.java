package reagodjj.example.com.homeworkfifth.activity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import reagodjj.example.com.homeworkfifth.R;
import reagodjj.example.com.homeworkfifth.adapter.GridViewAdapter;
import reagodjj.example.com.homeworkfifth.adapter.ListViewAdapter;
import reagodjj.example.com.homeworkfifth.entity.FoodInfo;
import reagodjj.example.com.homeworkfifth.entity.FoodResult;
import reagodjj.example.com.homeworkfifth.entity.GridViewCard;

public class MainActivity extends AppCompatActivity {
    public static final String ACTION = "action";
    private GridView gvFirstMenu;
    private ListView lvFoodItem;
    private List<GridViewCard> gridViewCardList;
    private List<FoodInfo> foodInfoList;
    private ListViewAdapter listViewAdapter;
    private int cardList[] = {R.drawable.fly1, R.drawable.car, R.drawable.autombile1,
            R.drawable.cake, R.drawable.food, R.drawable.watch, R.drawable.cp, R.drawable.phone};
    private static final String URL_STRING = "http://www.imooc.com/api/shopping?type=11";
    private static final String STATUS = "status";
    private static final String MESSAGE = "msg";
    private static final String DATA = "data";
    private static final String ID = "id";
    private static final String IMAGE = "img";
    private static final String NAME = "name";
    private static final String COUNT = "count";
    private static final String PRICE = "price";
    private static final String DESCRIPTION = "description";
    private static final String _ACTION = "action";

//    private String titleList[] = {"飞机", "车票", "汽车", "蛋糕", "美食", "手表", "电脑", "手机"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initGridView();

        lvFoodItem = findViewById(R.id.lv_food_item);
        new RequestFoodAsyncTask(listViewAdapter).execute();
    }

    private void initGridView() {
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

    @SuppressLint("StaticFieldLeak")
    public class RequestFoodAsyncTask extends AsyncTask<Void, Void, String> {
        private ListViewAdapter listViewAdapter;

        RequestFoodAsyncTask(ListViewAdapter listViewAdapter) {
            this.listViewAdapter = listViewAdapter;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... voids) {
            return request(URL_STRING);
        }

        private String request(String urlString) {
            try {
                URL url = new URL(urlString);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setConnectTimeout(30 * 1000);
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.connect();

                int requestCode = httpURLConnection.getResponseCode();

                if (requestCode == HttpURLConnection.HTTP_OK) {
                    InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    StringBuilder stringBuilder = new StringBuilder();

                    String line;

                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    return stringBuilder.toString();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            FoodResult foodResult = new FoodResult();

            try {
                JSONObject jsonObject = new JSONObject(result);
                int status = jsonObject.getInt(STATUS);
                String message = jsonObject.getString(MESSAGE);
                foodResult.setStatus(status);
                foodResult.setMessage(message);

                foodInfoList = new ArrayList<>();
                if (status == 1 && message.equals("成功")) {
                    JSONArray jsonArray = jsonObject.getJSONArray(DATA);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject tempJSONObject = (JSONObject) jsonArray.get(i);
                        int id = tempJSONObject.getInt(ID);
                        String name = tempJSONObject.getString(NAME);
                        String image = tempJSONObject.getString(IMAGE);
                        int count = tempJSONObject.getInt(COUNT);
                        String price = tempJSONObject.getString(PRICE);
                        String description = tempJSONObject.getString(DESCRIPTION);
                        String action = tempJSONObject.getString(_ACTION);
                        //为食物列表添加元素
                        FoodInfo foodInfo = new FoodInfo(id, name, image, count, price, description, action);
                        foodInfoList.add(foodInfo);
                    }

                    listViewAdapter = new ListViewAdapter(MainActivity.this, foodInfoList);
                    lvFoodItem.setAdapter(listViewAdapter);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            listViewAdapter.notifyDataSetChanged();
        }
    }
}
