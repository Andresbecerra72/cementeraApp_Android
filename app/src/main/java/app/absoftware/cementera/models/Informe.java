package app.absoftware.cementera.models;

import android.content.Context;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;
import app.absoftware.cementera.R;

import app.absoftware.cementera.service.Service;
import app.absoftware.cementera.utility.VolleyCallback;

public class Informe {

    Service service = new Service();

    private  int id;
    private String name;
    private String title;
    private String label;
    private String level;
    private String calculo_1;
    private String calculo_1_num;
    private String calculo_2;
    private String calculo_2_num;
    private String calculo_3;
    private String calculo_3_num;
    private String calculo_4;
    private String calculo_4_num;
    private String calculo_5;
    private String calculo_5_num;
    private String controles_num; // en porcentaje
    private String riesgo_total;
    private String riesgo_total_num;
    private String site;
    private String facility;
    private String ind_date;
    private String conducted_by;
    private String resultado_nivel_riesgo;
    private String exp_material_particulado;
    private String control_polvo_fugitivo;
    // controles existentes
    private String Q10_chk_1;
    private String Q10_chk_2;
    private String Q10_chk_3;
    private String Q10_chk_4;
    private String Q10_chk_5;
    private String Q10_chk_6;
    private String Q10_chk_7;

    // contructor con parametros

