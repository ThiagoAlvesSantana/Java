package Simulador;

import java.util.Random;

public class Alocacao extends Thread {

    public int[][] memoria = new int[10][10]; 
    public int[] tempo_processo = new int[200];
    public int[] lista_processo = new int[200];
    public int[] id_processo = new int[200];
    public int[] tempo_processoB = new int[200];
    public int[] lista_processoB = new int[200];
    public int[] id_processoB = new int[200];
    public int cont = 0, aux3 = 0, id = 0;
    public int total_mem = memoria.length * memoria.length;
    public int total_memB = memoria.length * memoria.length;
    public int t = memoria.length * memoria.length;
    public double tempo_inicioF = 0;
    public double tempo_finalF = 0;
    public double tempoF = 0;
    public double tempo_inicioB = 0;
    public double tempo_finalB = 0;
    public double tempoB = 0;
    public int descartado = 0;
    public int fragMax = 0;
    public int fragMin = 100;

    Gravar g = new Gravar();

    //Método de inicialização da Thread
    @Override
    public void run() {
        Alocacao a = new Alocacao();//Obejeto da Classe
        a.iniciaMemoria(a.memoria, a.tempo_processo, a.lista_processo, a.id_processo, a.tempo_processoB, a.lista_processoB, a.id_processoB);
        //Método que inicializa a memoria e os processos
        System.out.println("\nFIRST FIT\n");
        tempo_inicioF = (double) System.currentTimeMillis();//Variável recebe tempo inicio First
        a.inserir_FirstFit();//Começa a inserção do First
        tempo_finalF = (double) System.currentTimeMillis();//Variável recebe tempo final de First
        a.tempo_geralFirst(this.tempoF, this.tempo_inicioF, this.tempo_finalF);//Método que chama o tempo Geral de First
        System.out.println("\nBEST FIT\n");
        tempo_inicioB = (double) System.currentTimeMillis();//Variável recebe tempo Inicio Best
        a.inserir_BestFit();//Começa a inserção do Best
        tempo_finalB = (double) System.currentTimeMillis();//Variável recebe tempo final de Best
        //Método que chama o tempo geral do Best
        a.tempo_geralBest(this.tempoB, this.tempo_inicioB, this.tempo_finalB);
    }

    //inicializa a memoria e atribui os valores dos processos, tempo de processo e ID
    public void iniciaMemoria(int[][] memoria, int[] tempo_processo, int[] lista_processo, int[] id_processo, int[] tempo_processoB, int[] lista_processoB, int[] id_processoB) {
        Alocacao a = new Alocacao();
        Random random = new Random();
        //inicia a matriz memoria
        for (int i = 0; i < memoria.length; i++) {
            for (int j = 0; j < memoria.length; j++) {
                memoria[i][j] = 0;
            }
        }
           // distribui valores aleatorios
        for (int i = 0; i < lista_processo.length; i++) {
            lista_processo[i] = random.nextInt(5) + 1;
            tempo_processo[i] = random.nextInt(5) + 1;
            id_processo[i] = i + 1;
            lista_processoB[i] = lista_processo[i];
            tempo_processoB[i] = tempo_processo[i];
            id_processoB[i] = id_processo[i];
        }
            
        // printa a informações na tela
        System.out.println("Numero de Processos: " + id_processo.length);
        System.out.println("Tamanho da memoria inicial: " + total_mem + " MB\n");
        for (int i = 0; i < id_processo.length; i++) {
            if (t >= lista_processo[i]) {
                t -= lista_processo[i];
                System.out.println("Processo: " + id_processo[i]);
                System.out.println("Tamanho: " + lista_processo[i]);
                System.out.println("Tempo: " + tempo_processo[i]);
                System.out.println("Processo Executando");
                System.out.println("Memoria Restante: " + t);
                System.out.println();
            } else {
                System.out.println("Processo: " + id_processo[i]);
                System.out.println("Tamanho: " + lista_processo[i]);
                System.out.println("Tempo: " + tempo_processo[i]);
                System.out.println("Processo Descartado");
                this.descartado++;
                System.out.println("Memoria Restante: " + t);
                System.out.println();
            }
        }
        System.out.println("Total de processos descartados: " + this.descartado);
        g.gravando(memoria, id_processo, lista_processo, tempo_processo, total_mem, this.descartado);//Grava a memória inicial, processos, tempo, tamanho e outros dados
    }

