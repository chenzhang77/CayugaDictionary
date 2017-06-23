/**
 * 
 */
package mun.controller;

/**
 * @author cz5670
 *
 */
import mun.model.Dictionary;
import mun.MainApp;
import mun.util.Constant;
import mun.util.ExportDictionary;
import mun.util.ImportDictionary;
import mun.util.RemoveLine;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;


import javax.swing.event.HyperlinkEvent.EventType;

import impl.org.controlsfx.autocompletion.AutoCompletionTextFieldBinding;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.AutoCompletionBinding.ISuggestionRequest;
import org.controlsfx.control.textfield.TextFields;

import javafx.application.HostServices;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.util.Callback;

public class DictionaryController {

	
	  @FXML
	  private AnchorPane mainScene;	
	  @FXML
	  private ImageView logoImage;
	  @FXML
	  private ImageView imagebackgroup;
	  @FXML
	  private TableView<Dictionary> dictionaryTable;
	  @FXML
	  private TableColumn<Dictionary, String> firsteColumn;
	  @FXML
	  private TableColumn<Dictionary, String> lastColumn;  
	  @FXML
	  private RadioButton englishBut;
	  @FXML
	  private RadioButton cayugaBut; 
	  @FXML
	  private RadioButton related_1;
	  @FXML
	  private RadioButton related_2; 
	  @FXML
	  private RadioButton related_3;   
	  @FXML
	  private Button relatedWordBut;
	  @FXML
	  private Button searchBut;
	  @FXML
	  private Pane topPane;	  
	  @FXML
	  private Label firstLabel;
	  @FXML
	  private Label lastLabel; 
	  @FXML
	  private Button textBut_1;
	  @FXML
	  private Button textBut_2;
	  @FXML
	  private Button textBut_3;
	  @FXML
	  private Button textBut_4;  
	  @FXML
	  private Button showButton;  
	  @FXML
	  private Button deleteButton;  
	  @FXML
	  private TextField inputText;
	  
	  private MainApp mainApp;
	  
