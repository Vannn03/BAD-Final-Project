package main;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Random;
import java.util.Vector;
import java.util.regex.Pattern;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableSelectionModel;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import jfxtras.labs.scene.control.window.CloseIcon;
import jfxtras.labs.scene.control.window.Window;

public class Main extends Application implements EventHandler<ActionEvent> {
	
	Stage stage = new Stage();
	private Connect connect = Connect.getInstance();
	private PreparedStatement ps;
	Vector<Product> productList = new Vector<>();
	Vector<Cart> cartList = new Vector<>();
	Vector<TransactionHeader> headerList = new Vector<>();
	Vector<TransactionDetail> detailList = new Vector<>();
	ScrollPane sp = new ScrollPane();
	Random rand = new Random();
	String tempId;
	
	// =======================================================================================================================================================
	// Login Form
	
	BorderPane bpLogin;
	GridPane gpLogin;
	Scene scLogin;
	Label titleLabel, userLabel, passLabel;
	TextField userField;
	PasswordField passField;
	Button loginButton;
	Hyperlink regisLink, loginLink;
	
	public static void main(String[] args) {
		launch(args);
	}

	void initLogin() {
		bpLogin = new BorderPane();
		gpLogin = new GridPane();
		scLogin = new Scene(bpLogin, 550, 325);
		
		titleLabel = new Label("Welcome to T-Shirt Store");
		userLabel = new Label("Username");
		passLabel = new Label("Password");
		userField = new TextField();
		passField = new PasswordField();
		loginButton = new Button("Login");
		loginButton.setOnAction(this);
		regisLink = new Hyperlink("Register Here");
		loginLink = new Hyperlink("Login Here");
		regisLink.setOnAction(this);
		loginLink.setOnAction(this);
	}
	
	void setPositionLogin() {
		bpLogin.setTop(titleLabel);
		BorderPane.setMargin(titleLabel, new Insets(30));
		BorderPane.setAlignment(titleLabel, Pos.CENTER);
		
		gpLogin.add(userLabel, 0, 0);
		gpLogin.add(userField, 1, 0);
		gpLogin.add(passLabel, 0, 1);
		gpLogin.add(passField, 1, 1);
		gpLogin.add(loginButton, 1, 2);
		loginButton.setTranslateX(12);
		loginButton.setTranslateY(10);		
		gpLogin.setAlignment(Pos.CENTER);
		gpLogin.setVgap(20);
		gpLogin.setHgap(20);
		bpLogin.setCenter(gpLogin);
		BorderPane.setAlignment(gpLogin, Pos.CENTER);
		
		bpLogin.setBottom(regisLink);
		BorderPane.setMargin(regisLink, new Insets(25));
		BorderPane.setAlignment(regisLink, Pos.CENTER);
	}
	
	void setSizeLogin() {
		titleLabel.setFont(new Font("Verdana", 30));
		userLabel.setFont(new Font("Poppins", 14));
		passLabel.setFont(new Font("Poppins", 14));
		loginButton.setFont(Font.font("Poppins", FontWeight.BOLD, 13));
		regisLink.setFont(Font.font("Courier New", FontWeight.BOLD, 14));
		
		userField.setPrefSize(200, 30);
		passField.setPrefSize(200, 30);
		loginButton.setPrefSize(90, 35);
	}
	
	void setColourLogin() {
		bpLogin.setStyle("-fx-background-color: #FFFFFF");
		loginButton.setStyle("-fx-background-color: #FA4917");
		titleLabel.setStyle("-fx-text-fill: #2A293E");
		userLabel.setStyle("-fx-text-fill: #2A293E");
		passLabel.setStyle("-fx-text-fill: #2A293E");
		loginButton.setTextFill(Color.WHITE);
		regisLink.setTextFill(Color.BLUEVIOLET);
	}
	
	// =======================================================================================================================================================
	// Register Form
	
	BorderPane bpRegis;
	GridPane gpRegis;
	FlowPane genderFlow2;
	Scene scRegis;
	Label titleLabel2, idLabel2, userLabel2, passLabel2, confPassLabel2, genderLabel2, addressLabel2, nationLabel2;
	TextField idField2, userField2;
	PasswordField passField2, confPassField2;
	ToggleGroup genderToggle2;
	RadioButton maleRadio2, femaleRadio2;
	TextArea addressTA2;
	ComboBox<String> nationBox2;
	CheckBox checkBox2;
	Button regisButton;
	
	void initRegis() {
		bpRegis = new BorderPane();
		gpRegis = new GridPane();
		genderFlow2 = new FlowPane();
		scRegis = new Scene(bpRegis, 600, 575);
		
		titleLabel2 = new Label("REGISTER");
		idLabel2 = new Label("ID");
		userLabel2 = new Label("Username");
		passLabel2 = new Label("Password");
		confPassLabel2 = new Label("Confirmation Password");
		genderLabel2 = new Label("Gender");
		addressLabel2 = new Label("Address");
		nationLabel2 = new Label("Nationality");
		
		Integer randNum1 = rand.nextInt(10);
		Integer randNum2 = rand.nextInt(10);
		Integer randNum3 = rand.nextInt(10);
		Character randChar1 = (char) ('A' + rand.nextInt(26));
		Character randChar2 = (char) ('A' + rand.nextInt(26));
		String ID = "US-" + randNum1 + "" + randNum2 + "" + randNum3 + "" + randChar1 + "" + randChar2;
		
		idField2 = new TextField(ID);
		idField2.setEditable(false);
		idField2.setMouseTransparent(true);
		idField2.setFocusTraversable(false);
		
		userField2 = new TextField();
		passField2 = new PasswordField();
		confPassField2 = new PasswordField();
		genderToggle2 = new ToggleGroup();
		maleRadio2 = new RadioButton("Male");
		femaleRadio2 = new RadioButton("Female");
		
		addressTA2 = new TextArea();
		addressTA2.setWrapText(true);
		
		nationBox2 = new ComboBox<>();
		nationBox2.getItems().add("America");
		nationBox2.getItems().add("Australia");
		nationBox2.getItems().add("Brazil");
		nationBox2.getItems().add("Indonesia");
		nationBox2.getItems().add("Malaysia");
		nationBox2.getItems().add("Singapore");
		nationBox2.setValue("~~Choose one~~");
		
		checkBox2 = new CheckBox("I Agree With Terms and Conditions");
		regisButton = new Button("Register");
		regisButton.setOnAction(this);
		loginLink = new Hyperlink("Login Here");
		regisLink = new Hyperlink("Register Here");
		loginLink.setOnAction(this);
		regisLink.setOnAction(this);
	}
	
	void setPositionRegis() {
		bpRegis.setTop(titleLabel2);
		BorderPane.setMargin(titleLabel2, new Insets(30));
		BorderPane.setAlignment(titleLabel2, Pos.CENTER);
		
		gpRegis.add(idLabel2, 0, 0);
		gpRegis.add(idField2, 1, 0);
		gpRegis.add(userLabel2, 0, 1);
		gpRegis.add(userField2, 1, 1);
		gpRegis.add(passLabel2, 0, 2);
		gpRegis.add(passField2, 1, 2);
		gpRegis.add(confPassLabel2, 0, 3);
		gpRegis.add(confPassField2, 1, 3);
		
		genderFlow2.getChildren().add(maleRadio2);
		genderFlow2.getChildren().add(femaleRadio2);
		maleRadio2.setToggleGroup(genderToggle2);
		femaleRadio2.setToggleGroup(genderToggle2);
		
		gpRegis.add(genderLabel2, 0, 4);
		gpRegis.add(genderFlow2, 1, 4);
		genderFlow2.setHgap(50);
		gpRegis.add(addressLabel2, 0, 5);
		gpRegis.add(addressTA2, 1, 5);
		gpRegis.add(nationLabel2, 0, 6);
		gpRegis.add(nationBox2, 1, 6);
		gpRegis.add(checkBox2, 1, 7);
		gpRegis.add(regisButton, 1, 8);
		regisButton.setTranslateY(20);
		gpRegis.setVgap(15);
		gpRegis.setHgap(20);
		gpRegis.setTranslateX(70);
		gpRegis.setTranslateY(-5);
		
		bpRegis.setCenter(gpRegis);
		gpRegis.setAlignment(Pos.CENTER);
		BorderPane.setAlignment(bpRegis, Pos.CENTER);
		
		bpRegis.setBottom(loginLink);
		BorderPane.setMargin(loginLink, new Insets(30));
		BorderPane.setAlignment(loginLink, Pos.CENTER);
	}
	
