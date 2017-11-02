package vn.tranty.vovinam_client.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import vn.tranty.vovinam_client.R;

/**
 * Created by TRUC-SIDA on 11/2/2017.
 */

public class SpinnerExaminationAdapter extends BaseAdapter {
    Context context;
    String[] examinationName;
    LayoutInflater inflter;

    public SpinnerExaminationAdapter(Context applicationContext, String[] examinationName) {
        this.context = applicationContext;
        this.examinationName = examinationName;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return examinationName.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.custom_spinner_items, null);
        TextView names = (TextView) view.findViewById(R.id.tv_name);
        names.setText(examinationName[i]);
        return view;
    }
}
