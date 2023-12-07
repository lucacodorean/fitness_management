package frontend;

import backend.Client;
import backend.Employee;
import backend.Router;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

public class HomePage {

  public static JPanel build(
    Router router,
    Employee auth,
    Client[] data,
    String[] columnNames
  ) {
    JPanel panel = new JPanel();
    panel.setBackground(Color.WHITE);
    panel.add(getHero(router));
    panel.add(getTable(router, data, columnNames));
    return panel;
  }

  private static JPanel getHero(Router router) {
    JPanel heroWrapper = new JPanel();
    heroWrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, 95));
    heroWrapper.setBackground(Color.WHITE);
    heroWrapper.setBorder(new EmptyBorder(60, 0, 0, 0));
    JPanel hero = new JPanel(new BorderLayout());
    hero.setBackground(Color.WHITE);
    JLabel heading = new JLabel("Clienti");
    heading.setFont(heading.getFont().deriveFont(Font.BOLD, 36f));
    JButton registerClient = new JButton(
      new ImageIcon("lib/assets/icons/plus.png")
    );

    registerClient.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          // TODO: redirect to create client form
        }
      }
    );

    hero.add(heading, BorderLayout.WEST);
    hero.add(Box.createHorizontalStrut(860));
    hero.add(registerClient, BorderLayout.EAST);

    heroWrapper.add(hero, BorderLayout.WEST);
    return heroWrapper;
  }

  public static JPanel getTable(
    Router router,
    Client[] data,
    String[] columnNames
  ) {
    JPanel wrapper = new JPanel(new BorderLayout());
    JTable table = new JTable(new ClientTableModel(data));
    table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    table.setPreferredScrollableViewportSize(
      new Dimension(1040, table.getPreferredSize().height)
    );
    wrapper.add(new JScrollPane(table));
    return wrapper;
  }
}

class ClientTableModel extends AbstractTableModel {

  private Client[] clients;
  private final String[] columnNames = Client.getColumns();

  public ClientTableModel(Client[] clients) {
    this.clients = clients;
  }

  @Override
  public int getRowCount() {
    return clients.length;
  }

  @Override
  public int getColumnCount() {
    return columnNames.length;
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    Client client = clients[rowIndex];
    switch (columnIndex) {
      case 0:
        return client.getId();
      case 1:
        return client.getLastName();
      case 2:
        return client.getFirstName();
      case 3:
        return client.getEmail();
      case 4:
        return client.isSubscribed() ? "Da" : "Nu";
      case 5:
        return client.getSubscription().getDescription();
      case 6:
        return client.getNextPaymentAt();
      default:
        return null;
    }
  }

  @Override
  public String getColumnName(int column) {
    return columnNames[column];
  }
}
