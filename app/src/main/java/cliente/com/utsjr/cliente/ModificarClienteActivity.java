package cliente.com.utsjr.cliente;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;


public class ModificarClienteActivity extends ActionBarActivity {
    private EditText modificarTxtCliente,modificarTxtDireccion, modificarTxtTelefono,modificarTxtFechaEntrega;
    private Button modificarBtnRegistrar,modificarBtnRegresar;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_cliente);
        modificarTxtCliente=(EditText)findViewById(R.id.ModificarTxtCliente);
        modificarTxtDireccion=(EditText)findViewById(R.id.ModificarTxtDireccion);
        modificarTxtTelefono=(EditText)findViewById(R.id.ModificarTxtTelefono);
        modificarTxtFechaEntrega=(EditText)findViewById(R.id.ModificarTxtFechaEntrega);
        modificarBtnRegistrar=(Button)findViewById(R.id.ModificarBtnRegistrar);
        modificarBtnRegresar=(Button)findViewById(R.id.ModificarBtnRegresar);

       ClienteDAO cdao=new ClienteDAO(this);

        //obtenemos los datos del intent
        Bundle b=getIntent().getExtras();
        id=b.getInt("id");
        Cliente c=new Cliente();

        //abimos la conexion y buacamos el cliente que tenga ese ID y asi lo asignamos a un objeto
        try{
            cdao.open();
            c=cdao.buscarCliente(id);

        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            cdao.close();
        }

        //mostramos los datos en los cuador de txto para que el usuario los modifique

        modificarTxtCliente.setText(c.getCliente());
        modificarTxtDireccion.setText(c.getDireccion());
        modificarTxtTelefono.setText(c.getTelefono());
        modificarTxtFechaEntrega.setText(c.getFechaentrega());


        modificarBtnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cliente c=new Cliente();
                c.setId(ModificarClienteActivity.this.id);
                c.setCliente(modificarTxtCliente.getText().toString());
                c.setDireccion(modificarTxtDireccion.getText().toString());
                c.setTelefono(modificarTxtTelefono.getText().toString());
                c.setFechaentrega(modificarTxtFechaEntrega.getText().toString());


                ClienteDAO cdao=new ClienteDAO(ModificarClienteActivity.this);
                try{
                    cdao.open();
                    if(cdao.modificarCliente(c)){
                        Toast.makeText(ModificarClienteActivity.this, "El cliente ha sido modificado", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(ModificarClienteActivity.this, "El cliente no ha sido modificado", Toast.LENGTH_LONG).show();
                    }
                }
                catch (SQLException aqle){

                }
                finally {
                    cdao.close();

                }
                ModificarClienteActivity.this.finish();

            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_modificar_cliente, menu);
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
