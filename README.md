此项目为 IDEA Maven Web 项目。

使用 Tomcat 部署服务器

完全使用 MVC 设计模式

- `M`：Model	    业务数据：service（处理业务逻辑）、repository（与数据库交互）、entity（将从数据库中得到的数据封装成 Java 对象）
- `V`：View          视图。用户可以看到的东西：HTML
- `C`：Controller  控制。Servlet、Handler、Action

请求进入 Java Web 应用后，Controller 接收该请求，进行业务逻辑处理，最终将处理结果的结果 （Model） 返回给用户（View）![image-20200519004738585](README.assets/image-20200519004738585.png)

并使用 git 进行版本控制。

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



### debug

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

