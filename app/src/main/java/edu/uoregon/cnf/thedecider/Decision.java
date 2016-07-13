package edu.uoregon.cnf.thedecider;

/**
 * Created by Chris on 7/11/2016.
 */
public class Decision {

    private int decisionID;
    private String description;
    int numChoices;

    public Decision() {
    }

    public Decision(String text) {
        this.description = text;
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

    public void setDescription(String text) {
        this.description = text;
    }

    public int getNumChoices() {
        return numChoices;
    }

    public void setNumChoices(int numChoices) {
        this.numChoices = numChoices;
    }
}
