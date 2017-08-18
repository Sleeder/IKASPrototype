package com.ucicke.stand;

import android.content.Context;
import android.support.v4.app.Fragment;


public abstract class BaseFragment extends Fragment implements OnButtonsInteractionListener {
    private String mTitle;
    private String mLeftButtonName;
    private String mRightButtonName;
    protected OnCurrentFragmentInteractionListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnCurrentFragmentInteractionListener) {
            mListener = (OnCurrentFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnCurrentFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    protected void setTitles(String title, String leftButtonName, String rightButtonName) {
        mTitle = title;
        mLeftButtonName = leftButtonName;
        mRightButtonName = rightButtonName;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getLeftButtonName() {
        return mLeftButtonName;
    }

    public String getRightButtonName() {
        return mRightButtonName;
    }

    @Override
    public abstract void onLeftButtonClick();

    @Override
    public abstract void onRightButtonClick();
}
