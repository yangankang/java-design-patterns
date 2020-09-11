/**
 * https://blog.csdn.net/A1342772/article/details/91349142
 * https://www.runoob.com/design-pattern/design-pattern-tutorial.html
 * <p>
 * 第一部分：创建型模式：弹弓原件（单例，工厂，原型，建造） 注：工厂模式有两种
 * <p>
 * 单例模式      Singleton Pattern
 * 确保某一个类只有一个实例，而且自行实例化并向整个系统提供这个实例。
 * 1.预加载  2.懒加载
 * {@link com.myself.designpatterns.singleton}
 * <p>
 * 工厂方法模式      Factory Pattern
 * 定义一个用于创建对象的接口，让子类决定实例化哪一个类。工厂方法是一个类的实例化延迟到其子类。
 * 1.简单工厂  2.工厂方法  3.抽象工厂
 * {@link com.myself.designpatterns.factory}
 * <p>
 * 原型模式      Prototype Pattern
 * 用原型实例指定创建对象的种类，并且通过拷贝这些原型创建新的对象。
 * {@link com.myself.designpatterns.prototype}
 * <p>
 * 建造者模式      Builder Pattern
 * 将一个复杂对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示。
 * 1.产品(Product)  2.抽象生成器(Builder)  3.具体生产器(ConcreteBuilder)  4.指挥者(Director)
 * {@link com.myself.designpatterns.builder}
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * 第二部分：结构型模式：外援组 佩戴桥石（外观，享元，组合，适配器，代理，桥连，装饰器）
 * <p>
 * 外观模式/门面模式      Facade Pattern
 * 要求一个子系统的外部与其内部的通信必须通过一个统一的对象进行。门面模式提供了一个高层次的接口，使得子系统更容易使用。
 * {@link com.myself.designpatterns.facade}
 * <p>
 * 享元模式      Flyweight Pattern
 * 使用共享对象可有效地支持大量的细粒度对象。
 * {@link com.myself.designpatterns.flyweight}
 * <p>
 * 组合模式      Composite Pattern
 * 将对象组合成树形结构以表示“部分-整体”的层次结构，使得用户对单个对象和组合对象的使用具有一致性。
 * {@link com.myself.designpatterns.composite}
 * <p>
 * 适配器模式      Adapter Pattern
 * 将一个类的接口变换成客户端所期待的另一种接口，从而使原本因接口不匹配而无法在一起工作的两个类能够在一起工作。
 * {@link com.myself.designpatterns.adapter}
 * <p>
 * 代理模式      Proxy pattern
 * 为其他对象提供一种代理以控制对这个对象的访问。
 * {@link com.myself.designpatterns.proxy}
 * <p>
 * 桥连模式/桥梁模式      Bridge Pattern
 * 将抽象和实现解耦，使得两者可以独立的变化。
 * {@link com.myself.designpatterns.bridge}
 * <p>
 * 装饰模式      Decorator Pattern
 * 动态地给一个对象添加一些额外的职责。就增加功能来说，装饰模式相比生成子类更为灵活。
 * {@link com.myself.designpatterns.decorator}
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * 第三部分：行为型模式：爹责令房中介装备观测板（迭代，责任链，命令，访问者，中介者，解释器，状态，备忘录，观察者，策略，模板）
 * <p>
 * 迭代器模式          Iterator Pattern
 * 它提供一种方法访问一个容器对象中各个元素，而又不需要暴露该对象的内部细节。
 * {@link com.myself.designpatterns.iterator}
 * <p>
 * 责任链模式         Chain of Responsibility Pattern
 * 使多个对象有机会处理请求，从而避免了请求的发送者和接收者之间的耦合关系 。将这些对象连成一个链，并沿着这条链传递请求，知道有对象处理它为止。
 * {@link com.myself.designpatterns.chainofresponsibility}
 * <p>
 * 命令模式          Command Pattern
 * 将一个请求封装成一个对象，从而让你使用不同的请求把客户端参数化，对请求排队或者记录请求日志，可以提供命令的撤销和恢复功能。
 * 定义三个角色：1、received 真正的命令执行对象 2、Command 3、invoker 使用命令对象的入口
 * 通过调用者调用接受者执行命令，顺序：调用者→命令→接受者。
 * https://www.cnblogs.com/yulinfeng/p/5940704.html
 * {@link com.myself.designpatterns.command}
 * <p>
 * 访问者模式         Visitor Pattern
 * 封装一些作用于某种数据结构中的各种元素，它可以在不改变数据结构的前提下定义作用于这些元素的新的操作。
 * {@link com.myself.designpatterns.visitor}
 * <p>
 * 中介者模式         Mediator Pattern
 * 用一个中介对象封装一系列的对象交互，中介者使各对象不需要显示的相互作用，从而使其耦合松散，而且可以独立的改变它们之间的交互。
 * {@link com.myself.designpatterns.mediator}
 * <p>
 * 解释器模式         Interpreter Pattern
 * 给定一门语言，定义它的文法的一种表示，并定义一个解释器，该解释器使用该表示来解释语言中的句子。
 * 应用实例：编译器、运算表达式计算。
 * 使用场景： 1、可以将一个需要解释执行的语言中的句子表示为一个抽象语法树。
 * 2、一些重复出现的问题可以用一种简单的语言来进行表达。
 * 3、一个简单语法需要解释的场景。
 * https://www.cnblogs.com/adamjwh/p/10938852.html
 * {@link com.myself.designpatterns.interpreter}
 * {@link com.myself.designpatterns.interpreter.demo2}
 * <p>
 * 状态模式          State Pattern
 * 当一个对象在状态改变时允许其改变行为，这个对象看起来像改变了其类。
 * {@link com.myself.designpatterns.state}
 * <p>
 * 备忘录模式         Memento Pattern
 * 在不破坏封装的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态，这样以后就可将该对象恢复到原来保存的状态。
 * {@link com.myself.designpatterns.memento}
 * <p>
 * 观察者模式         Observer Pattern
 * 定义对象间一种一对多的依赖关系，使得每当一个对象改变状态，则所有依赖于它的对象都会得到通知并被自动更新。
 * {@link com.myself.designpatterns.observer}
 * <p>
 * 策略模式          Strategy Pattern
 * 定义一组算法，将每个算法都封装起来，并且使他们之间可以互换。
 * {@link com.myself.designpatterns.strategy}
 * <p>
 * 模板方法模式   Template Method Pattern
 * 定义一个操作中的算法框架，而将一些步骤延迟到子类中。使得子类可以不改变一个算法的结构即可以重定义该算法的某些特定步骤。
 * {@link com.myself.designpatterns.templatemethod}
 */
