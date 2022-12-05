package org.sec.asm.test;

import org.sec.asm.utils.DrawingUtils;

public class TestGraph {
    private static final String[][] TABLE = {
            {"0", "1","2","..."},
            {"String[] args", "Runtime rt","Process p (null)"," "},
    };

    public static void main(String[] args) {
        DrawingUtils.printTable(TABLE);
    }
}
