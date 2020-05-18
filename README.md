环境为 IDEA Maven Web 项目。

使用 Tomcat 部署服务器

完全使用 MVC 设计模式

- `M`：Model	    业务数据：service（处理业务逻辑）、repository（与数据库交互）、entity（将从数据库中得到的数据封装成 Java 对象）
- `V`：View          视图。用户可以看到的东西：HTML
- `C`：Controller  控制。Servlet、Handler、Action

请求进入 Java Web 应用后，Controller 接收该请求，进行业务逻辑处理，最终将处理结果的结果 （Model） 返回给用户（View）![image-20200519004738585](README.assets/image-20200519004738585.png)

并使用 git 进行版本控制。