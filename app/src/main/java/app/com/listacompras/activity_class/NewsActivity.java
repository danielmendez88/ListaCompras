package app.com.listacompras.activity_class;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import app.com.listacompras.R;
import app.com.listacompras.clases.Prngenerator;

/**
 * daniel Mendez Cruz on 25/02/2017. Created By DMC
 */

public class NewsActivity extends AppCompatActivity implements View.OnClickListener {
    //declare values
    private EditText Edt_texto;
    private TextView txtResult;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        //casting the widgets
        Button btn = (Button) findViewById(R.id.ok);
        Edt_texto = (EditText) findViewById(R.id.text_news);
        txtResult = (TextView) findViewById(R.id.result);
        //set method on click for activity button
        btn.setOnClickListener(this);
    }

    /**
     *
     * @param v this value set a view from activity such is a button widget
     */

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.ok:
                final int length = 8;
                int lg;
                String numero = Edt_texto.getText().toString();
                lg = Integer.valueOf(numero);
                Prngenerator PRNG = new Prngenerator(lg, length);
                txtResult.setText(String.valueOf(PRNG.random_generator()));
                break;
            default:
                break;
        }
    }
}
