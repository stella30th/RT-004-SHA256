package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE057 {
    @Test public void behavioralCase1() throws Exception {
        MusicPlayer m=new MusicPlayer(); m.addSong("Song1"); assertEquals(Arrays.asList("Song1"),m.getPlaylist()); m.removeSong("Song1"); assertTrue(m.getPlaylist().isEmpty());
    }
    @Test public void behavioralCase2() throws Exception {
        MusicPlayer m=new MusicPlayer(); assertTrue(m.setVolume(50)); assertEquals(50,m.getVolume());
    }
}
