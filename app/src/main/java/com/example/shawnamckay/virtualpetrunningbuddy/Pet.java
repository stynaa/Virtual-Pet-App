package com.example.shawnamckay.virtualpetrunningbuddy;

class Pet {
    private int happy;

    public Pet(){
        this.happy =50;
    }

    public int getHappy(){
        return this.happy;
    }

    public void setHappy(int happy){
        this.happy = happy;
    }

    public void feedPet(int foodCredit){
        int happyScore = getHappy();
        setHappy(happyScore+foodCredit);
    }

    public void decreaseHappy(){
        int happyScore = getHappy();
        happyScore=happyScore-5;
        setHappy(happyScore);
    }

}
