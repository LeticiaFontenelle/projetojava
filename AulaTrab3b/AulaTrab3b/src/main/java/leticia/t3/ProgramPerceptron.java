package leticia.t3;

import java.util.Arrays;

public class ProgramPerceptron {

    public static void main(String[] args) {
        double[][] datasetAnd = {
            {0, 0, 0},
            {0, 1, 0},
            {1, 0, 0},
            {1, 1, 1}
        };
        double[][] datasetOr = {
            {0, 0, 0},
            {0, 1, 1},
            {1, 0, 1},
            {1, 1, 1}
        };
        double[][] datasetNOr = {
            {0, 0, 1},
            {0, 1, 0},
            {1, 0, 0},
            {1, 1, 0}
        };
        double[][] datasetNAnd = {
            {0, 0, 1},
            {0, 1, 1},
            {1, 0, 1},
            {1, 1, 0}
        };

        mostraExemploDeTreinamento("AND", datasetAnd);
        mostraExemploDeTreinamento("OR", datasetOr);
        mostraExemploDeTreinamento("NOR", datasetNOr);
        mostraExemploDeTreinamento("NAND", datasetNAnd);
    }

    private static void mostraExemploDeTreinamento(String op, double[][] dataset) {
        Perceptron neuronio = new Perceptron(3);
        int ciclos = neuronio.aprender(dataset, 0.1);
        imprimeTabelaVerdade(op, neuronio, ciclos);
    }

    private static void imprimeTabelaVerdade(String op, Perceptron neuronio, int ciclos) {
        System.out.printf("%d ciclos para aprender %s:%n", ciclos, op);
        for (int x1 = 0; x1 <= 1; x1++) {
            for (int x2 = 0; x2 <= 1; x2++) {
                double[] entrada = {-1, x1, x2};
                int s = (int) neuronio.getSaida(entrada);
                System.out.printf("%d %s %d = %d %n", x1, op, x2, s);
            }
        }
        System.out.printf("Pesos atuais: %s%n", Arrays.toString(neuronio.getW()));
        System.out.println();
    }

}
