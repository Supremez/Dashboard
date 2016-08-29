package com.antonioleiva.materialeverywhere;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Log;

import java.io.IOException;

/**
 * <b>This class uses Threads</b>
 * An alternative to this class would be to use an ASyncTask
 *
 * @author blundell
 */
public class HeavyLifter {

    public static final int SUCCESS = 0;
    public static final int FAIL = 1;

    private final Context context;
    private final Handler callback;
    private WallpaperManager manager;

    /**
     * Setup the HeavyLifter
     *
     * @param context  the context we are running in - typically an activity
     * @param callback the handler you want to be notified when we finish doing an operation
     */
    public HeavyLifter(Context context, Handler callback) {
        this.context = context;
        this.callback = callback;
        this.manager = (WallpaperManager) context.getSystemService(Context.WALLPAPER_SERVICE);
    }

    /**
     * Takes a resource id of a file within our /res/drawable/ folder<br/>
     * It then spawns a new thread to do its work on<br/>
     * The reource is decoded and converted to a byte array<br/>
     * This array is passed to the system which can use it to set the phones wallpaper<br/>
     * Upon completion the callback handler is sent a message with eith {@link HeavyLifter#SUCCESS} or {@link HeavyLifter#FAIL}
     *
     * @param resourceId id of a file within our /res/drawable/ folder
     */
    public void setResourceAsWallpaper(final int resourceId) {
        new Thread() {
            @Override
            public void run() {
                try {
                    manager.setBitmap(getImage(resourceId));
                    callback.sendEmptyMessage(SUCCESS);
                } catch (IOException e) {
                    Log.e("Main", "Cant set wallpaper");
                    callback.sendEmptyMessage(FAIL);
                }
            }
        }.start();
    }

    /**
     * Decodes a resource into a bitmap, here it uses the convenience method 'BitmapFactory.decodeResource', but you can decode
     * using alternatives these will give you more control over the size and quality of the resource.
     * You may need certain size resources within each of your /hdpi/ /mdpi/ /ldpi/ folders in order
     * to have the quality and look you want on each different phone.
     */
    private Bitmap getImage(int resourceId) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resourceId, null);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, manager.getDesiredMinimumWidth(), manager.getDesiredMinimumHeight(), true);
        bitmap.recycle();
        bitmap = null;
        return scaledBitmap;
    }
}