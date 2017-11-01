package vn.tranty.vovinam_client.fragments.coban;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.security.Permission;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import vn.tranty.vovinam_client.VovinamApplication;
import vn.tranty.vovinam_client.fragments.BlankFragment;
import vn.tranty.vovinam_client.interfaces.ItemStudentListeners;
import vn.tranty.vovinam_client.R;
import vn.tranty.vovinam_client.dialogs.HistoryDialog;
import vn.tranty.vovinam_client.adapters.StudentAdapter;
import vn.tranty.vovinam_client.dialogs.PointDialog;
import vn.tranty.vovinam_client.mics.Contanst;
import vn.tranty.vovinam_client.models.students.StudentModel;
import vn.tranty.vovinam_client.models.users.UserModel;
import vn.tranty.vovinam_client.models.users.UserPermission;
import vn.tranty.vovinam_client.preferences.UserShared;

/**
 * Created by TRUC-SIDA on 10/18/2017.
 */

public class CoBanFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String LEVEL = "Level";

    @Bind(R.id.rc_student)
    RecyclerView rcStudent;
    @Bind(R.id.layout_null_permission)
    RelativeLayout layoutNullPermission;

    private StudentAdapter adapter;
    private ArrayList<StudentModel> arrStudents;
    private ArrayList<UserPermission> arrPermissions;
    private UserModel userVO;
    private VovinamApplication application;
    private int permission = 1;
    private int level;

    public CoBanFragment() {
    }

    public static CoBanFragment newInstance(int level) {
        CoBanFragment fragment = new CoBanFragment();
        Bundle args = new Bundle();
        args.putInt(LEVEL, level);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            level = getArguments().getInt(LEVEL);
            if(level == Contanst.FRAGMENT.LAMDAI)
                permission = Contanst.ROLE.LAMDAI_COBAN;
            else if(level == Contanst.FRAGMENT.LAMDAI_I)
                permission = Contanst.ROLE.LAMDAI_I_COBAN;
            else if(level == Contanst.FRAGMENT.LAMDAI_II)
                permission = Contanst.ROLE.LAMDAI_II_COBAN;
            else if(level == Contanst.FRAGMENT.LAMDAI_III)
                permission = Contanst.ROLE.LAMDAI_III_COBAN;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student,container,false);
        ButterKnife.bind(this, view);

        init();
        checkPermission();

        return view;
    }

    private void addRecycler() {

        adapter = new StudentAdapter(getActivity(), new ItemStudentListeners() {
            @Override
            public void onClick(View view, int position) {
                Intent i = new Intent(getActivity(), PointDialog.class);
                startActivity(i);
            }

            @Override
            public void onLongClick(View view, int position) {
                Intent i = new Intent(getActivity(), HistoryDialog.class);
                startActivity(i);
            }
        });

        rcStudent.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcStudent.setAdapter(adapter);
        adapter.setArrayStudents(arrStudents);
    }

    private void checkPermission() {
        UserPermission per= new UserPermission(userVO.id, permission);
        if(!arrPermissions.contains(per))
        {
            layoutNullPermission.setVisibility(View.VISIBLE);
        }
        else
        {
            addRecycler();
        }
    }

    private void init() {
        application = (VovinamApplication) getActivity().getApplication();
        userVO = UserShared.ins(getActivity()).getUserModel();
        arrPermissions = application.getArrayPermission(userVO.id);
        arrStudents = new ArrayList<>();

    }
}
