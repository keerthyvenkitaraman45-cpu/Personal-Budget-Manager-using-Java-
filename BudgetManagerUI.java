import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BudgetManagerUI extends JFrame implements ActionListener {

    JTextField budgetField, amountField;
    JComboBox<String> categoryBox;
    JTextArea summaryArea;

    double housingTotal = 0, transportTotal = 0, foodTotal = 0,
           utilitiesTotal = 0, healthTotal = 0, debtSavingTotal = 0, othersTotal = 0;

    double budgetLimit = 0;

    JButton setBudgetBtn, addExpenseBtn, showSummaryBtn, resetBtn;

    public BudgetManagerUI() {

        setTitle("Personal Budget Manager");
        setSize(420,420);
        setLayout(new FlowLayout());

        add(new JLabel("Enter Budget Limit:"));
        budgetField = new JTextField(10);
        add(budgetField);

        setBudgetBtn = new JButton("Set Budget");
        add(setBudgetBtn);

        add(new JLabel("Category:"));

        String categories[] = {
                "Housing",
                "Transportation",
                "Food",
                "Utilities",
                "Health & Insurance",
                "Debt & Saving",
                "Others"
        };

        categoryBox = new JComboBox<>(categories);
        add(categoryBox);

        add(new JLabel("Amount:"));
        amountField = new JTextField(10);
        add(amountField);

        addExpenseBtn = new JButton("Add Expense");
        add(addExpenseBtn);

        showSummaryBtn = new JButton("Show Summary");
        add(showSummaryBtn);

        resetBtn = new JButton("Reset");
        add(resetBtn);

        summaryArea = new JTextArea(12,30);
        summaryArea.setEditable(false);
        add(summaryArea);

        setBudgetBtn.addActionListener(this);
        addExpenseBtn.addActionListener(this);
        showSummaryBtn.addActionListener(this);
        resetBtn.addActionListener(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == setBudgetBtn) {
            try {
                budgetLimit = Double.parseDouble(budgetField.getText());
                summaryArea.setText("Budget set to: " + budgetLimit);
            } catch(Exception ex) {
                JOptionPane.showMessageDialog(this,"Enter a valid budget!");
            }
        }

        if(e.getSource() == addExpenseBtn) {

            try {

                double amount = Double.parseDouble(amountField.getText());
                String category = (String) categoryBox.getSelectedItem();

                if(category.equals("Housing"))
                    housingTotal += amount;

                else if(category.equals("Transportation"))
                    transportTotal += amount;

                else if(category.equals("Food"))
                    foodTotal += amount;

                else if(category.equals("Utilities"))
                    utilitiesTotal += amount;

                else if(category.equals("Health & Insurance"))
                    healthTotal += amount;

                else if(category.equals("Debt & Saving"))
                    debtSavingTotal += amount;

                else
                    othersTotal += amount;

                summaryArea.setText("Expense added successfully.");
                amountField.setText("");

            } catch(Exception ex) {
                JOptionPane.showMessageDialog(this,"Enter a valid amount!");
            }
        }

        if(e.getSource() == showSummaryBtn) {

            double total = housingTotal + transportTotal + foodTotal +
                           utilitiesTotal + healthTotal + debtSavingTotal + othersTotal;

            String result = "===== BUDGET SUMMARY =====\n\n";

            result += "Housing: " + housingTotal +
                    "\nTransportation: " + transportTotal +
                    "\nFood: " + foodTotal +
                    "\nUtilities: " + utilitiesTotal +
                    "\nHealth & Insurance: " + healthTotal +
                    "\nDebt & Saving: " + debtSavingTotal +
                    "\nOthers: " + othersTotal +
                    "\n\nTotal Expense: " + total;

            if(total > budgetLimit)
                result += "\n\nWarning! Budget exceeded by: " + (total - budgetLimit);
            else
                result += "\n\nSavings: " + (budgetLimit - total);

            summaryArea.setText(result);
        }

        if(e.getSource() == resetBtn) {

            housingTotal = transportTotal = foodTotal =
            utilitiesTotal = healthTotal = debtSavingTotal = othersTotal = 0;

            budgetLimit = 0;

            budgetField.setText("");
            amountField.setText("");
            summaryArea.setText("");

            JOptionPane.showMessageDialog(this,"All data reset!");
        }
    }

    public static void main(String[] args) {
        new BudgetManagerUI();
    }
}