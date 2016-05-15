package at.htlpinkafeld.projectmanager.service;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import at.htlpinkafeld.projectmanager.pojo.Activity;

/**
 * Created by tq on 16-02-01.
 */
public class ActivitySaver {

    private static String FILENAME = "activities.csv";

    public static void writeActivities(Context context, List<Activity> activities) {

        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            for (Activity a : activities) {
                fos.write((a.toCommaSeparatedLine() + "\n").getBytes());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String readRawActivities(Context context) {
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            byte[] buffer = new byte[32];
            StringBuilder s = new StringBuilder();
            int r = 0;
            do {
                r = fis.read(buffer, 0, 32);
                if (r >= 0) {
                    s.append(new String(buffer, 0, r));
                }
            } while (r >= 0);
            return s.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Activity> readActivities(Context context) {
        List<Activity> a = new ArrayList<>();
        String raw = readRawActivities(context);
        for (String s : raw.split("\n")) {
            a.add(Activity.fromCommaSeparatedLine(s));
        }
        return a;
    }

}
