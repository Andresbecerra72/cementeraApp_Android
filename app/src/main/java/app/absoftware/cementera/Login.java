package app.absoftware.cementera;

import androidx.appcompat.app.AppCompatActivity;
import app.absoftware.cementera.service.Service;
import app.absoftware.cementera.utility.VolleyCallback;
import android.content.Intent;
import android.content.pm.ActivityInfo;
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

public class Login extends AppCompatActivity {

    // Elementos
    TextInputEditText InputCorreo, InputPassword;
    TextView signUpText; // Texto para crear otra Cuenta
    TextView recoverPassText; // Texto para crear otra Recobrar el Password
    ProgressBar progressBar;
    Button LoginBtn;

    // Servicio Http
    Service service = new Service();



    // -----------------------metodos--------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // conexion con elementos
        InputCorreo = findViewById(R.id.InputEdit_Q1);
        InputPassword = findViewById(R.id.password_1_recover);
        signUpText = findViewById(R.id.signUpText);
        recoverPassText = findViewById(R.id.recoverPassText);
        LoginBtn = findViewById(R.id.LoginBtn);
        progressBar = findViewById(R.id.progress_login);

        // acciones del textView Registrar usuario: NEVEGACION
        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
                finish();

            }
        });


        // acciones del textView Recuperar Clave: NAVEGACION
        recoverPassText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), RecoverPass.class);
                startActivity(intent);
                finish();

            }
        });



        // ----------------------------------------------------------------------

        // acciones del Boton SignUpBtn
        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(!isValidEmailId(InputCorreo.getText().toString().trim())){

                    Toast.makeText(getApplicationContext(), "Ingrese un Correo Valido", Toast.LENGTH_SHORT).show();
                    return;
                }

                final  String correo, password;


                correo = String.valueOf(InputCorreo.getText());
                password = String.valueOf(InputPassword.getText());



                if (!correo.equals("") && !password.equals("")) {

                    // String url = "http://192.168.1.56/cementera-API/login";
                    // String url = "http://192.168.43.249/cementera-API/login";

                    // url Endpoint API - PHP
                    String url = getResources().getString(R.string.url) + "login";

                    progressBar.setVisibility(View.VISIBLE); // muestra el Loading

                    // Log.d("Nombre: ", nombre);

                    JSONObject objectJson = new JSONObject(); // crea el Objeto JSON con los datos
                    try {

                        //input your API parameters
                        objectJson.put("email", correo);
                        objectJson.put("password", password);

                    } catch (JSONException e) {
                        Log.i("MyTag JSONException", String.valueOf(e.getMessage()));
                    }

                    // llama el servicio Http / Volley
                    service.postData(getApplicationContext(), url, objectJson,
                            new VolleyCallback() { // implementa los metodos de la Interface
                                @Override
                                public void onSuccessResponse(Object result) {

                                    try {
                                        JSONObject data = new JSONObject(String.valueOf(result));
                                        // Log.d("TOKEN: ", data.getString("token"));
                                        // Log.d("ROLE: ", data.getString("ROLE"));
                                        progressBar.setVisibility(View.INVISIBLE); // quita el Loading

                                        // Salva el usuario en Local Storage
                                        service.saveLocalData(Login.this, "user", correo);
                                        service.saveLocalData(Login.this, "user_name", data.getString("user"));

                                        // Muestra la pagina del Menu
                                        Intent intent = new Intent(getApplicationContext(), MenuMain.class);
                                        startActivity(intent);
                                        finish();

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






    } //End OnCreate


    // metodo para validar el Correo
    private boolean isValidEmailId(String email){

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }


} //END class


/*
// CODIGO NO USADO

 String email = InputCorreo.getText().toString().trim();

        InputCorreo.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {



                if (email.matches(emailPattern) && s.length() > 0)
                {
                    Toast.makeText(getApplicationContext(),"valid email address",Toast.LENGTH_SHORT).show();
                    // or

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Invalid email address",Toast.LENGTH_SHORT).show();
                    //or

                }


            }
public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // other stuffs
        }
public void onTextChanged(CharSequence s, int start, int before, int count) {
        // other stuffs
        }
        });
 */