	void setSizeRegis() {
		titleLabel2.setFont(new Font("Verdana", 30));
		idLabel2.setFont(new Font("Poppins", 14));
		userLabel2.setFont(new Font("Poppins", 14));
		passLabel2.setFont(new Font("Poppins", 14));
		confPassLabel2.setFont(new Font("Poppins", 14));
		genderLabel2.setFont(new Font("Poppins", 14));
		addressLabel2.setFont(new Font("Poppins", 14));
		nationLabel2.setFont(new Font("Poppins", 14));
		regisButton.setFont(Font.font("Poppins", FontWeight.BOLD, 13));
		loginLink.setFont(Font.font("Courier New", FontWeight.BOLD, 14));
		
		idField2.setMaxWidth(250);
		userField2.setMaxWidth(250);
		passField2.setMaxWidth(250);
		confPassField2.setMaxWidth(250);
		addressTA2.setMaxWidth(250);
		addressTA2.setMaxHeight(50);
		
		regisButton.setPrefSize(100, 35);
	}
	
	void setColourRegis() {
		bpRegis.setStyle("-fx-background-color: #FFFFFF");
		idField2.setStyle("-fx-background-color: #E2E6EC");
		regisButton.setStyle("-fx-background-color: #FA4917");
		titleLabel2.setStyle("-fx-text-fill: #2A293E");
		userLabel2.setStyle("-fx-text-fill: #2A293E");
		passLabel2.setStyle("-fx-text-fill: #2A293E");
		confPassLabel2.setStyle("-fx-text-fill: #2A293E");
		genderLabel2.setStyle("-fx-text-fill: #2A293E");
		addressLabel2.setStyle("-fx-text-fill: #2A293E");
		nationLabel2.setStyle("-fx-text-fill: #2A293E");
		regisButton.setTextFill(Color.WHITE);
		loginLink.setTextFill(Color.BLUEVIOLET);
	}
	
	// =======================================================================================================================================================
	// Main Form User & Admin
	
	MenuBar menuBarUser, menuBarAdmin;
	Menu myUser, transaction, myAdmin, manage;
	MenuItem signOut, buyTShirt, viewTranscationHistory, manageTShirt;
	BorderPane bpMainUser, bpMainAdmin;
	Scene scMainUser, scMainAdmin;
	
	void initMainFormUser() {
		menuBarUser = new MenuBar();
		myUser = new Menu("My User");
		transaction = new Menu("Transaction");

		signOut = new MenuItem("Sign Out");
		signOut.setOnAction(this);

		buyTShirt = new MenuItem("Buy T-Shirt");
		buyTShirt.setOnAction(this);

		viewTranscationHistory = new MenuItem("View Transaction History");
		viewTranscationHistory.setOnAction(this);
		
		bpMainUser = new BorderPane();
		scMainUser = new Scene(bpMainUser, 800, 675);

		menuBarUser.getMenus().add(myUser);
		menuBarUser.getMenus().add(transaction);
		myUser.getItems().add(signOut);
		transaction.getItems().add(buyTShirt);
		transaction.getItems().add(viewTranscationHistory);

		bpMainUser.setTop(menuBarUser);
	}
			
	void initMainFormAdmin() {
		menuBarAdmin = new MenuBar();
		myAdmin = new Menu("My Admin");
		manage = new Menu("Manage");
		
		signOut = new MenuItem("Sign Out");
		signOut.setOnAction(this);
		
		manageTShirt = new MenuItem("Manage T-Shirt");
		manageTShirt.setOnAction(this);
		
		bpMainAdmin = new BorderPane();
		scMainAdmin = new Scene(bpMainAdmin, 800, 550);
		
		menuBarAdmin.getMenus().add(myAdmin);
		myAdmin.getItems().add(signOut);
		menuBarAdmin.getMenus().add(manage);
		manage.getItems().add(manageTShirt);
		
		bpMainAdmin.setTop(menuBarAdmin);
	}

	// =======================================================================================================================================================
	// Buy T-Shirt Window
	
	BorderPane bpBuy, bpCart;
	GridPane gpBuy;
	FlowPane fpCart;
	TableView productTable, cartTable;
	Label tshirtIDLabel, tshirtNameLabel, tshirtPriceLabel, tshirtQuantityLabel, cartLabel;
	TextField tshirtIDField, tshirtNameField, tshirtPriceField;
	Spinner<Integer> quantitySpinner;
	Button addToCartBtn, removeTShirtBtn, updateTShirtBtn, buyBtn;
	Window windowBuy;
	
	void initBuyTShirt() {
		bpBuy = new BorderPane();
		bpCart = new BorderPane();
		gpBuy = new GridPane();
		fpCart = new FlowPane();
		productTable = new TableView<>();
		cartTable = new TableView<>();

		tshirtIDLabel = new Label("T-Shirt ID");
		tshirtNameLabel = new Label("T-Shirt Name");
		tshirtPriceLabel = new Label("T-Shirt Price");
		tshirtQuantityLabel = new Label("T-Shirt Quantity");
		cartLabel = new Label("Cart");
		
		tshirtIDField = new TextField();
		tshirtIDField.setEditable(false);
		tshirtIDField.setMouseTransparent(true);
		tshirtIDField.setFocusTraversable(false);
		
		tshirtNameField = new TextField();
		tshirtNameField.setEditable(false);
		tshirtNameField.setMouseTransparent(true);
		tshirtNameField.setFocusTraversable(false);
		
		tshirtPriceField = new TextField();
		tshirtPriceField.setEditable(false);
		tshirtPriceField.setMouseTransparent(true);
		tshirtPriceField.setFocusTraversable(false);
		
		quantitySpinner = new Spinner<>();
		
		addToCartBtn = new Button("Add to Cart");
		removeTShirtBtn = new Button("Remove T-Shirt");
		updateTShirtBtn = new Button("Update T-Shirt");
		buyBtn = new Button("BUY");
		
		windowBuy = new Window("Buy T-Shirt");
		windowBuy.getRightIcons().add(new CloseIcon(windowBuy));
	}
	
