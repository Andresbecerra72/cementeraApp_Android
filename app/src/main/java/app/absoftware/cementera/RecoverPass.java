package app.absoftware.cementera;

import androidx.appcompat.app.AppCompatActivity;
import app.absoftware.cementera.service.Service;
import app.absoftware.cementera.utility.VolleyCallback;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Pattern;

public class RecoverPass extends AppCompatActivity {

    // Elementos
    TextInputLayout textInputPassword_0_recover, textInputPassword_1_recover;
    TextInputEditText InputCorreo, InputPassword_0, InputPassword_1;
    TextView loginText; // Texto para navegar pagina de Login
    ProgressBar progressBar;
    Button getUserBtn;
    Button RecoverPassBtn;

    // Servicio Http
    Service service = new Service();

    String email;
    String id;



    // -----------------------metodos--------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover_pass);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // conexion con elementos
        textInputPassword_0_recover = findViewById(R.id.textInputPassword_0_recover);
        textInputPassword_1_recover = findViewById(R.id.textInputPassword_1_recover);
        loginText = findViewById(R.id.loginText);
        InputCorreo = findViewById(R.id.correo_recover);
        InputPassword_0 = findViewById(R.id.InputEdit_Q1);
        InputPassword_1 = findViewById(R.id.password_1_recover);
        getUserBtn = findViewById(R.id.getUserBtn);
        RecoverPassBtn = findViewById(R.id.RecoverPassBtn);
        progressBar = findViewById(R.id.progress_recover);

        // underline textview
        loginText.setPaintFlags(loginText.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        email = "";
        id = "";



        // acciones del textView Inicio de sesion: NAVEGACION
        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();

            }
        });



        // ----------------------------------------------------------------------

        // acciones del Boton getUserBtn permite validar el correo / usuario
        getUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final  String correo;

                correo = String.valueOf(InputCorreo.getText()).trim();


                if(!isValidEmailId(InputCorreo.getText().toString().trim())){

                    Toast.makeText(getApplicationContext(), "Ingrese un Correo Valido", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (!correo.equals(""))  {

                   // url Endpoint API - PHP
                    String url = getResources().getString(R.string.url) + "client_email/" + correo;

                    progressBar.setVisibility(View.VISIBLE); // muestra el Loading

                    // Log.d("Correo: ", correo);


                    // llama el servicio Http / Volley
                    service.getData(getApplicationContext(), url,
                            new VolleyCallback() { // implementa los metodos de la Interface
                                @Override
                                public void onSuccessResponse(Object result) {


                                        // Log.d("Response", String.valueOf(result));

                                    progressBar.setVisibility(View.INVISIBLE); // quita el Loading


                                    try {

                                        JSONObject data = new JSONObject(String.valueOf(result)); // convierte la repuesta en Objeto JSON

                                        // Log.d(" Data Message: ", String.valueOf(data.getString("message")));

                                        if (!data.get("message").equals("Data Not Found")){ // si hay Clients


                                            JSONArray jsonArray = data.getJSONArray("client"); // Se almacena solo el cliente
                                            JSONObject jsonObject = jsonArray.getJSONObject(0); // se convierte en un Objeto JSON

                                            // Log.d(" Client Email: ", String.valueOf(jsonObject.get("Table_AdminUsers_email")));


                                                if (String.valueOf(jsonObject.get("Table_AdminUsers_email")).equals(correo)) {

                                                    email = String.valueOf(jsonObject.get("Table_AdminUsers_email"));
                                                    id = String.valueOf(jsonObject.get("Table_AdminUsers_id"));

                                                    // cambios de Visibility
                                                    getUserBtn.setVisibility(View.INVISIBLE);
                                                    textInputPassword_0_recover.setVisibility(View.VISIBLE);
                                                    textInputPassword_1_recover.setVisibility(View.VISIBLE);
                                                    RecoverPassBtn.setVisibility(View.VISIBLE);




                                                }else {
                                                    Toast.makeText(getApplicationContext(),"Usuario No Valido", Toast.LENGTH_LONG).show();
                                                }


                                        }else { // NO hay CLients

                                            Toast.makeText(getApplicationContext(),"Usuario No Valido", Toast.LENGTH_LONG).show();

                                        }


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


                }else{

                    Toast.makeText(getApplicationContext(),"Campos Incompletos", Toast.LENGTH_LONG).show();

                }

            }
        });
        // ----------------------------------------------------------------------

        // acciones del Boton RecoverPassBtn permite cambiar el Passwor del Client
        RecoverPassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar.setVisibility(View.VISIBLE); // muestra el Loading


                final  String password_0, password_1;

                password_0 = String.valueOf(InputPassword_0.getText());
                password_1 = String.valueOf(InputPassword_1.getText());


                if(password_0.equals("") && password_1.equals("") || !password_0.equals(password_1)) {
                    Toast.makeText(getApplicationContext(),"Password NO coincide", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.INVISIBLE); // quita el Loading
                    return;
                }

                if(email.equals("")) {
                    Toast.makeText(getApplicationContext(),"Valide Nuevamente el Usuario", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.INVISIBLE); // quita el Loading
                    return;
                }


                JSONObject objectJson = new JSONObject(); // crea el Objeto JSON con los datos
                try {
                    //input your API parameters
                    objectJson.put("email", email);
                    objectJson.put("password", password_0);

                } catch (JSONException e) {
                    Log.i("MyTag JSONException", String.valueOf(e.getMessage()));
                }

                // url Endpoint API - PHP
                String url = getResources().getString(R.string.url) + "pswd/" + id;

                // llama el servicio Http / Volley
                service.putData(getApplicationContext(), url, objectJson, new VolleyCallback() {
                    @Override
                    public void onSuccessResponse(Object result) {
                        // Log.d("Response", String.valueOf(result));
                        progressBar.setVisibility(View.INVISIBLE); // quita el Loading

                        // cambios de Visibility
                        getUserBtn.setVisibility(View.VISIBLE);
                        textInputPassword_0_recover.setVisibility(View.INVISIBLE);
                        textInputPassword_1_recover.setVisibility(View.INVISIBLE);
                        RecoverPassBtn.setVisibility(View.INVISIBLE);
                        // limpia los Inputs Fields
                        InputCorreo.setText("");
                        InputPassword_0.setText("");
                        InputPassword_1.setText("");

                    }

                    @Override
                    public void onErrorResponse(String error) {
                        // Log.d("Error", error);
                        progressBar.setVisibility(View.INVISIBLE); // quita el Loading

                    }
                });

            }
        });


        // ----------------------------------------------------------------------













    }// End OnCreate


    //--------------------------
    // metodo para validar el Correo
    private boolean isValidEmailId(String email){

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }

} // END class


