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
 * Created by hsu on 2017/1/18.
 */
public class Adapter_Allschule extends RecyclerView.Adapter<Adapter_Allschule.setviewholder> {
    private Context context;
    private MListener mlistener;
    public static interface MListener{
        public void onClick(int postition);
    }
    private List<information> my_data = Collections.emptyList();
    RequestQueue mQueue ;
    public Adapter_Allschule(Context context,List<information> my_data) {
        this.context = context;
        this.my_data = my_data;
        this.mQueue = Volley.newRequestQueue(context);

    }

    @Override
    public setviewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card,parent,false);

        return new setviewholder(cv);

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(setviewholder holder, final int position) {;
        CardView cardView = holder.cardView;
         ImageView all_addapter_image;
         TextView all_addapter_name;
        TextView all_addapter_time;
        all_addapter_time = (TextView) cardView.findViewById(R.id.card_t_time);
        all_addapter_name = (TextView) cardView.findViewById(R.id.card_t_name);
        all_addapter_image = (ImageView) cardView.findViewById(R.id.card_image);
        all_addapter_name.setText(my_data.get(position).name);
        ImageLoader imageLoader = new ImageLoader(mQueue, new ImageLoader.ImageCache() {
            @Override
            public void putBitmap(String url, Bitmap bitmap) {
            }

            @Override
            public Bitmap getBitmap(String url) {
                return null;
            }
        });
        final ImageLoader.ImageListener listener = ImageLoader.getImageListener(all_addapter_image,
                R.drawable.first, R.drawable.first);
        imageLoader.get(my_data.get(position).id, listener);
       // all_addapter_image.setImageBitmap(decodeSampledBitmapFromResource(cardView.getResources(),R.drawable.kaohsiung,200,200));
       all_addapter_time.setText(my_data.get(position).time);
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
            cardView = (CardView)itemView.findViewById(R.id.card_view);

        }
    }
}
