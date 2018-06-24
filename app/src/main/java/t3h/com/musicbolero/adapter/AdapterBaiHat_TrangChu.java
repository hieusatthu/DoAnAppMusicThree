package t3h.com.musicbolero.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import t3h.com.musicbolero.R;
import t3h.com.musicbolero.interface_class.IItemClick;
import t3h.com.musicbolero.objectclass.BaiHat;

/**
 * Created by songsong on 6/22/2018.
 */

public class AdapterBaiHat_TrangChu extends RecyclerView.Adapter<AdapterBaiHat_TrangChu.ViewHolderBaiHat_TrangChu>{
    private List<BaiHat> baiHatList;
    private Context context;
    private  callMethodInFragment callMethodInFragment;

    public AdapterBaiHat_TrangChu(List<BaiHat> baiHatList, Context context,callMethodInFragment callMethodInFragment) {
        this.baiHatList = baiHatList;
        this.context = context;
        this.callMethodInFragment=callMethodInFragment;

    }


    @Override
    public ViewHolderBaiHat_TrangChu onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.custom_item_song,parent,false);
        ViewHolderBaiHat_TrangChu viewHolderBaiHat_trangChu=new ViewHolderBaiHat_TrangChu(view);
        return viewHolderBaiHat_trangChu;
    }

    @Override
    public void onBindViewHolder(ViewHolderBaiHat_TrangChu holder, int position) {
        // đổ dữ liệu
        final BaiHat baiHat=baiHatList.get(position);
        holder.txtTenBaiHat.setText(baiHat.getTenBaiHat());
        holder.txtTenCaSy.setText(baiHat.getTenCaSy());
        Glide.with(context).load(baiHat.getHinhBaiHat()).into(holder.imgHinhBaiHat);
        holder.itemSetOnClick(new IItemClick() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(context, baiHatList.get(position).getTenBaiHat(), Toast.LENGTH_SHORT).show();
                callMethodInFragment.openMusic(position,baiHatList.get(position).getLinkBaiHat());
            }
        });
    }
    @Override
    public int getItemCount() {
        return baiHatList.size();
    }

    public class ViewHolderBaiHat_TrangChu extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgIconPlay;
        ImageView imgHinhBaiHat;
        TextView txtTenBaiHat;
        TextView txtTenCaSy;
        private IItemClick itemClick;
        public ViewHolderBaiHat_TrangChu(View itemView) {
            super(itemView);
            imgIconPlay=itemView.findViewById(R.id.img_ic_plays);
            imgHinhBaiHat=itemView.findViewById(R.id.img_hinhbaihat);
            txtTenBaiHat=itemView.findViewById(R.id.txt_tenbaihat);
            txtTenCaSy=itemView.findViewById(R.id.txt_casy);
            itemView.setOnClickListener(this);
        }
        public void itemSetOnClick(IItemClick iItemClick)
        {
            this.itemClick=iItemClick;
        }
        
        @Override
        public void onClick(View view) {
            itemClick.onClick(view,getAdapterPosition());
        }
    }

    public interface callMethodInFragment
    {
        void openMusic(int position,String url);
    }

}
