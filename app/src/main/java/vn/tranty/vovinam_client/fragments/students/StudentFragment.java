package vn.tranty.vovinam_client.fragments.students;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.tranty.vovinam_client.R;
import vn.tranty.vovinam_client.VovinamApplication;
import vn.tranty.vovinam_client.adapters.StudentAdapter;
import vn.tranty.vovinam_client.customs.CustomSwipeLayout;
import vn.tranty.vovinam_client.dialogs.HistoryDialog;
import vn.tranty.vovinam_client.dialogs.PointDialog;
import vn.tranty.vovinam_client.interfaces.ItemListeners;
import vn.tranty.vovinam_client.interfaces.Response;
import vn.tranty.vovinam_client.mics.Contanst;
import vn.tranty.vovinam_client.models.chamthi.StudentModel;
import vn.tranty.vovinam_client.models.users.UserModel;
import vn.tranty.vovinam_client.models.users.UserPermission;
import vn.tranty.vovinam_client.preferences.UserShared;
import vn.tranty.vovinam_client.requests.LevelUpRequest;


public class StudentFragment extends Fragment {
    private static final String LEVEL = "Level";
    private static final String POINT_TYPE = "PointType";
    public static final int START_POINT = 1001;
    public static final int END_POINT = 1002;

    @BindView(R.id.rc_student)
    RecyclerView rcStudent;
    @BindView(R.id.swpStudent)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.layout_null_permission)
    RelativeLayout layoutNullPermission;
    @BindView(R.id.layout_network)
    RelativeLayout layoutNetwork;

    // TODO: Rename and change types of parameters
    private StudentAdapter adapter;
    private ArrayList<StudentModel> arrStudents;
    private List<UserPermission> arrPermissions;
    private UserModel userVO;
    private VovinamApplication application;
    private int permission = 1;
    private int level = 1;
    private int pointType;
    private UserPermission per;
    private int studentSelected;
    private StudentModel studentModel;
    private float point;
    private int examinationId;

    public StudentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param level     Parameter 1.
     * @param pointType Parameter 1.
     * @return A new instance of fragment StudentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StudentFragment newInstance(int level, int pointType) {
        StudentFragment fragment = new StudentFragment();
        Bundle args = new Bundle();
        args.putInt(LEVEL, level);
        args.putInt(POINT_TYPE, pointType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            level = getArguments().getInt(LEVEL);
            pointType = getArguments().getInt(POINT_TYPE);

            // tu vệ
            if (level == Contanst.FRAGMENT.LAMDAI && pointType == Contanst.POINT_TYPE.CO_BAN)
                permission = Contanst.ROLE.LAMDAI_COBAN;
            else if (level == Contanst.FRAGMENT.LAMDAI && pointType == Contanst.POINT_TYPE.VO_DAO)
                permission = Contanst.ROLE.LAMDAI_VODAO;
            else if (level == Contanst.FRAGMENT.LAMDAI && pointType == Contanst.POINT_TYPE.QUYEN)
                permission = Contanst.ROLE.LAMDAI_QUYEN;
            else if (level == Contanst.FRAGMENT.LAMDAI && pointType == Contanst.POINT_TYPE.THE_LUC)
                permission = Contanst.ROLE.LAMDAI_THELUC;
                // lam dai 1
            else if (level == Contanst.FRAGMENT.LAMDAI_I && pointType == Contanst.POINT_TYPE.CO_BAN)
                permission = Contanst.ROLE.LAMDAI_I_COBAN;
            else if (level == Contanst.FRAGMENT.LAMDAI_I && pointType == Contanst.POINT_TYPE.VO_DAO)
                permission = Contanst.ROLE.LAMDAI_I_VODAO;
            else if (level == Contanst.FRAGMENT.LAMDAI_I && pointType == Contanst.POINT_TYPE.QUYEN)
                permission = Contanst.ROLE.LAMDAI_I_QUYEN;
            else if (level == Contanst.FRAGMENT.LAMDAI_I && pointType == Contanst.POINT_TYPE.DOI_KHANG)
                permission = Contanst.ROLE.LAMDAI_I_DOIKHANG;

                // lam dai 2
            else if (level == Contanst.FRAGMENT.LAMDAI_II && pointType == Contanst.POINT_TYPE.CO_BAN)
                permission = Contanst.ROLE.LAMDAI_II_COBAN;
            else if (level == Contanst.FRAGMENT.LAMDAI_II && pointType == Contanst.POINT_TYPE.VO_DAO)
                permission = Contanst.ROLE.LAMDAI_II_VODAO;
            else if (level == Contanst.FRAGMENT.LAMDAI_II && pointType == Contanst.POINT_TYPE.QUYEN)
                permission = Contanst.ROLE.LAMDAI_II_QUYEN;

                // lam dai 3
            else if (level == Contanst.FRAGMENT.LAMDAI_III && pointType == Contanst.POINT_TYPE.CO_BAN)
                permission = Contanst.ROLE.LAMDAI_III_COBAN;
            else if (level == Contanst.FRAGMENT.LAMDAI_III && pointType == Contanst.POINT_TYPE.VO_DAO)
                permission = Contanst.ROLE.LAMDAI_III_VODAO;
            else if (level == Contanst.FRAGMENT.LAMDAI_III && pointType == Contanst.POINT_TYPE.QUYEN)
                permission = Contanst.ROLE.LAMDAI_III_QUYEN;
           else if (level == Contanst.FRAGMENT.LAMDAI_III && pointType == Contanst.POINT_TYPE.SONG_LUYEN)
                permission = Contanst.ROLE.LAMDAI_III_SONGLUYEN;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student, container, false);
        ButterKnife.bind(this, view);

        init();
        checkPermission();
        return view;
    }

    private void addRecycler() {

        adapter = new StudentAdapter(getActivity(), new ItemListeners() {
            @Override
            public void onClick(View view, int position) {
                studentSelected = position;
                studentModel = arrStudents.get(position);

                Intent i = new Intent(getActivity(), PointDialog.class);
                i.putExtra("UserId", userVO.id);
                i.putExtra("StudentId", arrStudents.get(position).id);
                i.putExtra("StudentName", arrStudents.get(position).name);
                i.putExtra("PointType", pointType);
                startActivityForResult(i, START_POINT);
            }

            @Override
            public void onLongClick(View view, int position) {
                Intent i = new Intent(getActivity(), HistoryDialog.class);
                startActivity(i);
            }
        });
        rcStudent.addOnScrollListener(new CustomSwipeLayout(swipeRefreshLayout));
        rcStudent.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcStudent.setAdapter(adapter);

        swipeRefreshLayout.setColorSchemeResources(R.color.color_blue, R.color.color_yellow, R.color.color_red);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (UserPermission.contains(arrPermissions, per) || userVO.isAdminCompany) {
                    getData();
                } else
                    swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void checkPermission() {
        if (!UserPermission.contains(arrPermissions, per) && !userVO.isAdminCompany) {
            layoutNullPermission.setVisibility(View.VISIBLE);
            layoutNetwork.setVisibility(View.GONE);
        } else {
            swipeRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    swipeRefreshLayout.setRefreshing(true);
                    getData();
                }
            });

        }
    }

    @OnClick(R.id.layout_network)
    void clickNetowrk()
    {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                getData();
            }
        });
    }

    private void getData() {
        layoutNullPermission.setVisibility(View.GONE);
        layoutNetwork.setVisibility(View.GONE);
        LevelUpRequest.getLevelUps(examinationId, 1, level, new Response() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(int error_code, String message, Object obj) {
                if (error_code == 200) {
                    arrStudents = (ArrayList<StudentModel>) obj;
                    adapter.setArrayStudents(arrStudents, pointType);
                }
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure() {
                swipeRefreshLayout.setRefreshing(false);
                layoutNetwork.setVisibility(View.VISIBLE);
                layoutNullPermission.setVisibility(View.GONE);

            }
        });
    }

    private void init() {
        application = (VovinamApplication) getActivity().getApplication();
        examinationId = application.getExaminationId();
        userVO = UserShared.ins(getActivity()).getUserModel();
        arrPermissions = application.getArrayPermission(userVO.id);
        per = new UserPermission(userVO.id, permission);
        arrStudents = new ArrayList<>();
        addRecycler();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == END_POINT) {
            point = data.getFloatExtra("Point", 0);
            if (pointType == Contanst.POINT_TYPE.CO_BAN) {
                studentModel.coBan.point = point;
                studentModel.coBan.userName = userVO.fullName;
            } else if (pointType == Contanst.POINT_TYPE.VO_DAO) {
                studentModel.voDao.point = point;
                studentModel.voDao.userName = userVO.fullName;

            } else if (pointType == Contanst.POINT_TYPE.THE_LUC) {
                studentModel.theLuc.point = point;
                studentModel.theLuc.userName = userVO.fullName;

            } else if (pointType == Contanst.POINT_TYPE.QUYEN) {
                studentModel.quyen.point = point;
                studentModel.quyen.userName = userVO.fullName;

            } else if (pointType == Contanst.POINT_TYPE.SONG_LUYEN) {
                studentModel.songLuyen.point = point;
                studentModel.songLuyen.userName = userVO.fullName;
            }

            new CapNhatGiaoDienDiemThi().execute();
        }
    }


    public class CapNhatGiaoDienDiemThi extends AsyncTask<Void, StudentModel, Void> {


        @Override
        protected Void doInBackground(Void... params) {
            publishProgress(studentModel);
            return null;
        }

        @Override
        protected void onProgressUpdate(StudentModel... values) {
            super.onProgressUpdate(values);
            if (pointType == Contanst.POINT_TYPE.CO_BAN)
                arrStudents.get(studentSelected).coBan = values[0].coBan;
            else if (pointType == Contanst.POINT_TYPE.VO_DAO)
                studentModel.voDao.point = point;
            else if (pointType == Contanst.POINT_TYPE.THE_LUC)
                studentModel.theLuc.point = point;
            else if (pointType == Contanst.POINT_TYPE.QUYEN)
                studentModel.quyen.point = point;
            else if (pointType == Contanst.POINT_TYPE.SONG_LUYEN)
                studentModel.songLuyen.point = point;
            adapter.notifyItemChanged(studentSelected, studentModel);

        }
    }
}
