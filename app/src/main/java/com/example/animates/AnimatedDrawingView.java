package com.example.animates;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

public class AnimatedDrawingView extends View {

    private Paint paint;
    private Path path;
    private PathMeasure pathMeasure;
    private float pathLength;
    private float animatedProgress;

    public AnimatedDrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        // Initialize paint settings
        paint = new Paint();
        paint.setColor(0xFF6200EE); // Purple color
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(8f);
        paint.setAntiAlias(true);

        // Initialize path and shape (star shape for this example)
        path = new Path();
        path.moveTo(300, 500);
        path.lineTo(400, 200);
        path.lineTo(500, 500);
        path.lineTo(200, 300);
        path.lineTo(600, 300);
        path.close();

        // Measure the path length for the animation
        pathMeasure = new PathMeasure(path, false);
        pathLength = pathMeasure.getLength();

        // Start animation
        startAnimation();
    }

    private void startAnimation() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, "animatedProgress", 0f, 1f);
        animator.setDuration(2000); // 2 seconds
        animator.start();
    }

    // Setter used by ObjectAnimator to animate the drawing
    public void setAnimatedProgress(float progress) {
        this.animatedProgress = progress;
        invalidate(); // Redraw the view with the updated progress
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Create a partial path based on the animated progress
        Path partialPath = new Path();
        pathMeasure.getSegment(0, animatedProgress * pathLength, partialPath, true);

        // Draw the partial path to animate the drawing process
        canvas.drawPath(partialPath, paint);
    }
}
