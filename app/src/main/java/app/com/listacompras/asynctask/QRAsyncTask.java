package app.com.listacompras.asynctask;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import app.com.listacompras.R;

/**
 * Created by daniel on 20/11/2016.
 */

public class QRAsyncTask extends AsyncTask<String, String, Bitmap> {
    private Bitmap bitmap;
    public final static int QRCodeWidth = 500;
    private ImageView imageview;
    Context myContext;
    View viewRoot;

    public QRAsyncTask(Context context, View root) {
        myContext = context;
        viewRoot = root;
        imageview = (ImageView) root.findViewById(R.id.QRImageView);
    }

    //method to execute the principal task on a second thread
    @Override
    protected Bitmap doInBackground(String... params) {
            String cadena = params.toString();
            publishProgress(cadena);
        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        try {
            bitmap = TextToImageEncode(values.toString());
            imageview.setImageBitmap(bitmap);
        }catch (WriterException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        imageview.setImageBitmap(bitmap);
    }

    //first method is execute in Async Task
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    //method to convert text to Image
     Bitmap TextToImageEncode(String texto) throws WriterException{
        //declare a matrix
        BitMatrix bitMatrix;
        try {
            bitMatrix = new MultiFormatWriter().encode(texto, BarcodeFormat.DATA_MATRIX.QR_CODE, QRCodeWidth,
                    QRCodeWidth, null);
        }catch (IllegalArgumentException e)
        {
           return null;
        }
        //get sizes
        int bitMatrixWidth = bitMatrix.getWidth();
        int bitMatrixHeight = bitMatrix.getHeight();
        //pixel multiple BitMatrixWidth * BitMatrixHeight
        int pixel[] = new int[bitMatrixWidth * bitMatrixHeight];
        for (int y= 0; y < bitMatrixHeight; y++)
        {
            int offset =  y * bitMatrixWidth;
            for (int x = 0; x < bitMatrixWidth; x++)
            {
                pixel[offset + x] = bitMatrix.get(x, y) ? myContext.getResources().getColor(R.color.QRCodeBlackColor):myContext.getResources()
                        .getColor(R.color.QRCodeWhiteColor);
            }
        }
        //
        Bitmap bMap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_8888);
        bMap.setPixels(pixel, 0, 500, 0, 0, bitMatrixWidth, bitMatrixHeight);
        return bMap;
    }
}
