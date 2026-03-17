package com.kerware.simulateur;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SimulateurLegacyTest {

    ICalculateurImpot simulateur = new SimulateurLegacyAdapter();

    void setUpSimulateur(int revenuNet, SituationFamiliale situation, int nbEnfants, int nbEnfantsHandicapes, boolean parentIsole) {
        simulateur.setRevenusNet(revenuNet);
        simulateur.setSituationFamiliale(situation);
        simulateur.setNbEnfantsACharge(nbEnfants);
        simulateur.setNbEnfantsSituationHandicap(nbEnfantsHandicapes);
        simulateur.setParentIsole(parentIsole);
        simulateur.calculImpotSurRevenuNet();
    }

    @Test
    void testCelibataire() {
        setUpSimulateur(30000, SituationFamiliale.CELIBATAIRE, 0, 0, false);
        int impot = simulateur.getImpotSurRevenuNet();
        assertTrue(impot > 0);
    }

    @Test
    void testMarie3Enfants() {
        setUpSimulateur(65000, SituationFamiliale.MARIE, 3, 0, false);
        int impot = simulateur.getImpotSurRevenuNet();
        assertTrue(impot > 0);
    }

    @Test
    void testParentIsole() {
        setUpSimulateur(35000, SituationFamiliale.DIVORCE, 1, 0, true);
        int impot = simulateur.getImpotSurRevenuNet();
        assertTrue(impot > 0);
    }
}