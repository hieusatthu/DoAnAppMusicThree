package t3h.com.musicbolero.fragment.fragmentchudemoi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import t3h.com.musicbolero.R;

/**
 * Created by songsong on 6/20/2018.
 */

public class FragmentChuDeMoiDamVinhHung extends Fragment {
    private ImageView imgDamVinhHung;
    private ProgressBar progressBar;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chudemoi_damvinhhung, container, false);
        anhXa(view);
        return view;
    }

    private void anhXa(View view) {
        imgDamVinhHung=view.findViewById(R.id.img_chudemoi_damvinhhung);
        progressBar=view.findViewById(R.id.progress_chudemoi_damvinhhung);
        Picasso.with(getContext()).
                load("http://192.168.78.101:8080/appmusic/hinhchudemoi/chude_saigonbolerovahung.jpg").into(imgDamVinhHung, new Callback() {
            @Override
            public void onSuccess() {
                imgDamVinhHung.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError() {

            }
        });


    }
}
