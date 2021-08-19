package com.example.braintrainer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button Start;
    TextView Result;
    ArrayList<Integer> answers=new ArrayList<Integer>();
    int locOfCorrectAns;
    int score=0;
    int noOfQues=0;
    TextView Score;
    Button Ans1;
    Button Ans2;
    Button Ans3;
    Button Ans4;
    TextView Sum;
    TextView Timer;
    boolean gameIsActive=true;
    Button playAgain;
    ConstraintLayout consLayout;

    public void PlayAgain(View view)
    {
        score=0;
        noOfQues=0;
        gameIsActive=true;

        Timer.setText("30s");
        Score.setText("0/0");
        Result.setText("");
        playAgain.setVisibility(View.INVISIBLE);

        genQues();

        new CountDownTimer(30100,1000)
        {

            @Override
            public void onTick(long millisUntilFinished) {
                Timer.setText(String.valueOf(millisUntilFinished/1000)+"s");

            }

            @Override
            public void onFinish() {
                playAgain.setVisibility(View.VISIBLE);
                Timer.setText("0s");
                Result.setText("Your Score is: "+ Integer.toString(score) + "/" + Integer.toString(noOfQues));
                gameIsActive=false;

            }
        }.start();
    }

    public  void genQues()
    {
        Random rand=new Random();
        int a=rand.nextInt(21);
        int b=rand.nextInt(21);

        Sum.setText(Integer.toString(a) + "+" + Integer.toString(b));

        locOfCorrectAns=rand.nextInt(4);
        answers.clear();
        int incorrectAns;

        for(int i=0;i<4;i++)
        {
            if(i==locOfCorrectAns)
            {
                answers.add(a+b);
            }
            else
            {
                incorrectAns=rand.nextInt(41);
                while(incorrectAns== a+b)
                {
                    incorrectAns=rand.nextInt(41);
                }
                answers.add(incorrectAns);
            }
        }
        Ans1.setText(Integer.toString(answers.get(0)));
        Ans2.setText(Integer.toString(answers.get(1)));
        Ans3.setText(Integer.toString(answers.get(2)));
        Ans4.setText(Integer.toString(answers.get(3)));

    }
    public void chooseAns(View view)
    {
        if (gameIsActive==true)
        {
            //Log.i("Tag", (String) view.getTag());
            if (view.getTag().toString().equals(Integer.toString(locOfCorrectAns))) {
                //Log.i("Correct","Corect");
                score++;
                Result.setText("Correct!");
            } else {
                Result.setText("Incorrect!!");
            }
            noOfQues++;
            Score.setText(Integer.toString(score) + "/" + Integer.toString(noOfQues));
            genQues();
        }
    }

    public void start(View view)
    {

        Start.setVisibility(View.INVISIBLE);
        consLayout.setVisibility(View.VISIBLE);

        PlayAgain(findViewById(R.id.playAgain));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Start=(Button) findViewById(R.id.Start);
        Sum=(TextView)findViewById(R.id.Sum);
        Ans1=(Button)findViewById(R.id.Ans1);
        Ans2=(Button)findViewById(R.id.Ans2);
        Ans3=(Button)findViewById(R.id.Ans3);
        Ans4=(Button)findViewById(R.id.Ans4);
        Result=(TextView)findViewById(R.id.Result);
        Score=(TextView)findViewById(R.id.Score);
        Timer=(TextView)findViewById(R.id.Timer);
        playAgain=(Button) findViewById(R.id.playAgain);
        consLayout=(ConstraintLayout)findViewById(R.id.consLayout);

        PlayAgain(findViewById(R.id.playAgain));
        new CountDownTimer(30100,1000)
        {

            @Override
            public void onTick(long millisUntilFinished) {
                Timer.setText(String.valueOf(millisUntilFinished/1000)+"s");

            }

            @Override
            public void onFinish() {
                playAgain.setVisibility(View.VISIBLE);
                Timer.setText("0s");
                Result.setText("Your Score is: "+ Integer.toString(score) + "/" + Integer.toString(noOfQues));
                gameIsActive=false;

            }
        }.start();

    }
}