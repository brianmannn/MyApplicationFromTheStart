package com.crazyhands.myapplicationfromthestart.Items;

/**
 * Created by crazyhands on 10/04/2017.
 */

public class List_item {
    private String mCname;
    private String mCtime;
    public List_item(String CName, String CTime){
        mCname = CName;
        mCtime = CTime;
    }
    public String getCname() {
        return mCname;
    }

    public String getCtime () {return mCtime;}

}
