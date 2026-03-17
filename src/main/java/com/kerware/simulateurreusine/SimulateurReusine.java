package com.kerware.simulateurreusine;

import com.kerware.simulateur.SituationFamiliale;

public class SimulateurReusine {

    private final CalculQuotientFamilial quotient = new CalculQuotientFamilial();
    private final CalculBareme bareme = new CalculBareme();
    private final CalculDecote decote = new CalculDecote();

    public long calculer(
            int revenuNet,
            SituationFamiliale situation,
            int nbEnfants,
            int nbHandicap,
            boolean parentIsole) {

        double abattement = calculerAbattement(revenuNet);

        double revenuFiscal = revenuNet - abattement;

        double partsDeclarant =
                quotient.calculerPartsDeclarant(situation, nbEnfants);

        double parts =
                quotient.calculerPartsTotales(
                        situation,
                        nbEnfants,
                        nbHandicap,
                        parentIsole);

        double impotDeclarant = bareme.calculer(revenuFiscal, partsDeclarant);

        double impotFoyer = bareme.calculer(revenuFiscal, parts);

        double impot = appliquerPlafonnement(
                parts,
                partsDeclarant,
                impotDeclarant,
                impotFoyer);

        double d = decote.calculer(impot, partsDeclarant);

        return Math.round(impot - d);
    }

    private double calculerAbattement(double revenu) {

        double abattement = revenu * ParametresFiscaux.TAUX_ABATTEMENT;

        if (abattement > ParametresFiscaux.ABATTEMENT_MAX) {
            abattement = ParametresFiscaux.ABATTEMENT_MAX;
        }

        if (abattement < ParametresFiscaux.ABATTEMENT_MIN) {
            abattement = ParametresFiscaux.ABATTEMENT_MIN;
        }

        return abattement;
    }

    private double appliquerPlafonnement(
            double parts,
            double partsDeclarant,
            double impotSans,
            double impotAvec) {

        double gain = impotSans - impotAvec;

        double ecart = parts - partsDeclarant;

        double plafond =
                (ecart / 0.5) * ParametresFiscaux.PLAFOND_DEMI_PART;

        if (gain > plafond) {
            return impotSans - plafond;
        }

        return impotAvec;
    }
}


