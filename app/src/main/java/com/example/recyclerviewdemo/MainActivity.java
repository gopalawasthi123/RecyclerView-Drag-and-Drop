package com.example.recyclerviewdemo;

import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Layout;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView textView;
    RecyclerView.LayoutManager layoutManager;
    CustomAdapter adapter;
    // HeaderViewRecyclerAdapter headerViewRecyclerAdapter;
    NestedScrollView nestedScrollView;
    boolean dragging;
    Point mInitailPoint, mCurrentPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        final ArrayList<Item> arrayList = new ArrayList<>();
        arrayList.add(new Item("a"));
        arrayList.add(new Item("b"));
        arrayList.add(new Item("c"));
        arrayList.add(new Item("d"));
        arrayList.add(new Item("e"));
        arrayList.add(new Item("f"));
        arrayList.add(new Item("g"));
        arrayList.add(new Item("h"));
        arrayList.add(new Item("i"));
        arrayList.add(new Item("j"));
       arrayList.add(new Item("k"));
        arrayList.add(new Item("l"));
        arrayList.add(new Item("m"));
        arrayList.add(new Item("n"));
        arrayList.add(new Item("o"));
        arrayList.add(new Item("p"));
        arrayList.add(new Item("q"));
        arrayList.add(new Item("r"));
        arrayList.add(new Item("s"));

        recyclerView = findViewById(R.id.list);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        adapter = new CustomAdapter(this, arrayList);
        recyclerView.setLayoutManager(layoutManager);
       //  recyclerView.setNestedScrollingEnabled(false);
        //headerViewRecyclerAdapter = new HeaderViewRecyclerAdapter(adapter);
       // final View view = LayoutInflater.from(this).inflate(R.layout.header_view,recyclerView,false);
       // HeaderDecoration headerDecoration = new HeaderDecoration(view,false,50f,0.0f,1);

        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

       // recyclerView.addItemDecoration(headerDecoration);
        ItemTouchHelper.Callback _ithCallback = new ItemTouchHelper.Callback() {
            int dragFrom = -1;
            int dragTo = -1;
            //and in your imlpementaion of
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                    // get the viewHolder's and target's positions in your adapter data, swap them
                if(viewHolder.getItemViewType() != target.getItemViewType()){
                    return false;
                }
                int fromPosition = viewHolder.getAdapterPosition();
                int toPosition = target.getAdapterPosition();


                if(dragFrom == -1) {
                    dragFrom =  fromPosition;
                }
                dragTo = toPosition;

                if(dragFrom != -1 && dragTo != -1 && dragFrom != dragTo) {
                    reallyMoved(dragFrom, dragTo);
                    dragFrom = dragTo = -1;
                }

                // and notify the adapter that its dataset has changed
                adapter.notifyItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
                //nestedScrollView.requestDisallowInterceptTouchEvent(false);
                //recyclerView.setNestedScrollingEnabled(false);

                return true;
            }

            private void reallyMoved(int dragFrom, int dragTo) {
                if(dragFrom == 0 || dragTo == arrayList.size()){
                    return;
                }
                Collections.swap(arrayList, dragFrom-1, dragTo-1);

            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

                //TODO
            }


            //defines the enabled move directions in each state (idle, swiping, dragging).
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                if(viewHolder.getItemViewType() == CustomAdapter.HEADERVIEW){
                    return  makeMovementFlags(0,0);
                }
                int dragflags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;

                return makeMovementFlags(dragflags,0);
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(_ithCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
     //  final View view = LayoutInflater.from(this).inflate(R.layout.header_view, recyclerView, false);
       // View view1 = LayoutInflater.from(this).inflate(R.layout.footer_view, recyclerView, false);
       // headerViewRecyclerAdapter.addHeaderView(view);
        //headerViewRecyclerAdapter.addFooterView(view1);
// Extend the Callback class


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}


