package trabalho.frontend;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import trabalho.dominio.Usuario;

public class TelaDeListagem extends StackPane {
	TelaPrincipal telaPrincipal = null;
	List<Usuario> pessoas = telaPrincipal.service.listarElementos(Usuario.class);
	TableView<Usuario> tabela = new TableView<>();

	public TelaDeListagem(TelaPrincipal telaPrincipal) {
		this.telaPrincipal = telaPrincipal;

		Button add = new Button("Adicionar");
		TextField nome = new TextField();
		TextField cpf = new TextField();
		TextField email = new TextField();
		
		add.setOnAction(event ->{this.telaPrincipal.service.grava(null, nome);});
		Button del = new Button("Apagar");
		Button mod = new Button("Alterar");

		HBox control = new HBox(add, del, mod);

		control.setSpacing(10);

		BorderPane tabelaControle = new BorderPane();
		tabelaControle.setPadding(new Insets(10));
		tabelaControle.setTop(control);
		tabelaControle.setCenter(tabela);
		Tabela();

		getChildren().add(tabelaControle);

	}

	public void Tabela() {
		TableView<Usuario> tabela = new TableView<>();
		TableColumn<Usuario, String> colunaNome = new TableColumn<>("Nome");
		TableColumn<Usuario, String> colunaICpf = new TableColumn<>("Cpf");
		TableColumn<Usuario, String> colunaEmail = new TableColumn<>("E-mail");

		colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colunaICpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		colunaEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

		tabela.setItems(FXCollections.observableArrayList(pessoas));
		tabela.getColumns().addAll(colunaNome, colunaICpf, colunaEmail);
	}
}