package com.shakib.icenine;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by Chakib on 06/05/2018.
 */

public class ModePick extends DialogFragment {
    public MainActivity mAct;

    String[] mModesVals = {MainActivity.MOBILE_BASIC, MainActivity.BASIC_FREE, MainActivity.MOBILE_ADVANCED};
    String[] mModes = {"Mobile Basic", "Basic Free", "Mobile Advanced"};
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Pick connection mode")
                .setItems(mModes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        mAct.setMode(mModesVals[which]);
                        dismiss();
                    }
                });
        return builder.create();
    }

}
