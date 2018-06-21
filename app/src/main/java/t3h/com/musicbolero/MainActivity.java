package t3h.com.musicbolero;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import t3h.com.musicbolero.adapter.FragmentAdapterChuDe;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FragmentAdapterChuDe fragmentAdapterChuDe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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


}