// ***************CODIGO NO USADO*********************************
/*
  // ----------------------------------------------------------------------

        // acciones del Boton getUserBtn permite validar el correo / usuario
        getUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final  String correo;

                correo = String.valueOf(InputCorreo.getText());



                if (!correo.equals(""))  {

                    // String url = "http://192.168.1.56/cementera-API/client_email";
                    // String url = "http://192.168.43.249/cementera-API/client_email";
                    String url = getResources().getString(R.string.url) + "client";

                    progressBar.setVisibility(View.VISIBLE); // muestra el Loading

                    // Log.d("Correo: ", correo);


                    // llama el servicio Http / Volley
                    service.getData(getApplicationContext(), url,
                            new VolleyCallback() { // implementa los metodos de la Interface
                                @Override
                                public void onSuccessResponse(Object result) {


                                        // Log.d("Response", String.valueOf(result));

                                    progressBar.setVisibility(View.INVISIBLE); // quita el Loading

                                    Boolean flag = false;


                                    try {

                                        JSONObject data = new JSONObject(String.valueOf(result)); // convierte la repuesta en Objeto JSON
                                        // Log.d(" Data Message: ", String.valueOf(data.getJSONArray("message")));

                                        if (!data.getJSONArray("message").equals("Table Empty")){ // si hay Clients

                                            JSONArray jsonArray = data.getJSONArray("message"); // solo extrae la lista de Usuarios('message') del Objeto JSON

                                            ArrayList<Object> listObject = new ArrayList<Object>(); // se crea un nueva lista de Objetos para almacenar

                                            for(int i = 0; i < jsonArray.length(); i++) // se extrae cada Usuario/client del JSONArray
                                            {
                                                listObject.add(i, jsonArray.get(i)); // se crea la lista de Objetos Usuarios/client
                                            }



                                            for (Object item :listObject) { // Foreach de la lista de Objetos Usuarios/client

                                                JSONObject client = new JSONObject(String.valueOf(item));

                                                // Log.d("TAG", String.valueOf(client.get("Table_AdminUsers_email")));

                                                if (String.valueOf(client.get("Table_AdminUsers_email")).equals(correo)) {

                                                    // cambios de Visibility
                                                    getUserBtn.setVisibility(View.INVISIBLE);
                                                    textInputPassword_0_recover.setVisibility(View.VISIBLE);
                                                    textInputPassword_1_recover.setVisibility(View.VISIBLE);
                                                    RecoverPassBtn.setVisibility(View.VISIBLE);
                                                    flag = true;
                                                    return;
                                                }else {
                                                    flag = false;
                                                }
                                            }


                                        }else { // NO hay CLients

                                            Toast.makeText(getApplicationContext(),"Usuario No Valido", Toast.LENGTH_LONG).show();

                                        }

                                        if (!flag){
                                            Toast.makeText(getApplicationContext(),"Usuario No Valido", Toast.LENGTH_LONG).show();
                                        }


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


                }else{

                    Toast.makeText(getApplicationContext(),"Campos Incompletos", Toast.LENGTH_SHORT).show();

                }

            }
        });
        // ----------------------------------------------------------------------

 */