package com.ucicke.stand;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static com.ucicke.stand.R.string.back;
import static com.ucicke.stand.R.string.cal_voltage2;
import static com.ucicke.stand.R.string.menu_title;
import static com.ucicke.stand.R.string.next;
import static com.ucicke.stand.R.string.save;

public class InstrumentCalibrationFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_instrument_calibration, container, false);
    }

    @Override
    public void onLeftButtonClick() {
        Scene scene = new Scene(new MenuFragment(), getString(menu_title) + ":", getString(back), getString(next));
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, scene.getBaseFragment()).commit();
        mListener.onCurrentFragmentChanged(scene.getBaseFragment());
    }

    @Override
    public void onRightButtonClick() {
        Scene scene = new Scene(new VoltageCalibrationFragment(), getString(cal_voltage2), getString(back), getString(save));
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, scene.getBaseFragment()).commit();
        mListener.onCurrentFragmentChanged(scene.getBaseFragment());
    }
}
