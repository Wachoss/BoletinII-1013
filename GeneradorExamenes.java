import java.util.*;

public class GeneradorExamenes {

    // Clase proporcionada por el enunciado
    public static class Cuestion {
        public String codigo;
        public String enunciado;
        public List<String> respuestas; // La primera es la correcta 
    }

    /**
     * (a) Generar examen
     * @return Map con Código de Cuestión y sus respuestas barajadas
     */
    public Map<String, List<String>> generar_examen(int cantidad, Map<String, Cuestion> banco) {
        if(banco.keySet().size() < cantidad) return null;

        List<Cuestion> bara = new ArrayList<>(banco.values());
        barajar(bara);

        Map<String, List<String>> res = new HashMap<>();

        for(int i = 0; i<cantidad; i++){
            List <String>resp = new LinkedList<>(bara.get(i).respuestas);
            barajar(resp);

            res.put(bara.get(i).codigo, resp);


        }
        return res;
    }

    /**
     * (b) Corregir examen
     * @param respuestasDadas Map con Código de Cuestión y respuesta elegida
     */
    public double corregir_examen(Map<String, String> respuestasDadas, Map<String, Cuestion> banco, double correcta, double incorrecta, double blanco) {
        double nota = 0.0;
        for(String code : respuestasDadas.keySet()){
            String res = respuestasDadas.get(code);
            String resCorrecta = banco.get(code).respuestas.getFirst();
            if(res == null || res.equals(" ")){
                nota += blanco;
            } else if(res.equals(resCorrecta)){

                nota +=correcta;
            } else nota +=incorrecta;
        }
        return nota;
    }

    // Métodos auxiliares según el boletín 
    public int aleatorio(int maximo) {
        return new Random().nextInt(maximo);
    }

    public <T> void barajar(List<T> lista) {
        Collections.shuffle(lista);
    }
}