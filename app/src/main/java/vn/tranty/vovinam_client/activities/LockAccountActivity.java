package vn.tranty.vovinam_client.activities;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.tranty.vovinam_client.R;
import vn.tranty.vovinam_client.abtracts.AbstractResponse;
import vn.tranty.vovinam_client.adapters.AccountAdapter;
import vn.tranty.vovinam_client.customs.CustomSwipeLayout;
import vn.tranty.vovinam_client.interfaces.ItemListeners;
import vn.tranty.vovinam_client.interfaces.Response;
import vn.tranty.vovinam_client.mics.Utils;
import vn.tranty.vovinam_client.models.users.UserModel;
import vn.tranty.vovinam_client.preferences.UserShared;
import vn.tranty.vovinam_client.requests.UserRequest;

public class LockAccountActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar myToolbar;
    @BindView(R.id.rc_account)
    RecyclerView rcAccount;
    @BindView(R.id.swpAccount)
    SwipeRefreshLayout swpAccount;
    @BindView(R.id.layout_network)
    RelativeLayout layoutNetwork;

    private UserModel userVO;
    private ArrayList<UserModel> arrUsers;
    private AccountAdapter adapter;
    private UserModel userSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_account);
        ButterKnife.bind(this);
        setUpToolbar();
        init();
        setupRecycler();
    }

    private void setupRecycler() {
        adapter = new AccountAdapter(this, new ItemListeners() {
            @Override
            public void onClick(View view, int position) {
                userSelected = arrUsers.get(position);
                doLockAccount();
            }

            @Override
            public void onLongClick(View view, int position) {
                // do nothing
            }
        });
        rcAccount.addOnScrollListener(new CustomSwipeLayout(swpAccount));
        rcAccount.setLayoutManager(new LinearLayoutManager(this));
        rcAccount.setAdapter(adapter);

        rcAccount.addOnScrollListener(new CustomSwipeLayout(swpAccount));
        swpAccount.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swpAccount.setRefreshing(true);
                getData();
            }
        });

        swpAccount.post(new Runnable() {
            @Override
            public void run() {
                swpAccount.setRefreshing(true);
                getData();
            }
        });
    }

    private void doLockAccount() {
        UserRequest.lockAccount(userSelected.id, new AbstractResponse() {
            @Override
            public void onSuccess(int error_code, String message, Object obj) {
                super.onSuccess(error_code, message, obj);
                Utils.showToast(message, LockAccountActivity.this);
            }

            @Override
            public void onFailure() {
                super.onFailure();
                Utils.showToast("Vui lòng kiểm tra lại internet!", LockAccountActivity.this);
            }
        });
    }

    @OnClick(R.id.layout_network)
    void clickNetwork() {
        swpAccount.post(new Runnable() {
            @Override
            public void run() {
                swpAccount.setRefreshing(true);
                getData();
            }
        });
    }


    private void init() {
        userVO = UserShared.ins(this).getUserModel();
        arrUsers = new ArrayList<>();
    }

    private void setUpToolbar() {
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Thông tin");
    }

    private void getData() {
        layoutNetwork.setVisibility(View.GONE);
        UserRequest.getUsers(1, new AbstractResponse() {

            @Override
            public void onSuccess(int error_code, String message, Object obj) {
                if (error_code == 200) {
                    arrUsers = (ArrayList<UserModel>) obj;
                    adapter.setArrayAccounts(arrUsers);
                }
                swpAccount.setRefreshing(false);
            }

            @Override
            public void onFailure() {
                swpAccount.setRefreshing(false);
                layoutNetwork.setVisibility(View.VISIBLE);
            }
        });
    }

}
