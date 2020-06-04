package com.genesis.colourgametask;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static com.genesis.colourgametask.R.id.view1;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    View view1,view2,view3,view4;
    TextView Score;
    int randomnumber;
    Button StartGame;
    int score;
    Timer t;
    boolean status =true;
    Boolean GameStartStatus=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        view1 = (View)findViewById(R.id.view1);
        view2 = (View)findViewById(R.id.view2);
        view3 = (View)findViewById(R.id.view3);
        view4 = (View)findViewById(R.id.view4);
        Score = (TextView)findViewById(R.id.score);
        StartGame = (Button)findViewById(R.id.game);


        StartGame.setOnClickListener(this);
        view1.setOnClickListener(this);
        view2.setOnClickListener(this);
        view3.setOnClickListener(this);
        view4.setOnClickListener(this);

        t = new Timer();

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.game:
                t = new Timer();
                GameStartStatus=true;
                t.scheduleAtFixedRate(new TimerTask() {
                                          @Override
                                          public void run() {
                                              //Called each time when 1000 milliseconds (1 second) (the period parameter)
                                              if(status) {
                                                  chnageColour();
                                              }else{
                                                  DialogBox();
                                              }
                                          }
                                          },
                        0, 700);
                break;
            case R.id.view1:
                if(GameStartStatus) {
                    CheckClick(1);
                    status=true;

                }else{
                    showToast();
                }
                 break;
                 case R.id.view2:
                     if(GameStartStatus) {
                         CheckClick(2);
                         status=true;

                     }else {
                         showToast();
                     }
                     break;
                case R.id.view3:
                    if(GameStartStatus) {
                        CheckClick(3);
                        status=true;

                    }else {
                        showToast();
                    }
                    break;
                case R.id.view4:
                    if(GameStartStatus) {
                        CheckClick(4);
                        status=true;

                    }else {
                        showToast();
                    }
                    break;
        }
    }

    private void showToast() {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
        Toast.makeText(MainActivity.this," Tap on Start Game button first",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void CheckClick(int i) {
        if(randomnumber == i){
            Score.setText("Score : " + (++score));
        }else{
            DialogBox();
        }
    }

    private void chnageColour() {
        status=false;
        this.runOnUiThread(new Runnable() {
            public void run() {
//                Toast.makeText(MainActivity.this,"Game Started",Toast.LENGTH_SHORT).show();
                Random r = new Random();
                int i1 = r.nextInt(5 - 1) + 1;
                Log.d("Delay ", String.valueOf(i1));
                if(i1==randomnumber){
                    if(i1==4 || i1==3){
                        i1= --i1;
                    }else{
                        if(i1==1 | i1==2){
                            i1=++i1;
                        }
                    }
                }
                SwitchColor(i1);
                randomnumber=i1;

            }
        });
    }

    private void SwitchColor(int i1) {
        view1.setBackgroundResource(android.R.color.holo_orange_dark);
        view2.setBackgroundResource(android.R.color.holo_blue_dark);
        view3.setBackgroundResource(android.R.color.holo_orange_light);
        view4.setBackgroundResource(android.R.color.holo_green_dark);

        switch (i1) {
            case 1:
                view1.setBackgroundResource(R.color.grey);
                break;
            case 2:
                view2.setBackgroundResource(R.color.grey);
                break;
            case 3:
                view3.setBackgroundResource(R.color.grey);
                break;
            case 4:
                view4.setBackgroundResource(R.color.grey);
                break;
        }
    }


  public void  DialogBox(){
//      new Handler().post(new Runnable() {
//          @Override
//          public void run() {
//              // add your code here
//              SwitchColor(0);
//              StartGame.setEnabled(false);
//          }
//      });

     runOnUiThread(new Runnable() {
          public void run() {
              t.cancel();

              SwitchColor(0);
              StartGame.setEnabled(false);

              new AlertDialog.Builder(MainActivity.this)
                      .setTitle("Game Over")
                      .setMessage(" Your Score : " + score)
                      // Specifying a listener allows you to take an action before dismissing the dialog.
                      // The dialog is automatically dismissed when a dialog button is clicked.
                      .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                          public void onClick(DialogInterface dialog, int which) {
                              // Continue with delete operation

//                      new Handler().post(new Runnable() {
//                          @Override
//                          public void run() {
//                              // add your code here
//                              StartGame.setEnabled(true);
//                              Score.setText("Score : " + 0);
//                          }
//                      });
//                              runOnUiThread(new Runnable() {
//                                  public void run() {
                                      StartGame.setEnabled(true);
                                      Score.setText("Score : " + 0);
//                          }
//                              });
                          }
                      })
                      .setCancelable(false)
                      // A null listener allows the button to dismiss the dialog and take no further action.
//              .setNegativeButton(android.R.string.no, null)
                      .setIcon(android.R.drawable.ic_dialog_alert)
                      .show();

              score=0;
              randomnumber=-1;
              GameStartStatus = false;
              status=true;

          }
      });



  }

}
