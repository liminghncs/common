# common
api basic library

1.配置github为maven仓库
 <repositories>
        <repository>
            <id>mvn-repo</id>
            <url>https://raw.githubusercontent.com/liminghncs/common/repo/</url>
            <snapshots>
                <enabled>true</enabled>
                <updatePolicy>always</updatePolicy>
            </snapshots>
        </repository>
    </repositories>

2.添加依赖项

        <dependency>
            <artifactId>com.tanghuzi.common</artifactId>
            <groupId>com.tanghuzi</groupId>
            <version>1.0</version> 
        </dependency>