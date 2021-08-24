package app.absoftware.cementera.utility;

/*
Interfece usada para gestionar las respuestas de Http / Volley
 */

public interface VolleyCallback {

    void onSuccessResponse(Object result);
    void onErrorResponse(String error);

}
