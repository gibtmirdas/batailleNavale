package packet;

import java.io.UnsupportedEncodingException;

public class PacketLogin extends Packet {

    public PacketLogin(int idSource, byte[] infos) {
        super(infos.length, idSource, infos, packet.PacketHello.class);
        this.encode();
    }

    public PacketLogin(int idSource, int dataSize, String uname, String pwd) {
        super(dataSize, idSource, null, packet.PacketLogin.class);
        this.setDatas(uname, pwd);
        this.encode();
    }

    public PacketLogin(int idSource, String uname, String pwd) {
        super(uname.length() + pwd.length() + 1, idSource, null, packet.PacketLogin.class);
        this.setDatas(uname, pwd);
        this.encode();
    }

    public PacketLogin(byte[] encodedPacket) {
        super(encodedPacket);
    }

    private void setDatas(String uname, String pwd) {
        String msg = uname + ";" + pwd;
        this.data = msg.getBytes();
    }


    public String getUsername() throws UnsupportedEncodingException{
            String stmp = new String(data, "UTF-8");
            String s[] = stmp.split(";");
            if (s.length == 2) {
                return s[0];
            }

        return "";
    }

    public String getPassword() throws UnsupportedEncodingException{
        
            String stmp = new String(data, "UTF-8");
            String s[] = stmp.split(";");
            if (s.length == 2) {
                return s[1];
            }
        return "";
    }

    public boolean isAccepted() throws UnsupportedEncodingException{
        return !(getPassword().equals("") && getUsername().equals(""));
    }

}
