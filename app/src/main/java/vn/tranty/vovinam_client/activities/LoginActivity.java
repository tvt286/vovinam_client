package vn.tranty.vovinam_client.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.tranty.vovinam_client.R;
import vn.tranty.vovinam_client.VovinamApplication;
import vn.tranty.vovinam_client.activities.parrent.MyAppCompatActivity;
import vn.tranty.vovinam_client.interfaces.Response;
import vn.tranty.vovinam_client.mics.Utils;
import vn.tranty.vovinam_client.models.users.UserModel;
import vn.tranty.vovinam_client.preferences.UserShared;
import vn.tranty.vovinam_client.requests.UserRequest;

public class LoginActivity extends MyAppCompatActivity {
    
    @Bind(R.id.im_logo)
    ImageView imLogo;
    @Bind(R.id.view_login)
    LinearLayout viewlogin;
    @Bind(R.id.btn_login)
    Button btnLogin;

    @Bind(R.id.tv_username)
    TextView tvUserName;
    @Bind(R.id.cb_remember_me)
    CheckBox cbRememberMe;
    @Bind(R.id.tv_password)
    TextView tvPassword;
    private ProgressDialog progress;
    private UserModel userVO;
    private Boolean rememberMe;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setData();
    }

    private void setData() {
        userVO = UserShared.ins(this).getUserModel();
        if (userVO != null && UserShared.ins(this).getRememberMe())
        {
            tvUserName.setText(userVO.userName);
            tvPassword.setText(UserShared.ins(this).getPassword());
        }
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
                btnLogin.setClickable(false);
               UserRequest.login(tvUserName.getText().toString(), tvPassword.getText().toString(), new Response() {
                @Override
                public void onStart() {

                }

                @Override
                public void onSuccess(int error_code, String message, Object obj) {
                    if (error_code == 200)
                    {
                        userVO = (UserModel) obj;
                        // lưu user vào preferences
                        rememberMe = cbRememberMe.isChecked() ? true : false;
                        UserShared.ins(getBaseContext()).setUserModel(userVO, tvPassword.getText().toString(),rememberMe);

                        // lưu vào application
                        application.setArrPermission(userVO.id,userVO.userPermission);

                        Utils.showToast("Đăng nhập thành công!", LoginActivity.this);

                        Intent i = new Intent(getBaseContext(), MainActivity.class);
                        startActivity(i);
                        overridePendingTransition(0, 0);
                        finish();
                    }
                    else
                    {
                        btnLogin.setClickable(true);
                        Utils.showToast(message, LoginActivity.this);
                    }

                }

                @Override
                public void onFailure() {
                    btnLogin.setClickable(true);
                    Utils.showToast("Vui lòng kiểm tra lại internet!",LoginActivity.this);
                }
            });
        }

    }



}
