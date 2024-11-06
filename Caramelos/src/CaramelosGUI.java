import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CaramelosGUI extends JFrame {

    private JTextField numSacolasField;
    private JTextField[] sacolasFields;
    private JTextArea resultArea;
    private JPanel mainPanel;
    private JPanel initialPanel;
    private JPanel sacolasPanel;

    public CaramelosGUI() {
        setTitle("Distribuição de Caramelos");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setIconImage(new ImageIcon("caramelo_icon.png").getImage());

        mainPanel = new JPanel(new CardLayout());
        mainPanel.setBackground(new Color(255, 228, 196));

        initialPanel = new JPanel(new GridBagLayout());
        initialPanel.setBackground(new Color(255, 228, 196));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Distribuição de Caramelos");
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        initialPanel.add(titleLabel, gbc);

        JLabel numSacolasLabel = new JLabel("Número de Sacolas:");
        numSacolasLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        initialPanel.add(numSacolasLabel, gbc);

        numSacolasField = new JTextField(10);
        numSacolasField.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        initialPanel.add(numSacolasField, gbc);

        JButton submitButton = new JButton("Enviar");
        submitButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        submitButton.setBackground(new Color(255, 182, 193));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        initialPanel.add(submitButton, gbc);

        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(resultArea);
        gbc.gridy = 3;
        initialPanel.add(scrollPane, gbc);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numSacolas = Integer.parseInt(numSacolasField.getText());
                createSacolasFields(numSacolas);
            }
        });

        mainPanel.add(initialPanel, "InitialPanel");

        add(mainPanel);
        setVisible(true);
    }

    private void createSacolasFields(int numSacolas) {
        sacolasPanel = new JPanel(new GridBagLayout());
        sacolasPanel.setBackground(new Color(255, 228, 196));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        sacolasFields = new JTextField[numSacolas];
        for (int i = 0; i < numSacolas; i++) {
            JLabel label = new JLabel("Sacola " + (i + 1) + ":");
            label.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.anchor = GridBagConstraints.EAST;
            sacolasPanel.add(label, gbc);

            sacolasFields[i] = new JTextField(10);
            sacolasFields[i].setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
            gbc.gridx = 1;
            gbc.anchor = GridBagConstraints.WEST;
            sacolasPanel.add(sacolasFields[i], gbc);
        }

        JButton calculateButton = new JButton("Calcular");
        calculateButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        calculateButton.setBackground(new Color(144, 238, 144));
        gbc.gridx = 0;
        gbc.gridy = numSacolas;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        sacolasPanel.add(calculateButton, gbc);

        JButton backButton = new JButton("Voltar");
        backButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        backButton.setBackground(new Color(255, 182, 193));
        gbc.gridy = numSacolas + 1;
        sacolasPanel.add(backButton, gbc);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] sacolas = new int[numSacolas];
                for (int i = 0; i < numSacolas; i++) {
                    sacolas[i] = Integer.parseInt(sacolasFields[i].getText());
                }

                List<Integer> result = findEqualDistribution(sacolas);

                if (result == null) {
                    appendResult("-1");
                } else {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < result.size(); i++) {
                        sb.append(result.get(i));
                        if (i < result.size() - 1) {
                            sb.append(" ");
                        }
                    }
                    appendResult(sb.toString());
                }

                CardLayout cl = (CardLayout) mainPanel.getLayout();
                cl.show(mainPanel, "InitialPanel");
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) mainPanel.getLayout();
                cl.show(mainPanel, "InitialPanel");
            }
        });

        JScrollPane scrollPane = new JScrollPane(sacolasPanel);
        scrollPane.setPreferredSize(new Dimension(500, 300));

        mainPanel.add(scrollPane, "SacolasPanel");
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, "SacolasPanel");
    }

    private void appendResult(String result) {
        resultArea.append(result + "\n");
    }

    public static List<Integer> findEqualDistribution(int[] sacolas) {
        int n = sacolas.length;
        int soma = 0;
        for (int i = 0; i < n; i++) {
            soma += sacolas[i];
        }

        if (soma % 2 == 1) {
            return null;
        }

        soma /= 2;

        boolean[][] pd = new boolean[n + 1][soma + 1];
        for (int j = 0; j <= soma; j++) {
            pd[0][j] = false;
        }
        pd[0][0] = true;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= soma; j++) {
                pd[i][j] = pd[i - 1][j];
                if (sacolas[i - 1] <= j) {
                    pd[i][j] = pd[i][j] || pd[i - 1][j - sacolas[i - 1]];
                }
            }
        }

        if (!pd[n][soma]) {
            return null;
        }

        List<Integer> alice = new ArrayList<>();
        List<Integer> bob = new ArrayList<>();
        int s = soma;

        for (int i = n; i > 0; i--) {
            if (s >= sacolas[i - 1] && pd[i - 1][s - sacolas[i - 1]]) {
                alice.add(sacolas[i - 1]);
                s -= sacolas[i - 1];
            } else {
                bob.add(sacolas[i - 1]);
            }
        }

        List<Integer> result = new ArrayList<>();
        int s1 = 0, s2 = 0, prox = 0;

        for (int i = 0; i < n; i++) {
            if (s1 <= s2 && !alice.isEmpty()) {
                prox = alice.remove(alice.size() - 1);
                s1 += prox;
            } else if (!bob.isEmpty()) {
                prox = bob.remove(bob.size() - 1);
                s2 += prox;
            }
            result.add(prox);
        }

        return result;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CaramelosGUI();
            }
        });
    }
}