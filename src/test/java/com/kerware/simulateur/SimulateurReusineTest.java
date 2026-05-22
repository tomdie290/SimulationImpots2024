package com.kerware.simulateur;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SimulateurReusineTest {

    ICalculateurImpot simulateur = new SimulateurAdapter();

    void setUpSimulateur(int revenuNet, SituationFamiliale situation, int nbEnfants, int nbEnfantsHandicapes, boolean parentIsole) {
        simulateur.setRevenusNet(revenuNet);
        simulateur.setSituationFamiliale(situation);
        simulateur.setNbEnfantsACharge(nbEnfants);
        simulateur.setNbEnfantsSituationHandicap(nbEnfantsHandicapes);
        simulateur.setParentIsole(parentIsole);
        simulateur.calculImpotSurRevenuNet();
    }

    @Test
    void testCelibataireSansEnfant() {
        setUpSimulateur(30000, SituationFamiliale.CELIBATAIRE, 0, 0, false);
        int impot = simulateur.getImpotSurRevenuNet();
        assertTrue(impot > 0);
    }

    @Test
    void testCelibataireAvecEnfant() {
        setUpSimulateur(30000, SituationFamiliale.CELIBATAIRE, 1, 0, false);
        int impot = simulateur.getImpotSurRevenuNet();
        assertTrue(impot > 0);
    }

    @Test
    void testMarieSansEnfant() {
        setUpSimulateur(50000, SituationFamiliale.MARIE, 0, 0, false);
        int impot = simulateur.getImpotSurRevenuNet();
        assertTrue(impot > 0);
    }

    @Test
    void testMarieAvec3Enfants() {
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

    @Test
    void testEnfantHandicape() {
        setUpSimulateur(65000, SituationFamiliale.MARIE, 2, 1, false);
        int impot = simulateur.getImpotSurRevenuNet();
        assertTrue(impot > 0);
    }

    @Test
    void testRevenuEleve() {
        setUpSimulateur(200000, SituationFamiliale.CELIBATAIRE, 0, 0, false);
        int impot = simulateur.getImpotSurRevenuNet();
        assertTrue(impot > 0);
    }
    @Test
    void testDivorceSansEnfant() {
        setUpSimulateur(30000, SituationFamiliale.DIVORCE, 0, 0, false);
        assertTrue(simulateur.getImpotSurRevenuNet() >= 0);
    }
    @Test
    void test2Enfants() {
        setUpSimulateur(50000, SituationFamiliale.MARIE, 2, 0, false);
        assertTrue(simulateur.getImpotSurRevenuNet() > 0);
    }
    @Test
    void testBeaucoupEnfants() {
        setUpSimulateur(80000, SituationFamiliale.MARIE, 5, 0, false);
        assertTrue(simulateur.getImpotSurRevenuNet() >= 0);
    }
    @Test
    void testVeufZeroEnfant() {
    	 setUpSimulateur(80000, SituationFamiliale.VEUF, 0, 0, false);
         assertTrue(simulateur.getImpotSurRevenuNet() >= 0);
    }
    @Test
    void testVeufUnEnfant() {
    	 setUpSimulateur(80000, SituationFamiliale.VEUF, 1, 0, false);
         assertTrue(simulateur.getImpotSurRevenuNet() >= 0);
    }
    
}




