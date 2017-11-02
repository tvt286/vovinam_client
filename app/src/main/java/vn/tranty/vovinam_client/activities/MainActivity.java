package vn.tranty.vovinam_client.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RelativeLayout;

import java.lang.reflect.Field;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.tranty.vovinam_client.R;
import vn.tranty.vovinam_client.customs.BottomNavigationViewBehavior;
import vn.tranty.vovinam_client.fragments.levels.DoiKhangFragment;
import vn.tranty.vovinam_client.fragments.levels.LamDaiFragment;
import vn.tranty.vovinam_client.fragments.levels.LamDaiIFragment;
import vn.tranty.vovinam_client.fragments.levels.LamDaiIIFragment;
import vn.tranty.vovinam_client.fragments.levels.LamDaiIIIFragment;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar myToolbar;
    @BindView(R.id.bottom_nav)
    BottomNavigationView bottomNavigation;
    @BindView(R.id.ed_search)
    EditText editSearch;
    @BindView(R.id.profile_image)
    RelativeLayout imProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        setUpBottomMenu();
    }

    private void setUpBottomMenu() {
        disableShiftMode(bottomNavigation);
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) bottomNavigation.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationViewBehavior());

        // init fragment first
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, new LamDaiFragment()).commit();

        // onclick menu
        bottomNavigation.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        switch (item.getItemId()) {
                            case R.id.tab_lamdai:
                                transaction.replace(R.id.container, new LamDaiFragment()).commit();
                                return true;
                            case R.id.tab_lamdai_i:
                                transaction.replace(R.id.container, new LamDaiIFragment()).commit();
                                return true;

                            case R.id.tab_lamdai_ii:
                                transaction.replace(R.id.container, new LamDaiIIFragment()).commit();
                                return true;

                            case R.id.tab_lamdai_iii:
                                transaction.replace(R.id.container, new LamDaiIIIFragment()).commit();
                                return true;
                            case R.id.tab_doikhang:
                                transaction.replace(R.id.container, new DoiKhangFragment()).commit();
                                return true;
                        }
                        return true;
                    }
                });


    }


    @SuppressLint("RestrictedApi")
    public void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
        } catch (IllegalAccessException e) {
        }
    }


    @OnClick(R.id.profile_image)
    void onClickProfile() {
        Intent i = new Intent(this, ProfileActivity.class);
        startActivity(i);
    }


}
