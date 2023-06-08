import java.util.Scanner;

public class SLR {
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";
    //instancia para obtener los datos
    DataSet dataSet = new DataSet();
    Calculos discretMaths = new Calculos();
    Scanner lec = new Scanner(System.in);
    public static void main(String[] args) {
        SLR slr = new SLR();
        slr.print();
    }


    public void print() {
        //calculos regresionL
        double[][] x = dataSet.getX();
        double[] y = dataSet.getY();

        double[][] transpuesta = discretMaths.matrizTranspuesta(x);
        double[] resultadoTemp = discretMaths.multiplicarMatrizVector(transpuesta, y);
        double[][] multiplicacion = discretMaths.multipMatrices(transpuesta, x);
        double[][] inversa = discretMaths.calcularInversa(multiplicacion);
        double[] resultado = discretMaths.multiplicarMatrizVector(inversa, resultadoTemp);

        System.out.println(ANSI_BLUE+ "---------------------------------------");
        System.out.println("Valores beta:");
        for (int i = 0; i < resultado.length; i++) {
            System.out.println("B" + i + ": " + resultado[i]);
        }
        System.out.println("----------------------------------------------------"+ANSI_RESET);


        System.out.println("Ecuación:");
        System.out.print("y = " + resultado[0]);
        for (int i = 1; i < resultado.length; i++) {
            System.out.print(" + (" + resultado[i] + " * x" + i + ")");
        }
        System.out.println();


        System.out.println(ANSI_BLUE+ "-------------------------------------------------------");
        System.out.println("Predicciones para Machine Efficiency");
        for (int i = 0; i < 5; i++) {
            System.out.println("Ingrese el valor de x" + (i + 1) + ":");
            int xValue = lec.nextInt();
            double efficiency = calcularEficiencia(resultado, xValue);
            System.out.println("La eficiencia para x" + (i + 1) + " es de: " + efficiency);
        }
        System.out.println("-------------------------------------------------------"+ANSI_RESET);
    }

    private double calcularEficiencia(double[] resultado, int xValue) {
        double efficiency = resultado[0];
        for (int i = 1; i < resultado.length; i++) {
            efficiency += resultado[i] * Math.pow(xValue, i);
        }
        return efficiency;
    }
}
