package com.java.ventaCoffe.view.controller;

import com.java.ventaCoffe.VentaCoffeApplication;
import com.java.ventaCoffe.controller.impl.DetallePedidoServiceImpl;
import com.java.ventaCoffe.controller.impl.PedidoServiceImpl;
import com.java.ventaCoffe.controller.impl.PedidoTempServiceImpl;
import com.java.ventaCoffe.controller.impl.UsuarioServiceImpl;
import com.java.ventaCoffe.model.entity.*;
import com.java.ventaCoffe.view.controller.cartProducto.EliminarPedidoTempController;
import com.java.ventaCoffe.view.controller.cartProducto.PedidoController;
import com.java.ventaCoffe.view.controller.cartProducto.ViewPedidoTempController;
import com.java.ventaCoffe.view.controller.cartProducto.mostrarCartProductoController;
import com.java.ventaCoffe.view.controller.compraProducto.ComprarPedidoController;
import com.java.ventaCoffe.view.controller.compraProducto.GuardarPedidoController;
import com.java.ventaCoffe.view.controller.compraProducto.MostrarPedidoController;
import com.java.ventaCoffe.view.controller.graficos.VentasDiariasController;
import com.java.ventaCoffe.view.controller.inventario.*;
import com.java.ventaCoffe.view.controller.reportePedidoPdf.DetallePedidoPdf;
import jakarta.persistence.Tuple;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

@Controller
public class ControllerMenu implements Initializable {

    private final Logger logger = LoggerFactory.getLogger(ControllerMenu.class);

    //Pertenecen a agregar productos al 'INVENTARIO'

    @FXML
    private Button BagregarProducto;

    @FXML
    private Button BSalirPrograma;

    @FXML
    private Button BlimpiarProducto;

    @FXML
    private Label LidProducto;

    @FXML
    private Label LnombreProducto;

    @FXML
    private Label LprecioProducto;

    @FXML
    private Label LstockProducto;

    @FXML
    private Label LtipoProducto;

    @FXML
    private TableView<Producto> TableProductoInv;

    @FXML
    private TableColumn<Producto, String> columEstadoProducto;

    @FXML
    private TableColumn<Producto, LocalDateTime> columFechaProducto;

    @FXML
    private TableColumn<Producto, Double> columPrecioProducto;

    @FXML
    private TableColumn<Producto, String> columProducto;

    @FXML
    private TableColumn<Producto, Integer> columProductoID;

    @FXML
    private TableColumn<Producto, Integer> columStockProducto;

    @FXML
    private TableColumn<Producto, String> columTipoProducto;

    @FXML
    private ComboBox<String> comboTipoProducto;

    @FXML
    private ComboBox<String> comboEstadoProducto;

    @FXML
    private Label labelUsuarioLogeado;

    @FXML
    private AnchorPane menuForm;

    @FXML
    private TextField txtIdProducto;

    @FXML
    private TextField txtNombreProducto;

    @FXML
    private TextField txtPrecioProducto;

    @FXML
    private TextField txtStockProducto;

    @FXML
    private ImageView imagenProductoView;

    private Image image;

    private Integer idProductoSeleccionado;

    @Autowired
    private mostrarComboController comboController;

    @Autowired
    private ImagenProductoController imagenController;

    private String nombreUsuario;

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    @Autowired
    private AgregarProductoController agregarProducto;

    @Autowired
    private MostrarProductoController mostrarProductoController;

    @Autowired
    private ActualizarProductoContoller actualizarProductoContoller;

    @Autowired
    private EliminarProductoController eliminarProductoController;

    //Cambiar entre ventanas

    @Autowired
    private mostrarCartProductoController cartProductoController;

    @Autowired
    private PedidoTempServiceImpl pedidoTempService;

    @Autowired
    private EliminarPedidoTempController eliminarPedidoTemp;

    @Autowired
    private DetallePedidoPdf detallePedidoPdf;


    @FXML
    private GridPane menuGrip_pane;

