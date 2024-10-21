package trabalho.frontend;

import javafx.beans.property.SimpleObjectProperty;
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
import trabalho.dominio.Compromisso;

import java.time.LocalDate;
import java.time.LocalTime;

public class Compromissos extends StackPane {
    private ObservableList<Compromisso> compromissos;
    private TableView<Compromisso> tabelaCompromissos;

    public Compromissos(TelaPrincipal principal) {
        compromissos = FXCollections.observableArrayList();
        tabelaCompromissos = new TableView<>(compromissos);
        configurarTabela();

        Button btnAdicionar = new Button("Adicionar Compromisso");
        btnAdicionar.setOnAction(event -> abrirFormularioCompromisso(null));

        Button btnEditar = new Button("Editar Compromisso");
        btnEditar.setOnAction(event -> {
            Compromisso compromissoSelecionado = tabelaCompromissos.getSelectionModel().getSelectedItem();
            if (compromissoSelecionado != null) {
                abrirFormularioCompromisso(compromissoSelecionado);
            }
        });

        Button btnExcluir = new Button("Excluir Compromisso");
        btnExcluir.setOnAction(event -> {
            Compromisso compromissoSelecionado = tabelaCompromissos.getSelectionModel().getSelectedItem();
            if (compromissoSelecionado != null) {
                compromissos.remove(compromissoSelecionado);
            }
        });

        HBox botoes = new HBox(10, btnAdicionar, btnEditar, btnExcluir);
        botoes.setAlignment(Pos.CENTER);
        VBox layout = new VBox(10, tabelaCompromissos, botoes);
        layout.setPadding(new Insets(10));
        layout.setAlignment(Pos.CENTER);

        setPadding(new Insets(10));
        setStyle("-fx-background-color: lightblue;");
        getChildren().add(layout);
    }

    private void configurarTabela() {
        TableColumn<Compromisso, String> colunaDescricao = new TableColumn<>("Descrição");
        colunaDescricao.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDescricao()));

        TableColumn<Compromisso, LocalDate> colunaData = new TableColumn<>("Data");
        colunaData.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getData()));

        TableColumn<Compromisso, LocalTime> colunaHoraInicio = new TableColumn<>("Hora Início");
        colunaHoraInicio.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getHoraInicio()));

        TableColumn<Compromisso, LocalTime> colunaHoraFim = new TableColumn<>("Hora Fim");
        colunaHoraFim.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getHoraFim()));

        tabelaCompromissos.getColumns().addAll(colunaDescricao, colunaData, colunaHoraInicio, colunaHoraFim);
    }

    private void abrirFormularioCompromisso(Compromisso compromisso) {
        Stage stage = new Stage();
        stage.setTitle(compromisso == null ? "Adicionar Compromisso" : "Editar Compromisso");

        Label labelDescricao = new Label("Descrição:");
        TextField campoDescricao = new TextField(compromisso != null ? compromisso.getDescricao() : "");

        Label labelData = new Label("Data:");
        DatePicker campoData = new DatePicker(compromisso != null ? compromisso.getData() : LocalDate.now());

        Label labelHoraInicio = new Label("Hora Início:");
        TextField campoHoraInicio = new TextField(compromisso != null ? compromisso.getHoraInicio().toString() : "");

        Label labelHoraFim = new Label("Hora Fim:");
        TextField campoHoraFim = new TextField(compromisso != null ? compromisso.getHoraFim().toString() : "");

        Button btnSalvar = new Button("Salvar");
        btnSalvar.setOnAction(event -> {
            String descricao = campoDescricao.getText();
            LocalDate data = campoData.getValue();
            LocalTime horaInicio = LocalTime.parse(campoHoraInicio.getText());
            LocalTime horaFim = LocalTime.parse(campoHoraFim.getText());

            if (compromisso == null) {
                compromissos.add(new Compromisso(descricao, data, horaInicio, horaFim));
            } else {
                compromisso.setDescricao(descricao);
                compromisso.setData(data);
                compromisso.setHoraInicio(horaInicio);
                compromisso.setHoraFim(horaFim);
                tabelaCompromissos.refresh();
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
        layout.add(labelDescricao, 0, 0);
        layout.add(campoDescricao, 1, 0);
        layout.add(labelData, 0, 1);
        layout.add(campoData, 1, 1);
        layout.add(labelHoraInicio, 0, 2);
        layout.add(campoHoraInicio, 1, 2);
        layout.add(labelHoraFim, 0, 3);
        layout.add(campoHoraFim, 1, 3);
        layout.add(botoes, 0, 4, 2, 1);

        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.show();
    }
}
