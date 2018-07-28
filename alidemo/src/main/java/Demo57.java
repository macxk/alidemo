import com.alibaba.fastjson.JSONArray;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by xk on 2018/7/28.
 */
public class Demo57 {

   public static void main(String[] a){
       System.out.println(demoY());
       System.out.println(demoN01());
   }

   public static String demoY(){
       List<String> list = new ArrayList<String>();
       list.add("1");
       list.add("2");

       Iterator<String> iterator = list.iterator();
       while (iterator.hasNext()){
           String item = iterator.next();
           if (item.equals("2")){
               iterator.remove();
           }
       }

       return JSONArray.toJSONString(list);
   }

   //报错ConcurrentModificationException
   public static String demoN(){
       List<String> list = new ArrayList<String>();
       list.add("1");
       list.add("2");
       for (String item:list){
           if (item.equals("2")){
               list.remove(item);
           }
       }

       return JSONArray.toJSONString(list);
   }

   //反例反编译后的结果，问题出在next()方法上，正例用iterator.remove()重置了cursor,
    //而反例中用的是list.remove(item)，cursor未被重置造成 size!=cursor
   // 造成 hasNext() 为true 造成next()第三次被执行 造成异常抛出
   public static String demoN01(){

        List list = new ArrayList();
        list.add("1");
        list.add("2");
        Iterator i$ = list.iterator();
        do
            {
                if(!i$.hasNext())
                        break;
                String temp = (String)i$.next();
                if("2".equals(temp))
                    list.remove(temp);
            } while(true);

        return JSONArray.toJSONString(list);
   }

}
