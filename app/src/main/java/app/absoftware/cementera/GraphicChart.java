package app.absoftware.cementera;

import androidx.appcompat.app.AppCompatActivity;
import app.absoftware.cementera.popups.PopupChart;
import app.absoftware.cementera.service.Service;
import app.absoftware.cementera.utility.VolleyCallback;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputLayout;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class GraphicChart extends AppCompatActivity {

    // Elementos
    TextInputLayout selectInput;
    AutoCompleteTextView autoCompleteTxtView_reports;
    TextView tvWait, regresarText ;
    ProgressBar progressBar;
    Button getDataBtn;

    Service service = new Service(); // instancia la clase Service



    // -----------------------metodos--------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphic_chart);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // conexion con elementos
        tvWait = findViewById(R.id.tvWait);
        selectInput = findViewById(R.id.selectInput);
        autoCompleteTxtView_reports = findViewById(R.id.autoCompleteTxtView_reports);
        regresarText = findViewById(R.id.regresarText);
        progressBar = findViewById(R.id.progress_graphic);
        getDataBtn = findViewById(R.id.getDataBtn);



        // underline textview
        regresarText.setPaintFlags(regresarText.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        // ----------------------------

        // se deja solo visible mensaje cargando Informacion
        selectInput.setVisibility(View.INVISIBLE); // quita el Select
        getDataBtn.setVisibility(View.INVISIBLE); // quita el Button


        // para llenar el selec
        getChartData(); // llame el metodo para Obtener los datos de CHART des de el API

        // acciones del textView Inicio de sesion: NAVEGACION
        regresarText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MenuMain.class);
                startActivity(intent);
                finish();

            }
        });

        // acciones del getDataBtn para Obtener datos del Chart desde el API
        getDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(String.valueOf(autoCompleteTxtView_reports.getText()).equals("")){
                    Toast.makeText(GraphicChart.this, "Seleccione una Opción", Toast.LENGTH_LONG).show();
                    return;
                }

                // Linea para guardar la seleccion del Select
                service.saveLocalData(GraphicChart.this, "select_fuente", String.valueOf(autoCompleteTxtView_reports.getText()));

                Intent intent = new Intent(getApplicationContext(), PopupChart.class);
                startActivity(intent);

            }
        });




    } //End OnCreate





    // ----------------------------------------------
    // Metodo para obtener los datos desde BD: DISTINCT
    // ----------------------------------------------
    // este metodo llena el select
    private void getChartData() {

        String url = getResources().getString(R.string.url) + "chart/distinct"; // llama la funcion DISTINCT del API

        service.getData(GraphicChart.this, url, new VolleyCallback() { // regresa la respuesta del API
            @Override
            public void onSuccessResponse(Object result) {

                // Log.d("Response", String.valueOf(result));

                progressBar.setVisibility(View.INVISIBLE); // quita el Loading


                try {

                    JSONObject data = new JSONObject(String.valueOf(result)); // convierte la repuesta en Objeto JSON

                    // Log.d(" Data Message: ", String.valueOf(data.getString("message")));


                    if (!data.get("message").equals("Table Empty")){ // si hay Clients

                        tvWait.setVisibility(View.INVISIBLE); // quita mensaje Cargando Informacion
                        selectInput.setVisibility(View.VISIBLE);
                        getDataBtn.setVisibility(View.VISIBLE);


                        JSONArray jsonArray = data.getJSONArray("message"); // Solo la informacion del Menssage del API
                        // JSONObject jsonObject = jsonArray.getJSONObject(0); // usado para cuando viene un registro por consulta de id o por nombre

                        // Log.d(" Message Lenght: ", String.valueOf(jsonArray.length()));


                        ArrayList<Object> listObject = new ArrayList<Object>(); // se crea un nueva lista de Objetos para almacenar los datos de Message

                        for(int i = 0; i < jsonArray.length(); i++) // se extrae cada registro del JSONArray
                        {
                            listObject.add(i, jsonArray.get(i)); // se crea la lista de Objetos registros
                        }



                        List<String> items = new ArrayList<String>(); // lista para mostrar en el Select


                        for (Object item :listObject) { // Foreach de la lista de Objetos

                            JSONObject informsChart = new JSONObject(String.valueOf(item));
                            // Log.d(" Message Item: ", String.valueOf(informsChart));
                            // Log.d(" Message name: ", String.valueOf(informsChart.get("Table_Chart_name")));
                            items.add(String.valueOf(informsChart.get("Table_Chart_name")));
                        }

                        ArrayAdapter<String> myAdapter_Q1 = new ArrayAdapter<String>(GraphicChart.this, R.layout.dropdown_item, items);
                        autoCompleteTxtView_reports.setAdapter(myAdapter_Q1); // asigna el adaptador con los items al menu



                    }else { // NO hay Charts

                        Toast.makeText(getApplicationContext(),"NO hay Reportes Registrados", Toast.LENGTH_LONG).show();
                        tvWait.setText("No hay Reportes");

                    }


                } catch (JSONException e) {
                    Log.i("MyTag JSONException", String.valueOf(e.getMessage()));
                }



                }

            @Override
            public void onErrorResponse(String error) {

                Log.d("Error", error);

            }
        });


    }






} // END class

/*

// Funcion Flecha
        Function<Double, String> func = x -> {
            if (x> 13)
                return "Muy Alto";
            if (x >= 8 && x < 13)
                return "Alto";
            if (x >= 4 && x < 8)
                return "Moderado";
            if (x < 4)
                return "Bajo";
            return "NA";
        };
 */

/*

        textItemF1 = findViewById(R.id.textItemF1);
        textItemF2 = findViewById(R.id.textItemF2);
        textItemF3 = findViewById(R.id.textItemF3);
        textItemF4 = findViewById(R.id.textItemF4);
        textItemF5 = findViewById(R.id.textItemF5);
        textItemF6 = findViewById(R.id.textItemF6);
        textItemF7 = findViewById(R.id.textItemF7);
        textItemF8 = findViewById(R.id.textItemF8);
        textItemF9 = findViewById(R.id.textItemF9);


   setTextInTV(i, String.valueOf(informsChart.get("Table_Chart_Label")));

  private void setTextInTV(Integer index, String label) {

        Log.d(" TAG AQUI ", String.valueOf(i));

        if (index == 1)
            textItemF1.setText(label);
        if (index == 2)
            textItemF2.setText(label);
        if (index == 3)
            textItemF3.setText(label);
        if (index == 4)
            textItemF4.setText(label);
        if (index == 5)
            textItemF5.setText(label);
        if (index == 6)
            textItemF6.setText(label);
        if (index == 7)
            textItemF7.setText(label);
        if (index == 8)
            textItemF8.setText(label);
        if (index == 9)
            textItemF9.setText(label);
        if (index > 9)
            i = 0;


    }

 */

/*


        for (int i = 0; i < 10; i++){
            informeList.add(new Informe(
                    i,
                    "UNO",
                    "DOS",
                    "TRES",
                    "CUATRO",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    true,
                    true,
                    false,
                    true,
                    false,
                    false,
                    false
                    ));
        }


 */