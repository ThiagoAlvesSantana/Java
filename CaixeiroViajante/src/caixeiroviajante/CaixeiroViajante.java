package caixeiroviajante;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CaixeiroViajante {

    public static int CIDADES = 100;
    public static int POPULACAO = 50;
    public int NOVA_POPULACAO = 0;
    private List<Integer> cromossomo = new ArrayList<Integer>();
    private List<Integer> pais = new ArrayList<Integer>();
    private int[][] populacao_cromosso = new int[POPULACAO][CIDADES];
    private int[][] populacao_nova = new int[9999][CIDADES];
    private int[] pai1 = new int[CIDADES];
    private int[] pai2 = new int[CIDADES];
    private double[] rotas = new double[9999];
    public static double TAXA = 0.1;
    private double[][] XY = new double[CIDADES][3];

    public static void main(String[] args) {
        CaixeiroViajante cx = new CaixeiroViajante();
        AlgoritmoGenetico ag = new AlgoritmoGenetico();
        Cidades ci = new Cidades();
        Scanner in = new Scanner(System.in);
        int opcao = 0;
        int destino = 0;
        int geracoes = 0;
        int recebeI = 0;

        System.out.println("Selecione um numero para escolher o mapa de uma cidade. ");
        System.out.println("Mapa cidade 1 digite '1'");
        System.out.println("Mapa cidade 2 digite '2'");
        System.out.println("Mapa cidade 3 digite '3'");
        System.out.println("Mapa cidade 4 digite '4'");
        System.out.println("Mapa cidade 5 digite '5'");
        System.out.println("Mapa cidade 6 digite '6'");
        opcao = in.nextInt();
        System.out.println("\nSelecione o destino de saida: ");
        destino = in.nextInt();
        System.out.println("\nInforme o numero de gerações: ");
        geracoes = in.nextInt();

        switch (opcao) {
            case 1:
                for (int i = 0; i < CIDADES; i++) {
                    System.arraycopy(Cidades.CIDADES1[i], 0, cx.XY[i], 0, Cidades.CIDADES1[i].length);
                }
                break;
            case 2:
                for (int i = 0; i < CIDADES; i++) {
                    System.arraycopy(Cidades.CIDADES2[i], 0, cx.XY[i], 0, Cidades.CIDADES2[i].length);
                }
                break;
            case 3:
                for (int i = 0; i < CIDADES; i++) {
                    System.arraycopy(Cidades.CIDADES3[i], 0, cx.XY[i], 0, Cidades.CIDADES3[i].length);
                }
                break;
            case 4:
                for (int i = 0; i < CIDADES; i++) {
                    System.arraycopy(Cidades.CIDADES4[i], 0, cx.XY[i], 0, Cidades.CIDADES4[i].length);
                }
                break;
            case 5:
                for (int i = 0; i < CIDADES; i++) {
                    System.arraycopy(Cidades.CIDADES5[i], 0, cx.XY[i], 0, Cidades.CIDADES5[i].length);
                }
                break;

            case 6:
                for (int i = 0; i < CIDADES; i++) {
                    System.arraycopy(Cidades.CIDADES6[i], 0, cx.XY[i], 0, Cidades.CIDADES6[i].length);
                }
                break;

            default:
                System.out.println("Este não é um indice válido!");
                System.exit(0);
        }

        ag.inicializa_cromossomo(cx.cromossomo, destino);
        ag.inicializa_populacao(cx.cromossomo, cx.populacao_cromosso, destino, cx.POPULACAO);
        int cont = 0;
        double distancia = 0.0;
        while (cont < geracoes) {
            if (cont == 0) {
                ag.avaliacao(cx.populacao_cromosso, cx.XY, cx.rotas, cx.POPULACAO);
                cx.NOVA_POPULACAO += 2;
                distancia = (int) cx.rotas[0];

            } else {
                cx.NOVA_POPULACAO += 2;
                ag.avaliacao(cx.populacao_nova, cx.XY, cx.rotas, cx.POPULACAO);
                distancia = (int) cx.rotas[0];
            }
            for (int i = 0; i < cx.POPULACAO; i++) {
                if (cx.rotas[i] < distancia && cx.rotas[i] != 0) {
                    distancia = cx.rotas[i];
                    recebeI = i;
                }
            }

            ag.selecao_roleta(cx.rotas, cx.pais, cx.POPULACAO);
            // Laço de reptição que passa os indivíduos selecionados
            // para fazer o cruzamento.
            for (int j = 0; j < CIDADES; j++) {
                cx.pai1[j] = cx.populacao_cromosso[cx.pais.get(0)][j];
                cx.pai2[j] = cx.populacao_cromosso[cx.pais.get(1)][j];
            }
            ag.cruzamento(cx.pai1, cx.pai2, cx.populacao_nova);

            int cont_popVelho = 0;
            int popTot = cx.POPULACAO + 2;
            for (int i = cx.NOVA_POPULACAO; i < popTot; i++) {
                for (int j = 0; j < cx.populacao_nova[i].length; j++) {
                    if (cont_popVelho < 50) {
                        cx.populacao_nova[i][j] = cx.populacao_cromosso[cont_popVelho][j];
                    }
                }
                cont_popVelho++;
            }
            cx.POPULACAO += 2;
            cont++;
            System.out.println("\nMenor Rota : ");
            for (int j = 0; j < CIDADES; j++) {
                System.out.print(cx.populacao_nova[recebeI][j] + "-->");
            }
            System.out.println("\nDistancia da Rota = " + distancia);
        }
    }
}
