package com.example.bestbuy.cardviewtry;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Best Buy on 07-07-2017.
 */
//*********User-defined Adapter which extends RecyclerView.Adapter**********
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.DataObjectHolder> {
    ArrayList<DataProvider> DataSet;
    private static MyClickListener myClickListener; //MyClickListener is an interface defined internally later
    private int lastPos=-1; //This is the last position of the item...helpful in animation
    Context con;

    //************DataObjectHolder is needed to hold all the data received by this class in the form of DataSet********
    //In this we give Id and all to the widgets used inside the Card View i.e. activity_main
    public static class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView t1,t2;
        ImageView im1;
        public DataObjectHolder(View itemView) {
            super(itemView);

            t1=(TextView)itemView.findViewById(R.id.txt1);
            t2=(TextView)itemView.findViewById(R.id.txt2);
            im1=(ImageView)itemView.findViewById(R.id.img1);

            itemView.setOnClickListener(this);//This will be used on user clicks on the CardView
        }

        @Override
        public void onClick(View view) {
         //It is incomplete as if now...that is why app will crash when you click on any on e of the Cards
            myClickListener.onItemClick(getAdapterPosition(),view);
        }
    }


    //**********The Constructor for this class which is receiving data from the MainActivity*********
    public MyAdapter(ArrayList<DataProvider> newDataSet,Context con){
        this.con=con;
        DataSet=newDataSet;
    }

    //I don't know what this one does right no...will try to figure out soon plus right now it is not used anywhere
    public void setItemOnClickListener(MyClickListener myClickListener){
        this.myClickListener=myClickListener;
    }

    //onCreateViewHolder is a method in which we inflate our activity_main layout in other words the CardViews
    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main,parent,false);
        DataObjectHolder doh1=new DataObjectHolder(v);

        return doh1;  //It returns the DataObjectHolder to the places where they are called automatically
        //These methods are called automatically we don't have to call them
    }

    //************This is used to set the data in the widgets of the Cards**********
    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.t1.setText(DataSet.get(position).getText1());
        holder.t2.setText(DataSet.get(position).getText2());
        holder.im1.setImageResource(DataSet.get(position).getId());
        setAnimation(holder.itemView,position); //The Animation is set to the item here
    }

    //***********Animation is set up in this *********
    private void setAnimation(View viewToAnimate,int position){
        if(position>lastPos){
            Animation animation= AnimationUtils.loadAnimation(con,R.anim.slide_in_up); //These are all predefined but i had to add R.anim.slide_in_up in the res folder
            //slide_in_up is a simple xml file which gives the animation effect
            animation.setInterpolator(con,android.R.anim.overshoot_interpolator); //overshoot_interpolator is the effect with which they bounce a little
            animation.setDuration(700);
            viewToAnimate.startAnimation(animation);
            lastPos=position;
        }
    }

    @Override
    public int getItemCount() {
        return DataSet.size();
    }

    //***********This is the MyClickListener interface used above *****************
    public interface MyClickListener{
        public void onItemClick(int position,View v);
    }
}
