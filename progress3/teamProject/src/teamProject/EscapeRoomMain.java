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
                ImageIcon backgroundImage = new ImageIcon("src/images/메인화면.jpg");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        menuPanel.setLayout(null);

        JButton startButton = new JButton(new ImageIcon("src/images/startButtonBasic1.png"));
        startButton.setBounds(450, 400, 300, 80);
        startButton.setBorderPainted(false);
        startButton.setContentAreaFilled(false);
        startButton.setFocusPainted(false);
        startButton.addMouseListener(new MouseAdapter() {
        	 @Override
             public void mouseEntered(MouseEvent e) {
                 startButton.setIcon(new ImageIcon("src/images/startButtonEntered1.png"));
                 startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
             }

             @Override
             public void mouseExited(MouseEvent e) {
                 startButton.setIcon(new ImageIcon("src/images/startButtonBasic1.png"));
                 startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
             }

             @Override
             public void mousePressed(MouseEvent e) {
            	 startGame();
            	 startShowTypingEffect();
             }
         });
         menuPanel.add(startButton);

        JButton quitButton = new JButton(new ImageIcon("src/images/quitButtonBasic1.png"));
        quitButton.setBounds(450, 500, 300, 80);
        quitButton.setBorderPainted(false);
        quitButton.setContentAreaFilled(false);
        quitButton.setFocusPainted(false);
        quitButton.addMouseListener(new MouseAdapter() {
        	@Override
            public void mouseEntered(MouseEvent e) {
                quitButton.setIcon(new ImageIcon("src/images/quitButtonEntered1.png"));
                quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                quitButton.setIcon(new ImageIcon("src/images/quitButtonBasic1.png"));
                quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                System.exit(0);
            }
        });
        menuPanel.add(quitButton);

        JButton storyButton = new JButton("Story Explanation");
        storyButton.setBounds(450, 600, 300, 80);
        storyButton.setFont(new Font("Arial", Font.BOLD, 16));
        storyButton.setFocusPainted(false);
        storyButton.addActionListener(e -> cardLayout.show(mainPanel, "Story"));
        menuPanel.add(storyButton);
        
        mainPanel.add(menuPanel, "Menu");
        
        JButton rankingButton = new JButton(new ImageIcon("src/images/startButtonEntered1.png"));
        rankingButton.setBounds(450, 700, 300, 80);
        rankingButton.setBorderPainted(false);
        rankingButton.setContentAreaFilled(false);
        rankingButton.setFocusPainted(false);
        rankingButton.addMouseListener(new MouseAdapter() {
        	 @Override
             public void mouseEntered(MouseEvent e) {
        		 rankingButton.setIcon(new ImageIcon("src/images/startButtonEntered1.png"));
        		 rankingButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
             }

             @Override
             public void mouseExited(MouseEvent e) {
            	 rankingButton.setIcon(new ImageIcon("src/images/startButtonEntered1.png"));
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

                    RankingGUI rankingGUI = new RankingGUI();
                    rankingGUI.showRanking(scoreManager);

                    endGame();
                    endShowTypingEffect();
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
        clueLabel3.setBounds(800, 500, 300, 100);
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
        clueLabel4.setVisible(true);
        officeRoomPanel.add(clueLabel4);
        
        clueLabel3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                clueLabel3.setLocation(881, 725);
                clue3Visible = true;  // 상태 변수 업데이트
            }
        });
        clueLabel4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                clue4Visible = true;  // 상태 변수 업데이트
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
                PlayerStart(); // 닉네임 입력
                cardLayout.show(mainPanel, "OfficeRoom"); // 화면 전환
            }
        }).start();

        // Skip 버튼 추가 (게임 시작 시)
        addSkipButtonToPanel(800, 600, () -> {
            skipped = true;
            PlayerStart(); // 닉네임 받기
            cardLayout.show(mainPanel, "OfficeRoom"); // 화면 전환
        });
    }

    private void endShowTypingEffect() {
        String[] texts = {
            "엔딩 스토리 넣을 예정임다\n"
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
                System.exit(0); // 게임 종료
            }
        }).start();

        // Skip 버튼 추가 (게임 종료 시)
        addSkipButtonToPanel(800, 600, () -> {
            skipped = true;
            System.exit(0); // 게임 종료
        });
    }

    // 버튼 중복 방지하도록 수정
    private void addSkipButtonToPanel(int x, int y, Runnable action) {
        // Skip 버튼이 이미 추가되었는지 확인
        for (Component comp : typingPanel.getComponents()) {
            if (comp instanceof JButton && ((JButton) comp).getText().equals("Skip")) {
                return; // 이미 존재하면 추가하지 않음
            }
        }

        JButton skipButton = new JButton("Skip");
        skipButton.setBounds(x, y, 150, 50);
        skipButton.setFont(new Font("Arial", Font.PLAIN, 18));
        skipButton.addActionListener(e -> action.run()); // 버튼 클릭 시 동작 실행

        typingPanel.add(skipButton);
    }


    public void showClue1() {
        clueLabel1.setVisible(true);
        clue1Visible = true;
        System.out.print(playerName);
    }
    public void showClue2() {//              여기 추가
    	clueLabel2.setVisible(true);
    	clue2Visible = true;
    }

//    public static void main(String[] args) {
//        new EscapeRoomMain(); // EscapeRoomMain 클래스 인스턴스 생성
//    }
}
