package com.example.bestbuy.cardviewtry;

/**
 * Created by Best Buy on 07-07-2017.
 */
//***************This is just a simple class which is used to supply data in the form of its objects***********
public class DataProvider {

    private String text1,text2,text3,text4;
    private int id=R.drawable.logo;

    public int getId() {
        return id;
    }

    public String getText3() {
        return text3;
    }

    public String getText4() {
        return text4;
    }

    public String getText1() {
        return text1;
    }


    public String getText2() {
        return text2;
    }


    DataProvider(String text1, String text2, String text3,String text4){
        this.text1=text1;
        this.text2=text2;
        this.text3=text3;
        this.text4=text4;
    }
}
