package pratica1;

import org.jgrapht.*;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.Arrays;

public class Pratica1 {

    public static void main(String[] args){
        String[] vertices = {"a", "b", "c", "d", "e", "f"};
        String[] arestas = {"ab", "ac", "bc", "bd", "be", "ce", "cd", "de", "df", "ef"};
        Graph grafo = criaGrafoSimples(vertices, arestas);
        System.out.println(geraMatrizIncidenciaSimples(grafo));

        String[] vertices1 = {"a", "b", "c", "d", "e"};
        String[] arestas2 = {"ab", "ad", "bc", "bd", "cd", "de"};
        Graph grafo1 = criaGrafoSimples(vertices1, arestas2);
        System.out.println(geraMatrizIncidenciaSimples(grafo1));
    }

    /**
     * Cria um grafo do tipo "simples".
     *
     * @param vertices Vértices do grafo.
     * @param arestas Arestas do grafo no formato "ab"
     * @return Retorna um objeto SimpleGraph
     */
    private static Graph criaGrafoSimples(String[] vertices, String[] arestas){
        Graph<String, DefaultEdge> grafoSimples = new SimpleGraph<>(DefaultEdge.class);

        for (String vertice : vertices) {
            grafoSimples.addVertex(vertice);
        }

        for (String aresta : arestas){
            String vertice1 = aresta.substring(0,1);
            String vertice2 = aresta.substring(1);
            grafoSimples.addEdge(vertice1, vertice2);
        }

        return grafoSimples;
    }

    /**
     * Gera a matriz de incidência de um grafo simples, contabilizando o grau de cada vértice, o total de grau do grafo.
     * @param grafo Objeto Graph
     * @return Retorna uma string com a matriz de incidência.
     */
    private static String geraMatrizIncidenciaSimples(Graph<String, DefaultEdge> grafo){
        String[] linhas = new String[grafo.edgeSet().size()];
        String matrizIncidencia = "  " + grafo.edgeSet() + System.lineSeparator();

        for(String vertice: grafo.vertexSet()){
            int i = 0;
            int grau = 0;

            for (DefaultEdge aresta: grafo.edgeSet()){
                String ehIncidente;

                if (aresta.toString().contains(vertice)){
                    ehIncidente = "   1   ";
                    grau++;
                } else{
                    ehIncidente = "   0   ";
                }

                linhas[i] = ehIncidente;
                i++;
            }
            matrizIncidencia += vertice + ":" + Arrays.toString(linhas) + " Grau d(" + vertice + "): " + grau + System.lineSeparator();
        }

        matrizIncidencia += "Grau total: " + grafo.edgeSet().size() * 2 + System.lineSeparator();

        return matrizIncidencia;
    }
}
