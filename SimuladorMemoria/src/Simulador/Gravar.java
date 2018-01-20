package Simulador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Gravar {

    FileWriter arquivo;
    String log = "===================================\r\r\n\t\tLOG\r\r\n===================================\r\r\n";

    //Cria arquivo
    public void criarArquivo() {
        System.out.println("Arquivo com os 'LOGS' criado!!!");
        try {
            arquivo = new FileWriter(new File("resul.txt"));
        } catch (IOException e) {
        } catch (Exception e) {
        }
    }

    //Grava no arquivo memoria, processo, tempo, tamanho e outros dados
    public void gravando(int[][] memoria, int[] id_processo, int[] lista_processo, int[] tempo_processo, int total_mem, int descartado) {
        String linha;
        int t = memoria.length * memoria.length;
        try {
            BufferedReader br = new BufferedReader(new FileReader("resul.txt"));
            try (BufferedWriter arq = new BufferedWriter(new FileWriter("resul.txt", true))) {
                br.ready();
                linha = br.readLine();
                arq.append(log);
                arq.append("\r\n" + (String.format("Numero de Processos: %d", +id_processo.length)));
                arq.append("\r\n" + (String.format("Tamanho da memoria inicial: %d MB", +total_mem)));
                arq.append("\r\r\n");
                for (int i = 0; i < lista_processo.length; i++) {
                    if (t >= lista_processo[i]) {
                        t -= lista_processo[i];
                        arq.append("\r\n" + (String.format("Processo: " + id_processo[i])));
                        arq.append("\r\n" + (String.format("Tamanho: " + lista_processo[i])));
                        arq.append("\r\n" + (String.format("Tempo: " + tempo_processo[i])));
                        arq.append("\r\nProcesso executando");
                        arq.append("\r\r\n");
                    } else {
                        arq.append("\r\n" + (String.format("Processo: " + id_processo[i])));
                        arq.append("\r\n" + (String.format("Tamanho: " + lista_processo[i])));
                        arq.append("\r\n" + (String.format("Tempo: " + tempo_processo[i])));
                        arq.append("\r\nProcesso descartado");
                        arq.append("\r\r\n");
                    }
                }
                arq.append("\r\n" + (String.format("Total de Processo descartado: " + descartado)));
                arq.append("\r\r\n");
            }
        } catch (IOException e) {
        } catch (Exception e) {
        }
    }

    //Grava o nome do algoritmo First 
    public void gravanome_FirstFit() {
        String linha;
        try {
            BufferedReader br = new BufferedReader(new FileReader("resul.txt"));
            try (BufferedWriter arq = new BufferedWriter(new FileWriter("resul.txt", true))) {
                br.ready();
                linha = br.readLine();
                arq.append("\r\r\n");
                arq.append("===================================\r\r\n");
                arq.append("\t\tFIRT FIT\r\r\n");
                arq.append("===================================\r\r\n");
            }
        } catch (IOException e) {
        } catch (Exception e) {
        }
    }

    //Grava o nome do algoritmo Best
    public void gravanome_BestFit() {
        String linha;
        try {
            BufferedReader br = new BufferedReader(new FileReader("resul.txt"));
            try (BufferedWriter arq = new BufferedWriter(new FileWriter("resul.txt", true))) {
                br.ready();
                linha = br.readLine();
                arq.append("\r\r\n");
                arq.append("===================================\r\r\n");
                arq.append("\t\tBEST FIT\r\r\n");
                arq.append("===================================\r\r\n");
            }
        } catch (IOException e) {
        } catch (Exception e) {
        }
    }

    //Grava a execução do algoritmo First
    public void grava_FirstFit(int[][] memoria) {
        String linha;
        try {
            BufferedReader br = new BufferedReader(new FileReader("resul.txt"));
            try (BufferedWriter arq = new BufferedWriter(new FileWriter("resul.txt", true))) {
                br.ready();
                linha = br.readLine();
                arq.append("\r\r\n");
                for (int i = 0; i < memoria.length; i++) {
                    for (int j = 0; j < memoria.length; j++) {
                        arq.append(String.format(memoria[i][j] + " "));
                    }
                    arq.append("\r\r\n");
                }
            }
        } catch (IOException e) {
        } catch (Exception e) {
        }
    }

    //Grava a execução do algoritmo Best
    public void grava_BestFit(int[][] memoria) {
        String linha;
        try {
            BufferedReader br = new BufferedReader(new FileReader("resul.txt"));
            try (BufferedWriter arq = new BufferedWriter(new FileWriter("resul.txt", true))) {
                br.ready();
                linha = br.readLine();
                arq.append("\r\r\n");
                arq.append("===================================\r\r\n");
                arq.append("\t\tFIRT FIT\r\r\n");
                arq.append("===================================\r\r\n");
                for (int i = 0; i < memoria.length; i++) {
                    for (int j = 0; j < memoria.length; j++) {
                        arq.append(String.format(memoria[i][j] + " "));
                    }
                    arq.append("\r\r\n");
                }
            }
        } catch (IOException e) {
        } catch (Exception e) {
        }
    }

    //Grava as contas
    public void gravarContaF(int fragMax, int fragMin, float tempoM, int total_processo) {
        String linha;
        try {
            BufferedReader br = new BufferedReader(new FileReader("resul.txt"));
            try (BufferedWriter arq = new BufferedWriter(new FileWriter("resul.txt", true))) {
                br.ready();
                linha = br.readLine();
                arq.append("\r\r\n");
                if (fragMax != 0 && fragMin != 100) {
                arq.append("\r\n" + (String.format("Fragmentacao de Minima: " + fragMin)));
                arq.append("\r\n" + (String.format("Fragmentacao de Maxima: " + fragMax)));
                }
                arq.append("\r\n" + (String.format("Tempo Medio de Execução por Unidade de Tempo: " + tempoM)));
                arq.append("\r\n" + (String.format("Grau de Multiprogramação por Unidade de Tempo: " + total_processo)));
                arq.append("\r\r\n");
            }
        } catch (IOException e) {
        } catch (Exception e) {
        }
    }

    //Grava as contas
    public void gravarContaB(int fragMax, int fragMin, float tempoM, int total_processo) {
        String linha;
        try {
            BufferedReader br = new BufferedReader(new FileReader("resul.txt"));
            try (BufferedWriter arq = new BufferedWriter(new FileWriter("resul.txt", true))) {
                br.ready();
                linha = br.readLine();
                arq.append("\r\r\n");
                if (fragMax != 0 && fragMin != 100) {
                arq.append("\r\n" + (String.format("Fragmentacao de Minima: " + fragMin)));
                arq.append("\r\n" + (String.format("Fragmentacao de Maxima: " + fragMax)));
                }
                arq.append("\r\n" + (String.format("Tempo Medio de Execução por Unidade de Tempo: " + tempoM)));
                arq.append("\r\n" + (String.format("Grau de Multiprogramação por Unidade de Tempo: " + total_processo)));
                arq.append("\r\r\n");
            }
        } catch (IOException e) {
        } catch (Exception e) {
        }
    }

    //Grava o tempo de execução do First
    public void gravaTempo_First(double tempo) {
        String linha;
        try {
            BufferedReader br = new BufferedReader(new FileReader("resul.txt"));
            try (BufferedWriter arq = new BufferedWriter(new FileWriter("resul.txt", true))) {
                br.ready();
                linha = br.readLine();
                arq.append("\r\r\n");
                arq.append("\r\n" + (String.format("Tempo Total de Execucao do Algoritmo First Fit: 0%.0f Milissegundo", +tempo)));
                arq.append("\r\r\n");
            }
        } catch (IOException e) {
        } catch (Exception e) {
        }
    }

    //Grava o tempo de execucao Best
    public void gravaTempo_Best(double tempo) {
        String linha;
        try {
            BufferedReader br = new BufferedReader(new FileReader("resul.txt"));
            try (BufferedWriter arq = new BufferedWriter(new FileWriter("resul.txt", true))) {
                br.ready();
                linha = br.readLine();
                arq.append("\r\r\n");
                arq.append("\r\n" + (String.format("Tempo Total de Execucao do Algoritmo Best Fit: 0%.0f Milissegundo", +tempo)));
                arq.append("\r\r\n");
            }
        } catch (IOException e) {
        } catch (Exception e) {
        }
    }
}
