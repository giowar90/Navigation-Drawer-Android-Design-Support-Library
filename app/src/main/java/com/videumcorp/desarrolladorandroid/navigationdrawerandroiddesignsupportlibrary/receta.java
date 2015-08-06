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


public class receta extends ActionBarActivity {

    static boolean errored = false;
    String loginStatus;
    TextView statusTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receta);

        statusTV = (TextView) findViewById(R.id.resultado);

        AsyncCallWS task = new AsyncCallWS();
        task.execute();

    }

    private class AsyncCallWS extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... param) {
            loginStatus = webservice.Recetas(1,"rece");
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            if(!errored){
                //validamos el resultado del método
                if(!loginStatus.equals("NO")){
                    statusTV.setText(loginStatus);
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
        }
        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_receta, menu);
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
