package com.qianfeng.gamehelper.modules.me.widget;

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
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016/10/21.
 */
public class CircleDraw extends ImageView {

    private Context context;
    private Paint paint;
    private String TAG="circledraw";

    public CircleDraw(Context context) {
        this(context,null);
    }

    public CircleDraw(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CircleDraw(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
        Log.e(TAG, "circledraw: >>>>>>>>>" );
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
      this.context =context;
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.GREEN);
       paint.setDither(true);
        paint.setStyle(Paint.Style.FILL);
        Log.e(TAG, "init: 初始化" );
    }

    @Override
    protected void onDraw(Canvas canvas) {
       // super.onDraw(canvas);
        Drawable drawable = getDrawable();
        if(drawable!=null&& drawable instanceof BitmapDrawable){
            BitmapDrawable bitmapDrawable= (BitmapDrawable) drawable;
            Bitmap srcbitmap = bitmapDrawable.getBitmap();
            Bitmap cirbitmap=getCirbitmap(srcbitmap);
            Rect src=new Rect(0,0,cirbitmap.getWidth(),cirbitmap.getHeight());
            Rect dst=null;
            if(getWidth()>getHeight()){
                dst=new Rect(getWidth()/2-getHeight()/2,0,getWidth()/2+getHeight()/2,getHeight());
            }else {
                dst=new Rect(0,getHeight()/2-getWidth()/2,getWidth(),getHeight()/2+getWidth()/2);
            }
            canvas.drawBitmap(cirbitmap,src,dst,null);
            //invalidate();
            Log.e(TAG, "onDraw: 画圆形" );
        }else{
            super.onDraw(canvas);
            Log.e(TAG, "onDraw: 重写" );
        }
    }

    private Bitmap getCirbitmap(Bitmap srcbitmap) {
        int srcwidth = srcbitmap.getWidth();
        int srcheight = srcbitmap.getHeight();

        int dstwidth=Math.min(srcwidth,srcheight);
        int dstheight=dstwidth;

        Bitmap bitmap=Bitmap.createBitmap(dstwidth,dstheight, Bitmap.Config.ARGB_8888);

        Canvas canvas=new Canvas(bitmap);

        canvas.drawCircle(dstwidth/2,dstheight/2,dstwidth/2,paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        Rect src=new Rect(0,0,srcwidth,srcheight);

        Rect dst=new Rect(0,0,dstwidth,dstheight);
        canvas.drawBitmap(srcbitmap,src,dst,paint);
        Log.e(TAG, "getCirbitmap:>>>>>>>??????? " );

        return bitmap;
    }
}
