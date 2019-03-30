package com.example.hsu.youth_knight.schule;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.hsu.youth_knight.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by hsu on 2017/2/4.
 */
public class Adapter_hotel extends RecyclerView.Adapter<Adapter_hotel.setviewholder>{
    private Context context;
    private MListener mlistener;
    public static interface MListener{
        public void onClick(int postition);
    }
    private List<list_hotel> my_data = Collections.emptyList();
    RequestQueue mQueue ;
    public Adapter_hotel(Context context,List<list_hotel> my_data) {
        this.context = context;
        this.my_data = my_data;
        this.mQueue = Volley.newRequestQueue(context);

    }
    @Override
    public setviewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_data,parent,false);

        return new setviewholder(cv);

    }

    @Override
    public void onBindViewHolder(setviewholder holder, final int position) {
        CardView cardView = holder.cardView;
        ImageView imageView = (ImageView)cardView.findViewById(R.id.card_data_image);
        TextView textView = (TextView)cardView.findViewById(R.id.card_data_name);
        textView.setText(my_data.get(position).getName());
        ImageLoader imageLoader = new ImageLoader(mQueue, new ImageLoader.ImageCache() {
            @Override
            public void putBitmap(String url, Bitmap bitmap) {
            }

            @Override
            public Bitmap getBitmap(String url) {
                return null;
            }
        });
        final ImageLoader.ImageListener listener = ImageLoader.getImageListener(imageView,
                R.drawable.first, R.drawable.first);
        imageLoader.get(my_data.get(position).getImageurl(), listener);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mlistener!=null)
                {
                    mlistener.onClick(position);
                    Log.d("click",""+position);
                }
            }
        });
    }
    public void setListnear(MListener mlistnear) {
        this.mlistener = mlistnear;
    }

    @Override
    public int getItemCount() {
        return my_data.size();
    }

    public static class setviewholder extends  RecyclerView.ViewHolder{
        private CardView cardView;
        public setviewholder(View itemView) {
            super(itemView);
            cardView = (CardView)itemView.findViewById(R.id.card_data);

        }
    }
}

