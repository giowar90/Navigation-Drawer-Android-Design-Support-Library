package com.videumcorp.desarrolladorandroid.navigationdrawerandroiddesignsupportlibrary;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class eliminar extends ActionBarActivity {

    static boolean errored = false;
    Button b;
    EditText id;
    int idcita;
    String loginStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);
        id= (EditText) findViewById(R.id.id);
        b = (Button) findViewById(R.id.eliminar);

        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (id.length() != 0 && id.getText().toString() != "") {
                        idcita= Integer.parseInt(id.getText().toString());
                        AsyncCallWS task = new AsyncCallWS();
                        task.execute();
                } else {
                    Toast.makeText(eliminar.this,"Favor de ingresar la hora", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private class AsyncCallWS extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... param) {
            loginStatus = webservice.Citas(3,"2015-07-01","00:00:00",1,1,idcita,"crearCita");
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            if(!errored){
                //validamos el resultado del método
                if(!loginStatus.equals("NO")){
                    Toast.makeText(eliminar.this,loginStatus, Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(eliminar.this,loginStatus ,Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(eliminar.this,"Hay un error con el Servidor", Toast.LENGTH_LONG).show();
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
        getMenuInflater().inflate(R.menu.menu_eliminar, menu);
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
