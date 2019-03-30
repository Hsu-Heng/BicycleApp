package com.example.hsu.youth_knight.schule;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hsu.youth_knight.R;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

/**
 * A simple {@link Fragment} subclass.
 */
public class atacontent extends Fragment {
    private SectionedRecyclerViewAdapter sectionAdapter;
    ArrayList<list_section> mydata = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_atacontent, container, false);

        sectionAdapter = new SectionedRecyclerViewAdapter();
        list_section one = new list_section();
        one.setName("cool");
        one.setAddress("opop");
        one.setLng(new LatLng(53.22, 98.56));
        mydata.add(one);
        mydata.add(one);
        mydata.add(one);

        sectionAdapter.addSection(new mysection("kdd", mydata));
        sectionAdapter.addSection(new mysection("k5d", mydata));
        sectionAdapter.addSection(new mysection("9dd", mydata));
//        sectionAdapter.addSection(new NewSection(NewSection.BUSINESS));
//        sectionAdapter.addSection(new NewSection(NewSection.TECHNOLOGY));
//        sectionAdapter.addSection(new NewSection(NewSection.SPORTS));

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.ataercycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(sectionAdapter);

        return view;
    }





    class NewSection extends StatelessSection{
        final static int WORLD = 0;
        final static int BUSINESS = 1;
        final static int TECHNOLOGY = 2;
        final static int SPORTS = 3;
        ArrayList<String> mylist = new ArrayList<>();

        final int topic;

        String title;
        List<String> list;

        public List<String> getList() {
            mylist.add("hey");
            mylist.add("how");
            mylist.add("joj");
            return mylist;
        }

        public void setList(List<String> list) {
            this.list = list;
        }

        int imgPlaceholderResId;
        public NewSection(int topic) {
            super(R.layout.section_header,R.layout.section_footer,R.layout.secion_item);
            this.topic = topic;
            switch (topic)
            {
                case WORLD:
                    this.title = "hey";
                    this.list = getNews(R.array.news_world);
                    this.imgPlaceholderResId = R.drawable.kddlogo;
                    break;
                case BUSINESS:
                    this.title = "hey";
                    this.list = getNews(R.array.news_world);;
                    this.imgPlaceholderResId = R.drawable.kddlogo;
                    break;
                case TECHNOLOGY:
                    this.title = "hey";
                    this.list = getNews(R.array.news_world);;
                    this.imgPlaceholderResId = R.drawable.kddlogo;
                    break;
                case SPORTS:
                    this.title = "hey";
                    this.list = getNews(R.array.news_world);;
                    this.imgPlaceholderResId = R.drawable.kddlogo;
                    break;


            }
        }

        private List<String> getNews(int arrayResource) {
            return new ArrayList<>(Arrays.asList(getResources().getStringArray(arrayResource)));
        }

        @Override
        public int getContentItemsTotal() {
          return list.size();
        }

        @Override
        public RecyclerView.ViewHolder getItemViewHolder(View view) {
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
            final ItemViewHolder itemHolder = (ItemViewHolder) holder;

            String[] item = list.get(position).split("\\|");

            itemHolder.tvHeader.setText(item[0]);
            itemHolder.tvDate.setText(item[1]);
            itemHolder.imgItem.setImageResource(imgPlaceholderResId);

            itemHolder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), String.format("Clicked on position #%s of Section %s", sectionAdapter.getSectionPosition(itemHolder.getAdapterPosition()), title), Toast.LENGTH_SHORT).show();
                }
            });

        }
        @Override
        public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
            return new HeaderViewHolder(view);
        }

        @Override
        public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
            HeaderViewHolder headerHolder = (HeaderViewHolder) holder;

            headerHolder.tvTitle.setText(title);
        }

        @Override
        public RecyclerView.ViewHolder getFooterViewHolder(View view) {
            return new FooterViewHolder(view);
        }

        @Override
        public void onBindFooterViewHolder(RecyclerView.ViewHolder holder) {
            FooterViewHolder footerHolder = (FooterViewHolder) holder;

            footerHolder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), String.format("Clicked on footer of Section %s", title), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvTitle;

        public HeaderViewHolder(View view) {
            super(view);

            tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        }
    }

    class FooterViewHolder extends RecyclerView.ViewHolder {

        private final View rootView;

        public FooterViewHolder(View view) {
            super(view);

            rootView = view;
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private final View rootView;
        private final ImageView imgItem;
        private final TextView tvHeader;
        private final TextView tvDate;

        public ItemViewHolder(View view) {
            super(view);

            rootView = view;
            imgItem = (ImageView) view.findViewById(R.id.imgItem);
            tvHeader = (TextView) view.findViewById(R.id.tvHeader);
            tvDate = (TextView) view.findViewById(R.id.tvDate);
        }
    }
    private  class mysection extends StatelessSection{
        String title;
        List<list_section> list;
        int imgPlaceholderResId;
        public mysection(String head,ArrayList<list_section> data){
            super(R.layout.section_header,R.layout.section_footer,R.layout.secion_item);
            this.title = head;
            this.list = data;
            this.imgPlaceholderResId = R.drawable.kddlogo;

        }

        @Override
        public int getContentItemsTotal() {
            return list.size();
        }

        @Override
        public RecyclerView.ViewHolder getItemViewHolder(View view) {
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
            final ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            itemViewHolder.tvHeader.setText(list.get(position).getName());
            itemViewHolder.tvDate.setText(list.get(position).getAddress());
            itemViewHolder.imgItem.setImageResource(imgPlaceholderResId);
            itemViewHolder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), String.format("Clicked on position #%s of Section %s", sectionAdapter.getSectionPosition(itemViewHolder.getAdapterPosition()), title), Toast.LENGTH_SHORT).show();
                }
            });
        }
        @Override
        public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
            return new HeaderViewHolder(view);
        }

        @Override
        public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
            HeaderViewHolder headerHolder = (HeaderViewHolder) holder;

            headerHolder.tvTitle.setText(title);
        }

        @Override
        public RecyclerView.ViewHolder getFooterViewHolder(View view) {
            return new FooterViewHolder(view);
        }

        @Override
        public void onBindFooterViewHolder(RecyclerView.ViewHolder holder) {
            FooterViewHolder footerHolder = (FooterViewHolder) holder;

            footerHolder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), String.format("Clicked on footer of Section %s", title), Toast.LENGTH_SHORT).show();
                }
            });
        }

        }



}


