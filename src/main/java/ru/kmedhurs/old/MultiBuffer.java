package ru.kmedhurs.old;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class MultiBuffer {

    private List<String> buffer = new ArrayList<>();
    private List<Integer> widths = new ArrayList<>();
    private int height = 0;
    private int width = 0;
    private int widthLimit = 80;
    private int cursorLine = 0;

    public void add(String str) {
        // to calculate an accurate width, we need to strip any ANSI
        // control codes, and keep duplicate copy with the codes
        String stripped = str.replaceAll("\u001B\\[[\\d;]*[^\\d;]", "");
        String[] lines = str.split("\n");
        String[] slines = stripped.split("\n");
        int maxWidth = 0;
        for (int i = 0; i < lines.length; i++)
            if (slines[i].length() > maxWidth) maxWidth = slines[i].length();
        boolean fit = false;
        while (!fit) {
            while (cursorLine + lines.length + 1 > buffer.size()) {
                buffer.add("");
                widths.add(0);
                height = cursorLine;
            }
            fit = true;
            for (int i = cursorLine; i < cursorLine + lines.length; i++)
                if (buffer.get(i) != null &&
                        widths.get(i) + slines[i - cursorLine].length() > widthLimit)
                    fit = false;
            if (!fit) cursorLine = cursorLine + slines.length + 1;
        }
        this.addAtLine(cursorLine, lines, slines);
    }

    private void addAtLine(int lineNo, String[] lines, String[] slines) {
        // see if we need to append new lines and do it
        int heightReq = lineNo + lines.length;
        if (heightReq > buffer.size()) {
            buffer.addAll(Collections.nCopies(heightReq - height, " "));
            widths.addAll(Collections.nCopies(heightReq - height, 0));
        }
        //for (int i = 0; i < heightReq - h; i++) buffer.add("");
        height = buffer.size();
        // find max width at existing lines effected
        int maxExWidth = 0;
        for (int i = lineNo; i < lineNo + lines.length; i++)
            if (maxExWidth < widths.get(i)) maxExWidth = widths.get(i);
        // add required padding and the new string
        for (int i = lineNo; i < lineNo + lines.length; i++) {
            int padding = maxExWidth - widths.get(i);
            //String old = buffer.get(i);
            buffer.set(i, buffer.get(i) +
                    setPadding(padding) +
                    lines[i - lineNo]
            );
            widths.set(i, widths.get(i) + padding + slines[i - lineNo].length());
            if (widths.get(i) > width) width = widths.get(i);
        }

    }

    private String setPadding(int n) {
        return " ".repeat(n);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String line : buffer) {
            sb.append(line);
            sb.append('\n');
        }
        return sb.toString();
    }
}

