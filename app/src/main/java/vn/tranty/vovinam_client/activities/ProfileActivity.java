package vn.tranty.vovinam_client.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.tranty.vovinam_client.R;
import vn.tranty.vovinam_client.dialogs.ChangePasswordDialog;

public class ProfileActivity extends AppCompatActivity {
    @Bind(R.id.toolbar)
    Toolbar myToolbar;
    @Bind(R.id.row_change_password)
    LinearLayout changePass;
    @Bind(R.id.row_logout)
    LinearLayout changeLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Thông tin");

    }

    @OnClick(R.id.row_change_password)
    void changePass()
    {
        Intent i = new Intent(this, ChangePasswordDialog.class);
        startActivity(i);
    }

    @OnClick(R.id.row_logout)
    void logout()
    {
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

    }
}
