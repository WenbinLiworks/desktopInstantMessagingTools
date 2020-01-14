package com.lamfire.jspp;

import com.lamfire.json.JSON;

import java.util.Map;

/**
 * JSPP SERVICE
 * User: lamfire
 * Date: 13-11-11
 * Time: 上午10:24
 * To change this template use File | Settings | File Templates.
 */
public class SERVICE extends JSPP{
    public static final String TYPE_GET = "get";
    public static final String TYPE_SET = "set";
    public static final String TYPE_ERROR = "error";
    public static final String TYPE_RESULT = "result";

    private ARGS args;
    private RESULT result;

    public String getKey() {
        return (String)get("key");
    }

    public void setKey(String key) {
        put("key",key);
    }

    public String getNs() {
        return (String)get("ns");
    }

    public void setNs(String ns) {
        put("ns",ns);
    }

    public ARGS getArgs() {
        if(args != null){
            return args;
        }
        Map<String,Object> map = (Map<String,Object>)get("args");
        if(map == null){
            return null;
        }
        args = new ARGS();
        args.putAll(map);
        return args;
    }

    public void setArgs(ARGS args) {
        this.args = args;
        put("args",args);
    }

    public RESULT getResult() {
        if(result != null){
            return result;
        }
        Map<String,Object> map = (Map<String,Object>)get("result");
        if(map == null){
            return null;
        }
        result  = new RESULT();
        result.putAll(map);
        return result;
    }

    public void setResult(RESULT result) {
        this.result = result;
        put("result",result);
    }

    public String toString(){
        JSON json = new JSON();
        json.put(JSPP_TYPE_PREFIX_SERVICE,this);
        return json.toJSONString();
    }

    public static SERVICE valueOf(String jsppString) {
        JSON json = JSON.fromJSONString(jsppString);
        JSON jspp = (JSON)json.get(JSPP.JSPP_TYPE_PREFIX_SERVICE);
        if(jspp == null){
            return null;
        }
        SERVICE m = new SERVICE();
        m.putAll(jspp);
        return m;
    }
}
