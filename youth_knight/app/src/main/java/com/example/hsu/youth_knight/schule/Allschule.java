package com.example.hsu.youth_knight.schule;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.hsu.youth_knight.R;
import com.example.hsu.youth_knight.database.DBhelper_schule;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hsu on 2017/1/18.
 */
public class Allschule extends Fragment {
    DBhelper_schule dBhelper;
    private Adapter_Allschule adapter;
    private String mArgument;
    public static final String ARGUMENT = "argument";
    Cursor cursor;

    private List<information> data_list;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_allschule,container,false);
        data_list  = new ArrayList<>();
        adapter = new Adapter_Allschule(getContext(),sqlitedata());
        Log.d("string",String.valueOf(adapter.getItemCount()));
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter.setListnear(new Adapter_Allschule.MListener() {
            @Override
            public void onClick(int postition) {
                schuleday fragment1 = new schuleday();
                FragmentTransaction fragmentTransaction;
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                Bundle bundle = new Bundle();
                CustomObject obj = new CustomObject();
                obj.setName(sqlitedata().get(postition).name);
                obj.setDate(sqlitedata().get(postition).date);
                obj.setDateend(sqlitedata().get(postition).dateend);
                obj.setDay(sqlitedata().get(postition).day);
                obj.setImageurl(sqlitedata().get(postition).id);
                obj.setContentjson(sqlitedata().get(postition).jsoncontent);
                obj.setSqliteid(sqlitedata().get(postition).sqliteId);
                bundle.putSerializable("item",obj);

                Log.d("obj",obj.name);
                //bundle.putString("item",sqlitedata().get(postition).sqliteId);

                fragment1.setArguments(bundle);
                fragmentTransaction.replace(R.id.fragmentcontainer, fragment1);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();


                Log.d("get",""+postition+sqlitedata().get(postition).sqliteId);
            }
        });
        getActivity().setTitle("我的行程");
        setHasOptionsMenu(true);
        return recyclerView;
    }

    public static class CustomObject implements Serializable {
        private static final long serialVersionUID = -7060210544600464481L;
        public String name,date,dateend,imageurl,contentjson;
        long sqliteid;
        public int day;

        public long getSqliteid() {
            return sqliteid;
        }

        public void setSqliteid(long sqliteid) {
            this.sqliteid = sqliteid;
        }

        public String getDateend() {

            return dateend;
        }

        public String getContentjson() {
            return contentjson;
        }

        public void setContentjson(String contentjson) {
            this.contentjson = contentjson;
        }

        public String getImageurl() {
            return imageurl;
        }

        public void setImageurl(String imageurl) {
            this.imageurl = imageurl;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public void setDateend(String dateend) {
            this.dateend = dateend;
        }

        public String getDate() {
            return date;

        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getName() {

            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.allschule_addschule) {
            FragmentTransaction fragmentTransaction;
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragmentcontainer, new newschule());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_fragment_allschule, menu);
        super.onCreateOptionsMenu(menu,inflater);

    }
    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("我的行程");
    }
    public List<information> sqlitedata(){
        dBhelper = new DBhelper_schule(getContext());
        List<information> data = new ArrayList<>();
        SQLiteDatabase db = dBhelper.getWritableDatabase();;
        cursor = dBhelper.querydata(db," SELECT * FROM schule ");

        if(cursor!=null)
        {
            if(cursor.moveToFirst())
            {
                do {
                    information mdata = new information();
                    mdata.sqliteId = cursor.getLong(0);
                    mdata.name = cursor.getString(1);
                    mdata.date = cursor.getString(2);
                    mdata.dateend = cursor.getString(3);
                    mdata.id = cursor.getString(5);
                    mdata.day = cursor.getInt(4);
                    mdata.jsoncontent = cursor.getString(6);
                    mdata.time = cursor.getString(2)+"-"+cursor.getString(3);
                    data.add(mdata);


                }while(cursor.moveToNext());
            }
        }
        Log.d("data",data.get(0).name);
        db.close();
        cursor.close();
        return data;
    }
//    public static List<information> gerData(){
//        List<information> data = new ArrayList<>();
//        String[] title = {"Wade","Ryhen","James","Bryant"};
//        String[] date = {"2016","2015","2014","2013"};
//        int[] id = {R.drawable.kaohsiung,R.drawable.kaohsiung,R.drawable.kaohsiung,R.drawable.kaohsiung};
//        for(int i = 0;i<title.length;i++){
//            information mdata = new information();
//            mdata.name = title[i];
//            mdata.time = date[i];
////            mdata.id = id[i];
//            data.add(mdata);
//        }
//        return data;
//
//    }


}
