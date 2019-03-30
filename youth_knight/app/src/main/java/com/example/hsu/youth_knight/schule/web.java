package com.example.hsu.youth_knight.schule;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.hsu.youth_knight.R;



public class web extends Fragment {




    public web() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment web.
     */
//    // TODO: Rename and change types and number of parameters
//    public static web newInstance(String param1, String param2) {
//        web fragment = new web();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
    String url = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle=getArguments();
        url =  bundle.getString("weburl");

    }
    WebView webView = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_web,container,false);
        webView = (WebView)view.findViewById(R.id.webview);
        webView.loadUrl(url);

        return view;
    }



    @Override
    public void onDetach() {
        super.onDetach();

    }



}
