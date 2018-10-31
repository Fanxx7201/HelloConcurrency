# HelloConcurrency
高并发编程案例demo_高并发专用<br>
> 2018.10.30 : J.U.C之AQS-CyclicBarrier


<h2 id = "">J.U.C之AQS-CyclicBarrier</h2>
<h3 id = "代码">代码</h3> 
* com.test.confxx.example.aqs01
<h3 id = "概念">概念</h3> 
* 是一个同步辅助类, 允许一组线程相互等待, 直到到达某个屏障点(common battier point). 可以完成多个线程之间的相互等待. 每个线程都准备就绪后, 才能各自往下执行之后的操作.和CountDownLauth相似的地方就是都是通过计数器来实现的.某个线程调用await方法之后, 该线程就进入了等待的状态. 计数器执行的是加1的操作.计数器的值, 达到了我们设置的初始状态的时候, 因为调用了await方法而进入等待状态的线程会被唤醒.继续执行后续的操作. CyclicBarrier释放等待线程之后可以重用, 我们又称之为循环屏障.
<h3 id = "使用场景">使用场景</h3> 
* 多线程计算数据, 最后合并计算结果. 
<h3 id = "场景举例">场景举例</h3> 
* 场景举例:  多线程计算数据, 最后合并计算结果. 用excel保存所有银行流水. 线程先计算每一页的日均银行流水, 最后action计算出整个的日均银行流水. 

<h2 id = "">J.U.C之AQS-ReentrantLock与锁</h2>

<h3 id = "代码">代码</h3> 
* com.test.confxx.example.lock

<h3 id = "概念">ReentrantLock与Synchronized的区别</h3> 
* 可重入性：两者的锁都是可重入的，差别不大，有线程进入锁，计数器自增1，等下降为0时才可以释放锁
* 锁的实现：synchronized是基于JVM实现的（用户很难见到，无法了解其实现），ReentrantLock是JDK实现的(可见源码)。
* 性能区别：在最初的时候，二者的性能差别差很多，当synchronized引入了偏向锁、轻量级锁（自选锁）后，二者的性能差别不大，官方推荐synchronized（写法更容易、在优化时其实是借用了ReentrantLock的CAS技术，试图在用户态就把问题解决，避免进入内核态造成线程阻塞）
* 功能区别： 
 （1）便利性：synchronized更便利，它是由编译器保证加锁与释放。ReentrantLock是需要手动释放锁，所以为了避免忘记手工释放锁造成死锁，所以最好在finally中声明释放锁。 
 （2）锁的细粒度和灵活度，ReentrantLock优于synchronized
<h3 id = "如何选择锁？">如何选择锁？</h3> 
  1、当只有少量竞争者，使用synchronized <br>
  2、竞争者不少但是线程增长的趋势是能预估的，使用ReetrantLock <br>
  3、synchronized不会造成死锁，jvm会自动释放死锁。(一定要有unlock保证解锁)<br>
  