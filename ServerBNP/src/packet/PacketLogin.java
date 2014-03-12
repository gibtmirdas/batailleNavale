package packet;

public class PacketLogin extends Packet {
	public PacketLogin(int idSource, byte[] infos) {
		super(infos.length, idSource, infos, packet.PacketHello.class);
		this.encode();
	}
        
        public PacketLogin(int idSource, int dataSize, String uname, String pwd){
            super(dataSize,idSource,null,packet.PacketLogin.class);
            this.setDatas(uname, pwd);
            this.encode();
        }
        
	public PacketLogin(byte[] encodedPacket) {
		super(encodedPacket);
	}
        
        public void setDatas(String uname , String pwd){
            String msg = uname+";"+pwd;
            this.data = msg.getBytes();
        }
        
        public String getUsername(){
            return data.toString().split(";")[0];
        }
        
        public String getPassword(){
            return data.toString().split(";")[1];
        }
        
        public boolean isAccepted(){
            return !getPassword().equals("0") || !getUsername().equals("0");
        }        

}