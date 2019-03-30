package com.example.hsu.youth_knight.schule;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by hsu on 2017/1/27.
 */
public class myViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;
    private View mConverView;
    private Context mContext;

    public myViewHolder(Context context,View itemView) {
        super(itemView);
        mContext = context;
        mConverView = itemView;
        mViews = new SparseArray<View>();
    }
    public static myViewHolder creatViewHolder(Context context,View itemView)
    {
        myViewHolder holder = new myViewHolder(context,itemView);
        return holder;
    }
    public static myViewHolder creatViewHolder(Context context, ViewGroup parent,int layoutId){
        View itemview = LayoutInflater.from(context).inflate(layoutId,parent,false);
        myViewHolder holder = new myViewHolder(context,itemview);
        return holder;
    }
    public <T extends View> T getView(int viewId)
    {
        View view = mViews.get(viewId);
        if(view!=null)
        {
            view = mConverView.findViewById(viewId);
            mViews.put(viewId,view);
        }
        return (T) view;
    }
    public View getConverView()
    {
        return mConverView;
    }
    public myViewHolder setText(int viewId, String text)
    {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }
    public myViewHolder setOnclickListner(int viewId,View.OnClickListener listener)
    {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

}
