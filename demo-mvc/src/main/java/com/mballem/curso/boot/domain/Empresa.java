package com.mballem.curso.boot.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.mballem.curso.boot.web.annotation.CpfCnpj;

@Entity
@Table( indexes = { @Index( name="idxCnpj", columnList="cnpj") } )
@NamedQueries( {	
					@NamedQuery( name = "Empresa.findByCnpj", query = "Select e From Empresa e Where e.cnpj = :cnpj" ),
					@NamedQuery( name = "Empresa.findByName", query = "Select e From Empresa e Where e.razaoSocial like :razaoSocial")})
public class Empresa extends AbstractEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5742116273857999689L;

	@Column( 	length=14,
				unique = true,
				nullable=false)
	@NotNull( message="CNPJ Inválido")
	@CpfCnpj
	@Size(min=14, max=18, message="Tamanho inválido")
	private String cnpj;
	
	@NotNull( message="Razão Social Inválido" )
	@NotBlank(message="Razão Social Inválido")
	@Size(min=5, max=50, message="Razão Social Deverá ter Tamanho Mínimo de {min} a {max}")
	@Column( length= 50, nullable = false)
	private String razaoSocial;


	@Valid
	@OneToOne( cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "endereco_id", foreignKey = @ForeignKey(name = "fkEmpEndId_EndId"))
	private Endereco endereco;

	@NotNull(message="Situação Inválida")
	@Column(name="situacao", nullable=false, length=1)
	@Enumerated(EnumType.STRING)
	private SITUACAO situacao;

	@DateTimeFormat( iso = ISO.DATE)
	@Past( message = "Data Inválida, Deve ser Anterior a Data Atual")
	@NotNull( message="Data Inválida")
	@Column( nullable=false, columnDefinition="DATE")
	private LocalDate dataAbertura;

	@OneToMany(mappedBy = "empresa")
	private List<Telefone> telefones;

	@ManyToOne
	@JoinColumn( name="area_atividade",
					foreignKey=@ForeignKey(name = "fkEmpresa_AreaAtiv"))
	private AreaAtividade areaAtividade;

	@Positive( message="Valor deve ser positivo")
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	@Column( nullable = false, precision=7, scale=2) //  columnDefinition = "DECIMAL(7,2) DEFAULT 0.00")
	@Digits( integer=7, fraction=2)
	private BigDecimal faturamentoMensal;
	
	public Empresa() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Empresa(@NotNull(message = "CNPJ Inválido") String cnpj,
			@NotNull(message = "Razão Social Inválido") @NotBlank(message = "Razão Social Inválido") @Size(min = 5, max = 50, message = "Razão Social Deverá ter Tamanho Mínimo de {min} a {max}") String razaoSocial,
			Endereco endereco, 
			@NotNull(message = "Situação Inválida") SITUACAO situacao,
			@Past(message = "Data Inválido, Deve ser Anterior a Data Atual") @NotNull(message = "Data Inválida") LocalDate dataAbertura,
			AreaAtividade areaAtividade) {
		super();
		this.cnpj = cnpj;
		this.razaoSocial = razaoSocial;
		this.endereco = endereco;
		this.situacao = situacao;
		this.dataAbertura = dataAbertura;
		this.areaAtividade = areaAtividade;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
		result = prime * result + ((dataAbertura == null) ? 0 : dataAbertura.hashCode());
		result = prime * result + ((razaoSocial == null) ? 0 : razaoSocial.hashCode());
		result = prime * result + ((situacao == null) ? 0 : situacao.hashCode());
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
		Empresa other = (Empresa) obj;
		if (cnpj == null) {
			if (other.cnpj != null)
				return false;
		} else if (!cnpj.equals(other.cnpj))
			return false;
		if (dataAbertura == null) {
			if (other.dataAbertura != null)
				return false;
		} else if (!dataAbertura.equals(other.dataAbertura))
			return false;
		if (razaoSocial == null) {
			if (other.razaoSocial != null)
				return false;
		} else if (!razaoSocial.equals(other.razaoSocial))
			return false;
//		if (situacao != other.situacao)
//			return false;
		return true;
	}


	@Override
	public String toString() {
		return "\n Empresa [cpfCnpj=" + cnpj + ", razaoSocial=" + razaoSocial + ", situacao=" + situacao
				+ ", dataAbertura=" + dataAbertura + areaAtividade + endereco + "]";
	}


	public String getCnpj() {
		return cnpj;
	}


	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}


	public String getRazaoSocial() {
		return razaoSocial;
	}


	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public Endereco getEndereco() {
		return endereco;
	}


	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public SITUACAO getSituacao() {
		return situacao;
	}


	public void setSituacao(SITUACAO situacao) {
		this.situacao = situacao;
	}


	public LocalDate getDataAbertura() {
		return dataAbertura;
	}


	public void setDataAbertura(LocalDate dataAbertura) {
		this.dataAbertura = dataAbertura;
	}


	public List<Telefone> getTelefones() {
		return telefones;
	}


	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	public AreaAtividade getAreaAtividade() {
		return areaAtividade;
	}


	public void setAreaAtividade(AreaAtividade areaAtividade) {
		this.areaAtividade = areaAtividade;
	}


	public BigDecimal getFaturamentoMensal() {
		return faturamentoMensal;
	}


	public void setFaturamentoMensal(BigDecimal faturamentoMensal) {
		this.faturamentoMensal = faturamentoMensal;
	}
	
}
