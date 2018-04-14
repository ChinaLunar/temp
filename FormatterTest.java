package com.zlc;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FromatterTest {

    public static final String BIG_INTEGER = "100000000000000000000000000000";
    public static final String BIG_DECIMAL = "100000000000000000000000000000.0";

    public static void main(String[] args) throws ParseException {
        PrintStream jout = System.out;

        // percent
        jout.println("percent : =====================================>");
        jout.format("%% \n");// 两个百分号输出百分号
        jout.println();

        // line separator
        jout.println("line separator : =================================>");
        jout.format("当前行%n新行 \n");// 格式化符号n：输出换行符
        jout.println();

        // b/B:general:转换基本规则-String.valueOf(arg)
        jout.println("b/B : general : ==================================>");
        // 大小写
        jout.format("%b \n", true); // b小写true
        jout.format("%B \n", true); // B大写true
        // boolean / Boolean
        jout.format("%b \\n", new Boolean(true)); // boolean根据实际情况转换
        // null
        jout.format("%b \n", null); // null都转换为false
        // 非null
        jout.format("%b \n", new Object()); // 非boolean且非null的引用都转换为true
        // NaN
        jout.format("%b", 0.0 / 0.0);// NaN转换为false
        // 宽度
        jout.format("%8b \n", true); // 8宽度，不足时从头补全空格
        // 精度
        jout.format("%8.5b \n", true); // 转换boolean是忽略精度
        jout.println();

        // h/H:general:基本转换规则-Integer.toHexString(arg.hashCode())
        jout.println("h/H : general : ==================================>");
        // 大小写
        Object temp = new Object();
        jout.format("%h \n", temp); // h小写
        jout.format("%H \n", temp); // H大写
        // null
        jout.format("%h \n", null); // null都转换为null
        // NaN
        jout.format("%h \n", 0.0 / 0.0); // 7ff80000
        jout.println();

        // s/S:general:基本转换规则-arg.toString()，如果arg实现了Formattable则优先调用其方法formatTo
        jout.println("s/S : general : ==================================>");
        // 大小写
        jout.format("%s \n", "string"); // s小写arg.toString()
        jout.format("%S \n", "string"); // S大写arg.toString()
        // null
        jout.format("%s \n", null); // null转换为null
        // NaN
        jout.format("%s \n", 0.0 / 0.0);
        jout.println();

        // c/C:character:基本转换规则-Character.valueOf(char/Character)-Character.toChars(int/Integer)
        jout.println("c/C : general : ==================================>");
        // 大小写
        jout.format("%c \n", 'c'); // 转换结果是Unicode字符
        jout.format("%C \n", 'c');
        // char/Character
        jout.format("%c \n", '张');
        jout.format("%c \n", new Character('张'));
        // char/Character的Unicode编码值
        jout.format("%c \n", '\u5f20');
        jout.format("%c \n", new Character('\u5f20'));
        // int/Integer
        jout.format("%c \n", 0x5f20);
        jout.format("%c \n", Integer.valueOf(0x5f20));
        jout.println();

        // d:integral:基本转换规则-Integer.valueOf(int/Integer)
        jout.println("d : integral : ==================================>");
        jout.format("%d \n", 8);
        jout.format("%d \n", 010);
        jout.format("%d \n", 0x8);
        // BigInteger
        jout.format("%d \n", new BigInteger(BIG_INTEGER));
        jout.println();

        // o:integral:基本转换规则-Integer.toOctalString(Integer.valueOf(int/Integer))
        jout.println("o : integral : ==================================>");
        jout.format("%o \n", 8);
        jout.format("%o \n", 010);
        jout.format("%o \n", 0x8);
        // BigInteger
        jout.format("%o \n", new BigInteger(BIG_INTEGER));
        jout.println();

        // x/X:integral:基本转换规则-Integer.toHexString(Integer.valueOf(int/Integer))
        jout.println("x/X : integral : ==================================>");
        // 大小写
        jout.format("%x \n", 256);
        jout.format("%X \n", 256);
        // 不同进制
        jout.format("%x \n", 8);
        jout.format("%x \n", 010);
        jout.format("%x \n", 0x8);
        // BigInteger
        jout.format("%x \n", new BigInteger(BIG_INTEGER));
        jout.println();

        // e/E:floating point:基本转换规则-将float/Float或double/Double转换为科学计数法
        jout.println("e/E : floating point : ================================>");
        // 大小写
        jout.format("%e \n", 256.0);
        jout.format("%E \n", 256.0);
        // 不同进制（Java不支持十六进制小数）
        jout.format("%e \n", 256.0);
        jout.format("%e \n", 010.0);
        // 科学计数法
        jout.format("%e \n", 256e10);
        // BigDecimal
        jout.format("%e \n", new BigDecimal(BIG_DECIMAL));
        // NaN
        jout.format("%e \n", 0.0 / 0.0);
        jout.println();

        // f:floating point:基本转换规则-将float/Float或double/Double转换十进制
        jout.println("f : floating point : ===============================>");
        // 不同进制（Java不支持十六进制小数）
        jout.format("%f \n", 256.0);
        jout.format("%f \n", 010.0);
        // 科学计数法
        jout.format("%f \n", 230e10);
        // BigDecimal
        jout.format("%f \n", new BigDecimal(BIG_DECIMAL));
        // NaN
        jout.format("%f \n", 0.0 / 0.0);
        jout.println();

        // g/G:floating point:基本转换规则-
        jout.println("g/G : floating point : ===============================>");
        // 大小写
        jout.format("%g \n", 123.4567);
        jout.format("%G \n", 123.4567);
        jout.format("%g \n", 12345678.9);
        jout.format("%g \n", new BigDecimal(BIG_DECIMAL));
        // NaN
        jout.format("%g \n", 0.0 / 0.0);
        jout.println();

        // a/A:floating point:基本转换规则-浮点数规格化IEEE754?
        jout.println("a/A : floating point : ===============================>");
        // 大小写
        jout.format("%a \n", 0.0000001);
        jout.format("%A \n", 123.4567);
        // 单精度规格化
        jout.format("%a \n", 0.0000001f);
        // NaN
        jout.format("%a \n", 0.0 / 0.0);
        jout.println();

        // Date
        String dateStr = "2018-02-10 23:20:30,666";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
        Date date = sdf.parse(dateStr);

        // T:date
        jout.println("T : date : ===============================>");
        // param:Y:4位年:YYYY
        jout.format("%TY \n", date);
        // param:y:2位年:00-99
        jout.format("%Ty \n", date);
        // param:C:4位除100（前两位 00-99）
        jout.format("%TC \n", date);
        // param:B:月全称:本地化
        jout.format("%TB \n", date);
        // param:b:月简称:本地化
        jout.format("%Tb \n", date);
        // param:h:和b一致
        jout.format("%Th \n", date);
        // param:m:2位数字月:01-13
        jout.format("%Tm \n", date);
        // param:d:2位日:01-31
        jout.format("%Td \n", date);
        // param:e:2位日:1-31
        jout.format("%Te \n", date);
        // param:A:星期全称:本地化
        jout.format("%TA \n", date);
        // param:a:星期简称:本地化
        jout.format("%Ta \n", date);
        // param:j:一年中第几天:001-366
        jout.format("%Tj \n", date);
        jout.println();

        // t:time
        jout.println("t : time : ===============================>");
        // param:H:24小时制:00-23
        jout.format("%tH \n", date);
        // param:k:24小时制:0-23
        jout.format("%tk \n", date);
        // param:I:12小时制:01-12
        jout.format("%tI \n", date);
        // param:l:12小时制:1-12
        jout.format("%tl \n", date);
        // param:M:分钟:00-59
        jout.format("%tM \n", date);
        // param:S:秒钟:00-60
        jout.format("%tS \n", date);
        // param:L:毫秒:000-999
        jout.format("%tL \n", date);
        // param:N:纳秒:000000000-999999999
        jout.format("%tN \n", date);
        // param:p:晨午:am/pm（受本地化影响，T可以改成大写）
        jout.format("%tp \n", date);
        jout.format("%Tp \n", date);
        // param:z:时区
        jout.format("%tz \n", date);
        // param:Z:时区:GMT前缀表示
        jout.format("%tZ \n", date);
        // param:s:自"1 January 1970 00:00:00 UTC"以来的秒数
        jout.format("%ts \n", date);
        // param:Q:自"1 January 1970 00:00:00 UTC"以来的毫秒数
        jout.format("%tQ \n", date);
        jout.println();

        // T/t:date/time:通用，作用相同
        jout.println("T/t : 通用，作用相同 : ===============================>");
        // param:R:"%tH:%tM"
        jout.format("%tR \n", date);
        // param:T:"%tH:%tM:%tS"
        jout.format("%tT \n", date);
        // param:r:"%tI:%tM:%tS %Tp"
        jout.format("%tr \n", date);
        // param:D:"%tm/%td/%ty"
        jout.format("%tD \n", date);
        // param:F:"%tY-%tm-%td"
        jout.format("%tF \n", date);
        // param:c:"%ta %tb %td %tT %tZ %tY"
        jout.format("%tc \n", date);
        jout.println();

        // Flag
        jout.println("Flag : =======================================>");
        // flag:-:任意类型:左对齐
        jout.format("%-10d \n", 123);
        jout.format("%10d \n", 123);
        // flag:#:general/integral/floating point:作用不明
        jout.format("%#10s \n", "123");
        // flag:+:integral/floating point:始终显示数字符号
        jout.format("%+d \n", 123);// d:适用于byte/Byte/short/Short/int/Integer/long/Long/BigInteger
        jout.format("%+x \n", new BigInteger(BIG_INTEGER));// BigInteger才能使用o/x/X
        jout.format("%+f \n", -123.0);
        // flag: :integral/floating point:正数前显示空格
        jout.format("% d \n", 123);// d:适用于byte/Byte/short/Short/int/Integer/long/Long/BigInteger
        jout.format("% x \n", new BigInteger(BIG_INTEGER));// BigInteger才能使用o/x/X
        jout.format("% d \n", -123);
        // flag:0:integral/floating point:补零（一定要有宽度）
        jout.format("%010d \n", 123);// 正数前补零
        jout.format("%010f \n", 123.0);// 小数后补零
        // flag:,:integral/floating point:数字分组（本地化）
        jout.format("%,d \n", 123456);// integral只能用d
        jout.format("%,f \n", 123456.1);// floating point要使用f/g/G
        // flag:(:integral/floating point:使用括号包裹负数
        jout.format("%(d \n", -123);
        jout.format("%+(d \n", -123);// 负号就是不显示了
        jout.format("%(e", -123.0);// floating point要使用e/E/f/g/G
    }

}
