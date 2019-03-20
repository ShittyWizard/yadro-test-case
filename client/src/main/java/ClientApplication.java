import commands.CliNet;
import picocli.CommandLine;

import java.util.Scanner;

public class ClientApplication {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] arguments;
        System.out.println("Welcome to cli_net!");
        System.out.println("You can exit from cli_net by command 'exit' ");
        System.out.println("Enter your command:");
        while (sc.hasNext()) {
            String line = sc.nextLine();
            if (!line.equals("exit")) {
                arguments = line.split(" ");
                CommandLine.run(new CliNet(), arguments);
            } else {
                break;
            }
        }
    }
}
