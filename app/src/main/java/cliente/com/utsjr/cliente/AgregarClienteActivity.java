package cliente.com.utsjr.cliente;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.SQLException;


public class AgregarClienteActivity extends ActionBarActivity {
    private EditText txtCliente, txtDireccion, txtTelefono, txtPedido, txtFechaEntrega, txtCorreo,txtTotalpago;
    private Button btnRegistrar, btnRegresar;
    private ClienteDAO cdao;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_cliente);

        txtCliente=(EditText)findViewById(R.id.TxtNombre);
        txtDireccion= (EditText)findViewById(R.id.TxtDireccion);
        txtTelefono= (EditText)findViewById(R.id.TxtTelefono);
        txtPedido= (EditText)findViewById(R.id.TxtPedido);
        txtFechaEntrega=(EditText)findViewById(R.id.TxtPedido);
        txtCorreo=(EditText)findViewById(R.id.TxtCorreo);
        txtTotalpago=(EditText)findViewById(R.id.TxtPago);
        btnRegistrar=(Button)findViewById(R.id.BtnRegistrar);
        btnRegresar=(Button)findViewById(R.id.BtnRegresar);

        //accion de los botones

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //declaramos un objeto
                Cliente c=new Cliente();

                //asignamos los valosres de los cuadrso de texto a los atributos del objeto
                c.setCliente(txtCliente.getText().toString());
                c.setDireccion(txtDireccion.getText().toString());
                c.setTelefono(txtTelefono.getText().toString());
                c.setPedido(txtPedido.getText().toString());
                c.setFechaentrega(txtFechaEntrega.getText().toString());
                c.setCorreo(txtCorreo.getText().toString());
                c.setTotalpago(txtTotalpago.getText().toString());


                //inicializamos el dao y abrimos la conexion

                cdao=new ClienteDAO(AgregarClienteActivity.this);
                try{
                    cdao.open();
                }catch (SQLException e){
                    e.printStackTrace();
                }


                //llamamos al metodo agregar cancion del DAO y generamos mensaje en consecuencia
                if(cdao.agregarCliente(c)){
                    mostarMensaje("Alta Cliente","Cliente registrado correctamente");
                }
                else{
                    mostarMensaje("Alta Cliente","Cliente no registrado");
                }


            }
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AgregarClienteActivity.this.finish();
            }
        });

    }



    //metodo para generar el cuadro de dialogo con el mensaje
    private void mostarMensaje(String titulo,String mensaje){
        //declaramos e instanciamos el objeto AlertDialog
        AlertDialog .Builder builder=new AlertDialog.Builder(this);

        TextView lblMensaje=new TextView(this);
        builder.setTitle(titulo);
        lblMensaje.setText(mensaje);
        lblMensaje.setTextSize(20);
        lblMensaje.setGravity(Gravity.CENTER_HORIZONTAL);

        builder.setView(lblMensaje);

        builder.setCancelable(false);


        //agregamos boton al dialogo

        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();

                //bprramos contenido de los cuadros
                ((EditText)(findViewById(R.id.TxtNombre))).setText("");
                ((EditText)(findViewById(R.id.TxtDireccion))).setText("");
                ((EditText)(findViewById(R.id.TxtTelefono))).setText("");
                ((EditText)(findViewById(R.id.TxtFecha))).setText("");
                ((EditText)(findViewById(R.id.TxtPedido))).setText("");
                ((EditText)(findViewById(R.id.TxtCorreo))).setText("");
                ((EditText)(findViewById(R.id.TxtPago))).setText("");

                //enviamos el cursor al primer cuadro
                ((EditText)(findViewById(R.id.TxtNombre))).requestFocus();
            }
        });

        builder.show();


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_agregar_cliente, menu);
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
