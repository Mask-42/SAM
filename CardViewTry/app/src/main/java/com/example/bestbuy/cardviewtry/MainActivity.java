package com.example.bestbuy.cardviewtry;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;

import java.util.ArrayList;

import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class MainActivity extends AppCompatActivity {

    ArrayList<DataProvider> results;
    RecyclerView rv1;
    RecyclerView.Adapter my_adapter;
    RecyclerView.LayoutManager my_LM;
    Button b1;
    int j=6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_layout);

        rv1=(RecyclerView)findViewById(R.id.my_recycler);

        ItemTouchHelper ith= new ItemTouchHelper(createHelperCallback());
        ith.attachToRecyclerView(rv1);

        rv1.setHasFixedSize(true);

        my_LM = new LinearLayoutManager(this);
        rv1.setLayoutManager(my_LM);

        my_adapter = new MyAdapter(getDataSet());
        rv1.setAdapter(my_adapter);

        b1=(Button)findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataProvider temp= new DataProvider("Newly Added Main "+(j+1),"Newly Added Comment "+(j+1));
                j++;
                addItem(temp);
            }
        });
    }

    private ItemTouchHelper.Callback createHelperCallback() {
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback =
                new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                          RecyclerView.ViewHolder target) {
                        moveItem(viewHolder.getAdapterPosition(), target.getAdapterPosition());
                        return true;
                    }

                    @Override
                    public void onSwiped(final RecyclerView.ViewHolder viewHolder, int swipeDir) {
                        deleteItem(viewHolder.getAdapterPosition());
                    }
                };
        return simpleItemTouchCallback;
    }

    public void addItem(DataProvider dataObj){
        results.add(dataObj);
        my_adapter.notifyItemInserted(results.indexOf(dataObj));
    }

    public void deleteItem(int index){
        results.remove(index);
        my_adapter.notifyItemRemoved(index);
    }

    private void moveItem(int oldPos, int newPos) {
        DataProvider item=results.get(oldPos);
        results.remove(oldPos);
        results.add(newPos, item);
        my_adapter.notifyItemMoved(oldPos, newPos);
    }

    private ArrayList<DataProvider> getDataSet(){
        results= new ArrayList<DataProvider>();
        for(int i=0;i<6;i++){
            DataProvider obj=new DataProvider("The Main Text "+(i+1),"The Comment Text "+(i+1));
            results.add(i,obj);
        }
        return results;
    }
}