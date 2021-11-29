package org.yzh.web.model.enums;

import io.github.yezhihao.netmc.session.Session;
import org.yzh.protocol.basics.JTMessageFilter;
import org.yzh.web.model.vo.Location;

/**
 * @author yezhihao
 * @home https://gitee.com/yezhihao/jt808-server
 */
public enum SessionKey {

    DeviceInfo,
    Snapshot,
    AreaFilter;

    public static org.yzh.web.model.vo.DeviceInfo getDeviceInfo(Session session) {
        return (org.yzh.web.model.vo.DeviceInfo) session.getAttribute(DeviceInfo);
    }

    public static Location getSnapshot(Session session) {
        return (Location) session.getAttribute(Snapshot);
    }

    public static JTMessageFilter getAreaFilter(Session session) {
        return (JTMessageFilter) session.getAttribute(AreaFilter);
    }
}