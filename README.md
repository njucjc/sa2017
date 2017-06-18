#  工程概述
## 说明
*  这是sa2017的课程大实验，大实验要求[点这里](https://github.com/njuics/sa2017/wiki/%E8%AF%BE%E7%A8%8B%E4%BD%9C%E4%B8%9A)
## 概述
* 运用Spring框架搭建了一个SA课程的课程管理系统，系统采用前后端分离设计，前端采用[jquery-ui-1.12.1](http://jqueryui.com/changelog/1.12.1/) 通过ajax与后端通信进行CURD操作;
*  后端系统采用3-tiers架构与MVC结合的架构风格，实现repository、service、controller等beans；
*  后端采用Spring框架设计，实现restful风格API接口，以Spring Boot启动服务；
*  导入功能需采用Spring Batch技术进行从excel文件提取数据、处理输入和入库等流程
*  .......

## 运行程序
```
	1. git clone https://github.com/njucjc/sa2017.git
	2. 运行IntelJ IDEA打开工程，运行主类CourseApplication
	3. 进入webapp子目录用浏览器打开index.html即可
	4. 注意在使用导入功能时要使用与工程给出的students.xslx格式相同
```
## 参考资料
* [Spring Boot + Spring MVC + Spring Security + MySQL](https://medium.com/@gustavo.ponce.ch/spring-boot-spring-mvc-spring-security-mysql-a5d8545d837d)
* [SpringMvc+AngularJS通过CORS实现跨域方案](http://www.tuicool.com/articles/umymmqY)
* [Spring Batch Tutorial: Reading Information From an Excel File](https://www.petrikainulainen.net/programming/spring-framework/spring-batch-tutorial-reading-information-from-an-excel-file/)
*  [spring-petclinic-rest](https://github.com/spring-petclinic/spring-petclinic-rest)

