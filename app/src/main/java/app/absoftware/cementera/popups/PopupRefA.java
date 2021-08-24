package app.absoftware.cementera.popups;

import androidx.appcompat.app.AppCompatActivity;
import app.absoftware.cementera.R;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.DisplayMetrics;

public class PopupRefA extends AppCompatActivity {

    // variables

    // -------------metodos-------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_ref_a);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Codigo para reducir las medidas del Activity
        DisplayMetrics medidasVentana = new DisplayMetrics(); // instancia para capturar las medidas del display
        getWindowManager().getDefaultDisplay().getMetrics(medidasVentana);

        int ancho = medidasVentana.widthPixels;
        int alto = medidasVentana.heightPixels;

        getWindow().setLayout((int)(ancho * 0.85), (int)(alto * 0.7)); // los decimales son en %


    }
} // END class