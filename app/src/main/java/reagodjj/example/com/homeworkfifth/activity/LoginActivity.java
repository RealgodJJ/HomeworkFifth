package reagodjj.example.com.homeworkfifth.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;

import reagodjj.example.com.homeworkfifth.R;
import reagodjj.example.com.homeworkfifth.entity.Account;
import reagodjj.example.com.homeworkfifth.tools.SharedPreferencesUtil;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int REQUEST_CODE = 1;
    private EditText etInputUser, etInputPassword;
    private Button btLogin, btFindPassword, btFreeRegister;
    private SharedPreferencesUtil sharedPreferencesUtil;
    private Account account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        setListener();
        showLastLoginUser();
    }

    private void showLastLoginUser() {
        sharedPreferencesUtil = new SharedPreferencesUtil(LoginActivity.this, "user-list");
        try {
            account = (Account) sharedPreferencesUtil.getSerializableObject("account");
            if (account != null)
                etInputUser.setText(account.getUser());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        etInputUser = findViewById(R.id.et_input_user);
        etInputPassword = findViewById(R.id.et_input_password);
        btLogin = findViewById(R.id.bt_login);
        btFindPassword = findViewById(R.id.bt_find_password);
        btFreeRegister = findViewById(R.id.bt_free_register);
    }

    private void setListener() {
        btLogin.setOnClickListener(this);
        btFindPassword.setOnClickListener(this);
        btFreeRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login:
                String user = etInputUser.getText().toString();
                String password = etInputPassword.getText().toString();
                if (account.getUser().equals(user) && account.getPassword().equals(password))
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                break;

            case R.id.bt_find_password:
                break;

            case R.id.bt_free_register:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            sharedPreferencesUtil = new SharedPreferencesUtil(LoginActivity.this, "user-list");
            try {
                account = (Account) sharedPreferencesUtil.getSerializableObject("account");
                etInputUser.setText(account.getUser());
                etInputPassword.setText(account.getPassword());
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
