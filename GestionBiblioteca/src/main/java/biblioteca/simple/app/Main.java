package biblioteca.simple.app;

import biblioteca.simple.modelo.Formato;
import biblioteca.simple.modelo.Libro;
import biblioteca.simple.modelo.Pelicula;
import biblioteca.simple.modelo.Usuario;
import biblioteca.simple.servicios.Catalogo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Catalogo catalogo = new Catalogo();
    private static final List<Usuario> usuarios = new ArrayList<>();
    public static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        cargarDatos();
        menu();
    }
    private static void cargarDatos(){
        catalogo.alta(new Libro(1,"El Quijote", "1605", Formato.FISICO, "342342","Cervantes"));
        catalogo.alta(new Libro(1,"Cienfuegos", "1987", Formato.FISICO, "123342","Alberto Vazquez Figueroa"));
        catalogo.alta(new Pelicula(1,"El Padrino", "1972", Formato.FISICO, "Francis Ford Coopola",175));
        catalogo.alta(new Pelicula(1,"Parásitos", "2019", Formato.FISICO, "Bong Joon-ho",132));

        usuarios.add(new Usuario(1,"Juan"));
        usuarios.add(new Usuario(2,"María"));
    }
    private static void menu(){
        int op;
        do{
            System.out.println("\n====MENÚ DE BIBLIOTECA====");
            System.out.println("-1- Listar");
            System.out.println("-2- Buscar por título");
            System.out.println("-3- Buscar por año");
            System.out.println("-4- Prestar producto");
            System.out.println("-5- Devolver producto");
            System.out.println("-0- Salir");
            while(!sc.hasNextInt()) sc.next();
            op = sc.nextInt();

            sc.nextLine();
            switch (op){
                case 1 -> listar();
                case 2 -> buscarPorTitulo();
                case 3 -> buscarPorAnio();
                case 4 -> prestar();
                case 5 -> devolver();
                case 0 -> System.out.println("Adios!!!!");
                default -> System.out.println("Opción no válida");
            }
        }while (op !=0);
    }
    public static void listar(){
        List<>
    }
}
