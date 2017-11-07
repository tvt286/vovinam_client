package vn.tranty.vovinam_client.dialogs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.tranty.vovinam_client.R;
import vn.tranty.vovinam_client.activities.LoginActivity;
import vn.tranty.vovinam_client.fragments.students.StudentFragment;
import vn.tranty.vovinam_client.interfaces.Response;
import vn.tranty.vovinam_client.mics.Utils;
import vn.tranty.vovinam_client.models.users.UserModel;
import vn.tranty.vovinam_client.preferences.UserShared;
import vn.tranty.vovinam_client.requests.LevelUpRequest;

public class PointDialog extends AppCompatActivity {

    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.btn_ok)
    Button btnOk;
    @BindView(R.id.tv_point)
    EditText edPoint;
    @BindView(R.id.tv_name)
    TextView tvName;

    private int pointType;
    private int studentId;
    private String studentName;
    private int userId;
    private float point;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_point);
        ButterKnife.bind(this);
        getData();
        setData();
    }

    private void setData() {
        tvName.setText(studentName);
    }

    private void getData() {
        Intent intent = getIntent();
        studentId = intent.getIntExtra("StudentId",0);
        userId = intent.getIntExtra("UserId",0);
        studentName = intent.getStringExtra("StudentName");
        pointType = intent.getIntExtra("PointType",0);

    }

    @OnClick(R.id.btn_cancel)
    void cancelClick()
    {
        finish();
    }

    @OnClick(R.id.btn_ok)
    void okClick()
    {
        if (edPoint.getText().equals(""))
        {
            Utils.showToast("Vui lòng nhập điểm!", PointDialog.this);
        }
        else {
            point = Float.parseFloat(edPoint.getText().toString());
            LevelUpRequest.updatePoint(studentId, userId, point, pointType, new Response() {
                @Override
                public void onStart() {

                }

                @Override
                public void onSuccess(int error_code, String message, Object obj) {
                    if (error_code == 200) {
                        Utils.showToast("Cập nhật điểm thành công!", PointDialog.this);
                        Intent i  = getIntent();
                        i.putExtra("Point", point);
                        setResult(StudentFragment.END_POINT, i);
                        finish();
                    }
                }

                @Override
                public void onFailure() {
                    Utils.showToast("Vui lòng kiểm tra lại kết nối internet!", PointDialog.this);
                }
            });
        }
    }
}
