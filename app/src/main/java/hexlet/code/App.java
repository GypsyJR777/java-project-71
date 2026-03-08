package hexlet.code;

import java.util.concurrent.Callable;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(
    name = "gendiff",
    mixinStandardHelpOptions = true,
    version = "gendiff 1.0",
    description = "Compares two configuration files and shows a difference."
)
public class App implements Callable<Integer> {
    @Option(
        names = {"-f", "--format"},
        paramLabel = "format",
        defaultValue = "stylish",
        description = "output format [default: ${DEFAULT-VALUE}]"
    )
    private String format;

    @Parameters(index = "0", paramLabel = "filepath1", description = "path to first file")
    private String filepath1;

    @Parameters(index = "1", paramLabel = "filepath2", description = "path to second file")
    private String filepath2;

    public static void main(String[] args) {
        int exitCode;
        try {
            exitCode = new CommandLine(new App()).execute(args);
        } catch (final Exception e) {
            exitCode = 123;
            e.printStackTrace();
        }
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception {
        String diff = Differ.generate(filepath1, filepath2);
        if (!diff.isEmpty()) {
            System.out.println(diff);
        }
        return 0;
    }
}
