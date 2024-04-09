import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import java.io.File;
public class songArtistDisplay {
    private String songTitle,songArtist,filePath;
    public String getSongTitle() {
        return songTitle;
    }
    public String getSongArtist() {
        return songArtist;
    }
    songArtistDisplay(int indx) {
        filePath = "C:\\Users\\abdul\\IdeaProjects\\Music player\\src\\assets\\wavSongs\\song";
        try {
            AudioFile audioFile = AudioFileIO.read(new File(filePath + Integer.toString(indx+1) + ".wav"));

            Tag tag = audioFile.getTag();
            songTitle = tag.getFirst(FieldKey.TITLE);
            songArtist = tag.getFirst(FieldKey.ARTIST);

            System.out.println("jaudiotagger library retrieved : \n"+
                    "song title : " +songTitle + " song artist : " + songArtist);
        }catch (Exception e){
            System.out.println("exception while making audiofile object to get songartist and songtitle");
            e.printStackTrace();
        }
    }
}
