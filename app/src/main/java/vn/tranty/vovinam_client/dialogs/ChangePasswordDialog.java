package vn.tranty.vovinam_client.dialogs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.tranty.vovinam_client.R;
import vn.tranty.vovinam_client.interfaces.Response;
import vn.tranty.vovinam_client.mics.Utils;
import vn.tranty.vovinam_client.models.users.UserModel;
import vn.tranty.vovinam_client.preferences.UserShared;
import vn.tranty.vovinam_client.requests.UserRequest;

public class ChangePasswordDialog extends AppCompatActivity {
    @BindView(R.id.tv_password)
    EditText edPassword;
    @BindView(R.id.tv_password_new)
    EditText edPasswordNew;
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.btn_ok)
    Button btnOk;

    private UserModel userVO;
    private String password, passwordNew;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_change_password);
        ButterKnife.bind(this);
        userVO = UserShared.ins(this).getUserModel();
    }

    @OnClick(R.id.btn_cancel)
    void cancelClick() {
        finish();
    }

    @OnClick(R.id.btn_ok)
    void okClick() {
        password = edPassword.getText().toString();
        passwordNew = edPasswordNew.getText().toString();
        if (password.equals("") || passwordNew.equals("")) {
            Utils.showToast("Vui lòng không bỏ trống!", ChangePasswordDialog.this);
        } else if (!UserShared.ins(this).getPassword().equals(password)) {
            Utils.showToast("Mật khẩu cũ sai!", ChangePasswordDialog.this);
        } else if (UserShared.ins(this).getPassword().equals(passwordNew)) {
            Utils.showToast("Mật khẩu mới trùng với mật khẩu cũ!", ChangePasswordDialog.this);
        } else {
            UserRequest.changePassword(userVO.userName, password, passwordNew, new Response() {
                @Override
                public void onStart() {

                }

                @Override
                public void onSuccess(int error_code, String message, Object obj) {
                    if (error_code == 200) {
                        Utils.showToast("Cập nhật mật khẩu thành công!", ChangePasswordDialog.this);
                        finish();
                    }
                }

                @Override
                public void onFailure() {

                }
            });
        }
    }

}
