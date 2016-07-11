package edu.uoregon.cnf.thedecider;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Intro extends AppCompatActivity implements View.OnClickListener {

    Button rpsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rpsButton = (Button)findViewById(R.id.rpsButton);

        rpsButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rpsButton:
                Intent intent = new Intent(this, RPS.class);
                startActivity(intent);
        }
    }
}
