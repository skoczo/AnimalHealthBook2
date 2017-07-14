package com.skoczo.animalheathbook;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by skoczo on 10.07.17.
 */

@RunWith(AndroidJUnit4.class)

public class AnimalItemViewTest {

    @Test
    public void onMeasureTest() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        AnimalItemView view = new AnimalItemView(appContext);

        Assert.assertEquals(0, view.getMeasuredWidth());
        Assert.assertEquals(0, view.getMeasuredHeight());

        view.measure(1073742177,20);

        Assert.assertEquals(353, view.getMeasuredWidth());
        Assert.assertEquals(353, view.getMeasuredHeight());
    }
}
