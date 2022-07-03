package com.mechanitis.demo.stockui.Controller;



import com.mechanitis.demo.Backend.User;
import com.mechanitis.demo.stockui.FahrtenRepository;
import com.mechanitis.demo.stockui.UserRepository;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import net.rgielen.fxweaver.core.FxmlView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@Component
@FxmlView("/chart.fxml")
public class ChartController {

    //@Value("${spring.application.ui.costs}")
    private final double centPerMinute = 0.1;

    private Optional<User> momUser;


    @FXML
    private Label welcomeText;
    @FXML
    private TextField LoginTextField;
    @FXML
    private PasswordField loginPasswordField;
    @FXML
    private Label RegisterWelcomeText;
    @FXML
    private TextField RegisterFirstNameField;
    @FXML
    private TextField RegisterLastNameField;
    @FXML
    private TextField RegisterUsernameField;
    @FXML
    private PasswordField RegisterPasswordField;
    @FXML
    private Pane loginPane;
    @FXML
    private Pane registerPane;
    @FXML
    private Pane functionsPane;
    @FXML
    private TextField minuteField;
    @FXML
    private TextField secondField;
    

    public static boolean loggedIn = false;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FahrtenRepository fahrtenRepository;




    @FXML
    protected void onRegisterButtonClick() {
        try {
            if(isUsernameValid(RegisterUsernameField.getText())) {
                userRepository.save(new User(RegisterFirstNameField.getText(), RegisterLastNameField.getText(), RegisterUsernameField.getText(), RegisterPasswordField.getText()));
                RegisterWelcomeText.setTextFill((Paint) Color.GREEN);
                loginPane.setVisible(true);
                registerPane.setVisible(false);
            } else {
                RegisterWelcomeText.setText("Name is not available");
                RegisterWelcomeText.setTextFill((Paint) Color.RED);
            }


        } catch (Exception e) {
            RegisterWelcomeText.setTextFill((Paint) Color.RED);
            RegisterWelcomeText.setText("Username already exists");
        }

    }


    @FXML
    protected void onLoginbuttonClick() {

        if (userRepository.findByUsernameAndPassword(LoginTextField.getText(), loginPasswordField.getText()).isPresent()){
            LoginTextField.setText("");
            loginPasswordField.setText("");
            loggedIn = true;
            momUser = userRepository.findByUsername(LoginTextField.getText());
            functionsPane.setVisible(true);
            loginPane.setVisible(false);

        }
        else {welcomeText.setTextFill((Paint) Color.RED);
            welcomeText.setText("Wrong Username or Password");}

    }

    @FXML
    protected void goToRegister() {
        loginPane.setVisible(false);
        registerPane.setVisible(true);
    }

    @FXML
    protected void goToLogin() {
        loginPane.setVisible(true);
        registerPane.setVisible(false);
    }

    @FXML
    protected void onCalculateButtonClick() {

    }

    @FXML
    protected void onLogoutButtonClick() {
        loggedIn = false;
        momUser = Optional.empty();
        welcomeText.setTextFill((Paint) Color.GREEN);
        welcomeText.setText("Successfully logged out");
        }


    @FXML
    public void initialize() {

    }

    private boolean isUsernameValid(String valid) {
        if(valid.matches("^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$") && !valid.isEmpty()) {
            return true;
        } else return false;
    }

    private boolean isInputValid(String minutes, String seconds) {
        if(minutes.matches("^[0-9]*$") && seconds.matches("^[0-9]*$")) {
            return true;
        } else return false;
    }

    private double calculator(int minutes, int seconds) {
        double minuteCosts = minutes * 0.1;
        double secondCosts = (seconds * 0.1) / 60;
        double grundgebühr = 2;
        double all = grundgebühr + minuteCosts + secondCosts;
        BigDecimal bd = new BigDecimal(all).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();

    }




}
