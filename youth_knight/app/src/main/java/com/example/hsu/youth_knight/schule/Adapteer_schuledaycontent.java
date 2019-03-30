package com.example.hsu.youth_knight.schule;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hsu.youth_knight.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by hsu on 2017/1/23.
 */
public class Adapteer_schuledaycontent extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_FOOTER = 1;
    public static final int TYPE_NORMAL = 2;
    public static final int TYPE_MAP = 3;
    Context context;
    List<list_information> data = Collections.emptyList();
    ArrayList<Integer> type;

    int[] numeric = {R.drawable.numeric_1,R.drawable.numeric_2,R.drawable.numeric_3,R.drawable.numeric_4,R.drawable.numeric_5,R.drawable.numeric_6,
            R.drawable.numeric_7,R.drawable.numeric_8,R.drawable.numeric_9};

    public Adapteer_schuledaycontent(Context context, List<list_information> data, ArrayList<Integer> type,int day) {
        this.context = context;
        this.data = data;
        this.type = type;


    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       if(viewType == TYPE_HEADER){
           View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_addcontent,parent,false);
           return new header(v);
       }
        else if(viewType == TYPE_FOOTER)
       {
           View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_addcontent,parent,false);
           return new footer(v);
       }
        else if(viewType == TYPE_NORMAL)
       {
           View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_content,parent,false);
           return new normal(v);
       }

        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof header)
        {
            header header = (Adapteer_schuledaycontent.header)holder;
            header.date.setText(data.get(position).getDay());
        }
        else if(holder instanceof footer)
        {
            header header = (Adapteer_schuledaycontent.header)holder;
        }
        else if(holder instanceof normal)
        {
            normal normal = (Adapteer_schuledaycontent.normal)holder;
            normal.t_start.setText(data.get(position).getTime());
            normal.t_end.setText(data.get(position).getEndtime());
            normal.icon.setImageResource(numeric[data.get(position).getWhichschule()-1]);
            normal.t_name.setText(data.get(position).getAddress());
            normal.t_address.setText(data.get(position).getName());
        }


    }




    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(type.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class header extends RecyclerView.ViewHolder{
    TextView date;
        public header(View itemView) {
            super(itemView);
            date = (TextView)itemView.findViewById(R.id.card_addcontent);
        }
    }

    class footer extends RecyclerView.ViewHolder {
        TextView add;
        public footer(View itemView) {
            super(itemView);
            add = (TextView)itemView.findViewById(R.id.card_addcontent);
        }
    }

    class normal extends RecyclerView.ViewHolder {
        TextView t_start,t_end,t_name,t_address;
        ImageView icon;
        public normal(View itemView) {
            super(itemView);
            t_start = (TextView)itemView.findViewById(R.id.card_schuleconten_content_tstart);
            t_end = (TextView)itemView.findViewById(R.id.card_schuleconten_content_tend);
            icon = (ImageView)itemView.findViewById(R.id.card_schuleconten_content_image);
            t_name  = (TextView)itemView.findViewById(R.id.card_schuleconten_content_t_content);
            t_address = (TextView)itemView.findViewById(R.id.card_schuleconten_content_t_address);
        }
    }

}
