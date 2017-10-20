package vn.tranty.vovinam_client.dialogs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import vn.tranty.vovinam_client.R;
import vn.tranty.vovinam_client.adapters.HistoryAdapter;
import vn.tranty.vovinam_client.models.HistoryModel;

public class HistoryDialog extends AppCompatActivity {
    @Bind(R.id.rc_history)
    RecyclerView rcHistory;

    HistoryAdapter adapter;
    ArrayList<HistoryModel> arrHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_history);
        ButterKnife.bind(this);
        arrHistory = new ArrayList<>();

        adapter = new HistoryAdapter(this);

        rcHistory.setLayoutManager(new LinearLayoutManager(this));
        rcHistory.setAdapter(adapter);
        adapter.setArrayHistory(arrHistory);
    }
}
