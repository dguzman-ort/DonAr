package com.example.donar;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends DonArToolBar implements View.OnClickListener {

    private ImageButton donaciones;
    private ImageButton voluntarios;
    private ImageButton pacientes;
    private ImageButton reportes;
    private TextView id;
    private TextView usuario;
    boolean active;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configView();
    }

    private void configView() {
        donaciones = (ImageButton) findViewById(R.id.imbDonaciones);
        voluntarios = (ImageButton) findViewById(R.id.imbVoluntarios);
        pacientes = (ImageButton) findViewById(R.id.imbPacientes);
        reportes = (ImageButton) findViewById(R.id.imbReportes);
        id = (TextView)findViewById(R.id.txtId);
        usuario = (TextView) findViewById(R.id.txtUsuario);

        donaciones.setOnClickListener(this);
        voluntarios.setOnClickListener(this);
        pacientes.setOnClickListener(this);
        reportes.setOnClickListener(this);

        active = (verificarConexion() && id.getText().toString().compareTo(" ") != 0);
        //active = true; //SOLO PRUEBA

        if (active) {
            donaciones.setImageResource(R.mipmap.boton_donaciones);
            voluntarios.setImageResource(R.mipmap.boton_voluntarios);
            pacientes.setImageResource(R.mipmap.boton_pacientes);
            reportes.setImageResource(R.mipmap.boton_reportes);
        }
        else {
            donaciones.setImageResource(R.mipmap.boton_donaciones_gris);
            voluntarios.setImageResource(R.mipmap.boton_voluntarios_gris);
            pacientes.setImageResource(R.mipmap.boton_pacientes_gris);
            reportes.setImageResource(R.mipmap.boton_reportes_gris);
        }

        dataChangeToolbar("Pacientes -  Consulta", usuario.getText().toString(),
                id.getText().toString(), false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void dataChangeToolbar(String titulo, String usuario, String idUsuario, boolean verBotones) {
        super.dataChangeToolbar(titulo, usuario, idUsuario, verBotones);
    }

    private boolean verificarConexion() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return (networkInfo != null && networkInfo.isConnected());
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        if(active){
            switch(v.getId())
            {
                case R.id.imbDonaciones:
                    intent = new Intent(v.getContext(), pacienteSolicitarConsulta.class); //prueba
                    break;
                case R.id.imbPacientes:
                    intent = new Intent(v.getContext(), pacienteSolicitarConsulta.class);//prueba
                    break;
                case R.id.imbReportes:
                    intent = new Intent(v.getContext(), pacienteSolicitarConsulta.class);//prueba
                    break;
                case R.id.imbVoluntarios:
                    intent = new Intent(v.getContext(), voluntariosAutoMach.class);//prueba
                    break;
                default:
                    intent = new Intent(v.getContext(), registroGeneral.class);//prueba
                    break;
            }
        }
        else
            intent = new Intent(v.getContext(), registroGeneral.class);
        startActivity(intent);
    }
}
