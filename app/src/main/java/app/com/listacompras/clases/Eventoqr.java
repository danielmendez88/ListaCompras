package app.com.listacompras.clases;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.Result;

import app.com.listacompras.R;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static android.content.Context.MODE_PRIVATE;

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
    //creation of a constant to access the permission of CAMERA
    private static final int EXTERNAL_CAMERA_PERMISSION_CONSTANT = 101;
    private static final int REQUEST_PERMISSION_SETTING = 102;
    String[] permissionsRequired = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_FINE_LOCATION
    };
    //permission status
    private SharedPreferences permissionStatus;
    private boolean sentToSettings = false;
    //Method onclick listener



    @Override
    public void onClick(final View v) {
        //in this part we check if the application have permissions for use the camera as scanner
        permissionStatus = cntx.getSharedPreferences("permissionStatus", MODE_PRIVATE);
        if(ActivityCompat.checkSelfPermission(cntx, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
        {
            if (ActivityCompat.shouldShowRequestPermissionRationale(cntx, Manifest.permission.CAMERA))
            {
                //show information about they need permission
                //add Alert Dialog box for  information about permission Granted
                AlertDialog.Builder build = new AlertDialog.Builder(cntx);
                build.setTitle("Se necesita permiso")
                .setMessage("Esta aplicación necesita permisos sobre la Camara")
                .setPositiveButton("Conceder", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        ActivityCompat.requestPermissions(cntx, new String[]{Manifest.permission.CAMERA}, EXTERNAL_CAMERA_PERMISSION_CONSTANT);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //cancelamos
                        dialog.cancel();
                    }
                })
                .show();
            } else if (permissionStatus.getBoolean(Manifest.permission.CAMERA, false))
            {
               //previously permission request was cancelled whit 'DONT ASK AGAIN'
               // Redirect to settings after showing Information about why you need the permission
                AlertDialog.Builder builder_dialog = new AlertDialog.Builder(cntx);
                builder_dialog.setTitle("Necesita Permisos")
                .setMessage("Esta aplicación necesita permisos para la Camara")
                .setPositiveButton("Conceder", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        sentToSettings = true;
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", cntx.getPackageName(), null);
                        intent.setData(uri);
                        cntx.startActivityForResult(intent, REQUEST_PERMISSION_SETTING);
                        Snackbar.make(v, "Ir a permisos para conceder acceso a Camara", Snackbar.LENGTH_LONG)
                                .setAction("Actions", null).show();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
            }
            else {
                //just request the permission
                ActivityCompat.requestPermissions(cntx, new String[]{Manifest.permission.CAMERA}, EXTERNAL_CAMERA_PERMISSION_CONSTANT);
            }

            //shared preferences
            SharedPreferences.Editor editor = permissionStatus.edit();
            editor.putBoolean(Manifest.permission.CAMERA, true);
            editor.commit();
            //gave permission to camera
        }
        else
        {
            //already have the permission, just go ahead
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
