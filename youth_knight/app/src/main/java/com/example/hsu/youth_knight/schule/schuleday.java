package com.example.hsu.youth_knight.schule;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.hsu.youth_knight.R;
import com.example.hsu.youth_knight.database.DBhelper_schule;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hsu on 2017/1/21.
 */
public class schuleday extends Fragment {
    ListView list;
    ImageView imageView;

    RequestQueue mQueue ;
    TextView name;
    TextView date;
    Cursor cursor;
    int countlist;
    long sqliteid;
    Allschule.CustomObject obj  =null;
    DBhelper_schule dbhelper ;
    private ListAdapter adapter;
    ArrayList<String> listcontent = new ArrayList<>();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schuleday,container,false);
        mQueue = Volley.newRequestQueue(getContext());
        list = (ListView)view.findViewById(R.id.schuleday_list);
        Bundle bundle=getArguments();
        imageView = (ImageView)view.findViewById(R.id.Imageschuleday);
        name = (TextView)view.findViewById(R.id.schuleday_name);
        date = (TextView)view.findViewById(R.id.schuleday_date);
        obj = (Allschule.CustomObject) bundle.getSerializable("item");
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
        imageLoader.get(obj.getImageurl(), listener);
        if(obj!=null)
        {
            Log.d("new",obj.getName());
            name.setText(obj.getName());
            date.setText(obj.getDate()+"-"+obj.getDateend());
            sqliteid = obj.sqliteid;
            Log.d("long",""+obj.sqliteid);
        }

        new loadsqldata().execute(sqliteid);
       getActivity().setTitle(obj.getName());

        return view;
    }


    private class loadsqldata extends AsyncTask<Long,Void,Integer> {

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            listcontent.clear();
            for(int i = 1;i<=integer;i++)
            {
               listcontent.add("第"+i+"天");
            }
            listcontent.add("增加天數");
            String[] array = listcontent.toArray(new String[listcontent.size()]);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,array);
            list.setAdapter(adapter);


        }

        @Override
        protected Integer doInBackground(Long... params) {

            dbhelper = new DBhelper_schule(getContext());
            List<information> data = new ArrayList<>();
            SQLiteDatabase db = dbhelper.getWritableDatabase();

            cursor = dbhelper.get(db, sqliteid);
            if (cursor != null) {

                countlist = cursor.getInt(4);
            }
            return countlist;
        }

    }


    @Override
    public void onResume() {
        super.onResume();
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               if(position==list.getCount()-1)
               {
                new uploadsqldata().execute(sqliteid);
               }
                
                Log.d("pos",""+position+","+list.getCount());

            }
        });
    }
    private class uploadsqldata extends AsyncTask<Long,Void,Integer> {

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            listcontent.clear();
            for (int i = 1; i <= integer; i++) {
                listcontent.add("第" + i + "天");
            }
            listcontent.add("增加天數");
            String[] array = listcontent.toArray(new String[listcontent.size()]);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, array);
            list.setAdapter(adapter);


        }

        @Override
        protected Integer doInBackground(Long... params) {

            dbhelper = new DBhelper_schule(getContext());
            SQLiteDatabase db = dbhelper.getWritableDatabase();
            cursor = dbhelper.get(db, sqliteid);
            int day = obj.getDay();
            if (cursor != null) {

                countlist = cursor.getInt(4) + 1;
                day = countlist;
            }
            dbhelper.update(db, sqliteid, obj.name, obj.date, obj.dateend, day, obj.imageurl, obj.contentjson);


            return countlist;
        }
    }


}

