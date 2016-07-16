package edu.uoregon.cnf.thedecider;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class Intro extends AppCompatActivity implements View.OnClickListener {

    Button rpsButton;
    Button rouletteButton;
    Button plinkoButton;
    Button newDecisionButton;
    Spinner savedDecisionsSpinner;
    TheDeciderDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rpsButton = (Button)findViewById(R.id.rpsButton);
        rouletteButton = (Button)findViewById(R.id.rouletteButton);
        plinkoButton = (Button)findViewById(R.id.plinkoButton);
        newDecisionButton = (Button)findViewById(R.id.addDecisionButton);

        rpsButton.setOnClickListener(this);
        rouletteButton.setOnClickListener(this);
        plinkoButton.setOnClickListener(this);
        newDecisionButton.setOnClickListener(this);

        db = new TheDeciderDB(this);

        ArrayList<Decision> decisions = db.getDecisions();

        String[] decisionDescriptions = new String[decisions.size()];
        for(int i=0; i < decisions.size(); i++)
        {
            decisionDescriptions[i] = decisions.get(i).getDescription();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, decisionDescriptions);

        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        savedDecisionsSpinner = (Spinner)findViewById(R.id.decisionsSpinner);
        savedDecisionsSpinner.setAdapter(adapter);

    }

    protected void startNewDecision()
    {
        Intent intent = new Intent(this, Decisions.class);
        startActivity(intent);
    }

    protected void startRPS()
    {
        String selectedDecision = savedDecisionsSpinner.getSelectedItem().toString();
        Intent intent = new Intent(this, RPS.class);
        intent.putExtra("decision", selectedDecision);
        startActivity(intent);
    }

    protected void startRoulette()
    {
        String selectedDecision = savedDecisionsSpinner.getSelectedItem().toString();
        Intent intent = new Intent(this, Roulette.class);
        intent.putExtra("decision", selectedDecision);
        startActivity(intent);
    }

    protected void startPlinko()
    {
        String selectedDecision = savedDecisionsSpinner.getSelectedItem().toString();
        Intent intent = new Intent(this, Plinko.class);
        intent.putExtra("decision", selectedDecision);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addDecisionButton:
                startNewDecision();
                break;
            case R.id.rpsButton:
                startRPS();
                break;
            case R.id.rouletteButton:
                startRPS();
                break;
            case R.id.plinkoButton:
                startRPS();
                break;
        }
    }
}
