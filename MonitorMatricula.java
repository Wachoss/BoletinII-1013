import java.util.*;

public class MonitorMatricula {
    HashMap<String,String> horarios;
    HashMap<String,Integer> limites;
    HashMap<String,List<String>> matriculados;

    /**
     * Constructor por defecto
     */
    public MonitorMatricula() {
        horarios = new HashMap<>();
        limites = new HashMap<>();
        matriculados = new HashMap<>();
    }

    /**
     * (b) Crea un nuevo grupo o asignatura si no existe
     */
    public boolean crearGrupo(String asignatura, String grupo, int limite, String horario) {
        String idGrupo = asignatura + grupo;
        if(horarios.get(idGrupo) == null){
            horarios.put(idGrupo, horario);
            limites.put(idGrupo, limite);
            matriculados.put(idGrupo, new LinkedList<>());
            return true;
        }
        return false;
    }

    /**
     * (c) Matricula a un estudiante en múltiples grupos
     */
    public boolean matricular(String nombre, List<String> asignaturas, List<String> grupos) {
        boolean aux = false;
        Set<String> aceptados = new HashSet<>();

        Iterator<String> itAsignaturas = asignaturas.iterator();
        Iterator<String> itGrupos = grupos.iterator();

        while(itAsignaturas.hasNext()){
            String idGrupo = itAsignaturas.next() + itGrupos.next();
            if(limites.get(idGrupo)>matriculados.get(idGrupo).size()){
                matriculados.get(idGrupo).add(nombre);
                itAsignaturas.remove();
                itGrupos.remove();
                String hor1 = horarios.get(idGrupo);
                for(String hor2 : aceptados)
                    if(seSolapan(hor1, hor2)) aux = true;
                aceptados.add(hor1);

            }
        }

        return aux;
    }

    /**
     * Método auxiliar proporcionado por el enunciado 
     * Comprueba si dos horarios se solapan.
     */
    private boolean seSolapan(String h1, String h2) {
        return h1.equals(h2);
    }
}