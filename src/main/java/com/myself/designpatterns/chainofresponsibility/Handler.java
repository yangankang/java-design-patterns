package com.myself.designpatterns.chainofresponsibility;

public abstract class Handler {
    /**
     * 责任链下一个继承者
     */
    protected Handler successor;

    public void setHandler(Handler successor) {
        this.successor = successor;
    }

    /**
     * 调用请求
     *
     * @param request
     * @author daniel
     * @time 2016-6-2 上午9:53:43
     */
    public abstract void handlerRequest(int request);

}
