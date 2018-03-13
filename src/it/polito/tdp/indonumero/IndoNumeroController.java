/**
 * Sample Skeleton for 'IndoNumero.fxml' Controller Class
 */

package it.polito.tdp.indonumero;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class IndoNumeroController {
	
	private int NMAX = 100;
	private int TMAX = 7;
	
	private int segreto; // numero da indovinare;
	private int tentativi; // numero tentativi già fatti;
	
	private boolean inGame = false; // stiamo giacando?
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnNuova"
    private Button btnNuova; // Value injected by FXMLLoader

    @FXML // fx:id="txtCurr"
    private TextField txtCurr; // Value injected by FXMLLoader

    @FXML // fx:id="hbox"
    private HBox hbox; // Value injected by FXMLLoader
    
    @FXML // fx:id="txtMax"
    private TextField txtMax; // Value injected by FXMLLoader

    @FXML // fx:id="txtTentativo"
    private TextField txtTentativo; // Value injected by FXMLLoader

    @FXML // fx:id="btnProva"
    private Button btnProva; // Value injected by FXMLLoader

    @FXML // fx:id="txtLog"
    private TextArea txtLog; // Value injected by FXMLLoader

    @FXML
    void handleNuova(ActionEvent event) {
    	this.segreto = (int) (Math.random() * NMAX) + 1;
    	tentativi = 0;
    	inGame = true;
    	
    	btnNuova.setDisable(true);
    	hbox.setDisable(false);
    	txtCurr.setText(String.format("%d", this.tentativi));
    	txtMax.setText(String.format("%d", this.TMAX));
    	txtLog.clear();
    	txtTentativo.clear();
    	txtLog.setText(String.format("Indovina un numero tra %d e %d \n", 1, NMAX));
    }

    @FXML
    void handleProva(ActionEvent event) {
    	String numS = txtTentativo.getText();
    	
    	if(numS.length() == 0 ) {
    		txtLog.appendText("Devi inserire un numero\n");
    		return;
    	}
    	
    	try {
    		int num = Integer.parseInt(numS);
    		
    		if(num<1 || num>NMAX) {
    			txtLog.appendText("Valore fuori dall'intervallo consentito\n");
    			return;
    		}
    		if(num==this.segreto) {//ha indovinato
    			txtLog.setText("Hai Vinto!\n");
    			//terminare la partita
    			hbox.setDisable(true);
    			btnNuova.setDisable(false);
    			inGame = false;
    		}
    		else {
    			this.tentativi += 1; //aumento tentativi
    			txtCurr.setText(String.format("%d", this.tentativi));
    			
    			if(this.tentativi == this.TMAX) { //ha perso
    				txtLog.setText("Hai perso, il numero esatto era: "+ segreto);
        			hbox.setDisable(true);
        			btnNuova.setDisable(false);
        			inGame = false;
    			}
    				 else {
    					if(num < segreto) {	//numero troppo basso
    				txtLog.appendText("Troppo basso\n");
    			}
    					else { //numero troppo alto
    						txtLog.appendText("Troppo alto\n");
    				}
    			}
    		}
    	}
    	catch (NumberFormatException nfe) {
    		txtLog.appendText("Il dato inserito non è numerico");
    		return;
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnNuova != null : "fx:id=\"btnNuova\" was not injected: check your FXML file 'IndoNumero.fxml'.";
        assert txtCurr != null : "fx:id=\"txtCurr\" was not injected: check your FXML file 'IndoNumero.fxml'.";
        assert txtMax != null : "fx:id=\"txtMax\" was not injected: check your FXML file 'IndoNumero.fxml'.";
        assert hbox != null : "fx:id=\"hbox\" was not injected: check your FXML file 'IndoNumero.fxml'.";
        assert txtTentativo != null : "fx:id=\"txtTentativo\" was not injected: check your FXML file 'IndoNumero.fxml'.";
        assert btnProva != null : "fx:id=\"btnProva\" was not injected: check your FXML file 'IndoNumero.fxml'.";
        assert txtLog != null : "fx:id=\"txtLog\" was not injected: check your FXML file 'IndoNumero.fxml'.";

    }
}