    //faz a inserção inicial a memoria
    public void inserir_FirstFit() {
        Execucao e = new Execucao();
        for (int i = 0; i < memoria.length; i++) {
            for (int j = 0; j < memoria.length; j++) {
                
                // vê se o processo cabe na memoria
                if (lista_processo[cont] <= total_mem || id_processo[cont] == id) {
                    if (cont == aux3) {
                        aux3++;
                        id = id_processo[cont];
                        total_mem -= lista_processo[cont];
                    }
                    if (lista_processo[cont] != 0) {
                        memoria[i][j] = id_processo[cont];
                        lista_processo[cont]--;
                    }
                    if (lista_processo[cont] == 0 && cont < lista_processo.length - 1) {
                        cont++;
                        aux3 = cont;
                        id = 0;
                    }
                    //se o processo não cabe na memoria ele vai para o proximo processo, e assim consecutivamente
                } else {
                    if (cont < lista_processo.length - 1) {
                        cont++;
                        aux3 = cont;
                        j--;
                    }
                }
            }
        }
        imprimir(memoria);
        contasFirst(memoria, lista_processo, tempo_processo);
        e.menorTempoFirst(lista_processo, tempo_processo, memoria);
    }

    //faz a inserção inicial a memoria
    public void inserir_BestFit() {
        Execucao e = new Execucao();
        cont = 0;
        id = 0;
        aux3 = 0;
        for (int i = 0; i < memoria.length; i++) {
            for (int j = 0; j < memoria.length; j++) {
                if (lista_processoB[cont] <= total_memB || id_processoB[cont] == id) {
                    if (cont == aux3) {
                        aux3++;
                        id = id_processoB[cont];
                        total_memB -= lista_processoB[cont];
                    }
                    if (lista_processoB[cont] != 0) {
                        memoria[i][j] = id_processoB[cont];
                        lista_processoB[cont]--;
                    }
                    if (lista_processoB[cont] == 0 && cont < lista_processoB.length - 1) {
                        cont++;
                        aux3 = cont;
                        id = 0;
                    }
                } else {
                    if (cont < lista_processoB.length - 1) {
                        cont++;
                        aux3 = cont;
                        j--;
                    }
                }
            }
        }
        imprimir(memoria);
        contasBest(memoria, lista_processoB, tempo_processoB);
        e.menorTempoBest(lista_processoB, tempo_processoB, memoria);
    }

    //verifica se ha espaços na memoria
    public void verificaMemFirst(int[][] memoria, int[] lista_processo, int[] tempo_processo) {
        int contPos = 0;
        int[] verifica_memi = new int[200];
        int[] verifica_memj = new int[200];
        int[] numPosicao = new int[200];

        for (int i = 0; i < memoria.length; i++) {
            for (int j = 0; j < memoria.length; j++) {
                // verifica se tem espaço na memoria, se sim ela guarda as posiçoes livres
                if (memoria[i][j] == 0) {
                    if (numPosicao[contPos] == 0) {
                        verifica_memi[contPos] = i;
                        verifica_memj[contPos] = j;
                    }
                    numPosicao[contPos] += 1;
                } else {
                    if (numPosicao[contPos] > 0) {
                        contPos++;
                    }
                }
            }
        }
        verifProcNovoFirst(memoria, numPosicao, verifica_memi, verifica_memj, lista_processo, tempo_processo);
    }

    //verifica se ha espaços na memoria
    public void verificaMemBest(int[][] memoria, int[] lista_processoB, int[] tempo_processoB) {
        int contPos = 0;
        int[] verifica_memi = new int[200];
        int[] verifica_memj = new int[200];
        int[] numPosicao = new int[200];

        for (int i = 0; i < memoria.length; i++) {
            for (int j = 0; j < memoria.length; j++) {
                if (memoria[i][j] == 0) {
                    if (numPosicao[contPos] == 0) {
                        verifica_memi[contPos] = i;
                        verifica_memj[contPos] = j;
                    }
                    numPosicao[contPos] += 1;
                } else {
                    if (numPosicao[contPos] > 0) {
                        contPos++;
                    }
                }
            }
        }
        verifProcNovoBest(memoria, numPosicao, verifica_memi, verifica_memj, lista_processoB, tempo_processoB);
    }

