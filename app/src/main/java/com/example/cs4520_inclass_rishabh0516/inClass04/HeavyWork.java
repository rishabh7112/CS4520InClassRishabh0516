// Rishabh Sahu
// Assignment 4

package com.example.cs4520_inclass_rishabh0516.inClass04;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class HeavyWork implements Runnable {
    public final static int STATUS_PROGRESS = 0x002;
    public final static int STATUS_ARRAY = 0x007;
    public final static String KEY_PROGRESS = "0x004";
    public final static String KEY_ARRAY = "0x008";
    public final static int STATUS_START = 0x001;
    public final static int STATUS_END = 0x003;
    public final static String KEY_START = "0x009";
    public final static String KEY_END = "0x011";


    private int complexity;
    private Handler messageQueue;

    public HeavyWork(int complexity, Handler messageQueue){
        this.complexity = complexity;
        this.messageQueue = messageQueue;

    }


    static final int COUNT = 9000000;
    private ArrayList<Double> getArrayNumbers(int n){
        ArrayList<Double> returnArray = new ArrayList<>();
        Message startMessage = new Message();
        startMessage.what = STATUS_START;
        messageQueue.sendMessage(startMessage);
        for (int i=0; i<n; i++){
            returnArray.add(getNumber());
            Message progressMessage = new Message();
            progressMessage.what = STATUS_PROGRESS;
            Bundle bundle = new Bundle();
            bundle.putInt(KEY_PROGRESS, i);
            progressMessage.setData(bundle);
            messageQueue.sendMessage(progressMessage);
        }

        Message endMessage = new Message();
        endMessage.what = STATUS_END;
        messageQueue.sendMessage(endMessage);

        return returnArray;
    }

    static double getNumber(){
        double num = 0;
        Random ran = new Random();
        for(int i=0;i<COUNT; i++){
            num = num + (Math.random()*ran.nextDouble()*100+ran.nextInt(50))*1000;
        }
        return num / ((double) COUNT);
    }

    @Override
    public void run() {
        Message arrayMessage = new Message();
        arrayMessage.what = STATUS_ARRAY;
        ArrayList<Double> numbers = getArrayNumbers(this.complexity);
        double[] arrayNumbers  = numbers.stream().mapToDouble(j -> j).toArray();
        Bundle bundle = new Bundle();
        bundle.putDoubleArray(KEY_ARRAY, arrayNumbers);
        arrayMessage.setData(bundle);
        messageQueue.sendMessage(arrayMessage);

    }


}
