package leticia.t3;

public class Perceptron extends Neuronio {

    public Perceptron(int n) {
        super(n);
    }

    public Perceptron(double[] w) {
        super(w);
    }

    @Override
    public double ativacao(double soma) {
        return step0(soma);
    }

    public static double step0(double x) {
        return x < 0 ? 0 : 1;
    }

    @Override
    protected double getEntradaBias() {
        return -1;
    }

    @Override
    protected double getErro(double saidaDesejada, double saidaAtual) {
        return saidaDesejada - saidaAtual;
    }

}
