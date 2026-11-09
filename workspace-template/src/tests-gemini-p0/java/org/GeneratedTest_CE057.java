package org.example;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class GeneratedTest_CE057 {

@Test
public void testPlayAndRemoveSong() {
    MusicPlayer player = new MusicPlayer();
    assertNull(player.play());

    player.addSong("Song A");
    player.addSong("Song B");
    assertEquals("Song A", player.play());

    player.setCurrentSong("Song A");
    player.removeSong("Song A");
    assertNull(player.getCurrentSong());
    assertEquals(1, player.getPlaylist().size());
}

@Test
public void testSwitchAndPreviousSong() {
    MusicPlayer player = new MusicPlayer();
    player.addSong("Track 1");
    player.addSong("Track 2");
    player.addSong("Track 3");
    player.setCurrentSong("Track 1");

    assertTrue(player.switchSong());
    assertEquals("Track 2", player.getCurrentSong());

    assertTrue(player.previousSong());
    assertEquals("Track 1", player.getCurrentSong());

    // Previous on first track fails
    assertFalse(player.previousSong());

    // Switch to end
    player.switchSong(); // Track 2
    player.switchSong(); // Track 3
    assertFalse(player.switchSong()); // Cannot switch past end
}

@Test
public void testSetVolume() {
    MusicPlayer player = new MusicPlayer();
    assertEquals(50, player.getVolume());

    assertTrue(player.setVolume(80));
    assertEquals(80, player.getVolume());

    assertFalse(player.setVolume(-1));
    assertFalse(player.setVolume(101));
    assertEquals(80, player.getVolume());
}

@Test
public void testStopAndShuffle() {
    MusicPlayer player = new MusicPlayer();
    assertFalse(player.stop());
    assertFalse(player.shuffle()); // empty playlist

    player.setPlaylist(Arrays.asList("S1", "S2", "S3"));
    player.setCurrentSong("S1");
    assertTrue(player.stop());
    assertNull(player.getCurrentSong());

    assertTrue(player.shuffle());
    assertEquals(3, player.getPlaylist().size());
}

}
