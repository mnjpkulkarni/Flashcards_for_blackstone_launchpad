package com.mnjpk.flashcards.flashcards;

import java.io.Serializable;

/**
 * Created by manojkulkarni on 9/17/17.
 */

public class Flashcards implements Serializable{

    String word;

    String grammar;
    String definition;

    public Flashcards() {
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getGrammar() {
        return grammar;
    }

    public void setGrammar(String grammar) {
        this.grammar = grammar;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }


}
