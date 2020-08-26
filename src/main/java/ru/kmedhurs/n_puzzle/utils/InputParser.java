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

    private boolean isContainsStart = false;

    public Integer[][] initStartState(String filePath) throws InputErrorException {
        Path path = Paths.get(filePath);
        Integer[][] matrix;
        try {
            List<String> inputLines = Files.readAllLines(path).stream().filter(str -> !str.startsWith("#")).collect(Collectors.toList());
            int size = getMatrixSize(inputLines.get(0));
            matrix = new Integer[size][size];
            for (int i = 1; i <= inputLines.size() - 1; i++) {
                initMatrixRow(inputLines.get(i), matrix, i - 1);
            }
            if (!isContainsStart)
                throw new InputErrorException("ERROR: this puzzle doesn't contains start");
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

    private void initMatrixRow(String line, Integer[][] matrix, int rowNum) throws InputErrorException {
        String trimmedLine = line.replaceAll("\\s{2,}", " ").trim();
        List<Integer> inputNumbers = Arrays.stream(trimmedLine.split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        if (inputNumbers.size() != matrix.length)
            throw new InputErrorException("ERROR: input numbers count doesn't match the declared");
        if (inputNumbers.contains(0)) {
            if (isContainsStart)
                throw new InputErrorException("ERROR: multiple start positions");
            isContainsStart = true;
        }
        inputNumbers.toArray(matrix[rowNum]);
    }
}