	void setPositionBuyTShirt() {
		cartList.clear();
		
		TableColumn<Product, String> idCol = new TableColumn<>("ProductID");
		idCol.setCellValueFactory(new PropertyValueFactory<>("productID"));
		idCol.setMinWidth(150);
		idCol.setMaxWidth(150);
		TableColumn<Product, String> nameCol = new TableColumn<>("ProductName");
		nameCol.setCellValueFactory(new PropertyValueFactory<>("productName"));
		nameCol.setMinWidth(150);
		nameCol.setMaxWidth(150);
		TableColumn<Product, String> priceCol = new TableColumn<>("ProductPrice");
		priceCol.setCellValueFactory(new PropertyValueFactory<>("productPrice"));
		priceCol.setMinWidth(150);
		priceCol.setMaxWidth(150);
		TableColumn<Product, Integer> stockCol = new TableColumn<>("ProductStock");
		stockCol.setCellValueFactory(new PropertyValueFactory<>("productStock"));
		stockCol.setMinWidth(150);
		stockCol.setMaxWidth(150);
		TableColumn<Product, String> typeCol = new TableColumn<>("ProductType");
		typeCol.setCellValueFactory(new PropertyValueFactory<>("productType"));
		typeCol.setMinWidth(153);
		typeCol.setMaxWidth(153);
		
		productTable.getColumns().addAll(idCol, nameCol, priceCol, stockCol, typeCol);
		getDataProduct();
		refreshTableProduct();
		
		BorderPane.setMargin(productTable, new Insets(10));
		sp.setContent(productTable);
		sp.setFitToWidth(true);
		
		productTable.setOnMouseClicked(e -> {			
			TableSelectionModel<Product> tableSelectionProduct = productTable.getSelectionModel();
			tableSelectionProduct.setSelectionMode(SelectionMode.SINGLE);
			Product product = tableSelectionProduct.getSelectedItem();
			
			tshirtIDField.setText(product.getProductID());
			tshirtNameField.setText(product.getProductName());
			tshirtPriceField.setText(product.getProductPrice());
			SpinnerValueFactory<Integer> quantitySV = new IntegerSpinnerValueFactory(0, 100, product.getProductStock());
			quantitySpinner.setValueFactory(quantitySV);
			
			addToCartBtn.setOnMouseClicked(e2 -> {
				boolean checkStock = true, checkTShirt = true;
				Alert validateStock = new Alert(AlertType.ERROR);
				Alert validateTShirt = new Alert(AlertType.ERROR);
				
				if (tshirtIDField.getText().trim().isEmpty()) {
					checkTShirt = false;
					validateTShirt.setContentText("Click the Table Row First!");
					validateTShirt.showAndWait();
				}
				if (quantitySpinner.getValue() == 0 || quantitySpinner.getValue() > product.getProductStock()) {
					checkStock = false;
					validateStock.setContentText("1. Quantity can't be more than Stock!\n2. Quantity can't be zero");
					validateStock.showAndWait();
				}
				if (checkStock && checkTShirt) {
					product.setProductStock(product.getProductStock() - quantitySpinner.getValue());
					productList.set(tableSelectionProduct.getSelectedIndex(), product);
					productTable.getItems().add(tableSelectionProduct.getSelectedIndex(), product);
					productTable.getItems().remove(tableSelectionProduct.getSelectedIndex() -1);
					refreshTableProduct();
					
					boolean newRow = true;
					
					for (int i = 0; i < cartList.size(); i++) {
						if (cartList.get(i).getShirtID().equals(tshirtIDField.getText())) {
							newRow = false;
							cartList.get(i).setShirtQuantity(quantitySpinner.getValue() + cartList.get(i).getShirtQuantity());
						}
					}
					if (newRow) {
						String shirtID = tshirtIDField.getText();
						String shirtName = tshirtNameField.getText();
						String shirtPrice = tshirtPriceField.getText();
						Integer shirtQuantity = quantitySpinner.getValue();
						String shirtType = product.getProductType();
						cartList.add(new Cart(shirtID, shirtName, shirtPrice, shirtQuantity, shirtType));
						refreshTableCart();
					}
					
					refreshValueBuyTShirt();
					
					tableSelectionProduct.clearSelection();
					
					TableSelectionModel<Cart> tableSelectionCart = cartTable.getSelectionModel();
					tableSelectionCart.setSelectionMode(SelectionMode.SINGLE);
					tableSelectionCart.clearSelection();
				}
			});
		});		
		
		addToCartBtn.setOnMouseClicked(e -> {
			Alert validateTShirt = new Alert(AlertType.ERROR);
			if (tshirtIDField.getText().trim().isEmpty()) {
				validateTShirt.setContentText("Click the Table Row First!");
				validateTShirt.showAndWait();
			}
		});
		
		bpBuy.setTop(productTable);
		
		gpBuy.add(tshirtIDLabel, 0, 0);
		gpBuy.add(tshirtIDField, 1, 0);
		gpBuy.add(tshirtNameLabel, 0, 1);
		gpBuy.add(tshirtNameField, 1, 1);
		gpBuy.add(tshirtPriceLabel, 0, 2);
		gpBuy.add(tshirtPriceField, 1, 2);
		gpBuy.add(tshirtQuantityLabel, 0, 3);
		gpBuy.add(quantitySpinner, 1, 3);
		
		gpBuy.add(addToCartBtn, 2, 3);
		addToCartBtn.setTranslateX(20);
		addToCartBtn.setPadding(new Insets(7, 20, 7, 20));
		
		gpBuy.setVgap(10);
		gpBuy.setHgap(15);
		gpBuy.setPadding(new Insets(10, 10, 0, 10));
		
		bpBuy.setCenter(gpBuy);
		
		cartTable = new TableView<>();
		TableColumn<Cart, String> id2Col = new TableColumn<>("ShirtID");
		id2Col.setCellValueFactory(new PropertyValueFactory<>("shirtID"));
		id2Col.setMinWidth(150);
		id2Col.setMaxWidth(150);
		TableColumn<Cart, String> name2Col = new TableColumn<>("ShirtName");
		name2Col.setCellValueFactory(new PropertyValueFactory<>("shirtName"));
		name2Col.setMinWidth(150);
		name2Col.setMaxWidth(150);
		TableColumn<Cart, String> price2Col = new TableColumn<>("ShirtPrice");
		price2Col.setCellValueFactory(new PropertyValueFactory<>("shirtPrice"));
		price2Col.setMinWidth(150);
		price2Col.setMaxWidth(150);
		TableColumn<Cart, Integer> quantity2Col = new TableColumn<>("ShirtQuantity");
		quantity2Col.setCellValueFactory(new PropertyValueFactory<>("shirtQuantity"));
		quantity2Col.setMinWidth(150);
		quantity2Col.setMaxWidth(150);
		TableColumn<Cart, String> type2Col = new TableColumn<>("ShirtType");
		type2Col.setCellValueFactory(new PropertyValueFactory<>("shirtType"));
		type2Col.setMinWidth(153);
		type2Col.setMaxWidth(153);
	
		cartTable.getColumns().addAll(id2Col, name2Col, price2Col, quantity2Col, type2Col);
		refreshTableCart();
		
		BorderPane.setMargin(cartTable, new Insets(5, 10, 5, 10));
		sp.setContent(cartTable);

		cartTable.setOnMouseClicked(e -> {
			TableSelectionModel<Cart> tableSelectionCart = cartTable.getSelectionModel();
			tableSelectionCart.setSelectionMode(SelectionMode.SINGLE);
			Cart cart = tableSelectionCart.getSelectedItem();
			
			tshirtIDField.setText(cart.getShirtID());
			tshirtNameField.setText(cart.getShirtName());
			tshirtPriceField.setText(cart.getShirtPrice());
			SpinnerValueFactory<Integer> quantitySV = new IntegerSpinnerValueFactory(0, 100, cart.getShirtQuantity());
			quantitySpinner.setValueFactory(quantitySV);
			
			TableSelectionModel<Product> tableSelectionProduct = productTable.getSelectionModel();
			tableSelectionProduct.setSelectionMode(SelectionMode.SINGLE);
			Product product = tableSelectionProduct.getSelectedItem();

			removeTShirtBtn.setOnMouseClicked(e3 -> {
				Product store = null;
				
				for (int i = 0; i < productList.size(); i++) {
					if (productList.get(i).getProductID().equals(cart.shirtID)) {
						store = productList.get(i);
					}
				}
				
				boolean checkCart = true, checkProduct = true;
				
				if (tableSelectionCart.isEmpty() || tshirtIDField.getText().isEmpty()) {
					checkCart = false;
					Alert updateFailed = new Alert(AlertType.ERROR);
					updateFailed.setHeaderText("Update Failed");
					updateFailed.setContentText("Please click a cart table row!");
					updateFailed.showAndWait();
				}
				if (!tableSelectionProduct.isEmpty()) {
					checkProduct = false;
					Alert error = new Alert(AlertType.ERROR);
					error.setHeaderText("Remove T-Shirt Failed");
					error.setContentText("Please select only the cart table row!");
					error.showAndWait();
				}
				else if (checkCart && checkProduct) {
					store.setProductStock(store.getProductStock() + cart.getShirtQuantity());
					refreshTableProduct();
					
					cartList.remove(tableSelectionCart.getSelectedIndex());
					refreshTableCart();
					
					Alert removeSuccess = new Alert(AlertType.INFORMATION);
					removeSuccess.setContentText("Remove T-Shirt Success!");
					removeSuccess.showAndWait();
				}
				refreshValueBuyTShirt();
				tableSelectionProduct.clearSelection();
				tableSelectionCart.clearSelection();
			});
			
			updateTShirtBtn.setOnMouseClicked(e2 -> {
				Product store = null;
				
				for (int i = 0; i < productList.size(); i++) {
					if (productList.get(i).getProductID().equals(cart.shirtID)) {
						store = productList.get(i);
					}
				}
				
				Alert updateFailed = new Alert(AlertType.ERROR);
				updateFailed.setHeaderText("Update T-Shirt Failed");

				boolean checkCart = true, checkProduct = true;
				
				if (tableSelectionCart.isEmpty() || tshirtIDField.getText().isEmpty()) {
					checkCart = false;
					updateFailed.setContentText("Please click a cart table row!");
					updateFailed.showAndWait();
				}
				if (!tableSelectionProduct.isEmpty()) {
					checkProduct = false;
					updateFailed.setContentText("Please select only the cart table row!");
					updateFailed.showAndWait();
				}
				else if (checkCart && checkProduct) {
					if (quantitySpinner.getValue() < cart.getShirtQuantity() + store.getProductStock()) {
						store.setProductStock(store.getProductStock() + (cart.getShirtQuantity() - quantitySpinner.getValue()));	
						cart.setShirtQuantity(quantitySpinner.getValue());
						
						refreshTableProduct();
						refreshTableCart();
					}
					else if (quantitySpinner.getValue() > cart.getShirtQuantity() + store.getProductStock()) {
						if (store.getProductStock() - (quantitySpinner.getValue() - cart.getShirtQuantity()) < 0) {
							updateFailed.setContentText("The selected product stock result has to be zero or greater!");
							updateFailed.showAndWait();
						}
						else {
							store.setProductStock(product.getProductStock() - (quantitySpinner.getValue() - cart.getShirtQuantity()));
							cart.setShirtQuantity(quantitySpinner.getValue());
							
							refreshTableProduct();
							refreshTableCart();
						}
					}
					Alert updateSuccess = new Alert(AlertType.INFORMATION);
					updateSuccess.setContentText("Update T-Shirt Success!");
					updateSuccess.showAndWait();
				}
				refreshValueBuyTShirt();
				tableSelectionProduct.clearSelection();
				tableSelectionCart.clearSelection();
			});
		});
		
		removeTShirtBtn.setOnMouseClicked(e -> {
			TableSelectionModel<Cart> tableSelectionCart = cartTable.getSelectionModel();
			tableSelectionCart.setSelectionMode(SelectionMode.SINGLE);
			
			if (tableSelectionCart.isEmpty() || tshirtIDField.getText().isEmpty()) {
				Alert removeFailed = new Alert(AlertType.ERROR);
				removeFailed.setHeaderText("Remove Failed");
				removeFailed.setContentText("Please click a cart table row!");
				removeFailed.showAndWait();
			}
		});
		
		updateTShirtBtn.setOnMouseClicked(e -> {
			TableSelectionModel<Cart> tableSelectionCart = cartTable.getSelectionModel();
			tableSelectionCart.setSelectionMode(SelectionMode.SINGLE);
			
			if (tableSelectionCart.isEmpty() || tshirtIDField.getText().isEmpty()) {
				Alert updateFailed = new Alert(AlertType.ERROR);
				updateFailed.setHeaderText("Update Failed");
				updateFailed.setContentText("Please click a cart table row!");
				updateFailed.showAndWait();
			}
		});
		
		buyBtn.setOnMouseClicked(e -> {
			if (cartTable.getItems().isEmpty()) {
				Alert errorBuy = new Alert(AlertType.ERROR);
				errorBuy.setContentText("Your cart is empty!");
				errorBuy.showAndWait();
			}
			else {
				addDataCart(tempId);
				addDataTransactionHeader(tempId);

				Alert buySuccess = new Alert(AlertType.INFORMATION);
				buySuccess.setContentText("Buy T-Shirt Success!");
				buySuccess.showAndWait();
			}
		});
		
		bpCart.setTop(cartLabel);
		cartLabel.setPadding(new Insets(0, 0, 0, 30));
		
		bpCart.setCenter(cartTable);
		
		fpCart.getChildren().add(removeTShirtBtn);
		removeTShirtBtn.setPadding(new Insets(7, 20, 7, 20));
		fpCart.getChildren().add(updateTShirtBtn);
		updateTShirtBtn.setPadding(new Insets(7, 20, 7, 20));
		fpCart.getChildren().add(buyBtn);
		buyBtn.setPadding(new Insets(7, 20, 7, 20));
		fpCart.setHgap(100);
		fpCart.setAlignment(Pos.CENTER);
		fpCart.setPadding(new Insets(5, 0, 10, 0));
		
		bpCart.setBottom(fpCart);
		
		bpBuy.setBottom(bpCart);
		
		windowBuy.getContentPane().getChildren().add(bpBuy);
		bpMainUser.setCenter(windowBuy);
	}
	
