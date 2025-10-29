package biblioteca.simple.modelo;

import biblioteca.simple.contratos.Prestable;

//Hereda de producto e implementa prestable de interface
public class Videojuego extends Producto implements Prestable {
    private String plataforma;
    private int edadMinima;
    private boolean prestado;

    public Videojuego(String titulo, String anio, Formato formato, String plataforma, int edadMinima) {
        super(titulo, anio, formato);
        this.plataforma = plataforma;
        this.edadMinima = edadMinima;
        this.prestado = false;
    }

    //Metemos prestable

    @Override
    public void prestar(Usuario u) {
        if (!prestado) {
            prestado = true;
            System.out.println("Videojuego '" + getTitulo() + "' prestado a " + u.getNombre());
        } else {
            System.out.println("El videojuego '" + getTitulo() + "' está prestado.");
        }
    }

    @Override
    public void devolver() {
        if (prestado) {
            prestado = false;
            System.out.println("Videojuego '" + getTitulo() + "' devuelto de forma correcta.");
        } else {
            System.out.println("El videojuego '" + getTitulo() + "' está sin prestar.");
        }
    }

    @Override
    public boolean estaPrestado() {
        return prestado;
    }
    //Get de Plataforma
    public String getPlataforma() {
        return plataforma;
    }
    //Set de Plataforma
    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }
    //Get de edad mínima
    public int getEdadMinima() {
        return edadMinima;
    }
    //Set de edad mínima
    public void setEdadMinima(int edadMinima) {
        this.edadMinima = edadMinima;
    }
    // Se añade el ToString
    @Override
    public String toString() {
        return "Videojuego{" +
                "titulo='" + getTitulo() + '\'' +
                ", anio='" + getAnio() + '\'' +
                ", formato=" + getFormato() +
                ", plataforma='" + plataforma + '\'' +
                ", edadMinima=" + edadMinima +
                ", prestado=" + prestado +
                '}';
    }
}
