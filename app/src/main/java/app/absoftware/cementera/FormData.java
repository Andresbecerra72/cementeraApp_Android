package app.absoftware.cementera;

import androidx.appcompat.app.AppCompatActivity;
import app.absoftware.cementera.models.Informe;
import app.absoftware.cementera.models.Reporte;
import app.absoftware.cementera.popups.PopupRefA;
import app.absoftware.cementera.service.Service;
import app.absoftware.cementera.utility.DatePickerFormat;
import app.absoftware.cementera.utility.VolleyCallback;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.Calendar;


public class FormData extends AppCompatActivity {

    // Elementos
    ScrollView ScrollView;
    TextInputEditText InputEdit_site, InputEdit_facility;
    RadioGroup radioBtn_0;
    RadioButton yes_rBtn_Q2, Not_rBtn_Q2, NA_rBtn_Q2;
    Spinner spinner_Q3, spinner_Q5, spinner_Q6, spinner_Q7; // Spinner
    AutoCompleteTextView autoCompleteTxtView_Q1, autoCompleteTxtView_Q4, autoCompleteTxtView_Q8, autoCompleteTxtView_Q9, autoCompleteTxtView_Q11; // select
    CheckBox checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6, checkBox7;


    TextView regresarText, textView_conducted; // Texto para mostrar el usuario

    Button NewReportBtn, SendBtn, datePickerBtn, RefBtn_A;
    ProgressBar progressBar;

    // Servicio Http
    Service service = new Service(); // Instancia la clase Service
    // CalculosData calculosData = new CalculosData(); // Instancia la clase CalculoData
    DatePickerFormat datePickerFormat = new DatePickerFormat(); // Instancia la clase DatePicker

    // intancia los modelos
    Reporte reporte = new Reporte();
    Informe informe = new Informe();

    DatePickerDialog datePickerDialog;

    ArrayAdapter<String> myAdapter,  myAdapter_diff;


