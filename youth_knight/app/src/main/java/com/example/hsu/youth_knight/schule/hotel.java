package com.example.hsu.youth_knight.schule;


import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.hsu.youth_knight.R;
import com.example.hsu.youth_knight.seeattractions;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class hotel extends Fragment implements OnMapReadyCallback{


    public hotel() {
        // Required empty public constructor
    }
    MapView mapView;
    seeattractions.hotelobj obj = null;
    private GoogleMap googlemap;
    TextView name,address,describle;
    Button bt_add,bt_google;
    ImageView image;
    RequestQueue mQueue  ;
    RequestQueue mQueuejson  ;

    OkHttpClient client = new OkHttpClient();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_hotel,container,false);
        Bundle bundle=getArguments();
        obj = (seeattractions.hotelobj) bundle.getSerializable("mitem");
        name = (TextView)v.findViewById(R.id.hotel_t_name);
        name.setText(obj.getName());
        address = (TextView)v.findViewById(R.id.hotel_t_address);
        address.setText(obj.getAddress());
        describle = (TextView)v.findViewById(R.id.hotel_t_decripse);
        describle.setText(obj.getDescrible());
        image = (ImageView)v.findViewById(R.id.hotel_image);
        bt_google = (Button)v.findViewById(R.id.hotel_bt_google);
        bt_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle1 = new Bundle();
                bundle1.putString("weburl",obj.getHotelurl());
                web fragment1 = new web();
                FragmentTransaction fragmentTransaction;
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragment1.setArguments(bundle1);
                fragmentTransaction.replace(R.id.fragmentcontainer, fragment1);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        mQueue = Volley.newRequestQueue(getContext());
        mQueuejson = Volley.newRequestQueue(getContext());
        ImageLoader imageLoader = new ImageLoader(mQueue, new ImageLoader.ImageCache() {
            @Override
            public void putBitmap(String url, Bitmap bitmap) {
            }

            @Override
            public Bitmap getBitmap(String url) {
                return null;
            }
        });
        final ImageLoader.ImageListener listener = ImageLoader.getImageListener(image,
                R.drawable.first, R.drawable.first);
        imageLoader.get(obj.getImageurl(), listener);




       // scrollView.setScrollViewListener(this);
        // Inflate the layout for this fragment
        return v;
    }
    private String ans = null;
    private Handler mHandler;

    @Override
    public void onResume() {
        super.onResume();

//        LatLng latLng = loadmap(myans);
//        saveLating(latLng);

    }
    private class MapAsyn extends AsyncTask<String,Integer,LatLng>
    {

        @Override
        protected void onPostExecute(LatLng latLng) {
            super.onPostExecute(latLng);
            saveLating(latLng);
            moveMap(latLng);
        }

        @Override
        protected LatLng doInBackground(String... params) {
            Response response;
            Request request = new Request.Builder()
                    .url("http://maps.google.com/maps/api/geocode/json?address=" + obj.getAddress() + "&sensor=false")
                    .build();

            try {
               response = client.newCall(request).execute();
                return loadmap(response.body().string());
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

        }

    }
    private LatLng loadmap(final String s)
    {


                try {
                    JSONObject jsonObject = new JSONObject(s);
                    Double  longitute = ((JSONArray)jsonObject.get("results")).getJSONObject(0)
                            .getJSONObject("geometry").getJSONObject("location")
                            .getDouble("lng");
                    Double  latitude = ((JSONArray)jsonObject.get("results")).getJSONObject(0)
                            .getJSONObject("geometry").getJSONObject("location")
                            .getDouble("lat");


                    LatLng latLng = new LatLng(latitude,longitute);
                    return latLng;

                } catch (JSONException e) {

                    return null;
                }


    }
    Handler mapHandler;
    private void draw(final LatLng latLng)
    {
        mapHandler = new Handler();
        mapHandler.post(new Runnable() {
            @Override
            public void run() {
//                saveLating(latLng);
//                moveMap(latLng);
            }
        });
    }
    public void saveLating(LatLng lating) {

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(lating);
        markerOptions.title(lating.latitude + " : " + lating.longitude);

        googlemap.addMarker(markerOptions);
        //markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker));
        // mMap.clear();
        // Animating to the touched position
        //  mMap.animateCamera(CameraUpdateFactory.newLatLng(lating));
        // Placing a marker on the touched position

//        googlemap.addMarker(markerOptions);
        //moveMap(lating);
    }
    private void moveMap(LatLng place) {
        //建立地圖攝影機的位置元件
        CameraPosition cameraPosition =
                new CameraPosition.Builder()
                        .target(place)
                        .zoom(17)
                        .build();
        //使用動畫的效果移動地圖
        googlemap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition),500,null);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView = (MapView) view.findViewById(R.id.hotel_map);
        if (mapView != null) {
            mapView.onCreate(savedInstanceState);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }
    @Override
    public void onMapReady(final GoogleMap Map) {
        this.googlemap = Map;

        LatLng sydney = new LatLng(-34, 151);
        Map.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        Map.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        new MapAsyn().execute("http://maps.google.com/maps/api/geocode/json?address=" + obj.getAddress() + "&sensor=false");

    }



}
