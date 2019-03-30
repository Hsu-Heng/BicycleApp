package com.example.hsu.youth_knight.schule;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hsu.youth_knight.R;
import com.example.hsu.youth_knight.database.DBhelper_schule;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class newschule extends Fragment implements DatePickerDialog.OnDateSetListener{
    private EditText ed_name,ed_day;
    private TextView t_date;

    DBhelper_schule dBhelper;
    String[] url = {"https://picjumbo.imgix.net/HNCK3206.jpg?q=40&w=1000&sharp=30","https://picjumbo.imgix.net/HNCK3206.jpg?q=40&w=1000&sharp=30",
                   "https://picjumbo.imgix.net/P1010379.jpg?q=40&w=1000&sharp=30","https://picjumboblog.imgix.net/VH_0202.jpg?q=40&w=1650&sharp=30"};
    Activity mActivity;
    AppCompatActivity mAppCompatActivity;
    private Calendar calendar = Calendar.getInstance();
    private int mYear, mMonth, mDay;
    int day;
    Cursor cursor;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActivity().setTitle("新增行程");

//        new Allschule().onBackStackChanged();
}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_newschule, container, false);
        ed_name = (EditText) rootview.findViewById(R.id.newschule_ed_name);
        ed_day = (EditText)rootview.findViewById(R.id.newschule_ed_day);
        t_date = (TextView)rootview.findViewById(R.id.newschule_t_godate);
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        t_date.setText(setDateFormat(mYear,mMonth,mDay));
        t_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog start = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                        t_date.setText(setDateFormat(year,month,day));
                        mYear = year;
                        mMonth = month;
                        mDay = day;
                    }
                },mYear,mMonth,mDay);
                start.show();
            }
        });


        setHasOptionsMenu(true);
//        getActivity().getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
//            @Override
//            public void onBackStackChanged() {
//                AppCompatActivity mappactivity;
//                int stackHeight = getActivity().getSupportFragmentManager().getBackStackEntryCount();
//                if (stackHeight > 0) { // if we have something on the stack (doesn't include the current shown fragment)
//                    getSupportActionBar().setHomeButtonEnabled(true);
//                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//                } else {
//                    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//                    getSupportActionBar().setHomeButtonEnabled(false);
//                }
//            }
//        });


        return rootview;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("新增行程");
    }
    //    private Toolbar initToolbar(CharSequence title) {
//        mAppCompatActivity = (AppCompatActivity) getActivity();
//        Toolbar toolbar = (Toolbar) mAppCompatActivity.findViewById(R.id.toolbar);
//        toolbar.setTitle(title);
//
//        mAppCompatActivity.setSupportActionBar(toolbar);
//        ActionBar actionBar = mAppCompatActivity.getSupportActionBar();
//        if (actionBar != null) {
//            //actionBar.setDisplayHomeAsUpEnabled(true);
//            actionBar.setDisplayShowTitleEnabled(true);
//            actionBar.setHomeButtonEnabled(true);
//        }
//
//
//        return toolbar;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
           switch(item.getItemId())
           {
               case android.R.id.home: {

                   FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                   fragmentManager.popBackStack();
                   return true;
               }
               case R.id.allschule_addschule:

               {
                   if(ed_name.getText().toString()!=null) {
                       dBhelper = new DBhelper_schule(getContext());
                       SQLiteDatabase db = dBhelper.getWritableDatabase();
                       cursor = dBhelper.querydata(db, " SELECT * FROM schule ");
                       String date = t_date.getText().toString();
                       String[] num = date.split("/");
                       int count = cursor.getCount();
                       dBhelper.schuleinsertitem(db, ed_name.getText().toString(), t_date.getText().toString(), setDateend(mYear
                               , mMonth,mDay,Integer.valueOf(ed_day.getText().toString())), Integer.valueOf(ed_day.getText().toString()), url[count%4], "");
                       db.close();
                       cursor.close();
                       FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                       fragmentManager.popBackStack();
                       return true;
                   }
               }
               default:
                   return super.onOptionsItemSelected(item);
           }

    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_fragment_newschulle, menu);
        super.onCreateOptionsMenu(menu,inflater);

    }

    private String setDateFormat(int year, int monthOfYear, int dayOfMonth){
        return String.valueOf(year) + "/"
                + String.valueOf(monthOfYear + 1) + "/"
                + String.valueOf(dayOfMonth);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }
    private String setDateend(int year,int monthofyear,int dayofmouth,int day)
    {
        Calendar mcalendar =  Calendar.getInstance();
        mcalendar.set(Calendar.YEAR,year);
        mcalendar.set(Calendar.MONTH,monthofyear);
        mcalendar.set(Calendar.DAY_OF_MONTH,dayofmouth);
        mcalendar.add(Calendar.DAY_OF_MONTH,day-1);
        return String.valueOf(mcalendar.get(Calendar.YEAR))+"/"
                +String.valueOf(mcalendar.get(Calendar.MONTH)+1)+"/"
                +String.valueOf(mcalendar.get(Calendar.DAY_OF_MONTH));

    }
}
