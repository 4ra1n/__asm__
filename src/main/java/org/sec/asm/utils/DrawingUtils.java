package org.sec.asm.utils;

import org.sec.asm.drawing.canvas.Canvas;
import org.sec.asm.drawing.canvas.Drawable;
import org.sec.asm.drawing.canvas.TextAlign;
import org.sec.asm.drawing.theme.OneLineTable;

public class DrawingUtils {
    public static void printTable(String[][] matrix) {
        Drawable table = new OneLineTable(matrix, TextAlign.CENTER);
        Canvas canvas = new Canvas();
        canvas.draw(0, 0, table);
        System.out.println(canvas);
    }
}
