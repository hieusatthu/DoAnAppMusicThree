package t3h.com.musicbolero;

import android.media.MediaPlayer;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;

import t3h.com.musicbolero.adapter.FragmentAdapterChuDe;
import t3h.com.musicbolero.fragment.fragment_detail.FragmentDetail;
import t3h.com.musicbolero.fragment.fragmentchudemoi.fragmenttablayout.FragmentTrangChu;
import t3h.com.musicbolero.mediaplayer.MyMediaPlayer;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FragmentAdapterChuDe fragmentAdapterChuDe;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentTransaction transaction;
    private MyMediaPlayer myMediaPlayer = new MyMediaPlayer();
    private FragmentDetail fragmentDetail = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //vi du
        //em them mot doan code o dau
        //sau do em push ( day code len) thi ban hieu lay ve sex thay doan code cua em da code
        //--truoc khi day len phai commit cac file muon day len ( file do co the em tao moi,
        //hoac em sua
        //-- sau do pull( lay code cua ban hien lai neu co moi
        //-- dau do day code len: push
        setContentView(R.layout.activity_main);
        anhXa();
        fragmentAdapterChuDe = new FragmentAdapterChuDe(getSupportFragmentManager());
        viewPager.setAdapter(fragmentAdapterChuDe);
        viewPager.setOffscreenPageLimit(3);
        initTabLayout();
    }

    private void anhXa() {
        tabLayout = (TabLayout) findViewById(R.id.my_tablayout);
        viewPager = (ViewPager) findViewById(R.id.my_viewpager_main);
    }

    private void initTabLayout() {
        tabLayout.setupWithViewPager(viewPager);
    }

    public void addFragmentDetail(int position, String url) {
        transaction = fragmentManager.beginTransaction();
        fragmentDetail = new FragmentDetail();
        transaction.replace(R.id.fr_content, fragmentDetail, FragmentDetail.class.getName()).commit();
        Fragment page = getSupportFragmentManager().findFragmentByTag("android:switcher:" +
                R.id.my_viewpager_main + ":" + viewPager.getCurrentItem());
        transaction.hide(page);
        transaction.addToBackStack(null);
        tabLayout.setVisibility(View.GONE);
        try {
            myMediaPlayer.initMedia(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        myMediaPlayer.playMusic(url);

    }

}
