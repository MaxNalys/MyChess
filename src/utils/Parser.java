package utils;

import java.util.HashMap;
import java.util.Map;

public class Parser {
    //створити дві мапи
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
    public static String convertCoordinatesToMove(Coordinates coordinates1,Coordinates coordinates2) {
        String move;
        move=getKey(algebraicChessNotationMap, coordinates1.getY()).toString()+ coordinates1.getX() +"-"+getKey(algebraicChessNotationMap, coordinates2.getY()).toString()+ coordinates2.getX();
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


