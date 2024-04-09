import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.sound.sampled.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MusicPlayerGUI extends JFrame {
    //Constructor
    MusicPlayerGUI() {
        isplaying = false;
        isplayed = false;
        selectedIndex = -1;
        new Thread(()->initComponents()).start();
        new Thread(()->displaySongArtistnames(0)).start();
        new Thread(()->displaySongImage(0)).start();
    }

    //initializing components of JFrame
    private void initComponents() {

        MainPanel = new javax.swing.JPanel();
        LeftPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        RightPanel = new javax.swing.JPanel();
        ScrollPane = new javax.swing.JScrollPane();
        SongList = new javax.swing.JList<>();
        ListLabel = new javax.swing.JLabel();
        BottomPanel = new javax.swing.JPanel();
        ProgressBar = new javax.swing.JProgressBar();
        PrevBtn = new javax.swing.JButton();
        PlayBtn = new javax.swing.JButton();
        NextBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setVisible(true);
        setTitle("MUSIC PLAYER APPLICATION");
        setLocation(300,70);

        MainPanel.setBackground(new java.awt.Color(102, 102, 102));
        MainPanel.setPreferredSize(new java.awt.Dimension(900, 600));

        LeftPanel.setBackground(new java.awt.Color(51, 51, 51));

        jLabel1.setBackground(new java.awt.Color(51, 51, 51));
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Icon imageIcon = new ImageIcon(iconPath+"images.png");
        jLabel1.setIcon(imageIcon); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 68, 29));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setFont(new Font("SanSerif", Font.BOLD, 24));
        jLabel2.setText("SONG ARTIST");


        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 71, 39));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setFont(new Font("SanSerif", Font.BOLD, 24));
        jLabel3.setText("SONG NAME");



        javax.swing.GroupLayout LeftPanelLayout = new javax.swing.GroupLayout(LeftPanel);
        LeftPanel.setLayout(LeftPanelLayout);
        LeftPanelLayout.setHorizontalGroup(
                LeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LeftPanelLayout.createSequentialGroup()
                                .addContainerGap(80, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(74, 74, 74))
                        .addGroup(LeftPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(LeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        LeftPanelLayout.setVerticalGroup(
                LeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(LeftPanelLayout.createSequentialGroup()
                                .addContainerGap(19, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(111, 111, 111))
        );

        RightPanel.setBackground(new java.awt.Color(102, 102, 102));

        SongList.setBackground(new java.awt.Color(102, 102, 102));
        SongList.setFont(new java.awt.Font("Consolas", 3, 24)); // NOI18N
        SongList.setForeground(new java.awt.Color(239, 239, 239));
        SongList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] songList = {
                    "Perfect        - Ed Sheeran",
                    "Husn           - Anuv Jain",
                    "Baby           - Justin Beiber",
                    "Take me to the River - Thelma Costolo",
                    "Metamorphosis  - Interworld" ,
                    "Tum Hi Ho      - Arjit Singh",
                    "Dandelions     - Ruth B.",
                    "Inthandam      - Chandrashekar",
                    "Attention      - Charlie Puth",
                    "Every Day Normal Guy - Jon",
                    "Lover          - Taylor Swift",
                    "Iraadey        - Abdul Hannan",
                    "Lingi Lingidi  - Balayya",
                    "Dolby Walya    - Ajay Atul",
                    "Aika Dajiba    - Amol Rathod"
            };
            public int getSize() { return songList.length; }
            public String getElementAt(int i) { return songList[i]; }
        });
        SongList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        SongList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    selectedIndex = SongList.getSelectedIndex();
                    playSelectedSong();
                    displaySongArtistnames(selectedIndex);
                    displaySongImage(selectedIndex);
                    System.out.println("Selected JList index: " + selectedIndex);
                }
            }
        });
        ScrollPane.setViewportView(SongList);

        ListLabel.setBackground(new java.awt.Color(0, 0, 0));
        ListLabel.setFont(new java.awt.Font("Segoe UI", 1, 24));
        ListLabel.setForeground(new java.awt.Color(255, 65, 37));
        ListLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ListLabel.setText("LIST OF SONGS");

        javax.swing.GroupLayout RightPanelLayout = new javax.swing.GroupLayout(RightPanel);
        RightPanel.setLayout(RightPanelLayout);
        RightPanelLayout.setHorizontalGroup(
                RightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(RightPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(RightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(ScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(ListLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(19, Short.MAX_VALUE))
        );
        RightPanelLayout.setVerticalGroup(
                RightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RightPanelLayout.createSequentialGroup()
                                .addContainerGap(16, Short.MAX_VALUE)
                                .addComponent(ListLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(ScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        BottomPanel.setBackground(new java.awt.Color(0, 0, 0));
        ProgressBar.setForeground(new java.awt.Color(255, 43, 39));
        ProgressBar.setBorder(null);
        ProgressBar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ProgressBar.addMouseListener(new java.awt.event.MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e) {
                isDragged = true;
                System.out.println("progress bar clicked");
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                isDragged = false;
                playAudio();
                System.out.println("progress bar released");
            }
        });
        ProgressBar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter(){
            @Override
            public void mouseDragged(MouseEvent e) {
                if(isDragged){
                    updateDraggedProgressBarValue(e);
                }
            }
        });


        PrevBtn.setBackground(new java.awt.Color(255, 43, 39));
        PrevBtn.setForeground(new java.awt.Color(255, 51, 51));
        PrevBtn.setIcon(new javax.swing.ImageIcon("C:\\Users\\abdul\\IdeaProjects\\Music player\\src\\assets\\icons\\previous.png")); // NOI18N
        PrevBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrevBtnActionPerformed(evt);
            }
        });

        PlayBtn.setBackground(new java.awt.Color(255, 54, 33));
        PlayBtn.setIcon(new javax.swing.ImageIcon("C:\\Users\\abdul\\IdeaProjects\\Music player\\src\\assets\\icons\\play.png")); // NOI18N
        PlayBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PlayBtnActionPerformed(evt);
            }
        });

        NextBtn.setBackground(new java.awt.Color(255, 48, 30));
        NextBtn.setIcon(new javax.swing.ImageIcon("C:\\Users\\abdul\\IdeaProjects\\Music player\\src\\assets\\icons\\next.png")); // NOI18N
        NextBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout BottomPanelLayout = new javax.swing.GroupLayout(BottomPanel);
        BottomPanel.setLayout(BottomPanelLayout);
        BottomPanelLayout.setHorizontalGroup(
                BottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(BottomPanelLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(BottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BottomPanelLayout.createSequentialGroup()
                                                .addComponent(PrevBtn)
                                                .addGap(116, 116, 116)
                                                .addComponent(PlayBtn)
                                                .addGap(118, 118, 118)
                                                .addComponent(NextBtn)
                                                .addGap(253, 253, 253))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BottomPanelLayout.createSequentialGroup()
                                                .addComponent(ProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(161, 161, 161))))
        );
        BottomPanelLayout.setVerticalGroup(
                BottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(BottomPanelLayout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(ProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21)
                                .addGroup(BottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(PrevBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                                        .addComponent(NextBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(PlayBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout MainPanelLayout = new javax.swing.GroupLayout(MainPanel);
        MainPanel.setLayout(MainPanelLayout);
        MainPanelLayout.setHorizontalGroup(
                MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(MainPanelLayout.createSequentialGroup()
                                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(MainPanelLayout.createSequentialGroup()
                                                .addComponent(LeftPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(RightPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addComponent(BottomPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        MainPanelLayout.setVerticalGroup(
                MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(MainPanelLayout.createSequentialGroup()
                                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(RightPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(LeftPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BottomPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 894, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE)
                                .addContainerGap())
        );

        pack();
    }

    //progress bar event listener
    private void updateDraggedProgressBarValue(MouseEvent e){
        if(!isplayed){
            SongList.setSelectedIndex(0);
        }
            System.out.println("progress bar dragged");
            stopAudio();
            int mouseX = e.getX();
            int progressBarWidth = ProgressBar.getWidth();
            int percent = (int) (((double) mouseX / (double) progressBarWidth) * 100);
            ProgressBar.setValue(percent);
            currentFrame = (int) ((clip.getFrameLength() * percent) / 100);
            clip.setFramePosition(currentFrame);
    }

    //previous button event listener
    private void PrevBtnActionPerformed(java.awt.event.ActionEvent evt) {
        if(selectedIndex == 0){
            selectedIndex = 1;
        }
        selectedIndex -= 1;
        SongList.setSelectedIndex(selectedIndex);
        System.out.println("prev button worked");
    }

    //play button event listener
    private void PlayBtnActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            if(!isplayed){
                SongList.setSelectedIndex(0);
                isplayed = true;
            }else {
                if (isplaying) {
                    stopAudio();
                } else {
                    playAudio();
                }
            }
        }catch (Exception e){
            System.out.println("exception in play pause btn");
        }finally {
            System.out.println("play button worked");
        }
    }

    //next button event listener
    private void NextBtnActionPerformed(java.awt.event.ActionEvent evt) {
        if(selectedIndex == SongList.getModel().getSize()-1){
            selectedIndex = -1;
        }
        selectedIndex += 1;
        SongList.setSelectedIndex(selectedIndex);
    }

    //method to fill progress bar
    private void fillProgressBar(){
        new Thread(()->{
            while(clip.isOpen() && clip.getMicrosecondPosition()<clip.getMicrosecondLength()){
                int progress = (int) (((double)clip.getMicrosecondPosition()/clip.getMicrosecondLength())*100);
                SwingUtilities.invokeLater(() -> ProgressBar.setValue(progress));
                try {
                    Thread.sleep(100);
                }catch (Exception e){
                    System.out.println("Exception in filling progress bar");
                    e.printStackTrace();
                }}}).start();
    }

    //method to play selected song from JList
    private void playSelectedSong(){
        if(!isplayed){
            isplayed = true;
        }else{
            stopAudio();
        }
        currentFrame = 0;
        createClip(selectedIndex);
        playAudio();
        System.out.println("playing selected song");
    }

    //method to display song and artist names using jaudiotagger library from metadata of song file.
    private void displaySongArtistnames(int indx){
        songArtistDisplay sad = new songArtistDisplay(indx);
        jLabel3.setText(sad.getSongTitle());
        jLabel2.setText(sad.getSongArtist());
        System.out.println("song and artist name displayed");
    }

    //method to retrieve and  display song and artist names
    private void displaySongImage(int indx){
        ImageUpdater IU = new ImageUpdater(indx);
        Icon newIcon = new ImageIcon(IU.getImagepath());
        jLabel1.setIcon(newIcon);
        System.out.println("image displayed");
    }

    //method to start clip and play audio file
    private void playAudio() {
        try {
            if (currentFrame > 0) {
                clip.close();
                createClip(selectedIndex);
                clip.setFramePosition(currentFrame);
            }
            isplaying = true;
            fillProgressBar();
            clip.start();
            Icon pauseIcon = new ImageIcon(iconPath + "pause.png");
            PlayBtn.setIcon(pauseIcon);
            if(clip.getMicrosecondLength() == clip.getMicrosecondPosition()){
                selectedIndex += 1;
                SongList.setSelectedIndex(selectedIndex);
            }
        } catch (Exception e) {
            System.out.println("Exception while creating clip and playing audio in play btn");
            e.printStackTrace();
        }finally {
            System.out.println("playing audio");
        }
    }

    //method to stop clip and stop audio file
    private void stopAudio() {
        try {
            isplaying = false;
            clip.stop();
            Icon playIcon = new ImageIcon(iconPath + "play.png");
            PlayBtn.setIcon(playIcon);
            currentFrame = clip.getFramePosition();
        } catch (Exception e) {
            System.out.println("Exception while stopping the audio file from playing (stopAudio method)");
            e.printStackTrace();
        }finally {
            System.out.println("song stopped");
        }
    }

    //mehtod to create clip for operations on audio
    private void createClip(int songindx){
        String songtoplay;
        if(songindx >= 0) {
            songtoplay = audioFolderPath + "song" + Integer.toString(songindx+1)+".wav";
            songindx = -1;
        }else{
            songtoplay = audioFolderPath+"song1.wav";
        }
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File(songtoplay).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            System.out.println("exception while opening clip");
            e.printStackTrace();
        }finally {
            System.out.println("clip created");
        }

    }




    /// INITIALIZING INSTANCE VARIABLES
    private boolean isDragged;
    private boolean isplaying;
    private boolean isplayed;
    private int selectedIndex;
    private Clip clip;
    private int currentFrame;
    private AudioInputStream audioInputStream;
    private String iconPath = "C:\\Users\\abdul\\IdeaProjects\\Music player\\src\\assets\\icons\\";
    private String audioFolderPath = "C:\\Users\\abdul\\IdeaProjects\\Music player\\src\\assets\\wavSongs\\";


    ///INITIALIZING COMPONENTS
    private javax.swing.JPanel BottomPanel;
    private javax.swing.JPanel LeftPanel;
    private javax.swing.JLabel ListLabel;
    private javax.swing.JPanel MainPanel;
    private javax.swing.JButton NextBtn;
    private javax.swing.JButton PlayBtn;
    private javax.swing.JButton PrevBtn;
    private javax.swing.JProgressBar ProgressBar;
    private javax.swing.JPanel RightPanel;
    private javax.swing.JScrollPane ScrollPane;
    private javax.swing.JList<String> SongList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
}
