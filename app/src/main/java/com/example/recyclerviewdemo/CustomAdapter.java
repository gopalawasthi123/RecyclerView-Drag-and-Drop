package com.example.recyclerviewdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter {
    Context context;
    ArrayList<Item> arrayList;
    public static final int HEADERVIEW =0;
    public static final int LISTVIEW =1;


    public CustomAdapter(Context context, ArrayList<Item> arrayList) {
        this.arrayList =arrayList;
        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v;
        if(getItemViewType(i) == HEADERVIEW){
            v = LayoutInflater.from(context).inflate(R.layout.header_view,viewGroup,false);
            HeaderViewHoler headerViewHoler = new HeaderViewHoler(v);
            return  headerViewHoler;
        }
        v = LayoutInflater.from(context).inflate(R.layout.custom_item,viewGroup,false);
        MyHolder myHolder = new MyHolder(v);

        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if(viewHolder.getItemViewType() == HEADERVIEW ){
            HeaderViewHoler headerViewHoler = (HeaderViewHoler)viewHolder;
            headerViewHoler.headertextview.setText("sdfbhfhjbfjwfkjwnfwjnfwjnfkjsfnkflsnflksnfklsnfwnfkjwnfkwjnfkjwnfkwjnfksdfbhfhjbfjwfkjwnfwjnfwjnfkjsfnkflsnflksnfklsnfwnfkjwnfkwjnfkjwnfkwjnfksdfbhfhjbfjwfkjwnfwjnfwjnfkjsfnkflsnflksnfklsnfwnfkjwnfkwjnfkjwnfkwjnfksdfbhfhjbfjwfkjwnfwjnfwjnfkjsfnkflsnflksnfklsnfwnfkjwnfkwjnfkjwnfkwjnfksdfbhfhjbfjwfkjwnfwjnfwjnfkjsfnkflsnflksnfklsnfwnfkjwnfkwjnfkjwnfkwjnfkjwnfkjwnfkjwnfkjwnfkjwnfkjwnfkjwnfkjnwfkjnwfkjnwkjfnwkjnfkjwnfkjwnfkjwnfkjwnfkjwnfkjnwkfjnwknfwknfkjwenfkjwenfkjwenfjkwefnwefnkwjefnwejfnwekfnwkejfnkwjenfjwknfjwefnkwjfwfnwfnwekfnwenfkjewnfjwesknfsnjsnfjsfsfskfnskfnksnfw");

        }else{

            MyHolder myHolder = (MyHolder) viewHolder;
            myHolder.name.setText(arrayList.get(i-1).name);

        }

    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return HEADERVIEW;
        }
        return LISTVIEW;
    }




    @Override
    public int getItemCount() {
        return arrayList.size()+1;
    }


    class MyHolder extends RecyclerView.ViewHolder{

        TextView name;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);

        }
    }

    class HeaderViewHoler extends RecyclerView.ViewHolder{
        TextView headertextview;
        public HeaderViewHoler(@NonNull View itemView) {
            super(itemView);
            headertextview = itemView.findViewById(R.id.header);
        }
    }
}
