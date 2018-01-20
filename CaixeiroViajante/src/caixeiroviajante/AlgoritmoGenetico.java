package caixeiroviajante;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class AlgoritmoGenetico {

    private static int index = 0;

    //Inicializa o cromossomo de maneira aleatoria.
    public List inicializa_cromossomo(List cromossomo, int destino) {
        CaixeiroViajante cx = new CaixeiroViajante();
        Random r = new Random();
        int aux = 0;

        for (int i = 1; i <= cx.CIDADES; i++) {
            cromossomo.add(i);
        }

        cromossomo.remove(destino - 1); // Removendo destino selecionado. 

        return cromossomo;
    }

    //Inicializa o cromossomo de maneira aleatoria.
    public int[][] inicializa_populacao(List cromossomo, int[][] populacao_cromosso, int destino, int POPULACAO) {

        for (int i = 0; i < populacao_cromosso.length; i++) {
            populacao_cromosso[i][0] = destino;
        }

        for (int i = 0; i < POPULACAO; i++) {
            Collections.shuffle(cromossomo);
            for (int j = 1; j <= cromossomo.size(); j++) {
                populacao_cromosso[i][j] = (int) cromossomo.get(j - 1);
            }
        }
        return populacao_cromosso;
    }

    //Calcula a menor rota para um cromossomo da população.
    public double[] avaliacao(int[][] populacao_cromosso, double[][] cidades, double[] rotas, int POPULACAO) {
        for (int i = 0; i < rotas.length; i++) {
            rotas[i] = 0;
        }

        for (int i = 0; i < POPULACAO; i++) {
            for (int j = 0; j < populacao_cromosso[i].length - 1; j++) {
                int cidade1 = populacao_cromosso[i][j];
                int cidade2 = populacao_cromosso[i][j + 1];
                rotas[i] += distanciaEuclidiana(cidade1, cidade2, cidades);
            }
        }
        return rotas;
    }

    //Seleção de pais pelo o método roleta.
    public List selecao_roleta(double[] rotas, List pais, int populacao) {
        Random r = new Random();
        double soma_rotas = 0.0;
        double aux = 0.0;

        for (int i = 0; i < populacao; i++) {
            soma_rotas += rotas[i];
        }
        aux = soma_rotas;
        for (int i = 0; i < 2; i++) {
            double sorteado = r.nextDouble() * soma_rotas;
            int posicaoEscolhida = -1;
            do {
                posicaoEscolhida++;
                aux -= rotas[posicaoEscolhida];
            } while (aux > sorteado);
            pais.add(posicaoEscolhida);
            aux = soma_rotas;
        }
        return pais;
    }

    //Cruzamento PMX.
    public void cruzamento(int[] pai1, int[] pai2, int[][] populacao_nova) {
        int[] pai1_aux = new int[pai1.length];
        int[] pai2_aux = new int[pai2.length];
        float corte = ponto_de_corte(pai1, pai2);
        int corte1 = (int) corte + 1;
        int corte2 = (corte1 - 1) + (corte1 - 1);
        for (int i = 0; i < corte1; i++) {
            pai1_aux[i] = pai1[i];
            pai2_aux[i] = pai2[i];
            for (int j = corte1; j < corte2; j++) {
                pai1_aux[j] = pai2[j];
                pai2_aux[j] = pai1[j];
                for (int k = corte2; k < pai1.length; k++) {
                    pai1_aux[k] = pai1[k];
                    pai2_aux[k] = pai2[k];
                    if (pai1_aux[j] == pai1_aux[i]) {
                        int troca = i;
                        if (pai2_aux[pai1_aux[i]] != pai1_aux[j]) {
                            int aux = pai1_aux[i];
                            pai1_aux[i] = pai2_aux[troca];
                            pai2_aux[troca] = aux;
                        }
                    }
                }
            }
        }
        mutacao(pai1_aux, pai2_aux, populacao_nova);
    }

    //Mutação em ordem de 1 elemento tipo SWAP.
    public void mutacao(int[] filho1, int[] filho2, int[][] populacao_nova) {
        CaixeiroViajante cx = new CaixeiroViajante();
        Swap(filho1);
        Swap(filho2);
        if (index >= 100) {
            index = 0;
        }
        for (int i = 0; i < filho1.length; i++) {
            populacao_nova[index][i] = filho1[i];
        }
        index++;
        for (int i = 0; i < filho2.length; i++) {
            populacao_nova[index][i] = filho2[i];
        }
        index++;
    }

    //Ponto de corte, método de 2 pontos de corte, calcula o tamanho do pai e faz o ponto de corte.
    private float ponto_de_corte(int[] pai1, int[] pai2) {
        float corte, corte2;
        corte = Math.round(pai1.length / 3.0);
        corte2 = Math.round(pai2.length / 3.0);
        if (corte == corte2) {
            return corte;
        }
        return 0;
    }

    private int[] Swap(int[] filho) {
        CaixeiroViajante cx = new CaixeiroViajante();
        Random r = new Random();
        int swap1 = 0;
        int swap2 = 0;

        for (int i = 1; i < cx.CIDADES; i++) {
            double taxa = r.nextDouble() * 1.0;
            if (taxa <= cx.TAXA) {
                swap1 = filho[i];
                int gene_sorteado = 0;
                // Este laço de repetição impede que o destino de partida
                // seja mudado.
                do {
                    gene_sorteado = r.nextInt(cx.CIDADES);
                } while (gene_sorteado == 0);

                swap2 = filho[gene_sorteado];
                filho[i] = swap2;
                filho[gene_sorteado] = swap1;

            } else {
            }

        }
        return filho;
    }

    // Função que caulcula a distancia euclidiana entre dois pontos.
    public double distanciaEuclidiana(int X1, int X2, double[][] XY) {
        double resultado = 0;
        int aux = 0;
        int aux2 = 0;
        for (int i = 0; i < XY.length; i++) {
            for (int j = 0; j < XY[i].length; j++) {
                if (X1 == XY[i][0]) {
                    aux = i;
                }
                if (X2 == XY[i][j]) {
                    aux2 = i;
                }
            }
        }
        resultado = Math.sqrt((Math.pow((XY[aux][1] - XY[aux2][1]), 2)
                + Math.pow((XY[aux][2] - XY[aux2][2]), 2)));

        return resultado;
    }
}