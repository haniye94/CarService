package ir.tehranOil.adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import ir.tehranOil.FragmentGuid;
import ir.tehranOil.FragmentMain;
import ir.tehranOil.Fragmentprofile;

public class AdapterTabLayout extends FragmentPagerAdapter {
    Context context;
    int totalTabs;

    public AdapterTabLayout(Context c, FragmentManager fm, int totalTabs) {
        super(fm);
        context = c;
        this.totalTabs = totalTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                FragmentMain main = new FragmentMain();
                return main;

            case 1:
                FragmentGuid guid = new FragmentGuid();
                return guid;

            case 2:
                Fragmentprofile profile = new Fragmentprofile();
                return profile;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}