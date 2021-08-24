package app.absoftware.cementera;

import androidx.appcompat.app.AppCompatActivity;
import app.absoftware.cementera.service.Service;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MenuMain extends AppCompatActivity {

    // Elementos
    TextView textViewUser; // Texto para mostrar el usuario
    Button FormBtn;
    Button GraficaBtn;
    Button SalirBtn;

    // Servicio Http
    Service service = new Service(); // Instancia la clase Service

    // -----------------------metodos--------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // conexion con elementos
        textViewUser = findViewById(R.id.textViewUser);
        FormBtn = findViewById(R.id.FormBtn);
        GraficaBtn = findViewById(R.id.GraficaBtn);
        SalirBtn = findViewById(R.id.SalirBtn);

        // muestra el correo del usuario
        String user = service.getLocalData(MenuMain.this, "user");
        textViewUser.setText(user);

        // acciones del Boton : NAVEGACION pagina Form
        FormBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), FormData.class);
                startActivity(intent);
                finish();

            }
        });

        // acciones del Boton : NAVEGACION pagina Graficas
        GraficaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), GraphicChart.class);
                startActivity(intent);
                finish();

            }
        });


        // acciones del Boton : EXIT
        SalirBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);

            }
        });







    }




} // END class