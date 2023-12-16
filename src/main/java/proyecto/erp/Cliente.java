package proyecto.erp;

import javafx.beans.property.*;

import java.util.Date;

public class Cliente {
    private IntegerProperty idCliente;
    private StringProperty nombre;
    private StringProperty direccion;
    private StringProperty contacto;
    private StringProperty dni;

    public Cliente(int idCliente, String nombre, String direccion, String contacto, String dni) {
        this.idCliente = new SimpleIntegerProperty(idCliente);
        this.nombre = new SimpleStringProperty(nombre);
        this.direccion = new SimpleStringProperty(direccion);
        this.contacto = new SimpleStringProperty(contacto);
        this.dni = new SimpleStringProperty(dni);
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

    public String getDni() {
        return dni.get();
    }

    public StringProperty dniProperty() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni.set(dni);
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

    public int getIdCliente() {
        return idCliente.get();
    }

    public IntegerProperty idClienteProperty() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente.set(idCliente);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "idCliente=" + idCliente +
                ", nombre=" + nombre +
                ", direccion=" + direccion +
                ", contacto=" + contacto +
                ", dni=" + dni +
                '}';
    }
}
