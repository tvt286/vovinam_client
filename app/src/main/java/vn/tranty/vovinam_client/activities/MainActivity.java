package vn.tranty.vovinam_client.activities;

import android.app.Activity;
import android.content.Context;
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
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.lang.reflect.Field;

import butterknife.Bind;
import butterknife.ButterKnife;
import vn.tranty.vovinam_client.R;
import vn.tranty.vovinam_client.customs.BottomNavigationViewBehavior;
import vn.tranty.vovinam_client.fragments.levels.LamDaiFragment;
import vn.tranty.vovinam_client.fragments.levels.LamDaiIFragment;
import vn.tranty.vovinam_client.fragments.levels.LamDaiIIFragment;
import vn.tranty.vovinam_client.fragments.levels.LamDaiIIIFragment;
import vn.tranty.vovinam_client.mics.Utils;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar myToolbar;
    @Bind(R.id.bottom_nav)
    BottomNavigationView bottomNavigation;
    @Bind(R.id.ed_search)
    EditText editSearch;

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
                        }
                        return true;
                    }
                });


    }


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


}
