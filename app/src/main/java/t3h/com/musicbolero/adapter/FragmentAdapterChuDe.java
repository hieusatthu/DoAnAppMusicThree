package t3h.com.musicbolero.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import t3h.com.musicbolero.fragment.fragmentchudemoi.fragmenttablayout.FragmentChuDe;
import t3h.com.musicbolero.fragment.fragmentchudemoi.fragmenttablayout.FragmentTrangChu;
import t3h.com.musicbolero.fragment.fragmentchudemoi.fragmenttablayout.FragmentYeuThich;

/**
 * Created by songsong on 6/20/2018.
 */

public class FragmentAdapterChuDe extends FragmentPagerAdapter {
    public FragmentAdapterChuDe(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new FragmentTrangChu();
            case 1:
                return new FragmentChuDe();
            case 2:
                return new FragmentYeuThich();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    // xác định tiêu đề cho mỗi tab
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position)
        {
            case 0:
                return "Trang chủ";
            case 1:
                return "Chủ đề";
            case 2:
                return "Yêu thích";
        }
        return super.getPageTitle(position);
    }
}
