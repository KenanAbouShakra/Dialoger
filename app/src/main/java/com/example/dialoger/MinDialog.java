package com.example.dialoger;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.dialoger.R;

public class MinDialog extends DialogFragment {
    private MittInterface callback;
    public interface MittInterface {
        public void onYesClick();
        public void onNoClick();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            callback = (MittInterface) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException("Kallende klasse må implementere interfacet!");
        }
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new
            AlertDialog.Builder(getActivity()).setTitle(R.string.tittel).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    callback.onYesClick(); } }).setNegativeButton(R.string.ikkeok,
                new DialogInterface.OnClickListener() {
                        public void onClick (DialogInterface dialog,int whichButton){
                            callback.onNoClick(); } }) .create(); }
}
