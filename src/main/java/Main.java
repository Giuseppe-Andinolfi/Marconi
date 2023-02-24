import java.io.*;

public class Main {

    public static void main(String[] args) {
        String inputFilePath = "Marconi/src/main/java/input.txt";
        String outputFilePath = "Marconi/src/main/java/output.txt";

        // parse command line arguments
        if (args.length >= 1) {
            inputFilePath = args[0];
        }
        if (args.length >= 2) {
            outputFilePath = args[1];
        }

        int[] numbers = readNumbersFromFile(inputFilePath);

        if (numbers.length % 2 == 0) {
            numbers = filterEvenNumbers(numbers);
        } else {
            numbers = filterOddNumbers(numbers);
        }

        if (outputFilePath.isEmpty()) {
            printNumbersToConsole(numbers);
        } else {
            writeNumbersToFile(numbers, outputFilePath);
        }
    }

    private static int[] readNumbersFromFile(String filePath) {
        int[] numbers = new int[0];
        try {
            File file = new File(filePath);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null) {
                int number = Integer.parseInt(line);
                numbers = appendNumberToArray(numbers, number);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return numbers;
    }

    private static int[] appendNumberToArray(int[] array, int number) {
        int[] newArray = new int[array.length + 1];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        newArray[array.length] = number;
        return newArray;
    }

    private static int[] filterEvenNumbers(int[] numbers) {
        int[] evenNumbers = new int[numbers.length / 2];
        int index = 0;
        for (int number : numbers) {
            if (number % 2 == 0) {
                evenNumbers[index] = number;
                index++;
            }
        }
        return evenNumbers;
    }

    private static int[] filterOddNumbers(int[] numbers) {
        int[] oddNumbers = new int[numbers.length / 2 + 1];
        int index = 0;
        for (int number : numbers) {
            if (number % 2 == 1) {
                oddNumbers[index] = number;
                index++;
            }
        }
        return oddNumbers;
    }

    private static void writeNumbersToFile(int[] numbers, String filePath) {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (int number : numbers) {
                writer.write(number + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void printNumbersToConsole(int[] numbers) {
        for (int number : numbers) {
            System.out.println(number);
        }
    }
}
