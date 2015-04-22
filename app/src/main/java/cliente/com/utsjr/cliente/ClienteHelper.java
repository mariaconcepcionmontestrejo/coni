package cliente.com.utsjr.cliente;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Alumno on 10/03/2015.
 */
public class ClienteHelper extends SQLiteOpenHelper {

    //atributos
    private static final String DATABASE_NAME="contactos.db";
    private static final int DATABASE_VERSION=1;

    //constructor
    public ClienteHelper(Context context){

        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE "+ ClienteTable.TABLA+"("+
                ClienteTable.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ClienteTable.CLIENTE + " TEXT, "+
                ClienteTable.DIRECCION + " TEXT, "+
                ClienteTable.TELEFONO+ " TEXT, "+
                ClienteTable.PAGO+ " TEXT, "+
                ClienteTable.FECHA+ " TEXT, "+
                ClienteTable.CORREO+ " TEXT, "+
                ClienteTable.PEDIDO+ " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.w(ClienteHelper.class.getName(),
                "Actualizacion base de datos de la version " + oldVersion +
                        " a " + newVersion + ", y se perderan los datos");

        db.execSQL("DROP TABLE IF EXIST "+ ClienteTable.TABLA);
        onCreate(db);
    }
}