    public Informe(int id, String name, String title, String label, String level, String calculo_1, String calculo_1_num, String calculo_2, String calculo_2_num, String calculo_3, String calculo_3_num, String calculo_4, String calculo_4_num, String calculo_5, String calculo_5_num, String controles_num, String riesgo_total, String riesgo_total_num, String site, String facility, String ind_date, String conducted_by, String resultado_nivel_riesgo, String exp_material_particulado, String control_polvo_fugitivo, String q10_chk_1, String q10_chk_2, String q10_chk_3, String q10_chk_4, String q10_chk_5, String q10_chk_6, String q10_chk_7) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.label = label;
        this.level = level;
        this.calculo_1 = calculo_1;
        this.calculo_1_num = calculo_1_num;
        this.calculo_2 = calculo_2;
        this.calculo_2_num = calculo_2_num;
        this.calculo_3 = calculo_3;
        this.calculo_3_num = calculo_3_num;
        this.calculo_4 = calculo_4;
        this.calculo_4_num = calculo_4_num;
        this.calculo_5 = calculo_5;
        this.calculo_5_num = calculo_5_num;
        this.controles_num = controles_num;
        this.riesgo_total = riesgo_total;
        this.riesgo_total_num = riesgo_total_num;
        this.site = site;
        this.facility = facility;
        this.ind_date = ind_date;
        this.conducted_by = conducted_by;
        this.resultado_nivel_riesgo = resultado_nivel_riesgo;
        this.exp_material_particulado = exp_material_particulado;
        this.control_polvo_fugitivo = control_polvo_fugitivo;
        this.Q10_chk_1 = q10_chk_1;
        this.Q10_chk_2 = q10_chk_2;
        this.Q10_chk_3 = q10_chk_3;
        this.Q10_chk_4 = q10_chk_4;
        this.Q10_chk_5 = q10_chk_5;
        this.Q10_chk_6 = q10_chk_6;
        this.Q10_chk_7 = q10_chk_7;
    }



    // contructor vacio
    public Informe() {

    }

    // metodos getter y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCalculo_1() {
        return calculo_1;
    }

    public void setCalculo_1(String calculo_1) {
        this.calculo_1 = calculo_1;
    }

    public String getCalculo_1_num() {
        return calculo_1_num;
    }

    public void setCalculo_1_num(String calculo_1_num) {
        this.calculo_1_num = calculo_1_num;
    }

    public String getCalculo_2() {
        return calculo_2;
    }

    public void setCalculo_2(String calculo_2) {
        this.calculo_2 = calculo_2;
    }

    public String getCalculo_2_num() {
        return calculo_2_num;
    }

    public void setCalculo_2_num(String calculo_2_num) {
        this.calculo_2_num = calculo_2_num;
    }

    public String getCalculo_3() {
        return calculo_3;
    }

    public void setCalculo_3(String calculo_3) {
        this.calculo_3 = calculo_3;
    }

    public String getCalculo_3_num() {
        return calculo_3_num;
    }

    public void setCalculo_3_num(String calculo_3_num) {
        this.calculo_3_num = calculo_3_num;
    }

    public String getCalculo_4() {
        return calculo_4;
    }

    public void setCalculo_4(String calculo_4) {
        this.calculo_4 = calculo_4;
    }

    public String getCalculo_4_num() {
        return calculo_4_num;
    }

    public void setCalculo_4_num(String calculo_4_num) {
        this.calculo_4_num = calculo_4_num;
    }

    public String getCalculo_5() {
        return calculo_5;
    }

    public void setCalculo_5(String calculo_5) {
        this.calculo_5 = calculo_5;
    }

    public String getCalculo_5_num() {
        return calculo_5_num;
    }

    public void setCalculo_5_num(String calculo_5_num) {
        this.calculo_5_num = calculo_5_num;
    }

    public String getRiesgo_total() {
        return riesgo_total;
    }

    public void setRiesgo_total(String riesgo_total) {
        this.riesgo_total = riesgo_total;
    }

    public String getRiesgo_total_num() {
        return riesgo_total_num;
    }

    public void setRiesgo_total_num(String riesgo_total_num) {
        this.riesgo_total_num = riesgo_total_num;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public String getInd_date() {
        return ind_date;
    }

    public void setInd_date(String ind_date) {
        this.ind_date = ind_date;
    }

    public String getConducted_by() {
        return conducted_by;
    }

    public void setConducted_by(String conducted_by) {
        this.conducted_by = conducted_by;
    }

    public String isQ10_chk_1() {
        return Q10_chk_1;
    }

    public void setQ10_chk_1(String q10_chk_1) {
        Q10_chk_1 = q10_chk_1;
    }

    public String isQ10_chk_2() {
        return Q10_chk_2;
    }

    public void setQ10_chk_2(String q10_chk_2) {
        Q10_chk_2 = q10_chk_2;
    }

    public String isQ10_chk_3() {
        return Q10_chk_3;
    }

    public void setQ10_chk_3(String q10_chk_3) {
        Q10_chk_3 = q10_chk_3;
    }

    public String isQ10_chk_4() {
        return Q10_chk_4;
    }

    public void setQ10_chk_4(String q10_chk_4) {
        Q10_chk_4 = q10_chk_4;
    }

    public String isQ10_chk_5() {
        return Q10_chk_5;
    }

    public void setQ10_chk_5(String q10_chk_5) {
        Q10_chk_5 = q10_chk_5;
    }

    public String isQ10_chk_6() {
        return Q10_chk_6;
    }

    public void setQ10_chk_6(String q10_chk_6) {
        Q10_chk_6 = q10_chk_6;
    }

    public String isQ10_chk_7() {
        return Q10_chk_7;
    }

    public void setQ10_chk_7(String q10_chk_7) {
        Q10_chk_7 = q10_chk_7;
    }

    public String getControles_num() {
        return controles_num;
    }

    public void setControles_num(String controles_num) {
        this.controles_num = controles_num;
    }

    public String getResultado_nivel_riesgo() {
        return resultado_nivel_riesgo;
    }

    public void setResultado_nivel_riesgo(String resultado_nivel_riesgo) {
        this.resultado_nivel_riesgo = resultado_nivel_riesgo;
    }

    public String getExp_material_particulado() {
        return exp_material_particulado;
    }

    public void setExp_material_particulado(String exp_material_particulado) {
        this.exp_material_particulado = exp_material_particulado;
    }

    public String getControl_polvo_fugitivo() {
        return control_polvo_fugitivo;
    }

    public void setControl_polvo_fugitivo(String control_polvo_fugitivo) {
        this.control_polvo_fugitivo = control_polvo_fugitivo;
    }


    @Override
    public String toString() {
        return "Informe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", label='" + label + '\'' +
                ", level='" + level + '\'' +
                ", calculo_1='" + calculo_1 + '\'' +
                ", calculo_1_num='" + calculo_1_num + '\'' +
                ", calculo_2='" + calculo_2 + '\'' +
                ", calculo_2_num='" + calculo_2_num + '\'' +
                ", calculo_3='" + calculo_3 + '\'' +
                ", calculo_3_num='" + calculo_3_num + '\'' +
                ", calculo_4='" + calculo_4 + '\'' +
                ", calculo_4_num='" + calculo_4_num + '\'' +
                ", calculo_5='" + calculo_5 + '\'' +
                ", calculo_5_num='" + calculo_5_num + '\'' +
                ", controles_num='" + controles_num + '\'' +
                ", riesgo_total='" + riesgo_total + '\'' +
                ", riesgo_total_num='" + riesgo_total_num + '\'' +
                ", site='" + site + '\'' +
                ", facility='" + facility + '\'' +
                ", ind_date='" + ind_date + '\'' +
                ", conducted_by='" + conducted_by + '\'' +
                ", resultado_nivel_riesgo='" + resultado_nivel_riesgo + '\'' +
                ", exp_material_particulado='" + exp_material_particulado + '\'' +
                ", control_polvo_fugitivo='" + control_polvo_fugitivo + '\'' +
                ", Q10_chk_1=" + Q10_chk_1 +
                ", Q10_chk_2=" + Q10_chk_2 +
                ", Q10_chk_3=" + Q10_chk_3 +
                ", Q10_chk_4=" + Q10_chk_4 +
                ", Q10_chk_5=" + Q10_chk_5 +
                ", Q10_chk_6=" + Q10_chk_6 +
                ", Q10_chk_7=" + Q10_chk_7 +
                '}';
    }

    // -------------------------------------------------------------------------
    // Metodos para Guardar en SharePreference los datos del fomulario y hacer los CALCULOS
    // -------------------------------------------------------------------------
    // controles existentes en el área
    public void getCalculo_Controles(Context context, String key ) {

        int result = 0;

        if (Q10_chk_1.equals("true"))
            result = result + 20;
        if (Q10_chk_2.equals("true"))
            result = result + 20;
        if (Q10_chk_3.equals("true"))
            result = result + 20;
        if (Q10_chk_4.equals("true"))
            result = result + 15;
        if (Q10_chk_5.equals("true"))
            result = result + 10;
        if (Q10_chk_6.equals("true"))
            result = result + 10;
        if (Q10_chk_7.equals("true"))
            result = result + 5;

        this.controles_num = String.valueOf(result);

        // service.saveLocalData(context, key + "controles_num", String.valueOf(result));


    }
    // Clasificación del riesgo (cálculo 1).
    public void getCalculo_1(
            Context context,
            String key,
            String str_q2,
            String str_q3,
            String Q5,
            String Q6,
            String Q7,
            String Q8) { // clasificacion del riesgo



        String clasificacion_Riesgo = "NA";
        int result = 0;
        int num_q2 = 0;
        int num_q3 = 0;


        if(str_q2.equals("SI"))
            num_q2 = 0;
        if(str_q2.equals("NO"))
            num_q2 = 10;
        if(str_q2.equals("N/A"))
            num_q2 = 10;


        if(str_q3.equals("Bajo"))
            num_q3 = 0;
        if(str_q3.equals("Moderado"))
            num_q3 = 4;
        if(str_q3.equals("Alto"))
            num_q3 = 8;
        if(str_q3.equals("Muy Alto"))
            num_q3 = 10;

        result = num_q2 + num_q3;

        if(result >= 10)
            clasificacion_Riesgo = "Muy Alto";
        if(result == 8)
            clasificacion_Riesgo = "Alto";
        if(result == 4)
            clasificacion_Riesgo = "Moderado";
        if(result == 0)
            clasificacion_Riesgo = "Bajo";

        this.calculo_1 = clasificacion_Riesgo;
        this.calculo_1_num = String.valueOf(result);

        // Salva el usuario en Local Storage
        // service.saveLocalData(context, key + "calculo_1", clasificacion_Riesgo);
        // service.saveLocalData(context, key + "calculo_1_num", String.valueOf(result));

        getCalculo_2(context, key, Q5, Q6, Q7, Q8); // metodo para calcular y guardar el informe

    }


    //(cálculo 2) Puntaje general de riesgo de polvo
    private void getCalculo_2(
            Context context,
            String key,
            String str_q5,
            String str_q6,
            String str_q7,
            String str_q8 ) {

        String riesgo_exposicion_polvo = "NA";
        double result = 0;
        int num_q6 = 0;
        int num_q7 = 0;
        int num_q8 = 0;
        double num_q5 = 0;


        if(str_q5.equals("Bajo"))
            num_q5 = 0.5;
        if(str_q5.equals("Moderado"))
            num_q5 = 2;
        if(str_q5.equals("Alto"))
            num_q5 = 5;
        if(str_q5.equals("Muy Alto"))
            num_q5 = 10;

        if(str_q6.equals("Bajo"))
            num_q6 = 1;
        if(str_q6.equals("Moderado"))
            num_q6 = 2;

        if(str_q6.equals("Alto"))
            num_q6 = 3;
        if(str_q6.equals("Muy Alto"))
            num_q6 = 4;

        if(str_q7.equals("Bajo"))
            num_q7 = 1;
        if(str_q7.equals("Moderado"))
            num_q7 = 2;
        if(str_q7.equals("Alto"))
            num_q7 = 3;
        if(str_q7.equals("Muy Alto"))
            num_q7 = 4;

        if(str_q8.equals("MENOR 1 HORA"))
            num_q8 = 1;
        if(str_q8.equals("DE 1 A 4 HORAS"))
            num_q8 = 2;
        if(str_q8.equals("MAYOR 4 HORAS"))
            num_q8 = 3;

        result = num_q5 + num_q6 + num_q7 + num_q8;

        if(result >= 13)
            riesgo_exposicion_polvo = "Muy Alto";
        if(result >= 8 && result < 13)
            riesgo_exposicion_polvo = "Alto";
        if(result >= 4 && result < 8)
            riesgo_exposicion_polvo = "Moderado";
        if(result < 4)
            riesgo_exposicion_polvo = "Bajo";

        this.calculo_2 = riesgo_exposicion_polvo;
        this.calculo_2_num = String.valueOf(result);

        // Salva el usuario en Local Storage
       // service.saveLocalData(context, key + "calculo_2", riesgo_exposicion_polvo);
       // service.saveLocalData(context, key + "calculo_2_num", String.valueOf(result));

        getCalculo_3(context, key, num_q5, num_q6, num_q7, num_q8); // metodo para calcular y guardar el informe



    }

    //(cálculo 3) Combinación de puntaje general de riesgo de polvo y medido
    private void getCalculo_3(Context context,
                              String key,
                              double num_q5,
                              int num_q6,
                              int num_q7,
                              int num_q8 ) {

        String riesgo_polvo_medido = "";
        double result = 0;

        // String calculo_1 = service.getLocalData(context,key + "calculo_1_num");
        // String calculo_2 = service.getLocalData(context,key + "calculo_2_num");

        int cal_1 = Integer.parseInt(calculo_1_num);
        double cal_2 = Double.parseDouble(calculo_2_num);

        // riesgo_polvo_medido = service.getLocalData(context,key + "calculo_2");
        riesgo_polvo_medido = calculo_2;

        if(cal_2 < 10)
            result = cal_2;
        if(cal_2 > 10)
            result = cal_2 + cal_1;



        this.calculo_3 = riesgo_polvo_medido;
        this.calculo_3_num = String.valueOf(result);

        // Salva el usuario en Local Storage
       // service.saveLocalData(context, key + "calculo_3", riesgo_polvo_medido);
       // service.saveLocalData(context, key + "calculo_3_num", String.valueOf(result));

        getCalculo_4(context, key, cal_1, num_q5, num_q6, num_q7, num_q8); // metodo para calcular y guardar el informe

    }


    // (cálculo 4) Puntaje de riesgo de polvo fugitivo
    private void getCalculo_4(Context context,
                              String key,
                              int cal_1,
                              double num_q5,
                              int num_q6,
                              int num_q7,
                              int num_q8 ) {

        String riesgo_polvo_fugitivo = "polvo_fugitivo";
        double result = 0;

        if(cal_1 < 10)
            result = num_q5 + num_q6 + num_q8;
        if(cal_1 == 10)
            result = num_q5 + num_q6 + num_q8 + 2;

        this.calculo_4 = riesgo_polvo_fugitivo;
        this.calculo_4_num = String.valueOf(result);

        // Salva el usuario en Local Storage
       // service.saveLocalData(context, key + "calculo_4", riesgo_polvo_fugitivo);
       // service.saveLocalData(context, key + "calculo_4_num", String.valueOf(result));

        getCalculo_5(context, key, cal_1, num_q5, num_q7,  num_q8); // metodo para calcular y guardar el informe

    }

    // (cálculo 5) Puntaje de riesgo del derrame
    private void getCalculo_5(Context context,
                              String key,
                              int cal_1,
                              double num_q5,
                              int num_q7,
                              int num_q8 ) {

        String riesgo_derrame = "riesgo_derrame";
        double result = 0;

        if(cal_1 < 10)
            result = num_q5 + num_q7 + num_q8;
        if(cal_1 == 10)
            result = num_q5 + num_q7 + num_q8 + 2;


        this.calculo_5 = riesgo_derrame;
        this.calculo_5_num = String.valueOf(result);

        // Salva el usuario en Local Storage
       // service.saveLocalData(context, key + "calculo_5", riesgo_derrame);
       // service.saveLocalData(context, key + "calculo_5_num", String.valueOf(result));

        postChart_Data(context);

    }

    // -------------------------------------------------------------------------
    // metodo para guardar registro del CHART en BD
    // -------------------------------------------------------------------------
    private void postChart_Data(Context context) {


        final String user_mail;

        this.conducted_by = service.getLocalData(context, "user_name"); // nombre del Usuario

        this.name = this.site.trim() + "_" + this.facility.trim() + "_" + this.ind_date;
        this.title = "Informe " + this.site + " " + this.ind_date;
        this.level = this.calculo_2;

        user_mail = service.getLocalData(context, "user");

        JSONObject objectJsonChart = new JSONObject(); // crea el Objeto JSON con los datos
        try {
            //input your API parameters
            objectJsonChart.put("name", this.name );
            objectJsonChart.put("title", this.title );
            objectJsonChart.put("Label", this.label );
            objectJsonChart.put("Level", this.level );
            objectJsonChart.put("Calculo_1", this.calculo_1 );
            objectJsonChart.put("Calculo_1_num", this.calculo_1_num );
            objectJsonChart.put("Calculo_2", this.calculo_2 );
            objectJsonChart.put("Calculo_2_num", this.calculo_2_num );
            objectJsonChart.put("Calculo_3", this.calculo_3 );
            objectJsonChart.put("Calculo_3_num", this.calculo_3_num );
            objectJsonChart.put("Calculo_4", this.calculo_4 );
            objectJsonChart.put("Calculo_4_num", this.calculo_4_num );
            objectJsonChart.put("Calculo_5", this.calculo_5 );
            objectJsonChart.put("Calculo_5_num", this.calculo_5_num );
            objectJsonChart.put("Controles_num", this.controles_num );
            objectJsonChart.put("Q10_chk_1", this.Q10_chk_1);
            objectJsonChart.put("Q10_chk_2", this.Q10_chk_2);
            objectJsonChart.put("Q10_chk_3", this.Q10_chk_3);
            objectJsonChart.put("Q10_chk_4", this.Q10_chk_4);
            objectJsonChart.put("Q10_chk_5", this.Q10_chk_5);
            objectJsonChart.put("Q10_chk_6", this.Q10_chk_6);
            objectJsonChart.put("Q10_chk_7", this.Q10_chk_7);
            objectJsonChart.put("Riesgo_total", "0" );
            objectJsonChart.put("Riesgo_total_num", "0" );
            objectJsonChart.put("site", this.site );
            objectJsonChart.put("facility", this.facility );
            objectJsonChart.put("date", this.ind_date );
            objectJsonChart.put("conducted", this.conducted_by );
            objectJsonChart.put("source", "ANDROID");
            objectJsonChart.put("user_mail", user_mail);

        } catch (JSONException e) {
            Log.i("MyTag JSONException", String.valueOf(e.getMessage()));
        }

        // Log.d("ObjcJSON_Chart", String.valueOf(objectJsonChart));

        // url Endpoint API - PHP
        String url =  context.getResources().getString(R.string.url) + "chart";

        // Log.d("TAG", url);

        // **************************************
        PostByService(context, url, objectJsonChart); // envia Reporte a BD Hosting;


    }


    // ----------------------------------------------------------------------
    // METODO PARA ALMACENAR DATA EN BD
    // ----------------------------------------------------------------------
    private void PostByService(Context context, String url, JSONObject objectJson) {

        // lla ma el servicio Http / Volley
        service.postData(context, url, objectJson,
                new VolleyCallback() { // implementa los metodos de la Interface
                    @Override
                    public void onSuccessResponse(Object result) {

                        try {
                            JSONObject data = new JSONObject(String.valueOf(result));
                            // Log.d("Response", data.getString("message"));



                        } catch (JSONException e) {
                            Log.i("MyTag JSONException", String.valueOf(e.getMessage()));
                        }

                    }

                    @Override
                    public void onErrorResponse(String error) {
                        // Log.d("Error", error);

                    }
                });


    }






}// END class
