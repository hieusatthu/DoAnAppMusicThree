package t3h.com.musicbolero.adapter.main;

import android.os.CountDownTimer;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import t3h.com.musicbolero.adapter.AdapterChuDeMoi;
import t3h.com.musicbolero.databinding.CustomItemChudeBinding;
import t3h.com.musicbolero.databinding.CustomItemSongBinding;
import t3h.com.musicbolero.databinding.LayoutPageMainBinding;
import t3h.com.musicbolero.interface_class.IItemClick;
import t3h.com.musicbolero.objectclass.BaiHat;
import t3h.com.musicbolero.objectclass.ChuDe;

/**
 * Created by songsong on 6/24/2018.
 */

public class MainPageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private IMainPageAdapter inter;

    public MainPageAdapter(IMainPageAdapter inter) {
        this.inter = inter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if ( viewType == 0) {
            LayoutPageMainBinding layoutPageMainBinding = LayoutPageMainBinding.inflate(inflater, parent, false);
            return new ViewHolderPage(layoutPageMainBinding, inter);
        }
        if ( viewType == 1) {
            CustomItemChudeBinding customItemChudeBinding = CustomItemChudeBinding.inflate(inflater, parent, false);
            return new ViewHolderTopic(customItemChudeBinding);
        }

        CustomItemSongBinding customItemSongBinding = CustomItemSongBinding.inflate(inflater, parent, false);
        return new ViewHolderSong(customItemSongBinding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if ( holder instanceof  ViewHolderPage) {
            //page
            updatePage((ViewHolderPage)holder, position);
            return;
        }
        if ( holder instanceof ViewHolderTopic){
            updateTopic((ViewHolderTopic)holder, position);
            return;
        }
        updateSong((ViewHolderSong)holder, position);
    }

    private void updatePage(ViewHolderPage viewHolderPage, int position){

    }

    private void updateTopic(final ViewHolderTopic viewHolderTopic, int position){
        ChuDe chuDe = (ChuDe)inter.getData(position);
        viewHolderTopic.binding.txtTenchude.setText(chuDe.getTenChuDe());

        Picasso.with(viewHolderTopic.itemView.getContext())
                .load(chuDe.getHinhChuDeLon()).into( viewHolderTopic.binding.imgBackgroundChude, new Callback() {
            @Override
            public void onSuccess() {
                Log.d("aaa","success");
                viewHolderTopic.binding.txtTenchude.setVisibility(View.VISIBLE);
                viewHolderTopic.binding.imgBackgroundChude.setVisibility(View.VISIBLE);
                viewHolderTopic.binding.imgHinhchudeNho.setVisibility(View.VISIBLE);
                viewHolderTopic.binding.progreesCustomItem.setVisibility(View.GONE);
            }

            @Override
            public void onError() {
                Log.d("aaa","onError");

            }
        });
        Picasso.with(viewHolderTopic.itemView.getContext())
                .load(chuDe.getHinhChuDeNho()).into(viewHolderTopic.binding.imgHinhchudeNho);
    }

    private void updateSong(final ViewHolderSong holder, int position){
        final BaiHat baiHat=(BaiHat)inter.getData(position);
        holder.binding.txtTenbaihat.setText(baiHat.getTenBaiHat());
        holder.binding.txtCasy.setText(baiHat.getTenCaSy());
        Glide.with(holder.itemView.getContext()).load(baiHat.getHinhBaiHat()).into( holder.binding.imgHinhbaihat);
        holder.binding.rootSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(holder.itemView.getContext(),
                        baiHat.getTenBaiHat(), Toast.LENGTH_SHORT).show();
                inter.openMusic(holder.getAdapterPosition(),
                        baiHat.getLinkBaiHat());
            }
        });

    }

    @Override
    public int getItemViewType(int position) {
        if ( position == 0 ) {
            return 0;
        }
        if (inter.getData(position) instanceof ChuDe){
            return 1;
        }
        return 2;
    }

    @Override
    public int getItemCount() {
        return inter.getCount();
    }

    static final class ViewHolderPage extends RecyclerView.ViewHolder{
        private LayoutPageMainBinding binding;
        private AdapterChuDeMoi adapterChuDeMoi;
        private CountDownTimer timer;
        private int position = 0;
        public ViewHolderPage(LayoutPageMainBinding binding, IMainPageAdapter inter) {
            super(binding.getRoot());
            this.binding = binding;
            adapterChuDeMoi = new AdapterChuDeMoi(inter.getManager());
            binding.viewpagerTrangchu.setAdapter(adapterChuDeMoi);
            binding.indicatorTrangChu.setNumberOfItems(4);
            initTimer();
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
                        binding.viewpagerTrangchu.setCurrentItem(0);
                        binding.indicatorTrangChu.setSelectedItem(position, true);

                    }
                    if (position == 1) {
                        binding.viewpagerTrangchu.setCurrentItem(1);
                        binding.indicatorTrangChu.setSelectedItem(position, true);
                    }
                    if (position == 2) {
                        binding.viewpagerTrangchu.setCurrentItem(2);
                        binding.indicatorTrangChu.setSelectedItem(position, true);
                    }
                    if (position == 3) {
                        binding.viewpagerTrangchu.setCurrentItem(3);
                        binding.indicatorTrangChu.setSelectedItem(position, true);
                    }
                }

                @Override
                public void onFinish() {
                    this.start();
                }
            };
            timer.start();
        }
    }

    static final class ViewHolderTopic extends RecyclerView.ViewHolder{
        private CustomItemChudeBinding binding;
        public ViewHolderTopic(CustomItemChudeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    static final class ViewHolderSong extends RecyclerView.ViewHolder{
        private CustomItemSongBinding binding;
        public ViewHolderSong(CustomItemSongBinding binding) {
            super(binding.getRoot());
            this.binding= binding;
        }
    }

    public interface IMainPageAdapter{
        Object getData(int position);
        int getCount();
        FragmentManager getManager();
        void openMusic(int position,String url);
    }
}
