package com.stanislavsikorsyi.playingwithmaterialdesign.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stanislavsikorsyi on 07.11.14.
 */
public class Utils {


    public static List<PackageInfo> InstalledApps(Context context) {
        List<PackageInfo> installedInfos = new ArrayList<PackageInfo>();
        List<PackageInfo> PackList = context.getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < PackList.size(); i++) {
            PackageInfo PackInfo = PackList.get(i);
            if ((PackInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                String AppName = PackInfo.applicationInfo.loadLabel(context.getPackageManager()).toString();
                Log.e("App № " + Integer.toString(i), AppName);
                installedInfos.add(PackInfo);
            }
        }
        return installedInfos;
    }
}
