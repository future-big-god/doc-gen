package com.dev.tools.kit;

import com.dev.tools.kit.domain.FieldInfo;
import com.dev.tools.kit.domain.ModelInfo;

/**
 * Created by zhoujun5 on 2018/10/17.
 */
public interface InterfaceTest {
    /**
     * 简单接口测试
     *
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
    FieldInfo retuenComplexInterFace(Long id, Integer d);


    /***
     * 返回值超级复杂接口测试
     * @param id
     * @param d
     * @return
     */
    ModelInfo retuenSuperComplexInterFace(Long id, Integer d);

    /***
     * 返回值、参数复杂接口测试
     * @param id
     * @param d
     * @return
     */
    ModelInfo retuenAgrusComplexInterFace(FieldInfo id, Integer d);


    /***
     * 返回值、参数超级复杂接口测试111
     * @param id
     * @param d
     * @return
     */
    ModelInfo retuenArgusSuperComplexInterFace(ModelInfo id, Integer d);

    /**
     * 杀杀杀22
     *
     * @return
     */
    void simpleInterFace2(Long id);

}
