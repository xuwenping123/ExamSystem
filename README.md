# ExamSystem

基于play-1.2.7实现的简单的考试系统。

###实现的功能有：

- student teacher 进行系统的登录 注销
- teacher 组织考试科目、试题
- teacher 发布考试，并选择student参加考试
- teacher 批改试卷，查阅考生成绩
- student 参加考试，查阅考试成绩

###系统开发环境

- jdk1.7
- play-1.2.7
- mysql5.6.26

###部署开发环境

- 将系统导入到Eclipse中
- 将<strong>test/sql</strong>下数据库导入到本地数据库中
- 配置<strong>conf/application.conf</strong>中数据库连接项: db=mysql://root:pwd@127.0.0.1:3306/examsystem
- 安装play1.2.7开发环境，在项目中导入play-1.2.7开发jar包，包括play-1.2.7.jar
- 配置启动命令，在ExamSystem.launch中依据实际play安装位置填写<strong>E:\Exam\play-1.2.7\framework</strong>的值


	<stringAttribute key="org.eclipse.jdt.launching.VM_ARGUMENTS" value="-Xdebug -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=n -Dplay.debug=yes -Dplay.id= -Dapplication.path=&quot;${project_loc:ExamSystem}&quot; -Djava.endorsed.dirs=&quot;E:\Exam\play-1.2.7/framework/endorsed&quot; -javaagent:&quot;E:\Exam\play-1.2.7\framework/play-1.2.7.jar&quot;"/>

###启动服务调试

- 右击ExamSystem.launch进行Run启动，开启浏览器即可进行访问 <strong>http://localhost:9000/</strong>
![](https://i.imgur.com/3BSdnuW.png)
- 右击ExamSystem.launch进行Run启动，再右击Connect JPDA to ExamSystem.launch进行Debug启动，即可进入调试模式