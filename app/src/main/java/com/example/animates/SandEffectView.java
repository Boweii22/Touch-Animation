package com.example.animates;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SandEffectView extends View {

    private List<Particle> particles;
    private Random random;
    private Paint paint;
    private Handler handler;
    private Runnable clearRunnable;

    public SandEffectView(Context context) {
        super(context);
        init();
    }

    public SandEffectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SandEffectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        particles = new ArrayList<>();
        random = new Random();
        paint = new Paint();
        paint.setColor(Color.parseColor("#D4B775")); // Sand color
        paint.setStyle(Paint.Style.FILL);

        handler = new Handler();
        clearRunnable = new Runnable() {
            @Override
            public void run() {
                particles.clear();
                invalidate();
            }
        };
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Draw particles
        for (Particle particle : particles) {
            canvas.drawCircle(particle.x, particle.y, particle.radius, paint);
        }

        // Update particle positions and fade them
        for (int i = particles.size() - 1; i >= 0; i--) {
            Particle particle = particles.get(i);
            particle.x += particle.vx;
            particle.y += particle.vy;
            particle.alpha -= 5; // Decrease opacity

            // Remove particle when it's too small or faded
            if (particle.alpha <= 0) {
                particles.remove(i);
            }
        }

        // Schedule next frame
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        if (event.getAction() == MotionEvent.ACTION_MOVE || event.getAction() == MotionEvent.ACTION_DOWN) {
            // Spawn particles at the touch point
            for (int i = 0; i < 10; i++) { // Spawn 10 particles at once
                float radius = random.nextFloat() * 5 + 3; // Random radius between 3 and 8
                float vx = random.nextFloat() * 4 - 2; // Random horizontal velocity
                float vy = random.nextFloat() * 4 - 2; // Random vertical velocity

                particles.add(new Particle(x, y, radius, vx, vy, 255)); // Full opacity
            }

            // Optional: Clear the particles after some delay (e.g., 1 second)
            handler.removeCallbacks(clearRunnable);
            handler.postDelayed(clearRunnable, 1000); // Clear after 1 second
        }

        return true;
    }

    private static class Particle {
        float x, y;
        float vx, vy;
        float radius;
        int alpha;

        Particle(float x, float y, float radius, float vx, float vy, int alpha) {
            this.x = x;
            this.y = y;
            this.radius = radius;
            this.vx = vx;
            this.vy = vy;
            this.alpha = alpha;
        }
    }
}

