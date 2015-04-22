package cliente.com.utsjr.cliente;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.sql.SQLException;
import java.util.ArrayList;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {
    private ListView lstClientes;
    private Button btnAgregarCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lstClientes=(ListView)findViewById(R.id.LstDireccion);
        btnAgregarCliente=(Button)findViewById(R.id.BtnAgregarCliente);


        ClienteDAO cdao=new ClienteDAO(this);
        try{
            cdao.open();
            ArrayList<Cliente> searchResult=(ArrayList<Cliente>)cdao.getCliente();
            lstClientes.setAdapter(new AdaptadorCliente(this, searchResult));
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            cdao.close();
        }

        //agregamos un Listener al ListView
        lstClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cliente c=(Cliente)lstClientes.getItemAtPosition(position);

                Intent datosClientes=new Intent(MainActivity.this,DetallesCliente.class);

                Bundle b=new Bundle();
                b.putInt("id",c.getId());
                b.putString("cliente",c.getCliente());
                b.putString("direccion",c.getDireccion());
                b.putString("telefono",c.getTelefono());
                b.putString("fecha",c.getFechaentrega());
                b.putString("pedido",c.getPedido());
                b.putString("pago",c.getTotalpago());
                b.putString("correo",c.getCorreo());




                datosClientes.putExtras(b);
                startActivity(datosClientes);
            }
        });


        btnAgregarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent registoCliente=new Intent(MainActivity.this,AgregarClienteActivity.class);
                startActivity(registoCliente);

            }
        });

    }


    @Override
    protected void onResume(){
        super.onResume();
        lstClientes=(ListView)findViewById(R.id.LstDireccion);

        ClienteDAO cdao=new ClienteDAO(this);
        try{
            cdao.open();
            ArrayList<Cliente> searchResult=(ArrayList<Cliente>)cdao.getCliente();
            lstClientes.setAdapter(new AdaptadorCliente(this, searchResult));
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            cdao.close();
        }

    }

    @Override
    public void onBackPressed(){
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