    //verifica o novo processo que vai entrar na memoria
    public void verifProcNovoBest(int[][] memoria, int[] numPosicao, int[] verifica_memi, int[] verifica_memj, int[] lista_processoB, int[] tempo_processoB) {
        Execucao e = new Execucao();
        int id_novo = 0, melhor = 9999, aux = 0;
        for (int k = 0; k < lista_processoB.length; k++) {
            for (int contPos = 0; contPos < numPosicao.length; contPos++) {
                // verifica o melhor lugar que o processo pode ser alocado na mem.
                if (lista_processoB[k] > 0 && lista_processoB[k] <= numPosicao[contPos] && numPosicao[contPos] < melhor) {
                    melhor = numPosicao[contPos];
                    id_novo = k + 1;
                    aux = contPos;
                }
            }
            if (id_novo > 0) {
                inserir_novoBest(memoria, lista_processoB, id_novo, verifica_memi[aux], verifica_memj[aux], numPosicao[aux], k);
                contasBest(memoria, lista_processoB, tempo_processoB);
                verificaMemBest(memoria, lista_processoB, tempo_processoB);
                id_novo = 0;
            }
        }
        e.menorTempoBest(lista_processoB, tempo_processoB, memoria);
    }

    //verifica o novo processo que vai entrar na memoria
    public void verifProcNovoFirst(int[][] memoria, int[] numPosicao, int[] verifica_memi, int[] verifica_memj, int[] lista_processo, int[] tempo_processo) {
        Execucao e = new Execucao();
        int id_novo = 0;
        for (int k = 0; k < lista_processo.length; k++) {
            for (int contPos = 0; contPos < numPosicao.length; contPos++) {
                // somente verifica se o processo cabe no espaço vazio qua há na memoria
                if (lista_processo[k] > 0 && lista_processo[k] <= numPosicao[contPos]) {
                    id_novo = k + 1;
                    inserir_novoFirst(memoria, lista_processo, id_novo, verifica_memi[contPos], verifica_memj[contPos], numPosicao[contPos], k);
                    contasFirst(memoria, lista_processo, tempo_processo);
                    verificaMemFirst(memoria, lista_processo, tempo_processo);
                }
            }
        }
        e.menorTempoFirst(lista_processo, tempo_processo, memoria);
    }

    //inseri o novo processo na memoria
    public void inserir_novoFirst(int[][] memoria, int[] lista_processo, int id_novo, int verifica_memi, int verifica_memj, int numPosicao, int k) {
        for (int i = verifica_memi; i < memoria.length; i++) {
            for (int j = verifica_memj; j < memoria.length; j++) {
                if (numPosicao > 0 && lista_processo[k] > 0) {
                    memoria[i][j] = id_novo;
                    lista_processo[k]--;
                }
                numPosicao--;
            }
            verifica_memj = 0;
        }
        System.out.println("\nProcesso " + id_novo + " novo");
        imprimir(memoria);
    }

    //inseri o novo processo na memoria
    public void inserir_novoBest(int[][] memoria, int[] lista_processoB, int id_novo, int verifica_memi, int verifica_memj, int numPosicao, int k) {
        for (int i = verifica_memi; i < memoria.length; i++) {
            for (int j = verifica_memj; j < memoria.length; j++) {
                if (numPosicao > 0 && lista_processoB[k] > 0) {
                    memoria[i][j] = id_novo;
                    lista_processoB[k]--;
                }
                numPosicao--;
            }
            verifica_memj = 0;
        }
        System.out.println("\nProcesso " + id_novo + " novo");
        imprimir(memoria);
    }

    //imprimi a memoria
    public void imprimir(int[][] memoria) {
        for (int i = 0; i < memoria.length; i++) {
            for (int j = 0; j < memoria.length; j++) {
                System.out.print(memoria[i][j] + " ");
            }
            System.out.println();
        }
        g.grava_FirstFit(memoria);
    }
    //verifica a fragmentação maxima e minima

