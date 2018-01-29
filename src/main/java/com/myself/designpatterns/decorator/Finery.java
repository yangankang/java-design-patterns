package com.myself.designpatterns.decorator;

/**
 * 装饰模式核心
 *
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-18 上午10:25:51
 */
public class Finery implements IPersonShow {
    // 组件
    private IPersonShow compoment;

    /**
     * 装饰人类
     *
     * @param compoment
     * @author daniel
     * @time 2016-5-18 上午9:54:46
     */
    public void decorate(IPersonShow compoment) {
        this.compoment = compoment;
    }

    public void show() {
        System.out.println(" Finery:show");
        if (compoment != null) {
            compoment.show();
        }
    }

}
