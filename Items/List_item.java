package com.crazyhands.myapplicationfromthestart.Items;

import android.util.Log;

/**
 * Created by crazyhands on 10/04/2017.
 */

public class List_item {
    private String mCname;
    private int mCtime;
    private int mdate;
    public List_item(String CName, int CTime,int date){
        mCname = CName;
        mCtime = CTime;
        mdate = date;
    }
    public String getCname() {
        return mCname;
    }

    public String getCtime () {
        Log.v("simplelistadapter", "in get time"+ mCtime);

        return String.valueOf(mCtime);}

    public String getdate() {
        return String.valueOf(mdate);}

}
