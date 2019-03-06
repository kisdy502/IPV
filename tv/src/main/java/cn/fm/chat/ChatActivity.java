package cn.fm.chat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import cn.fm.ipv.R;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener {
    Button buttonSend;
    Button buttonReceive;
    private boolean isRunning = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        buttonSend = findViewById(R.id.send_muilt_broadcast);
        buttonSend.setOnClickListener(this);
        buttonReceive = findViewById(R.id.receive_muilt_broadcast);
        buttonReceive.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.send_muilt_broadcast) {
            new Thread() {
                @Override
                public void run() {
                    send();
                }
            }.start();
        } else if (v.getId() == R.id.receive_muilt_broadcast) {
            new Thread() {
                @Override
                public void run() {
                    receive();
                }
            }.start();
        }
    }

    public void send() {

        try {
            //发送的消息
            byte[] arb = "Hello".getBytes();
            //创建一个IP地址为： 230.0.0.1, 端口为： 7777的多播套接字。
            InetAddress inetAddress = InetAddress.getByName("230.0.0.1");
            DatagramPacket datagramPacket = new DatagramPacket(arb, arb.length, inetAddress, 7777);
            //新建一个数据报，封装多播信息并将其发送出去。
            MulticastSocket multicastSocket = new MulticastSocket();
            multicastSocket.setTimeToLive(255);
            multicastSocket.send(datagramPacket);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void receive() {
        try {
            // Create a multicast datagram socket for receiving IP
            // multicast packets. Join the multicast group at
            // 230.0.0.1, port 7777.
            //加入到IP为230.0.0.1, 端口为  7777 的多播组中，并通过创建一个多播套接字来接收IP多播数据报
            MulticastSocket multicastSocket = new MulticastSocket(7777);
            InetAddress inetAddress = InetAddress.getByName("230.0.0.1");
            multicastSocket.joinGroup(inetAddress);
            // 无限循环的接收来自发送端的消息

            while (isRunning) {
                byte[] arb = new byte[100];
                DatagramPacket datagramPacket = new DatagramPacket(arb, arb.length);
                multicastSocket.receive(datagramPacket);
                //接收并将消息打印输出
                System.out.println(new String(arb));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isRunning = false;
    }
}
