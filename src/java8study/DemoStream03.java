package java8study;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author qinc
 * @version V1.0
 * @Description: ${TODO}
 * @Date 2018/8/7 17:13
 */
public class DemoStream03 {
    public static void main(String args[]){
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
        //1.找出2011年的所有交易，并按照交易额排序（低到高）
        List<Transaction> trs1=transactions.stream().filter(transaction ->transaction.getYear()==2011)
                .sorted(Comparator.comparing(Transaction::getValue)).collect(Collectors.toList());
        System.out.println("1:"+trs1.toString());
        //2.交易员都在哪些城市工作过
        List<String> citys=transactions.stream().map(Transaction::getTrader).map(Trader::getCity).distinct().collect(Collectors.toList());
        System.out.println("2:"+citys);
        //3.查询所有来自剑桥的交易员，并按姓名排序
        List<Trader> traders=transactions.stream().map(Transaction::getTrader).filter(trader -> trader.getCity().equals("Cambridge")).distinct()
                .sorted(Comparator.comparing(Trader::getName)).collect(Collectors.toList());
        System.out.println("3:"+traders.toString());
        //4.返回所有操作员的姓名字符串按字母排序
        List<String> names=transactions.stream().map(transaction -> transaction.getTrader().getName()).distinct().sorted()
                .collect(Collectors.toList());
        System.out.println("4:"+names);
        //5.有没有交易员在米兰工作的
        if(transactions.stream().filter(transaction -> transaction.getTrader().getCity().equals("Milan")).findAny().isPresent()){
            System.out.println("5:有交易员在米兰工作的");
        };
        //6.打印生活在剑桥的所有交易员的交易额
        transactions.stream().filter(transaction -> transaction.getTrader().getCity().equals("Cambridge")).map(Transaction::getValue).forEach(value->{
            System.out.println("6:"+value);
        });
        //7.所有交易额中，最高的是多少
        Optional<Integer> max=transactions.stream().map(Transaction::getValue).reduce(Integer::max);
        System.out.println("7:"+max.get());
        //8.找到交易额最小的交易
        Optional<Transaction> transaction=transactions.stream().sorted(Comparator.comparing(Transaction::getValue)).findFirst();
        System.out.println("8:"+transaction.get().toString());
        //7.使用数值流
        OptionalInt optionalInt=transactions.stream().mapToInt(Transaction::getValue).max();
        System.out.println(optionalInt.orElse(1));


    }
}


class Trader{
    private final String name;
    private final String city;
    public Trader(String n, String c){
        this.name = n;
        this.city = c;
    }
    public String getName(){
        return this.name;
    }
    public String getCity(){
        return this.city;
    }
    @Override
    public String toString(){
        return "Trader:"+this.name + " in " + this.city;
    }
}
class Transaction{
    private final Trader trader;
    private final int year;
    private final int value;
    public Transaction(Trader trader, int year, int value){
        this.trader = trader;
        this.year = year;
        this.value = value;
    }
    public Trader getTrader(){
        return this.trader;
    }
    public int getYear(){
        return this.year;
    }
    public int getValue(){
        return this.value;
    }
    @Override
    public String toString(){
        return "{" + this.trader + ", " +
                "year: "+this.year+", " +
                "value:" + this.value +"}";
    }
}
