package reagodjj.example.com.homeworkfifth.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import reagodjj.example.com.homeworkfifth.R;
import reagodjj.example.com.homeworkfifth.entity.Account;
import reagodjj.example.com.homeworkfifth.tools.SharedPreferencesUtil;

public class RegisterActivity extends AppCompatActivity {
    private EditText etSetUser, etSetPassword;
    private Button btRegister;
    private SharedPreferencesUtil sharedPreferencesUtil;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
        setListener();
    }

    private void setListener() {
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = etSetUser.getText().toString();
                String password = etSetPassword.getText().toString();
                if (user.length() < 6) {
                    Toast.makeText(RegisterActivity.this, getString(R.string.user_rule), Toast.LENGTH_SHORT).show();
                } else if (password.length() < 6) {
                    Toast.makeText(RegisterActivity.this, R.string.password_rule, Toast.LENGTH_SHORT).show();
                } else {
                    sharedPreferencesUtil = new SharedPreferencesUtil(RegisterActivity.this, "user-list");
                    Account account = new Account(user, password);
                    try {
                        sharedPreferencesUtil.saveSerializableObject("account", account);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    sharedPreferencesUtil.commitAccount();
                    setResult(RESULT_OK);
                    finish();
                }
            }
        });
    }

    private void initView() {
        etSetUser = findViewById(R.id.et_set_user);
        etSetPassword = findViewById(R.id.et_set_password);
        btRegister = findViewById(R.id.bt_register);
    }
}
