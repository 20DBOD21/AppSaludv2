package DataAccess;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

import Models.Paciente;

public class DAOPaciente {
    private String nombreDB;
    private int versionDB;
    private List<Paciente> listaDAOPacientes;

    public DAOPaciente() {
        nombreDB = "SaludDB";
        versionDB = 1;
        listaDAOPacientes = new ArrayList<>();
    }

    public boolean Agregar(Activity context, Paciente paciente) {
        boolean rpta = false;

        DBOpenHelper dboh = new DBOpenHelper(context, nombreDB, null, versionDB);
        SQLiteDatabase oBD = dboh.getWritableDatabase();

        if (oBD != null) {
            ContentValues columnas = new ContentValues();

            columnas.put("nomb_paciente", paciente.getNombres());
            columnas.put("apel_paciente", paciente.getApellidos());
            columnas.put("gene_paciente", paciente.getGenero());
            columnas.put("edad_paciente", paciente.getEdad());
            columnas.put("ciud_paciente", paciente.getCiudad());
            columnas.put("dni_paciente", paciente.getDni());
            columnas.put("peso_paciente", paciente.getPeso());
            columnas.put("altu_paciente", paciente.getAltura());
            columnas.put("foto_paciente", paciente.getFoto());

            int fila = (int) oBD.insert("Paciente", null, columnas);
            if (fila > 0) {
                rpta = true;
            }
        }
        oBD.close();
        dboh.close();
        return rpta;
    }

    public boolean Cargar(Activity context) {
        boolean rpta = false;

        DBOpenHelper dboh = new DBOpenHelper(context, nombreDB, null, versionDB);
        SQLiteDatabase oBD = dboh.getReadableDatabase();

        String consulta = "SELECT * FROM Paciente";
        Cursor cursor = oBD.rawQuery(consulta, null);

        if (cursor.moveToFirst()) {
            rpta = true;
            do {
                String nombres = cursor.getString(1);
                String apellidos = cursor.getString(2);
                String genero = cursor.getString(3);
                int edad = cursor.getInt(4);
                String ciudad = cursor.getString(5);
                String dni = cursor.getString(6);
                double peso = cursor.getDouble(7);
                double altura = cursor.getDouble(8);
                byte[] foto = cursor.getBlob(9);

                Paciente oP = new Paciente(nombres, apellidos, genero, edad, ciudad, dni, peso, altura, foto);
                listaDAOPacientes.add(oP);
            } while (cursor.moveToNext());
        }
        return rpta;
    }

    public List<Paciente> getListaDAOPacientes() {
        return listaDAOPacientes;
    }

    public int getSize() {
        return listaDAOPacientes.size();
    }

    public Paciente getPaciente(int index) {
        return listaDAOPacientes.get(index);
    }
}
