package client;

import client.commands.CliNet;
import picocli.CommandLine;

public class ClientApplication {
    public static void main(String[] args) {
        CommandLine.run(new CliNet(), args);
    }
}
