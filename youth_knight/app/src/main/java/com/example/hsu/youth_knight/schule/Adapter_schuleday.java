package com.example.hsu.youth_knight.schule;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hsu.youth_knight.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by hsu on 2017/1/22.
 */
public class Adapter_schuleday extends RecyclerView.Adapter<Adapter_schuleday.setschuledayviewholder>{
    private Context context;
    private Listener mlistener;



    private List<information> my_data = Collections.emptyList();
    public static interface Listener{
        public void onClick(int postition);
    }
    public Adapter_schuleday(Context context,List<information> my_data) {
        this.context = context;
        this.my_data = my_data;
    }
    public void setListnear(Listener mlistnear) {
        this.mlistener = mlistnear;
    }
    @Override
    public setschuledayviewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_schuleday,parent,false);
        return new setschuledayviewholder(cv);
    }

    @Override
    public void onBindViewHolder(setschuledayviewholder holder, int position) {
        CardView cardView = holder.cardView;
       // TextView t_day = (TextView)cardView.findViewById(R.id.card_schuleday_t_day);
        //TextView t_content = (TextView)cardView.findViewById(R.id.card_schuleday_t_content);
       // t_day.setText(my_data.get(position));

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class setschuledayviewholder extends  RecyclerView.ViewHolder{
        private CardView cardView;
        public setschuledayviewholder(View itemView) {
            super(itemView);
            cardView = (CardView)itemView.findViewById(R.id.card_schuleday);

        }
    }
}
