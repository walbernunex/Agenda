package trabalho.frontend;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import trabalho.dominio.Contato;

public class Contatos extends StackPane {
    private ObservableList<Contato> contatos;
    private TableView<Contato> tabelaContatos;

    public Contatos(TelaPrincipal principal) {
        contatos = FXCollections.observableArrayList();
        tabelaContatos = new TableView<>(contatos);
        configurarTabela();

        Button btnAdicionar = new Button("Adicionar Contato");
        btnAdicionar.setOnAction(event -> abrirFormularioContato(null));

        Button btnEditar = new Button("Editar Contato");
        btnEditar.setOnAction(event -> {
            Contato contatoSelecionado = tabelaContatos.getSelectionModel().getSelectedItem();
            if (contatoSelecionado != null) {
                abrirFormularioContato(contatoSelecionado);
            }
        });

        Button btnExcluir = new Button("Excluir Contato");
        btnExcluir.setOnAction(event -> {
            Contato contatoSelecionado = tabelaContatos.getSelectionModel().getSelectedItem();
            if (contatoSelecionado != null) {
                contatos.remove(contatoSelecionado);
            }
        });

        HBox botoes = new HBox(10, btnAdicionar, btnEditar, btnExcluir);
        botoes.setAlignment(Pos.CENTER);
        VBox layout = new VBox(10, tabelaContatos, botoes);
        layout.setPadding(new Insets(10));
        layout.setAlignment(Pos.CENTER);

        setPadding(new Insets(10));
        setStyle("-fx-background-color: lightblue;");
        getChildren().add(layout);
    }

    private void configurarTabela() {
        TableColumn<Contato, String> colunaNome = new TableColumn<>("Nome");
        colunaNome.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNome()));

        TableColumn<Contato, String> colunaTelefone = new TableColumn<>("Telefone");
        colunaTelefone.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTelefone()));

        TableColumn<Contato, String> colunaEmail = new TableColumn<>("Email");
        colunaEmail.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmail()));

        boolean all = tabelaContatos.getColumns().addAll(colunaNome, colunaTelefone, colunaEmail);
    }

    private void abrirFormularioContato(Contato contato) {
        Stage stage = new Stage();
        stage.setTitle(contato == null ? "Adicionar Contato" : "Editar Contato");

        Label labelNome = new Label("Nome:");
        TextField campoNome = new TextField(contato != null ? contato.getNome() : "");

        Label labelTelefone = new Label("Telefone:");
        TextField campoTelefone = new TextField(contato != null ? contato.getTelefone() : "");

        Label labelEmail = new Label("Email:");
        TextField campoEmail = new TextField(contato != null ? contato.getEmail() : "");

        Button btnSalvar = new Button("Salvar");
        btnSalvar.setOnAction(event -> {
            if (contato == null) {
                contatos.add(new Contato(campoNome.getText(), campoTelefone.getText(), campoEmail.getText()));
            } else {
                contato.setNome(campoNome.getText());
                contato.setTelefone(campoTelefone.getText());
                contato.setEmail(campoEmail.getText());
                tabelaContatos.refresh();
            }
            stage.close();
        });

        Button btnCancelar = new Button("Cancelar");
        btnCancelar.setOnAction(event -> stage.close());

        HBox botoes = new HBox(10, btnSalvar, btnCancelar);
        botoes.setAlignment(Pos.CENTER);

        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10));
        layout.setVgap(5);
        layout.setHgap(5);
        layout.add(labelNome, 0, 0);
        layout.add(campoNome, 1, 0);
        layout.add(labelTelefone, 0, 1);
        layout.add(campoTelefone, 1, 1);
        layout.add(labelEmail, 0, 2);
        layout.add(campoEmail, 1, 2);
        layout.add(botoes, 0, 3, 2, 1);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 300, 200);
        stage.setScene(scene);
        stage.show();
    }
}
