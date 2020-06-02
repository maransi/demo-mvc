package com.mballem.curso.boot.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.mballem.curso.boot.web.annotation.CpfCnpj;

@SuppressWarnings("serial")
@Entity
@Table( name = "funcionarios",
		indexes = { @Index(name="idxFuncNome", columnList="nome", unique = true)})
@NamedQueries( {	@NamedQuery(name="funcionario.nome", query = "Select f From Funcionario f Where nome like :nome"),
					@NamedQuery(name="funcionario.cargo", 
								query = "Select f From Funcionario f Where f.cargo.id = :cargo"),
					@NamedQuery(name="funcionario.data",
								query="Select f From Funcionario f Where f.dataEntrada Between :dataInicial And :dataFinal")
})
public class Funcionario extends AbstractEntity<Long> {
	
	@NotBlank
	@Size(max = 255, min = 3)
	@Column( nullable = false)
	private String nome;
	
	
	@NotBlank
	@Size(min=11, max=15)
	@CpfCnpj
	@Column( length = 15)
	private String cpfCnpj;
	
	@Positive( message="Salário deve ser maior que zero")
	@NotNull
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	@Column( nullable = false, columnDefinition = "DECIMAL(7,2) DEFAULT 0.00")
	private BigDecimal salario;

	@NotNull
	@PastOrPresent(message = "{PastOrPresent.funcionario.dataEntrada}")
	@DateTimeFormat(iso = ISO.DATE)
	@Column( nullable = false, columnDefinition = "DATE")
	private LocalDate dataEntrada;
	
	@DateTimeFormat(iso = ISO.DATE)
	@Column( columnDefinition = "DATE")
	private LocalDate dataSaida;
	
	@NotNull(message="{NotNull.funcionario.cargo}")
	@ManyToOne
	@JoinColumn( name = "cargo_id_fk", 
					foreignKey = @ForeignKey(name = "fkFunc_Cargo") )	
	private Cargo cargo;
	

	@NotNull
	@ManyToOne
	@JoinColumn( name="departamento_id_fk",
					foreignKey = @ForeignKey(name="fkFunc_Depto") )
	private Departamento departamento;
	
	
	@Valid		// Informa que deve ser validado conforme as validações na classe de Endereco
	@OneToOne( cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn( name = "endereco_id_fk",
					foreignKey = @ForeignKey(name = "fkFunc_Endereco") )	
	private Endereco endereco;

	public Funcionario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Funcionario(String nome, BigDecimal salario, LocalDate dataEntrada, LocalDate dataSaida, Cargo cargo,
			Endereco endereco) {
		super();
		this.nome = nome;
		this.salario = salario;
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		this.cargo = cargo;
		this.endereco = endereco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public LocalDate getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(LocalDate dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public LocalDate getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(LocalDate dataSaida) {
		this.dataSaida = dataSaida;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cargo == null) ? 0 : cargo.hashCode());
		result = prime * result + ((dataEntrada == null) ? 0 : dataEntrada.hashCode());
		result = prime * result + ((dataSaida == null) ? 0 : dataSaida.hashCode());
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((salario == null) ? 0 : salario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcionario other = (Funcionario) obj;
		if (cargo == null) {
			if (other.cargo != null)
				return false;
		} else if (!cargo.equals(other.cargo))
			return false;
		if (dataEntrada == null) {
			if (other.dataEntrada != null)
				return false;
		} else if (!dataEntrada.equals(other.dataEntrada))
			return false;
		if (dataSaida == null) {
			if (other.dataSaida != null)
				return false;
		} else if (!dataSaida.equals(other.dataSaida))
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (salario == null) {
			if (other.salario != null)
				return false;
		} else if (!salario.equals(other.salario))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "\n Funcionario [nome=" + nome + ", salario=" + salario + ", dataEntrada=" + dataEntrada + ", dataSaida="
				+ dataSaida + ", cargo=" + cargo + ", endereco=" + endereco + "]";
	}

	
	
}
