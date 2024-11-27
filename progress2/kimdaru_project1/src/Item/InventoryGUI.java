package Item;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InventoryGUI {
    private DefaultListModel<String> inventoryModel; // 인벤토리 리스트 모델
    private JList<String> inventoryList; // 인벤토리 리스트
    private JLabel selectedItemLabel; // 선택된 아이템 표시

    public InventoryGUI() {
        // 기본 창 설정
        JFrame frame = new JFrame("inventory");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // 인벤토리 모델 및 리스트
        inventoryModel = new DefaultListModel<>();
        inventoryModel.addElement("key1");
        inventoryModel.addElement("key2");
        inventoryModel.addElement("key3");
        inventoryList = new JList<>(inventoryModel);
        inventoryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        inventoryList.setFont(new Font("Arial", Font.PLAIN, 14));

        // 스크롤 가능한 패널에 추가
        JScrollPane scrollPane = new JScrollPane(inventoryList);

        // 선택된 아이템 라벨
        selectedItemLabel = new JLabel("selcted item: none");
        selectedItemLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        selectedItemLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // 버튼 추가
        JButton selectButton = new JButton("select");
        selectButton.setFont(new Font("Arial", Font.BOLD, 14));

        // 버튼 클릭 이벤트
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = inventoryList.getSelectedValue();
                if (selectedItem != null) {
                    selectedItemLabel.setText("selcted item: " + selectedItem);
                } else {
                    selectedItemLabel.setText("select");
                }
            }
        });

        // 레이아웃에 컴포넌트 추가
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(selectButton, BorderLayout.SOUTH);
        frame.add(selectedItemLabel, BorderLayout.NORTH);

        // 창 표시
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InventoryGUI());
    }
}

