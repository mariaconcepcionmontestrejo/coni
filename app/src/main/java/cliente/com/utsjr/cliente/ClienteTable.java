package cliente.com.utsjr.cliente;

/**
 * Created by Alumno on 10/03/2015.
 */
public class ClienteTable {
    //atributos
    public static final String TABLA="cliente";
    public static final String ID="id";
    public static final String CLIENTE="cliente";
    public static final String DIRECCION="direccion";
    public static final String TELEFONO="telefono";
    public static final String FECHA="fecha";
    public static final String PAGO="pago";
    public static final String CORREO="correo";
    public static final String PEDIDO="pedido";

    //metodo para obtener en un arreglo los  campos de la table
    public static String[] getClienteColumns(){
        String array[]={ID,CLIENTE,DIRECCION,TELEFONO,FECHA,PAGO,CORREO,PEDIDO};
        return array;
    }


}

