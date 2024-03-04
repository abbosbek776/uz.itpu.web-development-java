1. Business logic is same everywhere: all the differences are only within context setup approach

2. Check code in package old and class OldMain

3. Check code in package springxml and class SpringXmlMain, which is same as old but using beans.xml

4. Check code in package springjava and class SpringJavaMain same as old but using AppConfig

5. Check code in package springannotation and class SpringAnnotationMain same as old but using Annotations
   1. Check SpringAnnotationMain.class line 11 notes. Solve issue by uncommenting one of constructor 
   2. or uncomment @Primary at Dao

6. Check code in package springspecifics and class SpringSpecificsMain:
   - init and destroy options
   - properties file, @PropertySource("classpath:/application.properties"), @Value("${my.custom.prop.1}")
   
7. Spring bean scopes ( @Scope("singleton") ):
    - singleton
    - prototype
    - request
    - session
    - application
    - webSocket

8. ...
