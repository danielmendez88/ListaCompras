package app.com.listacompras.asynctask;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import app.com.listacompras.R;

/**
 * Created by daniel on 20/11/2016.
 */

public class QRAsyncTask extends AsyncTask<String, Bitmap, ImageView> {
    Context myContext;
    View viewRoot;
    TextView textView;
    ImageView imageView;
    //create values in QR code
    public final static int QRCodeWidth = 300;
    Bitmap bitmap;
    public QRAsyncTask(Context context, View root) {
        myContext = context;
        viewRoot = root;
    }

    /* method to execute the principal task on a second thread */

    @Override
    protected ImageView doInBackground(String... params) {
        try {
            bitmap = TextToImageEncode(params.toString());
        } catch (WriterException e) {
            e.printStackTrace();
        }
        publishProgress(bitmap);
        return null;
    }

    //first method is execute in Async Task
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(ImageView bitmap) {
        super.onPostExecute(bitmap);
    }

    @Override
    protected void onProgressUpdate(Bitmap... values) {
        super.onProgressUpdate(values);
        imageView = (ImageView) viewRoot.findViewById(R.id.QRImageView);
        imageView.setImageBitmap(values[0]);
    }

    /* Method to generate Bitmap QR Code*/
    Bitmap TextToImageEncode(String text) throws WriterException{
        //declare a Matrix to content bitMap
        BitMatrix bitMatrix;
        try {
            bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.DATA_MATRIX.QR_CODE,
                   QRCodeWidth, QRCodeWidth, null);
        }catch (IllegalArgumentException e){ return null;}
        //get sizes
        int MatrixWidth = bitMatrix.getWidth();
        int MatrixHeight = bitMatrix.getHeight();
        // initiation of pixel
        int[] pixel = new int[MatrixWidth * MatrixHeight];
        //generate the loop to create the bitmap
        for (int y = 0; y < MatrixHeight; y++)
        {
            int offset = y * MatrixWidth;
            for (int x = 0; x < MatrixWidth; x++)
            {
                pixel[offset + x] = bitMatrix.get(x, y) ? myContext.getResources().getColor(R.color.QRCodeBlackColor)
                        :myContext.getResources().getColor(R.color.QRCodeWhiteColor);
            }
        }
        Bitmap bMap = Bitmap.createBitmap(MatrixWidth, MatrixHeight, Bitmap.Config.ARGB_8888);
        bMap.setPixels(pixel, 0, 300, 0, 0, MatrixWidth, MatrixHeight);
        return bMap;
    }
}
