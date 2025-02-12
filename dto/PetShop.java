package dto;

import enums.PorteAnimal;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class PetShop {
    private String nome;
    private double distanciaKm;
    private List<ValorBanho> valorBanhos;

    public PetShop() {
    }

    public PetShop(String nome, double distanciaKm, List<ValorBanho> valorBanhos) {
        this.nome = nome;
        this.distanciaKm = distanciaKm;
        this.valorBanhos = valorBanhos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }

    public void setDistanciaKm(double distanciaKm) {
        this.distanciaKm = distanciaKm;
    }

    public List<ValorBanho> getValorBanhos() {
        return valorBanhos;
    }

    public void setValorBanhos(List<ValorBanho> valorBanhos) {
        this.valorBanhos = valorBanhos;
    }

    public double calcularValorBanho(boolean isFinalSemana, PorteAnimal porteAnimal, Integer qtdAnimais) {
        if (qtdAnimais <= 0) return 0D;

        return qtdAnimais * getValorBanhos()
                .stream()
                .filter(valorBanho -> Objects.equals(valorBanho.getPorteAnimal(), porteAnimal))
                .findFirst()
                .map(valorBanho -> isFinalSemana ? valorBanho.getValorFinalSemana() : valorBanho.getValorDiaUtil())
                .orElse(0D);
    }
}
