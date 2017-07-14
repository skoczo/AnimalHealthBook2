package com.skoczo.animalheathbook;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.LinearLayout;

/**
 * Created by skoczo on 10.07.17.
 */

public class AnimalItemView extends LinearLayout implements Checkable {
    private boolean checked;

    public AnimalItemView(Context context) {
        super(context);
    }

    public AnimalItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AnimalItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    @Override
    public void setChecked(boolean b) {
        checked = b;
        refreshDrawableState();
    }

    @Override
    public boolean isChecked() {
        return checked;
    }

    @Override
    public void toggle() {
        setChecked(!checked);
    }
}
