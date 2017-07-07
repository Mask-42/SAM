package com.example.bestbuy.cardviewtry;

/**
 * Created by Best Buy on 07-07-2017.
 */

public class DataProvider {

    private String text1,text2;
    private int id=R.drawable.logo;

    public int getId() {
        return id;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    DataProvider(String text1, String text2){
        this.text1=text1;
        this.text2=text2;
    }
}
