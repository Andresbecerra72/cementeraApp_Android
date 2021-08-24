package app.absoftware.cementera.popups;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import app.absoftware.cementera.R;
import app.absoftware.cementera.adapters.InformeAdapter;
import app.absoftware.cementera.models.Informe;
import app.absoftware.cementera.service.Service;
import app.absoftware.cementera.utility.VolleyCallback;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.palettes.RangeColors;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class PopupChart extends AppCompatActivity {

    // Elementos

    TextView tvRiesgoEspecifico, tvFecha, tvSite, tvFacility, tvConducted ; // Texto para regresar al menu textItemF1, textItemF2, textItemF3, textItemF4, textItemF5, textItemF6, textItemF7, textItemF8, textItemF9
    ScrollView scrollViewGraphic;
    AnyChartView anyChartView;

    Boolean flag = false;

    // recycleView
    RecyclerView recyclerViewInforme;
    InformeAdapter informeAdapter;

    Service service = new Service(); // instancia la clase Service

    Cartesian cartesian;

    // contadores
    int vh = 0;
    int h = 0;
    int m = 0;
    int l = 0;

    int i = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_chart);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // conexion con elementos
        scrollViewGraphic = findViewById(R.id.scrollViewGraphic);
        anyChartView = findViewById(R.id.any_chart_view); // AnyChart - graficas
        tvRiesgoEspecifico = findViewById(R.id.tvRiesgoEspecifico);
        tvSite = findViewById(R.id.tvSite);
        tvFacility = findViewById(R.id.tvFacility);
        tvConducted = findViewById(R.id.tvConducted);
        tvFecha = findViewById(R.id.tvFecha);

        recyclerViewInforme = findViewById(R.id.recyclerView);

        // Estilo del recyclerView
        recyclerViewInforme.setLayoutManager(new LinearLayoutManager(this));

        // ----------------------------

        // se deja solo visible mensaje cargando Informacion
        anyChartView.setVisibility(View.INVISIBLE);
        scrollViewGraphic.setVisibility(View.INVISIBLE);


        // inicializa la grafica
        cartesian = AnyChart.column();

        // Codigo para reducir las medidas del Activity
        DisplayMetrics medidasVentana = new DisplayMetrics(); // instancia para capturar las medidas del display
        getWindowManager().getDefaultDisplay().getMetrics(medidasVentana);

        int ancho = medidasVentana.widthPixels;
        int alto = medidasVentana.heightPixels;

        getWindow().setLayout((int)(ancho * 0.85), (int)(alto * 0.75)); // los decimales son en %


        // llamo el metodo que carga las graficas y el recyclerView
        getInformed();



    } // End OnCreate

    // ----------------------------------------------
    // Metodo para Inicializar El RECYCERVIEW
    // ----------------------------------------------
    private void getRecyclerObject(List<Informe> informeList) {


        informeAdapter = new InformeAdapter(informeList, this);

        recyclerViewInforme.setAdapter(informeAdapter);


    }



    // ----------------------------------------------
    // Metodo para obtener los datos desde BD: segun seleccion en el Select
    // ----------------------------------------------
    // este metodo muestra los datos del informe en la Grafica / Chart
    private void getInformed() {



        anyChartView.setVisibility(View.INVISIBLE); // quita la grafica
        scrollViewGraphic.setVisibility(View.INVISIBLE);


        // opcion seleccionada en el select
        String selectOption =  service.getLocalData(PopupChart.this, "select_fuente"); //Optiene el valor del Select Fuente


        String url = getResources().getString(R.string.url) + "data_chart/" + selectOption; // llama la funcion DISTINCT del API

        service.getData(PopupChart.this, url, new VolleyCallback() { // regresa la respuesta del API
            @Override
            public void onSuccessResponse(Object result) {

                // Log.d("Response", String.valueOf(result));


                scrollViewGraphic.setVisibility(View.VISIBLE);
                // inicia la animacion como FadeIn
                AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
                alphaAnimation.setDuration(600);
                // scrollViewGraphic.startAnimation(alphaAnimation);



                try {

                    JSONObject data = new JSONObject(String.valueOf(result)); // convierte la repuesta en Objeto JSON

                    // Log.d(" Data Message: ", String.valueOf(data.getString("message")));


                    if (!data.get("message").equals("Table Empty")){ // si hay Charts by name


                        JSONArray jsonArray = data.getJSONArray("message"); // Solo la informacion del Menssage del API
                        // JSONObject jsonObject = jsonArray.getJSONObject(0); // usado para cuando viene un registro por consulta de id o por nombre

                        // Log.d(" Message Lenght: ", String.valueOf(jsonArray.length()));


                        ArrayList<Object> listObject = new ArrayList<Object>(); // se crea un nueva lista de Objetos para almacenar los datos de Message

                        for(int i = 0; i < jsonArray.length(); i++) // se extrae cada registro del JSONArray
                        {
                            listObject.add(i, jsonArray.get(i)); // se crea la lista de Objetos registros
                        }


                        List<String> labels = new ArrayList<String>();
                        List<Double> levels = new ArrayList<Double>();
                        List<Informe> informeList = new ArrayList<>(); // array con la informacion para el RecyclerView

                        String  site = "--";
                        String  facility = "--";
                        String  conducted = "--";
                        String  date = "00-00-000";


                        for (Object item :listObject) { // Foreach de la lista de Objetos

                            i = i + 1;

                            JSONObject informsChart = new JSONObject(String.valueOf(item));
                            // Log.d(" Message Item: ", String.valueOf(informsChart));
                            // Log.d(" Message name: ", String.valueOf(informsChart.get("Table_Chart_name")));
                            labels.add(String.valueOf(informsChart.get("Table_Chart_Label")));
                            levels.add(Double.parseDouble(String.valueOf(informsChart.get("Table_Chart_Calculo_2_num"))));

                            // calculo del riesgo especifico
                            calculoRiesgoEspecifico(String.valueOf(informsChart.get("Table_Chart_Calculo_2")));

                            String calculo_1 = String.valueOf(informsChart.get("Table_Chart_Calculo_1"));
                            double calculo_3 = Double.parseDouble(String.valueOf(informsChart.get("Table_Chart_Calculo_3_num")));
                            double calculo_4 = Double.parseDouble(String.valueOf(informsChart.get("Table_Chart_Calculo_4_num")));

                            // data de Informe para llenar el CARD_INFOME
                            informeList.add(new Informe(
                                    i,
                                    informsChart.get("Table_Chart_name").toString(),
                                    informsChart.get("Table_Chart_title").toString(),
                                    informsChart.get("Table_Chart_Label").toString(),
                                    informsChart.get("Table_Chart_Level").toString(),
                                    informsChart.get("Table_Chart_Calculo_1").toString(),
                                    informsChart.get("Table_Chart_Calculo_1_num").toString(),
                                    informsChart.get("Table_Chart_Calculo_2").toString(),
                                    informsChart.get("Table_Chart_Calculo_2_num").toString(),
                                    informsChart.get("Table_Chart_Calculo_3").toString(),
                                    informsChart.get("Table_Chart_Calculo_3_num").toString(),
                                    informsChart.get("Table_Chart_Calculo_4").toString(),
                                    informsChart.get("Table_Chart_Calculo_4_num").toString(),
                                    "N/A",
                                    informsChart.get("Table_Chart_Calculo_5_num").toString(),
                                    informsChart.get("Table_Chart_Controles_num").toString(),
                                    informsChart.get("Table_Chart_Riesgo_total").toString(),
                                    informsChart.get("Table_Chart_Riesgo_total_num").toString(),
                                    informsChart.get("Table_Chart_site").toString(),
                                    informsChart.get("Table_Chart_facility").toString(),
                                    informsChart.get("Table_Chart_insp_date").toString(),
                                    informsChart.get("Table_Chart_conductedby").toString(),
                                    informsChart.get("Table_Chart_Calculo_2").toString(), // Nivel riesgo
                                    calculoMatlParticulado(calculo_1, calculo_3), // Exp Material particulado
                                    calculoPolvoFujitivo(calculo_4), // Control recomendado de control de polvo fugitivo
                                    informsChart.get("Table_Chart_Q10_chk_1").toString(),
                                    informsChart.get("Table_Chart_Q10_chk_2").toString(),
                                    informsChart.get("Table_Chart_Q10_chk_3").toString(),
                                    informsChart.get("Table_Chart_Q10_chk_4").toString(),
                                    informsChart.get("Table_Chart_Q10_chk_5").toString(),
                                    informsChart.get("Table_Chart_Q10_chk_5").toString(),
                                    informsChart.get("Table_Chart_Q10_chk_6").toString()
                            ));


                            // data
                            site = "Lugar: " + informsChart.get("Table_Chart_site").toString();
                            facility = "Instalaciones: " + informsChart.get("Table_Chart_facility").toString();
                            date =  informsChart.get("Table_Chart_insp_date").toString();
                            conducted = "Realizado por: " + informsChart.get("Table_Chart_conductedby").toString();


                        }

                        // Log.d("TAG Objeto CHART", String.valueOf(informeList));


                        // asigna data a los TextView
                        tvSite.setText(site);
                        tvFacility.setText(facility);
                        tvFecha.setText(date);
                        tvConducted.setText(conducted);


                        // riesgo especifico
                        double riesgo_especifico = 0;
                        riesgo_especifico = (vh * 10) + (h * 8) + (m * 4) + (l * 0.1) ;

                        String r = "Riesgo Especifico por Fuentes: " + riesgo_especifico;

                        tvRiesgoEspecifico.setText(r);
                        tvRiesgoEspecifico.setTypeface(Typeface.DEFAULT_BOLD);
                        // tvRiesgoEspecifico.setTextColor(0xffBB4F4F); // rojo
                        tvRiesgoEspecifico.setTextColor(0xff133C6A);

                        // inicializando el recycleView
                        getRecyclerObject(informeList);


                        // **Grafica**
                        setUpCartesianChart(jsonArray.length(), labels, levels); // llama el metodo que crea la grafica


                    }else { // NO hay Charts

                        Toast.makeText(getApplicationContext(),"NO hay Informes Registrados", Toast.LENGTH_LONG).show();

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

    // ---------------------------------

    // ------------------------------------------------
    private String calculoMatlParticulado(String calculo_1, double calculo_3) {

        String exp_material_particulado = "N/A";

        if (calculo_3 < 7.5)
            exp_material_particulado = "BAJO";
        if (calculo_3 <= 7.5 && calculo_3 < 17)
            exp_material_particulado = "MODERADO";
        if (calculo_3 <= 17 && calculo_3 < 23)
            exp_material_particulado = "ALTO";
        if (calculo_3 > 23)
            exp_material_particulado = "MODERADO";

        if (calculo_1.equals("Muy Alto"))
            exp_material_particulado = "ALTO";


        return exp_material_particulado;


    }

    private String calculoPolvoFujitivo(double calculo_4) {

        String control_polvo_fujitivo = "N/A";


        if (calculo_4 < 3)
            control_polvo_fujitivo = "Nivel 1";
        if (calculo_4 >= 3 && calculo_4 <= 7)
            control_polvo_fujitivo = "Nivel 2";
        if (calculo_4 > 7 && calculo_4 <= 11)
            control_polvo_fujitivo = "Nivel 3";
        if (calculo_4 > 11)
            control_polvo_fujitivo = "Nivel 4";


        return control_polvo_fujitivo;


    }



    //---------------------------------------------------
    // CAlCULOS DEL INFORME
    //---------------------------------------------------
    private void calculoRiesgoEspecifico(String riesgo) {

        if(riesgo.equals("Muy Alto"))
            vh = vh + 1;
        if(riesgo.equals("Alto"))
            h = h + 1;
        if(riesgo.equals("Moderado"))
            m = m + 1;
        if(riesgo.equals("Bajo"))
            l = l + 1;

    }


    // ------------------------------------------------
    // Metodos para crear las graficas
    // ------------------------------------------------

    public void setUpCartesianChart(Integer total, List<String> Label, List<Double> Level) {

        // Log.d(" TOTAL AQUI", String.valueOf(total));


        // cartesian = AnyChart.column();

        List<DataEntry> data = new ArrayList<>();
        data.clear();

        // ingreso de data en el Chart
        for (int i = 0; i < total; i++){
            data.add(new ValueDataEntry(Label.get(i), Level.get(i)));
        }

        // muestra la informacion al dar ckilck sobre la barra - Personalizada
        cartesian.tooltip()
                .titleFormat("{%X}")
                .position(Position.CENTER_BOTTOM)
                .anchor(Anchor.CENTER_BOTTOM)
                .offsetX(0d)
                .offsetY(0d)
                .format("Nivel {%Value}{numDecimals:1,trailingZeros:true}");

        // titulo
        cartesian.title("Nivel de Riesgo por Fuentes");


        // cartesian.xAxis(0).labels().fontSize(1);
        cartesian.xAxis(0).labels().width(75);
        cartesian.xAxis(0).labels().height(50);
        cartesian.xAxis(0).labels().wordWrap("break-all");
        cartesian.xAxis(0).labels().wordBreak("break-word");
        cartesian.xAxis(0).labels().textOverflow("...");
        // cartesian.xAxis(0).labels().wordWrap("normal");
        // cartesian.xAxis(0).labels().wordBreak("keep-all");

        // Labels eje Y
        cartesian.yScale().minimum(0d);
        cartesian.yAxis(0).labels().format("{%Value}{numDecimals:3}");
        // cartesian.yAxis(0).labels().format("${%Value}{groupsSeparator: }");


        cartesian.animation(true);

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
        cartesian.interactivity().hoverMode(HoverMode.BY_X);

        RangeColors palette = RangeColors.instantiate();
        palette.items("#92B6C1", "#00ff00");
        palette.count(5);
        // cartesian.palette(palette);

        cartesian.data(data);

        if(!flag) // condicion para no cargar nuevamente el char
            anyChartView.setChart(cartesian);


        flag = true;
        anyChartView.setVisibility(View.VISIBLE);


    }






} // END class