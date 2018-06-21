package t3h.com.musicbolero.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import t3h.com.musicbolero.fragment.fragmentchudemoi.FragmentChuDeMoiDamVinhHung;
import t3h.com.musicbolero.fragment.fragmentchudemoi.FragmentChuDeMoiLeQuyen;
import t3h.com.musicbolero.fragment.fragmentchudemoi.FragmentChuDeMoiNgocSon;
import t3h.com.musicbolero.fragment.fragmentchudemoi.FragmentChuDeMoiQuangLe;

/**
 * Created by songsong on 6/20/2018.
 */

public class AdapterChuDeMoi extends FragmentPagerAdapter {
    public AdapterChuDeMoi(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
      switch (position)
      {
          case 0:
              return new FragmentChuDeMoiDamVinhHung();
          case 1:
              return new FragmentChuDeMoiQuangLe();
          case 2:
              return new FragmentChuDeMoiNgocSon();
          case 3:
              return new FragmentChuDeMoiLeQuyen();

      }
      return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
