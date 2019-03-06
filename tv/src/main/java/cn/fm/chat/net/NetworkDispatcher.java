
package cn.fm.chat.net;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.security.Provider;
import java.security.Security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.sun.crypto.provider.SunJCE;

import cn.fm.chat.Manager;
import cn.fm.chat.message.Message;


public abstract class NetworkDispatcher {
    //定义两静态的常量，用byte类型的字符表示，用不标只数据是否需要加密。
    private static final byte BLOCK_ENCRYPTED = (byte) 0xba;
    private static final byte BLOCK_UNENCRYPTED = (byte) 0xab;


    private boolean keySet = false;
    private Cipher encCipher;
    private Cipher decCipher;
    SecretKeySpec keyIV;
    private PBEParameterSpec paramSpec;
    private SecretKey secretKey;

    //定义一个字节类型的随机串
    private static byte[] salt = {(byte) 0xc9, (byte) 0x53, (byte) 0x67, (byte) 0x9a, (byte) 0x5b, (byte) 0xc8, (byte) 0xae, (byte) 0x18};

    /**
     * return_type : void
     * TODO:设置密码
     *
     * @param iKey，表示字符串类型的明文信息
     */
    public void SetKey(String iKey) {
        if (iKey == null || iKey.length() <= 0) {
            keySet = false;
            return;
        }
        //////////////此处关于数据加密的机制、方法，请参阅本书所讲的Java密码编程技术//////////////////////
        keySet = true;
        try {
            Provider sunJce = new SunJCE();
            Security.addProvider(sunJce);
            paramSpec = new PBEParameterSpec(salt, 20);
            PBEKeySpec keySpec = new PBEKeySpec(iKey.toCharArray());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
            secretKey = keyFactory.generateSecret(keySpec);


            encCipher.init(Cipher.ENCRYPT_MODE, secretKey, paramSpec);
            decCipher.init(Cipher.DECRYPT_MODE, secretKey, paramSpec);

            ///////////////////////////////////////////////////////////////////////////////////////////////
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * return_type : int
     * TODO:设置文件的最大值，用于控制文件发送的最大容量
     *
     * @return
     */
    public int GetMaxFileSize() {
        if (keySet)
            return decCipher.getOutputSize(65000);
        else
            return 65000;
    }

    /**
     * 构造方法，执行数据加密
     */
    protected NetworkDispatcher() {
        try {

            encCipher = Cipher.getInstance("PBEWithMD5AndDES");
            decCipher = Cipher.getInstance("PBEWithMD5AndDES");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    protected abstract void DispatchToAll(byte[] iBuf, int iSize) throws Exception;

    protected void DispatchToAll(byte[] iBuf) throws Exception {
        DispatchToAll(iBuf, iBuf.length);
    }

    /**
     * return_type : void
     * TODO:通过Java的I/O流方法，将消息发送出去
     *
     * @param iMsg
     */
    public void DispatchToAll(Message iMsg) {
        try {
            ByteArrayOutputStream bOut = new ByteArrayOutputStream();
            boolean EncData = (keySet && !iMsg.DontEncrypt());
            //根据数据信息的不同对加密要求的不同，执行不同的写入操作
            if (EncData)
                bOut.write(BLOCK_ENCRYPTED);
            else
                bOut.write(BLOCK_UNENCRYPTED);
            OutputStream underlayingStream = bOut;
            if (EncData)
                underlayingStream = new CipherOutputStream(bOut, encCipher);
            ObjectOutputStream ooStream = new ObjectOutputStream(underlayingStream);
            ooStream.writeObject(iMsg);
            ooStream.close();
            byte[] bb = bOut.toByteArray();
            DispatchToAll(bOut.toByteArray());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * return_type : void
     * TODO:接收数据，
     *
     * @param iBuf，存储数据的缓冲区
     * @param iLen，接收数据的长度
     */
    protected void DataReceived(byte[] iBuf, int iLen) {
        try {
            //接收加密数据时的处理
            if (iBuf[0] == BLOCK_ENCRYPTED) {
                if (!keySet)
                    return;
                //数据解密过程
                byte[] outBuf = new byte[decCipher.getOutputSize(iLen - 1) + 1];
                iLen = decCipher.doFinal(iBuf, 1, iLen - 1, outBuf, 1) + 1;
                iBuf = outBuf;
            } else if (iBuf[0] != BLOCK_UNENCRYPTED)
                return;
            ByteArrayInputStream bIn = new ByteArrayInputStream(iBuf, 1, iLen - 1);
            ObjectInputStream ooStream = new ObjectInputStream(bIn);
            Object msgIn = ooStream.readObject();
            ooStream.close();
            Message recMsg = (Message) msgIn;
            Manager.getInstance().ParseMessage(recMsg);

        } catch (BadPaddingException ex) {

            try {
                decCipher.init(Cipher.DECRYPT_MODE, secretKey, paramSpec);
            } catch (Exception exc) {
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
