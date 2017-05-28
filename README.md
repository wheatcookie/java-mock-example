# java-mock-example
（1）Mockito和PowerMock简介  
Mockito是java单元测试的模拟框架，不仅可以模拟类，还可以记录交互行为、即某个方法的调用行为。  
PowerMock作为Mockito的扩展，支持对静态方法、构造方法、私有方法以及final方法的模拟，这些是Mockito不支持的。  
（2）Mock中的几个概念  
Mock，在测试过程中，对于某些不容易构造或者不容易获取的对象，用一个虚拟的对象来模拟替代。  
Sub，自定义一个对象中某个方法的返回值，也可以返回异常。  
Spy，即partial mock，默认调用真实方法，如果某方法被stub,则调用stub后的方法。
