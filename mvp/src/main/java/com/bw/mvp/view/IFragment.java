package com.bw.mvp.view;

import android.app.Activity;
import android.view.View;

import androidx.annotation.IdRes;

public interface IFragment extends IActivity{
    <T extends View> T findViewById(@IdRes int id);
}
