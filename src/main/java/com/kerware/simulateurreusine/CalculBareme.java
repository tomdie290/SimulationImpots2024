package com.kerware.simulateurreusine;

public class CalculBareme {

    public double calculer(double revenu, double parts) {

        double revenuParPart = revenu / parts;

        double impot = 0;

        int[] limites = ParametresFiscaux.LIMITES;
        double[] taux = ParametresFiscaux.TAUX;

        int i = 0;

        while (i < taux.length) {

            if (revenuParPart >= limites[i] && revenuParPart < limites[i + 1]) {
                impot += (revenuParPart - limites[i]) * taux[i];
                break;
            } else {
                impot += (limites[i + 1] - limites[i]) * taux[i];
            }

            i++;
        }

        impot = impot * parts;

        return Math.round(impot);
    }
}