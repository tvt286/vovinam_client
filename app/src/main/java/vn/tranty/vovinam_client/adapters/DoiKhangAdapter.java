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
import vn.tranty.vovinam_client.R;
import vn.tranty.vovinam_client.interfaces.ItemListeners;
import vn.tranty.vovinam_client.models.chamthi.CompeteModel;

/**
 * Created by TRUC-SIDA on 10/19/2017.
 */

public class DoiKhangAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private ArrayList<CompeteModel> arrStudents;
    private ItemListeners listeners;
    private int pointType;

    public DoiKhangAdapter(Context mContext, ItemListeners listeners) {
        this.mContext = mContext;
        this.listeners = listeners;
        arrStudents = new ArrayList<>();
    }

    public void setArrayStudents(ArrayList<CompeteModel> arr, int pointType) {
        this.arrStudents = arr;
        this.pointType = pointType;
        this.notifyDataSetChanged();
    }

    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.item_doikhang, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        StudentViewHolder myHolder = (StudentViewHolder) holder;
        CompeteModel student = arrStudents.get(position);
        myHolder.tvName1.setText(String.valueOf(student.levelup1.name + " - " + student.levelup1.weight +"Kg"));
        myHolder.tvName2.setText(String.valueOf(student.levelup2.name + " - " + student.levelup2.weight +"Kg"));
        myHolder.tvClub1.setText(student.levelup1.club.name);
        myHolder.tvClub2.setText(student.levelup2.club.name);
        myHolder.tvPoint1.setText(String.valueOf(student.levelup1.doiKhang.point));
        myHolder.tvPoint2.setText(String.valueOf(student.levelup2.doiKhang.point));
        myHolder.tvStt.setText(String.valueOf(position + 1));
    }

    @Override
    public int getItemCount() {
        return arrStudents.size();
    }


    class StudentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        @BindView(R.id.tv_name_1)
        TextView tvName1;
        @BindView(R.id.tv_name_2)
        TextView tvName2;
        @BindView(R.id.tv_club_2)
        TextView tvClub2;
        @BindView(R.id.tv_club_1)
        TextView tvClub1;
        @BindView(R.id.tv_point_1)
        TextView tvPoint1;
        @BindView(R.id.tv_point_2)
        TextView tvPoint2;
        @BindView(R.id.tv_stt)
        TextView tvStt;

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
