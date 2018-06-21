package t3h.com.musicbolero;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.matthewtamlin.sliding_intro_screen_library.indicators.DotIndicator;

import t3h.com.musicbolero.adapter.LoginSigupAdapter;
import t3h.com.musicbolero.fragment.FragmentLogin;
import t3h.com.musicbolero.fragment.FragmentRegister;

/**
 * Created by songsong on 6/18/2018.
 */

public class LoginActivity extends AppCompatActivity implements IAddFragment{

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private ViewPager viewPager;
    private DotIndicator indicator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login_signup_activity);
        fragmentManager = getSupportFragmentManager();
        viewPager= (ViewPager) findViewById(R.id.content);
        indicator= (DotIndicator) findViewById(R.id.indicator_viewpager);
        LoginSigupAdapter adapter=new LoginSigupAdapter(fragmentManager);
        indicator.setNumberOfItems(2);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
              if(position==0)
              {
                  indicator.setSelectedItem(0,true);


              }
              if(position==1)
              {
                  indicator.setSelectedItem(1,true);

              }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        transaction=fragmentManager.beginTransaction();

    }

    public void addFragmentLogin() {
        viewPager.setCurrentItem(0);
        transaction.addToBackStack(null);
    }

    public void addFragmentRegister()
    {
        viewPager.setCurrentItem(1);
        transaction.addToBackStack(null);
    }


    @Override
    public int addFragment(String tag) {
        if(tag.equals(FragmentRegister.class.getName()))
        {
            addFragmentRegister();

            return 1;
        }
        return 0;
    }


}
