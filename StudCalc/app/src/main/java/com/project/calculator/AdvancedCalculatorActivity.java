package com.project.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.mariuszgromada.math.mxparser.*;

public class AdvancedCalculatorActivity extends Activity {

    Button button0, button1, button2, button3, button4, button5,
            button6, button7, button8, button9, buttonC,
            buttonChange, buttonBcsp, buttonDiv, buttonMultiply, buttonMinus,
            buttonPlus, buttonResult, buttonDot, buttonSin, buttonCos,
            buttonTan, buttonLn, buttonSqrt, buttonPow2, buttonPowN, buttonLog;

    TextView editText;
    TextView wholeText;

    int cButtonCounter = 0;
    boolean clicked = false;
    boolean blockOperations = false;
    boolean trygFunction = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_calculator);

        editText = (TextView) findViewById(R.id.resultText);
        wholeText = (TextView) findViewById(R.id.allText);

        buttonBcsp = (Button) findViewById(R.id.signBksp);
        buttonC = (Button) findViewById(R.id.signC);
        buttonChange = (Button) findViewById(R.id.signOperation);
        buttonDiv = (Button) findViewById(R.id.signDiv);
        buttonMultiply = (Button) findViewById(R.id.signMultiple);
        buttonMinus = (Button) findViewById(R.id.signMinus);
        buttonPlus = (Button) findViewById(R.id.signPlus);
        buttonResult = (Button) findViewById(R.id.signSum);
        buttonDot = (Button) findViewById(R.id.signDot);
        buttonSin = (Button) findViewById(R.id.signSin);
        buttonCos = (Button) findViewById(R.id.signCos);
        buttonTan = (Button) findViewById(R.id.signTan);
        buttonLn = (Button) findViewById(R.id.signLn);
        buttonSqrt = (Button) findViewById(R.id.signSqrt);
        buttonPow2 = (Button) findViewById(R.id.signPow2);
        buttonPowN = (Button) findViewById(R.id.signPowN);
        buttonLog = (Button) findViewById(R.id.signLog);

        button0 = (Button) findViewById(R.id.sign0);
        button1 = (Button) findViewById(R.id.sign1);
        button2 = (Button) findViewById(R.id.sign2);
        button3 = (Button) findViewById(R.id.sign3);
        button4 = (Button) findViewById(R.id.sign4);
        button5 = (Button) findViewById(R.id.sign5);
        button6 = (Button) findViewById(R.id.sign6);
        button7 = (Button) findViewById(R.id.sign7);
        button8 = (Button) findViewById(R.id.sign8);
        button9 = (Button) findViewById(R.id.sign9);

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumberToEditText("0");
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumberToEditText("1");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumberToEditText("2");
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumberToEditText("3");
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumberToEditText("4");
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumberToEditText("5");
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumberToEditText("6");
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumberToEditText("7");
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumberToEditText("8");
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumberToEditText("9");
            }
        });

        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                executeOperation("+");
            }
        });

        buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executeOperation("-");
            }
        });

        buttonMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                executeOperation("*");
            }
        });

        buttonDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                executeOperation("/");
            }
        });

        buttonDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!blockOperations) {
                    cButtonCounter = 0;
                    if (editText.getText().toString().length() > 0) {
                        if (Character.isDigit(editText.getText().charAt(editText.getText().toString().length() - 1))) {
                            if (!editText.getText().toString().contains(".")) {
                                editText.setText(editText.getText() + ".");
                            }
                        }
                    }
                }
            }
        });


        buttonResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!editText.getText().toString().equals("Błąd")) {
                    if (trygFunction)
                        cButtonCounter = 0;
                    else if (!blockOperations) {
                        addNumberToAllText();
                        cButtonCounter = 0;
                    }
                    if (wholeText.getText().toString().length() > 0) {
                        setResult();
                        wholeText.setText("");
                    }
                    blockOperations = false;
                    clicked = true;
                    trygFunction = false;
                }
            }
        });

        buttonBcsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!editText.getText().toString().equals("Błąd")) {
                    if (!blockOperations) {
                        cButtonCounter = 0;
                        if (editText.getText().toString().length() > 0) {
                            editText.setText(editText.getText().toString().substring(0, editText.getText().toString().length() - 1));
                        }
                    }
                }
            }
        });

        buttonChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!editText.getText().toString().equals("Błąd")) {
                    if (!blockOperations) {
                        cButtonCounter = 0;
                        if (editText.getText().toString().length() > 0) {
                            editText.setText(String.valueOf(Double.parseDouble(editText.getText().toString()) * (-1.0)));
                        }
                    }
                }
            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cButtonCounter++;
                if (cButtonCounter > 1) {
                    editText.setText("");
                    wholeText.setText("");
                    cButtonCounter = 0;
                    blockOperations = false;
                    trygFunction = false;
                } else {
                    editText.setText("");
                }

            }
        });

        buttonSin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!editText.getText().toString().equals("Błąd")) {
                    if (!trygFunction) {
                        if (editText.length() > 0) {
                            trygFunction = true;
                            clicked = false;
                            wholeText.setText(wholeText.getText() + "sin(" + editText.getText() + ")");
                            setResult();
                        }
                    }
                }


            }
        });

        buttonCos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!editText.getText().toString().equals("Błąd")) {
                    if (!trygFunction) {
                        if (editText.length() > 0) {
                            trygFunction = true;
                            clicked = false;
                            wholeText.setText(wholeText.getText() + "cos(" + editText.getText() + ")");
                            setResult();
                        }
                    }
                }


            }
        });

        buttonTan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!editText.getText().toString().equals("Błąd")) {
                    if (!trygFunction) {
                        if (editText.length() > 0) {
                            trygFunction = true;
                            clicked = false;
                            wholeText.setText(wholeText.getText() + "tan(" + editText.getText() + ")");
                            setResult();
                        }
                    }
                }
            }
        });

        buttonLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!editText.getText().toString().equals("Błąd")) {
                    if (!trygFunction) {
                        if (editText.length() > 0) {
                            trygFunction = true;
                            clicked = false;
                            wholeText.setText(wholeText.getText() + "log10(" + editText.getText() + ")");
                            setResult();
                        }
                    }
                }


            }
        });

        buttonLn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!editText.getText().toString().equals("Błąd")) {
                    if (!trygFunction) {
                        if (editText.length() > 0) {
                            trygFunction = true;
                            clicked = false;
                            wholeText.setText(wholeText.getText() + "ln(" + editText.getText() + ")");
                            setResult();
                        }
                    }
                }


            }
        });

        buttonSqrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!editText.getText().toString().equals("Błąd")) {
                    if (!trygFunction) {
                        if (editText.length() > 0) {
                            trygFunction = true;
                            clicked = false;
                            wholeText.setText(wholeText.getText() + "sqrt(" + editText.getText() + ")");
                            setResult();
                        }
                    }
                }


            }
        });

        buttonPow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!editText.getText().toString().equals("Błąd")) {
                    if (!trygFunction) {
                        if (editText.length() > 0) {
                            trygFunction = true;
                            clicked = false;
                            wholeText.setText(wholeText.getText().toString() + editText.getText() + "^2");
                            setResult();
                        }
                    }
                }


            }
        });

        buttonPowN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!editText.getText().toString().equals("Błąd")) {
                    if (!trygFunction) {
                        if (editText.length() > 0) {
                            clicked = false;
                            wholeText.setText(wholeText.getText().toString() + editText.getText() + "^");
                        }
                    }
                }

            }
        });

        if (savedInstanceState != null) {
            String aText = savedInstanceState.getString("wholeText");
            String eText = savedInstanceState.getString("editText");
            clicked = savedInstanceState.getBoolean("clicked");
            wholeText.setText(String.valueOf(aText));
            editText.setText(String.valueOf(eText));
        }

    }

    public void setResult() {

        String expression = wholeText.getText().toString();

        Expression e = new Expression(expression);

        if (!Double.isNaN(e.calculate())) {
            String result = String.valueOf(e.calculate());
            editText.setText(result);
            blockOperations = false;
        } else {
            if (wholeText.getText().toString().contains("/0")) {
                Toast.makeText(getApplicationContext(), "Nie można dzielić przez zero", Toast.LENGTH_LONG).show();
            }

            editText.setText("Błąd");
            blockOperations = true;
        }

    }

    public void addNumberToAllText() {

        if (editText.getText().toString().endsWith(".")) {
            editText.setText(editText.getText().subSequence(0, editText.length() - 1));
        }
        if (!editText.getText().toString().startsWith("-"))
            wholeText.setText(wholeText.getText() + editText.getText().toString());
        else
            wholeText.setText(wholeText.getText() + "(" + editText.getText().toString() + ")");
    }

    public void addNumberToEditText(String number) {

        if (!editText.getText().toString().equals("Błąd")) {
            if (!blockOperations && !trygFunction) {
                if (clicked) {
                    if (number.equals("0") && editText.getText().toString().equals("0")) {

                    } else
                        editText.setText(editText.getText() + number);
                } else
                    editText.setText(number);
                clicked = true;
            }
        }
    }

    public void executeOperation(String operation) {

        int index;

        if (editText.getText().toString().contains("-"))
            index = 2;
        else
            index = 1;
        if (!editText.getText().toString().equals("Błąd")) {
            if (clicked && !trygFunction) {
                if (!blockOperations) {
                    addNumberToAllText();
                    cButtonCounter = 0;
                    if (wholeText.getText().toString().length() > 0) {
                        if (Character.isDigit(wholeText.getText().charAt(wholeText.getText().toString().length() - index))) {
                            setResult();
                            wholeText.setText(wholeText.getText() + operation);
                            clicked = false;
                        }
                    }
                }
            } else if (wholeText.getText().toString().length() > 0) {
                if (Character.isDigit(wholeText.getText().charAt(wholeText.getText().toString().length() - 1)) || wholeText.getText().toString().endsWith(")")) {
                    if (!blockOperations) {
                        wholeText.setText(wholeText.getText() + operation);
                        trygFunction = false;
                        clicked = false;
                    } else {
                        wholeText.setText(wholeText.getText().toString().substring(0, wholeText.getText().toString().length() - 2) + operation);
                        trygFunction = false;
                        clicked = false;
                    }
                }
            }
            if (editText.getText().toString().length() > 0 && wholeText.getText().toString().length() > 0) {
                wholeText.setText(wholeText.getText().toString().substring(0, wholeText.getText().length() - 1) + operation);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("wholeText", wholeText.getText().toString());
        outState.putString("editText", editText.getText().toString());
        outState.putBoolean("clicked", clicked);
    }

}