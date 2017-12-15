package vn.tranty.vovinam_client.customs;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import vn.tranty.vovinam_client.R;
import vn.tranty.vovinam_client.interfaces.ItemListeners;
import vn.tranty.vovinam_client.models.chamthi.StudentModel;
import vn.tranty.vovinam_client.models.results.ResultModel;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by TRUC-SIDA on 11/10/2017.
 */

public class CustomListView extends LinearLayout {
    public static final int NUM_SHOW_LIMIT = 4;

    private Context mContext;
    private ArrayList<StudentModel> arrStudents;
    private boolean showAll = false;
    private HolderGender holderGender;


    public CustomListView(Context context) {
        super(context);
        mContext = context;
    }

    public CustomListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public CustomListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
    }

    public void addListView(LinearLayout linearMain, ArrayList<ResultModel> arrList, ItemListeners listener) {
        for (int i = 0; i < arrList.size(); i++) {
            arrStudents = arrList.get(i).students;

            if (arrStudents.size() > NUM_SHOW_LIMIT) {
                showAll = false;
                holderGender = new HolderGender(arrList.get(i), listener, true);
            } else {
                showAll = true;
                holderGender = new HolderGender(arrList.get(i), listener, false);
            }

            if (arrStudents.size() > 0) {
                if (showAll) {
                    for (int j = 0; j < arrStudents.size(); j++) {
                        if (arrList.get(i).gender.equals("Nam"))
                            holderGender.child.add(new HolderStudent(arrStudents.get(j), 1, listener));
                        else
                            holderGender.child.add(new HolderStudent(arrStudents.get(j), 2, listener));

                        holderGender.layout.addView(holderGender.child.get(j).viewWrap);
                    }
                } else {
                    for (int j = 0; j < NUM_SHOW_LIMIT; j++) {
                        if (arrList.get(i).gender.equals("Nam"))
                            holderGender.child.add(new HolderStudent(arrStudents.get(j), 1, listener));
                        else
                            holderGender.child.add(new HolderStudent(arrStudents.get(j), 2, listener));

                        holderGender.layout.addView(holderGender.child.get(j).viewWrap);
                    }
                }
            }
            linearMain.addView(holderGender.viewWrap);
        }
    }


    private class HolderGender {

        private TextView tvGender;
        private TextView tvShowall;
        private LinearLayout layout;
        private LayoutInflater inflater;
        private View viewWrap;
        private ArrayList<HolderStudent> child;
        private ItemListeners listener;

        private HolderGender(ResultModel resultModel, ItemListeners listener, boolean showAll) {
            this.listener = listener;
            child = new ArrayList<HolderStudent>();
            inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            viewWrap = inflater.inflate(R.layout.item_gender, null);
            tvGender = viewWrap.findViewById(R.id.tv_gender);
            tvShowall = viewWrap.findViewById(R.id.tv_show_all);
            layout = viewWrap.findViewById(R.id.linear_student);

            tvGender.setText(resultModel.gender);
            if (!showAll)
                tvShowall.setVisibility(GONE);
            else
                tvShowall.setVisibility(VISIBLE);
            setEvent(resultModel);
        }

        private void setEvent(final ResultModel resultModel) {
            tvShowall.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    tvShowall.setVisibility(GONE);
                    layout.removeAllViews();
                    child = new ArrayList<HolderStudent>();
                    for (int j = 0; j < resultModel.students.size(); j++) {
                        if (resultModel.gender.equals("Nam"))
                            child.add(new HolderStudent(resultModel.students.get(j), 1, listener));
                        else
                            child.add(new HolderStudent(resultModel.students.get(j), 2, listener));
                        layout.addView(child.get(j).viewWrap);
                    }
                }
            });

        }
    }

    private class HolderStudent {
        private CircleImageView imStudent;
        private TextView tvName;
        private TextView tvClub;
        private TextView tvPoint;
        private RelativeLayout rowStudent;

        private LayoutInflater inflater;
        private View viewWrap;
        private ItemListeners listener;

        private HolderStudent(StudentModel studentModel, int gender, ItemListeners listener) {
            this.listener = listener;
            inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            viewWrap = inflater.inflate(R.layout.item_student, null);
            imStudent = viewWrap.findViewById(R.id.im_avatar);
            tvClub = viewWrap.findViewById(R.id.tv_club);
            tvName = viewWrap.findViewById(R.id.tv_name);
            tvPoint = viewWrap.findViewById(R.id.tv_point);
            rowStudent = viewWrap.findViewById(R.id.row_student);

            tvName.setText(studentModel.name);
            tvClub.setText(studentModel.club.name);
            tvPoint.setText(String.valueOf(studentModel.total));

            if (gender == 1)
                imStudent.setImageResource(R.drawable.ic_user_male);
            else
                imStudent.setImageResource(R.drawable.ic_user_female);


        }


    }

}

