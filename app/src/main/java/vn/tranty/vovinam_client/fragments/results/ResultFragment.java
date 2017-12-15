package vn.tranty.vovinam_client.fragments.results;

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
import vn.tranty.vovinam_client.customs.CustomListView;
import vn.tranty.vovinam_client.customs.CustomSwipeLayout;
import vn.tranty.vovinam_client.dialogs.HistoryDialog;
import vn.tranty.vovinam_client.dialogs.PointDialog;
import vn.tranty.vovinam_client.interfaces.ItemListeners;
import vn.tranty.vovinam_client.interfaces.Response;
import vn.tranty.vovinam_client.mics.Contanst;
import vn.tranty.vovinam_client.models.chamthi.StudentModel;
import vn.tranty.vovinam_client.models.results.ResultModel;
import vn.tranty.vovinam_client.models.users.UserModel;
import vn.tranty.vovinam_client.models.users.UserPermission;
import vn.tranty.vovinam_client.preferences.UserShared;
import vn.tranty.vovinam_client.requests.LevelUpRequest;


public class ResultFragment extends Fragment implements ItemListeners {
    private static final String LEVEL = "Level";

    @BindView(R.id.swpStudent)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.layout_null_permission)
    RelativeLayout layoutNullPermission;
    @BindView(R.id.layout_network)
    RelativeLayout layoutNetwork;
    @BindView(R.id.linear_result)
    CustomListView lvResults;

    // TODO: Rename and change types of parameters
    private ArrayList<ResultModel> arrResults;
    private VovinamApplication application;
    private int level = 1;
    private int examinationId;
    private UserModel userVO;

    public ResultFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param level Parameter 1.
     * @return A new instance of fragment StudentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ResultFragment newInstance(int level) {
        ResultFragment fragment = new ResultFragment();
        Bundle args = new Bundle();
        args.putInt(LEVEL, level);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            level = getArguments().getInt(LEVEL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_result, container, false);
        ButterKnife.bind(this, view);
        init();
        checkPermission();
        return view;
    }


    private void checkPermission() {
        if (!userVO.isAdminCompany) {
            layoutNullPermission.setVisibility(View.VISIBLE);
            layoutNetwork.setVisibility(View.GONE);
        } else {
            swipeRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    swipeRefreshLayout.setRefreshing(true);
                //    getData();
                }
            });

        }
    }

    @OnClick(R.id.layout_network)
    void clickNetowrk() {
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
        LevelUpRequest.getResults(examinationId, 1, level, new Response() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(int error_code, String message, Object obj) {
                if (error_code == 200) {
                    arrResults = (ArrayList<ResultModel>) obj;
                    lvResults.addListView(lvResults, arrResults, ResultFragment.this);
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
        arrResults = new ArrayList<>();
        swipeRefreshLayout.setColorSchemeResources(R.color.color_blue, R.color.color_yellow, R.color.color_red);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (userVO.isAdminCompany) {
                    getData();
                } else
                    swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onClick(View view, int position) {

    }

    @Override
    public void onLongClick(View view, int position) {

    }
}
