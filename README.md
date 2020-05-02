# spring_boot_skeleton

<h4>Technologies</h4>
<ul>
    <li><b>Language : </b>Java</li>
    <li><b>Framework : </b>Spring Boot, JQuery, Bootstrap</li>
    <li><b>ORM : </b>Hibernate, JPA (Spring Data JPA)</li>
    <li><b>Database : </b>MYSQL</li>
    <li><b>Build Tool : </b>Maven</li>
    <li><b>Web Security : </b> Spring Security, Oauth2</li>
    <li><b>Social Integration : </b> Facebook and Google</li>
    <li><b>Logger : </b> Log4j</li>
    <li><b>Modal : </b> DTO</li>
    <li><b>Pattern : </b> MVC</li>
</ul>

<h4>Auto CRUD</h4>
<ul>
    <li><b>Command : </b>python3 auto_crud.py -e "entity name" -p "package name"</li>
    <li>
    Following classes are automatically generated.
        <ul>
            <li>Repository</li>
            <li>Dto</li>
            <li>Converter</li>
            <li>Service</li>
            <li>ServiceImpl</li>
            <li>Error</li>
            <li>Validation</li>
            <li>Controller</li>
            <li>Filter</li>
            <li>RestController</li>
        </ul>
    </li>
</ul>

<h4>Project Structure</h4>
<ul>
    <li>
    src
    <ul>
        <li>
        <a href="https://github.com/dhirajbadu/spring_boot_skeleton/tree/master/src/main" >main</a>
            <ul>
                <li>
                <a href="https://github.com/dhirajbadu/spring_boot_skeleton/tree/master/src/java" >java</a>
                    <ul>
                        <li>
                        <a href="https://github.com/dhirajbadu/spring_boot_skeleton/tree/master/src/main/java/com/realestate/re/config" >config</a><br>
                        </i>All server side config classes</i>
                        </li>
                        <li>
                        <a href="https://github.com/dhirajbadu/spring_boot_skeleton/tree/master/src/main/java/com/realestate/re/service">service</a>
                           <ul>
                            <li>
                            <a href="https://github.com/dhirajbadu/spring_boot_skeleton/tree/master/src/main/java/com/realestate/re/service/common">common</a><br>
                            <i>All the classes which are used multiple times in other class, means common classes</i>
                            </li>
                            <li>
                            <a href="https://github.com/dhirajbadu/spring_boot_skeleton/tree/master/src/main/java/com/realestate/re/service/core">core</a><br>
                            <i>All the classes which contains core business logics</i>
                            </li>
                            <li>
                            <a href="https://github.com/dhirajbadu/spring_boot_skeleton/tree/master/src/main/java/com/realestate/re/service/web">web</a><br>
                            <i>Controller, session, RestFull Api, etc</i>
                            </li>
                           </ul>
                        </li>
                    </ul>
                </li>
                <li>
                <a href="https://github.com/dhirajbadu/spring_boot_skeleton/tree/master/src/resources" >resources</a><br>
                <i>server side resorce and config files</i>
                </li>
                <li>
                <a href="https://github.com/dhirajbadu/spring_boot_skeleton/tree/master/src/webapp" >webapp</a>
                <br>
                 <i>web pages(JSP) and resources(JS,CSS,etc)</i>
                 </li>
            </ul>
        </li>
        <li>
         Test
        </li>
    </ul>
    </li>
</ul>