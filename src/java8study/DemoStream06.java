package java8study;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author qinc
 * @version V1.0
 * @Description: 规约和汇总
 * @Date 2018/8/13 15:54
 */
public class DemoStream06 {
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
        //统计流中的元素
        long count=transactions.stream().collect(Collectors.counting());
        //更直接的写法
        transactions.stream().count();
        System.out.println(count);
        //查找最大值和最小值
        Optional<Transaction> maxtr=transactions.stream().collect(Collectors.maxBy(Comparator.comparingInt(Transaction::getValue)));
        System.out.println(maxtr.get());
        Optional<Transaction> mintr=transactions.stream().collect(Collectors.minBy(Comparator.comparingInt(Transaction::getValue)));
        System.out.println(mintr.get());
        //汇总--求和
        int sum=transactions.stream().collect(Collectors.summingInt(Transaction::getValue));
        System.out.println(sum);
        //连接字符串
        String names=transactions.stream().map(tr->tr.getTrader().getName()).collect(Collectors.joining("-"));
        System.out.println(names);
        //分组--按城市
        Map<String,List<Transaction>> groups=transactions.stream().collect(
                Collectors.groupingBy(tr->tr.getTrader().getCity())
        );
        System.out.println(groups);
        //分组--多层分组
        Map<String,Map<Integer,List<Transaction>>> groups1=transactions.stream().collect(
                Collectors.groupingBy(
                        tr->tr.getTrader().getCity(),
                        Collectors.groupingBy(Transaction::getYear)
                )
        );
        System.out.println(groups1);
        //按子组收集数据--按年统计营业额
        Map<Integer,Integer> yearBroupSum=transactions.stream().collect(
                Collectors.groupingBy(
                        Transaction::getYear,
                        Collectors.summingInt(Transaction::getValue)
                )
        );
        System.out.println(yearBroupSum);

    }


}