    // -----------------------metodos--------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) { // OnCreate
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_data);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // conexion con elementos
        ScrollView = findViewById(R.id.ScrollView);
        NewReportBtn = findViewById(R.id.NewReportBtn);
        SendBtn = findViewById(R.id.SendBtn);
        progressBar = findViewById(R.id.progress_formdata);

        // popup elements
        RefBtn_A = findViewById(R.id.RefBtn_A);

        // Encabezado
        InputEdit_site = findViewById(R.id.InputEdit_site);
        InputEdit_facility = findViewById(R.id.InputEdit_facility);
        datePickerBtn = findViewById(R.id.datePickerBtn); // fecha de la evaluacion
        textView_conducted = findViewById(R.id.textView_conducted);
        // Formulario
        autoCompleteTxtView_Q1 = findViewById(R.id.autoCompleteTxtView_Q1);
        radioBtn_0 = findViewById(R.id.radioBtn_0);
        yes_rBtn_Q2 = findViewById(R.id.yes_rBtn_Q2);
        Not_rBtn_Q2 = findViewById(R.id.Not_rBtn_Q2);
        NA_rBtn_Q2 = findViewById(R.id.NA_rBtn_Q2);
        spinner_Q3 = findViewById(R.id.spinner_Q3);
        autoCompleteTxtView_Q4 = findViewById(R.id.autoCompleteTxtView_Q4);
        spinner_Q5 = findViewById(R.id.spinner_Q5);
        spinner_Q6 = findViewById(R.id.spinner_Q6);
        spinner_Q7 = findViewById(R.id.spinner_Q7);
        autoCompleteTxtView_Q8 = findViewById(R.id.autoCompleteTxtView_Q8);
        autoCompleteTxtView_Q9 = findViewById(R.id.autoCompleteTxtView_Q9);
        checkBox1 = findViewById(R.id.checkBox1);
        checkBox2 = findViewById(R.id.checkBox2);
        checkBox3 = findViewById(R.id.checkBox3);
        checkBox4 = findViewById(R.id.checkBox4);
        checkBox5 = findViewById(R.id.checkBox5);
        checkBox6 = findViewById(R.id.checkBox6);
        checkBox7 = findViewById(R.id.checkBox7);
        autoCompleteTxtView_Q11 = findViewById(R.id.autoCompleteTxtView_Q11);

        regresarText = findViewById(R.id.regresarText_Form);
        // underline textview
        regresarText.setPaintFlags(regresarText.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        // Indica quien realizo la Evaluacion
        String conducte_by = "Realizada por: " + service.getLocalData(FormData.this, "user_name");
        textView_conducted.setText(conducte_by);




        // llama el metono que inicializa el datepicker con la fecha de HOY
        datePickerBtn.setText(datePickerFormat.getTodayDate());


        // -------------SPINNER Y SELECT------------------------------
        // adaptador para ingresar los items en el Spinner-dropdown menu
        myAdapter = new ArrayAdapter<String>(FormData.this, R.layout.dropdown_item, getResources().getStringArray(R.array.spinner_items));
        myAdapter_diff = new ArrayAdapter<String>(FormData.this, R.layout.dropdown_item, getResources().getStringArray(R.array.spinner_items_diff)); // opcion N/A
        spinner_Q3.setAdapter(myAdapter_diff); // asigna el adaptador con los items al Spinner
        spinner_Q6.setAdapter(myAdapter); // asigna el adaptador con los items al Spinner
        spinner_Q7.setAdapter(myAdapter); // asigna el adaptador con los items al Spinner
        spinner_Q5.setAdapter(myAdapter); // asigna el adaptador con los items al Spinner

        ArrayAdapter<String> myAdapter_Q1 = new ArrayAdapter<String>(FormData.this, R.layout.dropdown_item, getResources().getStringArray(R.array.dropdown_items_Q1_patio)); // Todo: cambio por seleccion de AREA
        autoCompleteTxtView_Q1.setAdapter(myAdapter_Q1); // asigna el adaptador con los items al menu

        ArrayAdapter<String> myAdapter_Q4 = new ArrayAdapter<String>(FormData.this, R.layout.dropdown_item, getResources().getStringArray(R.array.dropdown_items_Q4));
        autoCompleteTxtView_Q4.setAdapter(myAdapter_Q4); // asigna el adaptador con los items al menu

        ArrayAdapter<String> myAdapter_Q8 = new ArrayAdapter<String>(FormData.this, R.layout.dropdown_item, getResources().getStringArray(R.array.dropdown_items_Q8));
        autoCompleteTxtView_Q8.setAdapter(myAdapter_Q8); // asigna el adaptador con los items al menu

        ArrayAdapter<String> myAdapter_Q9 = new ArrayAdapter<String>(FormData.this, R.layout.dropdown_item, getResources().getStringArray(R.array.dropdown_items_Q9_patio)); // Todo: cambio por seleccion de AREA
        autoCompleteTxtView_Q9.setAdapter(myAdapter_Q9); // asigna el adaptador con los items al menu
/*
        ArrayAdapter<String> myAdapter_Q10 = new ArrayAdapter<String>(FormData.this, R.layout.dropdown_item, getResources().getStringArray(R.array.dropdown_items_Q10));
        autoCompleteTxtView_Q10.setAdapter(myAdapter_Q10); // asigna el adaptador con los items al menu
*/
        ArrayAdapter<String> myAdapter_Q11 = new ArrayAdapter<String>(FormData.this, R.layout.dropdown_item, getResources().getStringArray(R.array.dropdown_items_Q11));
        autoCompleteTxtView_Q11.setAdapter(myAdapter_Q11); // asigna el adaptador con los items al menu
        // -------------------------------------------
        initDatePicker(); // inicializa el DatePicker



        // acciones del textView Inicio de sesion: NAVEGACION
        regresarText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MenuMain.class);
                startActivity(intent);
                finish();

            }
        });

        // -----------------------------------------------------------------------

        // acciones del Boton para gerenerar un nuevo reporte
        NewReportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ScrollView.setVisibility(View.VISIBLE);
                NewReportBtn.setVisibility(View.INVISIBLE);

                // inicia la animacion como FadeIn
                AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
                alphaAnimation.setDuration(1000);
                // alphaAnimation.setRepeatCount(1);
                // alphaAnimation.setRepeatMode(Animation.REVERSE);
                ScrollView.startAnimation(alphaAnimation);

            }
        });

        // -----------------------------------------------------------------------

        // acciones del Boton para Mostrar las Referencia por medio de Un POPUP
        RefBtn_A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), PopupRefA.class);
                startActivity(intent);

            }
        });



        // ----------------------------------------------------------------------

        // acciones del Boton SignUpBtn
        SendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                reporte = new Reporte(
                        0,
                        String.valueOf(autoCompleteTxtView_Q1.getText()),
                        getRadioGroupBtnsValue(radioBtn_0), // llame el metodo con el valor del radio Button
                        String.valueOf(spinner_Q3.getSelectedItem()),
                        String.valueOf(autoCompleteTxtView_Q4.getText()),
                        String.valueOf(spinner_Q5.getSelectedItem()),
                        String.valueOf(spinner_Q6.getSelectedItem()),
                        String.valueOf(spinner_Q7.getSelectedItem()),
                        String.valueOf(autoCompleteTxtView_Q8.getText()),
                        String.valueOf(autoCompleteTxtView_Q9.getText()),
                        checkBox1.isChecked(),
                        checkBox2.isChecked(),
                        checkBox3.isChecked(),
                        checkBox4.isChecked(),
                        checkBox5.isChecked(),
                        checkBox6.isChecked(),
                        checkBox7.isChecked(),
                        String.valueOf(autoCompleteTxtView_Q11.getText()),
                        String.valueOf(InputEdit_site.getText()),
                        String.valueOf(InputEdit_facility.getText()),
                        String.valueOf(datePickerBtn.getText()),
                        service.getLocalData(FormData.this, "user_name"),
                        "NA",
                        "NA",
                        "NA",
                        "ANDROID"
                );


                if (!getValidacionesForm()) { // condicion que valida los Inputs
                    return;
                }

                    /* **Si to_do OK */

                informe.setSite(reporte.getSite());
                informe.setFacility(reporte.getFacility());
                informe.setInd_date(reporte.getInsp_date());
                informe.setLabel(reporte.getQ1());


                   // ------Calculos--------
                String key = reporte.getSite() + "_" + reporte.getQ1() + "_"; // llave para almacenarlo en localStorage

                informe.setQ10_chk_1(String.valueOf(checkBox1.isChecked()));
                informe.setQ10_chk_2(String.valueOf(checkBox2.isChecked()));
                informe.setQ10_chk_3(String.valueOf(checkBox3.isChecked()));
                informe.setQ10_chk_4(String.valueOf(checkBox4.isChecked()));
                informe.setQ10_chk_5(String.valueOf(checkBox5.isChecked()));
                informe.setQ10_chk_6(String.valueOf(checkBox6.isChecked()));
                informe.setQ10_chk_7(String.valueOf(checkBox7.isChecked()));

                informe.getCalculo_Controles(FormData.this, key); // sumatoria de los controles existentes
                informe.getCalculo_1(FormData.this, key, reporte.getQ2(), reporte.getQ3(), reporte.getQ5(), reporte.getQ6(), reporte.getQ7(), reporte.getQ8());


                    // url Endpoint API - PHP
                    String url = getResources().getString(R.string.url) + "report";


                    progressBar.setVisibility(View.VISIBLE); // muestra el Loading

                    // Log.d("Nombre: ", nombre);

                   String user_mail = service.getLocalData(FormData.this, "user");

                    JSONObject objectJsonReport = new JSONObject(); // crea el Objeto JSON con los datos
                    try {

                        //input your API parameters
                        objectJsonReport.put("Q1", reporte.getQ1());
                        objectJsonReport.put("Q2", reporte.getQ2());
                        objectJsonReport.put("Q3", reporte.getQ3());
                        objectJsonReport.put("Q4", reporte.getQ4());
                        objectJsonReport.put("Q5", reporte.getQ5());
                        objectJsonReport.put("Q6", reporte.getQ6());
                        objectJsonReport.put("Q7", reporte.getQ7());
                        objectJsonReport.put("Q8", reporte.getQ8());
                        objectJsonReport.put("Q9", reporte.getQ9());
                        objectJsonReport.put("Q10_chk_1", reporte.isQ10_chk_1());
                        objectJsonReport.put("Q10_chk_2", reporte.isQ10_chk_2());
                        objectJsonReport.put("Q10_chk_3", reporte.isQ10_chk_3());
                        objectJsonReport.put("Q10_chk_4", reporte.isQ10_chk_4());
                        objectJsonReport.put("Q10_chk_5", reporte.isQ10_chk_5());
                        objectJsonReport.put("Q10_chk_6", reporte.isQ10_chk_6());
                        objectJsonReport.put("Q10_chk_7", reporte.isQ10_chk_7());
                        objectJsonReport.put("Q11", reporte.getQ11());
                        objectJsonReport.put("site", reporte.getSite());
                        objectJsonReport.put("facility", reporte.getFacility());
                        objectJsonReport.put("date", reporte.getInsp_date());
                        objectJsonReport.put("conducted", reporte.getConductedby());
                        objectJsonReport.put("source", "ANDROID");
                        objectJsonReport.put("user_mail", user_mail);

                    } catch (JSONException e) {
                        Log.i("MyTag JSONException", String.valueOf(e.getMessage()));
                    }

                    // Log.d(" Objeto_Report: ", String.valueOf(objectJsonReport));

                    // **************************************
                    PostByService(url, objectJsonReport); // envia Reporte a BD Hosting;


            }
        });
        // ----------------------------------------------------------------------


    } //end Oncreate


    // ----------------------------------------------------------------------
    // METODO PARA ALMACENAR DATA EN BD
    // ----------------------------------------------------------------------
    private void PostByService(String url, JSONObject objectJson) {

        // lla ma el servicio Http / Volley
        service.postData(getApplicationContext(), url, objectJson,
                new VolleyCallback() { // implementa los metodos de la Interface
                    @Override
                    public void onSuccessResponse(Object result) {

                        try {
                            JSONObject data = new JSONObject(String.valueOf(result));
                            // Log.d("Response", data.getString("message"));
                            progressBar.setVisibility(View.INVISIBLE); // quita el Loading


                                ScrollView.setVisibility(View.INVISIBLE);
                                NewReportBtn.setVisibility(View.VISIBLE);

                                // reset Form
                                // InputEdit_site.setText("");
                                // InputEdit_facility.setText("");
                                autoCompleteTxtView_Q1.setText("");
                                yes_rBtn_Q2.setChecked(false);
                                Not_rBtn_Q2.setChecked(false);
                                NA_rBtn_Q2.setChecked(true);
                                spinner_Q3.setAdapter(myAdapter);
                                autoCompleteTxtView_Q4.setText("");
                                spinner_Q5.setAdapter(myAdapter);
                                spinner_Q6.setAdapter(myAdapter);
                                spinner_Q7.setAdapter(myAdapter);
                                autoCompleteTxtView_Q8.setText("");
                                autoCompleteTxtView_Q9.setText("");
                                checkBox1.setChecked(false);
                                checkBox2.setChecked(false);
                                checkBox3.setChecked(false);
                                checkBox4.setChecked(false);
                                checkBox5.setChecked(false);
                                checkBox6.setChecked(false);
                                checkBox7.setChecked(false);
                                autoCompleteTxtView_Q11.setText("");



                        } catch (JSONException e) {
                            Log.i("MyTag JSONException", String.valueOf(e.getMessage()));
                        }

                    }

                    @Override
                    public void onErrorResponse(String error) {
                        Log.d("Error", error);
                        progressBar.setVisibility(View.INVISIBLE); // quita el Loading
                    }
                });


    }



    // -------------------------------------------------------------------------
    // Metodo para validar las entradas del Formulario
    // -------------------------------------------------------------------------
    private Boolean getValidacionesForm() {


        if (String.valueOf(InputEdit_site.getText()).equals("")) { // Site
            Toast.makeText(getApplicationContext(), "Ingrese Nombre del Sitio", Toast.LENGTH_LONG).show();
            return false;
        }
        if (String.valueOf(InputEdit_facility.getText()).equals("")) { // Facility
            Toast.makeText(getApplicationContext(), "Ingrese Instalaciones", Toast.LENGTH_LONG).show();
            return false;
        }
        if (String.valueOf(autoCompleteTxtView_Q1.getText()).equals("")) { // Q1
            Toast.makeText(getApplicationContext(), "Error: Responda Pregunta 1", Toast.LENGTH_LONG).show();
            return false;
        }
        if (getRadioGroupBtnsValue(radioBtn_0).equals("")) { //Q2
            Toast.makeText(getApplicationContext(), "Error: Responda Pregunta 2", Toast.LENGTH_LONG).show();
            return false;
        }
        if (spinner_Q3.getSelectedItem().equals("Seleccionar")) { // Q3
            Toast.makeText(getApplicationContext(), "Error: Responda Pregunta 3", Toast.LENGTH_LONG).show();
            return false;
        }
        if (String.valueOf(autoCompleteTxtView_Q4.getText()).equals("")) { // Q4
            Toast.makeText(getApplicationContext(), "Error: Responda Pregunta 4", Toast.LENGTH_LONG).show();
            return false;
        }
        if (spinner_Q5.getSelectedItem().equals("Seleccionar")) { // Q5
            Toast.makeText(getApplicationContext(), "Error: Responda Pregunta 5", Toast.LENGTH_LONG).show();
            return false;
        }
        if (spinner_Q6.getSelectedItem().equals("Seleccionar")) { // Q6
            Toast.makeText(getApplicationContext(), "Error: Responda Pregunta 6", Toast.LENGTH_LONG).show();
            return false;
        }
        if (spinner_Q7.getSelectedItem().equals("Seleccionar")) { // Q7
            Toast.makeText(getApplicationContext(), "Error: Responda Pregunta 7", Toast.LENGTH_LONG).show();
            return false;
        }
        if (String.valueOf(autoCompleteTxtView_Q8.getText()).equals("")) { // Q8
            Toast.makeText(getApplicationContext(), "Error: Responda Pregunta 8", Toast.LENGTH_LONG).show();
            return false;
        }
        if (String.valueOf(autoCompleteTxtView_Q9.getText()).equals("")) { // Q9
            Toast.makeText(getApplicationContext(), "Error: Responda Pregunta 9", Toast.LENGTH_LONG).show();
            return false;
        }
    /*
        if (String.valueOf(autoCompleteTxtView_Q10.getText()).equals("")) { // Q10
            Toast.makeText(getApplicationContext(), "Error: Responda Pregunta 10", Toast.LENGTH_LONG).show();
            return false;
        }
    */
        if (String.valueOf(autoCompleteTxtView_Q11.getText()).equals("")) { // Q11
            Toast.makeText(getApplicationContext(), "Error: Responda Pregunta 11", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;

    }

    // -------------------------------------------------------------------------
    // Metodo para Obtener el valor del Radio ButtonGroup
    // -------------------------------------------------------------------------
    private String getRadioGroupBtnsValue(RadioGroup radioGroup) {

        // Log.d("TAG", String.valueOf(radioGroup.getCheckedRadioButtonId()));

        // Get data from Radio Buttons
        int radioBtn = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) findViewById(radioBtn);

        if (radioBtn <= 0){
            return "";
        }

        return String.valueOf(radioButton.getText());

    }

    // -------------------------------------------------------------------------
    // Metodo para mostrar el DatePicker
    // -------------------------------------------------------------------------
    public void openDatePicker(View view) {

        datePickerDialog.show();

    }

    //------metodo para inicializar el DatePicker----------
    // coloca en el boton el texto con la seleccion de la fecha del DatePicker
    private void initDatePicker() {


        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = datePickerFormat.makeDateString(dayOfMonth, month, year);
                datePickerBtn.setText(date);

            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(FormData.this, style, dateSetListener, year, month, day);
        // datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis());// limita fechas pasadas

    }




} //END class

