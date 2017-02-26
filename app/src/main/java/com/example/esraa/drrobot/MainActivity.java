package com.example.esraa.drrobot;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ViewGroup mLinearLayout;
    private EditText mEdit;
    private ImageButton refreshButton;
    private ImageButton sendButton;
    private Toolbar toolbar;
    private ScrollView scrollView;
    String mess;
    static boolean welcome;
     String uuid;

    @Override
    protected void onRestart() {
        super.onRestart();
        welcome = false;
    }

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        mLinearLayout = (ViewGroup) findViewById(R.id.linearLayout2);
        mEdit   = (EditText)findViewById(R.id.textInput);
        refreshButton = (ImageButton) toolbar.findViewById(R.id.refreshButton);
        sendButton  = (ImageButton) findViewById(R.id.sendButton);
        scrollView = (ScrollView) findViewById(R.id.scrollView);

        refreshButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });

        if(!welcome) {
            RestAPI apiService =
                    BaseAPI.getClient().create(RestAPI.class);
            Call<Message> call = apiService.getQuestion();
            call.enqueue(new Callback<Message>() {
                @Override
                public void onResponse(Call<Message> call, Response<Message> response) {
                    Message message = response.body();

                    uuid = message.getUuid();
                    Log.d("uuid fo2", uuid);
                    try {


                        if (message != null) {
                            addRobotText(message.getMessage());
                        }
                        welcome = true;

                    } catch (NullPointerException e) {

                    }
                }

                @Override
                public void onFailure(Call<Message> call, Throwable t) {
                    Log.e("error", "error happened");
                }
            });
        }

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mess = mEdit.getText().toString();
                if (mess.length()==0)
                    return;
                addUserText(mess);
                mEdit.setText("");
                 RestAPI apiService =
                        BaseAPI.getClient().create(RestAPI.class);
                Log.d("uuid", uuid);
                Message message=new Message(mess,uuid);
                Call<Message> call = apiService.chatting(uuid,message);
                Log.e("eee",call.toString());
                call.enqueue(new Callback<Message>() {
                    @Override
                    public void onResponse(Call<Message> call, Response<Message> response) {
                        Message Response = response.body();
                        String responseMessage=Response.getMessage();
                        try {
                            if (responseMessage != null) {
                                addRobotText(responseMessage);
                            }

                        } catch (NullPointerException e) {

                        }

                    }

                    @Override
                    public void onFailure(Call<Message> call, Throwable t) {
                        Log.e("error","error happened");
                    }
                });
            }

        });

    }
    protected void addRobotText(String message)
    {
        View mLayout = LayoutInflater.from(this).inflate(R.layout.robot_message_layout, mLinearLayout, false);
        TextView textView = (TextView) mLayout.findViewById(R.id.textViewRob);
        textView.setText(message);
        mLinearLayout.addView(mLayout);
        scrollView.post(new Runnable() {

            @Override
            public void run() {
                scrollView.fullScroll(View.FOCUS_DOWN);
            }
        });
    }
    protected void addUserText(String message)
    {
        View mLayout = LayoutInflater.from(this).inflate(R.layout.user_message_layout, mLinearLayout, false);
        TextView textView = (TextView) mLayout.findViewById(R.id.textViewUser);
        textView.setText(message);
        mLinearLayout.addView(mLayout);
        scrollView.post(new Runnable() {

            @Override
            public void run() {
                scrollView.fullScroll(View.FOCUS_DOWN);
            }
        });
    }


}
