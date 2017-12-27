package com.tiandu.recruit.stud.mycoach;

import java.io.Serializable;
import java.util.List;

/**
 * 项目名称：RecruitStud
 * 类描述：
 * 创建人：chendequnn
 * 创建时间：2017/12/25 16:34
 * 修改人：chendequnn
 * 修改时间：2017/12/25 16:34
 * 修改备注：
 */
public class CityData implements Serializable {

    /**
     * name : 北京(直辖市)
     * cityList : [{"name":"东城区"},{"name":"西城区"},{"name":"崇文区"},{"name":"宣武区"},{"name":"朝阳区"},{"name":"丰台区"},{"name":"石景山区"},{"name":"海淀区"},{"name":"门头沟区"},{"name":"房山区"},{"name":"通州区"},{"name":"顺义区"},{"name":"昌平区"},{"name":"大兴区"},{"name":"平谷区"},{"name":"怀柔区"},{"name":"密云县"},{"name":"延庆县"}]
     */

    private String name;
    private List<CityListBean> cityList;

    public void setName(String name) {
        this.name = name;
    }

    public void setCityList(List<CityListBean> cityList) {
        this.cityList = cityList;
    }

    public String getName() {
        return name;
    }

    public List<CityListBean> getCityList() {
        return cityList;
    }

    public static class CityListBean {
        /**
         * name : 东城区
         */

        private String name;

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}