package biblioteca.simple.modelo;

import biblioteca.simple.contratos.Prestable;

public class Libro extends Producto implements Prestable {

    private String ISBN;
    private String autor;

    private boolean prestado;
    private Usuario prestadoA;

    public Libro(int id, String titulo, String anio, Formato formato, String ISBN, String autor) {
        // Llama al constructor de Producto que recibe id
        super(id, titulo, anio, formato);
        this.ISBN = ISBN;
        this.autor = autor;
    }

    public Libro(String titulo, String anio, Formato formato, String ISBN, String autor) {
        // Llama al constructor de Producto que NO tiene id
        super(titulo, anio, formato);
        this.ISBN = ISBN;
        this.autor = autor;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getAutor() {
        return autor;
    }

    @Override
    public void prestar(Usuario u) {
        // No se puede prestar si ya está prestado
        if (prestado) throw new IllegalStateException("Ya prestado");

        this.prestado = true;
        this.prestadoA = u;
    }

    @Override
    public void devolver() {
        this.prestado = false;
        this.prestadoA = null;
    }

    @Override
    public boolean estaPrestado() {
        return this.prestado;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "ISBN='" + ISBN + '\'' +
                ", autor='" + autor + '\'' +
                ", id=" + id +
                ", titulo='" + titulo + '\'' +
                ", anho='" + anio + '\'' +
                ", formato=" + formato +
                '}';
    }
}