    @FXML
    private AnchorPane fromAddProducto;

    @FXML
    private AnchorPane fromPedidos;

    @FXML
    private AnchorPane fromCartProducto;

    @FXML
    private AnchorPane fromMenuProducto;

    @FXML
    private Button BclientesMenu;

    @FXML
    private Button BinicioMenu;

    @FXML
    private Button BinventarioMenu;

    @FXML
    private Button BCartProducto;

    //Contenido de los pedidos

    @Autowired
    private GuardarPedidoController guardarPedidoController;

    @FXML
    private Label LtotalPedido;

    @FXML
    private TextField TxtcantidadPedido;

    @FXML
    private Label cambioPedido;

    @FXML
    private TableView<PedidoTemporal> tableViewPedido;

    @FXML
    private TableColumn<PedidoTemporal, Double> columnPrecioPedido;

    @FXML
    private TableColumn<PedidoTemporal, String> columnProductoPedido;

    @FXML
    private TableColumn<PedidoTemporal, Integer> columnStockPedido;


    private String nombrePedido;

    private int stockPedido;

    private double precioPedido;

    private Boolean pagoRealizado;

    public Boolean getPagoRealizado() {
        return pagoRealizado;
    }

    public void setPagoRealizado(Boolean pagoRealizado) {
        this.pagoRealizado = pagoRealizado;
    }

    public String getNombrePedido() {
        return nombrePedido;
    }

    public void setNombrePedido(String nombrePedido) {
        this.nombrePedido = nombrePedido;
    }

    public int getStockPedido() {
        return stockPedido;
    }

    public void setStockPedido(int stockPedido) {
        this.stockPedido = stockPedido;
    }

    public double getPrecioPedido() {
        return precioPedido;
    }

    public void setPrecioPedido(double precioPedido) {
        this.precioPedido = precioPedido;
    }

    @Autowired
    private ViewPedidoTempController pedidoTempController;

    @Autowired
    private PedidoController pedidoController;

    @Autowired
    private ComprarPedidoController comprarPedidoController;

    //Tabla de los pedidos

    @FXML
    private TableView<Pedido> pedidosTable;

    @FXML
    private TableColumn<Pedido,Integer> idPedidoColumn;

    @FXML
    private TableColumn<Pedido,Double> TotalPedidoColumn;

    @FXML
    private TableColumn<Pedido,LocalDateTime> FechaPedidoColumn;

    @FXML
    private TableColumn<Pedido, Usuario> usuarioPedidoColumn;

    @Autowired
    private MostrarPedidoController mostrarPedidoController;

    @Autowired
    private VentasDiariasController ventasDiariasController;


    //Mostrar el menu principal

    @FXML
    private Label LTotalUsuarios;

    @FXML
    private Label LVentaHoy;

    @FXML
    private Label LVentaTotal;

    @FXML
    private Label LtotalPorductosVendidos;

    @FXML
    private BarChart<?, ?> dhasboardVentasDiarias;


    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Autowired
    private PedidoServiceImpl pedidoService;

    @Autowired
    private DetallePedidoServiceImpl detallePedidoService;


    public void cambiarFrom(ActionEvent event) {
        if (event.getSource() == BinicioMenu) {
            fromAddProducto.setVisible(false);
            fromCartProducto.setVisible(false);
            fromMenuProducto.setVisible(true);
            fromPedidos.setVisible(false);

        } else if (event.getSource() == BinventarioMenu) {
            fromAddProducto.setVisible(true);
            fromCartProducto.setVisible(false);
            fromMenuProducto.setVisible(false);
            fromPedidos.setVisible(false);

        } else if (event.getSource() == BCartProducto) {

            fromAddProducto.setVisible(false);
            fromCartProducto.setVisible(true);
            fromMenuProducto.setVisible(false);
            fromPedidos.setVisible(false);

        } else if (event.getSource()==BclientesMenu) {
            fromAddProducto.setVisible(false);
            fromCartProducto.setVisible(false);
            fromMenuProducto.setVisible(false);
            fromPedidos.setVisible(true);
        }

    }

