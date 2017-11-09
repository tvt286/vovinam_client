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
import vn.tranty.vovinam_client.fragments.students.StudentFragment;
import vn.tranty.vovinam_client.interfaces.Response;
import vn.tranty.vovinam_client.mics.Utils;
import vn.tranty.vovinam_client.requests.CompeteRequest;
import vn.tranty.vovinam_client.requests.LevelUpRequest;

public class PointDoiKhangDialog extends AppCompatActivity {
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.btn_ok)
    Button btnOk;
    @BindView(R.id.tv_point_1)
    EditText edPoint1;
    @BindView(R.id.tv_point_2)
    EditText edPoint2;
    @BindView(R.id.tv_name_1)
    TextView tvName1;
    @BindView(R.id.tv_name_2)
    TextView tvName2;

    private int student1Id, student2Id, userId;
    private String student1Name, student2Name;
    private float point1, point2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_point_doi_khang);
        ButterKnife.bind(this);
        getData();
        setData();
    }

    private void setData() {
        tvName1.setText(student1Name);
        tvName2.setText(student2Name);
    }

    private void getData() {
        Intent intent = getIntent();
        student1Id = intent.getIntExtra("Student1Id", 0);
        student2Id = intent.getIntExtra("Student2Id", 0);
        userId = intent.getIntExtra("UserId", 0);
        student1Name = intent.getStringExtra("Student1Name");
        student2Name = intent.getStringExtra("Student2Name");
    }

    @OnClick(R.id.btn_cancel)
    void cancelClick() {
        finish();
    }

    @OnClick(R.id.btn_ok)
    void okClick() {
        if (edPoint1.getText().equals("") || edPoint2.getText().equals("")) {
            Utils.showToast("Vui lòng nhập điểm!", PointDoiKhangDialog.this);
        } else {
            point1 = Float.parseFloat(edPoint1.getText().toString());
            point2 = Float.parseFloat(edPoint2.getText().toString());

            CompeteRequest.updatePointCompete(student1Id, student2Id, userId, point1, point2, new Response() {
                @Override
                public void onStart() {

                }

                @Override
                public void onSuccess(int error_code, String message, Object obj) {
                    if (error_code == 200) {
                        Utils.showToast("Cập nhật điểm thành công!", PointDoiKhangDialog.this);
                        Intent i = getIntent();
                        i.putExtra("Point1", point1);
                        i.putExtra("Point2", point2);
                        setResult(StudentFragment.END_POINT, i);
                        finish();
                    }
                }

                @Override
                public void onFailure() {
                    Utils.showToast("Vui lòng kiểm tra lại kết nối internet!", PointDoiKhangDialog.this);
                }
            });
        }
    }
}
