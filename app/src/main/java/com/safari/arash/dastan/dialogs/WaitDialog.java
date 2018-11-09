package com.safari.arash.dastan.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.extra.widget.IranSansTextView;
import com.safari.arash.dastan.R;

public class WaitDialog {
    private Dialog mDialog;
    private Activity activity;
    private Button save;
    long timeSWeit;
    public WaitDialog(Activity activity) {
        this.activity = activity;
    }


    public void showDialog(long timeSWeit) {
        mDialog = new Dialog(activity, R.style.FullScreenDialog);
        mDialog.setContentView(R.layout.dialog_wait);
        this.timeSWeit = timeSWeit;
//        mDialog.getWindow().getAttributes().windowAnimations= R.style.DialogSlideUpDown;
        Drawable d = new ColorDrawable(Color.BLACK);
        d.setAlpha(200);
        mDialog.getWindow().setBackgroundDrawable(d);
        ImageView closeBtn = mDialog.findViewById(R.id.close_btn);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
            }
        });
        final IranSansTextView minutes = mDialog.findViewById(R.id.minutes);
        final IranSansTextView seconds = mDialog.findViewById(R.id.seconds);
        long tsLong = timeSWeit - System.currentTimeMillis()/1000 ;
        new CountDownTimer(tsLong*1000, 1000) {

            public void onTick(long millisUntilFinished) {
                long minL = getMinutes(millisUntilFinished / 1000);
                String minS=minL+"";
                if(minL<10)
                    minS="0"+minS;
                minutes.setText(minS);
                long sesL = getSeconds(millisUntilFinished / 1000);
                String seS=sesL+"";
                if(sesL<10)
                    seS="0"+seS;
                seconds.setText(seS);
            }

            public void onFinish() {
                mDialog.dismiss();
            }
        }.start();
//        save = (Button) mDialog.findViewById(R.id.save);
//        save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                saveClicked();
//            }
//        });
        mDialog.show();
    }

    private long getSeconds(long seconds) {
        return seconds%60;
    }

    private long getMinutes(long seconds) {
        return (seconds-seconds%60)/60;
    }


    private void saveClicked() {

    }
}
