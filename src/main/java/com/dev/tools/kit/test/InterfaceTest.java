package com.dev.tools.kit.test;

import com.dev.tools.kit.domain.ParamArgus;
import com.dev.tools.kit.domain.ParamInfo;

/**
 * Created by zhoujun5 on 2018/10/17.
 */
public interface InterfaceTest {
    /**
     * 简单接口测试
     * @param id
     * @return
     */
    Long simpleInterFace(Long id);

    /***
     * 返回值复杂接口测试
     * @param id
     * @param d
     * @return
     */
    ParamInfo retuenComplexInterFace(Long id, Integer d);


    /***
     * 返回值超级复杂接口测试
     * @param id
     * @param d
     * @return
     */
    ParamArgus retuenSuperComplexInterFace(Long id, Integer d);

    /***
     * 返回值、参数复杂接口测试
     * @param id
     * @param d
     * @return
     */
    ParamArgus retuenAgrusComplexInterFace(ParamInfo id, Integer d);


    /***
     * 返回值、参数超级复杂接口测试111
     * @param id
     * @param d
     * @return
     */
    ParamArgus retuenArgusSuperComplexInterFace(ParamArgus id, Integer d);

    /**
     * 杀杀杀22
     * @return
     */
    void simpleInterFace2(Long id);

}
