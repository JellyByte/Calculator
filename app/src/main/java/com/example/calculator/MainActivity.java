package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.SensorListener;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.Toast;


import org.mariuszgromada.math.mxparser.*;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private EditText display;
    double xValue;
    int level;


    SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.disEditText);
        display.setShowSoftInputOnFocus(false);

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);

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

    public void decBTN(View view) {
        updateText(getResources().getString(R.string.decimalText));
    }
    public void clearBTN(View view) { display.setText("");}
    public void openParamBTN(View view) { updateText(getResources().getString(R.string.parenthesesOpenText)); }
    public void closedParamBTN(View view) { updateText(getResources().getString(R.string.parenthesesCloseText)); }
    public void divBTN(View view) {
        updateText(getResources().getString(R.string.divideText));
    }
    public void multiBTN(View view) {
        updateText(getResources().getString(R.string.multiplyText));
    }
    public void subBTN(View view) {
        updateText(getResources().getString(R.string.subtractText));
    }
    public void addBTN(View view) {
        updateText(getResources().getString(R.string.addText));
    }

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
        Calculations calculations = new Calculations(display);
        calculations.start();
    }

    public void sinBTN(View view){
        if(level > 0){
            updateText("sin(");
        }
        else {
            Toast.makeText(this,"Locked", Toast.LENGTH_SHORT).show();
        }

    }

    public void cosBTN(View view){
        if(level > 0){
            updateText("cos(");
        }
        else {
            Toast.makeText(this,"Locked", Toast.LENGTH_SHORT).show();
        }

    }

    public void tanBTN(View view){
        if(level > 0){
            updateText("tan(");

        }
        else {
            Toast.makeText(this,"Locked", Toast.LENGTH_SHORT).show();
        }
    }

    public void arcSinBTN(View view){
        if(level > 0){
            updateText("arcsin(");

        }
    }

    public void arcCosBTN(View view){
        if(level > 0){
            updateText("arccos(");

        }
        else {
            Toast.makeText(this,"Locked", Toast.LENGTH_SHORT).show();
        }
    }

    public void arcTanBTN(View view){
        if(level > 0){
            updateText("arctan(");

        }
        else {
            Toast.makeText(this,"Locked", Toast.LENGTH_SHORT).show();
        }
    }

    public void logBTN(View view){
        if(level > 1){
            updateText("log(");

        }
        else {
            Toast.makeText(this,"Locked", Toast.LENGTH_SHORT).show();
        }
    }

    public void lnBTN(View view){
        if(level > 1){
            updateText("ln(");

        }
        else {
            Toast.makeText(this,"Locked", Toast.LENGTH_SHORT).show();
        }
    }

    public void sqrtBTN(View view){
        if(level > 1){
            updateText("sqrt(");

        }
        else {
            Toast.makeText(this,"Locked", Toast.LENGTH_SHORT).show();
        }
    }

    public void eBTN(View view){
        if(level > 1){
            updateText("e");

        }
        else {
            Toast.makeText(this,"Locked", Toast.LENGTH_SHORT).show();
        }
    }

    public void piBTN(View view){
        if(level > 1){
            updateText(getResources().getString(R.string.piText));

        }
        else {
            Toast.makeText(this,"Locked", Toast.LENGTH_SHORT).show();
        }
    }

    public void absBTN(View view){
        if(level > 1){
            updateText("abs(");

        }
        else {
            Toast.makeText(this,"Locked", Toast.LENGTH_SHORT).show();
        }
    }

    public void primeBTN(View view){
        if(level > 2){
            updateText("ispr(");

        }
        else {
            Toast.makeText(this,"Locked", Toast.LENGTH_SHORT).show();
        }
    }

    public void sqrBTN(View view){
        if(level > 2){
            updateText("^(2)");

        }
        else {
            Toast.makeText(this,"Locked", Toast.LENGTH_SHORT).show();
        }
    }

    public void sqryBTN(View view){
        if(level > 2){
            updateText("^(");

        }
        else {
            Toast.makeText(this,"Locked", Toast.LENGTH_SHORT).show();
        }
    }

    public void openSettings(View view) {
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(intent);
    }


    class Calculations extends Thread {
        EditText display;

        Calculations(EditText display){
            this.display = display;
        }

        @Override
        public void run(){

            String userExp = display.getText().toString();

            userExp = userExp.replaceAll(getResources().getString(R.string.divideText), "/");
            userExp = userExp.replaceAll(getResources().getString(R.string.multiplyText), "*");

            Expression exp = new Expression(userExp);
            String result = String.valueOf(exp.calculate());

            display.setText(result);
            display.setSelection(result.length());
        }
    }



    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy){

    }

    @Override
    public void onSensorChanged(SensorEvent event){
        xValue = event.values[0];
        if (xValue > 6) {
            display.setText("");
        }
    }




}