//written in reference to http://www.gadgetsaint.com/android/create-pedometer-step-counter-android/#.WyGI8WRKgy7

package com.example.christinalo.myapplication;

// Will listen to step alerts
public interface StepListener {
    public void step(long timeNs);
}
