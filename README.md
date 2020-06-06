**注意：由于为Maven Web项目，因此若想运行后端源码，必须使用 IDEA 下的 Maven Web 工程，并运行 Tomcat （推荐版本9.0.33）才可以正常运行整个 web 项目**

此项目为 IDEA Maven Web 项目。

系统实现功能：

1. 正常餐品结算和找零。

2. 基本套餐结算和找零。

3. 使用优惠劵购买餐品结算和找零。

4. 可在一定时间段参与店内活动（自行设计或参考官网信息）。

5. 模拟打印小票的功能（写到文件中）。

基本要求：

1. 程序设计风格良好，控制台界面友好，最多两人一组完成任务。

2. 实现功能测试代码，确保程序的健壮性。（测试已完成）

3. 画出使用的设计模式图。（MVC已画完）

提高要求：

1. 实现可视化界面（使用MFC）。

2. 实现会员储值卡功能，完成储值卡消费。

3. 实现当天营业额和餐品销量计算和统计，用数据库记录。



### 涉及技术：

前端：HTML、CSS、JavaScript、jQuery、JSON

后端：Java、Tomcat、Gson、JDBC、c3p0数据库连接池技术、Maven项目管理

数据库：MySQL

设计模式：MVC设计模式、构造函数模式、原型模式

版本控制：git



完全使用 MVC 设计模式

- `M`：Model	    业务数据：service（处理业务逻辑）、repository（与数据库交互）、entity（将从数据库中得到的数据封装成 Java 对象）
- `V`：View          视图。用户可以看到的东西：HTML
- `C`：Controller  控制。Servlet、Handler、Action

请求进入 Java Web 应用后，Controller 接收该请求，进行业务逻辑处理，最终将处理结果的结果 （Model） 返回给用户（View）![image-20200519004738585](README.assets/image-20200519004738585.png)

使用 git 进行版本控制。![image-20200606144350212](README.assets/image-20200606144350212.png)



在 order.js 中使用了许多的变量来表示用户选择的各种食物的数量：![](README.assets/oldFoodNum-1591425534460.png)

这些变量都是表示食物数量 这个功能范围内，因此，可以封装成一个对象。使用 JavaScript 中创建对象的两种著名的设计模式：`构造函数模式` 和 `原型模式`。![](README.assets/newFoodNum-1591425715198.png)

## 功能展示

输入 locahost:8080后便可以进入 肯德基 主界面：

![image-20200606150220278](README.assets/image-20200606150220278.png)

鼠标移至左边框后，即可激活展开动画：![image-20200606150454678](README.assets/image-20200606150454678.png)![image-20200606150513796](README.assets/image-20200606150513796.png)

中间活动图像使用轮播图：![image-20200606150552428](README.assets/image-20200606150552428.png)![image-20200606150559805](README.assets/image-20200606150559805.png)

在未登录状态下进入 “开始点餐”页面后，会提示用户注册账号或者登录：![image-20200606150829833](README.assets/image-20200606150829833.png)

登录页面 login.html 使用了 tab 选项卡，分别完成 注册 与 登录 两个业务。

在注册账号选项卡中，会检测用户的输入信息格式是否正确：![image-20200606151107253](README.assets/image-20200606151107253.png)

若手机号已被注册（在数据库中已存在相应手机号），会提示用户更换手机号：![image-20200606151225321](README.assets/image-20200606151225321.png)

若注册成功，则会提示用户使用账号登录：![image-20200606151433229](README.assets/image-20200606151433229.png)

登录：![image-20200606151523486](README.assets/image-20200606151523486.png)

账号登录成功后，会跳转至 order.html 页面，并在左上角展示用户账号信息：![image-20200606151642479](README.assets/image-20200606151642479.png)

在 order.html 中展示了**商品活动**信息，在15 ：17这个时间段香辣鸡翅 八折销售，所以购买香辣鸡翅价格为 9.6 元。![image-20200606151921124](README.assets/image-20200606151921124.png)

![image-20200606151904583](README.assets/image-20200606151904583.png)

点击购买后，后端数据库中会记录此次购买信息(实现当天营业额和餐品销量计算和统计，用数据库记录。)：![image-20200606152015758](README.assets/image-20200606152015758.png)

![image-20200606152148909](README.assets/image-20200606152148909.png)

并且，会产生一份账单文件（文件位置：本机的 Tomcat 安装路径下的bin/receipts），记录客户账单信息：![image-20200606152346990](README.assets/image-20200606152346990.png)

![image-20200606152405322](README.assets/image-20200606152405322.png)

若使用数据库 coupon 表中的特殊账号![image-20200606152941019](README.assets/image-20200606152941019.png)

登录 order.html 网页，便可以使用优惠券九折购买，在coupon表中没有添加的账号是不会显示这个优惠券购买功能的。

![image-20200606153124934](README.assets/image-20200606153124934.png)

![image-20200606153151751](README.assets/image-20200606153151751.png)



*********

## 测试

