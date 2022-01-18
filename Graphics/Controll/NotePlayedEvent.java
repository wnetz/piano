package Graphics.Controll;

public interface NotePlayedEvent {
    void notePressed(int note);
    void noteReleased(int note);
}
