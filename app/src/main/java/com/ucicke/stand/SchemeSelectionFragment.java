package com.ucicke.stand;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static com.ucicke.stand.R.string.back;
import static com.ucicke.stand.R.string.next;
import static com.ucicke.stand.R.string.selection_limits;

public class SchemeSelectionFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_scheme_selection, container, false);
    }

    @Override
    public void onLeftButtonClick() {
        Scene scene = new Scene(new MenuFragment(), getString(R.string.menu_title) + ":", getString(back), getString(next));
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, scene.getBaseFragment()).commit();
        mListener.onCurrentFragmentChanged(scene.getBaseFragment());
    }

    @Override
    public void onRightButtonClick() {
        Scene scene = new Scene(new IndividualFragment(), getString(selection_limits), getString(back), getString(next));
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, scene.getBaseFragment()).commit();
        mListener.onCurrentFragmentChanged(scene.getBaseFragment());
    }
}
