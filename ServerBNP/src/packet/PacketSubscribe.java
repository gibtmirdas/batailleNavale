package packet;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class PacketSubscribe extends Packet {

    public PacketSubscribe(int idSource, byte[] infos) {
        super(infos.length, idSource, infos, packet.PacketHello.class);
        this.encode();
    }

    public PacketSubscribe(int idSource, int dataSize, String uname, String pwd) {
        super(dataSize, idSource, null, packet.PacketSubscribe.class);
        this.setDatas(uname, pwd);
        this.encode();
    }

    public PacketSubscribe(int idSource, String uname, String pwd) {
        super(uname.length() + pwd.length() + 1, idSource, null, packet.PacketSubscribe.class);
        this.setDatas(uname, pwd);
        this.encode();
    }

    public PacketSubscribe(byte[] encodedPacket) {
        super(encodedPacket);
    }

    private void setDatas(String uname, String pwd) {
        String msg = uname + ";" + pwd;
        this.data = msg.getBytes();
    }

    public String getUsername() {
        try {
            String stmp = new String(data, "UTF-8");
            String s[] = stmp.split(";");
            if (s.length == 2) {
                return s[0];
            }
        } catch (UnsupportedEncodingException e) {
            return "";
        }
        return "";
    }

    public String getPassword(){
        try {
            String stmp = new String(data, "UTF-8");
            String s[] = stmp.split(";");
            if (s.length == 2) {
                return s[1];
            }
        } catch (UnsupportedEncodingException e) {
            return "";
        }
        return "";
    }

    public boolean isAccepted() throws UnsupportedEncodingException {
        return !(getPassword().equals("") && getUsername().equals(""));
    }

}
