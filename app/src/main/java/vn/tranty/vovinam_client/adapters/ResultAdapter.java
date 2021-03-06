package vn.tranty.vovinam_client.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.ButterKnife;
import vn.tranty.vovinam_client.interfaces.ItemListeners;
import vn.tranty.vovinam_client.R;
import vn.tranty.vovinam_client.models.chamthi.StudentModel;

/**
 * Created by TRUC-SIDA on 10/19/2017.
 */

public class ResultAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private ArrayList<StudentModel> arrStudents;
    private ItemListeners listeners;

    public ResultAdapter(Context mContext, ItemListeners listeners) {
        this.mContext = mContext;
        this.listeners = listeners;
        arrStudents = new ArrayList<>();
    }

    public void setArrayStudents(ArrayList<StudentModel> arr) {
        this.arrStudents = arr;
        this.notifyDataSetChanged();
    }

    @Override
    public ResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.item_result, parent, false);
        return new ResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }


    class ResultViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        public ResultViewHolder(View itemView) {
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
