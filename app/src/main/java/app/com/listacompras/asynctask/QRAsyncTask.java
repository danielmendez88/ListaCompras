package app.com.listacompras.asynctask;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import app.com.listacompras.R;

/**
 * Created by daniel on 20/11/2016.
 */

public class QRAsyncTask extends AsyncTask<String, Integer, Bitmap> {
    Context myContext;
    View viewRoot;
    TextView textView;
    ImageView imageView;
    //progress Bar
    ProgressBar bar;
    //create values in QR code
    public final static int QRCodeWidth = 300;
    Bitmap bitmap;
    public QRAsyncTask(Context context, View root, ProgressBar b) {
        myContext = context;
        viewRoot = root;
        this.bar = b;
    }

    /* method to execute the principal task on a second thread */

    @Override
    protected Bitmap doInBackground(String... params) {
            try {
                for (int i = 0; i < 5; i++) {
                    bitmap = TextToImageEncode(params[0]);
                    publishProgress(i);
                }
            } catch (WriterException e) {
                e.printStackTrace();
            }
        return bitmap;
    }

    //first method is execute in Async Task
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        bar.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        bar.setProgress(values[0]);
        imageView = (ImageView) viewRoot.findViewById(R.id.QRImageView);
        imageView.setImageBitmap(bitmap);
        //imageView.setImageBitmap(values[0]);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        imageView = (ImageView) viewRoot.findViewById(R.id.QRImageView);
        imageView.setImageBitmap(bitmap);
        bar.setVisibility(View.INVISIBLE);
        Log.d("Bitmap", bitmap.toString());
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