	void setSizeBuyTShirt() {
		productTable.setMaxWidth(800);
		productTable.setMaxHeight(150);
		
		tshirtIDLabel.setFont(new Font("Poppins", 14));
		tshirtNameLabel.setFont(new Font("Poppins", 14));
		tshirtPriceLabel.setFont(new Font("Poppins", 14));
		tshirtQuantityLabel.setFont(new Font("Poppins", 14));
		addToCartBtn.setFont(Font.font("Poppins", FontWeight.BOLD, 13));
		cartLabel.setFont(new Font("Poppins", 20));
		removeTShirtBtn.setFont(Font.font("Poppins", FontWeight.BOLD, 13));
		updateTShirtBtn.setFont(Font.font("Poppins", FontWeight.BOLD, 13));
		buyBtn.setFont(Font.font("Poppins", FontWeight.BOLD, 13));
		
		tshirtIDField.setPrefSize(250, 20);
		tshirtNameField.setPrefSize(250, 20);
		tshirtPriceField.setPrefSize(250, 20);
		quantitySpinner.setPrefSize(250, 20);
		
		cartTable.setMaxWidth(800);
		cartTable.setMaxHeight(150);
	}
	
	void setColourBuyTShirt() {
		bpBuy.setStyle("-fx-background-color: #FFFFFF");
		tshirtIDField.setStyle("-fx-background-color: #E2E6EC");
		tshirtNameField.setStyle("-fx-background-color: #E2E6EC");
		tshirtPriceField.setStyle("-fx-background-color: #E2E6EC");
		addToCartBtn.setStyle("-fx-background-color: #FA4917");
		removeTShirtBtn.setStyle("-fx-background-color: #FA4917");
		updateTShirtBtn.setStyle("-fx-background-color: #FA4917");
		buyBtn.setStyle("-fx-background-color: #FA4917");
		tshirtIDLabel.setStyle("-fx-text-fill: #2A293E");
		tshirtNameLabel.setStyle("-fx-text-fill: #2A293E");
		tshirtPriceLabel.setStyle("-fx-text-fill: #2A293E");
		tshirtQuantityLabel.setStyle("-fx-text-fill: #2A293E");
		cartLabel.setTextFill(Color.BLACK);
		addToCartBtn.setTextFill(Color.WHITE);
		removeTShirtBtn.setTextFill(Color.WHITE);
		updateTShirtBtn.setTextFill(Color.WHITE);
		buyBtn.setTextFill(Color.WHITE);
	}
	
