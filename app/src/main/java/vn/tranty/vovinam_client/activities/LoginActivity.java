package vn.tranty.vovinam_client.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.tranty.vovinam_client.R;
import vn.tranty.vovinam_client.interfaces.Response;
import vn.tranty.vovinam_client.requests.UserRequest;

public class LoginActivity extends AppCompatActivity {
    
    @Bind(R.id.im_logo)
    ImageView imLogo;
    @Bind(R.id.view_login)
    LinearLayout viewlogin;
    @Bind(R.id.btn_login)
    Button btnLogin;

    @Bind(R.id.tv_username)
    TextView tvUserName;
    @Bind(R.id.tv_password)
    TextView tvPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_login)
    void onClickLogin()
    {
        if (tvPassword.getText().equals("") || tvUserName.getText().equals(""))
        {
            Toast.makeText(this, "Vui lòng nhập!", Toast.LENGTH_SHORT).show();
            return;
        }

        UserRequest.login(tvUserName.getText().toString(), tvPassword.getText().toString(), new Response() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(int error_code, String message, Object obj) {
                Intent i = new Intent(getBaseContext(), MainActivity.class);
                startActivity(i);
                overridePendingTransition(0,0);
                finish();
            }

            @Override
            public void onFailure() {

            }
        });

    }



}
