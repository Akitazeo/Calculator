package com.olegbalj.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Float first;
    Float second;
    Float result;

    Button lastAction;
    Button clear;
    Button dot;
    Button zero;
    Button one;
    Button two;
    Button three;
    Button four;
    Button five;
    Button six;
    Button seven;
    Button eight;
    Button nine;
    TextView display;
    Button equals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        zero = findViewById(R.id.zero);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);
        display = findViewById(R.id.textView);
        equals = findViewById(R.id.equals);
        clear = findViewById(R.id.clear);
        dot = findViewById(R.id.dot);

        ArrayList<Button> symbols = new ArrayList<>(Arrays.asList(
                findViewById(R.id.minus),
                findViewById(R.id.plus),
                findViewById(R.id.divide),
                findViewById(R.id.multiply)));
        ArrayList<Button> numbers = new ArrayList<>(Arrays.asList(one, two, three, four, five, six, seven, eight, nine, zero,dot));
        numbers.forEach(t -> t.setOnClickListener(view -> {
            display.append(t.getText());
        }));

        symbols.forEach(t -> t.setOnClickListener(onclick -> {
            if (!display.getText().toString().isEmpty())
                second = Float.parseFloat(display.getText().toString());
            lastAction = t;
            display.setText("");
////            if (lastAction != null && first != null) {
////                second
////                equality();
////            }
//            if (!display.getText().toString().isEmpty()) {
//////                if (lastAction != null)
//////                    equality();
////                else {
//                    if(first==null || first == 0) {
//                        first = Float.parseFloat(display.getText().toString());
//                        display.setText("");
//                    }
////                }
//                lastAction = t;
//            }
        }));

        equals.setOnClickListener(onclick -> equality());
        clear.setOnClickListener(onclick -> {
            display.setText("");
            first = null;
            second = null;
            result = null;
        });
    }

    public void equality() {
        if (lastAction != null && second != null) {
            first = Float.parseFloat(display.getText().toString());
            switch (lastAction.getText().toString()) {
                case "/":
                    result = second / first;
                    display.setText(fmt(result));
                    break;
                case "*":
                    result = second * first;
                    display.setText(fmt(result));
                    break;
                case "+":
                    result = second + first;
                    display.setText(fmt(result));
                    break;
                case "-":
                    result = second - first;
                    display.setText(fmt(result));
                    break;
            }
            lastAction = null;
        }
    }

    public static String fmt(double d) {
        if (d == (long) d)
            return String.format(Locale.ENGLISH, "%d", (long) d);
        else
            return String.format("%s", d);
    }
}