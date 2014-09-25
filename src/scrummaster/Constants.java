package scrummaster;

/**
 *
 * @author Nick
 */
public class Constants {

    public static final double BASE_PROBABILITY = 100.0;
    public static final double PREVIOUS_MASTER_BASE_PROBABILITY = 20.0;
    public static final long TOTAL_DURATION_MS = 15000;
    public static final long START_INTERVAL_MS = 200;
    public static final long MAX_STEP_MS = 50;
    public static final boolean DEBUG = false;
    public static final String FINAL_CANDIDATE_EVENT = "finalCandidate";
    public static final long DRAMATIC_PAUSE_MS = 3500;
    public static final float TONE_SAMPLE_RATE = 8000f;
    public static final int TONE_HZ = 666;
    public static final int TONE_MS = 100;
    public static final double TONE_VOL = 0.3;
    public static final int WAV_SIZE_BYTES = 8192000;
    public static final int WAV_BUF_SIZE_BYTES = 128000;
    public static final String PONY_WAV_PATH = "/aud/pony.wav";
    public static final String DRUMS_WAV_PATH = "/aud/drums.wav";
    public static final String HISTORY_FILENAME = "history.properties";
}
