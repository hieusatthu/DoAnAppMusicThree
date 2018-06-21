package t3h.com.musicbolero.fragment.fragmentchudemoi.fragmenttablayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.eftimoff.viewpagertransformers.TabletTransformer;
import com.matthewtamlin.sliding_intro_screen_library.indicators.DotIndicator;

import java.util.ArrayList;
import java.util.List;

import t3h.com.musicbolero.R;
import t3h.com.musicbolero.adapter.AdapterChuDeMoi;
import t3h.com.musicbolero.adapter.ChuDeAdapter;
import t3h.com.musicbolero.model.GetListChuDe;
import t3h.com.musicbolero.objectclass.ChuDe;

/**
 * Created by songsong on 6/19/2018.
 */

public class FragmentTrangChu extends Fragment implements ViewPager.OnPageChangeListener {
    private DotIndicator indicator;
    private ViewPager viewPager;
    AdapterChuDeMoi adapterChuDeHot;
    private CountDownTimer timer;
    private int position = 0;

    private RecyclerView recyclerViewTrangChu;
    private ChuDeAdapter chuDeAdapter;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_trangchu, container, false);
        anhXa(view);

        adapterChuDeHot = new AdapterChuDeMoi(getActivity().getSupportFragmentManager());
        indicator.setNumberOfItems(4);
        viewPager.setAdapter(adapterChuDeHot);
        initTimer();
        viewPager.setOnPageChangeListener(this);
        viewPager.setPageTransformer(true,new TabletTransformer(),View.LAYER_TYPE_HARDWARE);

        GetListChuDe getListChuDe=new GetListChuDe();

        chuDeAdapter=new ChuDeAdapter(getListChuDe.layListChuDeTheoMa(),getContext());
        Log.d("hinh",getListChuDe.layListChuDeTheoMa().get(0).getHinhChuDeLon());
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getContext());
        recyclerViewTrangChu.setLayoutManager(layoutManager);
        recyclerViewTrangChu.setAdapter(chuDeAdapter);
        chuDeAdapter.notifyDataSetChanged();


        return view;
    }

    private void anhXa(View view) {
        indicator = view.findViewById(R.id.indicator_trang_chu);
        viewPager = view.findViewById(R.id.viewpager_trangchu);
        recyclerViewTrangChu=view.findViewById(R.id.recyclerview_trangchu);

    }

    private void initTimer() {
        timer = new CountDownTimer(4500, 4500) {
            @Override
            public void onTick(long l) {
                position++;
                if (position > 3) {
                    position = 0;
                }

                if (position == 0) {
                    viewPager.setCurrentItem(0);
                    indicator.setSelectedItem(position, true);

                }
                if (position == 1) {
                    viewPager.setCurrentItem(1);
                    indicator.setSelectedItem(position, true);
                }
                if (position == 2) {
                    viewPager.setCurrentItem(2);
                    indicator.setSelectedItem(position, true);
                }
                if (position == 3) {
                    viewPager.setCurrentItem(3);
                    indicator.setSelectedItem(position, true);
                }
            }

            @Override
            public void onFinish() {
                this.start();
            }
        };
        timer.start();
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }



}
