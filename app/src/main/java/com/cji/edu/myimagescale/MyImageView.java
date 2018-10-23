package com.cji.edu.myimagescale;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

public class MyImageView extends View {

    private Drawable image;
    private ScaleGestureDetector gestureDetector;
    private  float scale = 1.0f;

    public MyImageView(Context context) {
        super(context);
        image = context.getDrawable(R.drawable.dog2);
        setFocusable(true);
        image.setBounds(0,0, image.getIntrinsicHeight(), image.getIntrinsicWidth());
        gestureDetector = new ScaleGestureDetector(context,
                new ScaleGestureDetector.SimpleOnScaleGestureListener(){
                    @Override
                    public boolean onScale(ScaleGestureDetector detector) {

                        // 제스처에 따라 크기가 변함.
                        scale *= detector.getScaleFactor();

                        // 크기 제한
                        if(scale < 0.1f) scale = 0.1f;
                        if(scale > 10.0f) scale = 10.0f;

                        return true;
                    }

                    @Override
                    public boolean onScaleBegin(ScaleGestureDetector detector) {
                        return true;
                    }

                    @Override
                    public void onScaleEnd(ScaleGestureDetector detector) {

                    }
                }
        );
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.scale(scale, scale);;
        image.draw(canvas);
        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        invalidate();
        return true;
    }
}
