package vn.tranty.vovinam_client.fragments.doikhang;

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
import vn.tranty.vovinam_client.adapters.DoiKhangAdapter;
import vn.tranty.vovinam_client.adapters.StudentAdapter;
import vn.tranty.vovinam_client.customs.CustomSwipeLayout;
import vn.tranty.vovinam_client.dialogs.HistoryDialog;
import vn.tranty.vovinam_client.dialogs.PointDialog;
import vn.tranty.vovinam_client.interfaces.ItemStudentListeners;
import vn.tranty.vovinam_client.interfaces.Response;
import vn.tranty.vovinam_client.mics.Contanst;
import vn.tranty.vovinam_client.models.chamthi.CompeteModel;
import vn.tranty.vovinam_client.models.chamthi.StudentModel;
import vn.tranty.vovinam_client.models.users.UserModel;
import vn.tranty.vovinam_client.models.users.UserPermission;
import vn.tranty.vovinam_client.preferences.UserShared;
import vn.tranty.vovinam_client.requests.CompeteRequest;
import vn.tranty.vovinam_client.requests.LevelUpRequest;


public class CompeteFragment extends Fragment {
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
    private DoiKhangAdapter adapter;
    private ArrayList<CompeteModel> arrCompetes;
    private List<UserPermission> arrPermissions;
    private UserModel userVO;
    private VovinamApplication application;
    private int permission = 1;
    private int pointType;
    private UserPermission per;
    private int studentSelected;
    private CompeteModel competeModel;
    private float point;

    public CompeteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param pointType Parameter 1.
     * @return A new instance of fragment StudentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CompeteFragment newInstance(int pointType) {
        CompeteFragment fragment = new CompeteFragment();
        Bundle args = new Bundle();
        args.putInt(POINT_TYPE, pointType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            pointType = getArguments().getInt(POINT_TYPE);

            if (pointType == Contanst.GENDER.DK_NAM)
                permission = Contanst.ROLE.DK_NAM;
            else if (pointType == Contanst.GENDER.DK_NU)
                permission = Contanst.ROLE.DK_NU;

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

        adapter = new DoiKhangAdapter(getActivity(), new ItemStudentListeners() {
            @Override
            public void onClick(View view, int position) {
                studentSelected = position;
                competeModel = arrCompetes.get(position);

                Intent i = new Intent(getActivity(), PointDialog.class);
//                i.putExtra("UserId", userVO.id);
//                i.putExtra("StudentId", arrCompetes.get(position).id);
//                i.putExtra("StudentName", arrCompetes.get(position).name);
//                i.putExtra("PointType", pointType);
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
        CompeteRequest.getCompetes(5, 1, pointType, new Response() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(int error_code, String message, Object obj) {
                if (error_code == 200) {
                    arrCompetes = (ArrayList<CompeteModel>) obj;
                    adapter.setArrayStudents(arrCompetes, pointType);
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
        userVO = UserShared.ins(getActivity()).getUserModel();
        arrPermissions = application.getArrayPermission(userVO.id);
        per = new UserPermission(userVO.id, permission);
        arrCompetes = new ArrayList<>();
        addRecycler();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == END_POINT) {
            point = data.getFloatExtra("Point", 0);
//            if (pointType == Contanst.POINT_TYPE.CO_BAN) {
//                studentModel.coBan.point = point;
//                studentModel.coBan.userName = userVO.fullName;
//            } else if (pointType == Contanst.POINT_TYPE.VO_DAO) {
//                studentModel.voDao.point = point;
//                studentModel.voDao.userName = userVO.fullName;
//
//            }

            new CapNhatGiaoDienDiemThi().execute();
        }
    }


    public class CapNhatGiaoDienDiemThi extends AsyncTask<Void, CompeteModel, Void> {


        @Override
        protected Void doInBackground(Void... params) {
            publishProgress(competeModel);
            return null;
        }

        @Override
        protected void onProgressUpdate(CompeteModel... values) {
            super.onProgressUpdate(values);
//            if (pointType == Contanst.POINT_TYPE.CO_BAN)
//                arrStudents.get(studentSelected).coBan = values[0].coBan;
//            else if (pointType == Contanst.POINT_TYPE.VO_DAO)
//                studentModel.voDao.point = point;
//            else if (pointType == Contanst.POINT_TYPE.THE_LUC)
//                studentModel.theLuc.point = point;
//            else if (pointType == Contanst.POINT_TYPE.QUYEN)
//                studentModel.quyen.point = point;
//            else if (pointType == Contanst.POINT_TYPE.SONG_LUYEN)
//                studentModel.songLuyen.point = point;
//            adapter.notifyItemChanged(studentSelected, studentModel);

        }
    }
}
