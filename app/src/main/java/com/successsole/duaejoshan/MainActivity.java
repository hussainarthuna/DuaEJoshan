package com.successsole.duaejoshan;

import android.content.res.Resources;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private VideoView videoView;
    private MediaController mediaController;

    private int stopPosition;
    private String dayOfWeek;

    private Resources res;

    private Date date = new Date();
    private SimpleDateFormat simpleDateFormat;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        simpleDateFormat = new SimpleDateFormat("EEEE");
        dayOfWeek = simpleDateFormat.format(date).toLowerCase();


        res = this.getResources();



        int videoID = res.getIdentifier(dayOfWeek,"raw",this.getPackageName());




        videoView = (VideoView) findViewById(R.id.videoView);
        //videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.friday);
//        videoView.setVideoPath("android.resource://" + getPackageName() + "/" +videoID );
//
//
//        mediaController = new MediaController(this);
//
//        mediaController.setAnchorView(videoView);
//
//        videoView.setMediaController(mediaController);
//
//        //run it
//        videoView.start();

        play(videoID);
        
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

                videoView.stopPlayback();
                //mp.release();
                play(R.raw.duaekamil);


            }
        });



    }

    @Override
    public void onPause() {
       // Log.d(TAG, "onPause called");
        super.onPause();
        stopPosition = videoView.getCurrentPosition(); //stopPosition is an int
        videoView.pause();
    }
    @Override
    public void onResume() {
        super.onResume();
       // Log.d(TAG, "onResume called");
        videoView.seekTo(stopPosition);
        videoView.start(); //Or use resume() if it doesn't work. I'm not sure
    }

    private void play(int raw){
        videoView.setVideoPath("android.resource://" + getPackageName() + "/" +raw );


        mediaController = new MediaController(MainActivity.this);

        mediaController.setAnchorView(videoView);

        videoView.setMediaController(mediaController);

        //run it
        videoView.start();

    }
}
