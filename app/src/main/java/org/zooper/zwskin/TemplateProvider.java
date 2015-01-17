package org.zooper.zwskin;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;

import java.io.FileNotFoundException;

public class TemplateProvider extends ContentProvider {
    public int delete(Uri paramUri, String paramString, String[] paramArrayOfString) {
        return 0;
    }

    public String getType(Uri paramUri) {
        return null;
    }

    public Uri insert(Uri paramUri, ContentValues paramContentValues) {
        return null;
    }

    public boolean onCreate() {
        return false;
    }

    public AssetFileDescriptor openAssetFile(Uri paramUri, String paramString)
            throws FileNotFoundException {
        if (paramUri.getPathSegments().size() > 0)
            try {
                final String name = paramUri.getPath().substring(1);
                AssetFileDescriptor localAssetFileDescriptor = getContext().getAssets().openFd(name);
                return localAssetFileDescriptor;
            } catch (Throwable localThrowable) {
                return null;
            }
        return null;
    }

    public Cursor query(Uri paramUri, String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2) {
        MatrixCursor cursor = new MatrixCursor(new String[]{"string"});
        try {
            final String path = paramUri.getPath().substring(1);
            for (String s : getContext().getAssets().list(path)) {
                cursor.newRow().add(s);
                cursor.moveToNext();
            }
            cursor.moveToFirst();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cursor;
    }

    public int update(Uri paramUri, ContentValues paramContentValues, String paramString, String[] paramArrayOfString) {
        return 0;
    }
}
