package t3h.com.musicbolero.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import t3h.com.musicbolero.R;
import t3h.com.musicbolero.objectclass.ChuDe;

/**
 * Created by songsong on 6/20/2018.
 */

public class ChuDeMoiAdapter extends RecyclerView.Adapter<ChuDeMoiAdapter.ViewHolderChuDe> {
    private List<ChuDe> chuDes;
    private Context context;



    public ChuDeMoiAdapter(List<ChuDe> chuDes, Context context) {
        this.chuDes = chuDes;
        this.context = context;
    }

    @Override
    public ViewHolderChuDe onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.custom_item_chude, parent, false);
        ViewHolderChuDe viewHolderChuDe = new ViewHolderChuDe(view);
        return viewHolderChuDe;
    }

    @Override
    public void onBindViewHolder(final ViewHolderChuDe holder, int position) {
        ChuDe chuDe = chuDes.get(position);
        holder.txtTenChuDe.setText(chuDe.getTenChuDe());
        Picasso.with(context).load(chuDe.getHinhChuDeLon()).into(holder.imgHinhChuDeLon, new Callback() {
            @Override
            public void onSuccess() {
                Log.d("aaa","success");
                holder.txtTenChuDe.setVisibility(View.VISIBLE);
                holder.imgHinhChuDeLon.setVisibility(View.VISIBLE);
                holder.imgHinhChuDeNho.setVisibility(View.VISIBLE);
                holder.progressBarCustomItemChuDe.setVisibility(View.GONE);
            }

            @Override
            public void onError() {
                Log.d("aaa","onError");

            }
        });
        Picasso.with(context).load(chuDe.getHinhChuDeNho()).into(holder.imgHinhChuDeNho);
        //Glide.with(context).load(chuDe.getHinhChuDeNho()).into(holder.imgHinhChuDeNho);
        //Glide.with(context).load(chuDe.getHinhChuDeNho()).into(holder.imgHinhChuDeNho);

    }

    @Override
    public int getItemCount() {
        return chuDes.size();
    }

    public class ViewHolderChuDe extends RecyclerView.ViewHolder {
        ImageView imgHinhChuDeLon;
        ImageView imgHinhChuDeNho;
        TextView txtTenChuDe;
       ProgressBar progressBarCustomItemChuDe;

        public ViewHolderChuDe(View itemView) {

            super(itemView);

            // ánh xạ
            imgHinhChuDeLon = itemView.findViewById(R.id.img_background_chude);
            imgHinhChuDeNho = itemView.findViewById(R.id.img_hinhchude_nho);
            txtTenChuDe = itemView.findViewById(R.id.txt_tenchude);
            progressBarCustomItemChuDe=itemView.findViewById(R.id.progrees_custom_item);

        }
    }
}
