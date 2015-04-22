package cliente.com.utsjr.cliente;

/**
 * Created by Conii on 15/04/2015.
 */
public class Cliente {
    private static final long serialVersionUID=1L;

    private int id;
    private String cliente;
    private String direccion;
    private String telefono;
    private String pedido;
    private String fechaentrega;
    private String totalpago;
    private String correo;

    public Cliente() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPedido() {
        return pedido;
    }

    public void setPedido(String pedido) {
        this.pedido = pedido;
    }

    public String getFechaentrega() {
        return fechaentrega;
    }

    public void setFechaentrega(String fechaentrega) {
        this.fechaentrega = fechaentrega;
    }

    public String getTotalpago() {
        return totalpago;
    }

    public void setTotalpago(String totalpago) {
        this.totalpago = totalpago;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
