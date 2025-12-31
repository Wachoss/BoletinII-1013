import java.util.*;

// Excepciones que el enunciado pide lanzar
class ExcepcionMemoriaLlena extends Exception {}
class ExcepcionDireccionInvalida extends Exception {}

public class GestorMemoria {

    // Clase para representar la información de cada bloque
    private class Bloque {
        int direccion;
        int tamaño;
        boolean libre;

        public Bloque(int direccion, int tamaño, boolean libre) {
            this.direccion = direccion;
            this.tamaño = tamaño;
            this.libre = libre;
        }
    }

    private List<Bloque> bloques;

    // (a) Implementa el constructor
    public GestorMemoria(int cantidad) {
        this.bloques = new LinkedList<Bloque>();
        bloques.add(new Bloque(0,cantidad,true));
    }

    // (b) Implementa el método reserva
    public int reserva(int cantidad) throws ExcepcionMemoriaLlena {
        int maxSize = 0;
        Bloque bloqueMaximo= null;
        Iterator<Bloque> itBloque = bloques.iterator();
        int indexMax = 0;
        int contador = 0;
        for(Bloque bloque : bloques){
            if(bloque.tamaño>=maxSize && bloque.libre){
                bloqueMaximo = bloque;
                maxSize = bloque.tamaño;
                indexMax = contador;
            }
            contador++;
        }
        if (bloqueMaximo == null) throw new ExcepcionMemoriaLlena();
        if(bloqueMaximo.tamaño == cantidad ) {
            bloqueMaximo.libre = false;
            return bloqueMaximo.direccion;
        } else if (bloqueMaximo.tamaño > cantidad) {
            bloqueMaximo.libre = false;

            Bloque aux = new Bloque(bloqueMaximo.direccion + cantidad , bloqueMaximo.tamaño - cantidad, true );
            bloqueMaximo.tamaño = cantidad;
            bloques.add(indexMax +1,aux);
            return bloqueMaximo.direccion;
        } else{
            throw new ExcepcionMemoriaLlena();
        }
    }

    // (c) Implementa el método libera
    public void libera(int direccion) throws ExcepcionDireccionInvalida {
        for (Bloque b : bloques) {
            if (b.direccion == direccion) {
                b.libre = true;
                return;
            }
        }
        throw new ExcepcionDireccionInvalida();
    }
}