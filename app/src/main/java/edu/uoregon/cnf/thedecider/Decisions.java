package edu.uoregon.cnf.thedecider;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Decisions extends AppCompatActivity implements TextView.OnEditorActionListener, View.OnClickListener {

    EditText decisionDescriptionEditText;
    EditText firstChoiceEditText;
    EditText secondChoiceEditText;
    EditText thirdChoiceEditText;
    EditText fourthChoiceEditText;
    EditText fifthChoiceEditText;
    EditText sixthChoiceEditText;
    Button saveChangesButton;

    Decision decision;
    ArrayList<Choice> choices;
    Choice choice1;
    Choice choice2;
    Choice choice3;
    Choice choice4;
    Choice choice5;
    Choice choice6;

    TheDeciderDB db;

    ArrayList<EditText> editTexts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decisions);

        decisionDescriptionEditText = (EditText)findViewById(R.id.editDecisionNameEditText);
        firstChoiceEditText = (EditText)findViewById(R.id.firstChoiceEditText);
        secondChoiceEditText = (EditText)findViewById(R.id.secondChoiceEditText);
        thirdChoiceEditText = (EditText)findViewById(R.id.thirdChoiceEditText);
        fourthChoiceEditText = (EditText)findViewById(R.id.fourthChoiceEditText);
        fifthChoiceEditText = (EditText)findViewById(R.id.fifthChoiceEditText);
        sixthChoiceEditText = (EditText)findViewById(R.id.sixthChoiceEditText);

        saveChangesButton = (Button)findViewById(R.id.saveEditDecisionButton);

        decisionDescriptionEditText.setOnEditorActionListener(this);
        firstChoiceEditText.setOnEditorActionListener(this);
        secondChoiceEditText.setOnEditorActionListener(this);
        thirdChoiceEditText.setOnEditorActionListener(this);
        fourthChoiceEditText.setOnEditorActionListener(this);
        fifthChoiceEditText.setOnEditorActionListener(this);
        sixthChoiceEditText.setOnEditorActionListener(this);

        editTexts = new ArrayList<EditText>();

        editTexts.add(firstChoiceEditText);
        editTexts.add(secondChoiceEditText);
        editTexts.add(thirdChoiceEditText);
        editTexts.add(fourthChoiceEditText);
        editTexts.add(fifthChoiceEditText);
        editTexts.add(sixthChoiceEditText);

        saveChangesButton.setOnClickListener(this);

        db = new TheDeciderDB(this);

        Intent intent = getIntent();

        String selectedDecision = intent.getStringExtra("decision");

        if(selectedDecision != null)
        {
            decision = db.getDecisionByName(intent.getStringExtra("decision"));
            decisionDescriptionEditText.setText(decision.getDescription());
            if(decision != null) {
                choices = db.getChoicesByDecisionID(decision.getDecisionID());

                for(int i=0; i< choices.size(); i++)
                {
                    editTexts.get(i).setText(choices.get(i).getDescription());
                }
            }
        }
    }



    public void saveChanges()
    {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.saveEditDecisionButton:
                saveChanges();
                break;
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        int keyCode = -1;
        if (event != null) {
            keyCode = event.getKeyCode();
        }
        if (actionId == EditorInfo.IME_ACTION_DONE ||
                actionId == EditorInfo.IME_ACTION_UNSPECIFIED ||
                keyCode == KeyEvent.KEYCODE_DPAD_CENTER ||
                keyCode == KeyEvent.KEYCODE_ENTER) {
            // Do something
        }
        return false;
    }
}
