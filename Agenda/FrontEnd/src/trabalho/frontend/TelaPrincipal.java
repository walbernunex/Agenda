package trabalho.frontend;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import trabalho.backend.BancoDeDados;
import trabalho.dominio.PersistenceService;
import trabalho.dominio.Usuario;

public class TelaPrincipal extends Application {
    BorderPane root = null;
    public PersistenceService service = new BancoDeDados();
    private Usuario usuarioLogado;

    public BorderPane getRoot() {
        return root;
    }

    @Override
    public void start(Stage primaryStage) {
        root = new BorderPane();

        Image imagem = new Image("imagens/icone.png");

        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.setTitle("A G E N D A");
        primaryStage.getIcons().add(imagem);
        primaryStage.show();

        root.setCenter(new TelaDeLogin(this));
    }

    public static void main(String[] args) {
        launch(args);
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public void configureLayout() {
        if (usuarioLogado != null) {
            MenuBar menuBar = new MenuBar();

            Menu menuArquivo = new Menu("Arquivo");
            MenuItem itemTela1 = new MenuItem("Página Inicial");
            MenuItem itemTela2 = new MenuItem("Sair");
            menuArquivo.getItems().addAll(itemTela1, itemTela2);
            menuArquivo.setStyle("-fx-background-color:linear-gradient(lightblue,aliceblue);"
                    + "-fx-font-size: 12px;");

            menuBar.getMenus().addAll(menuArquivo);
            root.setTop(menuBar);

            // Eventos de clique nos itens de menu
            itemTela1.setOnAction(event -> {
                //  Ir para tela inicial ao clicar no item "Página Inicial"
                root.setCenter(new TelaDeEntrada(this));
            });

         
            itemTela2.setOnAction(event -> {
                // Ir para tela de login ao clicar em "Sair"
                usuarioLogado = null;
                root.setTop(null);
                root.setCenter(new TelaDeLogin(this));
            });

            // Definir a tela inicial após o login
            root.setCenter(new TelaDeEntrada(this));
        }
    }
}


