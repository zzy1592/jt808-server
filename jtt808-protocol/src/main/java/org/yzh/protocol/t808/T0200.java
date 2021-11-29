package org.yzh.protocol.t808;

import io.github.yezhihao.protostar.DataType;
import io.github.yezhihao.protostar.annotation.Field;
import io.github.yezhihao.protostar.annotation.Message;
import org.yzh.protocol.basics.JTMessage;
import org.yzh.protocol.commons.JT808;
import org.yzh.protocol.commons.MessageId;
import org.yzh.protocol.commons.transform.AttributeConverter;
import org.yzh.protocol.commons.transform.AttributeConverterYue;

import java.util.Map;

/**
 * @author yezhihao
 * @home https://gitee.com/yezhihao/jt808-server
 */
@Message(JT808.位置信息汇报)
public class T0200 extends JTMessage {

    @Field(index = 0, type = DataType.DWORD, desc = "报警标志")
    private int warnBit;
    @Field(index = 4, type = DataType.DWORD, desc = "状态")
    private int statusBit;
    @Field(index = 8, type = DataType.DWORD, desc = "纬度")
    private int latitude;
    @Field(index = 12, type = DataType.DWORD, desc = "经度")
    private int longitude;
    @Field(index = 16, type = DataType.WORD, desc = "高程(米)")
    private int altitude;
    @Field(index = 18, type = DataType.WORD, desc = "速度(1/10公里每小时)")
    private int speed;
    @Field(index = 20, type = DataType.WORD, desc = "方向")
    private int direction;
    @Field(index = 22, type = DataType.BCD8421, length = 6, desc = "时间(YYMMDDHHMMSS)")
    private String dateTime;
    @Field(index = 28, type = DataType.MAP, desc = "位置附加信息", converter = AttributeConverter.class, version = {-1, 0})
    @Field(index = 28, type = DataType.MAP, desc = "位置附加信息(粤标)", converter = AttributeConverterYue.class, version = 1)
    private Map<Integer, Object> attributes;

    public int getWarnBit() {
        return warnBit;
    }

    public void setWarnBit(int warnBit) {
        this.warnBit = warnBit;
    }

    public int getStatusBit() {
        return statusBit;
    }

    public void setStatusBit(int statusBit) {
        this.statusBit = statusBit;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public int getAltitude() {
        return altitude;
    }

    public void setAltitude(int altitude) {
        this.altitude = altitude;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Map<Integer, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<Integer, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(512);
        if (clientId != null) {
            sb.append(MessageId.get(messageId));
            sb.append('[');
            sb.append("cid=").append(clientId);
            sb.append(",msg=").append(messageId);
            sb.append(",ver=").append(protocolVersion);
            sb.append(",ser=").append(serialNo);
            sb.append(",prop=").append(properties);
            if (isSubpackage()) {
                sb.append(",pt=").append(packageTotal);
                sb.append(",pn=").append(packageNo);
            }
            sb.append("],T0200");
        }
        sb.append("{dateTime=").append(dateTime);
        sb.append(",longitude=").append(longitude);
        sb.append(",latitude=").append(latitude);
        sb.append(",altitude=").append(altitude);
        sb.append(",speed=").append(speed);
        sb.append(",direction=").append(direction);
        sb.append(",warnBit=").append(Integer.toBinaryString(warnBit));
        sb.append(",statusBit=").append(Integer.toBinaryString(statusBit));
        sb.append(",attributes=").append(attributes);
        sb.append('}');
        return sb.toString();
    }
}