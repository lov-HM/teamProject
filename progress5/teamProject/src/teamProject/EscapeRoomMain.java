package teamProject;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class EscapeRoomMain extends JFrame {
    private CardLayout cardLayout = new CardLayout(); 
    private JPanel mainPanel;
    private JPanel menuPanel; 
    private JPanel officeRoomPanel;
    private JPanel storyPanel;
    private JPanel rankingPanel;
    private JPanel typingPanel;
    private JLabel clueLabel1;
    private JLabel clueLabel2;
    private JLabel clueLabel3;
    private JLabel clueLabel4;
    private JPanel endPanel;
    private boolean clue1Visible = false;
    private boolean clue2Visible = false;
    private boolean clue3Visible = false;
    private boolean clue4Visible = false;
    private ScoreManager scoreManager;
    private String playerName;
    private long startTime;
    SoundPlayer soundPlayer = new SoundPlayer();
    
    public EscapeRoomMain() {
    	scoreManager = new ScoreManager("src/Ranking.txt");
    	
        setTitle("Escape Room");
        setSize(1200, 1000);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        mainPanel = new JPanel(cardLayout);
        add(mainPanel);
        createMainMenu();
        createStoryPanel();
        createOfficeRoom();
        createEndPanel();
        createRankingPanel();
        
        cardLayout.show(mainPanel, "Menu");
        setVisible(true);
    }
    
    public void startGame() {
        startCreateTypingPanel();
        cardLayout.show(mainPanel, "TypingPanel");
    }

    public void endGame() {
        endCreateTypingPanel();
        cardLayout.show(mainPanel, "TypingPanel");
    }
    
    public void PlayerStart() {
    	startTime = System.currentTimeMillis();
    	
        while (true) {
            try {
                playerName = JOptionPane.showInputDialog(
                    this,
                    "플레이어 이름을 입력하세요:", 
                    "플레이어 이름 입력",
                    JOptionPane.PLAIN_MESSAGE
                );

                if (playerName == null || playerName.trim().isEmpty()) {
                    throw new EmptyNameException("이름을 입력해야 합니다.");
                }
                break;

            } catch (EmptyNameException e) {
                JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "!경고!",
                    JOptionPane.WARNING_MESSAGE
                );
            }
        }
    }

    private void createMainMenu() {
        menuPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("src/images/시작페이지.png");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        menuPanel.setLayout(null);
        
        soundPlayer.playMenuBGM();

        JButton startButton = new JButton(new ImageIcon("src/images/시작기본.png"));
        startButton.setBounds(50, 700, 300, 80);
        startButton.setBorderPainted(false);
        startButton.setContentAreaFilled(false);
        startButton.setFocusPainted(false);
        startButton.addMouseListener(new MouseAdapter() {
        	 @Override
             public void mouseEntered(MouseEvent e) {
                 startButton.setIcon(new ImageIcon("src/images/시작노랑.png"));
                 startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
             }

             @Override
             public void mouseExited(MouseEvent e) {
                 startButton.setIcon(new ImageIcon("src/images/시작기본.png"));
                 startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
             }

             @Override
             public void mousePressed(MouseEvent e) {
            	 startGame();
            	 startShowTypingEffect();
            	 
            	 soundPlayer.playGameBGM();
             }
         });
         menuPanel.add(startButton);

        JButton quitButton = new JButton(new ImageIcon("src/images/종료기본.png"));
        quitButton.setBounds(830, 800, 300, 80);
        quitButton.setBorderPainted(false);
        quitButton.setContentAreaFilled(false);
        quitButton.setFocusPainted(false);
        quitButton.addMouseListener(new MouseAdapter() {
        	@Override
            public void mouseEntered(MouseEvent e) {
                quitButton.setIcon(new ImageIcon("src/images/종료노랑.png"));
                quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                quitButton.setIcon(new ImageIcon("src/images/종료기본.png"));
                quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                System.exit(0);
            }
        });
        menuPanel.add(quitButton);

        JButton storyButton = new JButton(new ImageIcon("src/images/설명기본.png"));
        storyButton.setBounds(50, 800, 300, 80);
        storyButton.setFont(new Font("Arial", Font.BOLD, 16));
        storyButton.setFocusPainted(false);
        storyButton.setBorderPainted(false);
        storyButton.setContentAreaFilled(false);
        storyButton.setFocusPainted(false);

        storyButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                storyButton.setIcon(new ImageIcon("src/images/설명노랑.png"));
                storyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                storyButton.setIcon(new ImageIcon("src/images/설명기본.png"));
                storyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
        storyButton.addActionListener(e -> cardLayout.show(mainPanel, "Story"));
        menuPanel.add(storyButton);
        
        mainPanel.add(menuPanel, "Menu");
        
        JButton rankingButton = new JButton(new ImageIcon("src/images/랭킹기본.png"));
        rankingButton.setBounds(830, 700, 300, 80);
        rankingButton.setFont(new Font("Arial", Font.BOLD, 16));
        rankingButton.setFocusPainted(false);
        rankingButton.setBorderPainted(false);
        rankingButton.setContentAreaFilled(false);
        rankingButton.setFocusPainted(false);
        rankingButton.addMouseListener(new MouseAdapter() {
        	 @Override
             public void mouseEntered(MouseEvent e) {
        		 rankingButton.setIcon(new ImageIcon("src/images/랭킹노랑.png"));
        		 rankingButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
             }

             @Override
             public void mouseExited(MouseEvent e) {
            	 rankingButton.setIcon(new ImageIcon("src/images/랭킹기본.png"));
                 rankingButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
             }
         });
        rankingButton.addActionListener(e -> {
            RankingGUI ranking = new RankingGUI();
            String filePath = "C:\\Users\\ckyst\\Desktop\\Ranking.txt";
            JPanel rankingPanel = ranking.displayRanking(filePath);
            cardLayout.show(mainPanel, "Ranking");
        });
         menuPanel.add(rankingButton);
    }
    
    private void createOfficeRoom() {
        officeRoomPanel = new JPanel(null);

        JButton computerpasswordgamebutton = new JButton(new ImageIcon("src/images/컴퓨터 원래.png"));
        computerpasswordgamebutton.setBounds(771, 114, 223, 178);
        computerpasswordgamebutton.setBorderPainted(false);
        computerpasswordgamebutton.setContentAreaFilled(false);
        computerpasswordgamebutton.setFocusable(false);
        computerpasswordgamebutton.addMouseListener(new MouseAdapter() {
            @Override        	
            public void mouseEntered(MouseEvent e) {
                computerpasswordgamebutton.setIcon(new ImageIcon("src/images/컴퓨터 진하게.png"));
                computerpasswordgamebutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                computerpasswordgamebutton.setIcon(new ImageIcon("src/images/컴퓨터 원래.png"));
                computerpasswordgamebutton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e) {
                new Password(EscapeRoomMain.this);
            }
        });
        officeRoomPanel.add(computerpasswordgamebutton);

        JButton DaruButton = new JButton(new ImageIcon("src/images/Daru1.png"));
        DaruButton.setBounds(30,530, 350, 500);
        DaruButton.setBorderPainted(false);
        DaruButton.setContentAreaFilled(false);
        DaruButton.setFocusable(false);
        DaruButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                DaruButton.setIcon(new ImageIcon("src/images/Daru2.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                DaruButton.setIcon(new ImageIcon("src/images/Daru1.png"));
                DaruButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
        officeRoomPanel.add(DaruButton);

        JButton logicartbutton = new JButton(new ImageIcon("src/images/쪽지 원본.png"));
        logicartbutton.setBounds(670, 260, 70, 70);
        logicartbutton.setBorderPainted(false);
        logicartbutton.setContentAreaFilled(false);
        logicartbutton.setFocusable(false);
        logicartbutton.addMouseListener(new MouseAdapter() {
            @Override        	
            public void mouseEntered(MouseEvent e) {
                logicartbutton.setIcon(new ImageIcon("src/images/쪽지 진하게.png"));
                logicartbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                logicartbutton.setIcon(new ImageIcon("src/images/쪽지 원본.png"));
                logicartbutton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e) {
                NonogramMonitor nonogramMonitor = new NonogramMonitor(EscapeRoomMain.this);
                nonogramMonitor.show();
            }
        });

        officeRoomPanel.add(logicartbutton);

        JButton key1button = new JButton(new ImageIcon("src/images/SmallBlueKey.png"));
        key1button.setBounds(900, 520, 100, 100);
        key1button.setBorderPainted(false);
        key1button.setContentAreaFilled(false);
        key1button.setFocusable(false);
        key1button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                key1button.setIcon(new ImageIcon("src/images/SmallBlueKey.png"));
                key1button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                key1button.setIcon(new ImageIcon("src/images/SmallBlueKey.png"));
                key1button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                key1button.setBounds(380, 710, 100, 100);
            }
        });

        officeRoomPanel.add(key1button);

        JButton key2button = new JButton(new ImageIcon("src/images/RedSmallKey.png"));
        key2button.setBounds(10000, 300, 220, 220);
        key2button.setBorderPainted(false);
        key2button.setContentAreaFilled(false);
        key2button.setFocusable(false);
        key2button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                key2button.setIcon(new ImageIcon("src/images/RedSmallKey.png"));
                key2button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                key2button.setIcon(new ImageIcon("src/images/RedSmallKey.png"));
                key2button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                key2button.setBounds(480, 710, 100, 100);
            }
        });

        officeRoomPanel.add(key2button);

        JButton door1button = new JButton(new ImageIcon("src/images/서랍1.png"));
        door1button.setBounds(605, 365, 148, 73 );
        door1button.setBorderPainted(false);
        door1button.setContentAreaFilled(false);
        door1button.setFocusable(false);
        door1button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                door1button.setIcon(new ImageIcon("src/images/서랍1 어둡게.png"));
                door1button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                door1button.setIcon(new ImageIcon("src/images/서랍1.png"));
                door1button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) { 
                if (key1button.getBounds().equals(new Rectangle(380, 710, 100, 100))) {
                    key2button.setBounds(480, 710, 100, 100);
                    showClue4();
                    JOptionPane.showMessageDialog(
                        officeRoomPanel,
                        "열쇠와 지도를 획득했습니다",
                        "문이 열렸습니다!",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                } else {
                    JOptionPane.showMessageDialog(
                        officeRoomPanel,
                        "서랍이 잠겨있습니다!",
                        "상호작용 실패",
                        JOptionPane.WARNING_MESSAGE
                    );
                }
            }

        });

        officeRoomPanel.add(door1button);

        JButton door2button = new JButton(new ImageIcon("src/images/서랍2.png"));
        door2button.setBounds(605, 435, 148, 73);
        door2button.setBorderPainted(false);
        door2button.setContentAreaFilled(false);
        door2button.setFocusable(false);
        door2button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                door2button.setIcon(new ImageIcon("src/images/서랍2 어둡게.png"));
                door2button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                door2button.setIcon(new ImageIcon("src/images/서랍2.png"));
                door2button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (key2button.getBounds().equals(new Rectangle(480, 710, 100, 100))) {
                    JOptionPane.showMessageDialog(
                        officeRoomPanel,
                        "프로그래밍 방법론을 듣는 강의실 번호는?",
                        "비밀번호 힌트!",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                } else {
                    JOptionPane.showMessageDialog(
                        officeRoomPanel,
                        "키가 필요합니다!",
                        "상호작용 실패",
                        JOptionPane.WARNING_MESSAGE
                    );
                }

            }
        });

        officeRoomPanel.add(door2button);

        JButton bookbutton = new JButton(new ImageIcon("src/images/책.png"));
        bookbutton.setBounds(350, 405, 60, 140);
        bookbutton.setBorderPainted(false);
        bookbutton.setContentAreaFilled(false);
        bookbutton.setFocusable(false);
        bookbutton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (clue1Visible && clue2Visible && clue3Visible && clue4Visible) {
                    bookbutton.setIcon(new ImageIcon("src/images/책 어둡게.png"));
                    bookbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (clue1Visible && clue2Visible && clue3Visible && clue4Visible) {
                    bookbutton.setIcon(new ImageIcon("src/images/책.png"));
                    bookbutton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (clue1Visible && clue2Visible && clue3Visible && clue4Visible) {
                    long endTime = System.currentTimeMillis();
                    int secDiffTime = (int) ((endTime - startTime) / 1000);

                    JOptionPane.showMessageDialog(null, "축하합니다!\n전설의 족보를 얻었습니다.", "결과", JOptionPane.INFORMATION_MESSAGE);

                    String name = playerName;

                    scoreManager.addPlayer(name, secDiffTime);
                    scoreManager.saveScores();
                    scoreManager.printRanking();

                    cardLayout.show(mainPanel, "End");

                    Timer timer = new Timer(5000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            endGame();
                            endShowTypingEffect();
                        }
                    });
                    timer.setRepeats(false);
                    timer.start();
                }
            }
        });

        officeRoomPanel.add(bookbutton);

        clueLabel1 = new JLabel(new ImageIcon("src/images/지도1.png"));
        clueLabel1.setBounds(740, 820, 300, 100); 
        clueLabel1.setVisible(false);
        officeRoomPanel.add(clueLabel1);

        clueLabel2 = new JLabel(new ImageIcon("src/images/지도2.png"));
        clueLabel2.setBounds(740, 725, 300, 100);
        clueLabel2.setVisible(false);
        officeRoomPanel.add(clueLabel2);

        clueLabel3 = new JLabel(new ImageIcon("src/images/지도4.png"));
        clueLabel3.setBounds(20, 40, 300, 100);
        clueLabel3.setVisible(true);
        officeRoomPanel.add(clueLabel3);      
        clueLabel3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                clueLabel3.setLocation(881,725);
            }
        });

        clueLabel4 = new JLabel(new ImageIcon("src/images/지도3.png"));
        clueLabel4.setBounds(881, 820, 300, 100);
        clueLabel4.setVisible(false);
        officeRoomPanel.add(clueLabel4);

        clueLabel3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                clueLabel3.setLocation(881, 725);
                clue3Visible = true;
            }
        });

        JLabel roomLabel = new JLabel(new ImageIcon("src/images/게임화면.png"));
        roomLabel.setBounds(0, 0, 1200, 1000);
        officeRoomPanel.add(roomLabel);

        mainPanel.add(officeRoomPanel, "OfficeRoom");
    	}

        private void createRankingPanel() {
            RankingGUI ranking = new RankingGUI();
            String filePath = "src/Ranking.txt";
            JPanel rankingPanel = ranking.displayRanking(filePath);

            JButton backButton = new JButton("Back");
            backButton.setFont(new Font("Arial", Font.PLAIN, 18));
            backButton.setBounds(650, 750, 100, 40);
            backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
            rankingPanel.add(backButton);

            mainPanel.add(rankingPanel, "Ranking");
        }

        private void createStoryPanel() {
            storyPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    ImageIcon backgroundImage = new ImageIcon("src/images/스토리.png"); 
                    g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
                }
            };
            storyPanel.setLayout(null);

            JButton backButton = new JButton("Back");
            backButton.setFont(new Font("Arial", Font.PLAIN, 18));
            backButton.setBounds(650, 750, 100, 40);
            backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
            storyPanel.add(backButton);

            mainPanel.add(storyPanel, "Story");
        }

        private void createEndPanel() {
            endPanel = new JPanel(new BorderLayout());
            endPanel.setBackground(Color.WHITE);

            ImageIcon endImageIcon = new ImageIcon("src/images/전설의족보.png");
            JLabel endImageLabel = new JLabel(endImageIcon);
            endImageLabel.setHorizontalAlignment(SwingConstants.CENTER);

            endPanel.add(endImageLabel, BorderLayout.CENTER);

            mainPanel.add(endPanel, "End");
        }

        private boolean skipped = false; 

        private Image[] backgroundImages = {
        	new ImageIcon("src/images/고뇌.png").getImage(),
            new ImageIcon("src/images/충격.png").getImage(),
            new ImageIcon("src/images/편지봉투.png").getImage(),
            new ImageIcon("src/images/고뇌.png").getImage(),
            new ImageIcon("src/images/고뇌.png").getImage()
        };
        private int currentBackgroundIndex = 0;

        private void startCreateTypingPanel() {
            typingPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(backgroundImages[currentBackgroundIndex], 0, 0, getWidth(), getHeight(), this);
                }
            };
            typingPanel.setLayout(null);
            typingPanel.setBounds(0, 0, 1200, 1000);

            JTextArea typingArea = new JTextArea();
            typingArea.setFont(new Font("NanumGothic", Font.BOLD, 30));
            typingArea.setForeground(Color.WHITE);
            typingArea.setBackground(Color.BLACK);
            typingArea.setBounds(100, 750, 1000, 200);
            typingArea.setLineWrap(true);
            typingArea.setWrapStyleWord(true);
            typingArea.setEditable(false);
            typingArea.setVisible(true);

            typingPanel.add(typingArea);

            addSkipButtonToPanel(1000, 50, () -> {
                skipped = true;
                PlayerStart();
                cardLayout.show(mainPanel, "OfficeRoom");
            });

            mainPanel.add(typingPanel, "TypingPanel");
            mainPanel.revalidate();
            mainPanel.repaint();
        }

        private Image[] endBackgroundImages = {
        	    new ImageIcon("src/images/책줍.png").getImage(),
        	    new ImageIcon("src/images/책놀1.png").getImage(),
        	    new ImageIcon("src/images/팔더블유.png").getImage(),
        	    new ImageIcon("src/images/주먹.png").getImage(),
        	    new ImageIcon("src/images/하늘찌르기.png").getImage(),
        	    new ImageIcon("src/images/고뇌.png").getImage(),
        	    new ImageIcon("src/images/책놀1.png").getImage(),
        	    new ImageIcon("src/images/주먹.png").getImage()
        };
    	private int currentEndBackgroundIndex = 0;

    	private void endCreateTypingPanel() {
    	    typingPanel = new JPanel() {
    	        @Override
    	        protected void paintComponent(Graphics g) {
    	            super.paintComponent(g);
    	            g.drawImage(endBackgroundImages[currentEndBackgroundIndex], 0, 0, getWidth(), getHeight(), this);
    	        }
    	    };
    	    typingPanel.setLayout(null);
    	    typingPanel.setBounds(0, 0, 1200, 1000);

    	    JTextArea typingArea = new JTextArea();
    	    typingArea.setFont(new Font("NanumGothic", Font.BOLD, 30));
    	    typingArea.setForeground(Color.WHITE);
    	    typingArea.setBackground(Color.BLACK);
    	    typingArea.setBounds(100, 750, 1000, 200);
    	    typingArea.setLineWrap(true);
    	    typingArea.setWrapStyleWord(true);
    	    typingArea.setEditable(false);
    	    typingArea.setVisible(true);

    	    typingPanel.add(typingArea);

    	    addSkipButtonToPanel(1000, 50, () -> {
    	        skipped = true;
    	        showEndingMessage();
    	        System.exit(0);
    	    });

    	    mainPanel.add(typingPanel, "TypingPanel");
    	    mainPanel.revalidate();
    	    mainPanel.repaint();
    	}

    	private void startShowTypingEffect() {
    	    String[] texts = {
    	        "하 프방은 진짜 A+받고싶다.. 무리무리! A+은 너무하다고www\n"
    	        + "어디 족보없나",
    	        "다루다루 : “으악. 아 아파라… 어? 이게뭐지?”\n",
    	        "내 필기(족보) 말인가?\r\n"
    	        + "원한다면 주도록 하지!\r\n"
    	        + "어디 찾아봐라!\r\n"
    	        + "프방론의 모든 요점 정리(족보)를 거기에 두고왔다.",
    	        "다루다루 : 뭐야! 이게 바로 고대 프방현자로부터 전해진 전설의 족보인가?\n"
    	        + "아.. ~분뒤에 수업 있는데 장난이면 어떡하지..?\n"
    	        + "그래도 혹시나 모르니까 한번 가볼까?",
    	        "다루다루 : 진짠가봐…! 헉 수업시작 얼마 안남았는데! 빨리 찾아야지!!!"
    	    };

    	    skipped = false;

    	    new Thread(() -> {
    	        JTextArea typingArea = (JTextArea) typingPanel.getComponent(0);
    	        for (int i = 0; i < texts.length; i++) {
    	            if (skipped) break;

    	            typingArea.setVisible(true);
    	            currentBackgroundIndex = i % backgroundImages.length;
    	            typingPanel.repaint();

    	            for (int j = 0; j <= texts[i].length(); j++) {
    	                if (skipped) break;
    	                typingArea.setText(texts[i].substring(0, j));
    	                try {
    	                    Thread.sleep(50);
    	                } catch (InterruptedException e) {
    	                    e.printStackTrace();
    	                }
    	            }

    	            if (skipped) break;

    	            try {
    	                Thread.sleep(1000);
    	            } catch (InterruptedException e) {
    	                e.printStackTrace();
    	            }

    	            typingArea.setText("");
    	            try {
    	                Thread.sleep(500);
    	            } catch (InterruptedException e) {
    	                e.printStackTrace();
    	            }
    	        }

    	        if (!skipped) {
    	            PlayerStart();
    	            cardLayout.show(mainPanel, "OfficeRoom");
    	        }
    	    }).start();

    	    addSkipButtonToPanel(1000, 50, () -> {
    	        skipped = true;
    	        PlayerStart();
    	        cardLayout.show(mainPanel, "OfficeRoom");
    	    });
    	}

    	private void endShowTypingEffect() {
    	    soundPlayer.playEndding();
    	    String[] texts = {
    	        "다루다루 :  ... 아니... 이럴수가..!!! \n",
    	        "다루다루 : 이제 이 방법만 체득하면 나도 A+을 받을 수 있는 건가..?!\n",
    	        "다루다루 : 인간이 어떻게 이런 생각을 할 수 있지..? 너무 아름답고 감동적인 방법이야...!\n",
    	        "다루다루 : 좋았어!!! 오늘부터 시작이다! \n",
    	        "다루다루 : A+ 넌 이제 내꺼야!!!\n",
    	        "시험치는중 ...\n",
    	        "다루다루 : 헉! 진짜 A+ 이잖아..! 이 족보만 있으면 [A+] 무리가 아니였다..?\n",
    	        "다루다루 : 오예~~!!!!!!!!! 평생 나만 알고있어야지!!!!!!!\n"
    	    };

    	    skipped = false;

    	    new Thread(() -> {
    	        JTextArea typingArea = (JTextArea) typingPanel.getComponent(0);
    	        for (int i = 0; i < texts.length; i++) {
    	            if (skipped) break;

    	            typingArea.setVisible(true);

    	            currentEndBackgroundIndex = i % endBackgroundImages.length;
    	            typingPanel.repaint();

    	            for (int j = 0; j <= texts[i].length(); j++) {
    	                if (skipped) break;
    	                typingArea.setText(texts[i].substring(0, j));
    	                try {
    	                    Thread.sleep(50);
    	                } catch (InterruptedException e) {
    	                    e.printStackTrace();
    	                }
    	            }
    	            if (skipped) break;

    	            try {
    	                Thread.sleep(1000);
    	            } catch (InterruptedException e) {
    	                e.printStackTrace();
    	            }

    	            typingArea.setText("");
    	            try {
    	                Thread.sleep(500);
    	            } catch (InterruptedException e) {
    	                e.printStackTrace();
    	            }
    	        }

    	        if (!skipped) {
    	            showEndingMessage();
    	            System.exit(0);
    	        }
    	    }).start();

    	    addSkipButtonToPanel(1000, 50, () -> {
    	        skipped = true;
    	        showEndingMessage();
    	        System.exit(0);
    	    });
    	}

    	private void addSkipButtonToPanel(int x, int y, Runnable action) {
            for (Component comp : typingPanel.getComponents()) {
                if (comp instanceof JButton && ((JButton) comp).getText().equals("Skip")) {
                    return;
                }
            }
            JButton skipButton = new JButton(new ImageIcon("src/images/스킵.png")); 
            skipButton.setBounds(x, y, 160, 100);  
            skipButton.setBorderPainted(false);
            skipButton.setContentAreaFilled(false);
            skipButton.setFocusPainted(false);

            skipButton.addActionListener(e -> action.run());
            typingPanel.add(skipButton);
        }

    	public void showClue1() {
    	    clueLabel1.setVisible(true);
    	    clue1Visible = true;
    	    System.out.print(playerName);
    	}

    	public void showClue2() {
    	    clueLabel2.setVisible(true);
    	    clue2Visible = true;
    	}

    	public void showClue4() {
    	    clueLabel4.setVisible(true);
    	    clue4Visible = true;
    	}

    	private void showEndingMessage() {
    	    JOptionPane.showMessageDialog(this, "엔딩 완료!\n게임이 끝났습니다.", "엔딩 완료", JOptionPane.INFORMATION_MESSAGE);
    	}
}