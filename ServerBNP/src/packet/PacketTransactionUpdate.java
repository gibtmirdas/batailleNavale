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
public class PacketTransactionUpdate extends Packet{

    public PacketTransactionUpdate(byte[] encodedPacket) {
        super(encodedPacket);
    }
    
    public PacketTransactionUpdate(int idSource, int cardID, int respons) {
        super(2,idSource,null, packet.PacketTransactionUpdate.class);
        
    }
    public void setDatas(int cardID, int respons){
        byte[] b = new byte[2];
        b[0] = (byte)(cardID & 0xFF);
        b[1] = (byte)(respons & 0xFF);
        this.data = b;
    }
    
    public boolean isAccepted(){
        return data[1] == 1;
    }
    
    public int getCardID(){
        return data[0];
    }
}
