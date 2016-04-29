package com.avimarineinnovations.yachtiesanonymous.geographical;

import android.location.Location;

/**
 * Created by aayaffe on 22/09/2015.
 */
public interface IGeo {

    Location getLoc();

    void stopLocationUpdates();

    void startLocationUpdates();
}
