package com.ucicke.stand;

import android.graphics.LightingColorFilter;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.ucicke.stand.R.string.back;
import static com.ucicke.stand.R.string.selection_limits;

public class RangeDetectionFragment extends BaseFragment implements OnInteractFragment {

    @BindView(R.id.detection_timer)
    TextView mDetectionTimer;
    @BindView(R.id.detection_progress_bar)
    ProgressBar mDetectionProgressBar;
    Unbinder unbinder;

    private final int[] progressBarStatus = {0};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_range_detection, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onLeftButtonClick() {
        if (progressBarStatus[0] >= 0) {
            progressBarStatus[0] = -10;
            mDetectionTimer.setText(getString(R.string.stopped));
        } else {
            Scene scene = new Scene(new SelectionLimitsFragment(), getString(selection_limits), getString(back), getString(R.string.start_measure));
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, scene.getBaseFragment()).commit();
            ((MainActivity) getActivity()).clearButton();
            mListener.onCurrentFragmentChanged(scene.getBaseFragment());
        }

    }

    @Override
    public void onRightButtonClick() {
        Scene scene = new Scene(new ResultsFragment(), getString(R.string.results), getString(R.string.choose_scheme), getString(R.string.repeat));
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, scene.getBaseFragment()).commit();
        mListener.onCurrentFragmentChanged(scene.getBaseFragment());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onInteract(final Button buttonLeft, final Button buttonRight) {
        final Handler handler = new Handler();

        buttonLeft.getBackground().setColorFilter(new LightingColorFilter(0xFFFFFFFF, 0xFFFF0000));

        buttonRight.setEnabled(false);
        buttonRight.getBackground().setColorFilter(new LightingColorFilter(0xFFFFFFFF, 0xFFAA0000));

        new Thread(new Runnable() {
            public void run() {
                while (progressBarStatus[0] < 100 && progressBarStatus[0] >= 0) {
                    try {
                        Thread.sleep(50);
                        progressBarStatus[0]++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.post(new Runnable() {
                        public void run() {
                            if (mDetectionProgressBar != null) {
                                mDetectionProgressBar.setProgress(progressBarStatus[0]);
                            }
                            if (mDetectionTimer != null && progressBarStatus[0] >= 0) {
                                mDetectionTimer.setText("Время измерения:" + progressBarStatus[0] / 20);
                            }
                            if (progressBarStatus[0] == 100) {
                                buttonLeft.getBackground().clearColorFilter();

                                buttonRight.setEnabled(true);
                                buttonRight.getBackground().clearColorFilter();
                            } else if (progressBarStatus[0] < 0) {
                                buttonLeft.getBackground().clearColorFilter();
                            }
                        }
                    });
                }
            }
        }).start();
    }
}
