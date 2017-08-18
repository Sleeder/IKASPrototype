package com.ucicke.stand;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.ucicke.stand.R.string.back;
import static com.ucicke.stand.R.string.cal_instr;
import static com.ucicke.stand.R.string.go_over;
import static com.ucicke.stand.R.string.u1_v;
import static com.ucicke.stand.R.string.u2_v;
import static com.ucicke.stand.R.string.u3_v;
import static com.ucicke.stand.R.string.u4_v;

public class VoltageCalibrationFragment extends BaseFragment {
    private static final String VOLTAGE_1 = "Voltage1";
    private static final String VOLTAGE_2 = "Voltage2";
    private static final String VOLTAGE_3 = "Voltage3";
    private static final String VOLTAGE_4 = "Voltage4";
    @BindView(R.id.voltage1)
    EditText mVoltage1;
    @BindView(R.id.voltage2)
    EditText mVoltage2;
    @BindView(R.id.voltage3)
    EditText mVoltage3;
    @BindView(R.id.voltage4)
    EditText mVoltage4;
    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_voltage_calibration, container, false);
        unbinder = ButterKnife.bind(this, view);

        SharedPreferences preference = getActivity().getPreferences(Context.MODE_PRIVATE);
        String voltage1 = preference.getString(VOLTAGE_1, getString(u1_v));
        mVoltage1.setText(voltage1);
        String voltage2 = preference.getString(VOLTAGE_2, getString(u2_v));
        mVoltage2.setText(voltage2);
        String voltage3 = preference.getString(VOLTAGE_3, getString(u3_v));
        mVoltage3.setText(voltage3);
        String voltage4 = preference.getString(VOLTAGE_4, getString(u4_v));
        mVoltage4.setText(voltage4);

        return view;
    }

    @Override
    public void onLeftButtonClick() {
        Scene scene = new Scene(new InstrumentCalibrationFragment(), getString(cal_instr), getString(back), getString(go_over));
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, scene.getBaseFragment()).commit();
        mListener.onCurrentFragmentChanged(scene.getBaseFragment());
    }

    @Override
    public void onRightButtonClick() {
        SharedPreferences preference = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preference.edit();
        editor.putString(VOLTAGE_1, mVoltage1.getText().toString());
        editor.putString(VOLTAGE_2, mVoltage2.getText().toString());
        editor.putString(VOLTAGE_3, mVoltage3.getText().toString());
        editor.putString(VOLTAGE_4, mVoltage4.getText().toString());
        editor.commit();

        Toast.makeText(getActivity(), getString(R.string.saved), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
