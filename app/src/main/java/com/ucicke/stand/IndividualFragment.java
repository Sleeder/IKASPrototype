package com.ucicke.stand;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static com.ucicke.stand.R.string.back;
import static com.ucicke.stand.R.string.connection_scheme;
import static com.ucicke.stand.R.string.menu_title;
import static com.ucicke.stand.R.string.next;
import static com.ucicke.stand.R.string.selection_limits;
import static com.ucicke.stand.R.string.start_measure;

public class IndividualFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_individual, container, false);
    }

    @Override
    public void onLeftButtonClick() {
        Scene scene = new Scene(new SchemeSelectionFragment(), getString(connection_scheme), getString(menu_title), getString(next));
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, scene.getBaseFragment()).commit();
        mListener.onCurrentFragmentChanged(scene.getBaseFragment());
    }
    
    @Override
    public void onRightButtonClick() {
        Scene scene = new Scene(new SelectionLimitsFragment(), getString(selection_limits), getString(back), getString(start_measure));
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, scene.getBaseFragment()).commit();
        mListener.onCurrentFragmentChanged(scene.getBaseFragment());
    }
}
