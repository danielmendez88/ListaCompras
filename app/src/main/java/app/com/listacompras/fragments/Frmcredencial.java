package app.com.listacompras.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import app.com.listacompras.R;

/**
 * Created by daniel on 15/11/2016.
 */

public class Frmcredencial extends Fragment {
    View vista;
    //declare some variables that we'll use in future
    Bitmap bitmap;
    public final static int QRCodeWidth = 250;
    ImageView imgview;
    public Frmcredencial() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.frmcredencial, container, false);
        imgview = (ImageView) vista.findViewById(R.id.QRImageView);
        //create a Thread to avoid Exception
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                // this is the msg which will be encode in QRcode
                final String tokenText = "www.facebook.com";
                try {
                    synchronized (this)
                    {
                        wait(300);
                        // runOnUiThread method used to do UI task in main thread.
                        getActivity().runOnUiThread(new Runnable(){

                            @Override
                            public void run() {
                                try {
                                    bitmap = TextToImageEncode(tokenText);
                                    imgview.setImageBitmap(bitmap);
                                }
                                catch (WriterException e)
                                {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                }
                catch (InterruptedException in)
                {
                    in.printStackTrace();
                }
            }
        });
        t.start();
        //Here we put some code that show the QR code
        return vista;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    Bitmap TextToImageEncode(String text) throws WriterException{
        //declare a BitMatix
        BitMatrix bitMatrix;
        try {
            bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.DATA_MATRIX.QR_CODE,
                    QRCodeWidth, QRCodeWidth, null);
        }catch (IllegalArgumentException e){ return null;}
        //get sizes
        int bitMatrixWidth = bitMatrix.getWidth();
        int bitMatrixHeight = bitMatrix.getHeight();
        //pixel multiple BitMatrixWidth * BitMatrixHeight
        int[] pixel = new int[bitMatrixWidth * bitMatrixHeight];
        for (int i = 0; i < bitMatrixHeight; i++)
        {
            int offsety = i * bitMatrixWidth;
            // j represents x
            for (int j = 0; j < bitMatrixWidth; j++)
            {
                pixel[offsety + j] = bitMatrix.get(j, i) ? getResources().getColor(R.color.QRCodeBlackColor):getResources()
                        .getColor(R.color.QRCodeWhiteColor);
            }
        }
        Bitmap bMap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_8888);
        bMap.setPixels(pixel, 0, 250, 0, 0, bitMatrixWidth, bitMatrixHeight);
        return bMap;
    }
}
