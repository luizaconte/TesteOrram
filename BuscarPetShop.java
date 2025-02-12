import dto.PetShop;
import dto.ValorBanho;
import enums.PorteAnimal;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class BuscarPetShop {

    private static final List<PetShop> petShopList = new ArrayList<>();

    public static void main(String[] args) {

        try {
            // buscando petshops pré informados
            carregarPetShops();

            System.out.println("Buscar melhor petshop!");
            System.out.println("Informe os dados no padrão: <data_do_banho> <quantidade_cães_pequenos> <quantidade_cães_grandes>");
            System.out.println("Exemplo: 03/08/2018 3 5");

            Scanner scanner = new Scanner(System.in);
            System.out.print("Informe os dados: ");
            String inputDados = scanner.nextLine();
            scanner.close();

            if (Objects.isNull(inputDados) || inputDados.isEmpty()) {
                throw new Exception("Dados inválidos.");
            }

            String[] inputs = inputDados.split(" ");
            if (inputs.length != 3) {
                throw new Exception("Entrada inválida! Use o formato correto.");
            }

            LocalDate data = LocalDate.parse(inputs[0], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            boolean isFinalSemana = Objects.equals(data.getDayOfWeek(), DayOfWeek.SATURDAY) || Objects.equals(data.getDayOfWeek(), DayOfWeek.SUNDAY);

            int pequenos = Integer.parseInt(inputs[1]);
            int grandes = Integer.parseInt(inputs[2]);

            PetShop melhorPetShop = petShopList.stream()
                    .min(Comparator.comparingDouble((PetShop p) -> p.calcularValorBanho(isFinalSemana, PorteAnimal.PEQUENO, pequenos) + p.calcularValorBanho(isFinalSemana, PorteAnimal.GRANDE, grandes))
                            .thenComparingDouble(PetShop::getDistanciaKm))
                    .orElse(null);

            if (Objects.nonNull(melhorPetShop)) {
                double precoFinal = melhorPetShop.calcularValorBanho(isFinalSemana, PorteAnimal.PEQUENO, pequenos) + melhorPetShop.calcularValorBanho(isFinalSemana, PorteAnimal.GRANDE, grandes);
                System.out.printf("%s R$ %.2f\n", melhorPetShop.getNome(), precoFinal);
            } else {
                throw new Exception("Nâo foi possível encontrar o melhor PetShop!");
            }

        } catch (NumberFormatException e) {
            System.out.println("Erro: Certifique-se de que a quantidade de cães pequenos e grandes são números válidos.");
        } catch (DateTimeParseException e) {
            System.out.println("Erro: Certifique-se de que a data está no formato solicitado.");
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }

    public static void carregarPetShops() {
        // Meu Canino Feliz
        List<ValorBanho> valorBanhosCaninoFeliz = new ArrayList<>();
        double banhoPequenoDiaUtilCaninoFeliz = 20.0;
        double percAumentoCaninoFeliz = 1 + 20.0 / 100.0;
        valorBanhosCaninoFeliz.add(new ValorBanho(banhoPequenoDiaUtilCaninoFeliz, banhoPequenoDiaUtilCaninoFeliz * percAumentoCaninoFeliz, PorteAnimal.PEQUENO));
        double banhoGrandeDiaUtilCaninoFeliz = 40.0;
        valorBanhosCaninoFeliz.add(new ValorBanho(banhoGrandeDiaUtilCaninoFeliz, banhoGrandeDiaUtilCaninoFeliz * percAumentoCaninoFeliz, PorteAnimal.GRANDE));
        petShopList.add(new PetShop("Meu Canino Feliz", 2.0, valorBanhosCaninoFeliz));

        // Vai Rex
        List<ValorBanho> valorBanhosVaiRex = new ArrayList<>();
        double aumentoFinalSemana = 5;
        double banhoPequenoDiaUtilVaiRex = 15.0;
        valorBanhosVaiRex.add(new ValorBanho(banhoPequenoDiaUtilVaiRex, banhoPequenoDiaUtilVaiRex + aumentoFinalSemana, PorteAnimal.PEQUENO));
        double banhoGrandeDiaUtilVaiRex = 50.0;
        valorBanhosVaiRex.add(new ValorBanho(banhoGrandeDiaUtilVaiRex, banhoGrandeDiaUtilVaiRex + aumentoFinalSemana, PorteAnimal.GRANDE));
        petShopList.add(new PetShop("Vai Rex", 1.7, valorBanhosVaiRex));

        //ChowChawgas
        List<ValorBanho> valorBanhosChowChawgas = new ArrayList<>();
        valorBanhosChowChawgas.add(new ValorBanho(30, 30, PorteAnimal.PEQUENO));
        valorBanhosChowChawgas.add(new ValorBanho(45, 45, PorteAnimal.GRANDE));
        petShopList.add(new PetShop("ChowChawgas", 0.8, valorBanhosChowChawgas));
    }
}
