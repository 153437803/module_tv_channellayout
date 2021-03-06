package com.kalu.channellayout;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import lib.kalu.tv.channel.ChannelLayout;
import lib.kalu.tv.channel.ChannelUtil;
import lib.kalu.tv.channel.listener.OnChannelChangeListener;
import lib.kalu.tv.channel.model.ChannelModel;

public class MainActivity extends AppCompatActivity {

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
//        // up
//        if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_DPAD_UP) {
//            ChannelLayout channelLayout = findViewById(R.id.test_channel);
//            if (channelLayout.getVisibility() != View.VISIBLE) {
//                channelLayout.selectNextUp(1);
//                return true;
//            }
//        }
//        // down
//        else if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_DPAD_DOWN) {
//            ChannelLayout channelLayout = findViewById(R.id.test_channel);
//            if (channelLayout.getVisibility() != View.VISIBLE) {
//                channelLayout.selectNextDown(1);
//                return true;
//            }
//        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ChannelUtil.setDebug(true);

        List<List<ChannelModel>> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {

            int column = i;
            ArrayList<ChannelModel> item = new ArrayList<>();

            for (int j = 0; j < 20; j++) {

                int num = j;
                ChannelModel model = new ChannelModel() {

                    @Override
                    public String initUrl() {
                        return null;
                    }

                    @Override
                    public String initText() {
                        return "column" + column + "=>item" + num;
                    }

                    @Override
                    public CharSequence initTip() {
                        return null;
                    }

                    @Nullable
                    @Override
                    public JSONObject initBundle() {
                        return null;
                    }

                    @Override
                    public int initTextColorPlaying() {
                        return 0;
                    }

                    @Override
                    public int initTextColorHighlight() {
                        return 0;
                    }

                    @Override
                    public int initTextColorDefault() {
                        return 0;
                    }

                    @Override
                    public int initDrawablePlaying() {
                        return 0;
                    }

                    @Override
                    public int initDrawablePlayingAndEqual() {
                        return 0;
                    }

                    @Override
                    public int initDrawablePlayingAndHightlght() {
                        return 0;
                    }

                    @Override
                    public int initDrawableHighlight() {
                        return 0;
                    }

                    @Override
                    public int initDrawableDefault() {
                        return 0;
                    }

                    @Override
                    public int initBackgroundResourcePlaying() {
                        return 0;
                    }

                    @Override
                    public int initBackgroundResourceHighlight() {
                        return 0;
                    }

                    @Override
                    public int initBackgroundResourceDefault() {
                        return 0;
                    }

                };
                item.add(model);
            }

            list.add(item);
        }

        ChannelLayout channelLayout = findViewById(R.id.test_channel);
        channelLayout.update(list);

        channelLayout.setOnChannelChangeListener(new OnChannelChangeListener() {

            @Override
            public void onHighlight(@NonNull int column, @NonNull int position, @NonNull int before, @NonNull int count, @NonNull ChannelModel value) {
                Log.e("TEST", "onHighlight => column = " + column + ", position = " + position + ", value = " + value);

                if (column == 0) {
                    ArrayList<ChannelModel> list = new ArrayList<>();
                    int nextInt = new Random().nextInt(20);
                    for (int j = 0; j < nextInt; j++) {

                        int num = j;
                        ChannelModel model = new ChannelModel() {

                            @Override
                            public String initUrl() {
                                return null;
                            }

                            @Override
                            public String initText() {
                                return "column0=>item" + position + "=>item" + num;
                            }

                            @Override
                            public CharSequence initTip() {
                                return null;
                            }

                            @Nullable
                            @Override
                            public JSONObject initBundle() {
                                return null;
                            }

                            @Override
                            public int initTextColorPlaying() {
                                return 0;
                            }

                            @Override
                            public int initTextColorHighlight() {
                                return 0;
                            }

                            @Override
                            public int initTextColorDefault() {
                                return 0;
                            }

                            @Override
                            public int initDrawablePlaying() {
                                return 0;
                            }

                            @Override
                            public int initDrawablePlayingAndEqual() {
                                return 0;
                            }

                            @Override
                            public int initDrawablePlayingAndHightlght() {
                                return 0;
                            }

                            @Override
                            public int initDrawableHighlight() {
                                return 0;
                            }

                            @Override
                            public int initDrawableDefault() {
                                return 0;
                            }

                            @Override
                            public int initBackgroundResourcePlaying() {
                                return 0;
                            }

                            @Override
                            public int initBackgroundResourceHighlight() {
                                return 0;
                            }

                            @Override
                            public int initBackgroundResourceDefault() {
                                return 0;
                            }
                        };
                        list.add(model);
                    }
                    channelLayout.update(3, 1, list);
                }
            }

            @Override
            public void onMove(@NonNull int column, @NonNull int position, @NonNull int before, @NonNull int count, @NonNull ChannelModel value) {
                Log.e("TEST", "onMove => column = " + column + ", position = " + position + ", value = " + value);
                channelLayout.setVisibility(2, column == 2 ? View.VISIBLE : View.GONE);
                channelLayout.setVisibility(3, column == 2 ? View.GONE : View.VISIBLE);
            }

            @Override
            public void onInit(@NonNull int column, @NonNull int position, @NonNull int before, int count, @NonNull ChannelModel value) {

            }

            @Override
            public void onClick(@NonNull int column, @NonNull int position, @NonNull int before, int count, @NonNull ChannelModel value) {

            }

            @Override
            public void onRepeat(@NonNull int column,@NonNull int position, @NonNull int before, int count, @NonNull ChannelModel value) {

            }
        });

        channelLayout.select(0, 0);
        channelLayout.select(1, 0, true);
        channelLayout.select(2, 0);
        channelLayout.setVisibility(2, View.GONE);
        channelLayout.setVisibility(3, View.VISIBLE);
    }
}