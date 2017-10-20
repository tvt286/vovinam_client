package vn.tranty.vovinam_client.fragments.levels;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import vn.tranty.vovinam_client.R;
import vn.tranty.vovinam_client.adapters.PagerAdapter;
import vn.tranty.vovinam_client.fragments.coban.CoBanFragment;

import static vn.tranty.vovinam_client.mics.Contanst.TAB_MENU.TAB_COBAN;
import static vn.tranty.vovinam_client.mics.Contanst.TAB_MENU.TAB_DOIKHANG;
import static vn.tranty.vovinam_client.mics.Contanst.TAB_MENU.TAB_DOIKHANG_NAM;
import static vn.tranty.vovinam_client.mics.Contanst.TAB_MENU.TAB_DOIKHANG_NU;
import static vn.tranty.vovinam_client.mics.Contanst.TAB_MENU.TAB_KETQUA;
import static vn.tranty.vovinam_client.mics.Contanst.TAB_MENU.TAB_QUYEN;
import static vn.tranty.vovinam_client.mics.Contanst.TAB_MENU.TAB_THELUC;
import static vn.tranty.vovinam_client.mics.Contanst.TAB_MENU.TAB_VODAO;

/**
 * Created by TRUC-SIDA on 10/20/2017.
 */

public class DoiKhangFragment extends Fragment {
    public  static final int POS_NAM = 0;
    public  static final int POS_NU = 1;


    @Bind(R.id.view_pager)
    ViewPager pager;
    @Bind(R.id.tab_layout)
    TabLayout tabLayout;

    private PagerAdapter adapter;

    public DoiKhangFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lamdai,container,false);
        ButterKnife.bind(this,view);

        addTab();

        //Add fragments
        addFragment();

        //Setting adapter
        settingAdapter();

        return view;
    }

    private void settingAdapter() {
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        // onclick tab
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
                switch (tab.getPosition())
                {
                    case POS_NAM:
                        break;
                    case POS_NU:
                        break;

                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                switch (tab.getPosition())
                {
                    case POS_NAM:
                        break;
                    case POS_NU:
                        break;

                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void addFragment() {
        adapter = new PagerAdapter(getFragmentManager());
        adapter.addFragment(new CoBanFragment());
        adapter.addFragment(new CoBanFragment());
    }

    private void addTab() {
        tabLayout.addTab(tabLayout.newTab().setText(TAB_DOIKHANG_NAM));
        tabLayout.addTab(tabLayout.newTab().setText(TAB_DOIKHANG_NU));


    }
}