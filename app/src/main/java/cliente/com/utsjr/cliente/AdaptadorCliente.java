package cliente.com.utsjr.cliente;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by Alumno on 10/03/2015.
 */
public class AdaptadorCliente extends BaseAdapter {

    //atributos
    private static ArrayList<Cliente> searchArrayList;
    private LayoutInflater mInflater;


    //constructor
    public AdaptadorCliente(Context context, ArrayList<Cliente> results) {
        searchArrayList=results;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return searchArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return searchArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if(convertView==null){
            convertView=mInflater.inflate(R.layout.list_item_cliente,null);
            holder=new ViewHolder();
            holder.txtCliente=(TextView) convertView.findViewById(R.id.LblCliente);
            holder.txtDireccion=(TextView) convertView.findViewById(R.id.DetallesLblDireccion);
            convertView.setTag(holder);
        }
        else{
            holder=(ViewHolder)convertView.getTag();
        }

        holder.txtCliente.setText(searchArrayList.get(position).getCliente());
        holder.txtDireccion.setText(searchArrayList.get(position).getDireccion());



        return convertView;
    }

    static class ViewHolder{
        TextView txtCliente, txtDireccion;
    }
}
