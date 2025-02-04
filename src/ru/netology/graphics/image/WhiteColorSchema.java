package ru.netology.graphics.image;
import java.util.Map;

public class WhiteColorSchema implements TextColorSchema {
    Map<Integer, Character> map = Map.of(
            0, '@',
            1, '%',
            2, '#',
            3, '*',
            4, '+',
            5, '.'
    );

    @Override
    public char convert(int color) {
        char converted = map.get(color/51);
        return converted;
    }
}
