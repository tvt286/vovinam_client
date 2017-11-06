package vn.tranty.vovinam_client.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import vn.tranty.vovinam_client.interfaces.ItemStudentListeners;
import vn.tranty.vovinam_client.R;
import vn.tranty.vovinam_client.mics.Contanst;
import vn.tranty.vovinam_client.models.chamthi.StudentModel;

/**
 * Created by TRUC-SIDA on 10/19/2017.
 */

public class StudentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private ArrayList<StudentModel> arrStudents;
    private ItemStudentListeners listeners;
    private int pointType;

    public StudentAdapter(Context mContext, ItemStudentListeners listeners) {
        this.mContext = mContext;
        this.listeners = listeners;
        arrStudents = new ArrayList<>();
    }

    public void setArrayStudents(ArrayList<StudentModel> arr, int pointType) {
        this.arrStudents = arr;
        this.pointType = pointType;
        this.notifyDataSetChanged();
    }

    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.item_student, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        StudentViewHolder myHolder = (StudentViewHolder) holder;
        StudentModel student = arrStudents.get(position);
        if (student.gender.contains("Male"))
            myHolder.imStudent.setImageResource(R.drawable.ic_user_male);
        else
            myHolder.imStudent.setImageResource(R.drawable.ic_user_female);

        myHolder.tvName.setText(student.name);
        myHolder.tvClub.setText(student.club.name);
        myHolder.tvPoint.setText(student.level.name);

        if (pointType == Contanst.POINT_TYPE.CO_BAN)
            myHolder.tvPoint.setText(String.valueOf(student.coBan.point));
        else if (pointType == Contanst.POINT_TYPE.VO_DAO)
            myHolder.tvPoint.setText(String.valueOf(student.voDao.point));
        else if (pointType == Contanst.POINT_TYPE.QUYEN)
            myHolder.tvPoint.setText(String.valueOf(student.quyen.point));
        else if (pointType == Contanst.POINT_TYPE.THE_LUC)
            myHolder.tvPoint.setText(String.valueOf(student.theLuc.point));
        else if (pointType == Contanst.POINT_TYPE.DOI_KHANG)
            myHolder.tvPoint.setText(String.valueOf(student.doiKhang.point));
        else if (pointType == Contanst.POINT_TYPE.SONG_LUYEN)
            myHolder.tvPoint.setText(String.valueOf(student.songLuyen.point));
    }

    @Override
    public int getItemCount() {
        return arrStudents.size();
    }


    class StudentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        @BindView(R.id.im_avatar)
        CircleImageView imStudent;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_club)
        TextView tvClub;
        @BindView(R.id.tv_point)
        TextView tvPoint;

        public StudentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (listeners != null)
                listeners.onClick(view, getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View view) {
            if (listeners != null)
                listeners.onLongClick(view, getAdapterPosition());
            return false;
        }
    }
}
