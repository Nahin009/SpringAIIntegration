package com.example.demo.DTO.pdf;

//{
//        "topic" : "ababa",
//        "score" : "2",
//        "date" : "ababa",
//        "q1" : "aaaa",
//        "q1o1" : "ababa",
//        "q1o2" : "ababa",
//        "q1o3" : "ababa",
//        "q1o4" : "ababa",
//        "q1des" : "ndjcndcjdncjncjsncjsdn",
//        "q1correctAns" : "c",
//        "q1ans" : "c",
//        "q1status" : "1",
//        "q2" : "bbbb",
//        "q2o1" : "ababa",
//        "q2o2" : "ababa",
//        "q2o3" : "ababa",
//        "q2o4" : "ababa",
//        "q2des" : "adskjn",
//        "q2correctAns" : "b",
//        "q2ans" : "c",
//        "q2status" : "-1"
//}


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class data {
    private String topic;
    private String score;
    private String date;
    private String q1;
    private String q1o1;
    private String q1o2;
    private String q1o3;
    private String q1o4;
    private String q1des;
    private String q1correctAns;
    private String q1ans;
    private String q1status;
    private String q2;
    private String q2o1;
    private String q2o2;
    private String q2o3;
    private String q2o4;
    private String q2des;
    private String q2correctAns;
    private String q2ans;
    private String q2status;
}
