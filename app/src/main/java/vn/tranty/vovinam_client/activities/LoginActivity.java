package vn.tranty.vovinam_client.activities;

import android.app.ProgressDialog;
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
import vn.tranty.vovinam_client.mics.Utils;
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
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btn_login)
    void onClickLogin()
    {
        if (tvPassword.getText().toString().matches("") || tvUserName.getText().toString().matches(""))
        {
            Utils.showToast("Vui lòng nhập tên đăng nhập hoặc mật khẩu!",this);
        }
        else
            {
                progress = new ProgressDialog(getBaseContext());
                progress = ProgressDialog.show(getBaseContext(), null, "Đang đăng nhập...", true);

                UserRequest.login(tvUserName.getText().toString(), tvPassword.getText().toString(), new Response() {
                @Override
                public void onStart() {

                }

                @Override
                public void onSuccess(int error_code, String message, Object obj) {
                    progress.dismiss();
                    Intent i = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(i);
                    overridePendingTransition(0, 0);
                    finish();
                }

                @Override
                public void onFailure() {
                    progress.dismiss();
                    Utils.showToast("Vui lòng kiểm tra lại internet!",LoginActivity.this);
                }
            });
        }

    }



}
