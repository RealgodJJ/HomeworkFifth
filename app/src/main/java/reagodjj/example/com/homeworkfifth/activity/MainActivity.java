package reagodjj.example.com.homeworkfifth.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import reagodjj.example.com.homeworkfifth.R;
import reagodjj.example.com.homeworkfifth.activity.fragment.MainFragment;
import reagodjj.example.com.homeworkfifth.activity.fragment.ShoppingFragment;
import reagodjj.example.com.homeworkfifth.activity.fragment.UserFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout llMainMenu, llShoppingMenu, llUserMenu;

    protected MainFragment mainFragment = new MainFragment();
    protected ShoppingFragment shoppingFragment = new ShoppingFragment();
    protected UserFragment userFragment = new UserFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        llMainMenu = findViewById(R.id.ll_main_menu);
        llShoppingMenu = findViewById(R.id.ll_shopping_menu);
        llUserMenu = findViewById(R.id.ll_user_menu);

        llMainMenu.setOnClickListener(this);
        llShoppingMenu.setOnClickListener(this);
        llUserMenu.setOnClickListener(this);

        getSupportFragmentManager().beginTransaction().add(R.id.rl_container_content, mainFragment)
                .add(R.id.rl_container_content, shoppingFragment).hide(shoppingFragment)
                .add(R.id.rl_container_content, userFragment).hide(userFragment).commit();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_main_menu:
                getSupportFragmentManager().beginTransaction().show(mainFragment).hide(shoppingFragment)
                        .hide(userFragment).commit();
                break;

            case R.id.ll_shopping_menu:
                getSupportFragmentManager().beginTransaction().hide(mainFragment).show(shoppingFragment)
                        .hide(userFragment).commit();
                break;

            case R.id.ll_user_menu:
                getSupportFragmentManager().beginTransaction().hide(mainFragment).hide(shoppingFragment)
                        .show(userFragment).commit();
                break;
        }
    }
}
