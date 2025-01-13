package flock.logger;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd HH:mm:ss");
    private final Object lock = new Object();

    public void log(LogType level, String content) {
        synchronized (lock) {
            switch (level) {
                case ERROR -> System.out.print("\033[31;1m"); // Red and Bold
                case DEBUG -> System.out.print("\033[35;4m"); // Purple and Underline
                case WARN -> System.out.print("\033[33;m"); // Color Yellow
                case INFO -> System.out.print("\033[0m"); // Color White
            }
            System.out.printf("[%s %s] ", LocalDateTime.now().format(formatter), level);
            System.out.print(content);
            System.out.println("\033[0m"); // Reset Style
        }
    }
}
