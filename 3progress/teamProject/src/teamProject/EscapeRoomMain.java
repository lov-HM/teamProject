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
    private JPanel typingPanel; // 새로운 패널 추가
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
   
    public EscapeRoomMain() {
    	scoreManager = new ScoreManager("C:\\Users\\ckyst\\Desktop\\Ranking.txt");
    	
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

        JButton startButton = new JButton(new ImageIcon("src/images/시작기본.png"));
        startButton.setBounds(20, 700, 300, 80);
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
             }
         });
         menuPanel.add(startButton);

        JButton quitButton = new JButton(new ImageIcon("src/images/종료기본.png"));
        quitButton.setBounds(860, 800, 300, 80);
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
        storyButton.setBounds(20, 800, 300, 80);
        quitButton.setBorderPainted(false);
        quitButton.setContentAreaFilled(false);
        quitButton.setFocusPainted(false);
        quitButton.addMouseListener(new MouseAdapter() {
        	@Override
            public void mouseEntered(MouseEvent e) {
                quitButton.setIcon(new ImageIcon("src/images/설명노랑.png"));
                quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                quitButton.setIcon(new ImageIcon("src/images/설명기본.png"));
                quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
            	
            }
        });
        menuPanel.add(storyButton);
        
        mainPanel.add(menuPanel, "Menu");
        
        JButton rankingButton = new JButton(new ImageIcon("src/images/랭킹기본.png"));
        rankingButton.setBounds(860, 700, 300, 80);
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

             @Override
             public void mousePressed(MouseEvent e) {
            	 RankingGUI rankingGUI = new RankingGUI();
                 rankingGUI.showRanking(scoreManager);
             }
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
        
        //key2 버튼
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
        
        //door1 버튼
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
                // key1button의 위치가 (380, 710, 100, 100)일 때 문을 열 수 있음
                if (key1button.getBounds().equals(new Rectangle(380, 710, 100, 100))) {
                    key2button.setBounds(480, 710, 100, 100); // 키2가 나타남
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
        
        //door2
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
                   //족보조각 추가
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
                // 세 가지 단서를 모두 모았는지 확인
                if (clue1Visible && clue2Visible && clue3Visible && clue4Visible) {
                    long endTime = System.currentTimeMillis();
                    int secDiffTime = (int) ((endTime - startTime) / 1000);

                    JOptionPane.showMessageDialog(null, "정답입니다!", "결과", JOptionPane.INFORMATION_MESSAGE);
                    
                    String name = playerName;

                    scoreManager.addPlayer(name, secDiffTime);
                    scoreManager.saveScores();
                    scoreManager.printRanking();
                    
                    cardLayout.show(mainPanel, "End");

                    Timer timer = new Timer(3000, new ActionListener() {
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
        }
        );
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

    private void createStoryPanel() {
        storyPanel = new JPanel(null); // Use null layout for precise positioning
        storyPanel.setBackground(Color.WHITE);

        // Story text
        JLabel storyLabel = new JLabel("<html><center>...</center></html>");
        storyLabel.setFont(new Font("Arial", Font.BOLD, 20));
        storyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        storyLabel.setBounds(300, 200, 600, 200); // Centered on the screen
        storyPanel.add(storyLabel);

        // Skip button
        JButton backButton = new JButton("back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 18));
        backButton.setBounds(550, 500, 100, 40); // Centered horizontally near the bottom
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
        storyPanel.add(backButton);

        mainPanel.add(storyPanel, "Story");
    }
    
    private void createEndPanel() {
        endPanel = new JPanel(new BorderLayout());
        endPanel.setBackground(Color.WHITE);
        
        ImageIcon endImageIcon = new ImageIcon("src/images/전설의족보.png"); // 원하는 이미지 파일 경로
        JLabel endImageLabel = new JLabel(endImageIcon);
        endImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        endPanel.add(endImageLabel, BorderLayout.CENTER);
        
        mainPanel.add(endPanel, "End");
    }
    
    // 추가   
    private boolean skipped = false; 

    private void startCreateTypingPanel() {
        typingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        typingPanel.setLayout(null);
        typingPanel.setBounds(0, 0, 1200, 1000);

        JTextArea typingArea = new JTextArea();
        typingArea.setFont(new Font("NanumGothic", Font.BOLD, 30));
        typingArea.setForeground(Color.WHITE);
        typingArea.setBackground(Color.BLACK);
        typingArea.setBounds(100, 400, 1000, 200);
        typingArea.setLineWrap(true);
        typingArea.setWrapStyleWord(true);
        typingArea.setEditable(false);
        typingArea.setVisible(true);

        typingPanel.add(typingArea);

        addSkipButtonToPanel(800, 600, () -> {
            skipped = true;
            PlayerStart();
            cardLayout.show(mainPanel, "OfficeRoom");
        });

        mainPanel.add(typingPanel, "TypingPanel");
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void endCreateTypingPanel() {
        typingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        typingPanel.setLayout(null);
        typingPanel.setBounds(0, 0, 1200, 1000);

        JTextArea typingArea = new JTextArea();
        typingArea.setFont(new Font("NanumGothic", Font.BOLD, 30));
        typingArea.setForeground(Color.WHITE);
        typingArea.setBackground(Color.BLACK);
        typingArea.setBounds(100, 400, 1000, 200);
        typingArea.setLineWrap(true);
        typingArea.setWrapStyleWord(true);
        typingArea.setEditable(false);
        typingArea.setVisible(true);

        typingPanel.add(typingArea);

        addSkipButtonToPanel(800, 600, () -> {
            skipped = true;
            System.exit(0);
        });

        mainPanel.add(typingPanel, "TypingPanel");
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void startShowTypingEffect() {
        String[] texts = {
            "(로비에서 어떤 그림자와 부딪혀 넘어진다)\n"
            + "(??) : “으악. 아 아파라… 어? 이게뭐지?”\n"
            + "(바닥에 떨어져 있던 편지봉투를 들어서 본다)",
            "내 필기(족보) 말인가?\r\n"
            + "원한다면 주도록 하지!\r\n"
            + "어디 찾아봐라!\r\n"
            + "프방론의 모든 요점 정리(족보)를 거기에 두고왔다.",
            "(??) : 뭐야! 이게 바로 그 소문의 전설의 족보인가?\n"
            + "        아.. ~분뒤에 수업 있는데 장난이면 어떡하지..?\n"
            + "        그래도 혹시나 모르니까 한번 가볼까?",
            "(??) : 진짠가봐…! 헉 수업시작 얼마 안남았는데! 빨리 찾아야지!!!"
        };

        skipped = false;

        new Thread(() -> {
            JTextArea typingArea = (JTextArea) typingPanel.getComponent(0); // JTextArea 참조
            for (String text : texts) {
                if (skipped) break;

                typingArea.setVisible(true); // JTextArea를 보이게 함

                for (int i = 0; i <= text.length(); i++) {
                    if (skipped) break;
                    typingArea.setText(text.substring(0, i)); // JTextArea에 텍스트 설정
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

                typingArea.setText(""); // JTextArea 비우기
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

        addSkipButtonToPanel(800, 600, () -> {
            skipped = true;
            PlayerStart();
            cardLayout.show(mainPanel, "OfficeRoom");
        });
    }

    private void endShowTypingEffect() {
        String[] texts = {
        	"(다루 책 줍는 사진)\n"
        	+"?? :  ...\n"
        	+"?? : 아니... 이럴수가..!!! \n",
        	
        	"( 다루 책들고 깜짝 놀라는 사진)\n"
        	+"?? : 이제 이 방법만 체득하면 나도 A+을 받을 수 있는 건가..?!\n",
        	
        	"(다루 팔 W 하고있는 사진)\n"
        	+"?? : 인간이 어떻게 이런 생각을 할 수 있지..? 너무 아름답고 감동적인 방법이야...!\n",
        	
        	"( 다루 주먹쥐고 있는 사진 )\n"
        	+"?? : 좋았어!!! 오늘부터 시작이다! \n",
        	
        	"(다루 하늘찌르기 사진 )\n"
        	+"?? : A+ 넌 이제 내꺼야!!!\n",

        	"시험치는중 ...(고뇌하는 다루 사진)\n",

        	"(다루 놀라는 사진 전신)\n"
       		+"?? : 헉! 진짜 A+ 이잖아..! 역시 전설의 족보인가...!!!!\n",
       		
        	"( 다루 주먹쥐고 있는 사진 )\n"
        	+"?? : 오예~~!!!!!!!!! 평생 나만 알고있어야지!!!!!!!\n",
        };

        skipped = false;

        new Thread(() -> {
            JTextArea typingArea = (JTextArea) typingPanel.getComponent(0);
            for (String text : texts) {
                if (skipped) break;

                typingArea.setVisible(true);

                for (int i = 0; i <= text.length(); i++) {
                    if (skipped) break;
                    typingArea.setText(text.substring(0, i));
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
                System.exit(0);
            }
        }).start();

        addSkipButtonToPanel(800, 600, () -> {
            skipped = true;
            System.exit(0);
        });
    }

    private void addSkipButtonToPanel(int x, int y, Runnable action) {
        for (Component comp : typingPanel.getComponents()) {
            if (comp instanceof JButton && ((JButton) comp).getText().equals("Skip")) {
                return;
            }
        }

        JButton skipButton = new JButton("Skip");
        skipButton.setBounds(x, y, 150, 50);
        skipButton.setFont(new Font("Arial", Font.PLAIN, 18));
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

}
