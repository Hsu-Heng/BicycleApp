package com.example.hsu.youth_knight.schule;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hsu.youth_knight.R;
import com.example.hsu.youth_knight.seeattractions;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

/**
 * A simple {@link Fragment} subclass.
 */
public class route extends Fragment implements OnMapReadyCallback {

    MapView mapView;
    seeattractions.routeobj obj = null;
    private GoogleMap googlemap;
    TextView name,length;
    public route() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_route,container,false);
        Bundle bundle=getArguments();
        obj = (seeattractions.routeobj) bundle.getSerializable("mroute");
        name = (TextView)v.findViewById(R.id.fragmemt_route_name);
        name.setText(obj.getName());
        length = (TextView)v.findViewById(R.id.fragmemt_route_km);
        length.setText(obj.getLength());
        return v;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView = (MapView) view.findViewById(R.id.route_map);
        if (mapView != null) {
            mapView.onCreate(savedInstanceState);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap Map) {
        this.googlemap = Map;
        PolylineOptions polylineOpt = new PolylineOptions();
        for (int i = 0 ;i < obj.getLatLngs().size();i++)
        {
            Log.d("plo",""+obj.getLatLngs().get(i));
            polylineOpt.add(obj.getLatLngs().get(i));
        }
        polylineOpt.color(Color.BLUE);
        Polyline line = googlemap.addPolyline(polylineOpt);
        line.setWidth(10);
        Map.addMarker(new MarkerOptions().position(obj.getLatLngs().get(0)).title(obj.getName()));
        moveMap(obj.getLatLngs().get(0));
    }
    private void moveMap(LatLng place) {
        //建立地圖攝影機的位置元件
        CameraPosition cameraPosition =
                new CameraPosition.Builder()
                        .target(place)
                        .zoom(25)
                        .build();
        //使用動畫的效果移動地圖
        googlemap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition),500,null);
    }
}
