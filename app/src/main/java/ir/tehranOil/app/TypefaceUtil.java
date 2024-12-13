package ir.tehranOil.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;

import java.lang.reflect.Field;

public class TypefaceUtil {

	@SuppressLint("LongLogTag")
	public static void overrideFont(Context context) {
		try {
			final Typeface customFontTypeface = Typeface.createFromAsset(context.getAssets(), G.font);

			final Field defaultFontTypefaceField1 = Typeface.class.getDeclaredField("SANS_SERIF");
			final Field defaultFontTypefaceField = Typeface.class.getDeclaredField("SERIF");
			defaultFontTypefaceField.setAccessible(true);
			defaultFontTypefaceField.set(null, customFontTypeface);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}