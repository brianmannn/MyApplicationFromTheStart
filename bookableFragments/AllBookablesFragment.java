package com.crazyhands.myapplicationfromthestart.bookableFragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.crazyhands.myapplicationfromthestart.Adapters.SimpleListAdapter;
import com.crazyhands.myapplicationfromthestart.Items.List_item;
import com.crazyhands.myapplicationfromthestart.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllBookablesFragment extends Fragment {


    public AllBookablesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_bookables, container, false);
        // Create an ArrayList of ClistItems objects

        final ArrayList<List_item> ListItems = new ArrayList<List_item>();
        ListItems.add(new List_item("YOGA", 800, 8976));
        ListItems.add(new List_item("PILaties", 900, 6749));


        // Create an {@link SimpleListAdapter}, whose data source is a list of
        // {@link List_item}s. The adapter knows how to create list item views for each item
        // in the list.
        SimpleListAdapter ItemsAdapter = new SimpleListAdapter(getActivity(), ListItems);

        // Get a reference to the ListView, and attach the adapter to the listView.
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(ItemsAdapter);

/*
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                List_item item = ListItems.get(position);

                Intent newIntent = new Intent(getActivity(), item.getActivityToName());
                startActivity(newIntent);



            }
        });
*/

        return rootView;




    }

}
