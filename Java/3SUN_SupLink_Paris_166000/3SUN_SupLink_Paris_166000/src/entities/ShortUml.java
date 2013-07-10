package entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "ShortUml" database table.
 * 
 */
@Entity
@Table(name="\"ShortUml\"")
public class ShortUml implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"shortUml\"")
	private String shortUml;

	@Column(name="\"initialUml\"")
	private String initialUml;

	@Column(name="\"isEnable\"")
	private Boolean isEnable;

	@Column(name="\"Nom\"")
	private String nom;

	@Column(name="\"numberOfUse\"")
	private Integer numberOfUse;

	//bi-directional many-to-one association to Client
	@ManyToOne
	@JoinColumn(name="proprietaire")
	private Client client;

	public ShortUml() {
	}

	public String getShortUml() {
		return this.shortUml;
	}

	public void setShortUml(String shortUml) {
		this.shortUml = shortUml;
	}

	public String getInitialUml() {
		return this.initialUml;
	}

	public void setInitialUml(String initialUml) {
		this.initialUml = initialUml;
	}

	public Boolean getIsEnable() {
		return this.isEnable;
	}

	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Integer getNumberOfUse() {
		return this.numberOfUse;
	}

	public void setNumberOfUse(Integer numberOfUse) {
		this.numberOfUse = numberOfUse;
	}

	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

}