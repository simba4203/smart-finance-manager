import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Transaction {
    private String type;   // deposti and withdraw and transfre 
    private double amount;
    private String detials; // like extra informations 
    private String dateTime;

    //constructor

    public Transaction(String type, double amount, String details){
        this.type = type;
        this.amount= amount;
        this.detials = details;

        //current date time 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.dateTime = LocalDateTime.now().format(formatter);
    }
    //Display the transaction 
    public void showTransaction(){
        System.out.println("-----------------------------------------");
        System.out.println("type     :"+ type );
        System.out.println("amount   :"+ amount);
        System.out.println("detials    : "+  detials);
        System.out.println("Date/Time  :"+dateTime);
    }
    
}
