package Iniflex;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Pessoa {
	private String nome;
	private LocalDate nascData;
	
	public Pessoa(String nome, LocalDate dataNascimento) {
        this.nome = nome;
        this.nascData = dataNascimento;
    }
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public LocalDate getNascData() {
		return nascData;
	}
	public void setNascData(LocalDate nascData) {
		this.nascData = nascData;
	}
	public int getIdade() {
        return Period.between(nascData, LocalDate.now()).getYears();
    }
	@Override
	public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "Nome: " + nome + ", Data de Nascimento: " + nascData.format(formatter);
    }
}
