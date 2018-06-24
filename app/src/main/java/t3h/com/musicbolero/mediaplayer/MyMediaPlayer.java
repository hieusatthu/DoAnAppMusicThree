package t3h.com.musicbolero.mediaplayer;

import android.media.MediaPlayer;

import java.io.IOException;

/**
 * Created by songsong on 6/24/2018.
 */

public class MyMediaPlayer {
    private MediaPlayer mediaPlayer;

    public MyMediaPlayer() {


    }
    public void initMedia(String url) throws IOException {
        mediaPlayer=new MediaPlayer();
        mediaPlayer.setDataSource(url);
    }
    public void playMusic(String url)
    {
        try {
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.start();

    }

}
