package vn.tranty.vovinam_client.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import vn.tranty.vovinam_client.ItemStudentListeners;
import vn.tranty.vovinam_client.R;
import vn.tranty.vovinam_client.models.StudentModel;

/**
 * Created by TRUC-SIDA on 10/19/2017.
 */

public class StudentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private ArrayList<StudentModel> arrStudents;
    private ItemStudentListeners listeners;

    public StudentAdapter(Context mContext, ItemStudentListeners listeners) {
        this.mContext = mContext;
        this.listeners = listeners;
        arrStudents = new ArrayList<>();
    }

    public void setArrayStudents(ArrayList<StudentModel> arr) {
        this.arrStudents = arr;
        this.notifyDataSetChanged();
    }

    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.item_student, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }


    class StudentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public StudentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (listeners != null)
                listeners.onClick(view, getAdapterPosition());
        }

    }
}
