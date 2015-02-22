package com.testing.harsha.smarty;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by harsha on 22/2/15.
 */
public class ContactsFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor>{

    private static String TAG = ContactsFragment.class.getName();
    private LayoutInflater inflater = null;

    private List contactItemList = new LinkedList<ContactItem>();
    private LayoutInflater mInflater;
    public boolean taskRun = false;
    long currentID = 0;
    long currentContactID = 0;

    public class ContactItem {
        public long mId;
        public long mContactId;
        public String mDisplayName;
        public String mPhone;
    }

    public static ContactsFragment newInstance(int section) {
        ContactsFragment fragment = new ContactsFragment();
        return fragment;
    }

    public ContactsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    class ContactsAdapter extends ArrayAdapter<ContactItem> {

        public ContactsAdapter () {

        }

        public ContactsAdapter(Context context, int resource, int textViewResId, List objects) {

        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
