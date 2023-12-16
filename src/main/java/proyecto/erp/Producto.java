package proyecto.erp;

import javafx.beans.property.*;

public class Producto {
    private IntegerProperty idProducto;
    private StringProperty nombre;
    private StringProperty descripcion;
    private FloatProperty precio;
    private IntegerProperty cantidadStock;
    private IntegerProperty idProveedor;

    public Producto(int idProducto, String nombre, String descripcion, float precio, int cantidadStock, int idProveedor) {
        this.idProducto = new SimpleIntegerProperty(idProducto);
        this.nombre = new SimpleStringProperty(nombre);
        this.descripcion = new SimpleStringProperty(descripcion);
        this.precio = new SimpleFloatProperty(precio);
        this.cantidadStock = new SimpleIntegerProperty(cantidadStock);
        this.idProveedor = new SimpleIntegerProperty(idProveedor);
    }

    public int getIdProducto() {
        return idProducto.get();
    }

    public IntegerProperty idProductoProperty() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto.set(idProducto);
    }

    public String getNombre() {
        return nombre.get();
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public String getDescripcion() {
        return descripcion.get();
    }

    public StringProperty descripcionProperty() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion.set(descripcion);
    }

    public float getPrecio() {
        return precio.get();
    }

    public FloatProperty precioProperty() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio.set(precio);
    }

    public int getCantidadStock() {
        return cantidadStock.get();
    }

    public IntegerProperty cantidadStockProperty() {
        return cantidadStock;
    }

    public void setCantidadStock(int cantidadStock) {
        this.cantidadStock.set(cantidadStock);
    }

    public int getIdProveedor() {
        return idProveedor.get();
    }

    public IntegerProperty idProveedorProperty() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor.set(idProveedor);
    }

    @Override
    public String toString() {
        return "Producto{" +
                "idProducto=" + idProducto +
                ", nombre=" + nombre +
                ", descripcion=" + descripcion +
                ", precio=" + precio +
                ", cantidadStock=" + cantidadStock +
                ", idProveedor=" + idProveedor +
                '}';
    }
}
