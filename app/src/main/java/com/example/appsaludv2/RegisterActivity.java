package com.example.appsaludv2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import DataAccess.DAOPaciente;
import Models.Paciente;

public class RegisterActivity extends AppCompatActivity {

    private EditText etNombres;
    private EditText etApellidos;
    private RadioGroup rgGenero;
    private Spinner spCiudad;
    private EditText etEdad;
    private EditText etDni;
    private EditText etPeso;
    private EditText etAltura;
    private ImageView ivFoto;
    private byte[] imgSeleccionada;
    private final String[] ciudades = {
            "Seleccionar una Ciudad",
            "Cajamarca",
            "Jaen",
            "Trujillo",
            "San Ignacio",
            "Chiclayo"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etNombres = findViewById(R.id.etNombres);
        etApellidos = findViewById(R.id.etApellidos);
        rgGenero = findViewById(R.id.rgGenero);
        spCiudad = findViewById(R.id.spCiudad);
        etEdad = findViewById(R.id.etEdad);
        etDni = findViewById(R.id.etDni);
        etPeso = findViewById(R.id.etPeso);
        etAltura = findViewById(R.id.etAltura);
        ivFoto = findViewById(R.id.ivFoto);

        spCiudad.setAdapter(
                new ArrayAdapter<>(
                        getApplicationContext(), android.R.layout.simple_list_item_1, ciudades
                )
        );

        Toolbar tbRegisterMenu = findViewById(R.id.tbRegisterMenu);
        setSupportActionBar(tbRegisterMenu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button btnRegistrar = findViewById(R.id.btnRegistrar);
        Button btnListar = findViewById(R.id.btnListar);

        btnRegistrar.setOnClickListener(v -> registrarPaciente());
        btnListar.setOnClickListener(v -> listarPacientes());
        ivFoto.setOnClickListener(v -> seleccionarFoto());
    }

    private void listarPacientes() {
        Intent intent = new Intent(RegisterActivity.this, ListingActivity.class);
        startActivity(intent);
    }

    private void registrarPaciente() {
        if (validarDatos()) {
            dialogo();
        }
    }

    private void dialogo() {
        AlertDialog.Builder dlog = new AlertDialog.Builder(this);
        dlog.setTitle("Aviso");
        dlog.setMessage("¿Desea seguir registrando?");
        dlog.setIcon(R.drawable.alert);

        dlog.setPositiveButton("Aceptar", (dialog, which) -> {
            pacienteRegistrado();
            limpiarDatos();
            dialog.dismiss();
        });

        dlog.setNegativeButton("Denegar", (dialog, which) -> {
            pacienteRegistrado();
            limpiarDatos();
            dialog.dismiss();

            Intent intent = new Intent(RegisterActivity.this, EntryActivity.class);
            startActivity(intent);
        });
        dlog.create();
        dlog.show();
    }

    private void limpiarDatos() {
        etNombres.setText("");
        etApellidos.setText("");
        rgGenero.check(R.id.rbFemenino);
        spCiudad.setSelection(0);
        etEdad.setText("");
        etDni.setText("");
        etPeso.setText("");
        etAltura.setText("");
        ivFoto.setImageResource(R.drawable.click_icon);
        imgSeleccionada = null;
    }

    private void pacienteRegistrado() {
        String nombres = String.valueOf(etNombres.getText());
        String apellidos = String.valueOf(etApellidos.getText());
        String genero = obtenerGenero();
        int edad = Integer.parseInt(etEdad.getText().toString().trim());
        String ciudad = String.valueOf(spCiudad.getSelectedItem());
        String dni = String.valueOf(etDni.getText());
        double peso = Double.parseDouble(etPeso.getText().toString().trim());
        double altura = Double.parseDouble(etAltura.getText().toString().trim());
        byte[] foto = imgSeleccionada;

        Paciente paciente = new Paciente(nombres, apellidos, genero, edad, ciudad, dni, peso, altura, foto);
        //EntryActivity.listaPacientes.add(paciente);

        DAOPaciente daoPaciente = new DAOPaciente();
        daoPaciente.Agregar(this, paciente);
        Toast.makeText(getApplicationContext(), paciente.toString(), Toast.LENGTH_LONG).show();
    }

    private boolean validarDatos() {
        if (etNombres.getText().toString().isEmpty()) {
            etNombres.setError("Nombre(s) del paciente Obligatorio");
            etNombres.requestFocus();
            return false;
        } else if (etApellidos.getText().toString().isEmpty()) {
            etApellidos.setError("Apellidos del paciente Obligatorio");
            etApellidos.requestFocus();
            return false;
        } else if (!generoValido()) {
            Toast.makeText(getApplicationContext(), "Debe seleccionar un Género", Toast.LENGTH_LONG).show();
            rgGenero.requestFocus();
            return false;
        } else if (!ciudadValida()) {
            Toast.makeText(getApplicationContext(), "Debe seleccionar una Ciudad", Toast.LENGTH_LONG).show();
            spCiudad.requestFocus();
            return false;
        } else if (etEdad.getText().toString().isEmpty()) {
            etEdad.setError("Edad del paciente Obligatorio");
            etEdad.requestFocus();
            return false;
        } else if (etDni.getText().toString().isEmpty()) {
            etDni.setError("N° del DNI Obligatorio");
            etDni.requestFocus();
            return false;
        } else if (!Paciente.verificarDNI(etDni.getText().toString())) {
            etDni.setError("Ingrese los 8 dígitos Correctamente");
            etDni.requestFocus();
            return false;
        } else if (etPeso.getText().toString().isEmpty()) {
            etPeso.setError("Peso del paciente Obligatorio");
            etPeso.requestFocus();
            return false;
        } else if (etAltura.getText().toString().isEmpty()) {
            etAltura.setError("Altura del paciente Obligatorio");
            etAltura.requestFocus();
            return false;
        } else if (imgSeleccionada == null) {
            Toast.makeText(getApplicationContext(), "Debe seleccionar una Foto", Toast.LENGTH_LONG).show();
            ivFoto.requestFocus();
            return false;
        }
        return true;
    }

    private boolean ciudadValida() {
        int idCiudad = spCiudad.getSelectedItemPosition();
        return idCiudad != 0;
    }

    private boolean generoValido() {
        int idGenero = rgGenero.getCheckedRadioButtonId();
        return idGenero == R.id.rbFemenino || idGenero == R.id.rbMasculino;
    }

    private String obtenerGenero() {
        int idGenero = rgGenero.getCheckedRadioButtonId();
        return idGenero == R.id.rbFemenino ? "Femenino" : "Masculino";
    }

    private void seleccionarFoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityIfNeeded(Intent.createChooser(intent, "Seleccionar Imagen"), 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            if (data != null && data.getData() != null) {
                Uri fotoSubido = data.getData();
                ivFoto.setImageURI(fotoSubido);
                ivFoto.buildDrawingCache();

                Bitmap bitmap = ivFoto.getDrawingCache();
                ByteArrayOutputStream bmFlujo = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 0, bmFlujo);
                imgSeleccionada = bmFlujo.toByteArray();
            } else {
                Toast.makeText(this, "No se seleccionó ninguna imagen", Toast.LENGTH_SHORT).show();
            }
        }
    }
}