package cliente.com.utsjr.cliente;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alumno on 10/03/2015.
 */
public class ClienteDAO {
    //atributos
    private ClienteHelper ch;
    private SQLiteDatabase sd;

    //constructor
    public ClienteDAO(Context context){
        ch=new ClienteHelper(context);
    }

    //metodo para abrir una conexion con la BD;
    public void open() throws SQLException{
        sd=ch.getWritableDatabase();
    }

    //metodo para cerrar la conexion con la BD
    public void close() {
        ch.close();
    }

    //metodo para el atributo de la base da datos
    public SQLiteDatabase getSd(){
        return sd;
    }

    //metodo para agregar
    public boolean agregarCliente(Cliente c){
        long resultado=-1;

        //declaramos objeto ContentValues
        ContentValues cv=new ContentValues();

        //agregamos elementos al ContentValues (clave,valor)
        cv.put(ClienteTable.CLIENTE,c.getCliente());
        cv.put(ClienteTable.DIRECCION,c.getDireccion());
        cv.put(ClienteTable.TELEFONO,c.getTelefono());
        cv.put(ClienteTable.PEDIDO,c.getPedido());
        cv.put(ClienteTable.FECHA,c.getFechaentrega());
        cv.put(ClienteTable.PAGO,c.getTotalpago());
        cv.put(ClienteTable.CORREO,c.getCorreo());

        //insertamos los valores en la tabla de la BD
        resultado=getSd().insert(ClienteTable.TABLA, ClienteTable.TABLA,cv);

        //verificar numero de filas afectadas
        if(resultado>0){
            return true;
        }
        else{
            return false;
        }


    }

    //metodo para eliminar
    public boolean eliminarCliente(int id){
        long resultado=getSd().delete(ClienteTable.TABLA, ClienteTable.ID + "=" + id,null);

        //verificar numero de filas afectadas
        if(resultado>0){
            return true;
        }
        else{
            return false;
        }

    }

    //metodo para modificar
    public boolean modificarCliente(Cliente c){
        long resultado=-1;

        //declaramos objeto ContentValues
        ContentValues cv=new ContentValues();

        //agregamos elementos al ContentValues (clave,valor)
        cv.put(ClienteTable.CLIENTE,c.getCliente());
        cv.put(ClienteTable.DIRECCION,c.getDireccion());
        cv.put(ClienteTable.TELEFONO,c.getTelefono());
        cv.put(ClienteTable.PEDIDO,c.getPedido());
        cv.put(ClienteTable.FECHA,c.getFechaentrega());
        cv.put(ClienteTable.PAGO,c.getTotalpago());
        cv.put(ClienteTable.CORREO,c.getCorreo());

        //se actualiza l registro
        resultado=getSd().update(ClienteTable.TABLA,cv, ClienteTable.ID + "=?",new String[]{String.valueOf(c.getId())});


        //verificar numero de filas afectadas
        if(resultado>0){
            return true;
        }
        else{
            return false;
        }

    }

    //metodo para obtener una lista

    public List<Cliente> getCliente(){
       Cliente c=null;
        List<Cliente> clienteList=new ArrayList<Cliente>();
        //declaramos un objeto Cursor y obtenemos los datos
        Cursor cursor=getSd().query(ClienteTable.TABLA, ClienteTable.getClienteColumns(),null,null,null,null,null);

        //nos moveremos al primer elemento del cursor
        cursor.moveToFirst();

        //recorremos el cursor y vamos extrayendo cada registro
        //y agregandolo a la lista
        while (!cursor.isAfterLast()){
            c=new Cliente();
            c.setId(cursor.getInt(0));
            c.setCliente(cursor.getString(1));
            c.setDireccion(cursor.getString(2));
            c.setTelefono(cursor.getString(3));
            c.setPedido(cursor.getString(4));
            c.setFechaentrega(cursor.getString(7));
            c.setTotalpago(cursor.getString(4));
            c.setCorreo(cursor.getString(4));
            clienteList.add(c);
            cursor.moveToNext();
        }
        return clienteList;
    }

    //metodo para buscar un solo cliente por si ID
    public Cliente buscarCliente(int id){
        //convertimos el id que es int a un arreglo de string
        String[] valor={String.valueOf(id)};

        //declaramos el cursor y hacemos la consulta
        Cursor cursor=getSd().query(ClienteTable.TABLA, ClienteTable.getClienteColumns(),"id=?",valor,null,null,null);


        cursor.moveToFirst();
        Cliente c=new Cliente();
        c.setId(cursor.getInt(0));
        c.setCliente(cursor.getString(1));
        c.setDireccion(cursor.getString(2));
        c.setTelefono(cursor.getString(3));
        c.setPedido(cursor.getString(4));
        c.setFechaentrega(cursor.getString(7));
        c.setTotalpago(cursor.getString(4));
        c.setCorreo(cursor.getString(4));


        return c;
    }


    //metodo que nos obtiene una lista de los nombres de los clientes

    public List<String> getNombreCliente(){
        List<String> clientesList=new ArrayList<String>();
        //declaramos el cursor y obtenemos los registros
        Cursor cursor=getSd().query(ClienteTable.TABLA, ClienteTable.getClienteColumns(),null,null,null,null,null);
        cursor.moveToFirst();

        //recorremo el cursor y extraemos el compo NOMBRE
        while(!cursor.isAfterLast()){
            clientesList.add(cursor.getString(1));
            cursor.moveToNext();
        }
        return clientesList;
    }

    //metodo para obtener lista de los ID de los clientes
    public List<String> getIdCliente(){
        List<String> idList=new ArrayList<String>();


        //declaramos el cursor y obtenemos los registros
        Cursor cursor=getSd().query(ClienteTable.TABLA, ClienteTable.getClienteColumns(),null,null,null,null,null);
        cursor.moveToFirst();



        //recorremo el cursor y extraemos el compo NOMBRE
        while(!cursor.isAfterLast()){
            idList.add(""+cursor.getString(0));
            cursor.moveToNext();
        }
        return idList;




    }



}
