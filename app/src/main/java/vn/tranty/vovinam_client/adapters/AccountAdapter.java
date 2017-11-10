package vn.tranty.vovinam_client.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import vn.tranty.vovinam_client.R;
import vn.tranty.vovinam_client.interfaces.ItemListeners;
import vn.tranty.vovinam_client.models.users.UserModel;

/**
 * Created by TRUC-SIDA on 10/19/2017.
 */

public class AccountAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private ArrayList<UserModel> arrAccount;
    private ItemListeners listeners;

    public AccountAdapter(Context mContext, ItemListeners listeners) {
        this.mContext = mContext;
        this.listeners = listeners;
        arrAccount = new ArrayList<>();
    }

    public void setArrayAccounts(ArrayList<UserModel> arr) {
        this.arrAccount = arr;
        this.notifyDataSetChanged();
    }

    @Override
    public AccountViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.item_account, parent, false);
        return new AccountViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AccountViewHolder myHolder = (AccountViewHolder) holder;
        UserModel item = arrAccount.get(position);
        myHolder.tvName.setText(item.fullName);
        myHolder.tvUserName.setText(item.userName);

        if (item.active)
            myHolder.switchLock.setChecked(true);
        else
            myHolder.switchLock.setChecked(false);


    }

    @Override
    public int getItemCount() {
        return arrAccount.size();
    }


    class AccountViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_username)
        TextView tvUserName;
        @BindView(R.id.switch_lock)
        SwitchCompat switchLock;

        public AccountViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            switchLock.setOnClickListener(this);
            switchLock.setOnLongClickListener(this);
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
