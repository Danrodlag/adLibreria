package proyecto.erp;

import javafx.beans.property.*;

import java.sql.Date;

public class Venta {
    private IntegerProperty id_venta;
    private ObjectProperty<Date> fecha;
    private IntegerProperty id_cliente;
    private FloatProperty total;
    private IntegerProperty id_producto;
    private IntegerProperty cantidad;

    public Venta(int id_venta, Date fecha, int id_cliente, float total, int id_producto, int cantidad) {
        this.id_venta = new SimpleIntegerProperty(id_venta);
        this.fecha = new SimpleObjectProperty<>(fecha);
        this.id_cliente = new SimpleIntegerProperty(id_cliente);
        this.total = new SimpleFloatProperty(total);
        this.id_producto = new SimpleIntegerProperty(id_producto);
        this.cantidad = new SimpleIntegerProperty(cantidad);
    }

    public int getId_venta() {
        return id_venta.get();
    }

    public IntegerProperty id_ventaProperty() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta.set(id_venta);
    }

    public Date getFecha() {
        return fecha.get();
    }

    public ObjectProperty<Date> fechaProperty() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha.set(fecha);
    }

    public int getId_cliente() {
        return id_cliente.get();
    }

    public IntegerProperty id_clienteProperty() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente.set(id_cliente);
    }

    public float getTotal() {
        return total.get();
    }

    public FloatProperty totalProperty() {
        return total;
    }

    public void setTotal(float total) {
        this.total.set(total);
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
