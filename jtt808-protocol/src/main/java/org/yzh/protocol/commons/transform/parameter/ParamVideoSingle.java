package org.yzh.protocol.commons.transform.parameter;

import io.github.yezhihao.protostar.annotation.Field;
import io.netty.buffer.ByteBuf;

import java.util.Map;
import java.util.TreeMap;

/**
 * 单独视频通道参数设置
 * @author yezhihao
 * @home https://gitee.com/yezhihao/jt808-server
 */
public class ParamVideoSingle {

    public static final int id = 0x0077;

    public static int id() {
        return id;
    }

    @Field(desc = "单独通道视频参数设置列表")
    private Map<Integer, ParamVideo> paramVideos = new TreeMap<>();

    public ParamVideoSingle() {
    }

    public ParamVideoSingle(Map<Integer, ParamVideo> paramVideos) {
        this.paramVideos = paramVideos;
    }

    public Map<Integer, ParamVideo> getParamVideos() {
        return paramVideos;
    }

    public void setParamVideos(Map<Integer, ParamVideo> paramVideos) {
        this.paramVideos = paramVideos;
    }

    public static class S implements io.github.yezhihao.protostar.Schema<ParamVideoSingle> {

        public static final S INSTANCE = new S();

        private S() {
        }

        @Override
        public ParamVideoSingle readFrom(ByteBuf input) {
            byte total = input.readByte();
            Map<Integer, ParamVideo> paramVideos = new TreeMap();
            for (int i = 0; i < total; i++) {
                byte channelNo = input.readByte();
                ParamVideo paramVideo = ParamVideo.Schema2.INSTANCE.readFrom(input);
                paramVideos.put((int) channelNo, paramVideo);
            }
            return new ParamVideoSingle(paramVideos);
        }

        @Override
        public void writeTo(ByteBuf output, ParamVideoSingle message) {
            Map<Integer, ParamVideo> paramVideos = message.paramVideos;
            output.writeByte(message.paramVideos.size());
            for (Map.Entry<Integer, ParamVideo> videoEntry : paramVideos.entrySet()) {
                output.writeByte(videoEntry.getKey());
                ParamVideo.Schema2.INSTANCE.writeTo(output, videoEntry.getValue());
            }
        }
    }
}