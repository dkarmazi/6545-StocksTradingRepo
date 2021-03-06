/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package StockTradingClient;

import java.net.InetAddress;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import RMI.*;
import StockTradingCommon.Enumeration.BrokerageFirm;
import StockTradingServer.User;

/**
 **
 * @author Sulochane
 */
public class Admin extends Application {
    
        private static final int PORT = 2019;
        private Stage stage;
        public static ServerInterface serverInterface;
    
    @Override
    public void start(Stage primaryStage) throws Exception{
//        Button btn = new Button();
//        btn.setText("Say 'Hello World'");
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Hello World!");
//            }
//        });
//        
//        StackPane root = new StackPane();
//        root.getChildren().add(btn);
//        
//        Scene scene = new Scene(root, 300, 250);
        
//        primaryStage.setTitle("Hello World!");
//        primaryStage.setScene(scene);
//        primaryStage.show();
        
        // TODO Capture the proper user ids
        int userId = 44;
        Utility.setCurrentUser_BrokerID(userId);
        // get the brokerage firm id of the user (Admin will not have a brokerage firm, hence set to -1)
        StockTradingServer.User user = Utility.GetBrokerInfo(userId);
        if (user.getBrokerFirmId() > 0)
        {
            Utility.setCurrentUser_BrokerageFirmID(user.getBrokerFirmId());
        }
        else
        {
            Utility.setCurrentUser_BrokerageFirmID(-1);
        }

        stage = primaryStage;
        
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        //Resource resource = getClass().getResource("Broker.fxml");
        Scene scene = new Scene(root);
        stage.setTitle("Stock Trading Platform");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
                        // Make reference to SSL-based registry
                        String host = (args.length < 1) ? "127.0.0.1" : args[0];

                        Registry registry = LocateRegistry.getRegistry(InetAddress
                                        .getByName(host).getHostName(), PORT,
                                        new RMISSLClientSocketFactory());

                        // "serverInterface" is the identifier that we'll use to refer
                        // to the remote object that implements the "serverInterface"
                        // interface

                        serverInterface = (ServerInterface) registry
                                        .lookup("TradingServer");

                        String message = serverInterface.getHello();

                        System.out.println(message + "\n");
                        launch(args);

                } catch (Exception e) {
                        System.out.println("Admin exception: " + e.getMessage());
                        e.printStackTrace();
                }


    }
    
    
}
