import java.util.*;

public class Biblioteca {

    // Clase auxiliar para representar la información de un libro 
    private class Libro {
        String isbn;
        String titulo;
        List<String> autores;
        int copias;

        public Libro(String isbn, String titulo, List<String> autores) {
            this.isbn = isbn;
            this.titulo = titulo;
            this.autores = autores;
            this.copias = 1; // La primera vez que se añade 
        }
    }

    // (a) Define aquí la parte privada [cite: 284]
    // Pista: Necesitas una estructura para buscar por ISBN [O(1)]
    // y otra para obtener libros de un autor ordenados.
    private HashMap<String, Libro> librosPorIsbn;
    private HashMap<String, TreeSet<String>> titulosPorAutor;

    /**
     * Constructor por defecto [cite: 285]
     */
    public Biblioteca() {
        this.librosPorIsbn = new HashMap<>();
        this.titulosPorAutor = new HashMap<>();
    }

    /**
     * (b) Añadir un libro [cite: 286, 287]
     * Si el ISBN ya existe, se incrementa el número de copias.
     */
    public void addBook(String Title, List<String> authors, String ISBN) {
        if(librosPorIsbn.containsKey(ISBN)){
            librosPorIsbn.get(ISBN).copias +=1;
        }else {
            Libro libro = new Libro(ISBN, Title, authors);
            librosPorIsbn.put(ISBN,libro);
            for(String autor : authors) {
                if(!titulosPorAutor.containsKey(autor)) titulosPorAutor.put(autor,new TreeSet<String>());
                titulosPorAutor.get(autor).add(Title);
            }
        }
    }

    /**
     * (c) Listar títulos de un autor ordenados alfabéticamente 
     * Nota: El enunciado pide imprimir por pantalla.
     */
    public void listByAuthor(String name) {
        if(!titulosPorAutor.containsKey(name)) return;
        System.out.println("Libros de " +name +" :");
        for (String titulo: titulosPorAutor.get(name)){
            System.out.println(" "+titulo +",");
        }
    }
}