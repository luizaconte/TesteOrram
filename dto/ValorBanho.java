package dto;

import enums.PorteAnimal;

public class ValorBanho {
    private double valorDiaUtil;
    private double valorFinalSemana;
    private PorteAnimal porteAnimal;

    public ValorBanho() {
    }

    public ValorBanho(double valorDiaUtil, double valorFinalSemana, PorteAnimal porteAnimal) {
        this.valorDiaUtil = valorDiaUtil;
        this.valorFinalSemana = valorFinalSemana;
        this.porteAnimal = porteAnimal;
    }

    public double getValorDiaUtil() {
        return valorDiaUtil;
    }

    public void setValorDiaUtil(double valorDiaUtil) {
        this.valorDiaUtil = valorDiaUtil;
    }

    public double getValorFinalSemana() {
        return valorFinalSemana;
    }

    public void setValorFinalSemana(double valorFinalSemana) {
        this.valorFinalSemana = valorFinalSemana;
    }

    public PorteAnimal getPorteAnimal() {
        return porteAnimal;
    }

    public void setPorteAnimal(PorteAnimal porteAnimal) {
        this.porteAnimal = porteAnimal;
    }
}
