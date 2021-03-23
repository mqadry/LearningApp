package com.example.learningapp;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
    }
}
//        GoogleSignInOptions gso =  new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestEmail()
//                .build();
//
//        googleApiClient = new GoogleApiClient.Builder(this)
//                .enableAutoManage(this,this)
//                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
//                .build();
//
//        signInButton = (SignInButton) findViewById(R.id.sign_in_button);
//
//        signInButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
//                startActivityForResult(intent,RC_SIGN_IN);
//            }
//        });
//    }

//    @Override
//    public void onConnectionFailed(@NonNull ConnectionResult connectionResult)
//    {    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == RC_SIGN_IN)
//        {
//            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
//            handleSignInResult(result);
//        }
//    }
//
//    private void handleSignInResult(GoogleSignInResult result)
//    {
//        if (result.isSuccess())
//        {
//            gotoProfile();
//        }
//        else {
//            Toast.makeText(getApplicationContext(),"Sign in cancel",Toast.LENGTH_LONG).show();
//        }
//    }
//
//    private void gotoProfile()
//    {
//        Intent intent = new Intent(MainActivity.this,ProfileActivity.class);
//        startActivity(intent);
//    }
//}