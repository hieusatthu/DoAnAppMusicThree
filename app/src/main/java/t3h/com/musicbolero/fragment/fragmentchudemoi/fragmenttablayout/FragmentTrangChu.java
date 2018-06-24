package t3h.com.musicbolero.fragment.fragmentchudemoi.fragmenttablayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.eftimoff.viewpagertransformers.TabletTransformer;
import com.matthewtamlin.sliding_intro_screen_library.indicators.DotIndicator;

import java.util.ArrayList;
import java.util.List;

import t3h.com.musicbolero.MainActivity;
import t3h.com.musicbolero.R;
import t3h.com.musicbolero.adapter.AdapterBaiHat_TrangChu;
import t3h.com.musicbolero.adapter.AdapterChuDeMoi;
import t3h.com.musicbolero.adapter.ChuDeMoiAdapter;
import t3h.com.musicbolero.adapter.main.MainPageAdapter;
import t3h.com.musicbolero.model.GetListChuDe;
import t3h.com.musicbolero.model.LayDanhSachBaiHatNgauNhien;
import t3h.com.musicbolero.objectclass.BaiHat;
import t3h.com.musicbolero.objectclass.ChuDe;

/**
 * Created by songsong on 6/19/2018.
 */

public class FragmentTrangChu extends Fragment implements   MainPageAdapter.IMainPageAdapter {
    //    private RecyclerView recyclerViewTrangChu;
//    private ChuDeMoiAdapter chuDeMoiAdapter;
//    private AdapterBaiHat_TrangChu adapterBaiHat_trangChu;
//    private RecyclerView recyclerViewBaiHat;
    private List<BaiHat> baiHatList;
    private List<ChuDe> chuDes;
    private List<Object> items;
    private RecyclerView rcMain;
    private MainPageAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_trangchu, container, false);
        items = new ArrayList<>();
        rcMain = (RecyclerView) view.findViewById(R.id.rc_main);
        adapter = new MainPageAdapter(this);
        LayDanhSachBaiHatNgauNhien layDanhSachBaiHatNgauNhien = new LayDanhSachBaiHatNgauNhien();
        baiHatList = layDanhSachBaiHatNgauNhien.layDanhSachBaiHatNgauNhien();
        GetListChuDe getListChuDe = new GetListChuDe();
        chuDes = getListChuDe.layListChuDeTheoMa();
        reloadItems();
        GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if ( position == 0 ) {
                    return 2;
                }
                if ( items.get(position) instanceof ChuDe){
                    return 2;
                }
                return 1;
            }
        });
        rcMain.setLayoutManager(manager);
        rcMain.setAdapter(adapter);
//        adapterChuDeHot = new AdapterChuDeMoi(getActivity().getSupportFragmentManager());
//        indicator.setNumberOfItems(4);
//        viewPager.setAdapter(adapterChuDeHot);
//        initTimer();
//        viewPager.setOnPageChangeListener(this);
//        viewPager.setPageTransformer(true, new TabletTransformer(), View.LAYER_TYPE_HARDWARE);
//
//        GetListChuDe getListChuDe = new GetListChuDe();
//
//        chuDeMoiAdapter = new ChuDeMoiAdapter(getListChuDe.layListChuDeTheoMa(), getContext());
//        Log.d("hinh", getListChuDe.layListChuDeTheoMa().get(0).getHinhChuDeLon());
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
//        recyclerViewTrangChu.setLayoutManager(layoutManager);
//        recyclerViewTrangChu.setAdapter(chuDeMoiAdapter);
//        chuDeMoiAdapter.notifyDataSetChanged();
//
//        //
//        LayDanhSachBaiHatNgauNhien layDanhSachBaiHatNgauNhien = new LayDanhSachBaiHatNgauNhien();
//        baiHatList = layDanhSachBaiHatNgauNhien.layDanhSachBaiHatNgauNhien();
//        adapterBaiHat_trangChu = new AdapterBaiHat_TrangChu(baiHatList, getContext(), this);
//        adapterBaiHat_trangChu.notifyDataSetChanged();
//        //
//
//        RecyclerView.LayoutManager manager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, true);
//        recyclerViewBaiHat.setLayoutManager(manager);
//
//        recyclerViewBaiHat.setAdapter(adapterBaiHat_trangChu);
//        //
//        //


        return view;
    }

    private void reloadItems(){
        items.clear();
        items.add(null);
        if ( chuDes != null && chuDes.size() > 0 ){
            items.addAll(chuDes);
        }

        if ( baiHatList != null && baiHatList.size() > 0 ){
            items.addAll(baiHatList);
        }
    }

    @Override
    public Object getData(int position) {
       return items.get(position);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public FragmentManager getManager() {
        return getChildFragmentManager();
    }

//    private void anhXa(View view) {
//        indicator = view.findViewById(R.id.indicator_trang_chu);
//        viewPager = view.findViewById(R.id.viewpager_trangchu);
//        recyclerViewTrangChu = view.findViewById(R.id.recyclerview_trangchu);
//        recyclerViewBaiHat = view.findViewById(R.id.recyclerview_baihat);
//        recyclerViewBaiHat.setOnScrollChangeListener(this);
//
//    }
//
//    private void initTimer() {
//        timer = new CountDownTimer(4500, 4500) {
//            @Override
//            public void onTick(long l) {
//                position++;
//                if (position > 3) {
//                    position = 0;
//                }
//
//                if (position == 0) {
//                    viewPager.setCurrentItem(0);
//                    indicator.setSelectedItem(position, true);
//
//                }
//                if (position == 1) {
//                    viewPager.setCurrentItem(1);
//                    indicator.setSelectedItem(position, true);
//                }
//                if (position == 2) {
//                    viewPager.setCurrentItem(2);
//                    indicator.setSelectedItem(position, true);
//                }
//                if (position == 3) {
//                    viewPager.setCurrentItem(3);
//                    indicator.setSelectedItem(position, true);
//                }
//            }
//
//            @Override
//            public void onFinish() {
//                this.start();
//            }
//        };
//        timer.start();
//    }
//
//
//    @Override
//    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//    }
//
//    @Override
//    public void onPageSelected(int position) {
//
//    }
//
//    @Override
//    public void onPageScrollStateChanged(int state) {
//
//    }


    @Override
    public void openMusic(int position, String url) {
        // gọi sang phương thức của activity
        ((MainActivity) getActivity()).addFragmentDetail(position, url);
    }

//    @Override
//    public void onScrollChange(View view, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//    }
}
