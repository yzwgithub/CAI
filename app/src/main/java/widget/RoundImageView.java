package widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 *
 * 自定义圆形ImageView
 */

public class RoundImageView extends android.support.v7.widget.AppCompatImageView {
    private Paint paint;

    public RoundImageView(Context context) {
        this(context,null);
    }

    public RoundImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RoundImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint=new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable=getDrawable();
        if (null!=drawable){
            Bitmap bitmap= ((BitmapDrawable)drawable).getBitmap();
            Bitmap bitmap1=getCircleBitmap(bitmap,14);
            final Rect rectSrc=new Rect(0,0,bitmap1.getWidth(),bitmap1.getHeight());
            final Rect rectDest=new Rect(0,0,getWidth(),getHeight());
            paint.reset();
            canvas.drawBitmap(bitmap1,rectSrc,rectDest,paint);
        }else {
            super.onDraw(canvas);
        }
    }

    /**
     * 设置图片的参数
     * @param bitmap
     * @param pixels
     * @return
     */
    private Bitmap getCircleBitmap(Bitmap bitmap,int pixels){
        final int color=0xff424242;
        final Rect rect=new Rect(0,0,bitmap.getWidth(),bitmap.getHeight());
        Bitmap output=Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(output);
        paint.setAntiAlias(true);
        canvas.drawARGB(0,0,0,0);
        paint.setColor(color);
        int x=bitmap.getWidth();
        canvas.drawCircle(x/2,x/2,x/2,paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap,rect,rect,paint);
        return output;
    }
}
