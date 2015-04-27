package benandfriends.stufftracker.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;


public final class RoundImageView extends ImageView {

    private Bitmap roundBitmap;


    public RoundImageView(Context context) {
        super(context);
    }


    public RoundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public RoundImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        int width = getWidth(), height = getHeight();

        boolean valid = (null != drawable && width != 0 && height != 0);
        if (!valid) {
            return;
        }

        if (null == this.roundBitmap) {
            Bitmap b = ((BitmapDrawable)drawable).getBitmap();
            Bitmap bCopy = b.copy(Bitmap.Config.ARGB_8888, true);

            this.roundBitmap = this.getCroppedBitmap(bCopy, width);
        }

        canvas.drawBitmap(this.roundBitmap, 0, 0, null);
    }


    private Bitmap getCroppedBitmap(Bitmap bmp, int radius) {
        Bitmap newBmp;
        if (bmp.getWidth() != radius || bmp.getHeight() != radius) {
            newBmp = Bitmap.createScaledBitmap(bmp, radius, radius, false);
        } else {
            newBmp = bmp;
        }

        Bitmap output = Bitmap.createBitmap(newBmp.getWidth(), newBmp.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, radius, radius);

        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.parseColor("#BAB399"));
        canvas.drawCircle(radius / 2 + 0.7f,
                radius / 2 + 0.7f, radius / 2 + 0.1f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(newBmp, rect, rect, paint);

        return output;
    }


}
