package ru.netology.graphics;

import ru.netology.graphics.image.Converter;
import ru.netology.graphics.image.TextColorSchema;
import ru.netology.graphics.image.TextGraphicsConverter;
import ru.netology.graphics.image.WhiteColorSchema;
import ru.netology.graphics.server.GServer;

import java.io.File;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        TextGraphicsConverter converter = new Converter();// Создайте тут объект вашего класса конвертера


        GServer server = new GServer(converter); // Создаём объект сервера
        server.start(); // Запускаем

         // Или то же, но с выводом на экран:
        // String url = "https://i.ibb.co/6DYM05G/edu0.jpg";
       //String imgTxt = converter.convert(url);
      //  System.out.println(imgTxt);
    }
}
