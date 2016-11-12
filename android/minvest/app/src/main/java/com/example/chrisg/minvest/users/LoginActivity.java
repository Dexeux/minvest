package com.example.chrisg.minvest.users;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.chrisg.minvest.R;

/**
 * Created by Christophe Gaboury on 16/09/2016. Copyright (c) 2016
 */
public class LoginActivity extends AppCompatActivity {

    private EditText mUserNameField;
    private EditText mPasswordField;
    private Button mLoginButton;
    private TextView mRegisterButton;
    private TextView mErrorText;
    private ProgressBar mLoadingIcon;


    @Override
    public void onCreate(Bundle savedInstances){
        super.onCreate(savedInstances);

        //Set the UI
        setContentView(R.layout.activity_login);
        mUserNameField = (EditText)findViewById(R.id.username_field);
        mPasswordField  = (EditText)findViewById(R.id.password_field);
        mPasswordField.setTypeface(Typeface.DEFAULT);
        mLoginButton = (Button)findViewById(R.id.login_button);
        mRegisterButton = (TextView)findViewById(R.id.register_button);
        mErrorText  = (TextView)findViewById(R.id.error_text);
        mLoadingIcon = (ProgressBar)findViewById(R.id.loading_icon);

        //Allows the user to register when they are done typing
        mPasswordField.setOnEditorActionListener(new EditText.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView v,int actionId, KeyEvent event ){
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    onLogin(null);
                }
                return false;
            }
        });

    }

    /**
     * Function that triggers when a user has logged in
     */
    public void onLogin(View v){
        if(mUserNameField.getText().toString().equals("")){
            mErrorText.setText(R.string.error_text);
            mErrorText.setVisibility(View.VISIBLE);
            return;
        }
        if(mPasswordField.getText().toString().equals("")){
            mErrorText.setText(R.string.error_text);
            mErrorText.setVisibility(View.VISIBLE);
            return;
        }
        mErrorText.setVisibility(View.INVISIBLE);
        startLoading();
        //Request a login
    }

    /**
     * Display an error message
     * @param message the error message to display
     */
    public void displayErrorMessage(String message){

    }


    /**
     * Function that triggers when a user has tried registering
     * Start a new activity?
     * @param v
     */
    public void onRegister(View v){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);

    }


    public void startLoading(){
        mLoadingIcon.setVisibility(View.VISIBLE);
        mErrorText.setVisibility(View.INVISIBLE);
        mLoginButton.setVisibility(View.GONE);
        mUserNameField.setEnabled(false);
        mPasswordField.setEnabled(false);
        mRegisterButton.setEnabled(false);
    }


}