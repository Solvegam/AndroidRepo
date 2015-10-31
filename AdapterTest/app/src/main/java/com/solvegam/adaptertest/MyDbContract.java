package com.solvegam.adaptertest;

/**
 * Created by Stas on 31.10.2015.
 */
import android.provider.BaseColumns;

public final class MyDbContract {

    private MyDbContract() {}

    public static abstract class Test implements BaseColumns {
        public static final String TABLE_NAME = "test";

        public static final String NAME = "test_name";
        public static final String DESC = "test_desc";
    }
}