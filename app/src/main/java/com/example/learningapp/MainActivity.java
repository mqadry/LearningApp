package com.example.learningapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    CallbackManager callbackManager;

    LoginButton loginButton;
    TextView userName, userID, userEmail;
    ProfilePictureView profilePictureView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = findViewById(R.id.login_button);
        userName = findViewById(R.id.userName);
        userEmail = findViewById(R.id.userEmail);
        userID = findViewById(R.id.userID);
        profilePictureView = findViewById(R.id.friendProfilePicture);

        loginButton.setReadPermissions(Arrays.asList("email"));
        callbackManager = CallbackManager.Factory.create();

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();
                if (currentAccessToken != null)
                {
                    GraphRequest request = GraphRequest.newMeRequest(
                            currentAccessToken, new GraphRequest.GraphJSONObjectCallback() {
                                @Override
                                public void onCompleted(JSONObject object, GraphResponse response) {
                                    try {
                                        String first_name = object.getString("first_name");
                                        String last_name = object.getString("last_name");
                                        String email = object.getString("email");
                                        String id = object.getString("id");

                                        userName.setText("Name: " + first_name + " " + last_name);
                                        userEmail.setText("Email: " + email);
                                        userID.setText("ID: " + id);
                                        profilePictureView.setProfileId(id);

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            });

                    Bundle parameters = new Bundle();
                    parameters.putString("fields", "first_name,last_name,email,id");
                    request.setParameters(parameters);
                    request.executeAsync();
                }

            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}