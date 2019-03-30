package com.example.hsu.youth_knight;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.hsu.youth_knight.database.DBhelper_hotel;
import com.example.hsu.youth_knight.database.DBhelper_route;
import com.example.hsu.youth_knight.schule.Adapter_hotel;
import com.example.hsu.youth_knight.schule.Adapter_route;
import com.example.hsu.youth_knight.schule.hotel;
import com.example.hsu.youth_knight.schule.list_hotel;
import com.example.hsu.youth_knight.schule.list_routedata;
import com.example.hsu.youth_knight.schule.route;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class seeattractions extends Fragment {


    public seeattractions() {
        // Required empty public constructor
    }
    private MListener mlistener;
    public static interface MListener{
        public void onClick(int postition);
    }
    DBhelper_hotel dBhelper_hotel;
    DBhelper_route dBhelper_route;
    Cursor cursor;
    private Spinner spinner;
    private Spinner spinner1;
    String[] hotelpostition = {"請選擇項目","基隆市","台北市","新北市","桃園市","宜蘭縣","新竹縣","新竹市","苗栗縣","台中市","南投縣","彰化市",
            "嘉義縣","嘉義市","雲林縣","台南市","高雄市","屏東縣","台東縣","花蓮縣"};
    String[] myitem = {"請選擇項目","hotel","route"};
    String[] myitem1 = {"請選擇項目","hotel"};
    Adapter_hotel hoteladapter;
    Adapter_route routeadapter;
    List<list_hotel> mycopydata = new ArrayList<>();
    List<list_routedata> myroutedata = new ArrayList<>();
    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_seeattractions,container,false);
        spinner = (Spinner)v. findViewById(R.id.planets_spinner);
        spinner1 = (Spinner)v. findViewById(R.id.planets_spinner1);
        recyclerView = (RecyclerView)v.findViewById(R.id.attraction_recycleview);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,hotelpostition);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
       spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, final int positions, long id) {

                 if(positions <=11 && positions!=0)
                {

                    ArrayAdapter<String> madapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,myitem);
                    madapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner1.setAdapter(madapter);
                    spinner1.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view,  int position, long id) {

                            switch (position)
                            {
                                case 1:
                                    coopydata(loadhotel(getjson(positions)));
                                    hoteladapter = new Adapter_hotel(getContext(),mycopydata);
                                    recyclerView.setAdapter(hoteladapter);
                                    GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2);
                                    recyclerView.setLayoutManager(layoutManager);
                                    hoteladapter.setListnear(new Adapter_hotel.MListener() {
                                        @Override
                                        public void onClick(int postition2) {
                                            hotel fragment1 = new hotel();
                                            FragmentTransaction fragmentTransaction;
                                            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                            fragmentTransaction = fragmentManager.beginTransaction();
                                            Bundle bundle = new Bundle();
                                            hotelobj obj = new hotelobj();
                                            obj.setAddress(mycopydata.get(postition2).getAddress());
                                            obj.setImageurl(mycopydata.get(postition2).getImageurl());
                                            obj.setDescrible(mycopydata.get(postition2).getHotel_discrible());
                                            obj.setName(mycopydata.get(postition2).getName());
                                            obj.setHotelurl(mycopydata.get(postition2).getHotelurl());
                                            bundle.putSerializable("mitem",obj);
                                            Log.d("mitem",obj.getName()+obj.getAddress()+obj.getDescrible());
                                            fragment1.setArguments(bundle);
                                            fragmentTransaction.replace(R.id.fragmentcontainer, fragment1);
                                            fragmentTransaction.addToBackStack(null);
                                            fragmentTransaction.commit();
                                        }
                                    });
                                    break;
                                case 2:
                                {
                                    new routeasyntasj().execute(new Integer(positions));
//                                    SQLiteDatabase db = dBhelper_route.getWritableDatabase();
//                                    cursor = dBhelper_route.getdata(db,hotelpostition[positions]);
//                                    String data = cursor.getString(1);
//                                    Log.d("data",data);
//                                    copyroutedata(loadroute(getroutejson(positions)));
//
////
//                                    routeadapter = new Adapter_route(getContext(),myroutedata);
//                                    recyclerView.setAdapter(hoteladapter);
//                                    GridLayoutManager mlayoutManager = new GridLayoutManager(getContext(),2);
//                                    recyclerView.setLayoutManager(mlayoutManager);



                                }
                                default:
                                    break;
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
               else if(positions>11){

                     ArrayAdapter<String> madapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,myitem1);
                     madapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                     spinner1.setAdapter(madapter);
                     spinner1.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
                         @Override
                         public void onItemSelected(AdapterView<?> parent, View view,  int position, long id) {
                             switch (position)
                             {
                                 case 1:
                                     coopydata(loadhotel(getjson(positions)));
                                     hoteladapter = new Adapter_hotel(getContext(),mycopydata);
                                     recyclerView.setAdapter(hoteladapter);
                                     GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2);
                                     recyclerView.setLayoutManager(layoutManager);
                                     hoteladapter.setListnear(new Adapter_hotel.MListener() {
                                         @Override
                                         public void onClick(int postition2) {
                                             hotel fragment1 = new hotel();
                                             FragmentTransaction fragmentTransaction;
                                             FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                             fragmentTransaction = fragmentManager.beginTransaction();
                                             Bundle bundle = new Bundle();
                                             hotelobj obj = new hotelobj();
                                             obj.setAddress(mycopydata.get(postition2).getAddress());
                                             obj.setImageurl(mycopydata.get(postition2).getImageurl());
                                             obj.setDescrible(mycopydata.get(postition2).getHotel_discrible());
                                             obj.setName(mycopydata.get(postition2).getName());
                                             obj.setHotelurl(mycopydata.get(postition2).getHotelurl());
                                             bundle.putSerializable("mitem",obj);
                                             Log.d("mitem",obj.getName()+obj.getAddress()+obj.getDescrible());
                                             fragment1.setArguments(bundle);
                                             fragmentTransaction.replace(R.id.fragmentcontainer, fragment1);
                                             fragmentTransaction.addToBackStack(null);
                                             fragmentTransaction.commit();
                                         }
                                     });
                                     break;

                                 default:
                                     break;
                             }
                         }

                         @Override
                         public void onNothingSelected(AdapterView<?> parent) {

                         }
                     });
                 }

           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });

        return v;
    }
    private boolean copydatabases(Context context)
    {
        try {
            InputStream inputStream = context.getAssets().open(DBhelper_route.DBname);
            String outFilename = dBhelper_route.DBlocation + dBhelper_route.DBname;
            OutputStream outputStream = new FileOutputStream(outFilename);
            byte[] buff = new byte[5000];
            int length = 0;
            while ((length = inputStream.read(buff)) > 0)
            {
                outputStream.write(buff,0,length);
            }
            outputStream.flush();
            outputStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private String getjson(final int postition)
    {


        dBhelper_hotel = new DBhelper_hotel(getContext());
        SQLiteDatabase db = dBhelper_hotel.getWritableDatabase();
        cursor = dBhelper_hotel.getdata(db,hotelpostition[postition]);
        String data = cursor.getString(1);

        Log.d("data",data);
        db.close();
        cursor.close();
        return data;

    }
    private String getroutejson(final int postition)
    {
            try {
                dBhelper_route = new DBhelper_route(getContext());
                dBhelper_route.checkdatabase();
                dBhelper_route.copydatabase();
                dBhelper_route.opendatabase();
                cursor = dBhelper_route.getdata(hotelpostition[postition]);
                String data = cursor.getString(1);
//                StringBuffer out = null;
//                out.append(data);
                Log.d("data",data);
                cursor.close();
                dBhelper_route.close();
                return data;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            //db.close();







//        SQLiteDatabase db = dBhelper_route.getWritableDatabase();


    }
    private void copyroutedata(List<list_routedata> data)
    {
        if(myroutedata!=null)
        {
            myroutedata.clear();
        }
        if(data!=null)
        {
            for(int i = 0;i<data.size();i++)
            {
                myroutedata.add(data.get(i));
            }
        }
    }
    private void coopydata(List<list_hotel> data)
    {
        if (mycopydata!=null)
        {
            mycopydata.clear();
        }
        if (data!=null) {
            for (int i = 0; i < data.size(); i++) {
                mycopydata.add(data.get(i));
            }
        }

    }
    Handler mhandler;
    private List<list_routedata> loadroute(String json)
    {
        List<list_routedata> data = new ArrayList<>();
        if (json!=null)
        {
            Log.d("jsonArray",json.toString());
            try {
                JSONObject jsonObject = new JSONObject(json);

                //JSONObject mjsonObject = jsonObject.getJSONObject("content");

                JSONArray jsonArray = jsonObject.getJSONObject("content").getJSONArray("polygon");
                Log.d("jsonArray",jsonArray.toString());
                for (int i =0;i<jsonArray.length();i++)
                {
                    list_routedata mdata = new list_routedata();
                    JSONObject jsondata = jsonArray.getJSONObject(i);
                    mdata.setName(jsondata.getString("name"));
                    Log.d("name",jsondata.getString("name"));
                    mdata.setKm(jsondata.getString("length")+"km");
                    Log.d("length",jsondata.getString("length")+"km");
                    ArrayList<LatLng> latLngs = new ArrayList<>();
                    JSONArray lat = jsondata.getJSONArray("latlng");
                    for(int j = 0;j<lat.length();j++)
                    {
                        JSONObject mlat = lat.getJSONObject(j);
                        Double x = mlat.getDouble("x");
                        Double y = mlat.getDouble("y");
                        latLngs.add(new LatLng(y,x));
                    }
                    mdata.setLatLngs(latLngs);

                    data.add(mdata);

                }


            } catch (JSONException e) {
                e.printStackTrace();

            }
            return data;

        }
        else {
            return data;
        }


    }
    Handler othethandler;
    private void route(final int postition){
        othethandler = new Handler();
        othethandler.post(new Runnable() {
            @Override
            public void run() {
                copyroutedata(loadroute(getroutejson(postition)));

//
                routeadapter = new Adapter_route(getContext(),myroutedata);
                recyclerView.setAdapter(hoteladapter);
                GridLayoutManager mlayoutManager = new GridLayoutManager(getContext(),2);
                recyclerView.setLayoutManager(mlayoutManager);

            }
        });

    }
    private List<list_hotel> loadhotel(String json){
        List<list_hotel> data = new ArrayList<>();
        if(json!=null)
        {
            try {
                JSONObject jsonObject = new JSONObject(json);
                JSONArray jsonArray = jsonObject.getJSONArray("hotel");

                for(int i = 0;i<jsonArray.length();i++)
                {
                    list_hotel mdata = new list_hotel();
                    JSONObject jsondata = jsonArray.getJSONObject(i);

                    mdata.setName(jsondata.getString("name"));
                    mdata.setImageurl(jsondata.getString("imageurl"));
                    mdata.setAddress(jsondata.getString("address"));
                    mdata.setHotel_discrible(jsondata.getString("descripe"));
                    mdata.setHotelurl(jsondata.getString("hotelurl"));
                    data.add(mdata);


                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return data;

        }
        else {
            return data;
        }


    }
    private class routeasyntasj extends AsyncTask<Integer,Integer,Integer>
    {

        @Override
        protected Integer doInBackground(Integer... params) {
            copyroutedata(loadroute(getroutejson(params[0])));
            return null;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            routeadapter = new Adapter_route(getContext(),myroutedata);
            recyclerView.setAdapter(routeadapter);
            GridLayoutManager mlayoutManager = new GridLayoutManager(getContext(),2);
            recyclerView.setLayoutManager(mlayoutManager);
            routeadapter.setListnear(new Adapter_route.MListener() {
                @Override
                public void onClick(int postition) {
                    route fragment1 = new route();
                    FragmentTransaction fragmentTransaction;
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    Bundle bundle = new Bundle();
                    routeobj obj = new routeobj();
                    obj.setName(myroutedata.get(postition).getName());
                    obj.setLength(myroutedata.get(postition).getKm());
                    obj.setLatLngs(myroutedata.get(postition).getLatLngs());
                    bundle.putSerializable("mroute",obj);
                    fragment1.setArguments(bundle);
                    fragmentTransaction.replace(R.id.fragmentcontainer, fragment1);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            });

        }
    }
    public static class routeobj implements Serializable{
        private static final long serialVersionUID = -7060210544600464482L;
        String name,length;
        ArrayList<LatLng> latLngs;

        public static long getSerialVersionUID() {
            return serialVersionUID;
        }

        public ArrayList<LatLng> getLatLngs() {
            return latLngs;
        }

        public void setLatLngs(ArrayList<LatLng> latLngs) {
            this.latLngs = latLngs;
        }

        public String getLength() {
            return length;

        }

        public void setLength(String length) {
            this.length = length;
        }

        public String getName() {

            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    public static class hotelobj implements Serializable{
        private static final long serialVersionUID = -7060210544600464481L;
        String name,imageurl,address,describle,hotelurl;

        public String getHotelurl() {
            return hotelurl;
        }

        public void setHotelurl(String hotelurl) {
            this.hotelurl = hotelurl;
        }

        public String getDescrible() {
            return describle;

        }

        public void setDescrible(String describle) {
            this.describle = describle;
        }

        public String getAddress() {
            return address;

        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getImageurl() {
            return imageurl;
        }

        public void setImageurl(String imageurl) {
            this.imageurl = imageurl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
