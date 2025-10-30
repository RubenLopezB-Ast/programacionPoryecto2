package biblioteca.simple.modelo;

public abstract class Producto {
    protected int id;
    public String titulo;
    public String anio;
    protected Formato formato;

    //Este constructor recibe objetos de la base de datos
    protected Producto(int id, String titulo, String anio, Formato formato) {
        this.id = id;
        this.titulo = titulo;
        this.anio = anio;
        this.formato = formato;
    }

    //Este cosntructor crea productos nuevos desde la aplicación
    protected Producto(String titulo, String anio, Formato formato) {
        this.titulo = titulo;
        this.anio = anio;
        this.formato = formato;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAnio() {
        return anio;
    }

    public Formato getFormato() {
        return formato;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", anio='" + anio + '\'' +
                ", formato=" + formato +
                '}';
    }
}
