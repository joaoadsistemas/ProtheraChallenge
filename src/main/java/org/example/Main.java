package org.example;

import org.example.entities.Funcionario;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // 3.1 - Inserir todos os funcionários
        List<Funcionario> funcionarios = new ArrayList<>();


        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"),
                "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"),
                "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"),
                "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"),
                "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"),
                "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"),
                "Gerente"));


        // 3.2 - Remover o funcionário "João"
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));

        // 3.3 - Imprimir todos os funcionários
        funcionarios.forEach(System.out::println);


        System.out.println("==================================================");
        // 3.4 - Aumentar salário em 10%
        funcionarios.forEach(funcionario -> funcionario.setSalario(funcionario.getSalario().multiply(new BigDecimal("1.1"))));

        // 3.5 - Agrupar por função
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        // 3.6 - Imprimir funcionários agrupados por função
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println("Funcionários da função " + funcao + ":");
            lista.forEach(System.out::println);
        });

        System.out.println("==================================================");
        // 3.8 - Imprimir funcionários com aniversário em outubro e dezembro
        funcionarios.stream()
                .filter(funcionario -> funcionario.getDataNascimento().getMonthValue() == 10 || funcionario.getDataNascimento().getMonthValue() == 12)
                .forEach(System.out::println);

        System.out.println("==================================================");
        // 3.9 - Imprimir funcionário mais velho
        Funcionario maisVelho = funcionarios.stream()
                .max(Comparator.comparing(funcionario -> ChronoUnit.YEARS.between(funcionario.getDataNascimento(), LocalDate.now())))
                .orElse(null);
        System.out.println("Funcionário mais velho: " + maisVelho);


        System.out.println("==================================================");
        // 3.10 - Imprimir lista de funcionários por ordem alfabética
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .forEach(System.out::println);

        System.out.println("==================================================");
        // 3.11 - Imprimir total dos salários
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Total dos salários: " + totalSalarios.toString().replace(".", ","));


        System.out.println("==================================================");
        // 3.12 - Imprimir quantos salários mínimos ganha cada funcionário
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        funcionarios.forEach(funcionario -> {
            BigDecimal salarioMinimoGanho = funcionario.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_DOWN);
            System.out.println("Salários mínimos ganhos por " + funcionario.getNome() + ": " + salarioMinimoGanho);
        });
    }
}