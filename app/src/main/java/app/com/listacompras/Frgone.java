package app.com.listacompras;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by daniel on 23/10/2016.
 */

public class Frgone extends Fragment {
    @Nullable
    View rootView;
    /**
     * crear un oncreateview que es parte del ciclo de vida del fragmento
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.frgone, container, false);
        /**
         * regresar la vista que al final obtuvimos
         * declaramos una clase y que se herede todo de fragment
         *
         */
        return rootView;
    }
}
