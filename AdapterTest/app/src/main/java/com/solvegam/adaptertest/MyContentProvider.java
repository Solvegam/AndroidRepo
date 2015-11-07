package com.solvegam.adaptertest;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.net.URI;

/**
 * Created by Stas on 07.11.2015.
 */
public class MyContentProvider extends ContentProvider {

    private MySQLiteHelper helper;

    public static final int ITEM_LIST = 1;
    public static final int ITEM_ID = 2;

    public final static UriMatcher URI_MATCHER;

    static
    {
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        URI_MATCHER.addURI(MyContract.AUTHORITY,"items",ITEM_LIST);
        URI_MATCHER.addURI(MyContract.AUTHORITY,"items/#",ITEM_ID);
    }

    @Override
    public boolean onCreate() {
        helper = new MySQLiteHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        switch (URI_MATCHER.match(uri))
        {
            case ITEM_LIST:
                break;
            case ITEM_ID:

                int itemIndex = Integer.parseInt(uri.getLastPathSegment());

                if (TextUtils.isEmpty(selection))
                {
                    selection = MyContract.Items._ID + " = " + itemIndex;
                }
                else
                {
                    selection = selection + " AND " +  MyContract.Items._ID + " = " + itemIndex;
                }
                break;
            default:
                throw new IllegalArgumentException("The unknown type of URI: " + uri);
        }
        Cursor cursor = helper.getWritableDatabase().query(MyContract.Items.TABLE_NAME,projection,selection,selectionArgs,null,null,null);
        cursor.setNotificationUri(getContext().getContentResolver(),MyContract.CONTENT_URI);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {

        switch(URI_MATCHER.match(uri))
        {
            case ITEM_LIST:
                return MyContract.Items.CONTENT_TYPE;

            case ITEM_ID:
                return MyContract.Items.CONTENT_ITEM_TYPE;

            default:
                throw new IllegalArgumentException("This URI does not match any resources in this app: "+ uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {

        if (URI_MATCHER.match(uri) != ITEM_LIST)
        {
                throw new IllegalArgumentException("You can't insert data with that URI. It points to whole set of data "+ uri);
        }

        long id = helper.getWritableDatabase().insert(MyContract.Items.TABLE_NAME,null,values);

        Uri newUri = ContentUris.withAppendedId(MyContract.CONTENT_URI, id);

        //посылает сигнал ресолверу, что новый ресур добавлен
        getContext().getContentResolver().notifyChange(newUri,null);

        return newUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        switch(URI_MATCHER.match(uri))
        {
            case ITEM_LIST:
                throw new IllegalArgumentException("You can't delete this data, because it is whole dataset:" + uri);
            case ITEM_ID:
                int itemIndex = Integer.parseInt(uri.getLastPathSegment());

                if (TextUtils.isEmpty(selection))
                {
                    selection = MyContract.Items._ID + "=" + itemIndex;
                }
                else
                {
                    selection = selection + " AND " + MyContract.Items._ID + "=" + itemIndex;
                }
                break;
            default:
                throw new IllegalArgumentException("This URI does not match any resources in this app: "+ uri);
        }

        int deletedRows = helper.getWritableDatabase().delete(MyContract.Items.TABLE_NAME,selection,selectionArgs);

        getContext().getContentResolver().notifyChange(uri,null);

        return deletedRows;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        switch (URI_MATCHER.match(uri))
        {
            case ITEM_LIST:
                throw new IllegalArgumentException("You can't update this data, because it is whole dataset:" + uri);
            case ITEM_ID:
                String itemIndex = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection))
                {
                    selection = MyContract.Items._ID + " = " + itemIndex;
                }
                else
                {
                    selection = selection + " AND " + MyContract.Items._ID + "=" + itemIndex;
                }

                break;
            default:
                throw new IllegalArgumentException("This URI does not match any resources in this app: "+ uri);
        }

        int updatedCount = helper.getWritableDatabase().update(MyContract.Items.TABLE_NAME,values,selection,selectionArgs);

        getContext().getContentResolver().notifyChange(uri,null);

        return updatedCount;
    }
}
