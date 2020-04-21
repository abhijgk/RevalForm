package com.example.revalform;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {
    FirebaseAuth mFirebaseAuth;
    TextView verifymsg;
    Button btnlogout,resendCode;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        resendCode =findViewById(R.id.verifybut);
        mFirebaseAuth =FirebaseAuth.getInstance();
        verifymsg =findViewById(R.id.Email_verify);
        final FirebaseUser user= mFirebaseAuth.getCurrentUser();
//        if(!user.isEmailVerified()){
////            resendCode.setVisibility(View.VISIBLE);
////            verifymsg.setVisibility(View.VISIBLE);
////
////            resendCode.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(final View v) {
////
////                    user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
////                        @Override
////                        public void onSuccess(Void aVoid) {
////                            Toast.makeText(v.getContext(), "Varification Email Has Been Sent", Toast.LENGTH_SHORT).show();
////                        }
////                    }).addOnFailureListener(new OnFailureListener() {
////                        @Override
////                        public void onFailure(@NonNull Exception e) {
////                            Log.d("tag","onFailure: Email Not Sent"+e.getMessage());
////                        }
////                    });
////                }
////            });
////            Intent inToMain=new Intent(HomeActivity.this, HomeverifyActivity.class);
////            startActivity(inToMain);
//        }
        btnlogout = findViewById(R.id.logout);
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFirebaseAuth.getInstance().signOut();
                Intent inToMain=new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(inToMain);
            }
        });

        bottomNavigationView =findViewById(R.id.bottom_nav);
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_conatainer,new view_results()).commit();
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment =null;

                switch (item.getItemId()){
                    case R.id.nav_view:
                        fragment = new view_results();
                        break;
                    case R.id.nav_forum:
                        fragment = new apply_forms();
                        break;
                    case R.id.nav_list:
                        fragment = new applied_forms();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_conatainer,fragment).commit();

                return true;
            }
        });
    }
    @Override
    public void onBackPressed(){
        Toast.makeText(HomeActivity.this,"Can't go Back",Toast.LENGTH_SHORT).show();
        return;
    }
}
