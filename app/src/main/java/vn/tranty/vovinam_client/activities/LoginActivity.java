package vn.tranty.vovinam_client.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.tranty.vovinam_client.R;
import vn.tranty.vovinam_client.VovinamApplication;
import vn.tranty.vovinam_client.activities.parrent.MyAppCompatActivity;
import vn.tranty.vovinam_client.adapters.SpinnerExaminationAdapter;
import vn.tranty.vovinam_client.interfaces.Response;
import vn.tranty.vovinam_client.mics.Utils;
import vn.tranty.vovinam_client.models.examinations.ExaminationModel;
import vn.tranty.vovinam_client.models.users.UserModel;
import vn.tranty.vovinam_client.preferences.UserShared;
import vn.tranty.vovinam_client.requests.ExaminationRequest;
import vn.tranty.vovinam_client.requests.UserRequest;

public class LoginActivity extends MyAppCompatActivity {

    @BindView(R.id.im_logo)
    ImageView imLogo;
    @BindView(R.id.view_login)
    LinearLayout viewlogin;
    @BindView(R.id.btn_login)
    Button btnLogin;

    @BindView(R.id.tv_username)
    TextView tvUserName;
    @BindView(R.id.cb_remember_me)
    CheckBox cbRememberMe;
    @BindView(R.id.tv_password)
    TextView tvPassword;
    @BindView(R.id.sp_examination)
    Spinner spExamination;
    @BindView(R.id.layout_content)
    LinearLayout layoutContent;
    @BindView(R.id.layout_loading)
    RelativeLayout layoutLoading;
    @BindView(R.id.layout_login)
    RelativeLayout layoutLogin;

    private UserModel userVO;
    private Boolean rememberMe;
    private String[] arrNameExamination;
    private ArrayList<ExaminationModel> arrExaminations;
    private HashMap<Integer, String> arrExaminationId;
    private AlphaAnimation animContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        layoutLoading.setAlpha(1.0f);
        getData();
        setData();
    }

    private void getData() {
        ExaminationRequest.getExaminations(1, new Response() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(int error_code, String message, Object obj) {
                if (error_code == 200) {
                    arrExaminations = (ArrayList<ExaminationModel>) obj;
                    setupSpinner(arrExaminations);
                    animationContent();
                }
            }

            @Override
            public void onFailure() {
                showMessage();
            }
        });
    }

    private void showMessage() {
        Snackbar snackbar = Snackbar
                .make(layoutLogin, "Không thể kết nối internet!", Snackbar.LENGTH_LONG)
                .setAction("THỬ LẠI", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getData();
                    }
                });
        snackbar.show();

        layoutLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
            }
        });
    }

    private void animationContent() {
        animContent = new AlphaAnimation(1.0f, 0.0f);
        animContent.setDuration(1000);
        animContent.setFillAfter(true);
        layoutLoading.startAnimation(animContent);
        animContent.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                layoutContent.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void setupSpinner(ArrayList<ExaminationModel> arrExaminations) {
        arrNameExamination = new String[arrExaminations.size()];
        arrExaminationId = new HashMap<Integer, String>();
        for (int i = 0; i < arrExaminations.size(); i++) {
            arrExaminationId.put(i, String.valueOf(arrExaminations.get(i).id));
            arrNameExamination[i] = arrExaminations.get(i).name;
        }

        SpinnerExaminationAdapter spinnerAdapter = new SpinnerExaminationAdapter(this, arrNameExamination);
        spExamination.setAdapter(spinnerAdapter);
        spExamination.setSelection(arrNameExamination.length - 1);

        spExamination.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String id = arrExaminationId.get(spExamination.getSelectedItemPosition());
                application.setExaminationId(Integer.parseInt(id));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setData() {
        userVO = UserShared.ins(this).getUserModel();
        if (userVO != null && UserShared.ins(this).getRememberMe()) {
            tvUserName.setText(userVO.userName);
            tvPassword.setText(UserShared.ins(this).getPassword());
        }
    }

    @OnClick(R.id.btn_login)
    void onClickLogin() {
        if (tvPassword.getText().toString().matches("") || tvUserName.getText().toString().matches("")) {
            Utils.showToast("Vui lòng nhập tên đăng nhập hoặc mật khẩu!", this);
        } else {
            btnLogin.setClickable(false);
            UserRequest.login(tvUserName.getText().toString(), tvPassword.getText().toString(), new Response() {
                @Override
                public void onStart() {

                }

                @Override
                public void onSuccess(int error_code, String message, Object obj) {
                    if (error_code == 200) {
                        userVO = (UserModel) obj;
                        // lưu user vào preferences
                        rememberMe = cbRememberMe.isChecked() ? true : false;
                        UserShared.ins(getBaseContext()).setUserModel(userVO, tvPassword.getText().toString(), rememberMe);

                        // lưu vào application
                        application.setArrPermission(userVO.id, userVO.userPermission);

                        Utils.showToast("Đăng nhập thành công!", LoginActivity.this);

                        Intent i = new Intent(getBaseContext(), MainActivity.class);
                        startActivity(i);
                        overridePendingTransition(0, 0);
                        finish();
                    } else {
                        btnLogin.setClickable(true);
                        Utils.showToast(message, LoginActivity.this);
                    }


                }

                @Override
                public void onFailure() {
                    btnLogin.setClickable(true);
                    Utils.showToast("Vui lòng kiểm tra lại internet!", LoginActivity.this);
                }
            });
        }

    }


}
