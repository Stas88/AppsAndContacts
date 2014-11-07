package com.stanislavsikorsyi.playingwithmaterialdesign;

import android.app.Fragment;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by stanislavsikorsyi on 07.11.14.
 */
public class AppsFragment extends Fragment
{

    GridView mGrid;
    private List<ResolveInfo> mApps;

    public static final String ARG_OBJECT = "object";

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        loadApps(); // do this in onresume?

        View rootView = inflater.inflate(
                R.layout.fragments_apps_gridview, container, false);
        mGrid = (GridView) rootView.findViewById(R.id.gridview);
        mGrid.setAdapter(new AppsAdapter());

        return rootView;
    }

    private void loadApps() {
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        mApps = getActivity().getPackageManager().queryIntentActivities(mainIntent, 0);
    }


    public class AppsAdapter extends BaseAdapter {

        public AppsAdapter() {
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;

            if (convertView == null) {
                imageView = new ImageView(getActivity());
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setLayoutParams(new GridView.LayoutParams(150, 150));
            } else {
                imageView = (ImageView) convertView;
            }

            ResolveInfo info = mApps.get(position);
            imageView.setImageDrawable(info.activityInfo.loadIcon(getActivity().getPackageManager()));

            return imageView;
        }


        public final int getCount() {
            return mApps.size();
        }

        public final Object getItem(int position) {
            return mApps.get(position);
        }

        public final long getItemId(int position) {
            return position;
        }
    }



}



