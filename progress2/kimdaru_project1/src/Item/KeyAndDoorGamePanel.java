package Item;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class KeyAndDoorGamePanel extends JPanel {

    private ArrayList<Item> items;  // 게임 아이템
    private ArrayList<Door> doors;  // 게임 문
    private DefaultListModel<String> inventoryModel; // 인벤토리 모델
    private JList<String> inventoryList; // 인벤토리 리스트
    private JLabel statusLabel;  // 상태 라벨
    private String selectedItem = null;  // 선택된 아이템
    private JPanel inventoryPanel; // 하단 인벤토리 패널

    // 문과 키의 이미지 로딩을 위한 필드
    private ImageIcon door1Image, door2Image, door3Image;
    private ImageIcon key1Image, key2Image, key3Image, key4Image;

    public KeyAndDoorGamePanel() {
        items = new ArrayList<>();
        doors = new ArrayList<>();
        inventoryModel = new DefaultListModel<>();

        // 문과 키의 이미지를 경로에 맞춰 로드
        door1Image = new ImageIcon("C:\\Users\\USER\\Desktop\\3학년 2학기\\프로그래밍방법론\\팀프로젝트\\이미지\\door1.jpg");
        door2Image = new ImageIcon("C:\\Users\\USER\\Desktop\\3학년 2학기\\프로그래밍방법론\\팀프로젝트\\이미지\\door2.jpg");
        door3Image = new ImageIcon("C:\\Users\\USER\\Desktop\\3학년 2학기\\프로그래밍방법론\\팀프로젝트\\이미지\\door3.jpg");
        key1Image = new ImageIcon("C:\\Users\\USER\\Desktop\\3학년 2학기\\프로그래밍방법론\\팀프로젝트\\이미지\\key1.jpg");
        key2Image = new ImageIcon("C:\\Users\\USER\\Desktop\\3학년 2학기\\프로그래밍방법론\\팀프로젝트\\이미지\\key2.jpg");
        key3Image = new ImageIcon("C:\\Users\\USER\\Desktop\\3학년 2학기\\프로그래밍방법론\\팀프로젝트\\이미지\\key3.png");
        key4Image = new ImageIcon("C:\\Users\\USER\\Desktop\\3학년 2학기\\프로그래밍방법론\\팀프로젝트\\이미지\\key4.png");

        // 아이템과 문 설정
        items.add(new Item("키1", new Point(50, 300)));
        items.add(new Item("키2", new Point(150, 300)));
        items.add(new Item("키3", new Point(250, 300)));

        doors.add(new Door("키1", new Rectangle(50, 50, 50, 50)));
        doors.add(new Door("키2", new Rectangle(150, 50, 50, 50)));
        doors.add(new Door("키3", new Rectangle(250, 50, 50, 50)));
        doors.add(new Door("키4", new Rectangle(500, 50, 50, 50)));

        // 레이아웃 설정
        setLayout(null);

        // 인벤토리 패널 초기화
        inventoryPanel = new JPanel();
        inventoryPanel.setLayout(null);
        inventoryPanel.setBounds(320, 515, 500, 200); // 위치와 크기 설정

        // 인벤토리 리스트 초기화
        inventoryList = new JList<>(inventoryModel);
        inventoryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        inventoryList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedIndex = inventoryList.getSelectedIndex();
                if (selectedIndex != -1) {
                    selectedItem = inventoryModel.getElementAt(selectedIndex);
                    updateStatusLabel("선택된 아이템: " + selectedItem);
                }
            }
        });

        JScrollPane inventoryScrollPane = new JScrollPane(inventoryList);
        inventoryScrollPane.setBounds(25, 25, 450, 150); // 스크롤 가능한 리스트 위치와 크기 설정
        inventoryPanel.add(inventoryScrollPane);

        // 상태 라벨 초기화
        statusLabel = new JLabel("상태: 대기 중");
        statusLabel.setForeground(Color.BLACK);
        statusLabel.setBounds(0, -35, 1000, 100); // 상태 라벨 위치와 크기 설정
        inventoryPanel.add(statusLabel);

        // 인벤토리 패널 추가
        add(inventoryPanel);

        // 마우스 클릭 이벤트 처리
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point clickPoint = e.getPoint();

                // 아이템 클릭 처리
                for (int i = 0; i < items.size(); i++) {
                    Item item = items.get(i);
                    if (new Rectangle(item.location.x, item.location.y, 40, 40).contains(clickPoint)) {
                        inventoryModel.addElement(item.name); // 인벤토리 리스트에 아이템 추가
                        items.remove(i);  // 아이템을 화면에서 제거
                        updateStatusLabel(item.name + "을(를) 인벤토리에 추가했습니다.");
                        repaint();
                        return;
                    }
                }

                // 문 클릭 처리
                for (Door door : doors) {
                    if (door.location.contains(clickPoint)) {
                        handleDoorInteraction(door);
                        return;
                    }
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 배경색
        ImageIcon monitorBackground = new ImageIcon("C:\\Users\\USER\\Desktop\\3학년 2학기\\프로그래밍방법론\\팀프로젝트\\이미지\\배경.png");
        g.drawImage(monitorBackground.getImage(), 0, 0, getWidth(), getHeight(), this);

        // 문 그리기
        for (Door door : doors) {
            if (door.name.equals("키1")) {
                g.drawImage(door1Image.getImage(), door.location.x, door.location.y, door.location.width, door.location.height, this);
            } else if (door.name.equals("키2")) {
                g.drawImage(door2Image.getImage(), door.location.x, door.location.y, door.location.width, door.location.height, this);
            } else if (door.name.equals("키3")) {
                g.drawImage(door3Image.getImage(), door.location.x, door.location.y, door.location.width, door.location.height, this);
            }
        }

        // 아이템 그리기
        for (Item item : items) {
            if (item.name.equals("키1")) {
                g.drawImage(key1Image.getImage(), item.location.x, item.location.y, 40, 40, this);
            } else if (item.name.equals("키2")) {
                g.drawImage(key2Image.getImage(), item.location.x, item.location.y, 40, 40, this);
            } else if (item.name.equals("키3")) {
                g.drawImage(key3Image.getImage(), item.location.x, item.location.y, 40, 40, this);
            }
        }
    }

    // 문과 아이템 상호작용 처리
 // 문과 아이템 상호작용 처리
    private void handleDoorInteraction(Door door) {
        if (selectedItem == null) {
            updateStatusLabel("아이템을 먼저 선택하세요!");
            return;
        }

        // key1-door1 상호작용: 문이 열렸습니다 팝업
        if (selectedItem.equals("키1") && door.name.equals("키1")) {
            JOptionPane.showMessageDialog(this, "문이 열렸습니다!", "상호작용 성공", JOptionPane.INFORMATION_MESSAGE);
            updateStatusLabel("문이 열렸습니다!");

        // key2-door2 상호작용: 새로운 팝업에 이미지 표시
        } else if (selectedItem.equals("키2") && door.name.equals("키2")) {
        	 // key4를 포함한 팝업창 생성
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            JLabel label = new JLabel("새로운 아이템이 발견되었습니다!", JLabel.CENTER);

            // key4 버튼 생성
            JButton key4Button = new JButton("키4");
            key4Button.setIcon(key4Image);

            key4Button.addActionListener(e -> {
                inventoryModel.addElement("키4"); // key4를 인벤토리에 추가
                updateStatusLabel("키4를 인벤토리에 추가했습니다!");
                JComponent comp = (JComponent) e.getSource();
                Window win = SwingUtilities.getWindowAncestor(comp);
                win.dispose(); // 팝업창 닫기
            });

            panel.add(label, BorderLayout.NORTH);
            panel.add(key4Button, BorderLayout.CENTER);
            JOptionPane.showMessageDialog(this, panel, "키4 발견", JOptionPane.PLAIN_MESSAGE);

            
            
        // key3-door3 상호작용: 비밀번호 입력 팝업
        } else if (selectedItem.equals("키3") && door.name.equals("키3")) {
            String password = JOptionPane.showInputDialog(this, "비밀번호를 입력하세요:");
            if (password != null && password.equals("1234")) { // 예시 비밀번호
                JOptionPane.showMessageDialog(this, "비밀번호가 맞습니다. 문이 열렸습니다!", "상호작용 성공", JOptionPane.INFORMATION_MESSAGE);
                updateStatusLabel("비밀번호가 맞습니다. 문이 열렸습니다!");
            } else {
                JOptionPane.showMessageDialog(this, "비밀번호가 틀렸습니다!", "상호작용 실패", JOptionPane.WARNING_MESSAGE);
                updateStatusLabel("비밀번호가 틀렸습니다!");
            }
        } else {
            // 키와 문이 맞지 않으면 팝업 창 띄우기
            JOptionPane.showMessageDialog(this, "열쇠가 맞지 않습니다!", "상호작용 실패", JOptionPane.WARNING_MESSAGE);
            updateStatusLabel("열쇠가 맞지 않습니다!");
        }
    }


    // 상태 라벨 업데이트
    private void updateStatusLabel(String message) {
        statusLabel.setText("상태: " + message);
    }
}