	// =======================================================================================================================================================
	// View Transaction History Window

	BorderPane bpHistory;
	TableView headerTransactionTable, cartHistoryTable;
	Window windowHistory;
	
	void initHistory() {
		bpHistory = new BorderPane();
		headerTransactionTable = new TableView<>();
		cartHistoryTable = new TableView<>();
		windowHistory = new Window("View Transaction History");
		windowHistory.getRightIcons().add(new CloseIcon(windowHistory));
	}
	
	void setPositionHistory() {
		TableColumn<TransactionHeader, Integer> idCol = new TableColumn<>("TransactionID");
		idCol.setCellValueFactory(new PropertyValueFactory<>("transactionID"));
		idCol.setMinWidth(376.5);
		TableColumn<TransactionHeader, Date> dateCol = new TableColumn<>("TransactionDate");
		dateCol.setCellValueFactory(new PropertyValueFactory<>("transactionDate"));
		dateCol.setMinWidth(376.5);
		
		headerTransactionTable.getColumns().addAll(idCol, dateCol);
		refreshTableHeader();
		
		BorderPane.setMargin(headerTransactionTable, new Insets(10, 10, 0, 10));
		sp.setContent(headerTransactionTable);
		sp.setFitToWidth(true);
		
		headerTransactionTable.setOnMouseClicked(e -> {
			TableSelectionModel<TransactionHeader> tableSelectionHeader = headerTransactionTable.getSelectionModel();
			tableSelectionHeader.setSelectionMode(SelectionMode.SINGLE);
			TransactionHeader header = tableSelectionHeader.getSelectedItem();
			
			getDataTransactionDetail(header.getTransactionID());
		});
		
		
		TableColumn<Product, String> id2Col = new TableColumn<>("ProductID");
		id2Col.setCellValueFactory(new PropertyValueFactory<>("productID"));
		id2Col.setMinWidth(150);
		TableColumn<Product, String> nameCol = new TableColumn<>("ProductName");
		nameCol.setCellValueFactory(new PropertyValueFactory<>("productName"));
		nameCol.setMinWidth(150);
		TableColumn<Product, Integer> priceCol = new TableColumn<>("ProductPrice");
		priceCol.setCellValueFactory(new PropertyValueFactory<>("productPrice"));
		priceCol.setMinWidth(150);
		TableColumn<Product, Integer> stockCol = new TableColumn<>("ProductStock");
		stockCol.setCellValueFactory(new PropertyValueFactory<>("productStock"));
		stockCol.setMinWidth(150);
		TableColumn<Product, String> typeCol = new TableColumn<>("ProductType");
		typeCol.setCellValueFactory(new PropertyValueFactory<>("productType"));
		typeCol.setMinWidth(153);
		
		cartHistoryTable.getColumns().addAll(id2Col, nameCol, priceCol, stockCol, typeCol);
		
		BorderPane.setMargin(cartHistoryTable, new Insets(30, 10, 30, 10));
		sp.setContent(cartHistoryTable);
		
		bpHistory.setTop(headerTransactionTable);
		bpHistory.setCenter(cartHistoryTable);
		
		windowHistory.getContentPane().getChildren().add(bpHistory);
		bpMainUser.setCenter(windowHistory);
	}
	
	void setSizeHistory() {
		headerTransactionTable.setMaxWidth(800);
		headerTransactionTable.setMaxHeight(150);
		cartHistoryTable.setMaxWidth(800);
		cartHistoryTable.setMaxHeight(250);
	}
	
	void setColourHistory() {
		bpHistory.setStyle("-fx-background-color: #FFFFFF");
	}
	
	// =======================================================================================================================================================
	// Manage T-Shirt Window

	BorderPane bpManage;
	GridPane gpManage;
	FlowPane fpManage;
	TableView productTableManage;
	Label productNameLabel, productPriceLabel, productStockLabel, productTypeLabel;
	TextField productNameTF, productPriceTF;
	Spinner<Integer> stockSpinner;
	ComboBox<String> typeBox;
	Button deleteBtn, updateBtn, addBtn;
	Window windowManage;
	
	void initManage() {
		bpManage = new BorderPane();
		gpManage = new GridPane();
		fpManage = new FlowPane();
		
		productTableManage = new TableView<>();
		productNameLabel = new Label("Product Name");
		productPriceLabel = new Label("Product Price");
		productStockLabel = new Label("Product Stock");
		productTypeLabel = new Label("Product Type");
		productNameTF = new TextField();
		productPriceTF = new TextField();
		
		stockSpinner = new Spinner<>(0, 100, 0);
		
		typeBox = new ComboBox<>();
		typeBox.getItems().add("Oversize");
		typeBox.getItems().add("Turtleneck");
		typeBox.getItems().add("O-Neck");
		typeBox.getItems().add("V-Neck");
		typeBox.setValue("~~Choose one~~");
		
		deleteBtn = new Button("Delete");
		updateBtn = new Button("Update");
		addBtn = new Button("Add");
		
		windowManage = new Window("Manage T-Shirt");
		windowManage.getRightIcons().add(new CloseIcon(windowManage));
	}
	
