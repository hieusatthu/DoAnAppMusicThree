package t3h.com.musicbolero.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import t3h.com.musicbolero.fragment.FragmentLogin;
import t3h.com.musicbolero.fragment.FragmentRegister;

/**
 * Created by songsong on 6/19/2018.
 */

public class LoginSigupAdapter extends FragmentPagerAdapter {


    public LoginSigupAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        if(position==0)
        {
            return new FragmentLogin();
        }
        return new FragmentRegister();
    }

    @Override
    public int getCount() {
        return 2;
    }
}
