package trabalho.dominio;


public class Usuario {
	private String nome;
	private String senha;
	private String cpf;
	private String email;
	public Usuario() {
	}
	public Usuario(String nome, String cpf, String senha, String email) {
		this.nome=nome;
		this.cpf=cpf;
		this.senha=senha;
		this.email=email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}



	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Usuario))
			return false;
		Usuario outroCliente = (Usuario)obj;
		if(this.getCpf().equals
				(outroCliente.getCpf()))
			return true;
		else
			return false;	
	}
	
	@Override
	public int hashCode() {
		int hashCode = this.getCpf().hashCode();
		return hashCode;
	}
	
	@Override
	public String toString() {
		return this.getNome();
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
