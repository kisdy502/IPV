package cn.fm.chat;

import cn.fm.chat.message.ServiceMessage;

/**
 * Created by Administrator on 2019/2/27.
 */

public class ChanAdvertiserThread extends Thread {
    private Channel channel;

    public ChanAdvertiserThread(Channel iChan) {
        channel = iChan;
    }

    public void run() {
        final Manager manager = Manager.getInstance();
        for (; ; ) {
            try {
                //要广告的消息
                ServiceMessage advMsg = new ServiceMessage(manager.getMe(), ServiceMessage
                        .CODE_CHAN_ADV, channel.GetName());
                //向每个Peer广播
                manager.getNetworkDispatcher().DispatchToAll(advMsg);
                //每隔2秒广播一次
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
