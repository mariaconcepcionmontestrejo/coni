package cliente.com.utsjr.cliente;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;


public class DetallesCliente extends ActionBarActivity {
    private TextView detallesLblCliente, detallesLblDireccion, detallesLblTelefono ,detallesLblPedido, detallesLblCorreo,
    detallesLblFecha, detallesLblPago;
    private ImageButton detallesBtnEliminar, detallesBtnModificar;


    private int id;
    private String cliente, direccion, telefono,pedido,correo,fecha,pago;

    private ClienteDAO cdao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_cliente);
        detallesLblCliente=(TextView)findViewById(R.id.DetallesLblCliente);
        detallesLblDireccion=(TextView)findViewById(R.id.DetallesLblDireccion);
        detallesLblTelefono=(TextView)findViewById(R.id.DetallesLblTelefono);
        detallesLblPedido=(TextView)findViewById(R.id.DetallesLblPedido);
        detallesLblCorreo=(TextView)findViewById(R.id.DetallesLblCorreo);
        detallesLblFecha=(TextView)findViewById(R.id.DetallesLblfechaEntrega);
        detallesLblPago=(TextView)findViewById(R.id.DetallesLblTotalPago);


        detallesBtnEliminar=(ImageButton)findViewById(R.id.DetallesBtnEliminar);
        detallesBtnModificar=(ImageButton)findViewById(R.id.DetallesBtnModificar);

        //obtenemos los valores que viene en el Intent
        Bundle b=getIntent().getExtras();
        id=b.getInt("id");
        cliente=b.getString("cliente");
        direccion=b.getString("direccion");
        telefono=b.getString("telefono");
        pedido=b.getString("pedido");
        correo=b.getString("correo");
        fecha=b.getString("fecha");
        pago=b.getString("pago");

        //mostramos los valoes en los TextView correspondientes
        detallesLblCliente.setText("CLIENTE: "+cliente);
        detallesLblDireccion.setText("DIRECCION: "+direccion);
        detallesLblTelefono.setText("TELFONO: "+telefono);
        detallesLblPedido.setText("PEDIDO: " + pedido);
        detallesLblCorreo.setText("CORREO: " + correo);
        detallesLblFecha.setText("FECHA: " + fecha);
        detallesLblPago.setText("PAGO: " + pago);





        //accion boton modificar
        detallesBtnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent modificarContacto=new Intent(DetallesCliente.this,ModificarClienteActivity.class);
                modificarContacto.putExtra("id",id);
                startActivity(modificarContacto);
                DetallesCliente.this.finish();
            }
        });

        //accion boton eliminar
        detallesBtnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cdao=new ClienteDAO(DetallesCliente.this);
                try{
                    cdao.open();
                    if(cdao.eliminarCliente(id)){
                        Toast.makeText(DetallesCliente.this,"Cliente Eliminado Correctamente", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(DetallesCliente.this,"Cliente No Eliminado", Toast.LENGTH_LONG).show();
                    }
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
                cdao.close();
                DetallesCliente.this.finish();
            }
        });


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detalles_cliente, menu);
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
