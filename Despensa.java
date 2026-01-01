import java.util.*;

public class Despensa {

    // (a) Define aquí la parte privada
    // Pista: Necesitas asociar cada nombre de ingrediente con su cantidad.

    private Map<String, Integer> existencias;
    /**
     * Constructor por defecto
     */
    public Despensa() {
        this.existencias = new HashMap<String,Integer>();
    }

    /**
     * (a) Añade los ingredientes de la cesta incrementando la cantidad en 1
     */
    public void reponer(Collection<String> cesta) {
        for(String ingrediente : cesta){
            if(!existencias.containsKey(ingrediente)){
                existencias.put(ingrediente,1);
            } else {
                int cantidadActual = existencias.get(ingrediente);
                existencias.put(ingrediente, cantidadActual + 1);
            }
        }
    }

    /**
     * (b) Comprueba qué ingredientes del pedido faltan en la despensa
     * No debe modificar el contenido de la despensa.
     */
    public Collection<String> buscar(Collection<String> pedido) {
        Map<String,Integer> aux = new HashMap<String, Integer>(existencias);
        Collection<String> res = new ArrayList<>();
        for (String ing : pedido){
            if(aux.containsKey(ing)){
                int cantidadActual = aux.get(ing);
                if(cantidadActual == 0){
                    aux.remove(ing);
                    res.add(ing);
                }else{
                    aux.put(ing, cantidadActual-1);

                }
            } else{
                res.add(ing);
            }
        }
        return res;
    }

    /**
     * (c) Si hay suficiente de todo, resta los ingredientes y devuelve true
     * Si falta algo, devuelve false y no modifica nada.
     */
    public boolean servir(Collection<String> pedido) {
        if(!buscar(pedido).isEmpty()) return false;

        for(String ing : pedido){
            int cantidadActual = existencias.get(ing);
            existencias.put(ing, cantidadActual -1 );
        }
        return true;
    }
}