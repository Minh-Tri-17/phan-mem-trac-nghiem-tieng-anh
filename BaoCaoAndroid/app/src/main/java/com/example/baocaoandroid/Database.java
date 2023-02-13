package com.example.baocaoandroid;

import static com.example.baocaoandroid.Table.QuestionTable.*;
import static com.example.baocaoandroid.Table.ResultTable.Column_score;
import static com.example.baocaoandroid.Table.ResultTable.Column_time;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.baocaoandroid.Table;
import com.example.baocaoandroid.model.Question;
import com.example.baocaoandroid.model.Result;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper{
    private static final String Database_name = "Question.db";

    private static final int Version = 1;

    private SQLiteDatabase db;

    public Database(@Nullable Context context) {
        super(context, Database_name,  null, Version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        this.db = sqLiteDatabase;
        final String Question_Table = " CREATE TABLE "+
                Table_name + " ( "+
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                Columm_question + " VARCHAR(500), "+
                Column_options1 + " VARCHAR(500), "+
                Column_options2 + " VARCHAR(500), "+
                Column_options3 + " VARCHAR(500), "+
                Column_options4 + " VARCHAR(500), "+
                Column_answer + " INTEGER ) ";

        final String Result_Table = " CREATE TABLE "+
                Table.ResultTable.Table_name + " ( "+
                Table.ResultTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                Column_score + " INTEGER, "+
                Table.ResultTable.Column_time + " VARCHAR(100) ) ";

        db.execSQL(Question_Table);
        db.execSQL(Result_Table);
        CreateQuetion();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ Table_name);
        db.execSQL("DROP TABLE IF EXISTS "+ Table.ResultTable.Table_name);
        onCreate(db);
    }

    //insert kết quả
    public void insertResult(Result result){
        ContentValues values = new ContentValues();

        values.put(Column_score,result.getScore());
        values.put(Column_time,result.getTime());
        try {
            db = getWritableDatabase();
            db.insert(Table.ResultTable.Table_name,null,values);
        }
        catch (Exception ex){
            Log.e(Table.ResultTable.Table_name,ex.getMessage());
        }
    }
    //tạo dữ liệu cho bảng kết quả
    private void CreateResult(){
        Result result1 = new Result(3,"9/12/22022");
        insertResult(result1);
        Result result2 = new Result(2,"9/12/22022");
        insertResult(result2);
        Result result3 = new Result(7,"9/12/22022");
        insertResult(result3);
        Result result4 = new Result(4,"9/12/22022");
        insertResult(result4);
        Result result5 = new Result(10,"10/12/22022");
        insertResult(result5);
    }
    //insert câu hỏi và đáp án vào csdl
    private void insertQuetion(Question question){
        ContentValues values = new ContentValues();

        values.put(Columm_question,question.getQuestion());
        values.put(Column_options1,question.getOptions1());
        values.put(Column_options2,question.getOptions2());
        values.put(Column_options3,question.getOptions3());
        values.put(Column_options4,question.getOptions4());
        values.put(Column_answer,question.getAnswer());

        db.insert(Table_name,null,values);
    }
    //tạo dữ liệu cho bảng câu hỏi
    private void CreateQuetion(){
        Question question1 = new Question(" 1. Who are all ________ people?",
                "A. this",
                "B. those",
                "C. them",
                "D. that",2);
        insertQuetion(question1);
        Question question2 = new Question(" 2. Claude is ________.",
                "A. Frenchman",
                "B. a French",
                "C. a Frenchman",
                "D. French man",2);
        insertQuetion(question2);
        Question question3 = new Question(" 3. I ____ a car next year",
                "A. buy",
                "B. am buying",
                "C. going to buy",
                "D. bought",2);
        insertQuetion(question3);
        Question question4 = new Question(" 4. They are all ________ ready for the party.",
                "A. getting",
                "B. going",
                "C. doing",
                "D. putting",1);
        insertQuetion(question4);
        Question question5 = new Question(" 5. When do you go ________ bed?",
                "A. to",
                "B. to the",
                "C. in",
                "D. in the",1);
        insertQuetion(question5);
        Question question6 = new Question(" 6. London is famous for _____ red buses.",
                "A. it’s",
                "B. its",
                "C. it",
                "D.  it is",2);
        insertQuetion(question6);
        Question question7 = new Question(" 7. Is there _____ milk in the fridge?",
                "A. a lot",
                "B. many",
                "C. much",
                "D. some",4);
        insertQuetion(question7);
        Question question8 = new Question(" 8. There is a flower shop in front _____ my house.",
                "A. of",
                "B. to",
                "C. off",
                "D. in",1);
        insertQuetion(question8);
        Question question9 = new Question(" 9. Where are _____ children? – They go to school.",
                "A. the",
                "B. you",
                "C. a",
                "D. an",1);
        insertQuetion(question9);
        Question question10 = new Question(" 10. Those students are working very _____ for their next exams",
                "A. hardly",
                "B. hard",
                "C. harder",
                "D. hardest",2);
        insertQuetion(question10);
    }

    //lấy dữ liệu câu hỏi và đáp án
    @SuppressLint("Range")
    public ArrayList<Result> getResult(){
        ArrayList<Result> resultArrayList = new ArrayList<>();

        db = getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + Table.ResultTable.Table_name,null);

        if(c.moveToFirst()){
            do {
                Result result = new Result();
                result.setId(c.getInt(c.getColumnIndex(_ID)));
                result.setScore(c.getInt(c.getColumnIndex(Column_score)));
                result.setTime(c.getString(c.getColumnIndex(Column_time)));

                resultArrayList.add(result);
            }while(c.moveToNext());
        }
        c.close();
        return resultArrayList;
    }
    //lấy dữ liệu kết quả
    @SuppressLint("Range")
    public ArrayList<Question> getQuestions(){
        ArrayList<Question> questionArrayList = new ArrayList<>();

        db = getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM "+ Table_name,null);

        if(c.moveToFirst()){
            do{
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(_ID)));
                question.setQuestion(c.getString(c.getColumnIndex(Columm_question)));
                question.setOptions1(c.getString(c.getColumnIndex(Column_options1)));
                question.setOptions2(c.getString(c.getColumnIndex(Column_options2)));
                question.setOptions3(c.getString(c.getColumnIndex(Column_options3)));
                question.setOptions4(c.getString(c.getColumnIndex(Column_options4)));
                question.setAnswer(c.getInt(c.getColumnIndex(Column_answer)));

                questionArrayList.add(question);
            }
            while (c.moveToNext());
        }
        c.close();
        return questionArrayList;
    }
}
