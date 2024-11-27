package password;

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
    private JLabel clueLabel;

    public EscapeRoomMain() {
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
        createTypingPanel(); // 새로운 패널 생성 메서드 호출
        
        cardLayout.show(mainPanel, "Menu");
        setVisible(true);
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
            	 cardLayout.show(mainPanel, "TypingPanel");
            	 showTypingEffect();
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
    }

    private void createOfficeRoom() {
        officeRoomPanel = new JPanel(null);
        

        JButton coputerpasswordgamebutton = new JButton(new ImageIcon("src/images/컴퓨터 원래.png"));
        coputerpasswordgamebutton.setBounds(771, 114, 223, 178);
        coputerpasswordgamebutton.setBorderPainted(false);
        coputerpasswordgamebutton.setContentAreaFilled(false);
        coputerpasswordgamebutton.setFocusable(false);
        coputerpasswordgamebutton.addMouseListener(new MouseAdapter() {
          @Override        	
        	public void mouseEntered(MouseEvent e) {
        	  coputerpasswordgamebutton.setIcon(new ImageIcon("src/images/컴퓨터 진하게.png"));
        	  coputerpasswordgamebutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	coputerpasswordgamebutton.setIcon(new ImageIcon("src/images/컴퓨터 원래.png"));
            	coputerpasswordgamebutton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e) {
                new Password(EscapeRoomMain.this);
            }
        });
        officeRoomPanel.add(coputerpasswordgamebutton);
        
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

        clueLabel = new JLabel();
        clueLabel.setOpaque(true);
        clueLabel.setBackground(Color.BLUE);
        clueLabel.setBounds(1000, 750, 100, 50);
        clueLabel.setVisible(false);
        officeRoomPanel.add(clueLabel);

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

    private void createTypingPanel() {
        typingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, getWidth(), getHeight());
//                ImageIcon backgroundImage = new ImageIcon("src/images/스토리배경.jpg"); // 이미지 배경
//                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        typingPanel.setLayout(null);
        typingPanel.setBounds(0, 0, 1200, 1000); 

        JTextArea typingArea = new JTextArea(); // JTextArea로 변경
        typingArea.setFont(new Font("NanumGothic", Font.BOLD, 30));
        typingArea.setForeground(Color.WHITE); // 텍스트 색깔
        typingArea.setBackground(Color.BLACK); // 배경색을 검은색으로 설정
        typingArea.setBounds(100, 400, 1000, 200); // 텍스트 위치 조정
        typingArea.setLineWrap(true); // 줄바꿈 허용
        typingArea.setWrapStyleWord(true); // 단어 단위로 줄바꿈
        typingArea.setEditable(false); // 편집 불가능하게 설정
        typingArea.setVisible(false); // 처음에는 보이지 않게 설정
        
        typingPanel.add(typingArea);

        JButton skipButton = new JButton("Skip"); 
        skipButton.setBounds(800, 600, 150, 50); //d nrr
        skipButton.setFont(new Font("Arial", Font.PLAIN, 18));
        skipButton.addActionListener(e -> {
            skipped = true; 
            cardLayout.show(mainPanel, "OfficeRoom"); 
       
        });
        typingPanel.add(skipButton);
     

        mainPanel.add(typingPanel, "TypingPanel");
        mainPanel.revalidate();
        mainPanel.repaint();
    }


    private void showTypingEffect() {
     String[] texts = { 
         "(로비에서 어떤 그림자와 부딪혀 넘어진다)\n"
         + "(??) : “으악. 아 아파라… 어? 이게뭐지?”\n"
         + "(바닥에 떨어져 있던 편지봉투를 들어서 본다)",
         "내 필기(족보) 말인가?\r\n"
         + "원한다면 주도록 하지!\r\n"
         + "어디 찾아봐라!\r\n"
         + "프방론의 모든 요점 정리(족보)를 거기에 두고왔다.",
         "(??) : 뭐야! 이게 바로 그 소문의 전설의 족보인가?\n" + "        아.. ~분뒤에 수업 있는데 장난이면 어떡하지..?\n"
         +"        그래도 혹시나 모르니까 한번 가볼까?",
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
             cardLayout.show(mainPanel, "OfficeRoom"); 
         }
     }).start();
 }
 // 여기까지요



    public void showClue() {
        clueLabel.setVisible(true);
    }

//    public static void main(String[] args) {
//        new EscapeRoomMain(); // EscapeRoomMain 클래스 인스턴스 생성
//    }
}
