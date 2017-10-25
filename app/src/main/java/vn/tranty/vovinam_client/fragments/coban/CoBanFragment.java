package vn.tranty.vovinam_client.fragments.coban;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import vn.tranty.vovinam_client.interfaces.ItemStudentListeners;
import vn.tranty.vovinam_client.R;
import vn.tranty.vovinam_client.dialogs.HistoryDialog;
import vn.tranty.vovinam_client.adapters.StudentAdapter;
import vn.tranty.vovinam_client.dialogs.PointDialog;
import vn.tranty.vovinam_client.models.students.StudentModel;

/**
 * Created by TRUC-SIDA on 10/18/2017.
 */

public class CoBanFragment extends Fragment {
    @Bind(R.id.rc_student)
    RecyclerView rcStudent;

    StudentAdapter adapter;
    ArrayList<StudentModel> arrStudents;

    public CoBanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student,container,false);
        ButterKnife.bind(this,view);

        arrStudents = new ArrayList<>();

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
        return view;
    }
}
