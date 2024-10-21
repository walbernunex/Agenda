package trabalho.frontend;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import trabalho.backend.BancoDeDados;
import trabalho.dominio.Usuario;

public class TelaDeLogin extends StackPane {
    private TelaPrincipal telaPrincipal;
    private Label mensagemErro;

    public TelaDeLogin(TelaPrincipal principal) {
        this.telaPrincipal = principal;
        mensagemErro = new Label(""); // Label para a mensagem de erro
        // Criando elementos da tela de login
        Label labelUsuario = new Label("Usuário (CPF):");
        TextField cpf = new TextField();
        Label labelSenha = new Label("Senha:");
        PasswordField senha = new PasswordField(); // Campo de senha
        Button btnLogin = new Button("Login");
        btnLogin.setOnAction(e -> entrar(cpf, senha));

        // Criando layout da tela de login
        GridPane tabelaPainel = new GridPane();
        tabelaPainel.setPadding(new Insets(50, 50, 50, 50));
        tabelaPainel.setVgap(5);
        tabelaPainel.setHgap(5);
        
        

        // Adicionando elementos ao layout
        tabelaPainel.add(mensagemErro, 0, 0, 2, 1); // Adicionando a mensagem de erro na primeira linha
        tabelaPainel.add(labelUsuario, 0, 1);
        tabelaPainel.add(cpf, 1, 1);
        tabelaPainel.add(labelSenha, 0, 2);
        tabelaPainel.add(senha, 1, 2); // Adicionando campo de senha
        tabelaPainel.add(btnLogin, 1, 3);

        getChildren().add(tabelaPainel);
        setPadding(new Insets(10));
        setStyle("-fx-background-color: lightblue;");
    }

    private void entrar(TextField textFieldCpf, PasswordField passwordFieldsenha) {
        String cpf = textFieldCpf.getText();
        String senha = passwordFieldsenha.getText();
        Usuario usuario = telaPrincipal.service.recupera(cpf);
        if (usuario != null) {
            if (usuario.getSenha().equals(senha)) {
                telaPrincipal.setUsuarioLogado(usuario);
                telaPrincipal.configureLayout(); // Atualizar o layout após login bem-sucedido
            } else {
                mensagemErro.setText("Senha incorreta!");
            }
        } else {
            mensagemErro.setText("Usuário não encontrado!");
        }
    }
}
