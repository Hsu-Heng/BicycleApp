package com.example.hsu.youth_knight.schule;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hsu.youth_knight.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by hsu on 2017/2/6.
 */
public class Adapter_route extends RecyclerView.Adapter<Adapter_route.setviewholder> {
    private Context context;
    private MListener mlistener;
    private List<list_routedata> my_data = Collections.emptyList();
    public Adapter_route(Context context,List<list_routedata> my_data) {
        this.context = context;
        this.my_data = my_data;


    }

    @Override
    public setviewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_route,parent,false);

        return new setviewholder(cv);
    }

    @Override
    public void onBindViewHolder(setviewholder holder, final int position) {
        CardView cardView = holder.cardView;
        TextView textView = (TextView)cardView.findViewById(R.id.card_route_name);
        textView.setText(my_data.get(position).getName());

        TextView textView1 = (TextView)cardView.findViewById(R.id.card_route_km);
        textView1.setText(my_data.get(position).getKm());
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

    public static interface MListener{
        public void onClick(int postition);
    }
    public static class setviewholder extends  RecyclerView.ViewHolder{
        private CardView cardView;
        public setviewholder(View itemView) {
            super(itemView);
            cardView = (CardView)itemView.findViewById(R.id.card_route);

        }
    }
}
