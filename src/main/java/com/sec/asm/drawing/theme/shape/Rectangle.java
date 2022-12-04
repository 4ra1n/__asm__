package com.sec.asm.drawing.theme.shape;

import com.sec.asm.drawing.canvas.Box;
import com.sec.asm.drawing.canvas.Canvas;
import com.sec.asm.drawing.canvas.Drawable;

public class Rectangle implements Drawable {
    public final int width;
    public final int height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @SuppressWarnings({"UnnecessaryLocalVariable", "Duplicates"})
    @Override
    public void draw(Canvas canvas, int startRow, int startCol) {
        int left = startCol;
        int right = left + width + 1;
        int top = startRow;
        int bottom = top + height + 1;

        // four corners
        canvas.moveTo(top, left);
        canvas.drawPixel(Box.DOWN_AND_RIGHT);
        canvas.moveTo(top, right);
        canvas.drawPixel(Box.DOWN_AND_LEFT);
        canvas.moveTo(bottom, left);
        canvas.drawPixel(Box.UP_AND_RIGHT);
        canvas.moveTo(bottom, right);
        canvas.drawPixel(Box.UP_AND_LEFT);

        // four borders
        canvas.moveTo(top, left + 1);
        canvas.drawHorizontalLine(width);
        canvas.moveTo(bottom, left + 1);
        canvas.drawHorizontalLine(width);
        canvas.moveTo(top + 1, left);
        canvas.drawVerticalLine(height);
        canvas.moveTo(top + 1, right);
        canvas.drawVerticalLine(height);
    }
}