	void setPositionManage() {
		TableColumn<Product, String> idManageCol = new TableColumn<>("ProductID");
		idManageCol.setCellValueFactory(new PropertyValueFactory<>("productID"));
		idManageCol.setMinWidth(150);
		idManageCol.setMaxWidth(150);
		TableColumn<Product, String> nameManageCol = new TableColumn<>("ProductName");
		nameManageCol.setCellValueFactory(new PropertyValueFactory<>("productName"));
		nameManageCol.setMinWidth(150);
		nameManageCol.setMaxWidth(150);
		TableColumn<Product, String> priceManageCol = new TableColumn<>("ProductPrice");
		priceManageCol.setCellValueFactory(new PropertyValueFactory<>("productPrice"));
		priceManageCol.setMinWidth(150);
		priceManageCol.setMaxWidth(150);
		TableColumn<Product, Integer> stockManageCol = new TableColumn<>("ProductStock");
		stockManageCol.setCellValueFactory(new PropertyValueFactory<>("productStock"));
		stockManageCol.setMinWidth(150);
		stockManageCol.setMaxWidth(150);
		TableColumn<Product, String> typeManageCol = new TableColumn<>("ProductType");
		typeManageCol.setCellValueFactory(new PropertyValueFactory<>("productType"));
		typeManageCol.setMinWidth(153);
		typeManageCol.setMaxWidth(153);
		
		productTableManage.getColumns().addAll(idManageCol, nameManageCol, priceManageCol, stockManageCol, typeManageCol);
		getDataProduct();
		refreshTableProductManage();
		
		BorderPane.setMargin(productTableManage, new Insets(10));
		sp.setContent(productTableManage);
		sp.setFitToWidth(true);
		
		productTableManage.setOnMouseClicked(e -> {
			TableSelectionModel<Product> tableSelectionProductManage = productTableManage.getSelectionModel();
			tableSelectionProductManage.setSelectionMode(SelectionMode.SINGLE);
			Product productManage = tableSelectionProductManage.getSelectedItem();
			
			productNameTF.setText(productManage.getProductName());
			productPriceTF.setText(productManage.getProductPrice());
			SpinnerValueFactory<Integer> stockSV = new IntegerSpinnerValueFactory(0, 100, productManage.getProductStock());
			stockSpinner.setValueFactory(stockSV);
			typeBox.setValue(productManage.getProductType());
			
			deleteBtn.setOnMouseClicked(e2 -> {
				Alert validateProduct = new Alert(AlertType.ERROR);
				if (stockSpinner.getValue() != productManage.getProductStock()) {
					validateProduct.setHeaderText("Remove T-Shirt Failed");
					validateProduct.setContentText("Product stock can't be edited!");
					validateProduct.showAndWait();
				}
				else {
					productTableManage.getItems().remove(tableSelectionProductManage.getSelectedIndex());
					productList.remove(productManage);
					
					String productID = productManage.getProductID();
					
					String query = String.format("DELETE FROM products WHERE ProductID = '%s'", productID);
					connect.execUpdate(query);
					
					refreshTableProductManage();
					refreshValueManageTShirt();
					tableSelectionProductManage.clearSelection();
					
					Alert deleteSuccess = new Alert(AlertType.INFORMATION);
					deleteSuccess.setContentText("Success Delete T-Shirt!");
					deleteSuccess.showAndWait();
				}
			});
			updateBtn.setOnMouseClicked(e2 -> {
				Alert validateProduct = new Alert(AlertType.ERROR);
				if (stockSpinner.getValue() != productManage.getProductStock()) {
					validateProduct.setHeaderText("Remove T-Shirt Failed");
					validateProduct.setContentText("Product stock can't be edited!");
					validateProduct.showAndWait();
				}
				else {
					productManage.setProductName(productNameTF.getText());
					productManage.setProductPrice(productPriceTF.getText());
					productManage.setProductType(typeBox.getSelectionModel().getSelectedItem());
					
					productList.set(tableSelectionProductManage.getSelectedIndex(), productManage);
					productTableManage.getItems().add(tableSelectionProductManage.getSelectedIndex(), productManage);
					productTableManage.getItems().remove(tableSelectionProductManage.getSelectedIndex() - 1);
					refreshTableProductManage();
					
					String productID = productManage.getProductID();
					String productName = productManage.getProductName();
					String productPrice = productManage.getProductPrice();
					Integer productStock = productManage.getProductStock();
					String productType = productManage.getProductType();
					
					updateDataProductManage(productID, productName, productPrice, productStock, productType);
					
					productList.set(tableSelectionProductManage.getSelectedIndex(), productManage);
					
					refreshTableProductManage();
					refreshValueManageTShirt();
					tableSelectionProductManage.clearSelection();
					
					Alert updateSuccess = new Alert(AlertType.INFORMATION);
					updateSuccess.setContentText("Success Update T-Shirt!");
					updateSuccess.showAndWait();
				}
			});
		});		
		
		deleteBtn.setOnMouseClicked(e -> {
			if (productNameTF.getText().trim().isEmpty() || productPriceTF.getText().trim().isEmpty() || typeBox.getSelectionModel().isEmpty()) {
				Alert validateProduct = new Alert(AlertType.ERROR);
				validateProduct.setHeaderText("Remove T-Shirt Failed");
				validateProduct.setContentText("Click the Table Row First!");
				validateProduct.showAndWait();
			}
		});
		
		updateBtn.setOnMouseClicked(e -> {
			
			if (productNameTF.getText().trim().isEmpty() || productPriceTF.getText().trim().isEmpty() || typeBox.getSelectionModel().isEmpty()) {
				Alert validateProduct = new Alert(AlertType.ERROR);
				validateProduct.setHeaderText("Update T-Shirt Failed");
				validateProduct.setContentText("Click the Table Row First!");
				validateProduct.showAndWait();
			}
		});
		
		addBtn.setOnMouseClicked(e -> {
			Alert validateAdd = new Alert(AlertType.ERROR);
			validateAdd.setHeaderText("Add New T-Shirt Failed");

			boolean checkProductName = true, checkProductPrice = true, checkProductStock = true, checkProductType = true;

			if (productNameTF.getText().trim().isEmpty() || productNameTF.getText().length() < 5 || productNameTF.getText().length() > 15) {
				checkProductName = false;
				validateAdd.setContentText("New T-Shirt Name must consist of 5 - 15 characters!");
				validateAdd.showAndWait();
			}
			if (productPriceTF.getText().trim().isEmpty() || productPriceTF.getText().equals("0") || productPriceTF.getText().contains("-")
					|| Pattern.compile("[a-z]").matcher(productPriceTF.getText()).find()) {
				checkProductPrice = false;
				validateAdd.setContentText("- New T-Shirt Price can't be zero! \n- New T-Shirt Price can't be minus! \n- New T-Shirt Price must be numeric!");
				validateAdd.showAndWait();
			}
			if (stockSpinner.getValue() == 0) {
				checkProductStock = false;
				validateAdd.setContentText("Quantity must be greater than zero!");
				validateAdd.showAndWait();
			}
			if (typeBox.getSelectionModel().isEmpty()) {
				checkProductType = false;
				validateAdd.setContentText("New T-Shirt Type must be chosen either Oversize, Turtleneck, \nO-Neck, or V-Neck!");
				validateAdd.showAndWait();
			}
			if (checkProductName && checkProductPrice && checkProductStock && checkProductType) {
				Integer randNum1 = rand.nextInt(10);
				Integer randNum2 = rand.nextInt(10);
				Integer randNum3 = rand.nextInt(10);
				Character randChar1 = (char) ('A' + rand.nextInt(26));
				Character randChar2 = (char) ('A' + rand.nextInt(26));
				String ID = "TS-" + randNum1 + "" + randNum2 + "" + randNum3 + "" + randChar1 + "" + randChar2;
				
				String productID = ID;
				String productName = productNameTF.getText();
				String productPrice = productPriceTF.getText();
				Integer productStock = stockSpinner.getValue();
				String productType = typeBox.getSelectionModel().getSelectedItem();
				addDataProductManage(productID, productName, productPrice, productStock, productType);
				
				productList.add(new Product(productID, productName, productPrice, productStock, productType));

				refreshValueManageTShirt();
				refreshTableProductManage();

				Alert addSuccess = new Alert(AlertType.INFORMATION);
				addSuccess.setContentText("Success add T-Shirt!");
				addSuccess.showAndWait();
			}
		});
		
		bpManage.setTop(productTableManage);
		
		gpManage.add(productNameLabel, 0, 0);
		gpManage.add(productNameTF, 1, 0);
		gpManage.add(productPriceLabel, 0, 1);
		gpManage.add(productPriceTF, 1, 1);
		gpManage.add(productStockLabel, 0, 2);
		gpManage.add(stockSpinner, 1, 2);
		gpManage.add(productTypeLabel, 0, 3);
		gpManage.add(typeBox, 1, 3);
		gpManage.setVgap(10);
		gpManage.setHgap(15);
		gpManage.setPadding(new Insets(0, 0, 10, 0));
		bpManage.setCenter(gpManage);
		gpManage.setAlignment(Pos.CENTER);
		BorderPane.setAlignment(gpManage, Pos.CENTER);
		
		fpManage.getChildren().add(deleteBtn);
		deleteBtn.setPadding(new Insets(7, 20, 7, 20));
		fpManage.getChildren().add(updateBtn);
		updateBtn.setPadding(new Insets(7, 20, 7, 20));
		fpManage.getChildren().add(addBtn);
		addBtn.setPadding(new Insets(7, 20, 7, 20));
		
		bpManage.setBottom(fpManage);
		fpManage.setHgap(100);
		fpManage.setPadding(new Insets(10, 0, 10, 0));
		fpManage.setAlignment(Pos.CENTER);
		BorderPane.setAlignment(fpManage, Pos.CENTER);
		
		windowManage.getContentPane().getChildren().add(bpManage);
		bpMainAdmin.setCenter(windowManage);
	}
	
