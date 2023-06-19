package utils;
import back.objects.BddObject;
import java.lang.*;
import java.util.Vector;
import java.awt.Point;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
public class Outils{
    public static int[] returnIntArray(Vector intArray)
    {
        int[]array=new int[intArray.size()];
        for(int i=0;i<intArray.size();i++)
        {
            array[i]=(int)intArray.elementAt(i);
        }
        return array;
    }
   public static String[] returnStringArray(Vector stringArray)
   {
       String[]table=new String[stringArray.size()];
       for(int i=0;i<stringArray.size();i++)
       {
           table[i]=(String)stringArray.elementAt(i);
       }
       return table;
   }
   
    public static String[] ObjectToStringArray(Object[] stringArray)
   {
       String[]table=new String[stringArray.length];
       for(int i=0;i<stringArray.length;i++)
       {
           table[i]=(String)stringArray[i];
       }
       return table;
   }
   
   public static Field[] returnFieldArray(ArrayList<Field> fieldArray)
   {
       Field[]table=new Field[fieldArray.size()];
       for(int i=0;i<fieldArray.size();i++)
       {
           table[i]=fieldArray.get(i);
       }
       return table;
   }
   
      public static BddObject[] returnBddArray(ArrayList<Object> BddArray)
   {
       BddObject[]table=new BddObject[BddArray.size()];
       for(int i=0;i<BddArray.size();i++)
       {
           table[i]=(BddObject)BddArray.get(i);
       }
       return table;
   }
      
     
           
              public static Method[] returnMethodArray(Object[] BddArray)
   {
       Method[]table=new Method[BddArray.length];
       for(int i=0;i<BddArray.length;i++)
       {
           table[i]=(Method)BddArray[i];
       }
       return table;
   }
                public static Double heure_diff(Date fin,Date debut){
            long diffTime=Math.abs(fin.getTime()-debut.getTime());
            long diff=TimeUnit.MILLISECONDS.convert(diffTime, TimeUnit.MILLISECONDS);
             double divided=1000d*3600d;
           
            return diff/divided;
     }
                
   
     public static int getNbPagination(int nbelement,int length){
        System.out.println(length%nbelement);
        int nb=length/nbelement;
        
        if(length%nbelement>0)return nb+1;
        else return nb;
    }
   public static boolean hasDecalage(Timestamp timestamp){
        // Convertir le Timestamp en OffsetDateTime avec le décalage actuel
        OffsetDateTime offsetDateTimeWithOffset = timestamp.toInstant().atOffset(ZoneOffset.systemDefault().getRules().getOffset(timestamp.toInstant()));

        // Convertir en OffsetDateTime en UTC
        OffsetDateTime offsetDateTimeUtc = offsetDateTimeWithOffset.withOffsetSameInstant(ZoneOffset.UTC);

        // Comparer les OffsetDateTime
        boolean hasOffset = !offsetDateTimeWithOffset.equals(offsetDateTimeUtc);
        return hasOffset;
   }
   public static Timestamp dropDecalage(Timestamp timestamp){
       if(Outils.hasDecalage(timestamp)){
            ZonedDateTime zonedDateTimeWithOffset = timestamp.toInstant().atZone(ZoneId.systemDefault());
        // Convertir en ZonedDateTime sans le décalage
        ZonedDateTime zonedDateTimeWithoutOffset = zonedDateTimeWithOffset.withZoneSameInstant(ZoneOffset.UTC);
        // Convertir en LocalDateTime
        LocalDateTime localDateTime = zonedDateTimeWithoutOffset.toLocalDateTime();
        // Convertir en Timestamp sans le décalage
        return Timestamp.valueOf(localDateTime);
       }
       return timestamp;
   }
}