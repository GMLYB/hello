## Maven的安装和配置步骤

以maven 3.6.1为例



### 1 下载地址

* 本人的GitHub：https://github.com/GMLYB/hello/blob/master/Maven/apache-maven-3.6.1-bin.zip



### 2 解压



### 3 配置环境变量

* 打开高级系统设置界面
  * 右击桌面的“此电脑”图标，点击“属性”，弹出系统窗口，然后点击“高级系统设置”
* 新建MAVEN_HOME，并将maven解压目录添加进去

![](..\Maven\images\配置环境变量1.jpg)

* 在Path中把MAVEN_HOME添加进去

> %MAVEN_HOME%\bin

![](..\Maven\images\配置环境变量2.jpg)

* 检验
  * Win + R，输入cmd
  * 在cmd窗口中键入`mvn -version`
* 步骤4~6为配置maven的setting.conf，已上传了一个现成的可以参考



### 4 配置本地仓库

* 在本地自己喜欢的地方创建一个文件夹，命名为repository（命名看自己喜欢）
* 打开maven的安装目录，选择conf文件夹中的setting.xml文件（建议先备份）
* 修改setting.xml

```xml
<!--找到下发的文字-->
<!-- localRepository
   | The path to the local repository maven will use to store artifacts.
   |
   | Default: ${user.home}/.m2/repository
  <localRepository>/path/to/local/repo</localRepository>
  -->
<!--添加本地仓库地址-->
  <localRepository>D:/Maven/repository</localRepository>
```



### 5 配置maven下载地址为阿里源

* setting.xml，操作步骤如上一个

```xml
  <mirrors>
    <!-- mirror
     | Specifies a repository mirror site to use instead of a given repository. The repository that
     | this mirror serves has an ID that matches the mirrorOf element of this mirror. IDs are used
     | for inheritance and direct lookup purposes, and must be unique across the set of mirrors.
     |
    <mirror>
      <id>mirrorId</id>
      <mirrorOf>repositoryId</mirrorOf>
      <name>Human Readable Name for this Mirror.</name>
      <url>http://my.repository.com/repo/path</url>
    </mirror>
     -->
	 <mirror>
		<id>alimaven</id>
		<name>aliyun maven</name>
		<url>http://maven.aliyun.com/nexus/content/groups/public/</url>
		<mirrorOf>central</mirrorOf>
	</mirror>
	<mirror>
		<id>alimaven</id>
		<mirrorOf>central</mirrorOf>
		<name>aliyun maven</name>
		<url>http://maven.aliyun.com/nexus/content/repositories/central/</url>
	</mirror>
  </mirrors>
```



### 6 设置Java编译和打包的版本

```xml
  <profiles>
    <!-- profile
     | Specifies a set of introductions to the build process, to be activated using one or more of the
     | mechanisms described above. For inheritance purposes, and to activate profiles via <activatedProfiles/>
     | or the command line, profiles have to have an ID that is unique.
     |
     | An encouraged best practice for profile identification is to use a consistent naming convention
     | for profiles, such as 'env-dev', 'env-test', 'env-production', 'user-jdcasey', 'user-brett', etc.
     | This will make it more intuitive to understand what the set of introduced profiles is attempting
     | to accomplish, particularly when you only have a list of profile id's for debug.
     |
     | This profile example uses the JDK version to trigger activation, and provides a JDK-specific repo.
    <profile>
      <id>jdk-1.4</id>

      <activation>
        <jdk>1.4</jdk>
      </activation>

      <repositories>
        <repository>
          <id>jdk14</id>
          <name>Repository for JDK 1.4 builds</name>
          <url>http://www.myhost.com/maven/jdk14</url>
          <layout>default</layout>
          <snapshotPolicy>always</snapshotPolicy>
        </repository>
      </repositories>
    </profile>
    -->
    <profile>
        <id>jdk-1.8</id>
        <activation>
            <activeByDefault>true</activeByDefault>
            <jdk>1.8</jdk>
        </activation>
        <properties>
            <maven.compiler.source>1.8</maven.compiler.source>
            <maven.compiler.target>1.8</maven.compiler.target>
            <maven.compiler.compilerVersion>1.8</maven.compiler.compilerVersion>
        </properties>
    </profile>

    <!--
     | Here is another profile, activated by the system property 'target-env' with a value of 'dev',
     | which provides a specific path to the Tomcat instance. To use this, your plugin configuration
     | might hypothetically look like:
     |
     | ...
     | <plugin>
     |   <groupId>org.myco.myplugins</groupId>
     |   <artifactId>myplugin</artifactId>
     |
     |   <configuration>
     |     <tomcatLocation>${tomcatPath}</tomcatLocation>
     |   </configuration>
     | </plugin>
     | ...
     |
     | NOTE: If you just wanted to inject this configuration whenever someone set 'target-env' to
     |       anything, you could just leave off the <value/> inside the activation-property.
     |
    <profile>
      <id>env-dev</id>

      <activation>
        <property>
          <name>target-env</name>
          <value>dev</value>
        </property>
      </activation>

      <properties>
        <tomcatPath>/path/to/tomcat/instance</tomcatPath>
      </properties>
    </profile>
    -->
  </profiles>
```



### 7 在IDEA中对maven进行配置

#### 7.1 配置当前工程

* file --> settings --> Build,Execution,Deployment --> Build Tools --> Maven
* 配置Maven home directory（Maven安装目录）、User setting file（用户的配置文件）、Local repository（本地仓库）
* 设置Maven --> Runner 
  * VM Options：-DarchetypeCatalog=internal（Maven项目创建时，会联网下载模板文件，比较大，使用-DarchetypeCatalog=internal ，取消下载，创建Maven项目速度快）
  * JRE：项目的JDK



#### 7.2 配置新工程

* file --> Other Setting --> Setting for New Projects --> Build,Execution,Deployment --> Build Tools --> Maven
* 剩下步骤和上面一致

