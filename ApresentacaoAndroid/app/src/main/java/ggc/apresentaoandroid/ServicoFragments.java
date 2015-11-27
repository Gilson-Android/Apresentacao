package ggc.apresentaoandroid;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gilson on 21/11/2015.
 */
public class ServicoFragments extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_servicos, container, false);
    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Inflate the layout for this fragment
        View view = getView();

        if(view != null) {
            viewPager = (ViewPager)  view.findViewById(R.id.viewpager);
            //viewPager = setupViewPager(viewPager);
            setupViewPager(viewPager);

            tabLayout = (TabLayout) view.findViewById(R.id.tabs);
            tabLayout.setupWithViewPager(viewPager);

            viewPager.clearOnPageChangeListeners();
            viewPager.addOnPageChangeListener(new WorkaroundTabLayoutOnPageChangeListener(tabLayout));
        }

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                switch (tab.getPosition()) {
                    case 0:
                        //showToast("One");
                        break;
                    case 1:
                        //showToast("Two");
                        break;
                    case 2:
                        //showToast("Three");
                        break;
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFrag(new AplicativoFragment(), getString(R.string.sub_aplicativo));
        adapter.addFrag(new FabricaFragment(), getString(R.string.sub_fabrica));
        adapter.addFrag(new SitesFragment(),getString(R.string.sub_sites));
        viewPager.setAdapter(adapter);
    }

  /*  private ViewPager setupViewPager(ViewPager viewPager) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.getFragments().removeAll(fragmentManager.getFragments());
        ViewPagerAdapter adapter = new ViewPagerAdapter(fragmentManager);
        adapter.addFragment(new AplicativoFragment(), getString(R.string.sub_aplicativo));
        adapter.addFragment(new FabricaFragment(), getString(R.string.sub_fabrica) );
        adapter.addFragment(new SitesFragment(), getString(R.string.sub_sites));
        viewPager.setAdapter(adapter);
        return  viewPager;
    }*/


    public class WorkaroundTabLayoutOnPageChangeListener extends TabLayout.TabLayoutOnPageChangeListener {
        private final WeakReference<TabLayout> mTabLayoutRef;

        public WorkaroundTabLayoutOnPageChangeListener(TabLayout tabLayout) {
            super(tabLayout);
            this.mTabLayoutRef = new WeakReference<>(tabLayout);
        }

        @Override
        public void onPageSelected(int position) {
            super.onPageSelected(position);
            final TabLayout tabLayout = mTabLayoutRef.get();
            if (tabLayout != null) {
                final TabLayout.Tab tab = tabLayout.getTabAt(position);
                if (tab != null) {
                    tab.select();
                }
            }
        }
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();
        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }
        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }
        @Override
        public int getCount() {
            return mFragmentList.size();
        }
        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


    /*class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }*/
}


