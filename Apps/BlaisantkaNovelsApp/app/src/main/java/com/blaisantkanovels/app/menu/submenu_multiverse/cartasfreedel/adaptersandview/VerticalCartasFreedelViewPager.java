package com.blaisantkanovels.app.menu.submenu_multiverse.cartasfreedel.adaptersandview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.blaisantkanovels.app.R;

import pl.droidsonroids.gif.GifImageView;

public class VerticalCartasFreedelViewPager extends ViewPager {

    public VerticalCartasFreedelViewPager(@NonNull Context context) {
        super(context);
        init();
    }

    public VerticalCartasFreedelViewPager(@NonNull Context context,
                                          @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {
        setPageTransformer(true, new VerticalPage());
        setOverScrollMode(OVER_SCROLL_NEVER);
    }

    private MotionEvent getIntercambioXY (MotionEvent ev) {
        float width = getWidth();
        float height = getHeight();

        float newX = (ev.getY() / height) * width;
        float newY = (ev.getX() / width) * height;

        ev.setLocation(newX, newY);
        return ev;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean interceptado = super.onInterceptTouchEvent(getIntercambioXY(ev));
        getIntercambioXY(ev);
        return interceptado;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(getIntercambioXY(ev));
    }


    private class VerticalPage implements PageTransformer {

        @Override
        public void transformPage(@NonNull View page, float position) {
            if (position <= -0.9) {
                page.setAlpha(0);
                GifImageView des = (GifImageView) page.findViewById(R.id.desliza);
                if (des != null) {
                    des.setVisibility(INVISIBLE);
                }
            }else if (position <= 1) {
                page.setAlpha(1);
                page.setTranslationX(page.getWidth() * -position);
                float yPosition = position * page.getHeight();
                page.setTranslationY(yPosition);
            }else{
                page.setAlpha(0);
            }
            System.out.println(position);
        }
    }
}
