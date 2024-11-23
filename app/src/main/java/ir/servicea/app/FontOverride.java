package ir.servicea.app;

import android.content.Context;
import android.graphics.Typeface;

import java.lang.reflect.Field;

public final class FontOverride {

    public static void setDefaultFont(Context context,
                                      String staticTypefaceFieldName, String font) {
        final Typeface regular = Typeface.createFromAsset(context.getAssets(),
                font);
        replaceFont(staticTypefaceFieldName, regular);
    }

    private static void replaceFont(String staticTypefaceFieldName,
                                    final Typeface newTypeface) {
        try {
            final Field staticField = Typeface.class
                    .getDeclaredField(staticTypefaceFieldName);
            staticField.setAccessible(true);
            staticField.set(null, newTypeface);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}