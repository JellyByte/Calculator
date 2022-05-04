package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

import org.mariuszgromada.math.mxparser.Expression;

public class GameActivity extends AppCompatActivity {

    private EditText display;
    private int num1;
    private int num2;
    Random random;
    int level;

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        display = findViewById(R.id.disEditText);
        display.setShowSoftInputOnFocus(false);

        random = new Random();
        num1 = random.nextInt(1000);
        num2 = random.nextInt(1000);

        display.setHint(String.valueOf(num1) + "+" +String.valueOf(num2));

        sp = getSharedPreferences("save", Context.MODE_PRIVATE);
        level = sp.getInt("level", level);
    }

    private void updateText(String string){
        String oldStr = display.getText().toString();

        int cursorPos = display.getSelectionStart();

        String lStr = oldStr.substring(0, cursorPos);
        String rStr = oldStr.substring(cursorPos);

        display.setText(String.format("%s%s%s", lStr, string, rStr));
        display.setSelection(cursorPos + string.length());

    }

    public void zeroBTN(View view) {
        updateText(getResources().getString(R.string.zeroText));
    }
    public void oneBTN(View view) {
        updateText(getResources().getString(R.string.oneText));
    }
    public void twoBTN(View view) {
        updateText(getResources().getString(R.string.twoText));
    }
    public void threeBTN(View view) {
        updateText(getResources().getString(R.string.threeText));
    }
    public void fourBTN(View view) {
        updateText(getResources().getString(R.string.fourText));
    }
    public void fiveBTN(View view) {
        updateText(getResources().getString(R.string.fiveText));
    }
    public void sixBTN(View view) {
        updateText(getResources().getString(R.string.sixText));
    }
    public void sevenBTN(View view) {
        updateText(getResources().getString(R.string.sevenText));
    }
    public void eightBTN(View view) {
        updateText(getResources().getString(R.string.eightText));
    }
    public void nineBTN(View view) {
        updateText(getResources().getString(R.string.nineText));
    }

    public void nullBTN(View view) {
        Toast.makeText(this,"Not available in game mode",Toast.LENGTH_SHORT).show();
    }
    public void clearBTN(View view) { display.setText("");}

    public void backspaceBTN(View view){
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();

        if (cursorPos != 0 && textLen !=0) {
            SpannableStringBuilder select = (SpannableStringBuilder) display.getText();
            select.replace(cursorPos - 1, cursorPos, "");
            display.setText(select);
            display.setSelection(cursorPos - 1);
        }
    }

    public void equalBTN(View view) {
        GameActivity.Calculations calculations = new GameActivity.Calculations(display, num1, num2, sp, level);
        calculations.start();
        Toast.makeText(this,"Exit the game",Toast.LENGTH_SHORT).show();
    }

    class Calculations extends Thread {
        EditText display;

        Calculations(EditText display, int num1, int num2, SharedPreferences sp, int level){
            this.display = display;
        }

        @Override
        public void run(){
            String ans = String.valueOf(num1 + num2);
            String userExp = display.getText().toString();


            userExp = userExp.replaceAll(getResources().getString(R.string.divideText), "/");
            userExp = userExp.replaceAll(getResources().getString(R.string.multiplyText), "*");

            Expression exp = new Expression(userExp);
            String result = String.valueOf(exp.calculate());

            if (userExp.equals(ans)) {
                display.setText("Correct!");
                level++;
                SharedPreferences.Editor edit = sp.edit();
                edit.putInt("level", level);
                edit.commit();
            }
            else{
                display.setText("Incorrect");
            }
            display.setSelection(result.length());


        }
    }

    public void exitGamemode(View view){
        Intent intent = new Intent(GameActivity.this, SettingsActivity.class);
        startActivity(intent);
    }
}