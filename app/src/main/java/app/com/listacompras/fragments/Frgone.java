package app.com.listacompras.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import app.com.listacompras.MainActivity;
import app.com.listacompras.R;
import app.com.listacompras.interfaces.CodeScan;

/**
 * Created by daniel on 23/10/2016.
 */

public class Frgone extends Fragment {
    @Nullable
    View rootView;
    CodeScan scanner;
    Button enviar;
    EditText texto;
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
        enviar = (Button) rootView.findViewById(R.id.enviar);
        texto = (EditText) rootView.findViewById(R.id.caja);
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mensaje;
                mensaje = texto.getText().toString();
                String TabofFragment = ((MainActivity)getActivity()).getTagFragmentToken();

                Frmtoken token = (Frmtoken) getActivity().getSupportFragmentManager()
                        .findFragmentByTag(TabofFragment);
                token.GetMensaje(mensaje);

                Snackbar.make(v, "text sent to Fragment Token:\n " + mensaje, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        return rootView;
    }

    public static Frgone newInstance(Bundle Arguments){
        Frgone fragmento = new Frgone();
        if (Arguments != null)
        {fragmento.setArguments(Arguments);}
        return fragmento;
    }

    //nos permite enviar una interfaz al activity
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            //Activity activity = (Activity) context;
            //scanner = (CodeScan) activity;
        }catch (ClassCastException e)
        {
            throw new ClassCastException("Necesitas implementar");
        }
    }
}
