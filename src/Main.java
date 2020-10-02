package at.markus;

import java.io.IOException;

public class Main {

    private static Input input;

    public static void main(String[] args) throws IOException {
        new Thread(Checker::check, "Checker").start();
        new Thread(()->{
            try {
                input.console();
            } catch (IOException e) {
            }
        }, "Console").start();
    }
}
