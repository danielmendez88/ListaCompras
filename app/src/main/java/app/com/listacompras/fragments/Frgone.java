package app.com.listacompras.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.com.listacompras.R;
import app.com.listacompras.interfaces.CodeScan;

/**
 * Created by daniel on 23/10/2016.
 */

public class Frgone extends Fragment {
    @Nullable
    View rootView;
    public Frgone() {
        // Required empty public constructor
    }

    /**
     * crear un oncreateview que es parte del ciclo de vida del fragmento
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //inflate the layout fragment file
        rootView = inflater.inflate(R.layout.frgone, container, false);
        /**
         * regresar la vista que al final obtuvimos
         * declaramos una clase y que se herede todo de fragment
         *
         */
        return rootView;
    }

    public static Frgone newInstance(Bundle Arguments){
        Frgone fragmento = new Frgone();
        if (Arguments != null)
        {fragmento.setArguments(Arguments);}
        return fragmento;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
        }catch (ClassCastException e)
        {
            throw new ClassCastException("Necesitas implementar");
        }
    }
}
