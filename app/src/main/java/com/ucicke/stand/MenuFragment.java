package com.ucicke.stand;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import static com.ucicke.stand.R.string.back;
import static com.ucicke.stand.R.string.cal_instr;
import static com.ucicke.stand.R.string.go_over;

public class MenuFragment extends BaseFragment implements PasswordDialog.OnInteractPassword {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onLeftButtonClick() {
        Scene scene = new Scene(new SchemeSelectionFragment(), getString(R.string.connection_scheme), getString(R.string.menu_title), getString(R.string.next));
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, scene.getBaseFragment()).commit();
        mListener.onCurrentFragmentChanged(scene.getBaseFragment());
    }

    @Override
    public void onRightButtonClick() {
        PasswordDialog pd = new PasswordDialog();
        pd.setTargetFragment(this, 0);
        pd.show(getActivity().getSupportFragmentManager(), "");
    }

    @Override
    public void onSuccess() {
        Scene scene = new Scene(new InstrumentCalibrationFragment(), getString(cal_instr), getString(back), getString(go_over));
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, scene.getBaseFragment()).commit();
        mListener.onCurrentFragmentChanged(scene.getBaseFragment());
    }

    @Override
    public void onFailed() {
        Toast.makeText(getActivity(), getString(R.string.wrong_pass), Toast.LENGTH_SHORT).show();
    }
}
