package utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * @author qinc
 * @version V1.0
 * @Description: ip转int 把ip的四个数按点分成四个数
 * @Date 2018/8/6 20:40
 */
public class IpUtils {

    public static int ipToInt(String ip){
        StringBuilder sb=new StringBuilder();
        String[] ipSub=ip.split("\\.");
        for (String s:ipSub){
            String ipSub2=Integer.toBinaryString(Integer.parseInt(s));
            String pinEncode=String.format("%8s", ipSub2).replaceAll(" ", "0");
            System.out.println(pinEncode);
            sb.append(pinEncode);
        }
        System.out.println("二进制表示："+sb.toString());
        return Integer.valueOf(sb.toString(),2);

    }
    public static String ipToStr(int ip){
        StringBuilder sb= new StringBuilder();
        String ipStr=Integer.toBinaryString(ip);
        System.out.println("十进制转为二进制："+ipStr);
        String pinEncode=String.format("%32s", ipStr).replaceAll(" ", "0");
        String ip1=pinEncode.substring(0,8);
        sb.append(Integer.valueOf(ip1,2)).append(".");
        String ip2=pinEncode.substring(9,16);
        sb.append(Integer.valueOf(ip2,2)).append(".");
        String ip3=pinEncode.substring(17,24);
        sb.append(Integer.valueOf(ip3,2)).append(".");
        String ip4=pinEncode.substring(25,32);
        sb.append(Integer.valueOf(ip4,2));
      return sb.toString();
    }





    public static String getHostIP() {
        String IFCONFIG = null;
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf
                        .getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()
                            && !inetAddress.isLinkLocalAddress()
                            && inetAddress.isSiteLocalAddress()
                            &&inetAddress.getHostAddress().toString().indexOf("10") >= 0) {
                        IFCONFIG = inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {
        }
        return IFCONFIG;
    }
    public static void main(String args[]){
        //"A".intern();
        System.out.println(ipToInt("10.30.27.50"));
        System.out.println(ipToStr(168827203));
//        long result = (10L << (3 * 8))+(0L << (2 * 8))+(27L << (1 * 8))+(63L << (0 * 8));
//        System.out.println(result);


        //System.out.println(getHostIP());
    }
}
