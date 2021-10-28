package com.example.doongdoong;

import static com.example.doongdoong.R.id.comp_start_record;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class RecordActivity extends AppCompatActivity {

    private static final String TAG = "RecordActivity";

    private int mAudioSource = MediaRecorder.AudioSource.MIC;
    private int mSampleRate = 44100;
    private int mChannelCount = AudioFormat.CHANNEL_IN_STEREO;
    private int mAudioFormat = AudioFormat.ENCODING_PCM_16BIT;
    private int mBufferSize = AudioTrack.getMinBufferSize(mSampleRate, mChannelCount, mAudioFormat);

    public AudioRecord mAudioRecord = null;

    public Thread mRecordThread = null;
    public boolean isRecording = false;

    public AudioTrack mAudioTrack = null;
    public Thread mPlayThread = null;
    public boolean isPlaying = false;

    public ImageButton mBtRecord = null;
    public Button mBtPlay = null;

    public String mFilePath = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_record_start);

        mBtRecord = (ImageButton) findViewById(R.id.comp_start_record);
        //mBtPlay = (Button)findViewById(R.id.bt_play);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mAudioRecord = new AudioRecord(mAudioSource, mSampleRate, mChannelCount, mAudioFormat, mBufferSize);
        mAudioRecord.startRecording();

        mAudioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, mSampleRate, mChannelCount, mAudioFormat, mBufferSize, AudioTrack.MODE_STREAM);

        mRecordThread = new Thread(new Runnable() {
            @Override
            public void run() {
                byte[] readData = new byte[mBufferSize];
                mFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/record.pcm";
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(mFilePath);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                while (isRecording) {
                    int ret = mAudioRecord.read(readData, 0, mBufferSize);
                    Log.d(TAG, "read bytes is " + ret);

                    try {
                        fos.write(readData, 0, mBufferSize);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                mAudioRecord.stop();
                mAudioRecord.release();
                mAudioRecord = null;

                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        mPlayThread = new Thread(new Runnable() {
            @Override
            public void run() {
                byte[] writeData = new byte[mBufferSize];
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(mFilePath);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                DataInputStream dis = new DataInputStream(fis);
                mAudioTrack.play();

                while (isPlaying) {
                    try {
                        int ret = dis.read(writeData, 0, mBufferSize);
                        if (ret <= 0) {
                            (RecordActivity.this).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    isPlaying = false;
                                    mBtPlay.setText("Play");
                                }
                            });

                            break;
                        }
                        mAudioTrack.write(writeData, 0, ret);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                mAudioTrack.stop();
                mAudioTrack.release();
                mAudioTrack = null;

                try {
                    dis.close();
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void onRecord(View view) {
        if (isRecording == true) {
            isRecording = false;
            //mBtRecord.setText("Record");
        } else {
            isRecording = true;
            //mBtRecord.setText("Stop");

            if (mAudioRecord == null) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                mAudioRecord = new AudioRecord(mAudioSource, mSampleRate, mChannelCount, mAudioFormat, mBufferSize);
                mAudioRecord.startRecording();
            }
            mRecordThread.start();
        }

    }

    public void onPlay(View view) {
        if(isPlaying == true) {
            isPlaying = false;
            mBtPlay.setText("Play");
        }
        else {
            isPlaying = true;
            mBtPlay.setText("Stop");

            if(mAudioTrack == null) {
                mAudioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, mSampleRate, mChannelCount, mAudioFormat, mBufferSize, AudioTrack.MODE_STREAM);
            }
            mPlayThread.start();
        }

    }
}