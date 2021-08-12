package com.bw.Caohaigang20210806.entity;

import com.flyco.tablayout.listener.CustomTabEntity;

public class MyBtnEntity implements CustomTabEntity {
    private String titles;
    private int selectIcon;
    private int unselectIcon;

    public MyBtnEntity(String titles, int selectIcon, int unselectIcon) {
        this.titles = titles;
        this.selectIcon = selectIcon;
        this.unselectIcon = unselectIcon;
    }

    @Override
    public String getTabTitle() {
        return titles;
    }

    @Override
    public int getTabSelectedIcon() {
        return selectIcon;
    }

    @Override
    public int getTabUnselectedIcon() {
        return unselectIcon;
    }
}
