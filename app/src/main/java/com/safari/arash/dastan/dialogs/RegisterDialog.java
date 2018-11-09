package com.safari.arash.dastan.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.safari.arash.dastan.R;

public class RegisterDialog {
    private Dialog mDialog;
    private Activity activity;
    private Button save;

    public RegisterDialog(Activity activity) {
        this.activity = activity;
    }


    public void showDialog() {
        mDialog = new Dialog(activity, R.style.FullScreenDialog);
        mDialog.setContentView(R.layout.dialog_register);
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
//        save = (Button) mDialog.findViewById(R.id.save);
//        save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                saveClicked();
//            }
//        });
        mDialog.show();
    }


    private void saveClicked() {

    }
}
