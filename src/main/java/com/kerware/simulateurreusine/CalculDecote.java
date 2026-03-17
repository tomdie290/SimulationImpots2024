package com.kerware.simulateurreusine;

public class CalculDecote {

    public double calculer(double impot, double partsDeclarant) {

        double decote = 0;

        if (partsDeclarant == 1) {

            if (impot < ParametresFiscaux.SEUIL_DECOTE_SEUL) {

                decote =
                        ParametresFiscaux.DECOTE_MAX_SEUL
                                - (impot * ParametresFiscaux.TAUX_DECOTE);
            }
        }

        if (partsDeclarant == 2) {

            if (impot < ParametresFiscaux.SEUIL_DECOTE_COUPLE) {

                decote =
                        ParametresFiscaux.DECOTE_MAX_COUPLE
                                - (impot * ParametresFiscaux.TAUX_DECOTE);
            }
        }

        decote = Math.round(decote);

        if (decote > impot) {
            decote = impot;
        }

        return decote;
    }
}