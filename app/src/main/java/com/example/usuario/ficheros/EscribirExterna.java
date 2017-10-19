package com.example.usuario.ficheros;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EscribirExterna extends AppCompatActivity implements View.OnClickListener{

    EditText edtOpera1,edtOpera2;
    TextView txvResul,txvPropiedades;
    Button btnSuma;

    Memoria miMemoria;

    public static final String NOMBREFICHERO = "resultado.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escribir_externa);

        edtOpera1 = (EditText)findViewById(R.id.edtOpera1);
        edtOpera2 = (EditText)findViewById(R.id.edtOpera2);
        txvPropiedades = (TextView)findViewById(R.id.txvPropiedades);
        txvResul = (TextView)findViewById(R.id.txvResul);
        btnSuma = (Button)findViewById(R.id.btnSuma);
        btnSuma.setOnClickListener(this);

        miMemoria = new Memoria(getApplicationContext());
    }

    @Override
    public void onClick(View view) {
        int r;
        String op1 = edtOpera1.getText().toString();
        String op2 = edtOpera2.getText().toString();
        String texto = "0";

        if(view == btnSuma){
            try {
                r = Integer.parseInt(op1)+Integer.parseInt(op2);
                texto = String.valueOf(r);
            }
            catch (NumberFormatException e){
                Log.e("Error", e.getMessage());
                texto = "0";
                Toast.makeText(getApplicationContext(), "Error: "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            catch (Exception e){
                Log.e("Error", e.getMessage());
                texto = "0";
                Toast.makeText(getApplicationContext(), "Error: "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            txvResul.setText(texto);

            if (miMemoria.disponibleEscritura()) {
                if (miMemoria.escribirExterna(NOMBREFICHERO, texto, false, "UTF-8"))
                    txvPropiedades.setText(miMemoria.mostrarPropiedadesExterna(NOMBREFICHERO));
                else
                    txvPropiedades.setText("Error al escribir el fichero: " + NOMBREFICHERO);
            }else
                txvPropiedades.setText("Memoria externa no disponible");
        }
    }
}
