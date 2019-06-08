package com.test.freelancer2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Welcome extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ImageView bgb,bgc,background,bga,gender;
    TextView manswitch,womenswitch,next;
    FirebaseAuth firebaseAuth;
    Intent intent;
    ImageView avatar;
    int a=R.drawable.bgb,b=R.drawable.e;
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder().setDefaultFontPath("fonts/algerian.ttf").setFontAttrId(R.attr.fontPath).build());

        setContentView(R.layout.activity_welcome);
        gender=findViewById(R.id.gender);
        manswitch=findViewById(R.id.manswitch);
        next=findViewById(R.id.next);
        womenswitch=findViewById(R.id.womenswitch);
        background=findViewById(R.id.background);
        avatar=findViewById(R.id.avatar);
        bgb=findViewById(R.id.bgb);
        bgc=findViewById(R.id.bgc);

        firebaseAuth=FirebaseAuth.getInstance();
        bga=findViewById(R.id.bga);
        bgb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bgb.setBackgroundResource(R.drawable.boarder2);
                background.setImageResource(R.drawable.bgb);
                bgc.setBackgroundResource(R.drawable.boarder);
                bga.setBackgroundResource(R.drawable.boarder);
                a=R.drawable.bgb;
                next.setEnabled(true);


            }
        });
        bgc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bgc.setBackgroundResource(R.drawable.boarder2);
                background.setImageResource(R.drawable.bgc);
                bgb.setBackgroundResource(R.drawable.boarder);
                bga.setBackgroundResource(R.drawable.boarder);
                a=R.drawable.bgc;
                next.setEnabled(true);


            }
        });
        bga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bga.setBackgroundResource(R.drawable.boarder2);
                background.setImageResource(R.drawable.bga);
                bgc.setBackgroundResource(R.drawable.boarder);
                bgb.setBackgroundResource(R.drawable.boarder);
                a=R.drawable.bga;
                next.setEnabled(true);

            }
        });
        manswitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender.setImageResource(R.drawable.frame);
                avatar.setImageResource(R.drawable.e);
                b=R.drawable.e;
                next.setEnabled(true);


            }
        });
        womenswitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender.setImageResource(R.drawable.frameb);
                avatar.setImageResource(R.drawable.j);
                b=R.drawable.j;
                next.setEnabled(true);

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             intent=new Intent(Welcome.this,NextActivity.class);
             intent.putExtra("bg1",a);
                intent.putExtra("bg2",b);
                startActivity(intent);



            }
        });




























        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.welcome, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            firebaseAuth.signOut();
            Intent intent=new Intent(Welcome.this,LoginActivity.class);
            startActivity(intent);
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
