package app.com.listacompras.clases;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;

import com.google.zxing.Result;

import app.com.listacompras.R;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by daniel on 16/11/2016.
 */

public class Eventoqr implements View.OnClickListener, ZXingScannerView.ResultHandler{
    Activity cntx;
    //add scanner
    private ZXingScannerView MyscannerView;
    //generate a constructor
    public Eventoqr(Activity c) {
        cntx = c;
    }

    //Method onclick listener

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.A1:
                //Do something
                    QRscan(v);
                break;
        }
        String MessageClick;
        //v.getId();
        MessageClick = "Hola Soy un mensaje";
        Log.i("ONCLICK", "botón cliqueado");
        Snackbar.make(v, "text sent to Activity One:\n " + MessageClick, Snackbar.LENGTH_LONG)
                .setAction("Actions", null).show();
    }

    @Override
    public void handleResult(Result result) {
        //in this Method get the result of scanning Barcode QR
        Log.v("Handler", result.getText());
        Log.e("Handler Barcode Format", result.getBarcodeFormat().toString());
        //add a Alert Dialog for show the QR code
            AlertDialog.Builder DialogBox = new AlertDialog.Builder(cntx, R.style.MyAlerDialogStyle);
            DialogBox.setTitle("Código QR")
                    .setMessage(result.getText())
                    .setPositiveButton("OK", null)
                    .show();
    }

    //method that implements start camera
    public void QRscan(View vista)
    {
        Log.e("Inicio de camara", cntx.toString());
        MyscannerView = new ZXingScannerView(cntx.getApplicationContext());
        cntx.setContentView(MyscannerView);
        MyscannerView.setResultHandler(this);
        MyscannerView.startCamera();
    }
}
