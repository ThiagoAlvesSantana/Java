package Simulador;

public class Execucao extends Alocacao implements Runnable {
    Gravar g = new Gravar();

    @Override
    public void run() {
        Execucao e = new Execucao();
        e.menorTempoFirst(lista_processo, tempo_processo, memoria);
        e.menorTempoBest(lista_processoB, tempo_processoB, memoria);
    }

    //verifica qual é o proximo processo que sai da memoria
    public void menorTempoFirst(int[] lista_processo, int[] tempo_processo, int[][] memoria) {
        Execucao e = new Execucao();
        int menor = 9999, idMenor = 0;
        int k = 0;
        for (int i = 0; i < lista_processo.length; i++) {
            if (lista_processo[i] == 0 && (tempo_processo[i] > 0 && tempo_processo[i] < menor)) {
                menor = tempo_processo[i];
                k = i;
                idMenor = i + 1;
            }
        }
        tempo_processo[k] = 0;
        if (idMenor != 0) {
            e.tirarProcessoFirst(idMenor, memoria);
            e.tirarRepFirst(menor, memoria, lista_processo, tempo_processo);
            tempoFirst(menor, lista_processo, tempo_processo, memoria);
        }
    }
    //verifica qual é o proximo processo que sai da memoria
    public void menorTempoBest(int[] lista_processoB, int[] tempo_processoB, int[][] memoria) {
        Execucao e = new Execucao();
        int menor = 9999, idMenor = 0;
        int k = 0;
        for (int i = 0; i < lista_processoB.length; i++) {
            if (lista_processoB[i] == 0 && (tempo_processoB[i] > 0 && tempo_processoB[i] < menor)) {
                menor = tempo_processoB[i];
                k = i;
                idMenor = i + 1;
            }
        }
        tempo_processoB[k] = 0;
        if (idMenor != 0) {
            e.tirarProcessoBest(idMenor, memoria);
            e.tirarRepBest(menor, memoria, lista_processoB, tempo_processoB);
            tempoBest(menor, lista_processoB, tempo_processoB, memoria);
        }
    }
    //diminuindo o tempo dos processos que estão sendo execultados
    public void tempoFirst(int menor, int[] lista_processo, int[] tempo_processo, int[][] memoria) {
        Alocacao a = new Alocacao();
        for (int i = 0; i < lista_processo.length; i++) {
            if (tempo_processo[i] > 0 && lista_processo[i] == 0) {
                tempo_processo[i] -= menor;
            }
        }
        a.verificaMemFirst(memoria, lista_processo, tempo_processo);
    }
    //diminuindo o tempo dos processos que estão sendo execultados
    public void tempoBest(int menor, int[] lista_processoB, int[] tempo_processoB, int[][] memoria) {
        Alocacao a = new Alocacao();
        for (int i = 0; i < lista_processoB.length; i++) {
            if (tempo_processoB[i] > 0 && lista_processoB[i] == 0) {
                tempo_processoB[i] -= menor;
            }
        }
        a.verificaMemBest(memoria, lista_processoB, tempo_processoB);
    }
    //depois que tirarProcessoFirst tirou o primeiro processo que acabou tirarRepFirst esta tirando todos os outros que tambem acabaram 
    public void tirarRepFirst(int menor, int[][] memoria, int[] lista_processo, int[] tempo_processo) {
        int idMenor = 0;
        for (int i = 0; i < lista_processo.length; i++) {
            if (tempo_processo[i] == menor && lista_processo[i] == 0) {
                tempo_processo[i] = 0;
                idMenor = i + 1;
                tirarProcessoFirst(idMenor, memoria);
            }
        }
    }
    //depois que tirarProcessoBest tirou o primeiro processo que acabou tirarRepBest esta tirando todos os outros que tambem acabaram
    public void tirarRepBest(int menor, int[][] memoria, int[] lista_processoB, int[] tempo_processoB) {
        int idMenor = 0;
        for (int i = 0; i < lista_processoB.length; i++) {
            if (tempo_processoB[i] == menor && lista_processoB[i] == 0) {
                tempo_processoB[i] = 0;
                idMenor = i + 1;
                tirarProcessoBest(idMenor, memoria);
            }
        }
    }
    //tira o primeiro processo que acaba
    public void tirarProcessoFirst(int idMenor, int[][] memoria) {
        Alocacao a = new Alocacao();
        for (int i = 0; i < memoria.length; i++) {
            for (int j = 0; j < memoria.length; j++) {
                if (idMenor == memoria[i][j]) {
                    memoria[i][j] = 0;
                }
            }
        }
        System.out.println("\nProcesso " + idMenor + " fora");
        a.imprimir(memoria);
    }
    //tira o primeiro processo que acaba
    public void tirarProcessoBest(int idMenor, int[][] memoria) {
        Alocacao a = new Alocacao();
        for (int i = 0; i < memoria.length; i++) {
            for (int j = 0; j < memoria.length; j++) {
                if (idMenor == memoria[i][j]) {
                    memoria[i][j] = 0;
                }
            }
        }
        System.out.println("\nProcesso " + idMenor + " fora");
        a.imprimir(memoria);
    }

}
