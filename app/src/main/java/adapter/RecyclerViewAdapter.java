package adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cai.R;

import Listener.OnItemClickListener;

/**
 * Created by ASUS on 2017/6/13.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{
    private OnItemClickListener listener=null;
    private Bitmap []bitmaps;
    private String []ImgDisc;
    private LayoutInflater inflater;
    public RecyclerViewAdapter(Context context, Bitmap[] bitmaps,String []ImgDisc) {
        inflater=LayoutInflater.from(context);
        this.bitmaps=bitmaps;
        this.ImgDisc=ImgDisc;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView=inflater.inflate(R.layout.cardview,null);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.imageview.setImageBitmap(bitmaps[position]);
        holder.textview.setText(ImgDisc[position]);
    }

    @Override
    public int getItemCount() {
        return bitmaps.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textview;
        private ImageView imageview;
        public MyViewHolder(final View itemView) {
            super(itemView);
            textview= (TextView) itemView.findViewById(R.id.card);
            imageview= (ImageView) itemView.findViewById(R.id.img);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (listener!=null){
                listener.onClick(v,getAdapterPosition());
            }
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener){

        this.listener=listener;

    }
}
