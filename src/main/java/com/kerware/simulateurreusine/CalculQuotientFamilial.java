package com.kerware.simulateurreusine;

import com.kerware.simulateur.SituationFamiliale;

public class CalculQuotientFamilial {

    public double calculerPartsDeclarant(SituationFamiliale situation, int nbEnfants) {

        switch (situation) {
            case CELIBATAIRE:
            case DIVORCE:
                return 1;

            case MARIE:
                return 2;

            case VEUF:
                if (nbEnfants == 0) {
                    return 1;
                }
                return 2;
        }

        return 1;
    }

    public double calculerPartsTotales(
            SituationFamiliale situation,
            int nbEnfants,
            int nbHandicap,
            boolean parentIsole) {

        double parts = calculerPartsDeclarant(situation, nbEnfants);

        if (nbEnfants <= 2) {
            parts += nbEnfants * 0.5;
        } else {
            parts += 1 + (nbEnfants - 2);
        }

        if (parentIsole && nbEnfants > 0) {
            parts += 0.5;
        }

        parts += nbHandicap * 0.5;

        return parts;
    }
}