package app.com.listacompras.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import app.com.listacompras.R;

/**
 * Created by daniel on 16/02/2017.
 */

public class Frmoptions extends Fragment {
    View v;
    private RecyclerView rvOptions;

    public Frmoptions() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.frmcardoptions, container, false);
        rvOptions = (RecyclerView) v.findViewById(R.id.rvoptions);
        //set the context and in this part of the code set getActivity
        LinearLayoutManager llM = new LinearLayoutManager(getActivity());
        llM.setOrientation(LinearLayoutManager.VERTICAL);
        rvOptions.setLayoutManager(llM);
        return v;

    }
}