    public void contasFirst(int[][] memoria, int[] lista_processo, int[] tempo_processo) {
        Alocacao a = new Alocacao();
        int total_tempo = 0;
        int total_processo = 0;
        float tempoM = 0;
        int frag = 0;
        //percorre o vetor de tempo
        //calcula o tempo de execução
        for (int i = 0; i < lista_processo.length; i++) {
            if (lista_processo[i] == 0 && tempo_processo[i] > 0) {
                total_processo++;
                total_tempo += tempo_processo[i];
            }
        }
        
        //vê a fragmentação da memoria
        for (int i = 0; i < memoria.length; i++) {
            for (int j = 0; j < memoria.length; j++) {
                if (memoria[i][j] == 0) {
                    frag++;
                }
            }
        }
        tempoM = total_tempo / total_processo; //tempo medio
        //fragmentação maxima
        if (frag > fragMax && frag != 0) {
            fragMax = frag;
        }
           // frag mim
        if (frag < fragMin && frag > 0) {
            fragMin = frag;
        }
        
        //printa na tela a info
        if (fragMax != 0 && fragMin != 100) {
            System.out.println("Fragmentacao Maxima de Memoria: " + fragMax);
            System.out.println("Fragmentacao Minima de Memoria: " + fragMin);
        } else {
            System.out.println("Não existe fragmentação nesse unidade de tempo");
        }
        System.out.println("Tempo Medio de Execução por Unidade de Tempo: " + tempoM);
        System.out.println("Grau de Multiprogramação por Unidade de Tempo: " + total_processo);
        g.gravarContaF(fragMax, fragMin, tempoM, total_processo);
    }

    public void contasBest(int[][] memoria, int[] lista_processoB, int[] tempo_processoB) {
        Alocacao a = new Alocacao();
        int total_tempo = 0;
        int total_processo = 0;
        float tempoM = 0;
        int frag = 0;
        //percorre o vetor de tempo
        for (int i = 0; i < lista_processoB.length; i++) {
            if (lista_processoB[i] == 0 && tempo_processoB[i] > 0) {
                total_processo++;
                total_tempo += tempo_processoB[i];
            }
        }
        for (int i = 0; i < memoria.length; i++) {
            for (int j = 0; j < memoria.length; j++) {
                if (memoria[i][j] == 0) {
                    frag++;
                }
            }
        }
        tempoM = total_tempo / total_processo;
        if (frag > fragMax) {
            fragMax = frag;
        }

        if (frag < fragMin && frag > 0) {
            fragMin = frag;
        }
        if (fragMax != 0 && fragMin != 100) {
            System.out.println("Fragmentacao Maxima de Memoria: " + fragMax);
            System.out.println("Fragmentacao Minima de Memoria: " + fragMin);
        } else {
            System.out.println("Não existe fragmentação nesse unidade de tempo");
        }
        System.out.println("Tempo Medio de Execução por Unidade de Tempo: " + tempoM);
        System.out.println("Grau de Multiprogramação por Unidade de Tempo: " + total_processo);
        g.gravarContaB(fragMax, fragMin, tempoM, total_processo);
    }
    //faz os calculos do tempo de exe do prog
    public void tempo_geralFirst(double tempoF, double tempo_inicioF, double tempo_finalF) {
        tempoF = tempo_finalF - tempo_inicioF;//subtrai tempo inicial pelo tempo final e acha o tempo de execução
        System.out.println((String.format("\nTempo Total de Execucao do Algoritmo First Fit: 0%.0f Milissegundo", tempoF)));
        g.gravaTempo_First(tempoF);
    }

    public void tempo_geralBest(double tempoB, double tempo_inicioB, double tempo_finalB) {
        tempoB = tempo_finalB - tempo_inicioB;//subtrai tempo inicial pelo tempo final e acha o tempo de execução
        System.out.println((String.format("\nTempo Total de Execucao do Algoritmo Best Fit: 0%.0f Milissegundo", tempoB)));
        g.gravaTempo_Best(tempoB);
    }
}
