package com.sec.asm.utils;

import com.sec.asm.drawing.canvas.Canvas;
import com.sec.asm.drawing.canvas.Drawable;
import com.sec.asm.drawing.canvas.TextAlign;
import com.sec.asm.drawing.theme.table.OneLineTable;

public class DrawingUtils {
    public static final String EMPTY = "";

    private static boolean contains(int[] array, int val) {
        for (int item : array) {
            if (item == val) {
                return true;
            }
        }
        return false;
    }

    public static void printTable(String[][] matrix) {
        Drawable table = new OneLineTable(matrix, TextAlign.CENTER);
        Canvas canvas = new Canvas();
        canvas.draw(0, 0, table);
        System.out.println(canvas);
    }
}
