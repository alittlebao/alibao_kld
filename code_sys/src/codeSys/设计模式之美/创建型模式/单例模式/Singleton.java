package codeSys.设计模式之美.创建型模式.单例模式;

/**
 * 
 * 单例模式
 * 
 * 在计算机系统中，线程池、缓存、日志对象、对话框、打印机、显卡的驱动程序对象常被设计成单例。
   1、单例类只能有一个实例。
　 2、单例类必须自己创建自己的唯一实例。
　 3、单例类必须给所有其他对象提供这一实例。

 * 解决问题，一个全局的类频繁底被创建和销毁
 * @author: alibao
 *
 * Modification History:
 * Date         Author          Version            Description
 *---------------------------------------------------------*
 * 2018年12月3日     alibao           v1.0.0               修改原因
 */
public class Singleton {
	
	/**
	 * 饿汉式
	 * 
	 * 优点：写法简单，在类装载的时候就完成实例化。避免了线程同步问题。
	 * 缺点：在类装载的时候就完成实例化，没有达到Lazy Loading的效果。如果从始至终从未使用过这个实例，则会造成内存的浪费。
	 */
	
	// 饿汉式（静态常量）[可用]==========================================
	private final static Singleton INSTANCE1 = new Singleton();
	
	private Singleton(){};
	
	public Singleton getInstance1(){
		return INSTANCE1;
	}
	//===================================================================
	
	

}
