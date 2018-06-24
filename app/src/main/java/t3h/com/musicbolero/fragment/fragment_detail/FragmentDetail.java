package t3h.com.musicbolero.fragment.fragment_detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import t3h.com.musicbolero.R;

/**
 * Created by songsong on 6/24/2018.
 */

public class FragmentDetail extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_detail,container,false);
        return view;
    }
}
