package com.solvegam.adaptertest;

import android.content.ContentResolver;
import android.net.Uri;

/**
 * Created by Stas on 07.11.2015.
 */
public class MyContract {

    public static final String AUTHORITY = "com.solvegam.adaptertest";
    public static  final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/items");

    public static final class Items extends MyDbContract.Test
    {
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "// like a table";
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "// like a row";


    }
}
