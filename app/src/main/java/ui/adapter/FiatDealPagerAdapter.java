package ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class FiatDealPagerAdapter extends FragmentPagerAdapter {
    private final ArrayList tabIndicatorsList;
    private final ArrayList tabFragments;

    public FiatDealPagerAdapter(FragmentManager fm, ArrayList tabIndicatorsList,ArrayList tabFragments) {
        super(fm);
        this.tabIndicatorsList=tabIndicatorsList;
        this.tabFragments=tabFragments;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return (CharSequence) tabIndicatorsList.get(position);
    }

    @Override
    public Fragment getItem(int i) {
        return (Fragment) tabFragments.get(i);
    }

    @Override
    public int getCount() {
        return tabFragments.size();
    }
}
