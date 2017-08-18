package com.ucicke.stand;

public class Scene {
    BaseFragment mBaseFragment;

    public Scene(BaseFragment baseFragment, String title, String leftButtonName, String rightButtonName) {
        mBaseFragment = baseFragment;
        baseFragment.setTitles(title, leftButtonName, rightButtonName);
    }

    public BaseFragment getBaseFragment() {
        return mBaseFragment;
    }
}
