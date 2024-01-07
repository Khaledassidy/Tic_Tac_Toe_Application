package com.example.tic_tac_toe;



import androidx.appcompat.app.AppCompatActivity;



import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Play extends AppCompatActivity implements View.OnClickListener {

    TextView text1, text2, win1, win2;
    Button rest, shr;
    boolean flag=true;
    boolean player1 = true;
    int player1point = 0;
    int player2point = 0;
    int roundcount = 0;
    ImageView crs1, zr1;
    String name1, name2;
    Button[][] buttons = new Button[3][3];
    LinearLayout line1,line2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
        win1 = findViewById(R.id.win1);
        win2 = findViewById(R.id.win2);
        rest = findViewById(R.id.Restart);
        shr = findViewById(R.id.share);
        crs1 = findViewById(R.id.cross1);
        zr1 = findViewById(R.id.zero1);
        line1=findViewById(R.id.anime1);
        line2=findViewById(R.id.anime2);
        Intent intent = getIntent();
        name1 = intent.getStringExtra("name1");
        name2 = intent.getStringExtra("name2");
        win1.setText("" + name1 + " 0");
        win2.setText("" + name2 + " 0");
        text1.setText(" " + name1);
        text2.setText(" " + name2);


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String id = "Btn" + i + j;
                int res;
                res = getResources().getIdentifier(id,
                        "id",
                        getPackageName());
                buttons[i][j] = findViewById(res);
                buttons[i][j].setOnClickListener(this);


            }
        }

        line1.setBackgroundResource(R.drawable.tv_border);
        rest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                restart();


            }
        });
        shr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share();
            }
        });




    }

    public void share(){
        String y = thewinner();
        Intent x = new Intent();
        x.setAction(Intent.ACTION_SEND);
        x.setType("text/plain");
        x.putExtra(Intent.EXTRA_TEXT,"The scores "+name1+" "+ player1point+" "+name2+" "+player2point+" "+ y );
        startActivity(Intent.createChooser(x,"chooser"));
    }



    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        } else {
            music();
            if (player1) {
                line2.setBackgroundResource(R.drawable.tv_border);
                line1.setBackgroundResource(R.drawable.stable);
                ((Button) v).setText("X");



            } else {
                line1.setBackgroundResource(R.drawable.tv_border);
                line2.setBackgroundResource(R.drawable.stable);
                ((Button) v).setText("O");

            }

            roundcount++;
        }

        if (checkforwin()) {
            if (player1) {
                player1win();
            } else {
                player2win();
            }
        } else if (roundcount == 9) {
            draw();
        } else {
            player1 = !player1;
        }
    }

    public boolean checkforwin() {
        String[][] x = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                x[i][j] = buttons[i][j].getText().toString();
            }
        }
        for (int i = 0; i < 3; i++) {
            if (x[i][0].equals(x[i][1]) && x[i][1].equals(x[i][2]) && !x[i][0].equals("")) {

                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (x[0][i].equals(x[1][i]) && x[1][i].equals(x[2][i]) && !x[0][i].equals("")) {

                return true;
            }
        }
        if (x[0][0].equals(x[1][1]) && x[1][1].equals(x[2][2]) && !x[0][0].equals("")) {

            return true;
        }
        if (x[0][2].equals(x[1][1]) && x[0][2].equals(x[2][0]) && !x[0][2].equals("")) {

            return true;
        }

        return false;
    }

    public void player1win() {
        String c= name1;
        player1point++;
        Toast.makeText(this, " "+c+" win", Toast.LENGTH_SHORT).show();
        updatePointtext();
        restboards();
    }

    public void player2win() {
        String c= name2;
        player2point++;
        Toast.makeText(this, " "+c+" win", Toast.LENGTH_SHORT).show();
        updatePointtext();
        restboards();
    }

    public void draw() {
        Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show();
        restboards();
    }


    public void updatePointtext() {

        win1.setText(name1 + " " + player1point);
        win2.setText(name2 + " " + player2point);
    }

    public void restboards() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        line1.setBackgroundResource(R.drawable.tv_border);
        line2.setBackgroundResource(R.drawable.stable);
        player1 = true;
        roundcount = 0;

    }

    public void restart() {
        player1point = 0;
        player2point = 0;
        updatePointtext();
        restboards();

    }

    public String thewinner() {
        if (player1point > player2point) {
            return (String)name1 + "Win";
        } else if (player1point < player2point) {
            return (String)name2 + " Win";

        } else {
            return "draw";

        }


    }

    public void music(){
        MediaPlayer mediaPlayer=MediaPlayer.create(this,R.raw.pop);
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }
        });
    }


}







    


