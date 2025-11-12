package biblioteca.simple.app;

import biblioteca.simple.contratos.Prestable;
import biblioteca.simple.modelo.*;
import biblioteca.simple.servicios.Catalogo;
import biblioteca.simple.servicios.PersistenciaUsuarios;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.util.Locale.filter;

public class Main {
    private static final Catalogo catalogo = new Catalogo();
    private static final List<Usuario> usuarios = new ArrayList<>();
    public static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        cargarDatos();
        menu();
    }

    private static void cargarDatos() {
        catalogo.alta(new Libro(1, "El Quijote", "1605", Formato.FISICO, "342342", "Cervantes"));
        catalogo.alta(new Libro(2, "Cienfuegos", "1987", Formato.FISICO, "123342", "Alberto Vazquez Figueroa"));
        catalogo.alta(new Pelicula(3, "El Padrino", "1972", Formato.FISICO, "Francis Ford Coopola", 175));
        catalogo.alta(new Pelicula(4, "Parásitos", "2019", Formato.FISICO, "Bong Joon-ho", 132));

        usuarios.add(new Usuario(1, "Juan"));
        usuarios.add(new Usuario(2, "María"));
    }

    private static void menu() {
        int op;
        do {
            System.out.println("\n====MENÚ DE BIBLIOTECA====");
            System.out.println("-1- Listar");
            System.out.println("-2- Buscar por título");
            System.out.println("-3- Buscar por año");
            System.out.println("-4- Prestar producto");
            System.out.println("-5- Devolver producto");
            System.out.println("-6- Exportar usuarios");
            System.out.println("-7- Importar usuarios");
            System.out.println("-0- Salir");
            while (!sc.hasNextInt()) sc.next();
            op = sc.nextInt();

            sc.nextLine();
            switch (op) {
                case 1 -> listar();
                case 2 -> buscarPorTitulo();
                case 3 -> buscarPorAnio();
                case 4 -> prestar();
                case 5 -> devolver();
                case 6 -> exportarUsuario();
                case 7 -> importarUsuarios();
                case 0 -> System.out.println("Adios!!!!");
                default -> System.out.println("Opción no válida");
            }
        } while (op != 0);
    }

    public static void listar() {
        List<Producto> lista = catalogo.listar();
        if (lista.isEmpty()) {
            System.out.println("Catalogo vacío");
            return;
        }
        System.out.println("==LISTA DE PRODUCTOS==");
        for (Producto p : lista) System.out.println("- " + p);
    }

    private static void buscarPorTitulo() {
        System.out.println("Título (escribe parte del título):");
        String t = sc.nextLine();
        catalogo.buscar(t).forEach(p -> System.out.println("- " + p));
    }

    private static void buscarPorAnio() {
        System.out.println("Año: ");
        int a = sc.nextInt();
        sc.nextLine();
        catalogo.buscar(a).forEach(p -> System.out.println("- " + p));
    }

    private static void listarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados");
            return;
        }
        System.out.println("Lista usuarios");
        usuarios.forEach(u ->
                System.out.println("Código: " + u.getId() + "| Nombre: " + u.getNombre())
        );
    }

    private static Usuario getUsuarioPorCodigo(int id) {
        return usuarios.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElse(null);
    }

    private static void prestar() {
        //1) Mostrar productos disponibles
        List<Producto> disponibles = catalogo.listar().stream()
                .filter(p -> p instanceof Prestable pN && !pN.estaPrestado())
                .collect(Collectors.toList());
        if (disponibles.isEmpty()) {
            System.out.println("No hay productos para prestar");
            return;
        }
        System.out.println("==PRODUCTOS DISPONIBLES==");
        disponibles.forEach(p -> System.out.println("* ID: " + p.getId() + " | " + p));
        System.out.println("Escribe el ID del producto: ");
        int id = sc.nextInt();
        sc.nextLine();

        Producto pEncontrado = disponibles.stream()
                .filter(p -> {
                    try {
                        return p.getId() == id;
                    } catch (NumberFormatException e) {
                        return false;
                    }
                })
                .findFirst()
                .orElse(null);
        if (pEncontrado == null) {
            System.out.println("El id indicado no existe");
            return;
        }
        listarUsuarios();
        System.out.println("Ingresa el código de usuario");

        int cUsuario = sc.nextInt();
        sc.nextLine();
        Usuario u1 = getUsuarioPorCodigo(cUsuario);

        if (u1 == null) {
            System.out.println("Usuario no encontrado.");
        }
        Prestable pPrestable = (Prestable) pEncontrado;
        pPrestable.prestar(u1);
    }

    public static void devolver() {
        List<Producto> pPrestados = catalogo.listar().stream()
                .filter(p -> p instanceof Prestable pN && pN.estaPrestado())
                .collect(Collectors.toList());
        if (pPrestados.isEmpty()) {
            System.out.println("No hay productos para prestar");
            return;
        }
        System.out.println("==PRODUCTOS PRESTADOS==");
        pPrestados.forEach(p -> System.out.println("* ID: " + p.getId() + " | " + p));
        System.out.println("Escribe el ID del producto: ");
        int id = sc.nextInt();
        sc.nextLine();

        Producto pEcontrado = pPrestados.stream()
                .filter(p -> {
                    try {
                        return p.getId() == id;
                    } catch (NumberFormatException e) {
                        return false;
                    }
                })
                .findFirst()
                .orElse(null);
        if (pEcontrado == null) {
            System.out.println("El id indicado no existe");
            return;
        }
        Prestable pE = (Prestable) pEcontrado;
        pE.devolver();
        System.out.println("Devuelto correctamente. ");
    }
    private static void exportarUsuario(){
        try {
            PersistenciaUsuarios.exportar(usuarios);
            System.out.println("Usuarios exportados correctamente. ");
        }catch (Exception e){
            System.out.println("Error al exportar usuarios" + e.getMessage());
        }

    }

    public static void importarUsuarios() {
        try {
            List<Usuario> cargados = PersistenciaUsuarios.importar();
            usuarios.clear();
            usuarios.addAll(cargados);
            System.out.println("Usuarios cargados con éxito");
        }catch (Exception e){
            System.out.println("Error al importar: " + e.getMessage());
        }
    }
}
