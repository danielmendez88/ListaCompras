package app.com.listacompras.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;


import app.com.listacompras.R;
import app.com.listacompras.asynctask.QRAsyncTask;

/**
 * Created by daniel on 15/11/2016.
 */

public class Frmcredencial extends Fragment {
    //declare some variables that we'll use in future
    View vista;
    ProgressBar pb;

    public Frmcredencial() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.frmcredencial, container, false);
        //cast ProgressBar
        pb = (ProgressBar) vista.findViewById(R.id.progressBar);
        pb.setVisibility(View.GONE);
        //create a Thread to avoid Exception
        final String tokenText = "596281";
        new QRAsyncTask(getContext(), vista, pb).execute(tokenText);
        //Here we put some code that show the QR code
        return vista;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

}
