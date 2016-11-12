package com.example.chrisg.minvest.users;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.chrisg.minvest.R;

/**
 * Created by Christophe Gaboury on 18/09/2016. Copyright (c) 2016
 */
public class RegisterActivity extends AppCompatActivity {


    private EditText mUsernameField;
    private EditText mEmailField;
    private EditText mPasswordField;
    private EditText mConfirmPasswordField;
    private TextView mErrorText;
    private Button mRegisterButton;
    private ProgressBar mProgressBar;



    @Override
    public void onCreate(Bundle SavedInstances){
        super.onCreate(SavedInstances);
        setContentView(R.layout.activity_register);

        mUsernameField = (EditText)findViewById(R.id.username_field);
        mEmailField = (EditText)findViewById(R.id.email_field);
        mPasswordField = (EditText)findViewById(R.id.password_field);
        mPasswordField.setTypeface(Typeface.DEFAULT);
        mConfirmPasswordField = (EditText)findViewById(R.id.password_confirm_field);
        mConfirmPasswordField.setTypeface(Typeface.DEFAULT);
        mErrorText = (TextView)findViewById(R.id.error_text);
        mRegisterButton = (Button)findViewById(R.id.register_button);
        mProgressBar = (ProgressBar)findViewById(R.id.loading_icon);


    }


    /**
     * Display an error message
     * @param message : Message to display
     */
    public void displayErrorMessage(String message){
        mErrorText.setText(message);
        mErrorText.setVisibility(View.VISIBLE);
    }

    public void onRegister(View v){
        if(mUsernameField.getText().toString().equals("")){
            mErrorText.setText(R.string.invalid_username);
            mErrorText.setVisibility(View.VISIBLE);
            return;
        }
        if(mEmailField.getText().toString().equals("")){
            mErrorText.setText(R.string.invalid_email);
            mErrorText.setVisibility(View.VISIBLE);
            return;
        }
        if(mPasswordField.getText().toString().equals("")){
            mErrorText.setText(R.string.invalid_password);
            mErrorText.setVisibility(View.VISIBLE);
            return;
        }
        if(!mConfirmPasswordField.getText().toString().equals(mPasswordField.getText().toString())){
            mErrorText.setText(R.string.invalid_password_confirm);
            mErrorText.setVisibility(View.VISIBLE);
            return;
        }
        startLoading();
        /**
        mPresenter.registerUser(mUsernameField.getText().toString(),
                mEmailField.getText().toString(),
                mPasswordField.getText().toString());
        **/
    }

    public void startLoading(){

        mErrorText.setVisibility(View.INVISIBLE);
        mProgressBar.setVisibility(View.VISIBLE);
        mRegisterButton.setVisibility(View.GONE);
        mUsernameField.setEnabled(false);
        mEmailField.setEnabled(false);
        mPasswordField.setEnabled(false);
        mConfirmPasswordField.setEnabled(false);

    }

}
