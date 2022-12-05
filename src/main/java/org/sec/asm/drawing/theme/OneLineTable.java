package org.sec.asm.drawing.theme;

import org.sec.asm.drawing.canvas.Canvas;
import org.sec.asm.drawing.canvas.Drawable;
import org.sec.asm.drawing.canvas.TextAlign;

public class OneLineTable extends AbstractTable implements Drawable {
    public final String[][] matrix;
    public final TextAlign align;

    private final int row_padding;
    private final int col_padding;

    public OneLineTable(String[][] matrix, TextAlign align) {
        this(matrix, align, 0, 1);
    }

    public OneLineTable(String[][] matrix, TextAlign align, int row_padding, int col_padding) {
        this.matrix = matrix;
        this.align = align;
        this.row_padding = row_padding;
        this.col_padding = col_padding;
    }

    @Override
    protected int getCellLength(int row, int col) {
        String item = matrix[row][col];
        int length = item == null ? 0 : item.length();
        return length + 2 * col_padding;
    }

    @Override
    public void draw(Canvas canvas, int startRow, int startCol) {
        int rowCount = matrix.length;
        int colCount = matrix[0].length;

        int[] rowHeightArray = new int[rowCount];
        int[] colWidthArray = getColWidthArray(rowCount, colCount);

        for (int i = 0; i < rowCount; i++) {
            rowHeightArray[i] = 1 + 2 * row_padding;
        }

        // draw border
        canvas.moveTo(startRow, startCol);
        canvas.drawTable(rowHeightArray, colWidthArray);

        // draw text
        int currentRow = startRow;
        for (int i = 0; i < rowCount; i++) {
            if (i > 0) {
                currentRow += rowHeightArray[i - 1] + 1;
            }

            int currentCol = startCol;
            for (int j = 0; j < colCount; j++) {
                if (j > 0) {
                    currentCol += colWidthArray[j - 1] + 1;
                }

                String item = matrix[i][j];
                if (item == null) item = "";

                int currentWidth = colWidthArray[j];

                int row = currentRow + 1 + row_padding;
                int left = currentCol;
                int right = left + currentWidth + 1;

                switch (align) {
                    case LEFT: {
                        canvas.moveTo(row, left + col_padding + 1);
                        canvas.drawText(item);
                        break;
                    }
                    case CENTER: {
                        canvas.moveTo(row, left + (currentWidth - item.length()) / 2 + 1);
                        canvas.drawText(item);
                        break;
                    }
                    case RIGHT: {
                        canvas.moveTo(row, right - col_padding - item.length());
                        canvas.drawText(item);
                        break;
                    }
                    default:
                        assert false : "impossible";
                }
            }

        }
    }
}
