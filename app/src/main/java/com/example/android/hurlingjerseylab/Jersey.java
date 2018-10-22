package com.example.android.hurlingjerseylab;

import java.util.HashMap;
import java.util.Random;

public class Jersey {

    private String mPlayerName = "ANDROID";
    private int mPlayerId = 17;
    private String mJerseyType;
    private boolean mJerseyTypeGreen;
    private boolean mJerseyTypePurple;

    private int mImageResourceId;
    private Random random = new Random();

    public static final HashMap<String, Integer> sJerseyImageMap;

    static {
        sJerseyImageMap = new HashMap<>();
        sJerseyImageMap.put("Green Hurling Jersey", R.drawable.green_jersey);
        sJerseyImageMap.put("Purple Hurling Jersey", R.drawable.purple_jersey);
    }



}
