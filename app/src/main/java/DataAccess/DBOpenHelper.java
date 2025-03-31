package DataAccess;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBOpenHelper extends SQLiteOpenHelper {
    private final String TABLE_PACIENTE =
            "CREATE TABLE Paciente (" +
                    "id_paciente INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," + //0
                    "nomb_paciente VARCHAR(100) NOT NULL," + //1
                    "apel_paciente VARCHAR(200) NOT NULL," + //2
                    "gene_paciente VARCHAR(15) NOT NULL," + //3
                    "edad_paciente INTEGER NOT NULL," + //4
                    "ciud_paciente VARCHAR(50)," + //5
                    "dni_paciente VARCHAR(8)," + //6
                    "peso_paciente DOUBLE NOT NULL," + //7
                    "altu_paciente DOUBLE NOT NULL," + //8
                    "foto_paciente BLOB NOT NULL" + //9
            ");";

    public DBOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_PACIENTE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Paciente");
        db.execSQL(TABLE_PACIENTE);
    }
}
