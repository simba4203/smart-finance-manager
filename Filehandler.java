import java.io.*;
import java.util.HashMap;

public class Filehandler {
    private static final String FILE_NAME = "user.text";

    /// save the file 
    public static void saveUsers(HashMap<Integer,User> user) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))){
            for(int aacNo : user.keySet()){
                User u = user.get(aacNo); 
                writer.write(aacNo + "," + u.getName() + "," + u.getPin() + "," + u.getBalance());
                writer.newLine();
            }
            System.out.println(" data saved succeddfully");

        }catch(IOException e){
            System.out.println("error in saving data ");
        }
        
    }
    public static HashMap<Integer, User>loadUsers(){
        HashMap<Integer, User>users = new HashMap<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))){

            String line;
            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                int accNo = Integer.parseInt(data[0]);
                String name = data[1];
                int pin = Integer.parseInt(data[2]);
                double balance = Double.parseDouble(data[3]);

                User user = new User(name, pin);

                //set balance manually
                user.deposit(balance);

                users.put(accNo, user);

                
            }

        }catch(IOException e){
            System.out.println(" no previous data Found -_- ");
        }

        return users;
    }
    
}
