package utils;

import java.util.HashMap;
import java.util.Map;

public class Parser {
     public static final Map<Character, Integer> algebraicChessNotationMap;
     static {
         algebraicChessNotationMap=new HashMap<>();
         algebraicChessNotationMap.put('a', 7);
         algebraicChessNotationMap.put('b', 6);
         algebraicChessNotationMap.put('c', 5);
         algebraicChessNotationMap.put('d', 4);
         algebraicChessNotationMap.put('e', 3);
         algebraicChessNotationMap.put('f', 2);
         algebraicChessNotationMap.put('g', 1);
         algebraicChessNotationMap.put('h', 0);
     }

    public static Coordinates[] parseInput(String move) {
        Coordinates[] arrayWithCoordinates = new Coordinates[2];
        int startX = Character.getNumericValue(move.charAt(1)) - 1;
        int startY = algebraicChessNotationMap.get(move.charAt(0));
        int x = Character.getNumericValue(move.charAt(4)) - 1;
        int y = algebraicChessNotationMap.get(move.charAt(3));
        Coordinates startCoordinates = new Coordinates(startX, startY);
        Coordinates nextCoordinates = new Coordinates(x, y);
        arrayWithCoordinates[0] = startCoordinates;
        arrayWithCoordinates[1] = nextCoordinates;
        return arrayWithCoordinates;
    }
}


