package com.avimarineinnovations.yachtiesanonymous.general;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.avimarineinnovations.yachtiesanonymous.communication.Firebase;
import com.avimarineinnovations.yachtiesanonymous.communication.ICommManager;


/**
 * Created by aayaffe on 19/03/2016.
 */
public class Versioning {
    private static final String TAG = "Versioning";
    private Context c;
    private ICommManager commManager;

    public Versioning(Context c){
        this.c = c;
        commManager = new Firebase(c);
    }

    public long getSupportedVersion(){
        try {
            return commManager.getSupportedVersion();
        }catch (Exception e){
            return -1;
        }
    }

    public int getInstalledVersion(){
        try {
            PackageInfo pInfo = c.getPackageManager().getPackageInfo(c.getPackageName(), 0);
            return pInfo.versionCode;
        }catch(PackageManager.NameNotFoundException e)
        {
            Log.d(TAG,"Error retreiving versioncode: ",e);
        }
        return -1;
    }
}
