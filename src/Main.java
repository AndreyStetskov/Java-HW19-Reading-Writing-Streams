import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

@SuppressWarnings({"MismatchedQueryAndUpdateOfCollection", "DataFlowIssue"})
public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = Files.newBufferedReader(Paths.get("resources/data.txt"));
        Stream<String> poem = bufferedReader.lines();

        File result = new File("resources/resize_data.txt");
        FileWriter writer = new FileWriter(result);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);

        Scanner scan = new Scanner(System.in);

        System.out.println("Enter first line");
        int start = scan.nextInt();
        System.out.println("Enter last line");
        int finish = scan.nextInt();

        if (start > 1 || finish > 1 || start < finish) {
            poem.skip(start - 1)
                    .limit(finish - start + 1)
                    .forEach(s -> {
                        try {
                            bufferedWriter.write(s);
                            bufferedWriter.newLine();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
//  Daniil, Idea forces to use try...catch. Why? I just realized that Try allows to define a block of code that will be checked for errors during its execution.
//  Catch allows you to define a block of code that will be executed if an error occurs in the try block.
//  I'd like to know more about what this beast try...catch is. Could you please explain?
                        System.out.println(s);
                    });
            bufferedWriter.close();
        }
        else {
            System.err.println("The line numbers are entered incorrectly");
        }
    }
}