package com.kerware.simulateur;

public class SimulateurLegacyAdapter implements ICalculateurImpot {

    private final Simulateur simulateur = new Simulateur(); // simulateur d’origine

    private int revenuNet;
    private SituationFamiliale situation;
    private int nbEnfants;
    private int nbEnfantsHandicapes;
    private boolean parentIsole;
    private int impot;

    @Override
    public void setRevenusNet(int rn) {
        this.revenuNet = rn;
    }

    @Override
    public void setSituationFamiliale(SituationFamiliale sf) {
        this.situation = sf;
    }

    @Override
    public void setNbEnfantsACharge(int nbe) {
        this.nbEnfants = nbe;
    }

    @Override
    public void setNbEnfantsSituationHandicap(int nbesh) {
        this.nbEnfantsHandicapes = nbesh;
    }

    @Override
    public void setParentIsole(boolean pi) {
        this.parentIsole = pi;
    }

    @Override
    public void calculImpotSurRevenuNet() {
        long resultat = simulateur.calculImpot(
                revenuNet,
                situation,
                nbEnfants,
                nbEnfantsHandicapes,
                parentIsole
        );
        this.impot = (int) resultat;
    }

    @Override
    public int getImpotSurRevenuNet() {
        return impot;
    }

    @Override
    public int getRevenuFiscalReference() {
        return 0;
    }

    @Override
    public int getAbattement() {
        return 0;
    }

    @Override
    public int getNbPartsFoyerFiscal() {
        return 0;
    }

    @Override
    public int getImpotAvantDecote() {
        return 0;
    }

    @Override
    public int getDecote() {
        return 0;
    }

    public long calculImpot(int revenuNet, SituationFamiliale situation,
                            int nbEnfants, int nbEnfantsHandicapes, boolean parentIsole) {
        return simulateur.calculImpot(revenuNet, situation, nbEnfants, nbEnfantsHandicapes, parentIsole);
    }
}