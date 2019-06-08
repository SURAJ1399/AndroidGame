package com.test.freelancer2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.test.freelancer2.R;

public class RegisterActivity extends AppCompatActivity {
    private EditText reg_email_field;
    private EditText reg_Pass_field;
    private EditText mobileno;
    EditText name;
    private Button reg_Btn;
    private TextView reg_login_btn;
    private FirebaseAuth mAuth;
    private ProgressBar reg_progress;
    TextView signup;
    String TAG = ".RegisterActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        reg_email_field = findViewById(R.id.reg_email);
        reg_Pass_field = findViewById(R.id.reg_pass);
        getSupportActionBar().hide();
      mobileno=findViewById(R.id.mobile);
      name=findViewById(R.id.fullname);
        reg_Btn = findViewById(R.id.reg_btn);
        reg_login_btn = findViewById(R.id.reg_login_btn);


        reg_progress = findViewById(R.id.reg_progerss);
        reg_progress.setVisibility(View.INVISIBLE);

        // Google Signin


        //Registeration method


        reg_login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginintent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(loginintent);
                finish();
            }
        });



        reg_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = reg_email_field.getText().toString();
                String pass = reg_Pass_field.getText().toString();
                String mobile = mobileno.getText().toString();
                String fullname = name.getText().toString();
                if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass) && !TextUtils.isEmpty(fullname)) {
                    if (!TextUtils.isEmpty(mobile)) {
                        reg_progress.setVisibility(View.VISIBLE);
                        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Intent setupintent = new Intent(RegisterActivity.this, Welcome.class);
                                    startActivity(setupintent);
                                    finish();
                                } else {
                                    String errormessage = task.getException().getMessage();
                                    Toast.makeText(RegisterActivity.this, "Error:" + errormessage, Toast.LENGTH_LONG).show();
                                }
                                reg_progress.setVisibility(View.INVISIBLE);
                            }
                        });

                    } else {
                        Toast.makeText(RegisterActivity.this, "Please Enter Complete Details", Toast.LENGTH_LONG).show();
                    }

                }
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            sendtoMain();
        }
    }

    private void sendtoMain() {
        Intent mainIntent = new Intent(RegisterActivity.this, Welcome.class);
        startActivity(mainIntent);
        finish();
    }
}
