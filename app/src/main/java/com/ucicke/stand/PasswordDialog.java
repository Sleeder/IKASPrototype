package com.ucicke.stand;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class PasswordDialog extends DialogFragment {
    @BindView(R.id.password)
    EditText mPassword;
    @BindView(R.id.ok)
    Button mOk;
    Unbinder unbinder;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_password, container, false);

        unbinder = ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.ok)
    public void onViewClicked() {
        final Fragment fragment = getTargetFragment();
        if (fragment instanceof OnInteractPassword) {
            if (mPassword.getText().toString().equals(getString(R.string.pass))) {
                ((OnInteractPassword) fragment).onSuccess();
            } else {
                ((OnInteractPassword) fragment).onFailed();
            }

        }
        dismiss();
    }

    public interface OnInteractPassword {
        void onSuccess();

        void onFailed();
    }

}
