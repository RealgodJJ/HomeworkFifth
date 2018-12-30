package reagodjj.example.com.homeworkfifth.activity.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

import reagodjj.example.com.homeworkfifth.R;
import reagodjj.example.com.homeworkfifth.entity.SingleFoodInfo;

public class FoodFragment extends Fragment implements View.OnClickListener {
    private TextView tvItemTitle, tvRateFood, tvFoodInformation;
    private ImageView ivBack, ivFood;
    private RatingBar rbRateFood;
    private TextView tvOriginalPrice, tvTPrice;
    private Button btPrice;
    private static final String URL_STRING = "http://www.imooc.com/api/shopping?type=12";
    private static final String STATUS = "status";
    private static final String MESSAGE = "msg";
    private static final String DATA = "data";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String IMAGE = "img";
    private static final String ORIGINAL_PRICE = "originalprice";
    private static final String T_PRICE = "tPrice";
    private static final String PRICE = "price";
    private static final String DESCRIPTION = "description";
    private static final int GET_INFORMATION_SUCCESS = 1;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_food, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();

        if (getArguments() != null) {
            String name = getArguments().getString("name");
            tvItemTitle.setText(name);
        }

        @SuppressLint("HandlerLeak") final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == GET_INFORMATION_SUCCESS) {
                    SingleFoodInfo singleFoodInfo = (SingleFoodInfo) msg.obj;

                    RequestOptions requestOptions = new RequestOptions();
                    requestOptions = requestOptions.centerCrop().placeholder(R.mipmap.ic_launcher);
                    Glide.with(FoodFragment.this).load(singleFoodInfo.getImage())
                            .apply(requestOptions).into(ivFood);

                    tvOriginalPrice.setText(getResources().getString(R.string.price_1,
                            singleFoodInfo.getOriginalPrice()));
                    tvTPrice.setText(getResources().getString(R.string.t_price, singleFoodInfo.gettPrice()));
                    btPrice.setText(getResources().getString(R.string.price_down, singleFoodInfo.getPrice()));
                    tvFoodInformation.setText(getResources().getString(R.string.food_information,
                            singleFoodInfo.getDescription()));
                }
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = request();
                SingleFoodInfo singleFoodInfo = getInformation(result);

                Message message = Message.obtain();
                message.obj = singleFoodInfo;
                message.what = GET_INFORMATION_SUCCESS;
                handler.sendMessage(message);
            }
        }).start();
    }

    private SingleFoodInfo getInformation(String result) {
        SingleFoodInfo singleFoodInfo = null;
        try {
            JSONObject jsonObject = new JSONObject(result);
            int status = jsonObject.getInt(STATUS);
            String message = jsonObject.getString(MESSAGE);

            if (status == 1 && message.equals("成功")) {
                JSONObject tempJsonObject = jsonObject.getJSONObject(DATA);
                int id = tempJsonObject.getInt(ID);
                String name = tempJsonObject.getString(NAME);
                String image = tempJsonObject.getString(IMAGE);
                double originalPrice = tempJsonObject.getDouble(ORIGINAL_PRICE);
                double tPrice = tempJsonObject.getDouble(T_PRICE);
                String price = tempJsonObject.getString(PRICE);
                String description = tempJsonObject.getString(DESCRIPTION);

                singleFoodInfo = new SingleFoodInfo(id, name, image, originalPrice, tPrice, price, description);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return singleFoodInfo;
    }

    private void initView() {
        tvItemTitle = Objects.requireNonNull(getActivity()).findViewById(R.id.tv_item_title);
        ivBack = getActivity().findViewById(R.id.iv_back);
        ivFood = getActivity().findViewById(R.id.iv_food);
        rbRateFood = getActivity().findViewById(R.id.rb_rate_food);
        tvOriginalPrice = getActivity().findViewById(R.id.tv_original_price);
        tvTPrice = getActivity().findViewById(R.id.tv_t_price);
        btPrice = getActivity().findViewById(R.id.bt_price);
        tvRateFood = getActivity().findViewById(R.id.tv_rate_food);
        tvFoodInformation = getActivity().findViewById(R.id.tv_food_information);

        tvRateFood.setText(R.string.no_rating);

        rbRateFood.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                tvRateFood.setText(getResources().getString(R.string.rating, rating));
                Toast.makeText(getActivity(), getString(R.string.rating, rating),
                        Toast.LENGTH_SHORT).show();
            }
        });

        ivBack.setOnClickListener(this);
        btPrice.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                Objects.requireNonNull(getActivity()).onBackPressed();
                break;

            case R.id.bt_price:
                Toast.makeText(getActivity(), getString(R.string.buy_success), Toast.LENGTH_SHORT).show();
                Objects.requireNonNull(getActivity()).onBackPressed();
                break;
        }
    }

    private String request() {
        try {
            URL url = new URL(URL_STRING);
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


}
