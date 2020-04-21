package com.example.revalform;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.text.SimpleDateFormat;

public class ScrollingActivity extends AppCompatActivity implements TextWatcher{
    private static final String TAG = "ScrollingActivity";
    public static List<EditText> allEdscc = new ArrayList<EditText>();

    public static String[] strings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        EditText e = (EditText) findViewById(R.id.editText3);
        e.addTextChangedListener(this);

        long date = System.currentTimeMillis();
        TextView tvDisplayDate=(TextView)findViewById(R.id.textView14);
        SimpleDateFormat sdf = new SimpleDateFormat("dd MM, yyyy h:mm a");
        String dateString = sdf.format(date);
        tvDisplayDate.setText(dateString);


        //database
        editTextUSN=(EditText)findViewById(R.id.editText);
        editTextName=(EditText)findViewById(R.id.editText2);
        editTextSem=(EditText)findViewById(R.id.editText_sem);
    }

        @SuppressLint("SetTextI18n")
        public void addRows(){
            //RelativeLayout rl=findViewById(R.id.rl);
                TableLayout ll = findViewById(R.id.courses);
                EditText t1= findViewById(R.id.editText3);
                int n=Integer.parseInt(t1.getText().toString());

                int i;
                for (i = 1; i <=n; i++) {

                    TableRow row= new TableRow(this);
                    TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                    row.setLayoutParams(lp);

                    TextView t=new TextView(this);
                    t.setText(Integer.toString(i));


                    //EditText sem = new EditText(this);
                    EditText cc = new EditText(this);
                    EditText ct = new EditText(this);
                    EditText grade = new EditText(this);




                    cc.setId(200+i);
                    ct.setId(300+i);
                    grade.setId(400+i);

                    allEdscc.add(cc);

                    row.addView(t);
                    //row.addView(sem);
                    row.addView(cc);
                    row.addView(ct);
                    row.addView(grade);

                    ll.addView(row,i);

                }
            TextView total=(TextView)findViewById(R.id.textView12);
            total.setText(Integer.toString(1000 * n));



    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    //String s=e.getText().toString();

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                TableLayout ll = findViewById(R.id.courses);
                if (ll.getRootView() != null) {
                    int i = 1;
                    while (ll.getChildCount() != 1) {
                        ll.removeViewAt(i);
                    }
                }
                if(!TextUtils.isEmpty(s)){
                    addRows();
            }



    }

    @Override
    public void afterTextChanged(Editable s) {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private static final String KEY_USN = "usn";
    private static final String KEY_NAME = "name";


    private EditText editTextUSN;
    private EditText editTextName;
    private EditText editTextSem;
    //private EditText editTextNum;
    //String sem=editTextSem.getText().toString();
    //String sem="3";

    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    public void submitReval(View v) {
        String usn = editTextUSN.getText().toString();
        String name = editTextName.getText().toString();
        String sem=editTextSem.getText().toString();

        int size = allEdscc.size();

        /*for(int j = 0; j < size; j++){
            strings[j] = allEdscc.get(j).getText().toString();
        }*/


        CollectionReference notebookRef = db.collection("CseReval").document("sem").collection(sem);

        RevalData note= new RevalData(usn,name,strings);

        notebookRef.add(note);

    }



}
