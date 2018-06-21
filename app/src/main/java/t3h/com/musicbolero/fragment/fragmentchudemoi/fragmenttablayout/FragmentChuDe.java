package t3h.com.musicbolero.fragment.fragmentchudemoi.fragmenttablayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import t3h.com.musicbolero.R;

/**
 * Created by songsong on 6/20/2018.
 */

public class FragmentChuDe extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_chude,container,false);
        return view;
    }
}
