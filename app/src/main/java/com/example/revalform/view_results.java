package com.example.revalform;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class view_results extends Fragment {
    Spinner semsdropdown,depdropdown;
    Button sub;
    String usnval;
    EditText usn;
    String usnMatch="1BM\\d{2}\\w{2}\\d{3}";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview=inflater.inflate(R.layout.view_results_frag,container,false);
        semsdropdown=rootview.findViewById(R.id.sem);
        depdropdown=rootview.findViewById(R.id.dep);
        initspinnerfooter();
        sub =rootview.findViewById(R.id.submit);
        usn =rootview.findViewById(R.id.usn);
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usnval = usn.getText().toString();
                String semyo = semsdropdown.getSelectedItem().toString();
               // Toast.makeText(getActivity(), usnval, Toast.LENGTH_SHORT).show();
                if (!usnval.matches(usnMatch)) {
                    Toast.makeText(getActivity(), "Enter Proper Usn", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(getActivity(), semyo, Toast.LENGTH_SHORT).show();
            }
        });
        return rootview;
    }
    private void initspinnerfooter(){
        String[] semitems =new String[]{
                "1","2","3","4","5","6","7","8"};
        String[] depitems =new String[]{
                "CSE","ISE","EEE","ME","ECE","CVE","TE","CE","hatroo"};
        ArrayAdapter<String> semadapter =new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,semitems);
        semsdropdown.setAdapter(semadapter);
        semsdropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //
            }
        });
        ArrayAdapter<String> depadapter =new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,depitems);
        depdropdown.setAdapter(depadapter);
        depdropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //
            }
        });

        }
    }

