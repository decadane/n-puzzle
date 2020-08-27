package ru.kmedhurs.n_puzzle.utils;

import ru.kmedhurs.n_puzzle.exceptions.InputErrorException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputParser {

    public int[][] initStartState(String filePath) throws InputErrorException {
        Path path = Paths.get(filePath);
        int[][] matrix;
        try {
            List<String> inputLines = Files.readAllLines(path).stream().filter(str -> !str.startsWith("#")).collect(Collectors.toList());
            int size = getMatrixSize(inputLines.get(0));
            matrix = new int[size][size];
            for (int i = 1; i <= inputLines.size() - 1; i++) {
                initMatrixRow(inputLines.get(i), matrix, i - 1);
            }
        } catch (IOException e) {
            throw new InputErrorException("ERROR: file on this path doesn't exists!");
        }
        return matrix;
    }

    private int getMatrixSize(String firstString) throws InputErrorException {
        int size = Integer.parseInt(firstString.trim());
        if (size < 1)
            throw new InputErrorException("ERROR: matrix size " + size + " smaller then minimum value");
        return size;
    }

    private void initMatrixRow(String line, int[][] matrix, int rowNum) throws InputErrorException {
        String trimmedLine = line.replaceAll("\\s{2,}", " ").trim();
        String lineWithoutComments = trimmedLine.split("#")[0];
        List<String> splittedLine = Arrays.asList(lineWithoutComments.split(" "));
        if (splittedLine.stream().anyMatch(input -> !input.matches("\\d+")))
            throw new InputErrorException("ERROR: unexpected symbol in puzzle");
        List<Integer> inputNumbers = splittedLine.stream().map(Integer::parseInt).collect(Collectors.toList());
        if (inputNumbers.size() != matrix.length)
            throw new InputErrorException("ERROR: input numbers count doesn't match the declared");
        matrix[rowNum] = inputNumbers.stream().mapToInt(i->i).toArray();
    }
}
