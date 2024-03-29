
package com.test.freelancer2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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

public class LoginActivity extends AppCompatActivity {
    private  EditText loginEmailText;
    private  EditText loginPassText;
    Button loginBtn;
    TextView loginPageBtn;
    private FirebaseAuth mAuth;
    private   ProgressBar loginprogress;
    TextView signin;
    int GOOGLE_SIGN=123;
    GoogleSignInClient mgoogleSignInClient;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth=FirebaseAuth.getInstance();
        loginEmailText=(EditText)findViewById(R.id.login_email);
        loginPassText=(EditText)findViewById(R.id.login_pass);
        loginBtn=(Button)findViewById(R.id.login_btn);
        loginPageBtn=findViewById(R.id.login_reg_btn);
        loginprogress=findViewById(R.id.login_progerss);
        signin=findViewById(R.id.loginsignin);
        loginprogress.setVisibility(View.INVISIBLE);
        getSupportActionBar().hide();

        mAuth=FirebaseAuth.getInstance();
        overridePendingTransition(android.R.anim.fade_out,android.R.anim.fade_out);



        loginPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regIntent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(regIntent);

            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { loginprogress.setVisibility(View.VISIBLE);
                Intent regIntent=new Intent(LoginActivity.this,PhoneAuth.class);
                startActivity(regIntent);
                finish();


            }
        });



        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String loginEmail=loginEmailText.getText().toString();
                String loginPassword=loginPassText.getText().toString();
                if(!TextUtils.isEmpty(loginEmail)&&!TextUtils.isEmpty(loginPassword))
                {
                    loginprogress.setVisibility(View.VISIBLE);
                    mAuth.signInWithEmailAndPassword(loginEmail,loginPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                sendtoMain();
                            } else {
                                String errormessage = task.getException().getMessage();
                                Toast.makeText(LoginActivity.this, "ERROR:" + errormessage, Toast.LENGTH_SHORT).show();
                            }
                            loginprogress.setVisibility(View.INVISIBLE);
                        }
                    });
                }


            }
        });

    }





    private void sendtoMain() {

            Intent mainIntent = new Intent(LoginActivity.this, Welcome.class);
            startActivity(mainIntent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() != null) {
            Intent mainIntent = new Intent(LoginActivity.this, Welcome.class);
            startActivity(mainIntent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();

        }
    }
}

