package app.com.listacompras.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.com.listacompras.R;

/**
 * Created by daniel on 15/11/2016.
 */

public class Frmcredencial extends Fragment {
    View vista;
    public Frmcredencial() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.frmcredencial, container, false);

        return vista;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
