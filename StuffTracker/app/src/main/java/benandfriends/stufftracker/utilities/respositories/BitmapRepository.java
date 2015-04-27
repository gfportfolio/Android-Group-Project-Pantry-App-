package benandfriends.stufftracker.utilities.respositories;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BitmapRepository {


    private static final String IMAGES_DIRECTORY = "stuffTrackerImages";
    private final Context context;


    public BitmapRepository(Context context) {
        this.context = context;
    }


    public String saveBitmap(Bitmap bitmap, String id) {
        ensureDirectoryAvailability();

        File directory = new File(context.getFilesDir(), IMAGES_DIRECTORY);
        File file = new File(directory, id);

        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, fileOutputStream);
        } catch (IOException e) {
            Log.d(IMAGES_DIRECTORY, e.getMessage());
        }

        return id;
    }


    public Bitmap retrieveBitmapForKey(String bitmapKey) {
        ensureDirectoryAvailability();

        File directory = new File(context.getFilesDir(), IMAGES_DIRECTORY);
        File file = new File(directory, bitmapKey);

        if (file.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                return BitmapFactory.decodeStream(fileInputStream);
            } catch (FileNotFoundException e) {
                Log.d(IMAGES_DIRECTORY, e.getMessage());
            }
        }

        return null;
    }


    private void ensureDirectoryAvailability() {
        File file = new File(context.getFilesDir() + File.separator + IMAGES_DIRECTORY);
        if (!file.exists()) {
            file.mkdirs();
        }
    }


}
