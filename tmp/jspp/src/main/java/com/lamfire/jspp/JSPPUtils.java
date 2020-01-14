package com.lamfire.jspp;


import com.lamfire.json.JSON;
import com.lamfire.logger.Logger;


/**
 * 数据包工且
 * User: lamfire
 * Date: 13-11-8
 * Time: 下午4:48
 * To change this template use File | Settings | File Templates.
 */
public class JSPPUtils {
    private static final Logger LOGGER = Logger.getLogger(JSPPUtils.class);

    public static JSPP decode(byte[] message) {
        if(message == null){
            return null;
        }
        return JSPPSerializer.get().decode(message);
    }

    public static byte[] encode(JSPP jspp){
        return JSPPSerializer.get().encode(jspp);
    }

    public ProtocolType getProtocolType(byte[] bytes)    {
        return JSPPSerializer.get().getProtocolType(bytes) ;
    }

    public ProtocolType getProtocolType(String json)    {
        return JSPPSerializer.get().getProtocolType(json) ;
    }

    public static ProtocolType getProtocolType(JSON json){
        return JSPPSerializer.get().getProtocolType(json);
    }

    public static String toJSPPString(JSPP jspp){
       return JSPPSerializer.get().toJSPPString(jspp);
    }

    public static JSPP parseJSPPString(String jsppString){
        return JSPPSerializer.get().parse(jsppString);
    }
}
