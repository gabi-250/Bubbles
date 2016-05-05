package com.example.gabi.bubbles.BubblesModel;

import android.graphics.Color;

/**
 * Created by gabi on 05/05/16.
 */
public class Colors {

    public static final int[] colors = {Color.GREEN, Color.RED, Color.BLUE, Color.YELLOW};
    private static Colors ourInstance = new Colors();

    public static Colors getInstance() {
        return ourInstance;
    }

    private Colors() {
    }

}
