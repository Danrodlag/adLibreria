package proyecto.erp;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Pedido {
    private IntegerProperty id_pedido;
    private IntegerProperty id_proveedor;
    private IntegerProperty id_producto;
    private IntegerProperty cantidad;

    public Pedido(int id_pedido, int id_proveedor, int id_producto, int cantidad) {
        this.id_pedido = new SimpleIntegerProperty(id_pedido);
        this.id_proveedor = new SimpleIntegerProperty(id_proveedor);
        this.id_producto = new SimpleIntegerProperty(id_producto);
        this.cantidad = new SimpleIntegerProperty(cantidad);
    }

    public int getId_pedido() {
        return id_pedido.get();
    }

    public IntegerProperty id_pedidoProperty() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido.set(id_pedido);
    }

    public int getId_proveedor() {
        return id_proveedor.get();
    }

    public IntegerProperty id_proveedorProperty() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor.set(id_proveedor);
    }

    public int getId_producto() {
        return id_producto.get();
    }

    public IntegerProperty id_productoProperty() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto.set(id_producto);
    }

    public int getCantidad() {
        return cantidad.get();
    }

    public IntegerProperty cantidadProperty() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad.set(cantidad);
    }
}
