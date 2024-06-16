package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

//{
    //question : string;
    //option1: string;
    //option2: string;
    //option3: string;
    //option4: string;
    //correctOptionNo: int;
    //solutionDescription: string;
//}

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Question {
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private int correctOptionNo;
    private String solutionDescription;

    @Override
    public String toString() {
        return "Question: " + question + "\n" +
                "Option1: " + option1 + "\n" +
                "Option2: " + option2 + "\n" +
                "Option3: " + option3 + "\n" +
                "Option4: " + option4 + "\n" +
                "CorrectOptionNo: " + correctOptionNo + "\n" +
                "Description: " + solutionDescription + "\n";
    }
}
