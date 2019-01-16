package br.com.view;

import br.com.controller.bo.CarroBO;
import br.com.model.bean.CarroBean;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int op, in_sim_or_not = 0, id = 0;
        CarroBean car = new CarroBean();
        CarroBO bo = new CarroBO();
        Scanner ler = new Scanner(System.in);

        System.out.println("1 - Insert");
        System.out.println("2 - Update");
        System.out.println("3 - Delete");
        System.out.println("0 - Sair");
        do {
            op = ler.nextInt();
            switch (op) {
                case 1:
                    System.out.println("Insert");
                    System.out.println("Digite a marca: ");
                    car.setNm_marca(ler.next());
                    System.out.println("Digite o modelo: ");
                    car.setNm_modelo(ler.next());
                    bo.save(car);
                    break;
                case 2:
                    System.out.println("Updade");
                    System.out.println("Digite o ID: ");
                    id = ler.nextInt();
                    car = (CarroBean) bo.getByID(car, id);
                    
                    System.out.println("Carro");
                    System.out.println("Id = "+car.getCd_carro());
                    System.out.println("Marca = "+car.getNm_marca());
                    System.out.println("Modelo = "+car.getNm_modelo());
                    
                    System.out.println("Update na marca  <1> Sim <2> Nao");
                    in_sim_or_not = ler.nextInt();
                    if (in_sim_or_not == 1) {
                        System.out.println("Digite a marca: ");
                        car.setNm_marca(ler.next());
                        in_sim_or_not = 0;
                    }
                    System.out.println("Update no modelo <1> Sim <2> Nao");
                    in_sim_or_not = ler.nextInt();
                    if (in_sim_or_not == 1) {
                        System.out.println("Digite o modelo: ");
                        car.setNm_modelo(ler.next());
                        in_sim_or_not = 0;
                    }
                    bo.update(car);
                    break;
                case 3:
                    System.out.println("Delete");
                    bo.delete(car);
                    break;
                default:
                    break;
            }

        } while (op != 0);

    }
}
