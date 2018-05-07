package pratica1;

import org.jgrapht.*;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.util.Arrays;

public class Pratica1 {

    public static void main(String[] args){
        /**
         * Exemplo de grafo simples para geração de matriz de incidência.
         */
        String[] vertices = {"a", "b", "c", "d", "e", "f"};
        String[] arestas = {"ab", "ac", "bc", "bd", "be", "ce", "cd", "de", "df", "ef"};
        Graph grafo = criaGrafoSimples(vertices, arestas);
        System.out.println(geraMatrizIncidenciaSimples(grafo));

        /**
         * Exemplo do grafo não-orientado ponderado para obter o menor caminho.
         */
        String[] vertices2 = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};
        String[] arestas2 = {"AB2", "AG3", "AF7", "BG6", "BC4", "CH2", "CD2", "DH8", "DE1", "EI2", "EF6", "FI5", "IG1", "IH4", "GH3"};
        SimpleWeightedGraph grafoPonderado = criaGrafoPonderado(vertices2, arestas2);
        System.out.println(obterMenorCaminho(grafoPonderado, "A", "D"));
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
     * Cria um grafo do tipo "ponderado não-orientado".
     *
     * @param vertices Vértices do grafo.
     * @param arestas Arestas do grafo no formato "ab1", o número indica o peso da aresta.
     * @return Retorna um objeto SimpleWeightedGraph
     */
    private static SimpleWeightedGraph criaGrafoPonderado(String[] vertices, String[] arestas){
        SimpleWeightedGraph<String,DefaultWeightedEdge> grafoPonderado = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);

        for (String vertice : vertices) {
            grafoPonderado.addVertex(vertice);
        }

        for (String aresta : arestas){
            String vertice1 = aresta.substring(0, 1);
            String vertice2 = aresta.substring(1, 2);
            int peso = Integer.parseInt(aresta.substring(2));
            grafoPonderado.setEdgeWeight(grafoPonderado.addEdge(vertice1, vertice2), peso);
        }

        return grafoPonderado;
    }

    /**
     * Retorna o menor caminho entre dois vértices em um grafo ponderado. Utiliza o algoritmo de Dijkstra.
     *
     * @param grafoPonderado Grafo do tipo ponderado.
     * @param verticeOrigem Vértice de origem do caminho.
     * @param verticeDestino Vértice de destino do caminho.
     * @return String formatada com informação do caminho e a distância do caminho.
     */
    private static String obterMenorCaminho(SimpleWeightedGraph<String, DefaultWeightedEdge> grafoPonderado, String verticeOrigem, String verticeDestino){
        DijkstraShortestPath<String,DefaultWeightedEdge> menorCaminho = new DijkstraShortestPath <> (grafoPonderado);
        String caminho = "Menor caminho: " + menorCaminho.getPaths(verticeOrigem).getPath(verticeDestino) + System.lineSeparator();
        double distancia = menorCaminho.getPathWeight(verticeOrigem, verticeDestino);
        caminho += "Distância de " + verticeOrigem + " -> " + verticeDestino + ": " + distancia + System.lineSeparator();

        return caminho;
    }

    /**
     * Gera a matriz de incidência de um grafo simples, contabilizando o grau de cada vértice, o total de grau do grafo.
     *
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
                if (aresta.toString().contains(vertice)){
                    linhas[i] = "   1   ";
                    grau++;
                } else{
                    linhas[i] = "   0   ";
                }
                i++;
            }
            matrizIncidencia += vertice + ":" + Arrays.toString(linhas) + " Grau d(" + vertice + "): " + grau + System.lineSeparator();
        }

        matrizIncidencia += "Grau total: " + grafo.edgeSet().size() * 2 + System.lineSeparator();

        return matrizIncidencia;
    }
}
