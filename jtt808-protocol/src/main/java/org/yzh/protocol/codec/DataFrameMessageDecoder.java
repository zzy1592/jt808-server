package org.yzh.protocol.codec;

import io.github.yezhihao.netmc.util.ByteBufUtils;
import io.github.yezhihao.protostar.ProtostarUtil;
import io.github.yezhihao.protostar.schema.RuntimeSchema;
import io.netty.buffer.ByteBuf;
import org.yzh.protocol.basics.JTMessage;
import org.yzh.protocol.jsatl12.DataPacket;

/**
 * 数据帧解码器
 * @author yezhihao
 * @home https://gitee.com/yezhihao/jt808-server
 */
public class DataFrameMessageDecoder extends JTMessageDecoder {

    private final RuntimeSchema<DataPacket> dataFrameSchema;
    private final byte[] dataFramePrefix;

    public DataFrameMessageDecoder(byte[] dataFramePrefix, String... basePackages) {
        super(basePackages);
        this.dataFramePrefix = dataFramePrefix;
        this.dataFrameSchema = ProtostarUtil.getRuntimeSchema(DataPacket.class, 0);
    }

    @Override
    public JTMessage decode(ByteBuf input) {
        if (ByteBufUtils.startsWith(input, dataFramePrefix)) {
            DataPacket message = new DataPacket();
            message.setPayload(input);
            dataFrameSchema.mergeFrom(input, message);
            return message;
        }
        return super.decode(input);
    }
}