package utils;

import java.util.HashMap;
import java.util.Map;

public class Parser {
    //створити дві мапи
    public static final Map<Character, Integer> algebraicChessNotationMap;

    static {
        algebraicChessNotationMap = new HashMap<>();
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

        arrayWithCoordinates[0] = convertFistPartOfMove(move);
        arrayWithCoordinates[1] = convertSecondPartOfMove(move);
        return arrayWithCoordinates;
    }

    public static Coordinates convertFistPartOfMove(String move) {
        int startX = Character.getNumericValue(move.charAt(1)) - 1;
        int startY = algebraicChessNotationMap.get(move.charAt(0));
        return new Coordinates(startX, startY);
    }

    public static Coordinates convertSecondPartOfMove(String move) {
        int x = Character.getNumericValue(move.charAt(4)) - 1;
        int y = algebraicChessNotationMap.get(move.charAt(3));
        return new Coordinates(x, y);
    }

    public static String convertCoordinatesToMove(Coordinates coordinates1, Coordinates coordinates2) {
        String move;
        move = getKey(algebraicChessNotationMap, coordinates1.getY()).toString() + (coordinates1.getX() + 1) + "-" + getKey(algebraicChessNotationMap, coordinates2.getY()).toString() + (coordinates2.getX() + 1);
        return move;
    }

    public static <K, V> K getKey(Map<K, V> map, V value) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (value.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }
}


