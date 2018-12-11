package com.hwlantian.udp.beans.tbsd;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.lang.reflect.Method;

/**
 * Created by ling on 17/8/22.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class KnownDatas {
    @SaveToStatus
    protected TBSD<String> ssid;
    @SaveToStatus
    protected TBSD<String> password;
    @SaveToStatus
    protected TBSD<Boolean> started;
    @SaveToStatus
    protected TBSD<Boolean> pairing;
    @SaveToStatus
    protected TBSD<Integer> power;
    @SaveToStatus
    protected TBSD<Integer> mode;
    @SaveToStatus
    protected TBSD<Integer> ledSwitch;
    @SaveToStatus
    protected TBSD<Integer> fanSpeedPercent;
    @SaveToStatus
    protected TBSD<Integer> fanSpeed;
    @SaveToStatus
    protected TBSD<Integer> fanLevel;
    @SaveToStatus
    protected TBSD<Double> slope;
    @SaveToStatus
    protected TBSD<Integer> airLoop;
    @SaveToStatus
    protected TBSD<Integer> airHeat;
    @SaveToStatus
    protected TBSD<Integer> ionPower;
    @SaveToStatus
    protected TBSD<Integer> humidifier;
    @SaveToStatus
    protected TBSD<Integer> screen;
    @SaveToStatus
    protected TBSD<String> mac;
    @SaveToStatus
    protected TBSD<String> gps;
    @SaveToStatus
    protected TBSD<Integer> battStatus;
    @SaveToStatus
    protected TBSD<Integer> automatic;
    @SaveToStatus
    protected TBSD<Integer> theme;
    @SaveToStatus
    protected TBSD<Integer> language;
    @SaveToStatus
    protected TBSD<Integer> powerOffTimeout;
    @SaveToStatus
    protected TBSD<Integer> durTarget;


    protected TBSD<Boolean> upgrade;

    @JsonIgnore
    public boolean isEmpty() {
        for (Method method : KnownDatas.class.getDeclaredMethods()) {
            if (method.getName().startsWith("get") && method.getParameterCount() == 0) {
                try {
                    if (method.invoke(this)!=null) {
                        return false;
                    }
                } catch (Exception e) {
                    return false;
                }
            }
        }
        return true;
    }

//    public TBSD get(String name) throws Exception {
//        String methodName = "get" + StringUtils.capitalize(name);
//        Method method = this.getClass().getMethod(methodName);
//        return (TBSD)method.invoke(this);
//    }
//
//    //NOTE: set(A externs B) may faild
//    public void set(String name, TBSD value) throws Exception {
//        String methodName = "set" + StringUtils.capitalize(name);
//        Method method = this.getClass().getMethod(methodName, value.getClass());
//        method.invoke(this,value);
//    }

}
