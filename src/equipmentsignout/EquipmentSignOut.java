/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package equipmentsignout;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

//Hereford Engineering Equipment Usage and Signout

/* 
 * @author DSW029
 */
public class EquipmentSignOut extends Application {

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());
        
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        
        Button btn = new Button();
        btn.setText("Sign out equipment");
        btn.setTranslateX(0);
        btn.setTranslateY(0);
        
        Button btn2 = new Button();
        btn2.setText("Manage equipment usage");
        btn2.setTranslateY(80);
        
        Label hhs = new Label("Hereford Engineering Equipment Usage and Signout");
        hhs.setId("hhs");
        hhs.setTranslateY(-300);
        root.getChildren().add(hhs);
        
        //adds the bull image to the first screen
        try
        {
            ImageView bullImage = new ImageView();
            Image bull = new Image(new FileInputStream("L:\\TechEd-Engineering\\Equipment Sign Out\\EquipmentSignOut\\src\\equipmentsignout\\bull3.png"));
            bullImage.setImage(bull);
            bullImage.setTranslateY(-160);
            root.getChildren().add(bullImage);
        }
        catch(FileNotFoundException e)
        {
            System.out.print("image not found");
        }
        
        //checks to see who the user is to give them access to the manage equipment button
        if(System.getProperty("user.name").toUpperCase().equals("DSW029")||System.getProperty("user.name").toUpperCase().equals("WFELLOWS")||System.getProperty("user.name").toUpperCase().equals("MDODDO"))
        {
            root.getChildren().add(btn2);
        }    
         
        Scene scene = new Scene(root, width, height);
        
        //manage equipment button
        btn2.setOnAction((ActionEvent event) -> {
            StackPane soe = new StackPane();
            
            Button back = new Button("Back");
            back.setTranslateX(-180);
            back.setTranslateY(250);
            
            Label manageEquip = new Label("Manage Equipment");
            
            //begin to construct the table
            TableView table = new TableView();
            
            //constructs the columns
            TableColumn nameColumn = new TableColumn("Name");
            nameColumn.setMinWidth(100);
            nameColumn.setCellValueFactory(new PropertyValueFactory<Equipment, String>("name"));
            nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            nameColumn.setOnEditCommit(                //makes the column editable
                    new EventHandler<CellEditEvent<Equipment, String>>() {
                        @Override
                        public void handle(CellEditEvent<Equipment, String> t) {
                            ((Equipment) t.getTableView().getItems().get(
                                    t.getTablePosition().getRow())
                                    ).setName(t.getNewValue());
                            Equipment test = new Equipment();
                            test.changeData(t.getNewValue(),t.getTablePosition().getRow(),0);
                            System.out.print("\n"+t.getTablePosition().getRow()+"\n"+t.getNewValue());
                        }
                    }
            );
            
            TableColumn equip = new TableColumn("Equipment");
            equip.setMinWidth(100);
            equip.setCellValueFactory(new PropertyValueFactory<Equipment, String>("type"));
            equip.setCellFactory(TextFieldTableCell.forTableColumn());
            equip.setOnEditCommit(
                    new EventHandler<CellEditEvent<Equipment, String>>() {
                        @Override
                        public void handle(CellEditEvent<Equipment, String> t) {
                            ((Equipment) t.getTableView().getItems().get(
                                    t.getTablePosition().getRow())
                                    ).setName(t.getNewValue());
                            Equipment test = new Equipment();
                            test.changeData(t.getNewValue(),t.getTablePosition().getRow(),2);
                            System.out.print("\n"+t.getTablePosition().getRow()+"\n"+t.getNewValue());
                        }
                    }
            );
            
            TableColumn dateOut = new TableColumn("Date Signed Out");
            dateOut.setMinWidth(150);
            dateOut.setCellValueFactory(new PropertyValueFactory<Equipment, String>("date"));
            dateOut.setCellFactory(TextFieldTableCell.forTableColumn());
            dateOut.setOnEditCommit(
                    new EventHandler<CellEditEvent<Equipment, String>>() {
                        @Override
                        public void handle(CellEditEvent<Equipment, String> t) {
                            ((Equipment) t.getTableView().getItems().get(
                                    t.getTablePosition().getRow())
                                    ).setName(t.getNewValue());
                            Equipment test = new Equipment();
                            test.changeData(t.getNewValue(),t.getTablePosition().getRow(),1);
                            System.out.print("\n"+t.getTablePosition().getRow()+"\n"+t.getNewValue());
                        }
                    }
            );
            
            TableColumn purpose = new TableColumn("Purpose");
            purpose.setMinWidth(100);
            purpose.setCellValueFactory(new PropertyValueFactory<Equipment, String>("purpose"));
            purpose.setCellFactory(TextFieldTableCell.forTableColumn());
            purpose.setOnEditCommit(
                    new EventHandler<CellEditEvent<Equipment, String>>() {
                        @Override
                        public void handle(CellEditEvent<Equipment, String> t) {
                            ((Equipment) t.getTableView().getItems().get(
                                    t.getTablePosition().getRow())
                                    ).setName(t.getNewValue());
                            Equipment test = new Equipment();
                            test.changeData(t.getNewValue(),t.getTablePosition().getRow(),3);
                            System.out.print("\n"+t.getTablePosition().getRow()+"\n"+t.getNewValue());
                        }
                    }
            );
            
            TableColumn usage = new TableColumn("Usage");
            usage.setMinWidth(100);
            usage.setCellValueFactory(new PropertyValueFactory<Equipment, String>("usage"));
            usage.setCellFactory(TextFieldTableCell.forTableColumn());
            usage.setOnEditCommit(
                    new EventHandler<CellEditEvent<Equipment, String>>() {
                        @Override
                        public void handle(CellEditEvent<Equipment, String> t) {
                            ((Equipment) t.getTableView().getItems().get(
                                    t.getTablePosition().getRow())
                                    ).setName(t.getNewValue());
                            Equipment test = new Equipment();
                            test.changeData(t.getNewValue(),t.getTablePosition().getRow(),4);
                            System.out.print("\n"+t.getTablePosition().getRow()+"\n"+t.getNewValue());
                        }
                    }
            );
            
            TableColumn time = new TableColumn("Time Out");
            time.setMinWidth(100);
            time.setCellValueFactory(new PropertyValueFactory<Equipment, String>("otime"));
            time.setCellFactory(TextFieldTableCell.forTableColumn());
            time.setOnEditCommit(
                    new EventHandler<CellEditEvent<Equipment, String>>() {
                        @Override
                        public void handle(CellEditEvent<Equipment, String> t) {
                            ((Equipment) t.getTableView().getItems().get(
                                    t.getTablePosition().getRow())
                                    ).setName(t.getNewValue());
                            Equipment test = new Equipment();
                            test.changeData(t.getNewValue(),t.getTablePosition().getRow(),5);
                            System.out.print("\n"+t.getTablePosition().getRow()+"\n"+t.getNewValue());
                        }
                    }
            );
            
            TableColumn app = new TableColumn("Teacher Approved");
            app.setMinWidth(100);
            app.setCellValueFactory(new PropertyValueFactory<Equipment, String>("teachApp"));
            app.setCellFactory(TextFieldTableCell.forTableColumn());
            app.setOnEditCommit(
                    new EventHandler<CellEditEvent<Equipment, String>>() {
                        @Override
                        public void handle(CellEditEvent<Equipment, String> t) {
                            ((Equipment) t.getTableView().getItems().get(
                                    t.getTablePosition().getRow())
                                    ).setName(t.getNewValue());
                            Equipment test = new Equipment();
                            test.changeData(t.getNewValue(),t.getTablePosition().getRow(),6);
                            System.out.print("\n"+t.getTablePosition().getRow()+"\n"+t.getNewValue());
                        }
                    }
            );
            
            TableColumn inOut = new TableColumn("In/Out");
            inOut.setMinWidth(100);
            inOut.setCellValueFactory(new PropertyValueFactory<Equipment, String>("inout"));
            inOut.setCellFactory(TextFieldTableCell.forTableColumn());
            inOut.setOnEditCommit(
                    new EventHandler<CellEditEvent<Equipment, String>>() {
                        @Override
                        public void handle(CellEditEvent<Equipment, String> t) {
                            ((Equipment) t.getTableView().getItems().get(
                                    t.getTablePosition().getRow())
                                    ).setName(t.getNewValue());
                            Equipment test = new Equipment();
                            test.changeData(t.getNewValue(),t.getTablePosition().getRow(),7);
                            System.out.print("\n"+t.getTablePosition().getRow()+"\n"+t.getNewValue());
                        }
                    }
            );
            
            TableColumn itime = new TableColumn("Time In");
            itime.setMinWidth(100);
            itime.setCellValueFactory(new PropertyValueFactory<Equipment, String>("itime"));
            itime.setCellFactory(TextFieldTableCell.forTableColumn());
            itime.setOnEditCommit(
                    new EventHandler<CellEditEvent<Equipment, String>>() {
                        @Override
                        public void handle(CellEditEvent<Equipment, String> t) {
                            ((Equipment) t.getTableView().getItems().get(
                                    t.getTablePosition().getRow())
                                    ).setName(t.getNewValue());
                            Equipment test = new Equipment();
                            test.changeData(t.getNewValue(),t.getTablePosition().getRow(),8);
                            System.out.print("\n"+t.getTablePosition().getRow()+"\n"+t.getNewValue());
                        }
                    }
            );
            
            Equipment test = new Equipment();
            test.readEquip();
            ArrayList<Equipment> info = new ArrayList<Equipment>();
            info = test.getEquip();
            
            //adds the data to the table
            ObservableList<Equipment> data = FXCollections.observableArrayList();
            for(int i = 0; i < info.size(); i++)
            {
                data.add(info.get(i));
            }
            
            table.setItems(data);
            table.getColumns().addAll(nameColumn, equip, dateOut, purpose, usage, time, app, inOut, itime);
            table.setId("th");
            
            //adds text boxes to add to the data
            final TextField addName = new TextField();
            addName.setPromptText("Name");
            addName.setMaxWidth(nameColumn.getPrefWidth());
            final TextField addEquip = new TextField();
            addEquip.setMaxWidth(equip.getPrefWidth());
            addEquip.setPromptText("Equipment");
            final TextField addDate = new TextField();
            addDate.setMaxWidth(dateOut.getPrefWidth());
            addDate.setPromptText("Date");
            final TextField addPurpose = new TextField();
            addPurpose.setMaxWidth(purpose.getPrefWidth());
            addPurpose.setPromptText("Purpose");
            final TextField addUsage = new TextField();
            addUsage.setMaxWidth(purpose.getPrefWidth());
            addUsage.setPromptText("Usage");
            final TextField addTime = new TextField();
            addTime.setMaxWidth(dateOut.getPrefWidth());
            addTime.setPromptText("Time Out");
            final TextField addApp = new TextField();
            addApp.setMaxWidth(dateOut.getPrefWidth());
            addApp.setPromptText("Teacher Approved?");
            final TextField addInOut = new TextField();
            addInOut.setMaxWidth(dateOut.getPrefWidth());
            addInOut.setPromptText("In/Out");
            final TextField addItime = new TextField();
            addItime.setMaxWidth(dateOut.getPrefWidth());
            addItime.setPromptText("Time In");
            
            Label wrong = new Label("Please fill in all boxes correctly");
            wrong.setTranslateX(205);
            wrong.setTranslateY(90);
            wrong.setVisible(false);
            soe.getChildren().add(wrong);
            
            //adds the new data to the table when you hit the add button
            final Button addButton = new Button("Add");
            addButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    wrong.setVisible(false);
                    if(addName.getText()!=null&&addEquip.getText()!=null&&addDate.getText()!=null&&addPurpose.getText()!=null&&(addInOut.getText().toUpperCase().equals("IN")||addInOut.getText().toUpperCase().equals("OUT"))&&addUsage.getText()!=null&&addTime.getText()!=null&&(addApp.getText().toUpperCase().equals("YES")||addApp.getText().toUpperCase().equals("NO"))&&addItime.getText()!=null)
                    {
                        data.add(new Equipment(
                                addName.getText(),
                                addDate.getText(),
                                addEquip.getText(),
                                addPurpose.getText(),
                                addUsage.getText(),
                                addTime.getText(),
                                addApp.getText(),
                                addInOut.getText(),
                                addItime.getText()));
                        Equipment add = new Equipment(addName.getText(),addDate.getText(),addEquip.getText(),addPurpose.getText(), addUsage.getText(), addApp.getText(), addInOut.getText(), addItime.getText());
                        add.addDatas(addName.getText(), addDate.getText(), addEquip.getText(), addPurpose.getText(), addUsage.getText(), addTime.getText(), addApp.getText(), addInOut.getText(), addItime.getText());
                        addName.clear();
                        addEquip.clear();
                        addDate.clear();
                        addPurpose.clear();
                        addUsage.clear();
                        addTime.clear();
                        addApp.clear();
                        addInOut.clear();
                        addItime.clear();
                    }
                    else
                    {
                        wrong.setVisible(true);
                    }
                }
            });
            
            //adds the text boxes to an HBox
            HBox hb = new HBox();
            hb.getChildren().addAll(addName, addEquip, addDate, addPurpose, addUsage, addTime, addApp, addInOut, addItime, addButton);
            hb.setSpacing(3);
            hb.setTranslateY(480);
            hb.setTranslateX(width-1400);
            //hb.setTranslateX(-500);
            
            //adds the table to a VBox
            VBox vbox = new VBox();
            vbox.setSpacing(5);
            vbox.setPadding(new Insets(10, 0, 0, 10));
            vbox.getChildren().addAll(manageEquip, table);
            vbox.setMaxWidth(962);//762
            table.setEditable(true);
            
            soe.getChildren().addAll(vbox);
            soe.getChildren().addAll(hb);
            soe.getChildren().add(back);
            
            Scene scsoe = new Scene(soe, width, height);
            //Scene scsoe = new Scene(soe);
            //scsoe.setFullScreen(true);
            primaryStage.setScene(scsoe);
            scsoe.getStylesheets().add(EquipmentSignOut.class.getResource("GUIstyle.css").toExternalForm());
            primaryStage.show();
            
            back.setOnAction(e -> {
                primaryStage.setScene(scene);
            });
            
            Button print = new Button("Open in Excel");
            print.setTranslateX(215);
            print.setTranslateY(150);
            soe.getChildren().add(print);
            
            //opens the data file in excel
            print.setOnAction((ActionEvent event1) -> {
                try
                {
                    File file = new File("L:\\TechEd-Engineering\\Equipment Sign Out\\EquipmentSignOut\\dataFile.csv");
                    //File file = new File("\"\\\\HHS0772-FS1\\Studentshd\\DSW029\\dataFile.csv\"");
                    Desktop.getDesktop().open(file);            //opens the data file in excel
                }
                catch(IOException e)
                {
                    System.out.print("it no werk");
                }
            });
        });
        
        
        Button btn3 = new Button();
        btn3.setText("Sign in equipment");
        btn3.setTranslateX(0);
        btn3.setTranslateY(40);
        
        root.getChildren().add(btn);
        root.getChildren().add(btn3);
        
        
        scene.getStylesheets().add(EquipmentSignOut.class.getResource("GUIstyle.css").toExternalForm());
        primaryStage.show();
        
        Label GUItitle = new Label("What would you like to do?");
        GUItitle.setId("GUItitle");
        GUItitle.setTranslateY(-40);
        root.getChildren().add(GUItitle);
        
        primaryStage.setTitle("Equipment Usage");
        primaryStage.setScene(scene);
        
        
        //on action for the sign in button
        btn3.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                StackPane soe = new StackPane();
                
                Button back = new Button("Back");
                back.setTranslateX(-180);
                back.setTranslateY(250);
                soe.getChildren().add(back);
                
                Label in = new Label("Sign in Equipment");
                in.setTranslateY(-100);
                in.setId("in");
                soe.getChildren().add(in);
                
                back.setOnAction(new EventHandler<ActionEvent>() {
            
                    @Override
                    public void handle(ActionEvent event) {
                        primaryStage.setScene(scene);
                }
                });
                
                Label name = new Label("Name:");
                name.setTranslateX(-100);
                soe.getChildren().add(name);
                
                TextField userName = new TextField();
                userName.setTranslateX(0);
                userName.setMaxWidth(100);
                soe.getChildren().add(userName);
                
                Button enter = new Button("Enter");
                enter.setTranslateX(100);
                soe.getChildren().add(enter);
                
                Label notFound = new Label("You don't have any items signed out");
                notFound.setTranslateY(50);
                notFound.setVisible(false);
                soe.getChildren().add(notFound);
                
                Label success = new Label("Success");
                success.setTranslateY(150);
                success.setVisible(false);
                soe.getChildren().add(success);
                
                ComboBox<String> signedOut = new ComboBox<String>();
                signedOut.setEditable(false); 
                signedOut.setTranslateX(-60);
                signedOut.setTranslateY(50);
                signedOut.setVisible(false);
                soe.getChildren().add(signedOut);
                
                Button signIn = new Button("Sign In");
                signIn.setTranslateX(60);
                signIn.setTranslateY(52);
                signIn.setVisible(false);
                soe.getChildren().add(signIn);
                
                Scene scsoe = new Scene(soe,width,height);
                primaryStage.setScene(scsoe);
                scsoe.getStylesheets().add(EquipmentSignOut.class.getResource("GUIstyle.css").toExternalForm());
                primaryStage.show();  
                
                //on action when you hit the enter button
                enter.setOnAction(new EventHandler<ActionEvent>() {
            
                    @Override
                    public void handle(ActionEvent event) {
                        success.setVisible(false);
                        
                        String n = userName.getText();
                        
                        Equipment test  = new Equipment();
                        test.readEquip();
                        
                        signedOut.getItems().clear();
                        notFound.setVisible(false);
                        signedOut.setVisible(false);
                        signIn.setVisible(false);
                        
                        ArrayList<Equipment> equip = new ArrayList<>();
                        equip = test.getEquip();
                                
                        boolean b = false;
                        
                        //checks to see if the entered name has an item signed out
                        for(int i = 0; i < test.getEquip().size(); i++)
                        {
                            if(equip.get(i).getName().toUpperCase().equals(n.toUpperCase())&&equip.get(i).getInout().toUpperCase().equals("OUT"))
                            {
                                signedOut.getItems().addAll(equip.get(i).getType());
                                b = true;
                                System.out.println(equip.get(i).getSign());
                            }
                        }
                        if(b == true)
                        {
                            signedOut.setVisible(true);
                            signIn.setVisible(true);
                        }
                        else
                        {
                            notFound.setVisible(true);
                        }
                    }
                });
                
                //signs in the selected combobox item
                signIn.setOnAction(new EventHandler<ActionEvent>() {
            
                    @Override
                    public void handle(ActionEvent event) {
                        String type = signedOut.getValue();
                        String name = userName.getText();
                        
                        Equipment test  = new Equipment();
                        test.readEquip();
                        
                        ArrayList<Equipment> equip = new ArrayList<>();
                        equip = test.getEquip();
                        
                        test.signIn(name, type);
                        
                        success.setVisible(true);
                        signIn.setVisible(false);
                        signedOut.setVisible(false);
                }
                });    
            }
        });
        
        //sign out page
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                StackPane soe = new StackPane();
                
                Button back = new Button("Back");
                back.setTranslateX(-180);
                back.setTranslateY(240);
                soe.getChildren().add(back);
                
                Label out = new Label("Sign out Equipment");
                out.setTranslateY(-200);
                out.setId("out");
                soe.getChildren().add(out);
                
                Button btn = new Button("Enter");
                HBox hbBtn = new HBox(10);
                hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
                hbBtn.getChildren().add(btn);
                btn.setTranslateX(100);
                btn.setTranslateY(200);
                soe.getChildren().add(btn);
                
                Label name = new Label("Name:");
                name.setTranslateX(-152);
                name.setTranslateY(-100);
                soe.getChildren().add(name);

                TextField userTextField = new TextField();
                userTextField.setTranslateX(9);
                userTextField.setTranslateY(-100);
                userTextField.setMaxWidth(100);
                soe.getChildren().add(userTextField);
                
                Label date = new Label("Date:");
                date.setTranslateX(-158);
                date.setTranslateY(-62);
                soe.getChildren().add(date);
                
                Button today = new Button("Today");
                today.setTranslateY(-62);
                today.setTranslateX(180);
                
                ComboBox<String> month = new ComboBox<String>();
                month.getItems().addAll("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
                month.setEditable(false); 
                month.setTranslateX(10);
                month.setTranslateY(-62);
                soe.getChildren().add(month);  
                
                TextField day = new TextField();
                day.setMinWidth(40);
                day.setMaxWidth(40);
                day.setTranslateX(100);
                day.setTranslateY(-62);
                soe.getChildren().add(day);
                
                //sets combobox to current date
                today.setOnAction(new EventHandler<ActionEvent>() {
            
                    @Override
                    public void handle(ActionEvent event) {
                        Equipment test = new Equipment();
                        day.setText(test.date().substring(3));
                        if(test.date().substring(0,2).equals("01"))
                        {
                            month.setValue("January");
                        }
                        if(test.date().substring(0,2).equals("02"))
                        {
                            month.setValue("February");
                        }
                        if(test.date().substring(0,2).equals("03"))
                        {
                            month.setValue("March");
                        }
                        if(test.date().substring(0,2).equals("04"))
                        {
                            month.setValue("April");
                        }
                        if(test.date().substring(0,2).equals("05"))
                        {
                            month.setValue("May");
                        }
                        if(test.date().substring(0,2).equals("06"))
                        {
                            month.setValue("June");
                        }
                        if(test.date().substring(0,2).equals("07"))
                        {
                            month.setValue("July");
                        }
                        if(test.date().substring(0,2).equals("08"))
                        {
                            month.setValue("August");
                        }
                        if(test.date().substring(0,2).equals("09"))
                        {
                            month.setValue("September");
                        }
                        if(test.date().substring(0,2).equals("10"))
                        {
                            month.setValue("October");
                        }
                        if(test.date().substring(0,2).equals("11"))
                        {
                            month.setValue("November");
                        }
                        if(test.date().substring(0,2).equals("12"))
                        {
                            month.setValue("December");
                        }      
                }
                });
                
                soe.getChildren().add(today);
                
                Label equip = new Label("Equipment:");
                equip.setTranslateX(-135);
                equip.setTranslateY(-25);
                soe.getChildren().add(equip);
                
                ComboBox<String> equipBox = new ComboBox<String>();
                equipBox.getItems().addAll("Camera","Laser Engraver","3D Printer"); 
                equipBox.setTranslateX(45);//20
                equipBox.setTranslateY(-25);
                equipBox.setEditable(true);
                soe.getChildren().add(equipBox);
                
                Label purpose = new Label("Purpose:");
                purpose.setTranslateX(-146);
                purpose.setTranslateY(12);
                soe.getChildren().add(purpose);
                
                TextField purposeText = new TextField();
                purposeText.setTranslateX(9);
                purposeText.setTranslateY(12);
                purposeText.setMaxWidth(100);
                soe.getChildren().add(purposeText);
                
                Label usage = new Label("Material Usage:");
                usage.setTranslateX(-120);
                usage.setTranslateY(49);
                soe.getChildren().add(usage);
                
                TextField usageText = new TextField();
                usageText.setTranslateX(9);
                usageText.setTranslateY(49);
                usageText.setMaxWidth(100);
                soe.getChildren().add(usageText);
                
                Label from = new Label("Time Out:");
                from.setTranslateX(-142);
                from.setTranslateY(86);
                soe.getChildren().add(from);
                
                ComboBox<String> hrsBox = new ComboBox<String>();
                hrsBox.getItems().addAll("1","2","3","4","5","6","7","8","9","10","11","12");
                hrsBox.setEditable(false); 
                hrsBox.setTranslateX(-12);
                hrsBox.setTranslateY(86);
                soe.getChildren().add(hrsBox);
                
                Label colon = new Label(":");
                colon.setTranslateX(25);
                colon.setTranslateY(86);
                soe.getChildren().add(colon);
                
                ComboBox<String> minsBox = new ComboBox<String>();
                minsBox.getItems().addAll("00","05","10","15","20","25","30","35","40","45","50","55");
                minsBox.setEditable(false); 
                minsBox.setTranslateX(60);
                minsBox.setTranslateY(86);
                soe.getChildren().add(minsBox);
                
                ComboBox<String> ampmBox = new ComboBox<String>();
                ampmBox.getItems().addAll("AM", "PM");
                ampmBox.setEditable(false); 
                ampmBox.setTranslateX(140);
                ampmBox.setTranslateY(86);
                soe.getChildren().add(ampmBox);
                
                Button time = new Button("Current Time");
                time.setTranslateY(86);
                time.setTranslateX(250);
                soe.getChildren().add(time);
                
                //sets combobox to current time
                time.setOnAction((ActionEvent event1) -> {
                    Equipment test = new Equipment();
                    if(Integer.parseInt(test.time().substring(0,2))<=11)
                    {
                        hrsBox.setValue(test.time().substring(0,2));
                        minsBox.setValue(test.time().substring(3));
                        ampmBox.setValue("AM");
                    }
                    if(Integer.parseInt(test.time().substring(0,2))>12)
                    {
                        hrsBox.setValue(""+(-12+Integer.parseInt(test.time().substring(0,2))));
                        minsBox.setValue(test.time().substring(3));
                        ampmBox.setValue("PM");
                    }
                    if(Integer.parseInt(test.time().substring(0,2))==0)
                    {
                        hrsBox.setValue("12");
                        minsBox.setValue(test.time().substring(3));
                        ampmBox.setValue("AM");
                    }
                    if(Integer.parseInt(test.time().substring(0,2))==12)
                    {
                        hrsBox.setValue("12");
                        minsBox.setValue(test.time().substring(3));
                        ampmBox.setValue("PM");
                    }
                });
       
                Scene scsoe = new Scene(soe,width,height);
                primaryStage.setScene(scsoe);
                scsoe.getStylesheets().add(EquipmentSignOut.class.getResource("GUIstyle.css").toExternalForm());
                primaryStage.show();  
                
                //goes back to the first scene
                back.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        primaryStage.setScene(scene);
                    }
                });
                
                Label error = new Label("Please fill in all boxes correctly");
                error.setTranslateX(150);
                error.setTranslateY(275);
                error.setVisible(false);
                soe.getChildren().add(error);
                
                Label notAvail = new Label();
                notAvail.setTranslateX(130);
                notAvail.setTranslateY(275);
                notAvail.setVisible(false);
                soe.getChildren().add(notAvail);
                
                Label approve = new Label("Teacher Approved?");
                approve.setTranslateX(-130);
                approve.setTranslateY(124);
                soe.getChildren().add(approve);
                
                ComboBox<String> yn = new ComboBox<String>();
                yn.getItems().addAll("No", "Yes");
                yn.setTranslateY(124);
                yn.setTranslateX(60);//120
                soe.getChildren().add(yn);
                
                //button to sign in the equipment on acton
                btn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        notAvail.setVisible(false);
                        error.setVisible(false);
                        Equipment test = new Equipment(equipBox.getValue());
                        
                        //checks to make sure all boxes are filled in
                        if(userTextField.getText().length()!=0&&equipBox.getValue()!=null&&hrsBox.getValue()!=null&&test.isInteger(day.getText())&&month.getValue()!=null&&ampmBox.getValue()!=null&&usageText.getText().length()!=0&&purposeText.getText().length()!=0&&minsBox.getValue()!=null&&yn.getValue()!=null)
                        {
                            StackPane success = new StackPane();
                            Equipment equip = new Equipment(userTextField.getText(), month.getValue()+" "+day.getText(), equipBox.getValue(), purposeText.getText(), usageText.getText(), hrsBox.getValue()+":"+minsBox.getValue()+" "+ampmBox.getValue(), yn.getValue());
                            //makes sure the quipment is available
                            if (equip.isAvailable(equip))
                            {
                                equip.addData(equip.getName(), equip.getDate(), equip.getType(), equip.getPurpose(), equip.getUsage(), equip.getoTime(), equip.getTeachApp());
                                Label entry = new Label(equip.getUse());
                                success.getChildren().add(entry);
                                Scene sucscene = new Scene(success,width,height);
                             
                                Button btn = new Button();
                                btn.setText("Back");
                                btn.setTranslateX(50);
                                btn.setTranslateY(50);
                                success.getChildren().add(btn);
        
                                sucscene.getStylesheets().add(EquipmentSignOut.class.getResource("GUIstyle.css").toExternalForm());
                            
                                primaryStage.setScene(sucscene);
                                primaryStage.show();
                            
                                btn.setOnAction(new EventHandler<ActionEvent>() {
            
                                    @Override
                                    public void handle(ActionEvent event) {
                                        primaryStage.setScene(scene);
                                    }
                            });
                            }
                            else if (test.isAvailable(test)==false)
                            {
                                notAvail.setText(equipBox.getValue()+" is not available at this time.");
                                notAvail.setVisible(true);
                            }
                        }
                        else
                        {
                            error.setVisible(true);
                            System.out.print("hi");
                            System.out.println(date.getText());
                        }
                    }
                }); 
            }
        });
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Equipment test = new Equipment();
        System.out.print(test.date());
        System.out.println(test.time());
        System.out.println("\n");
        System.out.println(System.getProperty("user.name"));
        System.out.print("\nCan read: "+test.canRead());
        System.out.print("\nCan write: "+test.canWrite());
        System.out.print("\nCan execute: "+test.canExecute()+"\n\n");
        launch(args);
    }  
}