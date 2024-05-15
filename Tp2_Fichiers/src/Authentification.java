import java.io.*;
public class Authentification {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("/home/welf9i/Documents/in.txt"));
        boolean authentifie = false;
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\\s");
            if (parts.length >= 2) {
                String login = parts[0];
                String password = parts[1];

                String loginSaisi = "User2";
                String passwordSaisi = "passUser2";

                if (login.equals(loginSaisi) && password.equals(passwordSaisi)) {
                    authentifie = true;
                    break;
                }
            }
        }
        reader.close();
        if (authentifie) {
            System.out.println("Authentification terminée avec succès !");
        } else {
            System.out.println("Authentification échoué !");
        }
    }
}