    String ruta;

    @FXML
    void salirPrograma(ActionEvent event) {

        try {
            System.out.println("Entrando");

            Stage salir = (Stage) BSalirPrograma.getScene().getWindow();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setTitle("SALIR DEL PROGRAMA");
            alert.setContentText("¿Estas seguro de salir del programa?");
            Optional<ButtonType> option = alert.showAndWait();
            ApplicationContext context = VentaCoffeApplication.context;


            if (option.get().equals(ButtonType.OK)) {

                Stage ventanaLogin = new Stage();
                FXMLLoader ruta = new FXMLLoader();
                ruta.setLocation(getClass().getResource("/com/java/ventaCoffe/login.fxml"));
                ruta.setControllerFactory(context::getBean);
                Parent root = ruta.load();
                Scene scene = new Scene(root);
                ventanaLogin.setScene(scene);
                ventanaLogin.show();
                salir.close();
                pedidoTempService.eliminarRegistroTablaPedido();


            }

        } catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());
        }

    }

    public void agregarImagenProducto() {

        FileChooser openFile = new FileChooser();
        openFile.getExtensionFilters().add(new FileChooser.ExtensionFilter("Open image File", "*png", "*jpg"));
        File file = openFile.showOpenDialog(menuForm.getScene().getWindow());

        if (file != null) {

            ruta = file.getAbsolutePath();
            image = new Image(file.toURI().toString(), 137, 135, false, true);
            imagenProductoView.setImage(image);

        }

    }

    @FXML
    void limpiarCasillas(ActionEvent event) {
        agregarProducto.limpiarCasillas(txtIdProducto, txtNombreProducto, txtStockProducto, txtPrecioProducto,
                imagenProductoView);
        ruta = null;
        idProductoSeleccionado = null;

    }

    @FXML
    void imprimirBoton(ActionEvent event) {
        agregarImagenProducto();
    }

    @FXML
    AnchorPane pruebaPane;

    @FXML
    void guardarProducto(ActionEvent event) {
        System.out.println("Prueba");
        agregarProducto.agregarProducto(txtIdProducto, txtNombreProducto, txtStockProducto, txtPrecioProducto,
                getNombreUsuario(),
                comboTipoProducto, comboEstadoProducto, ruta, imagenProductoView);
        System.out.println("Paso");
        ruta = null;
        mostrarProductoController.MostrarProductos(TableProductoInv, columProductoID,
                columProducto, columTipoProducto, columStockProducto, columPrecioProducto, columEstadoProducto,
                columFechaProducto);
        idProductoSeleccionado = null;
        cartProductoController.menuDisplayCard(menuGrip_pane, pruebaPane);

    }


    public void SeleccionarTableView() {
        idProductoSeleccionado = actualizarProductoContoller.ObtenerTable(TableProductoInv, txtIdProducto,
                txtNombreProducto, txtStockProducto, txtPrecioProducto, imagenProductoView);
        logger.info("Prueba Id Producto: {}", idProductoSeleccionado);

    }

    @FXML
    void actualizarProducto(ActionEvent event) {
        actualizarProductoContoller.ActualizarProducto(txtIdProducto,
                idProductoSeleccionado,
                txtNombreProducto,
                txtStockProducto,
                txtPrecioProducto,
                comboEstadoProducto,
                comboTipoProducto,
                ruta,
                imagenProductoView);

        mostrarProductoController.MostrarProductos(TableProductoInv, columProductoID,
                columProducto, columTipoProducto, columStockProducto, columPrecioProducto, columEstadoProducto,
                columFechaProducto);
        idProductoSeleccionado = null;
        cartProductoController.menuDisplayCard(menuGrip_pane, pruebaPane);

    }

    @FXML
    void TeliminarProducto(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("SALIR DEL PROGRAMA");
        alert.setContentText("¿Deseas eliminar el producto?");
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get().equals(ButtonType.OK)) {

            eliminarProductoController.eliminarProducto(txtIdProducto);
            mostrarProductoController.MostrarProductos(TableProductoInv, columProductoID,
                    columProducto, columTipoProducto, columStockProducto, columPrecioProducto, columEstadoProducto,
                    columFechaProducto);
            agregarProducto.limpiarCasillas(txtIdProducto, txtNombreProducto, txtStockProducto, txtPrecioProducto,
                    imagenProductoView);
            idProductoSeleccionado = null;
            cartProductoController.menuDisplayCard(menuGrip_pane, pruebaPane);


        } else {
            mostrarProductoController.MostrarProductos(TableProductoInv, columProductoID,
                    columProducto, columTipoProducto, columStockProducto, columPrecioProducto, columEstadoProducto,
                    columFechaProducto);
            cartProductoController.menuDisplayCard(menuGrip_pane, pruebaPane);
            agregarProducto.limpiarCasillas(txtIdProducto, txtNombreProducto, txtStockProducto, txtPrecioProducto,
                    imagenProductoView);
            idProductoSeleccionado = null;
        }
    }

    public void actualizarTablaPedidoTemp(Producto producto) {
        pedidoTempController.mostrarTablePedidoTemp(tableViewPedido,
                columnStockPedido,
                columnPrecioPedido,
                columnProductoPedido);
        TotalVenta();

    }

    //Pedidos

    @FXML
    void eliminarPedido(ActionEvent event) {

        System.out.println("Eliminar pedido");
        Integer idPedido = pedidoController.ObtenerIdPedidoTemp(tableViewPedido);
        Integer idEliminar = idPedido;
        eliminarPedidoTemp.eliminarPedidoTemporal(idEliminar);
        pedidoTempController.mostrarTablePedidoTemp(tableViewPedido,
                columnStockPedido,
                columnPrecioPedido,
                columnProductoPedido);
        TotalVenta();


    }

    public Double TotalVenta() {
        Double totalPedido = pedidoTempService.sumarTotalPedido();
        Double total = totalPedido;
        if (!(total == null)) {
            LtotalPedido.setText("$" + total);
            return total;
        } else {
            LtotalPedido.setText("$" + 0.0);
            return 0.0;
        }

    }

    //Aqui debes chambear abel
    @FXML
    void totalPedido(ActionEvent event) {
        Platform.runLater(() -> {
            logger.info("Total venta: {}", pedidoTempService.sumarTotalPedido());
            guardarPedidoController.guardarPedido(TotalVenta(), getNombreUsuario(), TxtcantidadPedido,LtotalPedido,cambioPedido);
            pedidoTempController.mostrarTablePedidoTemp(tableViewPedido,
                    columnStockPedido,
                    columnPrecioPedido,
                    columnProductoPedido);
            logger.info("Test mombre: {}", getNombreUsuario());
            mostrarPedidoController.mostrarTablaPedido(pedidosTable,idPedidoColumn,
                    TotalPedidoColumn,FechaPedidoColumn,usuarioPedidoColumn);
            ventaHoy();
            ventaTotal();
            productosTotalVendidos();
        });

    }

    @FXML
    void montoPagar(ActionEvent event) {
        comprarPedidoController.pagar(TxtcantidadPedido, TotalVenta(), cambioPedido);
        mostrarPedidoController.mostrarTablaPedido(pedidosTable,idPedidoColumn,
                TotalPedidoColumn,FechaPedidoColumn,usuarioPedidoColumn);
    }

    @FXML
    void imprimirBoleta(ActionEvent event){
        try {

            detallePedidoPdf.crearDocumento();
            detallePedidoPdf.abrirDocumento();
            detallePedidoPdf.agregarTitulo("Reporte de Detalle De Pedidos");
            detallePedidoPdf.agregarSaltoDeLinea();
            detallePedidoPdf.agregarParrafo("Reporte de pedidos actual 2024");
            detallePedidoPdf.agregarSaltoDeLinea();
            detallePedidoPdf.agregarTableDetallePedido();
            detallePedidoPdf.cerrarDocumento();
            logger.info("Cerrando el docmuento");
        }catch (Exception exception) {
            System.out.println("Error: "+exception.getMessage());
        }
    }

    public void totalUsuarios(){
        try {
            Integer testTotalUsuario = usuarioService.totalDeUsuarios();
            logger.info("Total usuarios: {}",testTotalUsuario);
            if(testTotalUsuario==null){
                logger.info("Error de null");
                return;
            }
            LTotalUsuarios.setText(String.valueOf(testTotalUsuario));

        }catch (Exception exception){
            logger.info("Error count");
            LTotalUsuarios.setText("0");
            System.out.println("Error: "+exception.getMessage());
        }

    }

    //  Esta clase mostrara el total de las ventas del dia
    public void ventaHoy(){

        try {
            Double totalVentaHoy= pedidoService.ventaDelDia();
            if(totalVentaHoy ==null){
                LVentaHoy.setText("$0.0");
                logger.info("No hay ventas hoy");
                return;
            }if (totalVentaHoy==0){
                LVentaHoy.setText("$0.0");
                return;
            }
            LVentaHoy.setText("$"+String.valueOf(totalVentaHoy));

        }catch (Exception exception){
            LVentaHoy.setText("$0.0");
            System.out.println("Error: "+exception.getMessage());
        }

    }

    public void ventaTotal(){

        try {
            Double totalVentaHoy= pedidoService.totalVentasPedidos();
            if(totalVentaHoy ==null){
                LVentaTotal.setText("$0.0");
                logger.info("No hay ventas hoy");
                return;
            }if (totalVentaHoy==0){
                LVentaTotal.setText("$0.0");
                return;
            }
            LVentaTotal.setText("$"+String.valueOf(totalVentaHoy));

        }catch (Exception exception){
            LVentaTotal.setText("$0.0");
            System.out.println("Error: "+exception.getMessage());
        }

    }

    public void productosTotalVendidos(){

        try {
            Integer totalProductos= detallePedidoService.cantidadVendidaProductos();
            if(totalProductos ==null){
                LtotalPorductosVendidos.setText("0");
                logger.info("No hay ventas hoy");
                return;
            }if (totalProductos==0){
                LtotalPorductosVendidos.setText("0");
                return;
            }
            LtotalPorductosVendidos.setText(String.valueOf(totalProductos));

        }catch (Exception exception){
            LtotalPorductosVendidos.setText("0");
            System.out.println("Error: "+exception.getMessage());
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {

            labelUsuarioLogeado.setText(getNombreUsuario());
            totalUsuarios();
            ventaHoy();
            ventaTotal();
            productosTotalVendidos();


        });
        agregarProducto.fijarlogitudMaximo(txtStockProducto, 4);

        agregarProducto.fijarlogitudMaximo(TxtcantidadPedido, 6);

        agregarProducto.fijarlogitudMaximo(txtPrecioProducto, 6);

        comboController.recorrerEstadoProducto(comboEstadoProducto);

        comboController.recorrerTipoProducto(comboTipoProducto);

        mostrarProductoController.MostrarProductos(TableProductoInv, columProductoID,
                columProducto, columTipoProducto,
                columStockProducto, columPrecioProducto,
                columEstadoProducto,
                columFechaProducto);

        cartProductoController.menuDisplayCard(menuGrip_pane, pruebaPane);

        pedidoTempController.mostrarTablePedidoTemp(tableViewPedido,
                columnStockPedido,
                columnPrecioPedido,
                columnProductoPedido);

        mostrarPedidoController.mostrarTablaPedido(pedidosTable,idPedidoColumn,
                TotalPedidoColumn,FechaPedidoColumn,usuarioPedidoColumn);

        TotalVenta();

        ventasDiariasController.mostrarGraficaVentaDiaria(dhasboardVentasDiarias);



    }

}