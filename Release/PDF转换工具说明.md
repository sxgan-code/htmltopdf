# 一、前言

该工具（`openhtml-demo-aio-1.0.4.jar`）是方便前端构建ui所提供的工具包，配合resources目录使用，目的是使前端通过编写html页面直接转换为PDF。

## 1、资源目录说明

科技赋能项目（aclife-boot）项目中，在`aclife-boot\resources\makesuggest`目录下的`assets`、`fonts`对应该文档所在目录的`resources`下的`assets`、`fonts`，如果要使用此工具还请与项目中的资源保持一致。

`\resources\assets`该目录对应`aclife-boot\resources\makesuggest\assets`

`\resources\fonts`该目录对应`aclife-boot\resources\makesuggest\fonts`

启动项目后`\resources\css\index.css`该文件为`\resources\test.html`页面所引用的对应的样式

## 2、开发人员注意事项

### 前端

前端人员可将`openhtml-demo-aio-1.0.4.jar`工具及`resources`目录复制到无中文路径，可使用`WebStorm`、`VsCode`编辑器打开`resources`目录进行页面开发

字体样式只能使用控制台中打印出的字体,注意有些字体不支持中文，如果中文字体强行使用不支持中文的字体或不使用字体，生成的PDF则会显示为`#`号

环境及启动方式附后

resourc目录为前端项目，在`test.html`中进行修改则会自动进行更新，生成的`pdf`在`resource`目录下: `result.pdf`

修改后请查看pdf，请按照pdf展示内容进行调整

如自动更新失败请在命令行手动更新：`直接回车`

如文件更新失败`请关闭pdf`重新生成后再打开

### 后端

前端将样式改好后，可通过改好的`\resources\test.html`文件将样式复制到项目

`aclife-boot\resources\makesuggest\templates\test.ftl`中，注意需要配合后端数据模型转换为`freemarker`模板，具体语法请查看官方文档：[Apache FreeMarker 手册](https://freemarker.apache.org/docs/dgui_quickstart_basics.html)



# 二、环境

要求系统环境安装`JDK8`

查看系统版本

```sh
D:\IDEA_PRO\FrontEnd\openhtml-demo>java -version
java version "1.8.0_341"
Java(TM) SE Runtime Environment (build 1.8.0_341-b10)
Java HotSpot(TM) 64-Bit Server VM (build 25.341-b10, mixed mode)
```

命令行显示如上内容则jdk配置生效

# 三、启动

`openhtml-demo-aio-1.0.4.jar`: html转pdf工具

解压后切换至目录下，使用`java -jar` 命令启动转换工具

```sh
D:\IDEA_PRO\FrontEnd\openhtml-demo>java -jar openhtml-demo-aio-1.0.4.jar
Adding font with path = 'D:\Company\Front\htmltopdf\Release\resources\fonts\HarmonyOS_Sans_Black.ttf', name = 'Harmon
yOS Sans Black', weight = 400, style = NORMAL
Adding font with path = 'D:\Company\Front\htmltopdf\Release\resources\fonts\HarmonyOS_Sans_Bold.ttf', name = 'Harmony
OS Sans', weight = 700, style = NORMAL
Adding font with path = 'D:\Company\Front\htmltopdf\Release\resources\fonts\HarmonyOS_Sans_Light.ttf', name = 'Harmon
yOS Sans Light', weight = 400, style = NORMAL
Adding font with path = 'D:\Company\Front\htmltopdf\Release\resources\fonts\HarmonyOS_Sans_Medium.ttf', name = 'Harmo
nyOS Sans Medium', weight = 400, style = NORMAL
Adding font with path = 'D:\Company\Front\htmltopdf\Release\resources\fonts\HarmonyOS_Sans_Regular.ttf', name = 'Harm
onyOS Sans', weight = 400, style = NORMAL
Adding font with path = 'D:\Company\Front\htmltopdf\Release\resources\fonts\HarmonyOS_Sans_Thin.ttf', name = 'Harmony
OS Sans Thin', weight = 400, style = NORMAL
当前字体文件目录：[D:\Company\Front\htmltopdf\Release/resources/fonts];如需增加字体请增加后重启应用
目前支持在CSS中使用的字体名称为：'HarmonyOS Sans Black', 'HarmonyOS Sans', 'HarmonyOS Sans Light', 'HarmonyOS Sans Me
dium', 'HarmonyOS Sans Thin'
即将处理的html文件：[D:\Company\Front\htmltopdf\Release/resources/test.html]
输入N回车结束，直接回车开始转换：
```

出现以上内容则启动成功





