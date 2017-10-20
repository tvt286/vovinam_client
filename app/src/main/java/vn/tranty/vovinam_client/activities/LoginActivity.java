package vn.tranty.vovinam_client.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.tranty.vovinam_client.R;

public class LoginActivity extends AppCompatActivity {
    
    @Bind(R.id.im_logo)
    ImageView imLogo;
    @Bind(R.id.view_login)
    LinearLayout viewlogin;
    @Bind(R.id.btn_login)
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_login)
    void onClickLogin()
    {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
        overridePendingTransition(0,0);
        finish();
    }



}
