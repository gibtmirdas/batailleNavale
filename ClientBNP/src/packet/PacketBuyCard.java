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
public class PacketBuyCard extends Packet{

    public PacketBuyCard(byte[] encodedPacket) {
        super(encodedPacket);
    }
    
    public PacketBuyCard(int idSource, int cardID){
        super(1,idSource, null, packet.PacketBuyCard.class);
        
    }
    
    public void setDatas(int cardID){
        byte[] b = new byte[1];
        b[0] = (byte) (cardID & 0xFF);
        this.data = b;
    }
    
    public int getCardID(){
        return this.data[0];
    }
}
