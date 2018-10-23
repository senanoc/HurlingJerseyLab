package com.example.android.hurlingjerseylab;

import java.util.HashMap;
import java.util.Random;

public class Jersey {

    private String mPlayerName = "Ireland";
    private int mPlayerId = 16;
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


    public Jersey() {


        mJerseyType = getRandomJerseyName();
        mImageResourceId = sJerseyImageMap.get(mJerseyType);

        if (mJerseyType == "Green Hurling Jersey") {
            mJerseyTypeGreen = true;
            mJerseyTypePurple = false;
        }

        if (mJerseyType == "Purple Hurling Jersey") {
            mJerseyTypeGreen = false;
            mJerseyTypePurple = true;
        }

    }

    public Jersey(String name, Integer number) {

        setName(name);
        setPlayerNumber(number);

        mJerseyType = getRandomJerseyName();
        mImageResourceId = sJerseyImageMap.get(mJerseyType);

        if (mJerseyType == "Green Hurling Jersey") {
            mJerseyTypeGreen = true;
            mJerseyTypePurple = false;
        }

        if (mJerseyType == "Purple Hurling Jersey") {
            mJerseyTypeGreen = false;
            mJerseyTypePurple = true;
        }

    }

    public static Jersey getDefaultJersey() {
        return new Jersey();
    }

    ;


    private String getRandomJerseyName() {
        Object[] jerseys = Jersey.sJerseyImageMap.keySet().toArray();
        return (String) jerseys[random.nextInt(jerseys.length)];
    }

    public String getName() {
        return mPlayerName;
    }

    public int getPlayerNumber() {
        return mPlayerId;
    }

    public void setName(String PlayerName) {
        mPlayerName = PlayerName;
    }

    public void setPlayerNumber(int PlayerNumber) {
        mPlayerId = PlayerNumber;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }
}



