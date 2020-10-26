# 自动识别图片中的验证码
tess4j

## 效果


## 样例
1. 引入
```xml
<!-- https://mvnrepository.com/artifact/net.sourceforge.tess4j/tess4j -->
<dependency>
    <groupId>net.sourceforge.tess4j</groupId>
    <artifactId>tess4j</artifactId>
    <version>4.5.3</version>
</dependency>
```

2. 使用说明
http://tess4j.sourceforge.net/usage.html


## BUG

1. Please make sure the TESSDATA_PREFIX environment variable is set to your "tessdata" directory.

```java
ITesseract instance = new Tesseract();  // JNA Interface Mapping
//In case you don't have your own tessdata, let it also be extracted for you
File tessDataFolder = LoadLibs.extractTessResources(DEST);
//Set the tessdata path
instance.setDatapath(tessDataFolder.getAbsolutePath());
```