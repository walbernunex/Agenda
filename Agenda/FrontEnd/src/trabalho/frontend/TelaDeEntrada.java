package trabalho.frontend;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class TelaDeEntrada extends StackPane {
    private TelaPrincipal telaPrincipal;

    public TelaDeEntrada(TelaPrincipal principal) {
        this.telaPrincipal = principal;

        Label labelNome = new Label("Bem vindo(a), ");
        Label nome = new Label(telaPrincipal.getUsuarioLogado().getNome() + "!");
        Button contatos = new Button("Contatos");
        
        contatos.setOnAction(event -> {
            telaPrincipal.getRoot().setCenter(new Contatos(telaPrincipal));
        });
        
        Button compromissos = new Button("Compromissos");
        compromissos.setOnAction(event -> {
            telaPrincipal.getRoot().setCenter(new Compromissos(telaPrincipal));
        });
        setPadding(new Insets(10));
        setStyle("-fx-background-color: lightblue;");
       

        VBox tabelaPainel = new VBox(labelNome, nome);
        tabelaPainel.setAlignment(Pos.CENTER);

        HBox menus = new HBox(contatos, compromissos);
        menus.setSpacing(10);

        BorderPane controle = new BorderPane();
        controle.setPadding(new Insets(10));
        controle.setTop(tabelaPainel);
        controle.setLeft(menus);

        getChildren().add(controle);
    }
}
