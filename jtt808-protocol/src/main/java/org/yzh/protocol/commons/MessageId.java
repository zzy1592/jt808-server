package org.yzh.protocol.commons;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yezhihao
 * @home https://gitee.com/yezhihao/jt808-server
 */
public class MessageId {

    private static final Map<Integer, String> messageId = new HashMap<>(256);

    static {
        for (Class clazz : new Class[]{JT808.class, JT1078.class, JSATL12.class}) {
            Field[] fields = clazz.getFields();
            for (Field field : fields) {
                try {
                    if (!Integer.TYPE.isAssignableFrom(field.getType()))
                        continue;
                    int id = field.getInt(null);
                    String hexId = leftPad(Integer.toHexString(id), 4, '0');
                    String name = field.getName();
                    messageId.put(id, "[" + hexId + "]" + name);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String get(int id) {
        String name = messageId.get(id);
        if (name != null)
            return name;
        return leftPad(Integer.toHexString(id), 4, '0');
    }

    public static String leftPad(String str, int size, char ch) {
        int length = str.length();
        int pads = size - length;
        if (pads > 0) {
            char[] result = new char[size];
            str.getChars(0, length, result, pads);
            while (pads > 0)
                result[--pads] = ch;
            return new String(result);
        }
        return str;
    }
}