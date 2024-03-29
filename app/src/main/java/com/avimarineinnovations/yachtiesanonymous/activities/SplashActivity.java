package com.avimarineinnovations.yachtiesanonymous.activities;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import java.util.Date;
import com.avimarineinnovations.yachtiesanonymous.R;
import com.avimarineinnovations.yachtiesanonymous.communication.CommManagerEventListener;
import com.avimarineinnovations.yachtiesanonymous.communication.Firebase;
import com.avimarineinnovations.yachtiesanonymous.communication.ICommManager;
import com.avimarineinnovations.yachtiesanonymous.dialogs.DialogUtils;
import com.avimarineinnovations.yachtiesanonymous.general.Versioning;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = "SplashActivity";
    private ICommManager commManager;
    private boolean dbConnected;
    private Versioning versioning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        versioning = new Versioning(this);
        commManager = new Firebase(this);
        commManager.setCommManagerEventListener(new CommManagerEventListener() {
            @Override
            public void onConnect(Date time) {
                dbConnected = true;
                if (versioning.getInstalledVersion()<versioning.getSupportedVersion())
                {
                    Log.d(TAG, "Version not supported, starting dialog");
                    Dialog d = DialogUtils.createDialog(SplashActivity.this, R.string.version_not_supported_dialog_title,
                            R.string.version_not_supported_dialog_message, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            final String appPackageName = SplashActivity.this.getPackageName(); // getPackageName() from Context or Activity object
                            try {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                            } catch (android.content.ActivityNotFoundException anfe) {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                            }
                        }
                    }, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
                    d.show();

                } else {
//                    Intent intent = new Intent(SplashActivity.this, ChooseEventActivity.class);
//                    startActivity(intent);
//                    finish();
                }
            }

            @Override
            public void onDisconnect(Date time) {
                dbConnected = false;
            }
        });
        commManager.login(null, null, null);
    }
}