package proyecto.erp;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class Producto {
    private IntegerProperty idProducto;
    private StringProperty nombre;
    private StringProperty descripcion;
    private IntegerProperty precio;
    private IntegerProperty cantidadStock;
    private IntegerProperty idProveedor;

    public Producto(IntegerProperty idProducto, StringProperty nombre, StringProperty descripcion, IntegerProperty precio, IntegerProperty cantidadStock, IntegerProperty idProveedor) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidadStock = cantidadStock;
        this.idProveedor = idProveedor;
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

    public int getPrecio() {
        return precio.get();
    }

    public IntegerProperty precioProperty() {
        return precio;
    }

    public void setPrecio(int precio) {
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
