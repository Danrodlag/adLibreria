package proyecto.adbd1;

import javafx.beans.property.*;

import java.util.Date;

public class Libro {
    private IntegerProperty idLibro;
    private StringProperty titulo;
    private StringProperty autor;
    private ObjectProperty<Date> fecha;
    private IntegerProperty cantidadDisponible;

    public Libro(int idLibro, String titulo, String autor, Date fecha, int cantidadDisponible) {
        this.idLibro = new SimpleIntegerProperty(idLibro);
        this.titulo = new SimpleStringProperty(titulo);
        this.autor = new SimpleStringProperty(autor);
        this.fecha = new SimpleObjectProperty<>(fecha);
        this.cantidadDisponible = new SimpleIntegerProperty(cantidadDisponible);
    }


    public String getTitulo() {
        return titulo.get();
    }

    public StringProperty tituloProperty() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo.set(titulo);
    }

    public String getAutor() {
        return autor.get();
    }

    public StringProperty autorProperty() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor.set(autor);
    }

    public int getIdLibro() {
        return idLibro.get();
    }

    public IntegerProperty idLibroProperty() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro.set(idLibro);
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

    public int getCantidadDisponible() {
        return cantidadDisponible.get();
    }

    public IntegerProperty cantidadDisponibleProperty() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible.set(cantidadDisponible);
    }

    @Override
    public String toString() {
        return "Libro{" +
                "idLibro=" + idLibro +
                ", titulo=" + titulo +
                ", autor=" + autor +
                ", fecha=" + fecha +
                ", cantidadDisponible=" + cantidadDisponible +
                '}';
    }
}
