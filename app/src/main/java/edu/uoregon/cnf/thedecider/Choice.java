package edu.uoregon.cnf.thedecider;

/**
 * Created by Chris on 7/11/2016.
 */
public class Choice {
    private int choiceID;
    private int decisionID;
    private String description;

    public Choice ()
    {
        description = "";
    }

    public Choice(String description)
    {
        this.description = description;
    }

    public int getChoiceID() {
        return choiceID;
    }

    public void setChoiceID(int choiceID) {
        this.choiceID = choiceID;
    }

    public int getDecisionID() {
        return decisionID;
    }

    public void setDecisionID(int decisionID) {
        this.decisionID = decisionID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
