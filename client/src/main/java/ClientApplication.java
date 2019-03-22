import commands.CliNet;
import picocli.CommandLine;

import java.util.Arrays;
import java.util.Scanner;

public class ClientApplication {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] arguments;
        System.out.println("Welcome to cli_net!");
        System.out.println(new CommandLine(new CliNet()).getUsageMessage());
        System.out.println("You can exit from cli_net by command 'exit' ");
        System.out.println("Enter your command:");
        while (sc.hasNext()) {
            String line = sc.nextLine();
            if (!line.equals("exit")) {
                arguments = line.split(" ");
                if (arguments[0].equals("cli_net")) {
                    arguments = Arrays.copyOfRange(arguments, 1, arguments.length);
                    try {
                        CommandLine.run(new CliNet(), arguments);
                    } catch (CommandLine.ExecutionException e) {
                        System.out.println("Error... ");
                    } catch (Exception e) {
                        System.out.println("Ooops... Something happened");
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Every command starts with 'cli_net'");
                }
            } else {
                break;
            }
        }
    }
}
