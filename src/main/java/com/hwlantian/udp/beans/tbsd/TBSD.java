package com.hwlantian.udp.beans.tbsd;

import com.fasterxml.jackson.annotation.*;
import com.hwlantian.udp.beans.PackageKey;
import com.hwlantian.udp.service.TimeUtil;
import lombok.Data;

/**
 * Created by AirJ on 17/8/17.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TBSD <T> {
    public interface BaseView{}
    public interface WithIdView extends BaseView{};


    public Long getTimestamp() {
        return timestamp != null ? timestamp : 0L;
    }
    @JsonView(BaseView.class)
    @PackageKey(3)
    protected String by;


    @JsonView(BaseView.class)
    @PackageKey(0x10)
    protected T data;


    @JsonIgnore
    @PackageKey(value = 4,ignore = true)
    protected Long timestamp;

    @JsonSetter
    @PackageKey(2)
    public void setDelayed(Long delayed){
        if (delayed == null) {
            timestamp = null;
        } else {
            timestamp = TimeUtil.getTime()-delayed;
        }
    }
    @JsonGetter
    @JsonView(BaseView.class)
    @PackageKey(2)
    public Long getDelayed(){
        if (timestamp == null) {
            return null;
        } else {
            long delayed = TimeUtil.getTime() - timestamp;
            if (delayed > 24 * 60 * 60 * 1000L) {
                return null;
            }
            return delayed;
        }
    }


    public TBSD() {
    }

    public TBSD(T data) {
        setData(data);
    }

    public TBSD(T data, Long delay) {
        setData(data);
        setDelayed(delay);
    }
    public TBSD(T data, Long delay, String by) {
        setData(data);
        setDelayed(delay);
        setBy(by);
    }

}
