package com.example.baocaoandroid;

import android.provider.BaseColumns;

public final class Table {
    private Table(){}
    //Bảng dữ liệu câu hỏi
    public static class QuestionTable implements BaseColumns{
        //Tên bảng
        public static final String Table_name = "questions";
        //Câu hỏi
        public static final String Columm_question = "question";
        //4 câu đáp án
        public static final String Column_options1 = "options1";
        public static final String Column_options2 = "options2";
        public static final String Column_options3 = "options3";
        public static final String Column_options4 = "options4";
        //Đáp án
        public static final String Column_answer = "answer";
    }
    //Bảng dữ liệu kết quả
    public static class ResultTable implements BaseColumns{
        //Tên bảng
        public static final String Table_name = "result";
        //Điểm
        public static final String Column_score = "score";
        //Thời gian
        public static final String Column_time = "time";
    }
}
