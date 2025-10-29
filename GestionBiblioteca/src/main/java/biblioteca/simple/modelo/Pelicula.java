package biblioteca.simple.modelo;

public class Pelicula extends Producto{
        private String director;
        private int minutosDuracion;

    public Pelicula(int id, String titulo, String anio, Formato formato, String director, int minutosDuracion) {
        super(id, titulo, anio, formato);
        this.director = director;
        this.minutosDuracion = minutosDuracion;
    }

    public Pelicula(String titulo, String anio, Formato formato, String director, int minutosDuracion) {
        super(titulo, anio, formato);
        this.director = director;
        this.minutosDuracion = minutosDuracion;
    }


    public String getDirector() {
        return director;
    }

    public int getMinutosDuracion() {
        return minutosDuracion;
    }
    @Override
    public String toString() {
        return "Pelicula{" +
                "director='" + director + '\'' +
                ", minutosDuracion=" + minutosDuracion +
                ", formato=" + formato +
                ", anho='" + anio + '\'' +
                ", titulo='" + titulo + '\'' +
                ", id=" + id +
                '}';
    }

}
