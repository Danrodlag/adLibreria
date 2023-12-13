package proyecto.erp;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class Proveedor {
    private IntegerProperty idProveedor;
    private StringProperty nombre;
    private StringProperty direccion;
    private StringProperty contacto;

    public Proveedor(IntegerProperty idProveedor, StringProperty nombre, StringProperty direccion, StringProperty contacto) {
        this.idProveedor = idProveedor;
        this.nombre = nombre;
        this.direccion = direccion;
        this.contacto = contacto;
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

    public String getNombre() {
        return nombre.get();
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public String getDireccion() {
        return direccion.get();
    }

    public StringProperty direccionProperty() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion.set(direccion);
    }

    public String getContacto() {
        return contacto.get();
    }

    public StringProperty contactoProperty() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto.set(contacto);
    }
}
