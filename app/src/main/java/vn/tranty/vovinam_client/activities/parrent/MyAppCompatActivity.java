package vn.tranty.vovinam_client.activities.parrent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import vn.tranty.vovinam_client.VovinamApplication;

/**
 * Created by TRUC-SIDA on 10/31/2017.
 */

public class MyAppCompatActivity extends AppCompatActivity {
    protected VovinamApplication application;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (VovinamApplication) getApplication();
    }
}
