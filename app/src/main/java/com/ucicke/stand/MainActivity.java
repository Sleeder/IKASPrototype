package com.ucicke.stand;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements OnCurrentFragmentInteractionListener {

    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.date)
    TextView mDateTV;
    @BindView(R.id.time)
    TextView mTimeTV;
    @BindView(R.id.fragment_holder)
    FrameLayout mFragmentHolder;
    @BindView(R.id.left_button)
    Button mLeftButton;
    @BindView(R.id.right_button)
    Button mRightButton;

    private final SimpleDateFormat mDateFormat = new SimpleDateFormat("dd-MM-yy");
    private final SimpleDateFormat mTimeFormat = new SimpleDateFormat("HH:mm:ss");

    private BaseFragment mCurrentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mDateTV.setText(mDateFormat.format(System.currentTimeMillis()));
        Thread timeThread = new Thread(new CountDownRunner());
        timeThread.start();

        mCurrentFragment = new SchemeSelectionFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, mCurrentFragment).commit();
    }

    public void doWork() {
        runOnUiThread(new Runnable() {
            public void run() {
                try {
                    mTimeTV.setText(mTimeFormat.format(System.currentTimeMillis()));
                } catch (Exception e) {
                }
            }
        });
    }

    @OnClick({R.id.left_button, R.id.right_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_button:
                mCurrentFragment.onLeftButtonClick();
                break;
            case R.id.right_button:
                mCurrentFragment.onRightButtonClick();
                break;
        }
    }

    public void clearButton() {
        mRightButton.setEnabled(true);
        mRightButton.getBackground().clearColorFilter();
    }

    @Override
    public void onCurrentFragmentChanged(BaseFragment fragment) {
        mCurrentFragment = fragment;
        mTitle.setText(fragment.getTitle());
        mLeftButton.setText(fragment.getLeftButtonName());
        mRightButton.setText(fragment.getRightButtonName());

        if (mCurrentFragment instanceof OnInteractFragment) {
            ((OnInteractFragment) mCurrentFragment).onInteract(mLeftButton, mRightButton);
        }
    }

    private class CountDownRunner implements Runnable {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    doWork();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch (Exception e) {
                }
            }
        }
    }

}
