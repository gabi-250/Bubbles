package com.example.gabi.bubbles.BubblesModel;

import android.graphics.Color;

/**
 * Represents a container for the allowed colors.
 */
public class Colors {

    public static final int[] colors = {Color.GREEN, Color.RED, Color.BLUE, Color.YELLOW, Color.parseColor("#f9845b")};
    private static Colors ourInstance = new Colors();

    public static Colors getInstance() {
        return ourInstance;
    }

    private Colors() {
    }

}
