package app.com.listacompras.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import app.com.listacompras.R;

/**
 * Created by daniel on 14/11/2016.
 */

public class Frmtoken extends Fragment {
    View root;
    TextView texto;
    public Frmtoken() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.frtoken, container, false);
        texto = (TextView) root.findViewById(R.id.text_token);
        Bundle bundle = this.getArguments();
        if (bundle != null)
        {
            String texto_mensaje = bundle.getString("mensajes");
            texto.setText(texto_mensaje);
        }
        //return my root view
        return root;
    }

    public void GetMensaje(String mensaje)
    {
        texto.setText(mensaje);
    }
     public static Frmtoken newInstance(Bundle Arguments)
     {
         Frmtoken token = new Frmtoken();
         if (Arguments != null)
         {token.setArguments(Arguments);}
         return token;
     }
}
