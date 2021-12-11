package leticia.t3;

import static java.lang.Math.abs;
import java.util.Arrays;
import static java.util.stream.IntStream.range;

public abstract class Neuronio {

    private static final double LIMITE = 0.0001;

    private double[] w;

    public Neuronio(int n) {
        this(range(0, n).mapToDouble(i -> random(-0.5, 0.5)).toArray());
    }

    public Neuronio(double[] w) {
        this.w = w;
    }

    public double[] getW() {
        return w;
    }

    public double getSaida(double[] entrada) {
        return ativacao(dot(entrada, w));
    }

    public abstract double ativacao(double soma);

    public static double dot(double[] u, double[] v) {
        return range(0, u.length).mapToDouble(i -> u[i] * v[i]).sum();
    }

    public int aprender(double[][] dataset, double taxa) {
        return aprender(dataset, taxa, 100000);
    }

    public int aprender(double[][] dataset, double taxa, int maxCiclos) {
        int ciclos = 0;
        boolean precisoTreinar;
        do {
            ciclos++;
            double taxaErro = treinar(dataset, taxa);
            precisoTreinar = taxaErro != 0 && ciclos < maxCiclos;
            System.out.printf("Fim do ciclo %d - Taxa de erro: %.2f%% - %s%n", ciclos, taxaErro * 100, Arrays.toString(w));
        } while (precisoTreinar);
        return ciclos;
    }

    public double treinar(double[][] dataset, double taxa) {
        double erros = 0;
        for (double[] linha : dataset) {
            double[] entrada = getEntrada(linha);
            double saidaDesejada = getSaidaDesejada(linha);
            boolean teveErro = ajustarPesos(entrada, saidaDesejada, taxa);
            if (teveErro) {
                erros++;
            }
        }
        return erros / dataset.length;
    }

    private double[] getEntrada(double[] linha) {
        double[] entrada = new double[linha.length];
        entrada[0] = getEntradaBias();
        System.arraycopy(linha, 0, entrada, 1, entrada.length - 1);
        return entrada;
    }

    protected abstract double getEntradaBias();

    private double getSaidaDesejada(double[] linha) {
        return linha[linha.length - 1];
    }

    public boolean ajustarPesos(double[] entrada, double saidaDesejada, double taxa) {
        double saidaAtual = getSaida(entrada);
        double erro = getErro(saidaDesejada, saidaAtual);
        range(0, w.length).forEach(i -> w[i] += taxa * erro * entrada[i]);
        return abs(erro) > LIMITE;
    }

    protected abstract double getErro(double saidaDesejada, double saidaAtual);

    public double getLinear(double x) {
        return (w[0] - w[1] * x) / w[2];
    }

    public static double random(double min, double max) {
        double f = Math.random() / Math.nextDown(1.0);
        return min * (1.0 - f) + max * f;
    }

}
