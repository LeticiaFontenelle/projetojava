package leticia.t3;

import java.awt.Color;
import static java.lang.Math.PI;
import java.util.Arrays;
import java.util.function.Function;
import static java.util.stream.IntStream.range;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.markers.SeriesMarkers;

public class ProgramAdaline {

    public static void main(String[] args) {
        int n = 45;
        Adaline neuronio = getNeuronioTreinado(n, 0.1, 20);

        double[] angulos = getX();
        double[] yf1 = getY(angulos, ProgramAdaline::f1);
        double[] yf2 = getY(angulos, ProgramAdaline::f2);
        double[] yf3 = getY(angulos, ProgramAdaline::f3);
        double[] yf = getY(angulos, ProgramAdaline::f);
        double[] yna = Arrays.stream(angulos).map(x -> neuronio.getSaida(new double[]{1, f1(x), f2(x), f3(x)})).toArray();

        XYChart chart = new XYChartBuilder().theme(Styler.ChartTheme.Matlab).title("Neurônio Adaline").build();
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);
        chart.getStyler().setLegendVisible(false);
        chart.addSeries("f1", angulos, yf1).setMarker(SeriesMarkers.NONE).setLineColor(Color.BLUE);
        chart.addSeries("f2", angulos, yf2).setMarker(SeriesMarkers.NONE).setLineColor(Color.ORANGE);
        chart.addSeries("f3", angulos, yf3).setMarker(SeriesMarkers.NONE).setLineColor(Color.GREEN);
        chart.addSeries("f", angulos, yf).setMarker(SeriesMarkers.NONE).setLineColor(Color.RED);
        chart.addSeries("na", angulos, yna).setMarker(SeriesMarkers.NONE).setLineColor(Color.MAGENTA);
        new SwingWrapper(chart).displayChart();
    }

    public static Adaline getNeuronioTreinado(int n, double taxa, int maxCiclos) {
        double[][] dataset = new double[n][4];
        for (int x = 0; x < dataset.length; x++) {
            dataset[x][0] = f1(x);
            dataset[x][1] = f2(x);
            dataset[x][2] = f3(x);
            dataset[x][3] = f(x);
        }

        Adaline neuronio = new Adaline(new double[]{-2.4013, 0.393, 1.902, 0.429});
        neuronio.aprender(dataset, taxa, maxCiclos);
        return neuronio;
    }

    public static double[] getX() {
        return range(0, 360).mapToDouble(i -> i).toArray();
    }

    public static double[] getY(double[] angulos, Function<Double, Double> fn) {
        return Arrays.stream(angulos).map(x -> fn.apply(x)).toArray();
    }

    public static double f1(double x) {
        return Math.sin(x * PI / 180);
    }

    public static double f2(double x) {
        return Math.cos(x * PI / 180);
    }

    public static double f3(double x) {
        return x * PI / 180;
    }

    public static double f(double x) {
        return -PI + 0.565 * f1(x) + 2.657 * f2(x) + 0.674 * f3(x);
    }
    
}
