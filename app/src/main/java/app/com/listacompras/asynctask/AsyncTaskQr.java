package app.com.listacompras.asynctask;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import app.com.listacompras.R;

/**
 * daniel Mendez Cruz on 25/02/2017. Created By DMC
 */

public class AsyncTaskQr extends AsyncTask<String, Integer, Bitmap> {
    private Context MyContext;
    //image view
    private ImageView imageView;
    //progress bar
    private ProgressBar MyPb;
    private Activity view;
    //create values in QR code
    public final static int QRCodeWidth = 300;
    private Bitmap bitmap;
    public AsyncTaskQr(Context context, Activity root, ProgressBar Pb) {
        this.MyContext = context;
        this.MyPb = Pb;
        this.view = root;

    }

    @Override
    protected Bitmap doInBackground(String... params) {
        try {
            for (int i = 0; i < 5; i++)
            {
                bitmap = TextToImage(params[0]);
                publishProgress(i);
            }
        }
        catch (WriterException e)
        {
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        imageView = (ImageView) view.findViewById(R.id.imageView);
        imageView.setVisibility(View.INVISIBLE);
        MyPb.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        imageView = (ImageView) view.findViewById(R.id.imageView);
        imageView.setImageBitmap(bitmap);
        MyPb.setVisibility(View.INVISIBLE);
        imageView.setVisibility(View.VISIBLE);
        Log.d("mostrar bitmap",bitmap.toString());
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        //update the progress bar
        MyPb.setProgress(values[0]);
    }

    Bitmap TextToImage(String s) throws WriterException
    {
        ////declare a Matrix to content bitMap
        BitMatrix bitMatrix;
        try {
            bitMatrix = new MultiFormatWriter().encode(s, BarcodeFormat.DATA_MATRIX.QR_CODE,
                    QRCodeWidth, QRCodeWidth, null);
        }
        catch (IllegalArgumentException e)
        {
            return null;
        }
        //get sizes
        int MatrixWidth = bitMatrix.getWidth();
        int MatrixHeight = bitMatrix.getHeight();
        //initiation of pixel
        int [] pixel = new int[MatrixWidth * MatrixHeight];
        //generate the loop to create Bitmap
        for (int y = 0; y < MatrixHeight; y++)
        {
            int offset = y * MatrixWidth;
            for (int x = 0; x < MatrixWidth; x++)
            {
                pixel[offset + x] = bitMatrix.get(x, y) ? ContextCompat.getColor(MyContext, R.color.QRCodeBlackColor)
                        :ContextCompat.getColor(MyContext, R.color.QRCodeWhiteColor);
            }
        }
        Bitmap bMap = Bitmap.createBitmap(MatrixWidth, MatrixHeight, Bitmap.Config.ARGB_8888);
        bMap.setPixels(pixel, 0, 300, 0, 0, MatrixWidth, MatrixHeight);
        return bMap;
    }
}