	void setSizeManage() {
		productTableManage.setMaxWidth(800);
		productTableManage.setMaxHeight(175);
		
		productNameLabel.setFont(new Font("Poppins", 14));
		productPriceLabel.setFont(new Font("Poppins", 14));
		productStockLabel.setFont(new Font("Poppins", 14));
		productTypeLabel.setFont(new Font("Poppins", 14));
		deleteBtn.setFont(Font.font("Poppins", FontWeight.BOLD, 13));
		updateBtn.setFont(Font.font("Poppins", FontWeight.BOLD, 13));
		addBtn.setFont(Font.font("Poppins", FontWeight.BOLD, 13));
		
		productNameTF.setPrefSize(250, 20);
		productPriceTF.setPrefSize(250, 20);
		stockSpinner.setPrefSize(250, 20);
		typeBox.setPrefSize(250, 20);
	}
	
	void setColourManage() {
		bpManage.setStyle("-fx-background-color: #FFFFFF");
		deleteBtn.setStyle("-fx-background-color: #FA4917");
		updateBtn.setStyle("-fx-background-color: #FA4917");
		addBtn.setStyle("-fx-background-color: #FA4917");
		productNameLabel.setStyle("-fx-text-fill: #2A293E");
		productPriceLabel.setStyle("-fx-text-fill: #2A293E");
		productStockLabel.setStyle("-fx-text-fill: #2A293E");
		productTypeLabel.setStyle("-fx-text-fill: #2A293E");
//		stockSpinner.setStyle("-fx-control-inner-background: #E2E6EC");
		deleteBtn.setTextFill(Color.WHITE);
		updateBtn.setTextFill(Color.WHITE);
		addBtn.setTextFill(Color.WHITE);
	}
	
	// =======================================================================================================================================================
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		initLogin();
		setPositionLogin();
		setSizeLogin();
		setColourLogin();

