package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class
MainActivity extends AppCompatActivity {

    TextView wlcm,playername;
    ImageView crs,zer;
    EditText name1,name2;
    Button Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wlcm=findViewById(R.id.welcome);
        playername=findViewById(R.id.playername);
        crs=findViewById(R.id.cross);
        zer=findViewById(R.id.zero);
        name1=findViewById(R.id.nameplayer1);
        name2=findViewById(R.id.nameplayer2);
        Btn=findViewById(R.id.startgame);

        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namepl1=name1.getText().toString();
                String namepl2=name2.getText().toString();
             if(namepl1.isEmpty() || namepl2.isEmpty()){
                 Toast.makeText(MainActivity.this, "Please enter the names of players", Toast.LENGTH_SHORT).show();
             }
             else{
                 gotoactivity2(namepl1,namepl2);
             }
            }
        });

    }
    public void gotoactivity2(String x,String y){
        Intent intent = new Intent(this, Play.class);
        intent.putExtra("name1",x);
        intent.putExtra("name2",y);
        startActivity(intent);




    }


}