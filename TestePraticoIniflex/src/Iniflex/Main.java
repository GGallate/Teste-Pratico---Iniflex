package Iniflex;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Main {
	
	//Declarando salario minimo
	private static final BigDecimal SALARIO_MINIMO = new BigDecimal("1212.00");
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Funcionario> funcionarios = new ArrayList<>();
		
		//3.1 Adicionando os funcionarios na Tabela
		funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
		funcionarios.add(new Funcionario("Joao", LocalDate.of(1990, 05, 12), new BigDecimal("2284.38"), "Operador"));
		funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 05, 02), new BigDecimal("9836.14"), "Coordenador"));
		funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
		funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 01, 05), new BigDecimal("2234.68"), "Recepcionista"));
		funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
		funcionarios.add(new Funcionario("Arthur", LocalDate.of(1999, 03, 31), new BigDecimal("4071.84"), "Contador"));
		funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 07, 8), new BigDecimal("3017.75"), "Gerente"));
		funcionarios.add(new Funcionario("Heloisa", LocalDate.of(2003, 05, 24), new BigDecimal("1606.85"), "Eletricista"));
		funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 02), new BigDecimal("2799.93"), "Gerente"));
		
        //3.2 Removendo o funcionario especifico com o nome Joao
		funcionarios.removeIf(funcionario -> funcionario.getNome().equals("Joao"));
		
		//3.3 Print da lista de funcionarios
		System.out.println("Funcionários antes da remoção:");
        for (Funcionario funcionario : funcionarios) {
        	System.out.println(funcionario);
        }
		
        //3.4 Aumentando o salário dos funcionários em 10%
        BigDecimal percentualAumento = new BigDecimal("10");
        for (Funcionario funcionario : funcionarios) {
            funcionario.aumentarSalario(percentualAumento);
        }
        
        //3.5 Agrupando os funcionarios por função em um MAP
        Map<String, List<Funcionario>> funcionariosPorFuncao = agruparPorFuncao(funcionarios);

        //3.6 Print dos funcionários agrupados por função
        System.out.println("\nFuncionários agrupados por função:");
        for (Map.Entry<String, List<Funcionario>> entry : funcionariosPorFuncao.entrySet()) {
            System.out.println("Função: " + entry.getKey());
            for (Funcionario funcionario : entry.getValue()) {
                System.out.println(funcionario);
            }
        }
        
        //3.8 Print dos funcionários que fazem aniversário no mês 10 e 12
        System.out.println("\nFuncionários que fazem aniversário nos meses 10 e 12:");
        for (Funcionario funcionario : funcionarios) {
            int mesNascimento = funcionario.getNascData().getMonthValue();
            if (mesNascimento == 10 || mesNascimento == 12) {
                System.out.println(funcionario);
            }
        }
        
        //3.9 Print do usuario mais velho, mostrando o nome e a idade
        Funcionario funcionarioMaisVelho = funcionarios.stream()
                .max(Comparator.comparingInt(Funcionario::getIdade))
                .orElse(null);

        if (funcionarioMaisVelho != null) {
            System.out.println("\nFuncionário com a maior idade:");
            System.out.println("Nome: " + funcionarioMaisVelho.getNome() + ", Idade: " + funcionarioMaisVelho.getIdade());
        }
        
        //3.10 Print da lista de funcionários por ordem alfabética
        System.out.println("\nFuncionários em ordem alfabética:");
        funcionarios.sort(Comparator.comparing(Funcionario::getNome));
        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario);
        }
        
        //3.11 Print do total dos salários dos funcionários
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        NumberFormat numberFormat = NumberFormat.getInstance(new Locale("pt", "BR"));
        numberFormat.setMinimumFractionDigits(2);
        numberFormat.setMaximumFractionDigits(2);

        System.out.println("\nTotal dos salários dos funcionários:");
        System.out.println(numberFormat.format(totalSalarios));

        //3.12 Print de quantos salários mínimos ganha cada funcionário
        System.out.println("\nQuantos salários mínimos ganha cada funcionário:");
        for (Funcionario funcionario : funcionarios) {
            BigDecimal quantidadeSalariosMinimos = funcionario.getSalario().divide(SALARIO_MINIMO, 2, BigDecimal.ROUND_HALF_UP);
            System.out.println(funcionario.getNome() + " ganha " + quantidadeSalariosMinimos + " salários mínimos.");
        }
	}
	
	//Agrupar Funcionarios por função
	public static Map<String, List<Funcionario>> agruparPorFuncao(List<Funcionario> funcionarios) {
        Map<String, List<Funcionario>> map = new HashMap<>();
        for (Funcionario funcionario : funcionarios) {
            map.computeIfAbsent(funcionario.getFuncao(), k -> new ArrayList<>()).add(funcionario);
        }
        return map;
    }

}
