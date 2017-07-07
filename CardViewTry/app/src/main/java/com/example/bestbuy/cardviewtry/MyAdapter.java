package com.example.bestbuy.cardviewtry;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Best Buy on 07-07-2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.DataObjectHolder> {
    ArrayList<DataProvider> DataSet;
    private static MyClickListener myClickListener;

    public static class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView t1,t2;
        ImageView im1;
        public DataObjectHolder(View itemView) {
            super(itemView);

            t1=(TextView)itemView.findViewById(R.id.txt1);
            t2=(TextView)itemView.findViewById(R.id.txt2);
            im1=(ImageView)itemView.findViewById(R.id.img1);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            myClickListener.onItemClick(getAdapterPosition(),view);
        }
    }

    public MyAdapter(ArrayList<DataProvider> newDataSet){
        DataSet=newDataSet;
    }
    public void setItemOnClickListener(MyClickListener myClickListener){
        this.myClickListener=myClickListener;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main,parent,false);
        DataObjectHolder doh1=new DataObjectHolder(v);

        return doh1;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.t1.setText(DataSet.get(position).getText1());
        holder.t2.setText(DataSet.get(position).getText2());
        holder.im1.setImageResource(DataSet.get(position).getId());
    }




    @Override
    public int getItemCount() {
        return DataSet.size();
    }

    public interface MyClickListener{
        public void onItemClick(int position,View v);
    }
}
