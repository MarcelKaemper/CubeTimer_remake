package dev.marcelkaemper.cubeTimer;


import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class PortListener {

    private TimeMeasurer tm = new TimeMeasurer();

    public PortListener(String port) {
        SerialPort comPort = SerialPort.getCommPort(port);
        comPort.setBaudRate(9600);
        comPort.openPort();

        comPort.addDataListener(new SerialPortDataListener() {
            @Override
            public int getListeningEvents() {
                return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
            }

            @Override
            public void serialEvent(SerialPortEvent event) {
                System.out.println("event");
                if(event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE) {
                    return;
                }
                byte[] data = new byte[comPort.bytesAvailable()];
                int numRead = comPort.readBytes(data, data.length);

                String command = new String(data);
                command = command.replace("\n", "").replace("\r", "");

                System.out.println(command);

                if(command.equals("tmr_start")) {
                    tm.startTimer();
                }else if(command.equals("tmr_stop")) {
                    tm.endTimer();
                }
            }
        });
    }


    public static SerialPort[] getPorts() {
        return SerialPort.getCommPorts();
    }
}
