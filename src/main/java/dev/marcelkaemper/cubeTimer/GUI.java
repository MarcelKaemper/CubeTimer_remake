package dev.marcelkaemper.cubeTimer;

import com.fazecast.jSerialComm.SerialPort;
import net.miginfocom.swing.MigLayout;

import javax.sound.sampled.Port;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI {

    private JComboBox<String> portList;
    private JTextField status = new JTextField();


    public GUI() {
        JFrame frame = new JFrame("Cubetimer");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);

        JPanel panel = new JPanel(new MigLayout());
        frame.add(panel);

        portList = new JComboBox<String>();
        fillPortlist();
        panel.add(portList, "wrap");

        portList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println(actionEvent.getActionCommand());
                System.out.println(portList.getSelectedItem());
                PortListener portListener = new PortListener(portList.getSelectedItem().toString());
            }
        });


        status = new JTextField();

        panel.add(status, "span, grow");

        frame.setVisible(true);
    }

    private void fillPortlist() {
        for(SerialPort sp : PortListener.getPorts()) {
            portList.addItem(sp.getSystemPortName());
        }
    }

    public void setStatusText(String text) {
        this.status.setText(text);
    }
}
