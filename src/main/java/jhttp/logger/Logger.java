package jhttp.logger;

public class Logger {
    public void log(LogType level, String content) {
        switch (level) {
            case ERROR -> System.out.print("\033[31;1m"); // Red and Bold
            case DEBUG -> System.out.print("\033[35;4m"); // Purple and Underline
            case WARN -> System.out.print("\033[33;m"); // Color Yellow
            case INFO -> System.out.print("\033[0m"); // Color White
        }
        System.out.print(level + ": ");
        System.out.print(content);
        System.out.println("\033[0m"); // Reset Style
    }
}