/*
        if(str_q5.equals("Bajo (0.5%-5%)"))
            num_q5 = 0.5;
        if(str_q5.equals("Moderado (5%-20%)"))
            num_q5 = 2;
        if(str_q5.equals("Alto (20%-50%)"))
            num_q5 = 5;
        if(str_q5.equals("Muy Alto (&gt;50%)"))
            num_q5 = 10;
 */

/*
                final String site, facility, date, conducted_by, Q1, Q2, Q3, Q4, Q5, Q6, Q7, Q8, Q9, Q11;
                final boolean Q10_chk_1, Q10_chk_2, Q10_chk_3, Q10_chk_4, Q10_chk_5, Q10_chk_6, Q10_chk_7;

                site = String.valueOf(InputEdit_site.getText());
                facility = String.valueOf(InputEdit_facility.getText());
                date = String.valueOf(datePickerBtn.getText());
                conducted_by = service.getLocalData(FormData.this, "user_name"); // nombre del Usuario
                Q1 = String.valueOf(autoCompleteTxtView_Q1.getText());
                Q2 = getRadioGroupBtnsValue(radioBtn_0); // llame el metodo con el valor del radio Button
                Q3 = String.valueOf(spinner_Q3.getSelectedItem());
                Q4 = String.valueOf(autoCompleteTxtView_Q4.getText());
                Q5 = String.valueOf(spinner_Q5.getSelectedItem());
                Q6 = String.valueOf(spinner_Q6.getSelectedItem());
                Q7 = String.valueOf(spinner_Q7.getSelectedItem());
                Q8 = String.valueOf(autoCompleteTxtView_Q8.getText());
                Q9 = String.valueOf(autoCompleteTxtView_Q9.getText());
                Q10_chk_1 = checkBox1.isChecked();
                Q10_chk_2 = checkBox2.isChecked();
                Q10_chk_3 = checkBox3.isChecked();
                Q10_chk_4 = checkBox4.isChecked();
                Q10_chk_5 = checkBox5.isChecked();
                Q10_chk_6 = checkBox6.isChecked();
                Q10_chk_7 = checkBox7.isChecked();
                Q11 = String.valueOf(autoCompleteTxtView_Q11.getText());
*/
// calculosData.getCalculo_1(FormData.this, key, Q2, Q3, Q5, Q6, Q7, Q8); // metodo para calcular y guardar el informe
            /*           objectJsonReport.put("Q1", Q1);
                        objectJsonReport.put("Q2", Q2);
                        objectJsonReport.put("Q3", Q3);
                        objectJsonReport.put("Q4", Q4);
                        objectJsonReport.put("Q5", Q5);
                        objectJsonReport.put("Q6", Q6);
                        objectJsonReport.put("Q7", Q7);
                        objectJsonReport.put("Q8", Q8);
                        objectJsonReport.put("Q9", Q9);
                        objectJsonReport.put("Q10_chk_1", Q10_chk_1);
                        objectJsonReport.put("Q10_chk_2", Q10_chk_2);
                        objectJsonReport.put("Q10_chk_3", Q10_chk_3);
                        objectJsonReport.put("Q10_chk_4", Q10_chk_4);
                        objectJsonReport.put("Q10_chk_5", Q10_chk_5);
                        objectJsonReport.put("Q10_chk_6", Q10_chk_6);
                        objectJsonReport.put("Q10_chk_7", Q10_chk_7);
                        objectJsonReport.put("Q11", Q11);
                        objectJsonReport.put("site", site);
                        objectJsonReport.put("facility", facility);
                        objectJsonReport.put("date", date);
                        objectJsonReport.put("conducted", conducted_by);
                        objectJsonReport.put("source", "ANDROID");
                        objectJsonReport.put("user_mail", user_mail);
            */
// almacena en local Storage
/*              service.saveLocalData(FormData.this, "site", reporte.getSite());
                service.saveLocalData(FormData.this, "facility", reporte.getFacility());
                service.saveLocalData(FormData.this, "date", reporte.getInsp_date());
                service.saveLocalData(FormData.this, "fuente", reporte.getQ1());
*/
/*                 calculosData.setChk1(Q10_chk_1);
                   calculosData.setChk2(Q10_chk_2);
                   calculosData.setChk3(Q10_chk_3);
                   calculosData.setChk4(Q10_chk_4);
                   calculosData.setChk5(Q10_chk_5);
                   calculosData.setChk6(Q10_chk_6);
                   calculosData.setChk7(Q10_chk_7);
*/