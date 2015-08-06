package com.videumcorp.desarrolladorandroid.navigationdrawerandroiddesignsupportlibrary;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class citas extends ActionBarActivity {

    static boolean errored = false;
    Button b,b2;
    TextView statusTV;
    EditText fecha, hora;
    Spinner medico;
    int idpac;
    ProgressBar webservicePG;
    String fechaText;
    String horaText;
    int medicoText;
    int idPacint;
    String loginStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citas);
        Spinner spinner = (Spinner) findViewById(R.id.medico);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
        R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        fecha = (EditText) findViewById(R.id.fecha);
        hora = (EditText) findViewById(R.id.hora);
        idpac = 1;
        medico = (Spinner) findViewById(R.id.medico);
        statusTV = (TextView) findViewById(R.id.tv_result);
        b = (Button) findViewById(R.id.crear);
        b2 = (Button) findViewById(R.id.eliminar);
        webservicePG = (ProgressBar) findViewById(R.id.progressBar1);
        // /
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (fecha.length() != 0 && fecha.getText().toString() != "") {
                    if(hora.getText().length() != 0 && hora.getText().toString() != ""){
                        fechaText= fecha.getText().toString();
                        horaText= hora.getText().toString();
                        medicoText = 1;
                        AsyncCallWS task = new AsyncCallWS();
                        task.execute();
                    }
                    else{
                        Toast.makeText(citas.this,"Favor de ingresar la fecha", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(citas.this,"Favor de ingresar la hora", Toast.LENGTH_LONG).show();
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent eliminar = new Intent(citas.this, eliminar.class);
                startActivity(eliminar);
            }
        });

    }


    private class AsyncCallWS extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... param) {
            loginStatus = webservice.Citas(2,fechaText,horaText,medicoText,1,0,"crearCita");
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            if(!errored){
                //validamos el resultado del método
                if(!loginStatus.equals("NO")){
                    Toast.makeText(citas.this,loginStatus+fechaText+horaText, Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(citas.this,loginStatus+fechaText+horaText, Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(citas.this,"Hay un error con el Servidor", Toast.LENGTH_LONG).show();
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
        getMenuInflater().inflate(R.menu.menu_citas, menu);
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