Java 项目在开发过程中一种常见测试类的方法是在被测试的类中写 main 方法，在 main 方法中创建一个 被测试类 即可验证 被测试类 的各种方法。测试获得的用户订单时间 date 转成数据库变量时格式是否正确

```java
public class BuyServiceImpl implements BuyService {

    private OrderRepository orderRepository = new OrderRepositoryImpl();

    @Override
    public void customersBuy(Integer chickenWing, Integer chickenWingSetMeal, Integer beer, Integer hamburger, Integer congee, Integer cola) {
        Date date = new Date(); // 获取当前客户购买时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String boughtTime = simpleDateFormat.format(date);
        System.out.println(boughtTime);
        //orderRepository.addOrder(chickenWing, chickenWingSetMeal, beer, hamburger, congee, cola, boughtTime);
    }

    public static void main(String[] args) {
        BuyService buyService = new BuyServiceImpl();
        buyService.customersBuy(1, 1, 1,1,1,1);
    }
}
```

控制台输出结果格式与数据库中的 datetime 变量格式一样，没有错误。![image-20200520154804060](README.assets/image-20200520154804060.png)



### 测试不同时间段是否能得到正确的折扣食品

在 BuyServiceImpl 类中 方法中临时增加一行控制台输出以及注释与数据库的连接，来测试能否获得当前时间段中折扣物品的名称。![image-20200526165341453](README.assets/image-20200526165341453.png)



在 BuyServiceImpl 类中的 main 方法中新建 BuyServiceImpl 类，并执行 addOrder 方法来检查商品名称。![image-20200526165439538](README.assets/image-20200526165439538.png)

运行后，控制台显示结果正确。![image-20200526165549042](README.assets/image-20200526165549042.png)

当前时间段的确为 chickenWing 打折：![image-20200526165710642](README.assets/image-20200526165710642.png)



## debug

登陆界面写好后，测试正确账号与密码能否正常跳转至 order.html 。发现无法进入。![image-20200521142109517](README.assets/image-20200521142109517.png)

原因使 loginServlet 服务器请求被取消。

将 login.html 中的 form 表单中的服务器响应地址action 修改后即可解决问题。

修改前：![image-20200521142320419](README.assets/image-20200521142320419.png)

修改后：![image-20200521142401315](README.assets/image-20200521142401315.png)

成功进入跳转页面：![image-20200521142735105](README.assets/image-20200521142735105.png)



### Debug2

在注册界面注册过程中发现一个注册异常现象，注册账号数据库中并不存在，但是却注册失败。![image-20200522164756801](README.assets/image-20200522164756801.png)

数据库中并没有这个账号：![image-20200522164903051](README.assets/image-20200522164903051.png)

在浏览器按下 F12，查看传入后端数据，发现数据传送正确，所以应该是后端出现了 bug。![image-20200522165017554](README.assets/image-20200522165017554.png)

![image-20200522165105908](README.assets/image-20200522165105908.png)

在后端，打开 IDEA 的 DEBUG 功能。为确定 bug 位置，首先在 RegisterServlet 这个接收客户端数据和请求的服务器中打断点，检查是哪条语句没有达到预期效果。

![image-20200522165651670](README.assets/image-20200522165651670.png)

结果发现 customer 值返回为 null。![image-20200522165740271](README.assets/image-20200522165740271.png)

这说明与数据库直接进行操作的 customerRepository 类中出现了 bug。再在 customerRepository 中打断点进一步判断 bug 位置。![image-20200522165947298](README.assets/image-20200522165947298.png)

结果发现 执行 preparedStatement.executeUpdate(); 语句后游标直接跳到了 catch 捕获异常模块中：![image-20200522170357066](README.assets/image-20200522170357066.png)

![image-20200522170507612](README.assets/image-20200522170507612.png)



说明异常一定是因为与 mysql 数据库直接交互过程中发生了错误。查看抛出的异常信息：![image-20200522170655535](README.assets/image-20200522170655535.png)

原来是因为用户的姓名太长！

查看 mysql 数据库 customer 表，发现存储的 name 变量字符串长度最多只能是 10 个字符长度！！![image-20200522170902753](README.assets/image-20200522170902753.png)

更改长度后即可解决此 bug。

![image-20200522170943768](README.assets/image-20200522170943768.png)

![image-20200522171011036](README.assets/image-20200522171011036.png)

用户注册正常，bug 解决。![image-20200522171122117](README.assets/image-20200522171122117.png)

### Debug3

在后端开发过程即将完成时，突然无法使用账号登录网页，在后端使用断点后发现是因为 c3p0 无法获得 数据库连接。查看异常日志，显示异常信息：

**APPARENT DEADLOCK!!! Creating emergency threads for unassigned pending task!**

花了两天时间，终于在一篇博客中找到了解决方案(csdn)[https://blog.csdn.net/abcyyjjkk/article/details/70240412]

修改 c3p0-config.xml 配置文件中的属性为：![](README.assets/c3p0xml-1591425450783.png)

重启 Tomcat 服务器后，终于可以取得 connection 对象，登录成功！bug解决![image-20200606142319188](README.assets/image-20200606142319188.png)

