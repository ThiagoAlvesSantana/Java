package Simulador;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        Gravar g = new Gravar();
        Alocacao a = new Alocacao();
        Execucao e = new Execucao();
        g.criarArquivo();
        a.start();//Chamada da thread Alocação
        e.start();//Chamada da thread Execução
        try {
            a.join();
            e.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
}
