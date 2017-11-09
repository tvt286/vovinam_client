package vn.tranty.vovinam_client.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.tranty.vovinam_client.R;
import vn.tranty.vovinam_client.dialogs.ChangePasswordDialog;
import vn.tranty.vovinam_client.models.users.UserModel;
import vn.tranty.vovinam_client.preferences.UserShared;

public class ProfileActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar myToolbar;
    @BindView(R.id.row_change_password)
    LinearLayout changePass;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.row_logout)
    LinearLayout changeLogout;
    @BindView(R.id.row_lock)
    LinearLayout lockAccount;

    private UserModel userVO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        setUpToolbar();
        userVO = UserShared.ins(this).getUserModel();
        setData();
    }

    private void setUpToolbar() {
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Thông tin");
    }

    private void setData() {
        tvName.setText(userVO.fullName);
        if (userVO.isAdminCompany)
            lockAccount.setVisibility(View.VISIBLE);
    }


    @OnClick(R.id.row_change_password)
    void changePass() {
        Intent i = new Intent(this, ChangePasswordDialog.class);
        startActivity(i);
    }


    @OnClick(R.id.row_lock)
    void lockAccount() {
        Intent i = new Intent(this, LockAccountActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.row_logout)
    void logout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Đăng Xuất");
        builder.setMessage("Bạn có muốn đăng xuất không?");
        builder.setCancelable(false);
        builder.setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

            }
        });

        builder.setNegativeButton("Đăng xuất", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                doLogout();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void doLogout() {
        UserShared.ins(this).clear();
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();
    }
}
