package Item;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class KeyAndDoorGame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("키와 문 게임");

        // 게임 화면 패널
        KeyAndDoorGamePanel gamePanel = new KeyAndDoorGamePanel();
        frame.add(gamePanel);

        // 프레임 설정
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.setVisible(true);
    }

    // Item 클래스 - 아이템의 이름과 위치를 포함
    static class Item {
        String name;
        Point location;

        Item(String name, Point location) {
            this.name = name;
            this.location = location;
        }
    }

    // Door 클래스 - 문 이름과 위치를 포함
    static class Door {
        String name;
        Rectangle location;

        Door(String name, Rectangle location) {
            this.name = name;
            this.location = location;
        }
    }

    // 게임 화면 클래스
static class KeyAndDoorGamePanel extends JPanel {
        private ArrayList<Item> items;  // 게임 아이템
        private ArrayList<Door> doors;  // 게임 문
        private DefaultListModel<String> inventoryModel; // 인벤토리 모델
        private JList<String> inventoryList; // 인벤토리 리스트
        private JLabel statusLabel;  // 상태 라벨
        private String selectedItem = null;  // 선택된 아이템
        
        public KeyAndDoorGamePanel() {
            items = new ArrayList<>();
            doors = new ArrayList<>();
            inventoryModel = new DefaultListModel<>();

            // 아이템과 문 설정
            items.add(new Item("키1", new Point(50, 300)));
            items.add(new Item("키2", new Point(150, 300)));
            items.add(new Item("키3", new Point(250, 300)));

            doors.add(new Door("키1", new Rectangle(50, 50, 50, 50)));
            doors.add(new Door("키2", new Rectangle(150, 50, 50, 50)));
            doors.add(new Door("키3", new Rectangle(250, 50, 50, 50)));

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

            // 상태 라벨 설정
            statusLabel = new JLabel("상태: 대기 중", SwingConstants.CENTER);

            // 레이아웃 설정
            setLayout(new BorderLayout());
            add(new JScrollPane(inventoryList), BorderLayout.EAST);  // 인벤토리 리스트
            add(statusLabel, BorderLayout.SOUTH);  // 상태 라벨
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // 배경색
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(1200, 800, 1200, 800);

            // 문 그리기
            g.setColor(Color.RED);
            for (Door door : doors) {
                g.fillRect(door.location.x, door.location.y, door.location.width, door.location.height);
            }

            // 아이템 그리기
            g.setColor(Color.YELLOW);
            for (Item item : items) {
                g.fillOval(item.location.x, item.location.y, 40, 40);
            }
        }

        // 문과 아이템 상호작용 처리
        private void handleDoorInteraction(Door door) {
            if (selectedItem == null) {
                updateStatusLabel("아이템을 먼저 선택하세요!");
                return;
            }

            if (selectedItem.equals(door.name)) {
                updateStatusLabel(door.name + " 문이 열렸습니다!");
            } else {
                updateStatusLabel("열쇠가 맞지 않습니다!");
            }
        }

        // 상태 라벨 업데이트
        private void updateStatusLabel(String message) {
            statusLabel.setText("상태: " + message);
        }
    }
}

