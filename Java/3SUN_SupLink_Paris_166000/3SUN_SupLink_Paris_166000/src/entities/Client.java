package entities;

import java.io.Serializable;
import javax.persistence.*;

import dao.ClientDao;

import java.util.List;


/**
 * The persistent class for the "Client" database table.
 * 
 */
@Entity
@Table(name="\"Client\"")
public class Client implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String email;

	@Column(name="\"motDePasse\"")
	private String motDePasse;

	//bi-directional many-to-one association to ShortUml
	@OneToMany(mappedBy="client")
	private List<ShortUml> shortUmls;

	public Client() {
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMotDePasse() {
		return this.motDePasse;
	}

	public void setMotDePasse(String motDePasse) throws Exception {
		this.motDePasse = ClientDao.encrypt(motDePasse);
	}

	public List<ShortUml> getShortUmls() {
		return this.shortUmls;
	}

	public void setShortUmls(List<ShortUml> shortUmls) {
		this.shortUmls = shortUmls;
	}

}