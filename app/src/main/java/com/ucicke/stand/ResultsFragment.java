package com.ucicke.stand;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static com.ucicke.stand.R.string.next;
import static com.ucicke.stand.R.string.range;
import static com.ucicke.stand.R.string.stop;

public class ResultsFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_results, container, false);
    }

    @Override
    public void onLeftButtonClick() {
        Scene scene = new Scene(new SchemeSelectionFragment(), getString(R.string.connection_scheme), getString(R.string.menu_title), getString(next));
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, scene.getBaseFragment()).commit();
        mListener.onCurrentFragmentChanged(scene.getBaseFragment());
    }

    @Override
    public void onRightButtonClick() {
        Scene scene = new Scene(new RangeDetectionFragment(), getString(range), getString(stop), getString(next));
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, scene.getBaseFragment()).commit();
        mListener.onCurrentFragmentChanged(scene.getBaseFragment());
    }
}
