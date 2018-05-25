package com.example.shucheng_fu.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.shucheng_fu.calculator.R;

import java.text.DecimalFormat;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private TextView _screen;
    private String display = "";
    private String currentOperator="";
    private String currentOpint="";
    double result;
    String formatResult;
    String updateResult;
    boolean clickOp = false;
    boolean clickPoint = false;
    String add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _screen = (TextView)findViewById(R.id.myTextView1);
        _screen.setText(display);
    }

    public void onClickNumber(View v){
        Button b = (Button) v;
        display = display + b.getText();
        _screen.setText(display);

        clickOp = true;
        clickPoint = true;
        if(currentOpint.length() ==1){
            clickPoint =false;
        }
        //display = display + updateResult;
        if (currentOperator.length() < 1){
            return;
        }else {
            if(updateResult == null) {     // display=updateResult + display;
                String[] operation = display.split(Pattern.quote(currentOperator));

                //get first one value:

                result = operateArithmetic(operation[0], operation[1], currentOperator);
                if(result == (int)result) {
                    DecimalFormat decimalFormat = new DecimalFormat("#.#");
                    formatResult = decimalFormat.format(result);
                    _screen.setText(display + "\n" + String.valueOf(formatResult));
                    updateResult = String.valueOf(formatResult);
                }else {
                    _screen.setText(display + "\n" + String.valueOf(result));
                    updateResult = String.valueOf(result);
                    System.out.println(updateResult);
                }

            }else {

                String[] operation = display.split(Pattern.quote(currentOperator));
                System.out.println(currentOperator);
                System.out.println(display);

                //get value:
                result = operateArithmetic(operation[0], operation[1], currentOperator);
                System.out.println(result);
                if(result == (int)result) {
                    DecimalFormat decimalFormat = new DecimalFormat("#.#");
                    formatResult = decimalFormat.format(result);
                    System.out.println(formatResult);
                    //updateResult = String.valueOf(result);
                    _screen.setText(display + "\n" + String.valueOf(formatResult));
                    //updateResult = String.valueOf(result);
                    updateResult = String.valueOf(formatResult);
                }else {
                    _screen.setText(display + "\n" + String.valueOf(result));
                    updateResult = String.valueOf(result);
                    System.out.println(updateResult);
                }
            }

        }
    }
    public void onClickPoint(View v){
        Button b = (Button) v;
        if(display == ""){
            return;
        }else {
            if(clickPoint) {
                display += b.getText();
                currentOpint = b.getText().toString();
                _screen.setText(display);
                clickPoint = false;
            }
        }
    }
    public void onClickOperator (View v) {

        Button b = (Button) v;
        if (display == "") {
            return;
        }
        else{
            if (updateResult == null) {
                if(clickOp) {
                    display = display + b.getText();
                    currentOperator = b.getText().toString();
                    _screen.setText(display);
                    clickOp =false;
                    currentOpint = "";
                }
            }
            else {
                //modify
                //clickOp =false;
                display = updateResult + b.getText();
                currentOperator = b.getText().toString();
                _screen.setText(display);
                //clickOp =false;
            }
        }
    }

    private double operateArithmetic (String a ,String b ,String op){

        switch (op) {
            case "+":
                return (Double.valueOf(a) + Double.valueOf(b));

            case "-":
                return (Double.valueOf(a) - Double.valueOf(b));
            case "*":
                return (Double.valueOf(a) * Double.valueOf(b));
            case "/":
                return (Double.valueOf(a) / Double.valueOf(b));
        }

        return -1;
    }


    public void onClickEqual (View v){
        _screen.setText(updateResult);

//        String[] operation = display.split(Pattern.quote(currentOperator));
//        if (currentOperator.length() < 1){
//            return;
//        }else {
//            //get value:
//            result = operateArithmetic(operation[0] , operation[1] ,currentOperator);
//            //_screen.setText(display + "\n" + String.valueOf(result));
//            _screen.setText(display + "\n" + String.valueOf(result));
//            updateResult = String.valueOf(result);
//
//        }

    }
    private void clear (){
        //display.substring(0,display.length()-1);
        display = "";
        currentOperator = "";
        updateResult = null;
        currentOpint ="";
        _screen.setText(updateResult);
    }
    //    public void onClickClear (View v){
//        clear();
//    }
    public boolean onLongClick(View v){
        clear();
        //String a;
        //String str = new String();
        //str = str.substring(0,display.length() - 1);
        return true;
    }
}