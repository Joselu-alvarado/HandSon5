public class Calculos {


    public double[][] multipMatrices(double[][] matriz1, double[][] matriz2) {
        int filas1 = matriz1.length;
        int columnas1 = matriz1[0].length;
        int columnas2 = matriz2[0].length;
//matriz para guardar los resultados
        double[][] resultado = new double[filas1][columnas2];

        for (int i = 0; i < filas1; i++) {
            for (int j = 0; j < columnas2; j++) {
                double suma = 0;
                for (int k = 0; k < columnas1; k++) {
                    suma += matriz1[i][k] * matriz2[k][j];
                }
                resultado[i][j] = suma;
            }
        }

        return resultado;
    }
    public double[][] matrizTranspuesta(double[][] matriz) {
        int filas = matriz.length;
        int columnas = matriz[0].length;

        double[][] transpuesta = new double[columnas][filas];
//rellena la matriz intercambiando f y colum
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                transpuesta[j][i] = matriz[i][j];
            }
        }
        return transpuesta;
    }

    public double[][] calcularInversa(double[][] matriz) {
        int n = matriz.length;
        double[][] identidad = new double[n][n];
        double[][] aumentada = new double[n][2 * n];

        //matriz identidad
        for (int i = 0; i < n; i++) {
            identidad[i][i] = 1;
        }

        // Crear matriz aumentada [matriz | identidad]
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                aumentada[i][j] = matriz[i][j];
                aumentada[i][j + n] = identidad[i][j];
            }
        }

        // Reducción por filas
        for (int i = 0; i < n; i++) {
            double pivote = aumentada[i][i];
            for (int j = 0; j < 2 * n; j++) {
                aumentada[i][j] /= pivote;
            }
            for (int k = 0; k < n; k++) {
                if (k != i) {
                    double factor = aumentada[k][i];
                    for (int j = 0; j < 2 * n; j++) {
                        aumentada[k][j] -= factor * aumentada[i][j];
                    }
                }
            }
        }

        //matriz inversa
        double[][] inversa = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = n; j < 2 * n; j++) {
                inversa[i][j - n] = aumentada[i][j];
            }
        }

        return inversa;
    }

    public double[] multiplicarMatrizVector(double[][] matriz, double[] vector) {
        int filas = matriz.length;
        int columnas = matriz[0].length;

        double[] resultado = new double[filas];

        for (int i = 0; i < filas; i++) {
            double suma = 0;
            for (int j = 0; j < columnas; j++) {
                suma += matriz[i][j] * vector[j];
            }
            resultado[i] = suma;
        }

        return resultado;
    }
}