package cn.fm.chat.message;

import java.io.Serializable;

import cn.fm.chat.Peer;

/**
 * Created by Administrator on 2019/2/27.
 */

public class ServiceMessage extends Message implements Serializable {
    private char code;
    String arg;
    Peer to;
    //给每一个不同消息定义一个不同的编号，用来区分哪些消息是不需要加密的
    public final static char CODE_CHAN_ADV = 'a';
    public final static char CODE_CHAN_OWNER = 'o';
    public final static char CODE_QUERY_NICK_FREE = 'n';
    public final static char CODE_QUERY_CHAN_FREE = 'c';
    public final static char CODE_NICK_TAKEN = 't';
    public final static char CODE_CHAN_TAKEN = 'h';
    public final static char CODE_HELOJOIN = 'i';
    public final static char CODE_ASK_SHARE = 's';
    public final static char CODE_ASK_FILE = 'f';
    public final static char CODE_JOIN = 'j';
    public final static char CODE_PART = 'p';
    //消息编号定义结束

    public boolean IsBroadcast() {
        return to == null;
    }

    public Peer GetToUser() {
        return to;
    }

    //用来判断哪些消息是不需要加密的
    public boolean DontEncrypt() {
        if (code == CODE_CHAN_ADV || code == CODE_QUERY_NICK_FREE || code == CODE_QUERY_CHAN_FREE || code == CODE_NICK_TAKEN || code == CODE_CHAN_TAKEN)
            return true;
        return false;
    }

    public char GetCode() {
        return code;
    }

    public String GetArg() {
        return arg;
    }

    public ServiceMessage(Peer iFrom, Peer iTo, char iCode, String iArg) {
        this(iFrom, iCode, iArg);
        to = iTo;
    }

    public ServiceMessage(Peer iFrom, char iCode, String iArg) {
        super("", iFrom);
        code = iCode;
        arg = iArg;
    }

}