		primaryStage.setScene(scLogin);
		primaryStage.setResizable(false);
		primaryStage.setTitle("T-Shirt Store");
		primaryStage.show();
	}

	@Override
	public void handle(ActionEvent e) {
		if (e.getSource() == regisLink) {
			initRegis();
			setPositionRegis();
			setSizeRegis();
			setColourRegis();
			
			stage.setScene(scRegis);
			stage.setResizable(false);
			stage.setTitle("T-Shirt Store");
			stage.show();
		}
		if (e.getSource() == loginButton) {
			Alert validate = new Alert(AlertType.ERROR);
			Alert successful = new Alert(AlertType.INFORMATION);
			
				if (userField.getText().trim().isEmpty()) {
					validate.setHeaderText("Login Failed");
					validate.setContentText("Username field must be filled!");
					validate.showAndWait();
				}
				if (passField.getText().trim().isEmpty()) {
					validate.setHeaderText("Login Failed");
					validate.setContentText("Password field must be filled!");
					validate.showAndWait();
				}
				
				String query = "SELECT * FROM users WHERE UserUsername = '" + userField.getText() + "' AND UserPassword = '" + passField.getText() + "' ";
				connect.rs = connect.execQuery(query);
				
				try {
					if (connect.rs == null) {
						validate.setHeaderText("Login Failed");
						validate.setContentText("Username and Password must be correct!");
						validate.showAndWait();
					}
					else if (connect.rs.next() || !userField.getText().trim().isEmpty() || !passField.getText().trim().isEmpty()) {
						successful.setHeaderText("Message");
						successful.setContentText("Login Successful!");
						successful.showAndWait();
						
						if (userField.getText().equals("Cheryl") || passField.getText().equals("cher123")) {
							successful.setHeaderText("Message");
							successful.setContentText("Your role is ADMIN!");
							successful.showAndWait();
							initMainFormAdmin();
							
							stage.setScene(scMainAdmin);
							stage.setResizable(false);
							stage.setTitle("T-Shirt Store");
							stage.show();
						}
						else {
							tempId = connect.rs.getString(1);
							
							successful.setHeaderText("Message");
							successful.setContentText("Your role is USER!");
							successful.showAndWait();
							initMainFormUser();
							
							stage.setScene(scMainUser);
							stage.setResizable(false);
							stage.setTitle("T-Shirt Store");
							stage.show();
						}
						
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
		if (e.getSource() == loginLink || e.getSource() == signOut) {
			initLogin();
			setPositionLogin();
			setSizeLogin();
			setColourLogin();
			
			stage.setScene(scLogin);
			stage.setResizable(false);
			stage.setTitle("T-Shirt Store");
			stage.show();
		}
		if (e.getSource() == regisButton) {
			Alert validate2 = new Alert(AlertType.ERROR);
			validate2.setHeaderText("Register Failed");
			
			boolean checkUserField2 = true, checkPassField2 = true, checkConfPassField2 = true, 
					checkRadioButton2 = true, checkAddressTA2 = true, checkNationBox2 = true, 
					checkCheckBox2 = true;
			
			if (userField2.getLength() < 5 || userField2.getLength() > 15) {
				checkUserField2 = false;
				validate2.setContentText("Your username length must be between 5 - 15 characters!");
				validate2.showAndWait();
			}
			if (passField2.getLength() < 5 || passField2.getLength() > 10
					|| !Pattern.compile("[0-9]").matcher(passField2.getText()).find()
					|| !Pattern.compile("[a-z]").matcher(passField2.getText()).find()) {
				checkPassField2 = false;
				validate2.setContentText(
						"Your password length must be between 5 - 10 with at least \n1 character and 1 digit!");
				validate2.showAndWait();
			}
			if (!passField2.getText().equals(confPassField2.getText())) {
				checkConfPassField2 = false;
				validate2.setContentText("Your password and confirmation password must be the same!");
				validate2.showAndWait();
			}
			if (!(maleRadio2.isSelected() || femaleRadio2.isSelected())) {
				checkRadioButton2 = false;
				validate2.setContentText("Your gender must be selected either \"Male\" or \"Female\"");
				validate2.showAndWait();
			}
			if (addressTA2.getLength() < 10 || addressTA2.getLength() > 30) {
				checkAddressTA2 = false;
				validate2.setContentText("Your address length must be between 10 - 30 characters!");
				validate2.showAndWait();
			}
			if (nationBox2.getSelectionModel().isEmpty()) {
				checkNationBox2 = false;
				validate2.setContentText("Your nationality must be chosen either \"America\", \"Australia\", \n\"Brazil\", \"Indonesia\", \"Malaysia\", or \"Singapore\"");
				validate2.showAndWait();
			}
			if (!checkBox2.isSelected()) {
				checkCheckBox2 = false;
				validate2.setContentText("The terms and conditions must be checked!");
				validate2.showAndWait();
			}
			if (checkUserField2 && checkPassField2 && checkConfPassField2 && checkRadioButton2 && checkAddressTA2 && checkNationBox2 && checkCheckBox2) {
				Alert successful2 = new Alert(AlertType.INFORMATION);
				successful2.setContentText("Register Successful!");
				successful2.showAndWait();
				
				tempId = idField2.getText();
				String user = userField2.getText();
				String pass = passField2.getText();
				RadioButton radBtn = (RadioButton)genderToggle2.getSelectedToggle();
				String gender = radBtn.getText();
				String address = addressTA2.getText();
				String role = "User";
				String nationality = nationBox2.getSelectionModel().getSelectedItem();
				
				addDataRegis(tempId, user, pass, gender, address, role, nationality);
				
				initMainFormUser();
				
				stage.setScene(scMainUser);
				stage.setResizable(false);
				stage.setTitle("T-Shirt Store");
				stage.show();
			}
		}
		
		if (e.getSource() == buyTShirt) {
			initBuyTShirt();
			setPositionBuyTShirt();
			setSizeBuyTShirt();
			setColourBuyTShirt();
			
			stage.setScene(scMainUser);
			stage.setResizable(false);
			stage.setTitle("T-Shirt Store");
			stage.show();
		}
		
		if (e.getSource() == viewTranscationHistory) {
			initHistory();
			setPositionHistory();
			setSizeHistory();
			setColourHistory();
			
			stage.setScene(scMainUser);
			stage.setResizable(false);
			stage.setTitle("T-Shirt Store");
			stage.show();
		}
		
		if (e.getSource() == manageTShirt) {
			initManage();
			setPositionManage();
			setSizeManage();
			setColourManage();
			
			stage.setScene(scMainAdmin);
			stage.setResizable(false);
			stage.setTitle("T-Shirt Store");
			stage.show();
		}
	}
	
	private int checkTransactionID() {
		String query = "SELECT TransactionID FROM headertransaction ORDER BY TransactionID DESC";
		connect.rs = connect.execQuery(query);
		
		int count = 0;
		
		try {
			while (connect.rs.next()) {
				count++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;
	}
	
	// INSERT DATA
	
	private void addDataRegis(String ID, String user, String pass, String gender,  String address, String role, String nationality) {
		String query = String.format("INSERT INTO users VALUES('%s', '%s', '%s', '%s', '%s', '%s', '%s')", ID, user,
				pass, gender, address, role, nationality);
		connect.execUpdate(query);
	}
	
	private void addDataProductManage(String productID, String productName, String productPrice, Integer productStock, String productType) {
		String query = String.format("INSERT INTO products VALUES('%s', '%s', '%s', '%s', '%s')", productID, productName, productPrice, productStock, productType);
		connect.execUpdate(query);
	}
	
	private void addDataTransactionHeader(String userID) {
		Integer transactionID = checkTransactionID();
		transactionID += 1;
		
		String query = "INSERT INTO headertransaction (TransactionID, UserID, TransactionDate) VALUES(?, ?, ?)";
		
		try {
			ps = connect.prepareStatement(query);
			ps.setInt(1, transactionID);
			ps.setString(2, userID);
			ps.setDate(3, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
			
			addDataTransactionDetail(transactionID, userID);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void addDataTransactionDetail(Integer transactionID, String userID) {
		String checkUserCart = String.format("SELECT * FROM cart WHERE userID = '%s' ", userID);
		connect.rs = connect.execQuery(checkUserCart);
		
		try {
			while (connect.rs.next()) {
				String productID = connect.rs.getString(2);
				Integer transactionQuantity = connect.rs.getInt(3);

				String query = String.format("INSERT INTO detailtransaction VALUES('%s', '%s', '%s')", transactionID, productID, transactionQuantity);
				connect.execUpdate(query);
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void addDataCart(String UserID) {	
		for (Cart allCart : cartList) {
			String query = String.format("INSERT INTO cart VALUES('%s', '%s', '%s')", UserID, allCart.getShirtID(),
					allCart.getShirtQuantity());
			connect.execUpdate(query);
		}
	}
	
	// GET DATA

	private void getDataProduct() {
		productList.removeAllElements();
		
		String query = "SELECT * FROM products";
		connect.rs = connect.execQuery(query);
		
		try {
			while (connect.rs.next()) {
				String productID = connect.rs.getString(1);
				String productName = connect.rs.getString(2);
				String productPrice = connect.rs.getString(3);
				Integer productStock = connect.rs.getInt(4);
				String productType = connect.rs.getString(5);
				productList.add(new Product(productID, productName, productPrice, productStock, productType));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void getDataTransactionHeader(String UserID) {
		headerList.removeAllElements();

		String query = String.format("SELECT * FROM headertransaction WHERE UserID = '%s'", UserID);
		connect.rs = connect.execQuery(query);
		
		try {
			while (connect.rs.next()) {
				Integer transactionID = connect.rs.getInt(1);
				String userID = connect.rs.getString(2);
				Date transactionDate = connect.rs.getDate(3);
				headerList.add(new TransactionHeader(transactionID, userID, transactionDate));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void getDataTransactionDetail(Integer transactionID) {
		String query = "SELECT p.* FROM detailtransaction c JOIN Products p ON c.ProductID = p.ProductID WHERE c.TransactionID = '" + transactionID + "' ";
		connect.rs = connect.execQuery(query);
		
		cartHistoryTable.getItems().clear();
		
		try {
			while (connect.rs.next()) {
				String shirtID = connect.rs.getString(1);
				String shirtName = connect.rs.getString(2);
				String shirtPrice = connect.rs.getString(3);
				Integer shirtQuantity = connect.rs.getInt(4);
				String shirtType = connect.rs.getString(5);
				Product productDetail = new Product(shirtID, shirtName, shirtPrice, shirtQuantity, shirtType);
				cartHistoryTable.getItems().add(productDetail);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// UPDATE DATA
	
	private void updateDataProductManage(String productID, String productName, String productPrice, Integer productStock, String productType) {
		String query = String.format("UPDATE products SET ProductName = '%s', ProductPrice = '%s', ProductStock = '%s', ProductType = '%s' WHERE ProductID = '%s'", productName, productPrice, productStock, productType, productID);
		connect.execUpdate(query);
	}
	
	// REFRESH TABLE
	
	private void refreshTableProduct() {
		ObservableList<Product> productObs = FXCollections.observableArrayList(productList);
		productTable.setItems(productObs);
	}
	
	private void refreshTableProductManage() {
		ObservableList<Product> productManageObs = FXCollections.observableArrayList(productList);
		productTableManage.setItems(productManageObs);
	}
	
	private void refreshTableCart() {
		ObservableList<Cart> cartObs = FXCollections.observableArrayList(cartList);
		cartTable.setItems(cartObs);
	}
	
	private void refreshTableHeader() {
		getDataTransactionHeader(tempId);
		ObservableList<TransactionHeader> headerObs = FXCollections.observableArrayList(headerList);
		headerTransactionTable.setItems(headerObs);
	}
	
	// REFRESH VALUE
	
	private void refreshValueBuyTShirt() {
		tshirtIDField.setText("");
		tshirtNameField.setText("");
		tshirtPriceField.setText("");
		SpinnerValueFactory<Integer> resetQuantitySV = new IntegerSpinnerValueFactory(0, 100, 0);
		quantitySpinner.setValueFactory(resetQuantitySV);
	}
	
	private void refreshValueManageTShirt() {
		productNameTF.setText("");
		productPriceTF.setText("");
		typeBox.setValue("~~Choose one~~");
		SpinnerValueFactory<Integer> stockSV = new IntegerSpinnerValueFactory(0, 100, 0);
		stockSpinner.setValueFactory(stockSV);
	}
}
