import java.io.IOException;
import java.net.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class UDPChat {
    static int serverPort = 1024;
    static boolean isRecv = true;
    static boolean isOffline = true;
    static boolean OfflineMessageStored = true;
    //key is the client's port, value is the server's name + server's IP + client's IP + client's port
    static HashMap<Integer, String> ClientInfo = new HashMap<>();
    //key is the client's name, value is the message for him
    static HashMap<String, String> ClientMessage = new HashMap<>();
    static HashMap<String, Integer> ClientNameToPort = new HashMap<>();
    static HashMap<Integer, String> PortToClientName = new HashMap<>();
    static volatile int flag1 = 0;
    static volatile int flag2 = 0;
    static volatile int flag3 = 0;


    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        String mode = sc.nextLine();
        String[] modesplit = mode.split(" ");
        if(modesplit.length == 3) {
            runServer();
        } else if(modesplit.length == 6) {
            String clientName = modesplit[2];
            String serverIp = modesplit[3];
            String clientPort = modesplit[5];
            System.out.println(clientName + " " + clientPort);
            ClientNameToPort.put(clientName, Integer.valueOf(clientPort));
            PortToClientName.put(Integer.valueOf(clientPort), clientName);
            runClient(clientName, serverIp, clientPort);
        }
    }

    public static void runServer() throws IOException {
        DatagramSocket socket = new DatagramSocket(serverPort);
        while(true) {
            byte[] data = new byte[1024];
            DatagramPacket packet = new DatagramPacket(data, data.length);
            socket.receive(packet);
            String info = new String(data, 0, packet.getLength());
            System.out.println("get the package, " + info);
            String [] infosplit = info.split(" ");
            if(infosplit.length == 4) {
                int port = packet.getPort();
                if(flag1 == 0) {
                    flag1 = port;
                    ClientInfo.put(Integer.valueOf(infosplit[3]), info);
                } else if(flag2 == 0) {
                    flag2 = port;
                    ClientInfo.put(Integer.valueOf(infosplit[3]), info);
                } else {
                    flag3 = port;
                    ClientInfo.put(Integer.valueOf(infosplit[3]), info);
                }
                //inform the server of the other servers
                StringBuilder sb = new StringBuilder();
                sb.append("[Welcome, You are registered.]");
                sb.append("&");
                if(flag1 != 0) {
                    sb.append(ClientInfo.get(flag1));
                    sb.append("&");
                } else {
                    //client1 is offline
                    sb.append("#");
                    sb.append("&");
                }
                if(flag2 != 0) {
                    sb.append(ClientInfo.get(flag2));
                    sb.append("&");
                } else {
                    //client2 is offline
                    sb.append("#");
                    sb.append("&");
                }
                if(flag3 != 0) {
                    sb.append(ClientInfo.get(flag3));
                } else {
                    //client3 is offline
                    sb.append("#");
                }
                byte[] data2 = sb.toString().getBytes();
                if(flag1 != 0) {
                    String [] cif = ClientInfo.get(flag1).split(" ");
                    InetAddress address = InetAddress.getByName(cif[2]);
                    DatagramPacket packet2 = new DatagramPacket(data2, data2.length, address, flag1);
                    socket.send(packet2);
                }
                if(flag2 != 0 ) {
                    String [] cif = ClientInfo.get(flag2).split(" ");
                    InetAddress address = InetAddress.getByName(cif[2]);
                    DatagramPacket packet2 = new DatagramPacket(data2, data2.length, address, flag2);
                    socket.send(packet2);
                }
                if(flag3 != 0) {
                    String [] cif = ClientInfo.get(flag3).split(" ");
                    InetAddress address = InetAddress.getByName(cif[2]);
                    DatagramPacket packet2 = new DatagramPacket(data2, data2.length, address, flag3);
                    socket.send(packet2);
                }
                //if there are offline message, just send if to the client
                String nickname = infosplit[0];
                if(ClientMessage.get(nickname) != null) {
                    //                String toStore = senderName + " : <" + infosplit[2] + "> " + infosplit[4];
                    System.out.println(">>>" + ClientMessage.get(nickname));
                    byte [] data10 = (">>>" + ClientMessage.get(nickname)).getBytes();
                    //        String output = nickName + " " + serverIp + " " + clinetIP + " " + clientPort;
                    InetAddress clientIp = InetAddress.getByName(infosplit[2]);
                    int port2 = Integer.valueOf(infosplit[3]);
                    DatagramPacket datagramPacket10 = new DatagramPacket(data10, data10.length, clientIp, port2);
                    socket.send(datagramPacket10);
                }
            } else if(infosplit.length == 3) {
                String offname = infosplit[1];
                Integer offPort = Integer.valueOf(infosplit[2]);
                System.out.println("offname is " + offname + "offport is" + offPort + "in map" + ClientNameToPort.get(offname)) ;
                //inform the server of the other servers
                String offlineMessage = "[You are Offline. Bye.]";
                byte[] data6 = offlineMessage.getBytes();
                if(flag1 == offPort) {
                    flag1 = 0;
                    InetAddress inetAddress = InetAddress.getByName(ClientInfo.get(offPort).split(" ")[2]);
                    DatagramPacket packet6 = new DatagramPacket(data6, data6.length, inetAddress, Integer.valueOf(ClientInfo.get(offPort).split(" ")[3]));
                    socket.send(packet6);
                } else if(flag2 == offPort) {
                    flag2 = 0;
                    InetAddress inetAddress = InetAddress.getByName(ClientInfo.get(offPort).split(" ")[2]);
                    DatagramPacket packet6 = new DatagramPacket(data6, data6.length, inetAddress, Integer.valueOf(ClientInfo.get(offPort).split(" ")[3]));
                    socket.send(packet6);
                } else {
                    flag3 = 0;
                    InetAddress inetAddress = InetAddress.getByName(ClientInfo.get(offPort).split(" ")[2]);
                    DatagramPacket packet6 = new DatagramPacket(data6, data6.length, inetAddress, Integer.valueOf(ClientInfo.get(offPort).split(" ")[3]));
                    socket.send(packet6);
                }
                byte[] data7 = ("[offLineInfo]" + " " + offname + " " + offPort).getBytes();
                if(flag1 != 0) {
                    InetAddress inetAddress = InetAddress.getByName(ClientInfo.get(flag1).split(" ")[2]);
                    int port = Integer.valueOf(ClientInfo.get(flag1).split(" ")[3]);
                    DatagramPacket packet7 = new DatagramPacket(data7, data7.length, inetAddress, port);
                    socket.send(packet7);
                }
                if(flag2 != 0) {
                    InetAddress inetAddress = InetAddress.getByName(ClientInfo.get(flag2).split(" ")[2]);
                    int port = Integer.valueOf(ClientInfo.get(flag2).split(" ")[3]);
                    DatagramPacket packet7 = new DatagramPacket(data7, data7.length, inetAddress, port);
                    socket.send(packet7);
                }
                if(flag3 != 0) {
                    InetAddress inetAddress = InetAddress.getByName(ClientInfo.get(flag3).split(" ")[2]);
                    int port = Integer.valueOf(ClientInfo.get(flag3).split(" ")[3]);
                    DatagramPacket packet7 = new DatagramPacket(data7, data7.length, inetAddress, port);
                    socket.send(packet7);
                }

            } else if(infosplit.length > 5) {
                //String offlineMessage = receiverPort + " " + nickName + " " + message + " " + name + " " + new Date().toString();
                String senderName = infosplit[1];
                String receiverName = infosplit[3];
                //check if the receiver is still online
                if(flag1 != 0 && flag1 == Integer.valueOf(infosplit[0])) {
                    byte [] data11 = ("[ClientExists!!]" + " " + ClientInfo.get(flag1)).getBytes();
                    DatagramPacket datagramPacket11 = new DatagramPacket(data11, data11.length, packet.getAddress(), packet.getPort());
                    socket.send(datagramPacket11);
                }
                if(flag2 != 0 && flag2 == Integer.valueOf(infosplit[0])) {
                    byte [] data11 = ("[Client " + receiverName + " exists!!]" + ClientInfo.get(flag2)).getBytes();
                    DatagramPacket datagramPacket11 = new DatagramPacket(data11, data11.length, packet.getAddress(), packet.getPort());
                    socket.send(datagramPacket11);
                }
                if(flag3 != 0 && flag3 == Integer.valueOf(infosplit[0])) {
                    byte [] data11 = ("[Client " + receiverName + " exists!!]" + ClientInfo.get(flag3)).getBytes();
                    DatagramPacket datagramPacket11 = new DatagramPacket(data11, data11.length, packet.getAddress(), packet.getPort());
                    socket.send(datagramPacket11);
                }
                String toStore = senderName + " : <" + infosplit[7] + "> " + infosplit[2];
                if(ClientMessage.get(receiverName) != null) {
                    String sb = ClientMessage.get(receiverName) + " " +
                            toStore;
                    ClientMessage.put(receiverName, sb);
                } else {
                    ClientMessage.put(receiverName, toStore);
                }
                byte [] data9 = "[ Messages received by the server and saved]".getBytes();
                DatagramPacket datagramPacket9 = new DatagramPacket(data9, data9.length, packet.getAddress(), packet.getPort());
                socket.send(datagramPacket9);
            }

        }
    }

    public static void runClient(String nickName, String serverIp, String clientPort) throws IOException, InterruptedException {
        System.out.println("initialized registion");
        DatagramSocket socket = new DatagramSocket(Integer.valueOf(clientPort));
        //register
        String clinetIP = InetAddress.getLocalHost().toString();
        clinetIP = clinetIP.substring(clinetIP.lastIndexOf("/") + 1 );
        String output = nickName + " " + serverIp + " " + clinetIP + " " + clientPort;
        System.out.println(output);
        byte[] data = output.getBytes();
        InetAddress address = InetAddress.getByName(serverIp);
        DatagramPacket packet = new DatagramPacket(data, data.length, address, serverPort);
        socket.send(packet);

        new Thread(() -> {
            while(true) {
                byte[] data4 = new byte[1024];
                DatagramPacket packet4 = new DatagramPacket(data4, data4.length);
                try {
                    socket.receive(packet4);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //maybe the message to the receiver, or the ACK to the sender, or one servers make the regeistion
                String whichone = new String(data4, 0, packet4.getLength());
                if(whichone.charAt(0) == '[' && whichone.charAt(1) == 'W') {
                    //register
                    String [] replySplit = whichone.split("&");
                    if(replySplit.length != 4) {
                        continue;
                    }
                    System.out.println(replySplit[0]);
                    if(replySplit[1].equals("#")) {
                        flag1 = 0;
                    } else {
                        String [] clientInfo1 = replySplit[1].split(" ");
                        flag1 = Integer.valueOf(clientInfo1[3]);
                        ClientInfo.put(flag1, replySplit[1]);
                        ClientNameToPort.put(clientInfo1[0], flag1);
                        PortToClientName.put(flag1, clientInfo1[0]);
                    }
                    if(replySplit[2].equals("#")) {
                        flag2 = 0;
                    } else {
                        String [] clientInfo2 = replySplit[2].split(" ");
                        flag2 = Integer.valueOf(clientInfo2[3]);
                        ClientInfo.put(flag2, replySplit[2]);
                        ClientNameToPort.put(clientInfo2[0], flag2);
                        PortToClientName.put(flag2, clientInfo2[0]);
                    }
                    if(replySplit[3].equals("#")) {
                        flag3 = 0;
                    } else {
                        String [] clientInfo3 = replySplit[3].split(" ");
                        flag3 = Integer.valueOf(clientInfo3[3]);
                        ClientInfo.put(flag3, replySplit[3]);
                        ClientNameToPort.put(clientInfo3[0], flag3);
                        PortToClientName.put(flag3, clientInfo3[0]);
                    }
                    System.out.println(" [Client table updated.]");
                } else if(whichone.charAt(0) == ' ') {
                    //just the ACK from the receiver
                    isRecv = true;
                    System.out.println(whichone);
                } else if(whichone.charAt(0) == '[' && whichone.charAt(1) == 'Y') {
                    //you are offline!
                    isOffline = true;
                    System.out.println(whichone);
                } else if(whichone.charAt(0) == '[' && whichone.charAt(1) == ' ') {
                    //the offline message has been stored
                    OfflineMessageStored = true;
                    System.out.println(whichone);

                }  else if (whichone.charAt(0) == '[' && whichone.charAt(1) == 'C') {
                    //byte [] data11 = ("[ClientExists!!]" + " " + ClientInfo1.get(flag1)).getBytes();
                    //key is the client's port, value is the server's name + server's IP + client's IP + client's port
                    String [] whichoneSplit = whichone.split(" ");
                    int clientPort2 = Integer.valueOf(whichoneSplit[4]);
                    String toStore2 = whichoneSplit[1] + whichoneSplit[2] + whichoneSplit[3] + whichoneSplit[4];
                    ClientInfo.put(clientPort2, toStore2);
                } else if(whichone.charAt(0) == '[' && whichone.charAt(1) == 'o') {
                    String [] whichoneSplit = whichone.split(" ");
                    //                byte[] data7 = ("[offLineinfo]" + " " + offname + " " + offPort).getBytes();
                    int offport = Integer.valueOf(whichoneSplit[2]);
                    String offname = whichoneSplit[1];
                    System.out.println(offname + " has gone!");
                    if(flag1 == offport) {
                        flag1 = 0;
                        System.out.println();
                    } else if(flag2 == offport) {
                        flag2 = 0;
                    } else if(flag3 == offport) {
                        flag3 = 0;
                    }
                } else if(whichone.charAt(3) == 'c' && whichone.charAt(4) == 'l') {
                    //offline message
                    System.out.println("<<< You Have Messages!");
                    System.out.println(whichone);
                }
                else {
                    //receive the message from other client
                    isRecv = true;
                    System.out.println(whichone);
                    String chatmessage = new String(data4, 0, packet4.getLength());
                    String [] charmessageSplit = chatmessage.split(" ");
                    byte[] data5 = (" [Message received by <" + nickName + ">.]").getBytes();
                    DatagramPacket packet5 = new DatagramPacket(data5, data5.length, packet4.getAddress(), ClientNameToPort.get(charmessageSplit[0]));
                    try {
                        socket.send(packet5);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        while(true) {
            Scanner sc = new Scanner(System.in);
            String order = sc.nextLine();
            String [] orderSplit = order.split(" ");
            if(orderSplit[0].equals("send")) {
                String name = orderSplit[1];
                String message = orderSplit[2];
                String sb2 = nickName +
                        " : " +
                        "<" +
                        new Date().toString() +
                        "> " +
                        message;
                byte [] data3 = sb2.getBytes();
                isRecv = false;
                System.out.println("flag1: " + flag1 + "flag2: " + flag2 + "flag3: " + flag3 + "name1: " + PortToClientName.get(flag1) + "name2: " + PortToClientName.get(flag2) + "name3: " + PortToClientName.get(flag3));
                if(flag1 != 0 && name.equals(PortToClientName.get(flag1))) {
                    String [] clinet1Info = ClientInfo.get(flag1).split(" ");
                    InetAddress toIp = InetAddress.getByName(clinet1Info[2]);
                    DatagramPacket datagramPacket3 = new DatagramPacket(data3, data3.length, toIp, Integer.valueOf(clinet1Info[3]));
                    socket.send(datagramPacket3);
                }
                if(flag2 != 0 && name.equals(PortToClientName.get(flag2))) {
                    String [] clinet2Info = ClientInfo.get(flag2).split(" ");
                    InetAddress toIp = InetAddress.getByName(clinet2Info[2]);
                    DatagramPacket datagramPacket3 = new DatagramPacket(data3, data3.length, toIp, Integer.valueOf(clinet2Info[3]));
                    socket.send(datagramPacket3);
                }
                if(flag3 != 0 && name.equals(PortToClientName.get(flag3))) {
                    String [] clinet3Info = ClientInfo.get(flag3).split(" ");
                    InetAddress toIp = InetAddress.getByName(clinet3Info[2]);
                    DatagramPacket datagramPacket3 = new DatagramPacket(data3, data3.length, toIp, Integer.valueOf(clinet3Info[3]));
                    socket.send(datagramPacket3);
                }
                Thread.sleep(500);
                //offline message: sender's name, time, receiver' name, message
                int tryTime = 5;
                if(!isRecv) {
                    OfflineMessageStored = false;
                    while(!OfflineMessageStored && --tryTime > 0) {
                        int receiverPort = ClientNameToPort.get(name);
                        String offlineMessage = receiverPort + " " + nickName + " " + message + " " + name + " " + new Date().toString();
                        byte [] data8 = offlineMessage.getBytes();
                        InetAddress serverIpInet = InetAddress.getByName(serverIp);
                        DatagramPacket datagramPacket8 = new DatagramPacket(data8, data8.length, serverIpInet, serverPort);
                        socket.send(datagramPacket8);
                        System.out.println("try to save the message for the " + (5-tryTime) + "time");
                    }
                    if(!OfflineMessageStored) {
                        System.out.println("something wrong, the message will be dropped");
                        break;
                    }
                }
            } else if(orderSplit[0].equals("dereg")) {
                isOffline = false;
                int count = 5;
                while(!isOffline && --count > 0) {
                    String name = orderSplit[1];
                    Integer port = ClientNameToPort.get(name);
                    String degreMessage = "dereg" + " " + name + " " + port;
                    byte [] data5 = degreMessage.getBytes();
                    InetAddress address5 = InetAddress.getByName(serverIp);
                    DatagramPacket datagramPacket5 = new DatagramPacket(data5, data5.length, address5, serverPort);
                    socket.send(datagramPacket5);
                    Thread.sleep(500);
                }
                if(!isOffline) {
                    System.out.println("[Server not responding]");
                    System.out.println(" [Exiting]");
                }
            } else if(orderSplit[0].equals("reg")) {
                System.out.println("regist back!!!!");
                //register back
                String output58 = nickName + " " + serverIp + " " + clinetIP + " " + clientPort;
                byte[] data58 = output.getBytes();
                InetAddress address58 = InetAddress.getByName(serverIp);
                DatagramPacket packet58 = new DatagramPacket(data58, data58.length, address58, serverPort);
                socket.send(packet58);
            }
        }


    }
}

