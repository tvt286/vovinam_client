package vn.tranty.vovinam_client.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.ButterKnife;
import vn.tranty.vovinam_client.R;
import vn.tranty.vovinam_client.models.HistoryModel;

/**
 * Created by TRUC-SIDA on 10/19/2017.
 */

public class HistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private ArrayList<HistoryModel> arrHistory;

    public HistoryAdapter(Context mContext) {
        this.mContext = mContext;
        arrHistory = new ArrayList<>();
    }

    public void setArrayHistory(ArrayList<HistoryModel> arr) {
        this.arrHistory = arr;
        this.notifyDataSetChanged();
    }

    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.item_history, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class HistoryViewHolder extends RecyclerView.ViewHolder {

        public HistoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
