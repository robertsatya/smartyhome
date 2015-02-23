package com.testing.harsha.smarty;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by harsha on 23/2/15.
 */
public class AppDrawerFragment extends Fragment {

    private PackageManager manager;
    private List<AppDetail> apps;
    GridView mAppGrid;
    private Activity mActivity = getActivity();


    public static AppDrawerFragment newInstance() {
        AppDrawerFragment fragment = new AppDrawerFragment();
        return fragment;
    }

    public AppDrawerFragment(){

    }

    public class AppDetail {
        CharSequence label;
        CharSequence name;
        Drawable icon;
    }

    private void loadApps() {
        manager = getActivity().getPackageManager();
        apps = new ArrayList<AppDetail>();

        Intent i = new Intent(Intent.ACTION_MAIN, null);
        i.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> mAvailActivities = manager.queryIntentActivities(i, 0);
        for (ResolveInfo ri:mAvailActivities) {
            AppDetail app = new AppDetail();
            app.label = ri.loadLabel(manager);
            app.name = ri.activityInfo.packageName;
            app.icon = ri.activityInfo.loadIcon(manager);
            apps.add(app);
        }
    }

    private void populateGridView(){
        CustomGridAdapter adapter = new CustomGridAdapter(getActivity(),apps);
        mAppGrid = (GridView) getActivity().findViewById(R.id.app_grid);
        mAppGrid.setAdapter(adapter);
        mAppGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"You clicked",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mAppView = inflater.inflate(R.layout.app_drawer_layout, container, false);
        loadApps();
        populateGridView();
        return mAppView;
    }

    public class CustomGridAdapter extends BaseAdapter {

        private Context mContext;
        private final List<AppDetail> apps;

        public CustomGridAdapter(Context c, List<AppDetail> appList){
            mContext = c;
            this.apps = appList;
        }


        @Override
        public int getCount() {
            return apps.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View grid;
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (convertView == null) {
                grid = new View(mContext);
                grid = inflater.inflate(R.layout.app_icon, null);
                TextView mAppName = (TextView) grid.findViewById(R.id.app_name);
                ImageView mAppIcon = (ImageView) grid.findViewById(R.id.app_icon);
                AppDetail app = apps.get(position);
                mAppName.setText(app.name);
                mAppIcon.setImageDrawable(app.icon);
            } else {
                grid = (View) convertView;
            }


            return grid;
        }
    }
}
