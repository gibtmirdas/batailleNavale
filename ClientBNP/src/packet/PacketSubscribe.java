/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packet;

/**
 *
 * @author antho
 */
public class PacketSubscribe extends PacketLogin {

    public PacketSubscribe(int idSource, byte[] infos) {
        super(idSource, infos);
    }

    public PacketSubscribe(int idSource, int dataSize, String uname, String pwd) {
        super(idSource, dataSize, uname, pwd);
    }

    public PacketSubscribe(int idSource, String uname, String pwd) {
        super(idSource, uname, pwd);
    }

    public PacketSubscribe(byte[] encodedPacket) {
        super(encodedPacket);
    }

}
