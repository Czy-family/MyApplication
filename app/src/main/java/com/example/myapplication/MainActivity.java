package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView display;
    private String number="";
    private String num1str;
    private String num2str;
    private double result;
    private double num1;
    private double num2;
    private String operator = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);

        findViewById(R.id.btn_0).setOnClickListener(this);
        findViewById(R.id.btn_1).setOnClickListener(this);
        findViewById(R.id.btn_2).setOnClickListener(this);
        findViewById(R.id.btn_3).setOnClickListener(this);
        findViewById(R.id.btn_4).setOnClickListener(this);
        findViewById(R.id.btn_5).setOnClickListener(this);
        findViewById(R.id.btn_6).setOnClickListener(this);
        findViewById(R.id.btn_7).setOnClickListener(this);
        findViewById(R.id.btn_8).setOnClickListener(this);
        findViewById(R.id.btn_9).setOnClickListener(this);

        findViewById(R.id.btn_clear).setOnClickListener(this);
        findViewById(R.id.btn_add).setOnClickListener(this);
        findViewById(R.id.btn_subtract).setOnClickListener(this);
        findViewById(R.id.btn_multiply).setOnClickListener(this);
        findViewById(R.id.btn_divide).setOnClickListener(this);

        findViewById(R.id.btn_sin).setOnClickListener(this);
        findViewById(R.id.btn_cos).setOnClickListener(this);
        findViewById(R.id.btn_tan).setOnClickListener(this);

        findViewById(R.id.btn_remainder).setOnClickListener(this);
        findViewById(R.id.btn_point).setOnClickListener(this);
        findViewById(R.id.btn_negative).setOnClickListener(this);

        findViewById(R.id.btn_A).setOnClickListener(this);
        findViewById(R.id.btn_square).setOnClickListener(this);

        findViewById(R.id.btn_equal).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final int buttonID =v.getId();
        if (buttonID == R.id.btn_0) {
            number+="0";
            display.setText(number);
        } else if (buttonID == R.id.btn_1) {
            number+="1";
            display.setText(number);
        } else if (buttonID == R.id.btn_2) {
            number+="2";
            display.setText(number);
        } else if (buttonID == R.id.btn_3) {
            number+="3";
            display.setText(number);
        } else if (buttonID == R.id.btn_4) {
            number+="4";
            display.setText(number);
        } else if (buttonID == R.id.btn_5) {
            number+="5";
            display.setText(number);
        } else if (buttonID == R.id.btn_6) {
            number+="6";
            display.setText(number);
        } else if (buttonID == R.id.btn_7) {
            number+="7";
            display.setText(number);
        } else if (buttonID == R.id.btn_8) {
            number+="8";
            display.setText(number);
        } else if (buttonID == R.id.btn_9) {
            number+="9";
            display.setText(number);
        } else if (buttonID == R.id.btn_add) {
            setOperator("+");
            num1str=number;
            number="";
        } else if (buttonID == R.id.btn_subtract) {
            setOperator("-");
            num1str=number;
            number="";
        } else if (buttonID == R.id.btn_multiply) {
            setOperator("×");
            num1str=number;
            number="";
        } else if (buttonID == R.id.btn_divide) {
            setOperator("÷");
            num1str=number;
            number="";
        } else if(buttonID == R.id.btn_sin){
            setOperator("sin");
            //display.setText("sin");
        }else if(buttonID == R.id.btn_cos){
            setOperator("cos");
        }else if(buttonID == R.id.btn_tan){
            setOperator("tan");
        }else if(buttonID == R.id.btn_A){
            setOperator("!");
        }else if(buttonID == R.id.btn_square){
            setOperator("√");
        }else if(buttonID == R.id.btn_point){
            number+=".";
            display.setText(number);
        }else if(buttonID == R.id.btn_remainder){
            num1str=number;
            num1=Double.parseDouble(num1str)*0.01;
            number+="%";
            display.setText(number);
        }else if(buttonID == R.id.btn_negative){
            number+="-";
            display.setText(number);
        }else if (buttonID == R.id.btn_equal) {
            calculate();
        } else if (buttonID == R.id.btn_clear) {
            reset();
        }
    }
    private void setOperator(String op) {
        operator = op;
        display.setText(op);
    }

    private double point(String numstr){
        double num=0;
        if(numstr.indexOf(".")!=-1){
            String front=numstr.substring(0,numstr.indexOf("."));
            String behind=numstr.substring(numstr.indexOf(".")+1);
            num=Double.parseDouble(front)+Double.parseDouble(behind)/Math.pow(10,behind.length());
        }
        return num;
    }

    private double percent(String numstr){
        double num=0;
        if(numstr.charAt(numstr.length()-1)=='%'){
            if(numstr.indexOf(".")!=-1){
                num=point(numstr.substring(0,numstr.length()-1))*0.01;
            }else{
                num=Double.parseDouble(numstr.substring(0,numstr.length()-1))*0.01;
            }

        }else{
            if(numstr.indexOf(".")!=-1){
                num=point(numstr);
            }else{
                num=Double.parseDouble(numstr);
            }
        }
        return num;
    }

    private double nagetive(String numstr){
        double num=0;
        if(numstr.charAt(0)=='-'){
            num=Double.parseDouble(numstr.substring(1));
            num=num*(-1);
        }else{
            num=percent(numstr);
        }
        return num;
    }

    private double getA(double n){
        double num=1;
        for(int i=1;i<=n;i++){
            num*=i;
        }
        return num;
    }

    private void calculate() {
        double n1=0;
        double n2=0;
        double result=0;
        if(num1str.isEmpty()){
            if(number.charAt(0)=='-'){
                n2=nagetive(number);
            }else{
                n2=percent(number);
            }
            if((operator=="!"||operator=="√")&&n2<=0){
                Toast.makeText(MainActivity.this,"请输入正数，屏幕上输出为正数的情况",Toast.LENGTH_SHORT).show();
                n2*=-1;
            }

            switch (operator) {
                case "sin":
                    result = Math.sin(n2);
                    break;
                case "cos":
                    result = Math.cos(n2);
                    break;
                case "tan":
                    result = Math.tan(n2);
                    break;
                case "!":
                    result = getA(n2);
                    break;
                case "√":
                    result = Math.sqrt(n2);
                    break;
                default:
                    break;
            }
            //num2Str="";
        }
        //
        if (!num1str.isEmpty() && !number.isEmpty() && !operator.isEmpty()) {

            if(num1str.charAt(0)=='-'||number.charAt(0)=='-'){
                n1=nagetive(num1str);
                n2=nagetive(number);

            }else{
                n1=percent(num1str);
                n2=percent(number);
            }
            if(operator=="÷"&&n2==0){
                Toast.makeText(MainActivity.this,"请输入正数，屏幕上输出为除数为1的情况",Toast.LENGTH_SHORT).show();
                n2=1;
            }
           // System.out.println(number);
            switch (operator) {
                case "+":
                    result = n1 + n2;
                    break;
                case "-":
                    result = n1 - n2;
                    break;
                case "×":
                    result = n1 * n2;
                    break;
                case "÷":
                    result = n1 / n2;
                    break;
                default:
                    break;
            }
            //num1Str="";
            //num2Str="";
            //operator="";
        }
        String str = String.format("%.2f", result);
        //String str = String.valueOf(result);
        display.setText(str);
    }

    private void reset() {
        num1str = "";
        number = "";
        operator = "";
        display.setText("");
    }
}