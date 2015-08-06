package com.videumcorp.desarrolladorandroid.navigationdrawerandroiddesignsupportlibrary;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;


public class login extends ActionBarActivity {

    static boolean errored = false;
    Button b;
    TextView statusTV;
    EditText userNameET , passWordET;
    ProgressBar webservicePG;
    String editTextUsername;
    String loginStatus;
    String editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userNameET = (EditText) findViewById(R.id.editText1);
        passWordET = (EditText) findViewById(R.id.editText2);
        statusTV = (TextView) findViewById(R.id.tv_result);
        b = (Button) findViewById(R.id.button1);
        webservicePG = (ProgressBar) findViewById(R.id.progressBar1);
        // /
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (userNameET.getText().length() != 0 && userNameET.getText().toString() != "") {
                    if(passWordET.getText().length() != 0 && passWordET.getText().toString() != ""){
                        editTextUsername = userNameET.getText().toString();
                        editTextPassword = passWordET.getText().toString();
                        statusTV.setText("");
                        AsyncCallWS task = new AsyncCallWS();
                        task.execute();
                    }
                    else{
                        statusTV.setText("Favor de ingresar su clave");
                    }
                } else {statusTV.setText("Favor de ingresar su Usuario");
                }
            }
        });
    }
    private class AsyncCallWS extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... param) {
            loginStatus = webservice.LlamadaLogin(editTextUsername,editTextPassword,"login");
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            webservicePG.setVisibility(View.INVISIBLE);
            Intent ActivitySiguiente = new Intent(login.this,MainActivity.class);
            //si ocurre un error
            if(!errored){
                //validamos el resultado del método
                if(!loginStatus.equals("NO")){
                    statusTV.setText("SI PASAS "+ loginStatus);
                    startActivity(ActivitySiguiente);
                }else{
                    statusTV.setText("DATOS INCORRECTOS "+ loginStatus);
                }
            }else{
                statusTV.setText("Hay un error con el Servidor");
            }
            errored = false;
        }
        @Override
        protected void onPreExecute() {
            webservicePG.setVisibility(View.VISIBLE);
        }
        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
