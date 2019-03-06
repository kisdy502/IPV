package cn.fm.chat;

import cn.fm.chat.message.Message;
import cn.fm.chat.net.MulticastDispatcher;
import cn.fm.chat.net.NetworkDispatcher;

/**
 * Created by Administrator on 2019/2/27.
 */

public class Manager {

    //定义两个静态常量，一个是默认的超时时间，另一个是可接收的最大文件数量
    public static final int DefaultOperTimeout = 3000;
    private static final int MAX_RECVFILES_NUM = 50;

    private Peer me;
    private ChanAdvertiserThread advThread;
    private NetworkDispatcher networkDispatcher;

    private Channel curChan;
    private String ReqChan = "";

    public static Manager getInstance() {
        return ManagerHolder.instance;
    }

    public Manager() throws Exception {
        networkDispatcher = new MulticastDispatcher();
        me = Peer.Anonymous; //Anonymous user
    }

    public Peer getMe() {
        return me;
    }

    public NetworkDispatcher getNetworkDispatcher() {
        return networkDispatcher;
    }

    public void SetAndAdvertiseChannel(Channel iChan) {
        curChan = iChan;
        curChan.AddPeer(me);
        ReqChan = "";
        if (advThread != null)
            advThread.stop();
        advThread = new ChanAdvertiserThread(curChan);
        //启动多线程 ，开始进行广播
        advThread.start();
    }

    public void ParseMessage(Message recMsg) {

    }

    private final static class ManagerHolder {
        private static Manager instance;

        static {
            try {
                instance = new Manager();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