	  private ArrayList<String> dictionaryEnglishList = new ArrayList<>();
	  private ArrayList<String> dictionaryCayugaList = new ArrayList<>();
	  private String firstItem = "";
	  private String inputTextString ="";
	  private ArrayList<String> aa = new ArrayList<>();
	  public DictionaryController() {
		  
	  }
	
	  
	  /**
	     * Initializes the controller class. This method is automatically called
	     * after the fxml file has been loaded.
	  */
    @SuppressWarnings("unchecked")
	@FXML
    private void initialize() {
        // Initialize the person table with the two columns.
    	
    	firsteColumn.setCellValueFactory(cellData -> cellData.getValue().secondcolProperty());
     	lastColumn.setCellValueFactory(cellData -> cellData.getValue().thirdcolProperty());
    	dictionaryTable.setVisible(true);
    	showButton.setVisible(false);
    	deleteButton.setVisible(false);
    	
    	initialArrayList();
    	initialButtons();
    	
//    	showButton.textProperty().addListener(new ChangeListener() {
//            public void changed(ObservableValue observable, Object oldVal,Object newVal) {
//               System.out.println("text change");
//               //Event.fireEvent(inputText, event);
//            }
//        });
    	
//    	TextFields.bindAutoCompletion(inputText, new Callback<AutoCompletionBinding.ISuggestionRequest, ArrayList<String>>() {
//    	    @Override
//    	    public ArrayList<String> call(AutoCompletionBinding.ISuggestionRequest param) {
//    	    	
//    	    	
//    	    	
//    	    	ArrayList<String> aa = new ArrayList<>();
//    	    	String inputString = param.getUserText();
//    	    	inputString = inputString.replace("*", ".*");
//    	    	System.out.println(inputString);
//    	    	if(inputString.length() != 0 && inputString != null) {
//        	    	
//    	    		if(englishBut.selectedProperty().getValue())
//	        	    	for(String item :dictionaryEnglishList) {
//	        	    		if(item.matches("^"+inputString+".*$"))
//	        	    			aa.add(item);
//	        	    	}
//    	    		else
//            	    	for(String item :dictionaryCayugaList) {
//            	    		//System.out.println(item + "          " +inputString);
//            	    		if(item.matches("^"+inputString+".*$")){
//            	    			//System.out.println("coming");
//            	    			aa.add(item);
//            	    		}
//            	    			
//            	    	}
//    	    		
//    	    		return aa;	
//    	    	} else {
//    	    		return aa;
//    	    	}   	
//    	    }
//    	});

//        ArrayList al = new ArrayList();
//        System.out.println("Initial size of al: " + al.size());
//
//        // add elements to the array list
//        al.add("Caaaa");
//        al.add("CCa");
//        al.add("Eaa");
//        al.add("Baa");
//        al.add("Daa");
//        al.add("Faa");
//        
//    	TextFields.bindAutoCompletion(inputText,al);
//    	TextFields.createClearableTextField();
//    	inputText.setOnKeyPressed((KeyEvent e)->{
//    		switch(e.getCode()) {
//    			case ENTER:
//    				System.out.println("hi");
//    				break;
//    			default:
//    				System.out.println("hiaaaaaaaaa");
//    				break;
//    		}
//    		
//    	});
    	//ArrayList<String> test = new ArrayList<String>();
//    	AutoCompletionTextFieldBinding<String> autoTF = new AutoCompletionTextFieldBinding(inputText, new Callback<AutoCompletionBinding.ISuggestionRequest, ArrayList<String>>(){
//
//			@Override
//			public ArrayList<String> call(ISuggestionRequest param) {
//				// TODO Auto-generated method stub
//				return null;
//			}
//    		
//    	});
    	
    	//autoTF.buildEventDispatchChain(null);
    	//EventHandler
  
//    	//EventHandler.create(ActionListener.class, autoTF, "doActionEvent", "");
//    	autoTF.addEventHandler(null, new EventHandler(){
//
//			@Override
//			public void handle(Event event) {
//				// TODO Auto-generated method stub
//				System.out.println("hi33");
//			}
//    		
//    	});
    	
//    	inputText.textProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//            	System.out.println("hissss");
//                //statusBarLabel.setText("状态：当前字符数为：" + textField.getText().length());
//            }
//        });

//    	inputText = TextFields.createClearablePasswordField();
//    	AnchorPane.setBottomAnchor(inputText, 20d);
//    	AnchorPane.setLeftAnchor(inputText, 20d);
    	AutoCompletionTextFieldBinding<String> autoTF = new AutoCompletionTextFieldBinding(inputText, new Callback<AutoCompletionBinding.ISuggestionRequest, ArrayList<String>>() {
    	    @Override
    	    public ArrayList<String> call(AutoCompletionBinding.ISuggestionRequest param) {
    	    	
    	    	aa = new ArrayList<>();
    	    	String inputString = param.getUserText();
    	    	
    	    	inputTextString = inputString;
    	    	
    	    	
    	    	inputString = inputString.replace("*", ".*");
    	    	//System.out.println(inputString);
    	    	if(inputString.length() != 0 && inputString != null) {
        	    	
    	    		if(englishBut.selectedProperty().getValue())
	        	    	for(String item :dictionaryEnglishList) {
	        	    		if(item.matches("^"+inputString+".*$")){
	        	    			//System.out.println(item);
	        	    			//if(firstItem.length() == 0)firstItem = item;
	        	    			aa.add(item);
	        	    		}
	        	    			
	        	    	}
    	    		else
            	    	for(String item :dictionaryCayugaList) {
            	    		//System.out.println(item + "          " +inputString);
            	    		if(item.matches("^"+inputString+".*$")){
            	    			//System.out.println(item);
            	    			//if(firstItem.length() == 0)firstItem = item;
            	    			aa.add(item);
            	    		}
            	    			
            	    	}
    	    		//System.out.println("change");
    	    		Collections.sort(aa);
    	    		return aa;	
    	    	} else {
    	    		return aa;
    	    	}   	
    	    }
    	});

    	autoTF.setMinWidth(457);
//    	autoTF.setUserInput("why");
//    	//autoTF.
//    	
//    	inputText.setOnKeyPressed((KeyEvent e)->{
//		switch(e.getCode()) {
//			case ENTER:
//				System.out.println("hifff");
//				break;
//			default:
//				System.out.println("hiffdddddf");
//				break;
//		}
//		
//	});
    	
//    	mainScene.setOnKeyPressed((KeyEvent e)->{
//		switch(e.getCode()) {
//			case ENTER:
//				System.out.println("qinong qi");
//				break;
//			default:
//				System.out.println("hiffdqinong qiddddf");
//				break;
//		}
//		
//	});
    	
    	//autoTF.buildEventDispatchChain(null);
    //	autoTF.setHideOnEscape(true);
    	
	  	autoTF.setOnAutoCompleted(new EventHandler(){
	
				@Override
				public void handle(Event event) {
					// TODO Auto-generated method stub
					System.out.println(inputText.getText());
					//inputText.setText("dddddddddd");
					firstItem = aa.get(0);
					System.out.println("firstItem ==="+firstItem);
					System.out.println(inputTextString);
					firstItem = aa.get(0);
					if(inputText.getText().equalsIgnoreCase(firstItem)){
						inputText.setText(inputTextString);
						firstItem = "";
						inputTextString = "";
						onEnter(null);
					}
				}
	    		
	    	});
    	
    	
      	//autoTF.re
      	//EventType h = EventType("ROOT");
      	//javafx.event.EventType.ROOT,
//    	autoTF.addEventHandler(javafx.event.EventType.ROOT, new EventHandler(){
//
//			@Override
//			public void handle(Event event) {
//				// TODO Auto-generated method stub
//				//event.getEventType()
//				//System.out.println(event.getEventType());
//				//System.out.println(inputText.getText());
////	    		switch(e.getCode()) {
////    			case ENTER:
//   				System.out.println("hi---------------");
////    				break;
////    			default:
////    				break;
////    		}
////				
////				System.out.println("hi33");
//			}
//    		
//    	});
//    	
//    	autoTF.setUserInput("dddd");
//    	
//    	autoTF.addEventHandler(KeyEvent.KEY_TYPED, new EventHandler(){
//
//			@Override
//			public void handle(Event event) {
//				// TODO Auto-generated method stub
//				//event.getEventType()
//				System.out.println("1111");
//				System.out.println(event.getEventType());
////	    		switch(e.getCode()) {
////    			case ENTER:
////    				System.out.println("hi");
////    				break;
////    			default:
////    				break;
////    		}
////				
////				System.out.println("hi33");
//			}
//    		
//    	});
    	
    	
    	
    	
    	

      dictionaryTable.setRowFactory( tv -> {
      TableRow<Dictionary> row = new TableRow<>();
      row.setOnMouseClicked(event -> {
    	  
    	  if(event.getClickCount() == 1 && (! row.isEmpty()) ) {
    		  
    		  logoImage.setVisible(false);
    		  showButton.setVisible(true);
    		  deleteButton.setVisible(true);		  
//    		  Dictionary rowData = row.getItem();
    		  
    		  //double click the row, show the dialog
    	  }else if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
        	 
    		  showAction(null);
//    		  	Dictionary tempData = dictionaryTable.getSelectionModel().getSelectedItem();
//    		 	   
//    		    if (tempData !=null) {
//    		    		    	
//    	    	   boolean okClicked = mainApp.showAddItemDialog(tempData);
//    	           if (okClicked) {
//    	               mainApp.getData().add(tempData);
//    	           } 
//    		    	
//    		    } else {
//    		        // Nothing selected.
//    		    	Alert alert = new Alert(AlertType.INFORMATION);
//    		    	alert.setTitle("Information Dialog");
//    		    	alert.setHeaderText(null);
//    		    	alert.setContentText("Please select one row !");
//    		    	alert.showAndWait();
//    		    }
    		  
          }
         
      });
      return row ;
      });
    	
    }
        
    private void initialButtons() {
    	
    	final ToggleGroup group = new ToggleGroup();
      	englishBut.setToggleGroup(group);
    	englishBut.setSelected(true);
    	cayugaBut.setToggleGroup(group);
    	relatedWordBut.setVisible(false);
    	final ToggleGroup group2 = new ToggleGroup();
    	related_1.setToggleGroup(group2);
    	related_2.setToggleGroup(group2);
    	related_3.setToggleGroup(group2);
    	related_1.setVisible(false);
    	related_2.setVisible(false);
    	related_3.setVisible(false);
    	textBut_1.setVisible(false);
    	textBut_2.setVisible(false);
    	textBut_3.setVisible(false);
    	textBut_4.setVisible(false);
    	
    	group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
    		
    	    public void changed(ObservableValue<? extends Toggle> ov,
    	        Toggle old_toggle, Toggle new_toggle) {
    	    	
    	            if (group.getSelectedToggle() != null) {
    	            	if(englishBut.selectedProperty().getValue()) {
    	                	textBut_1.setVisible(false);
    	                	textBut_2.setVisible(false);
    	                	textBut_3.setVisible(false);
    	                	textBut_4.setVisible(false);
    	                	
    	                	relatedWordBut.setVisible(false);
    	                	related_1.setVisible(false);
    	                	related_2.setVisible(false);
    	                	related_3.setVisible(false);
    	                	
    	                	
    	                	mainApp.getData().clear();
    	                	inputText.clear();
    	                	
    	                	  logoImage.setVisible(true);
    	            		  showButton.setVisible(false);
    	            		  deleteButton.setVisible(false);
    	                	
    	            	} else {
    	            		inputText.clear();
		            		  logoImage.setVisible(true);
		            		  showButton.setVisible(false);
		            		  deleteButton.setVisible(false);
		            		  
		            		  relatedWordBut.setVisible(true);
		            		related_1.setVisible(false);
		            	    	related_2.setVisible(false);
		            	    	related_3.setVisible(false);
		            		  
		            		  
    	                	textBut_1.setVisible(true);
    	                	textBut_2.setVisible(true);
    	                	textBut_3.setVisible(true);
    	                	textBut_4.setVisible(true);
    	                	mainApp.getData().clear();
    	            	}
    	            }                
    	        }
    	});
    	
    	
    	group2.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
    		
    	    public void changed(ObservableValue<? extends Toggle> ov,
    	        Toggle old_toggle, Toggle new_toggle) {
    	    	
    	    	
	    	    	relatedWordBut.requestFocus();
		            if (group2.getSelectedToggle() != null) {
		            	mainApp.getData().clear();
		            	String currentInput;
		            	if(related_1.selectedProperty().getValue()) {
		            		currentInput = related_1.getText();
		                	
		            	} else if (related_2.selectedProperty().getValue()) {
		            		currentInput = related_2.getText();
		            	}
		            	
		            	else {
		            		currentInput = related_3.getText();
		            	}
		            	
		        		for(int i=0; i<dictionaryCayugaList.size();i++) {
		    			
			    			if(dictionaryCayugaList.get(i).toString().matches(".*"+currentInput+".*")) {
			    				//System.out.println("coming in update dictionaryCayugaList");
			    				Dictionary tempData = new Dictionary();
			    				tempData.setSecondcol(dictionaryEnglishList.get(i));
			    				tempData.setThirdcol(dictionaryCayugaList.get(i));
			    				mainApp.getData().add(tempData);
			    			}
		        		}
		            }                
	    	   }
    	});
    	
  
    
    	
    }
    
    private void initialArrayList() {
    	
    	String file = Constant.dictionaryPath;
		FileInputStream fstream;
		dictionaryCayugaList.clear();
		dictionaryEnglishList.clear();
		try {
			
			
			fstream = new FileInputStream(file);
			Reader chars = new InputStreamReader(fstream, StandardCharsets.UTF_16);
			BufferedReader br = new BufferedReader(chars);
			String strLine;
			
			//Read File Line By Line
			while ((strLine = br.readLine()) != null && !strLine.trim().isEmpty())   {
				
				String[] outstring = strLine.split("     ");
				dictionaryCayugaList.add(outstring[0].replaceAll("\\p{C}", "").trim());
				dictionaryEnglishList.add(outstring[1]);
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
    }
    
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        // Add observable list data to the table
        dictionaryTable.setItems(mainApp.getData());
    }
    
    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewData() {
    	Dictionary tempData = new Dictionary();
        boolean okClicked = mainApp.showAddItemDialog(tempData);
        if (okClicked) {
        	dictionaryCayugaList.add(tempData.getThirdcol());
        	dictionaryEnglishList.add(tempData.getSecondcol());
            mainApp.getData().add(tempData);
        }     
    }
    
    @FXML
    public void onEnter(ActionEvent ae){
    	
    	searchBut.requestFocus();
    	searchButtonAction();
    	return;
    	
//      mainApp.getData().clear();
//    	related_1.setVisible(false);
//    	related_2.setVisible(false);
//    	related_3.setVisible(false);
//    	
//      searchBut.requestFocus();
//      String currentInput = inputText.getText();
//      if(currentInput.length() == 0) return;
//		if(englishBut.selectedProperty().getValue()) {
//
//			for(int i=0; i<dictionaryEnglishList.size();i++) {
//			
//				String words = dictionaryEnglishList.get(i).toString();
//		
//				String matches = "^"+currentInput+".*";
//				
//				StringBuilder words_sb = new StringBuilder(words);
//				for(int a=0; a<words_sb.length();a++) {
//					
//					if(words_sb.charAt(a)==')'||words_sb.charAt(a)=='(') words_sb.deleteCharAt(a);
//					
//				}
//				words = words_sb.toString();
//				
//				StringBuilder sb = new StringBuilder(matches);
//				for(int a=0; a<sb.length();a++) {
//					
//					if(sb.charAt(a)==')'||sb.charAt(a)=='(') sb.deleteCharAt(a);	
//				}		
//				matches = sb.toString();				
//				if(words.matches(matches)) {
//
//					Dictionary tempData = new Dictionary();
//					tempData.setSecondcol(dictionaryEnglishList.get(i));
//					tempData.setThirdcol(dictionaryCayugaList.get(i));
//					mainApp.getData().add(tempData);
//				}
//			}	
//		} else {
//			
//			for(int i=0; i<dictionaryCayugaList.size();i++) {
//				
//				if(dictionaryCayugaList.get(i).toString().matches("^"+currentInput+".*")) {
//
//					Dictionary tempData = new Dictionary();
//					tempData.setSecondcol(dictionaryEnglishList.get(i));
//					tempData.setThirdcol(dictionaryCayugaList.get(i));
//					mainApp.getData().add(tempData);
//				}
//			}	
//		}	
    }
    
    public void showAction(ActionEvent ae) {
    	
   
    	
    	Dictionary tempData = dictionaryTable.getSelectionModel().getSelectedItem();

 	   
	    if (tempData !=null) {
	    		   
	    	String english = tempData.getSecondcol();
	    	String cayuga = tempData.getThirdcol();
	    	Dictionary old = mainApp.showUpdateItemDialog(tempData);
	    		   
               for(int j=0; j<dictionaryEnglishList.size();j++) {
            	   //System.out.println(dictionaryEnglishList.get(j));
            	   if(dictionaryEnglishList.get(j).toString().equals(english) 
            			   &&dictionaryCayugaList.get(j).toString().equals(cayuga))  {

            		   dictionaryEnglishList.set(j, tempData.getSecondcol());
            		   dictionaryCayugaList.set(j, tempData.getThirdcol());
            		   
            	   }      	   
               }
	    } else {
	        // Nothing selected.
	    	Alert alert = new Alert(AlertType.INFORMATION);
	    	alert.setTitle("Information Dialog");
	    	alert.setHeaderText(null);
	    	alert.setContentText("Please select one row !");
	    	alert.showAndWait();
	    }
    }
    
    public void deleteAction(ActionEvent ae) {
    	
    	   int selectedIndex = dictionaryTable.getSelectionModel().getSelectedIndex();
    	   Dictionary tempData = dictionaryTable.getSelectionModel().getSelectedItem();
    	   
    	    if (selectedIndex >= 0) {
    	    	
    	 
    	    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	    	alert.setTitle("Confirmation Dialog");
    	    	alert.setHeaderText("Delete");
    	    	alert.setContentText("Are you sure to delete this row from the database?");

    	    	Optional<ButtonType> result = alert.showAndWait();
    	    	if (result.get() == ButtonType.OK){
    
    	    		RemoveLine rl = new RemoveLine();
    	        	rl.removeLine(tempData.getThirdcol(),tempData.getSecondcol());
    	    		
    	    		dictionaryTable.getItems().remove(selectedIndex); 
    	    		
	               for(int j=0; j<dictionaryEnglishList.size();j++) {
	            	   
	            	   if(dictionaryEnglishList.get(j).toString().equals(tempData.getSecondcol()) 
	            			   &&dictionaryCayugaList.get(j).toString().equals(tempData.getThirdcol()))  {
	            		   
	            		 //System.out.println("coming in update dictionaryEnglishList");
	            		 dictionaryEnglishList.remove(j);
	            		 dictionaryCayugaList.remove(j);    	            		   
	            	   }   
	               }  	    		
    	    	} else {
	
    	    	}
    	    	
    	    } else {
    	        // Nothing selected.
    	    	Alert alert = new Alert(AlertType.INFORMATION);
    	    	alert.setTitle("Information Dialog");
    	    	alert.setHeaderText(null);
    	    	alert.setContentText("Please select one row !");
    	    	alert.showAndWait();
    	    }
    	
    }
    
           
    @FXML
    public void MouseMoveText() {
    	mainScene.requestFocus();
    }
    
    @FXML
    public void textButAction_1(ActionEvent ae){
    	inputText.requestFocus();
    	String currentString = inputText.getText();
    	inputText.setText(currentString+"ˀ");
    	inputText.positionCaret(currentString.length()+1);
    }
    @FXML
    public void textButAction_2(ActionEvent ae){
    	inputText.requestFocus();
    	String currentString = inputText.getText();
    	inputText.setText(currentString+"ǫ");
    	inputText.positionCaret(currentString.length()+1);
    }
    @FXML
    public void textButAction_3(ActionEvent ae){
    	inputText.requestFocus();
    	String currentString = inputText.getText();
    	inputText.setText(currentString+":");
    	inputText.positionCaret(currentString.length()+1);
    }
    @FXML
    public void textButAction_4(ActionEvent ae){
    	inputText.requestFocus();
    	String currentString = inputText.getText();
    	inputText.setText(currentString+"ę");
    	inputText.positionCaret(currentString.length()+1);
    }
    
    @FXML
    public void searchButtonAction() {	
    	//onEnter(null);
    	
    	mainApp.getData().clear();
    	related_1.setVisible(false);
    	related_2.setVisible(false);
    	related_3.setVisible(false);
    	
    	
       	String currentInput = inputText.getText();
       	if(currentInput.length() == 0) return;
		if(englishBut.selectedProperty().getValue()) {

			for(int i=0; i<dictionaryEnglishList.size();i++) {
			
				String words = dictionaryEnglishList.get(i).toString();
		
				
				
				StringBuilder words_sb = new StringBuilder(words);
				for(int a=0; a<words_sb.length();a++) {
					
					if(words_sb.charAt(a)==')'||words_sb.charAt(a)=='(') words_sb.deleteCharAt(a);
					
				}
				words = words_sb.toString();
				words = " "+words+" ";
				
				
				StringBuilder sb = new StringBuilder(currentInput);
				for(int a=0; a<sb.length();a++) {
					
					if(sb.charAt(a)==')'||sb.charAt(a)=='(') sb.deleteCharAt(a);					
				}
				String matches = sb.toString();
				matches = createRegularExpression(matches);
				
				if(words.matches(matches)) {

					Dictionary tempData = new Dictionary();
					tempData.setSecondcol(dictionaryEnglishList.get(i));
					tempData.setThirdcol(dictionaryCayugaList.get(i));
					mainApp.getData().add(tempData);
				}
			}	
		} else {
			
			for(int i=0; i<dictionaryCayugaList.size();i++) {
				
				String words = dictionaryCayugaList.get(i).toString();
				StringBuilder words_sb = new StringBuilder(words);
				for(int a=0; a<words_sb.length();a++) {
					
					if(words_sb.charAt(a)=='ˀ'||words_sb.charAt(a)==':') words_sb.deleteCharAt(a);
					
				}
				words = words_sb.toString();
				words = " "+words+" ";
				
				String matches = currentInput;
				
				StringBuilder sb = new StringBuilder(matches);
				for(int a=0; a<sb.length();a++) {
					
					if(sb.charAt(a)=='ˀ'||sb.charAt(a)==':') sb.deleteCharAt(a);
					
				}
				matches = sb.toString();
				matches = createRegularExpression(matches);

				
				if(words.toString().matches(matches)) {
					//System.out.println("coming in update dictionaryCayugaList");
					Dictionary tempData = new Dictionary();
					tempData.setSecondcol(dictionaryEnglishList.get(i));
					tempData.setThirdcol(dictionaryCayugaList.get(i));
					mainApp.getData().add(tempData);
				}
			}
			
		}
    }
    
    public String createRegularExpression(String input) {
    	
		String matches = input;
		
		StringBuilder sb = new StringBuilder(matches);
		int begin = 0;
		int mid = 0;
		int end = 0;
		for(int a=0; a<sb.length();a++) {
			
			if(sb.charAt(a)=='*'&& a == 0)	{
				begin = 1;
				sb.deleteCharAt(a);
			} else if(sb.charAt(a)=='*'&& a == sb.length() -1 ){
				end = 1;
				sb.deleteCharAt(a);
			} else if(sb.charAt(a)=='*'&& a!= 0 && a!= sb.length() -1)	{
				
				sb.deleteCharAt(a);
				sb.insert(a, ".");
				mid = 1;
			}
		}
		matches = sb.toString();

		matches = matches.replace(".", ".*");

		if(begin == 0 && mid == 0 && end == 0) {
			matches = ".* "+matches+" .*";
		} else if (begin == 1 && mid == 0 && end == 0) {
			matches = " .*"+matches+" .*";
		} else if (begin == 0 && mid == 1 && end == 0) {
			matches = ".* "+matches+" .*";
		} else if (begin == 0 && mid == 0 && end == 1) {
			matches = ".* "+matches+".* ";
		} else if (begin == 1 && mid == 1 && end == 0) {
			matches = " .*"+matches+" .*";
		} else if (begin == 0 && mid == 1 && end == 1) {
			matches = ".* "+matches+".* ";
		} else if (begin == 1 && mid == 0 && end == 1) {
			matches = " .*"+matches+".* ";
		} else if (begin == 1 && mid == 1 && end == 1) {
			matches = " .*"+matches+".* ";
		}
		
//		if(sb.charAt(0)=='*') {
//			
//			sb.deleteCharAt(0);
//			sb.insert(0, " .*");
//			matches = sb.toString();
//			matches = matches +" .*";	
//			
//		} else if(sb.charAt(sb.length()-1)=='*') {
//			
//			sb.deleteCharAt(sb.length()-1);
//			sb.insert(0, ".* ");
//			matches = sb.toString();
//			matches = " .*"+matches;	
//			
//		}
//		
//		else {
//			matches = sb.toString();
//			matches = ".* "+matches +" .*";	
//		}
//		
		
		
//		matches = sb.toString();
//		matches = " .*"+matches +" .*";	
		//matches = ".*"+matches+".*";
		//System.out.println(sb);
		//System.out.println(matches);

    	
    	
    	return matches;
    }
    
    @FXML
    public void partialSearch(ActionEvent ae) {
    	
    	mainApp.getData().clear();
    	related_1.setVisible(false);
    	related_2.setVisible(false);
    	related_3.setVisible(false);
    	
    	
       	String currentInput = inputText.getText();
       	if(currentInput.length() == 0) return;
		if(englishBut.selectedProperty().getValue()) {

			for(int i=0; i<dictionaryEnglishList.size();i++) {
			
				String words = dictionaryEnglishList.get(i).toString();
		
				String matches = currentInput;
				
				StringBuilder words_sb = new StringBuilder(words);
				for(int a=0; a<words_sb.length();a++) {
					
					if(words_sb.charAt(a)==')'||words_sb.charAt(a)=='(') words_sb.deleteCharAt(a);
					
				}
				
				words = words_sb.toString();
				words = " "+words+" ";
				
				StringBuilder sb = new StringBuilder(matches);
				for(int a=0; a<sb.length();a++) {
					
					if(sb.charAt(a)==')'||sb.charAt(a)=='(') sb.deleteCharAt(a);
					
				}
					
				matches = sb.toString();
				matches = " "+matches +" ";	
				matches = ".*"+matches+".*";
				System.out.println(matches);
				if(words.matches(matches)) {

					Dictionary tempData = new Dictionary();
					tempData.setSecondcol(dictionaryEnglishList.get(i));
					tempData.setThirdcol(dictionaryCayugaList.get(i));
					mainApp.getData().add(tempData);
				}
			}	
		} else {
			
			for(int i=0; i<dictionaryCayugaList.size();i++) {
				String words = dictionaryCayugaList.get(i).toString();
				words = " "+words+" ";
				currentInput = " "+currentInput +" ";
				if(words.matches(".*"+currentInput+".*")) {
					//System.out.println("coming in update dictionaryCayugaList");
					Dictionary tempData = new Dictionary();
					tempData.setSecondcol(dictionaryEnglishList.get(i));
					tempData.setThirdcol(dictionaryCayugaList.get(i));
					mainApp.getData().add(tempData);
				}
			}
			
		}
    	
    }
      
    public void relatedWord(ActionEvent ae) {
    	
    	String currentInput = inputText.getText();
       	if(currentInput.length() < 3) partialSearch(null);
       	int total = 0;
    	int length = currentInput.length();
       	int subStringLength = length-1;
       	ArrayList<String> newList = new ArrayList<>(dictionaryCayugaList);
       	
       	for(int i =subStringLength; i>=2; i--) {
       		if(total >=3) break;
       		
       		for(int j=0; j+i<=length; j++) {
       			
       			if(total >=3) break;
       			
       			String sub  = currentInput.substring(length-j-i,length-j);		
       			for(int d=0; d<newList.size();d++) {
    			
	    			if(newList.get(d).toString().matches(".*"+sub+".*") && !newList.get(d).toString().matches(".*"+currentInput+".*")) {
	    				
	    				if(total == 0) {
	    					newList.remove(d);
	    					related_1.setText(sub);
	    					related_1.setVisible(true);
	    					total++;
	    					break;
	    				}
	    				else if(total == 1) {
	    					newList.remove(d);
	    					related_2.setText(sub);
	    					related_2.setVisible(true);
	    					total++;
	    					break;
	    				}
	    				else if(total == 2) {
	    					newList.remove(d);
	    					related_3.setText(sub);
	    					related_3.setVisible(true);
	    					total++;
	    					break;
	    				} else {
	    					
	    					break;
	    				}		
	    			}
       			}
       			
       		}       		
       	}

    }
    
    public void exportDictionary() {
    	System.out.println("export");
        FileChooser fileChooser = new FileChooser();
        
        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*cayuga-english.txt)", "*cayuga-english.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialFileName("cayuga-english");
        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());
        
        if(file != null){
        	new ExportDictionary(file);    
        }     
    }
    
    public void importDictionary() {
    	System.out.println("import");
    	FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "TXT files (cayuga-english.txt)", "cayuga-english.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

        if (file != null) {
            new ImportDictionary(file);
            initialArrayList();
        }
    }
}
