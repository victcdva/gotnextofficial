package com.gottnext.gotnextoficial;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;

import java.net.MalformedURLException;
import java.io.InputStream;
import java.net.URL;
import java.io.IOException;
public class Imagen {

    public static Drawable ImagenString(String Image){
        Drawable d = null;

        try {
            InputStream data = (InputStream)new URL(Image).getContent();
            d = Drawable.createFromStream(data, "");
        }
        catch (MalformedURLException ex) {
            Log.e("Error", ex.getMessage());
        }
        catch (IOException ex) {
            Log.e("Error", ex.getMessage());
        }

        return d;
    }
}
