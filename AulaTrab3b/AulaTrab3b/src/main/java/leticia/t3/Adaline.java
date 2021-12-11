package leticia.t3;

import static java.lang.Math.pow;

public class Adaline extends Neuronio {

    public Adaline(int n) {
        super(n);
    }

    public Adaline(double[] w) {
        super(w);
    }

    @Override
    public double ativacao(double soma) {
        return soma;
    }

    @Override
    protected double getEntradaBias() {
        return 1;
    }

    @Override
    protected double getErro(double saidaDesejada, double saidaAtual) {
        return pow(saidaDesejada - saidaAtual, 2) / 2;
    }

}
