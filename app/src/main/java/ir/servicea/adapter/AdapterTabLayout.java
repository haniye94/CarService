package ir.servicea.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import ir.servicea.FragmentGuid;
import ir.servicea.FragmentMain;
import ir.servicea.Fragmentprofile;
import ir.servicea.activity.WebViewActivity;
import ir.servicea.app.G;

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