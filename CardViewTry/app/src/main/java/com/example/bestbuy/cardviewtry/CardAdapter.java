package com.example.bestbuy.cardviewtry;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.LinkedList;

/**
 * Created by Best Buy on 07-07-2017.
 */
//*********User-defined Adapter which extends RecyclerView.Adapter**********
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.DataObjectHolder> {
    LinkedList<DataProvider> DataSet;
    private int lastPos=-1; //This is the last position of the item...helpful in animation
    Context con;

    private SparseBooleanArray expandState=new SparseBooleanArray(); //It is a Array of Boolean which takes less memory



    //************DataObjectHolder is needed to hold all the data received by this class in the form of DataSet********
    //In this we give Id and all to the widgets used inside the Card View i.e. activity_main
    public static class DataObjectHolder extends RecyclerView.ViewHolder {

        TextView t1,t2,t3,t4;   //The number of TextViews have increased since Last Commit

        ImageView im1;

        LinearLayout upper,expandable;  //These are the two linear layouts which are placed in the Card


        public DataObjectHolder(View itemView) {
            super(itemView);

            t1=(TextView)itemView.findViewById(R.id.txt1);
            t2=(TextView)itemView.findViewById(R.id.txt2);
            im1=(ImageView)itemView.findViewById(R.id.img1);
            t3=(TextView)itemView.findViewById(R.id.txt3);
            t4=(TextView)itemView.findViewById(R.id.txt4);
            upper=(LinearLayout)itemView.findViewById(R.id.Upper);
            expandable=(LinearLayout)itemView.findViewById(R.id.expandableLayout);

        }
    }



    //**********The Constructor for this class which is receiving data from the MainActivity*********
    public CardAdapter(LinkedList<DataProvider> newDataSet,Context con){
        this.con=con;
        DataSet=newDataSet;
        for(int i=0;i<DataSet.size();i++){
            expandState.append(i,false);   //Here the expandState of all the Cards is initially set to false
        }
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
    public void onBindViewHolder(final DataObjectHolder holder, final int position) {
        holder.setIsRecyclable(false);

        holder.t1.setText(DataSet.get(position).getText1());
        holder.t2.setText(DataSet.get(position).getText2());
        holder.im1.setImageResource(DataSet.get(position).getId());
        holder.t3.setText(DataSet.get(position).getText3());   //The Data for the new TextViews is being set
        holder.t4.setText(DataSet.get(position).getText4());

        setAnimation(holder.itemView,position); //The Animation is set to the item here

        final boolean isExpanded=expandState.get(position);


        holder.expandable.setVisibility(isExpanded?View.VISIBLE:View.GONE); //associating a Visibility with the layout to use later

        holder.upper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                onClickButton(holder.expandable, holder.upper,  position); //OnClickButton method is called here
            }
        });
    }

    private void onClickButton(final LinearLayout l1,final LinearLayout l2, int position){

        if (l1.getVisibility() == View.VISIBLE){

            //This is the Collapse Section
            expandState.put(position, false);
            int finalHeight=l1.getHeight();

            ValueAnimator vAnim=slideAnimator(finalHeight,0,l1);  //the slideAnimator is called here to give the animation

            vAnim.addListener(new Animator.AnimatorListener() {
                //Three of these methods were not needed but could not be removed so are left empty
                @Override
                public void onAnimationStart(Animator animator) {                }

                @Override
                public void onAnimationEnd(Animator animator) {

                    //Visibility is set to GONE even though the height is set to 0 to give the animation
                    l1.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationCancel(Animator animator) {                }

                @Override
                public void onAnimationRepeat(Animator animator) {                }

            });
            vAnim.setDuration(400);
            vAnim.start();
        }
        else{

            //This is the Expand Section
            l1.setVisibility(View.VISIBLE);
            expandState.put(position, true);

            //The Specifications are required to give the needed animation
            final int widthSpec=View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
            final int heightSpec=View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);

            l1.measure(widthSpec,heightSpec);


            ValueAnimator vAnim=slideAnimator(0,l1.getMeasuredHeight(),l1);
            vAnim.setDuration(400);
            vAnim.start();
        }
    }



//**********slideAnimator method is used to set the animator and update the layout *************
    private ValueAnimator slideAnimator(int start, int end,final LinearLayout l1) {
        ValueAnimator animator=ValueAnimator.ofInt(start,end);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value=(Integer)valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams lay=l1.getLayoutParams();

                lay.height=value;
                l1.setLayoutParams(lay);
            }
        });
        return animator;
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

}
