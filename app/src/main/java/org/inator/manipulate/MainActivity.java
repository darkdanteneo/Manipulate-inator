package org.inator.manipulate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Switch;
import android.os.StrictMode.ThreadPolicy;
import android.os.StrictMode.ThreadPolicy.Builder;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class MainActivity extends AppCompatActivity {

    ThreadPolicy policy = new Builder().permitAll().build();
    final OkHttpClient httpClient = new OkHttpClient();


    EditText roll;
    TextView textView;
    String branchS = null;
    String semesterS = null;
    String recordID;
    int sub_id;
    int intitial_subID;
    ArrayAdapter<String> subjectAdapter;
    ArrayAdapter<String> semesterAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Change thread policy, requires to access internet from main activity without async task
        StrictMode.setThreadPolicy(this.policy);

        //Initialize UI elements
        Button absentButton;
        Button markButton;
        CalendarView calenderView;

        //Assign UI elements
        final Spinner semester = (Spinner)findViewById(R.id.semester);
        final Spinner subject = (Spinner)findViewById(R.id.subject);
        Spinner branch = (Spinner) findViewById(R.id.branch);
        calenderView = (CalendarView)findViewById(R.id.calendarView);
        textView = (TextView) findViewById(R.id.textView);
        roll = (EditText)findViewById(R.id.rollNo);
        markButton = (Button) findViewById(R.id.mark);
        absentButton = (Button) findViewById(R.id.absent);

        //On Date Change
        calenderView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                StringBuilder sb = new StringBuilder();
                sb.append(year);
                String str = "-";
                sb.append(str);
                sb.append(month + 1);
                sb.append(str);
                sb.append(dayOfMonth);
                sb.append("  11:11:11");
                textView.setText(sb.toString());
            }
        });

        //Initialize adapter
        ArrayAdapter<String> branchAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.branch));

        //Set Adapter
        branchAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);

        //Implement Adapter
        branch.setAdapter(branchAdapter);

        //On Spinner Select
        branch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               branchS = parent.getItemAtPosition(position).toString();
               setSemesterAdapter();
               semester.setAdapter(semesterAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                branchS = "CSE";
            }
        });
        semester.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                semesterS = parent.getItemAtPosition(position).toString();
                setSubjectAdapter();
                subject.setAdapter(subjectAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                semesterS = "2";
            }
        });
        subject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setSubID(parent.getItemAtPosition(position).toString(), (int) id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        //On Button Click
        markButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recieveRecordId();
                markAttendence("p");
            }
        });
        absentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recieveRecordId();
                markAttendence("a");
            }
        });
    }

    public String getRecorID() {
        return recordID;
    }
    public void setRecorID(String a) {
        Log.d("b", a);
        recordID = a;
    }
    public void setSubID(String subName, int i) {
        switch (i)
        {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
                sub_id = intitial_subID + i;
                break;
        }
        switch (subName){
            case "Sad p-lab":
                sub_id = 145;
                break;
            case "Computer-Networks":
                sub_id = 166;
                break;
            case "CN-LAB-4":
                sub_id = 173;
                break;
            case "Cn- Lab":
                sub_id = 154;
                break;
            case "Cg- Lab":
                sub_id = 155;
                break;
            case "Parallel-Computing":
                sub_id = 178;
                break;
            case "Cn-theory":
                sub_id = 193;
                break;
            case "cn-la":
                sub_id = 194;
                break;
            case "c lab":
                sub_id = 183;
                break;
            case "Ai-lab":
                sub_id = 195;
                break;
            case "Elec and comm":
                sub_id = 192;
                break;
        }
    }
    public void setSubjectAdapter(){
        switch (branchS) {
            case "CSE":
                switch (semesterS) {
                    case "2":
                        subjectAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.subjectCSE2));
                        intitial_subID = 103;
                        break;
                    case "4":
                        subjectAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.subjectCSE4));
                        intitial_subID = 137;
                        break;
                    case "6":
                        subjectAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.subjectCSE6));
                        intitial_subID = 146;
                        break;
                    case "8":
                        subjectAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.subjectCSE8));
                        intitial_subID = 156;
                        break;
                }
                break;
            case "IT":
                switch (semesterS) {
                    case "2":
                        subjectAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.subjectIT2));
                        intitial_subID = 110;
                        break;
                    case "4":
                        subjectAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.subjectIT4));
                        intitial_subID = 164;
                        break;
                    case "6":
                        subjectAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.subjectIT6));
                        intitial_subID = 174;
                        break;
                    case "8":
                        subjectAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.subjectIT8));
                        intitial_subID = 184;
                        break;
                }
                break;
            case "ECE":
            {
                switch (semesterS){
                    case "2":
                        subjectAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.subjectECE2));
                        intitial_subID = 117;
                        break;
                }
            }
            break;
            case "CE":
            {
                switch (semesterS){
                    case "2":
                        subjectAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.subjectCE2));
                        intitial_subID = 130;
                        break;
                }
            }
            break;
            case "EE":
            {
                switch (semesterS){
                    case "2":
                        subjectAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.subjectEE2));
                        intitial_subID = 123;
                        break;
                }
            }
            break;
        }
    }
    public void setSemesterAdapter(){
        switch (branchS){
            case "CSE":
            case "IT":
                semesterAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.semester8));
                break;
            case "ECE":
            case "EE":
            case "CE":
                semesterAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.semester2));
                break;
        }

    }
    public void recieveRecordId() {
        try {
            StringBuilder classCode = new StringBuilder();
            classCode.append(branchS);
            classCode.append(semesterS);
            String output = httpClient.newCall(new Request.Builder().url("https://uiit.ac.in/@!=1234578knowitzq=-451za479sss7757884446674bfgsj003n34$23/api/todayAttendenceAndStudent.php").post(new FormBody.Builder().add("key", "123456__123_321").add("classcode", classCode.toString()).add("sub_id", Integer.toString(sub_id)).add("dated", textView.getText().toString()).build()).build()).execute().body().string();
            StringBuilder sb = new StringBuilder();
            sb.append("rollno\":\"");
            sb.append(roll.getText().toString());
            String output2 = output.substring(output.indexOf(sb.toString()), output.length());
            String output3 = output2.substring(output2.indexOf("rec_id") + 9, output2.length());
            setRecorID(output3.substring(0, output3.indexOf("}")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void markAttendence(String status) {
        RequestBody formBody = new FormBody.Builder().add("key", "123456__123_321").add("rec_id[]", getRecorID()).add("status[]", status).build();
        try {
            if(getRecorID()!= null) {
                httpClient.newCall(new Request.Builder().url("https://uiit.ac.in/@!=1234578knowitzq=-451za479sss7757884446674bfgsj003n34$23/api/updateattendance.php").post(formBody).build()).execute().body().string();
            }
            else
            {

                Toast.makeText(getApplicationContext(), "Record does not exist", Toast.LENGTH_LONG).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
