package DataAccess;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.CallLog;
import android.widget.Toast;
import android.util.Pair;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

public class DAOCalling {
    private final Uri tabla;
    private final String[] columna;
    private final List<Pair<Integer, String>> listaLlamadas;
    private List<Pair<Integer, String>> listaTotal;
    public DAOCalling() {
        tabla = CallLog.Calls.CONTENT_URI;
        columna = new String[] { CallLog.Calls.TYPE, CallLog.Calls.NUMBER };
        listaLlamadas = new ArrayList<>();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public List<Pair<Integer, String>> LlamadasListadas(Activity context) {
        try {
            ContentResolver contentResolver = context.getContentResolver();
            Cursor cursor = contentResolver.query(tabla, columna, null, null);

            if (cursor.moveToFirst()) {
                do {
                    int indexType = cursor.getInt(0);
                    String indexNumber = cursor.getString(1);
                    listaLlamadas.add(new Pair<>(indexType, indexNumber));

                } while (cursor.moveToNext());
                cursor.close();

                listaTotal = new ArrayList<>();
                listaTotal.addAll(listaLlamadas);
            }
        }
        catch (Exception exception) {
            Toast.makeText(context, "Error: " + exception.getMessage(), Toast.LENGTH_LONG).show();
        }
        return listaLlamadas;
    }

    public int getSize() {
        return listaLlamadas.size();
    }

    public Pair<Integer, String> getCall(int index) {
        return listaLlamadas.get(index);
    }

    public List<Pair<Integer, String>> LlamadasFiltradas(int indexTipo) {
        listaLlamadas.clear();
        for (Pair<Integer, String> llamada : listaTotal) {
            if (llamada.first == indexTipo) {
                listaLlamadas.add(new Pair<>(llamada.first, llamada.second));
            }
        }
        return listaLlamadas;
    }
}
