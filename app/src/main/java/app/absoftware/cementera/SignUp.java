package app.absoftware.cementera;

import app.absoftware.cementera.service.Service;
import app.absoftware.cementera.utility.VolleyCallback;
import androidx.appcompat.app.AppCompatActivity;
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
import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Pattern;


public class SignUp extends AppCompatActivity {

    // Elementos
    TextInputEditText InputNombre, InputCorreo, InputPassword_0, InputPassword_1;
    TextView loginText; // Texto para crear otra Cuenta
    ProgressBar progressBar;
    Button SignUpBtn;

    // Servicio Http
    Service service = new Service(); // Instancia la clase Service


    // -----------------------metodos--------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        // conexion con elementos
        loginText = findViewById(R.id.loginText);
        InputNombre = findViewById(R.id.fullname);
        InputCorreo = findViewById(R.id.correo_recover);
        InputPassword_0 = findViewById(R.id.InputEdit_Q1);
        InputPassword_1 = findViewById(R.id.password_1_recover);
        SignUpBtn = findViewById(R.id.SignUpBtn);
        progressBar = findViewById(R.id.progress_singup);

        // underline textview
        loginText.setPaintFlags(loginText.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

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

        // acciones del Boton SignUpBtn
        SignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(!isValidEmailId(InputCorreo.getText().toString().trim())){

                    Toast.makeText(getApplicationContext(), "Ingrese un Correo Valido", Toast.LENGTH_SHORT).show();
                    return;
                }


                final  String nombre, correo, password_0, password_1;

                nombre = String.valueOf(InputNombre.getText());
                correo = String.valueOf(InputCorreo.getText());
                password_0 = String.valueOf(InputPassword_0.getText());
                password_1 = String.valueOf(InputPassword_1.getText());

                if(password_0.equals("") && password_1.equals("") || !password_0.equals(password_1)) {
                    Toast.makeText(getApplicationContext(),"Password NO coincide", Toast.LENGTH_LONG).show();
                    return;
                }


               if (!nombre.equals("") && !correo.equals("") && !password_0.equals("") && !password_1.equals("")) {

                   // url Endpoint API - PHP
                   String url = getResources().getString(R.string.url) + "client";


                   progressBar.setVisibility(View.VISIBLE); // muestra el Loading

                   // Log.d("Nombre: ", nombre);

                   JSONObject objectJson = new JSONObject(); // crea el Objeto JSON con los datos
                   try {

                       //input your API parameters
                       objectJson.put("name", nombre);
                       objectJson.put("email", correo);
                       objectJson.put("password", password_0);

                   } catch (JSONException e) {
                       Log.i("MyTag JSONException", String.valueOf(e.getMessage()));
                   }

                   // lla ma el servicio Http / Volley
                   service.postData(getApplicationContext(), url, objectJson,
                           new VolleyCallback() { // implementa los metodos de la Interface
                               @Override
                               public void onSuccessResponse(Object result) {

                                   try {
                                       JSONObject data = new JSONObject(String.valueOf(result));
                                       Log.d("Response", data.getString("message"));
                                       progressBar.setVisibility(View.INVISIBLE); // quita el Loading

                                       InputNombre.setText("");
                                       InputCorreo.setText("");
                                       InputPassword_0.setText("");
                                       InputPassword_1.setText("");

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




    } // End OnCreate


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





