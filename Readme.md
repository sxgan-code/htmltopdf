# 项目说明

该项目基于openhtmltopdf插件，官方地址：https://github.com/danfickle/openhtmltopdf

该项目为Freemarker模板([test.ftl](resources/temp/test.ftl))与数据模型([rootData.json](resources/data/rootData.json))生成中间Html，通过openhtmltopdf插件将生成的HTML转换为PDF(无法直接由HTML转PDF,如果需要请使用[Release](Release)目录下的工具进行生成)

## 源码说明
启动类：[HtmlToPdfApplication.java](src/main/java/com/daniel/fm/HtmlToPdfApplication.java)

访问：http://localhost:9090/create 即可生成PDF


## [resources](resources)目录说明
[assets](resources/assets)：assets为静态图片目录

[fonts](resources/fonts)：fonts为字体目录，注意有些字体不支持中文，如果中文字体强行使用不支持中文的字体或不使用字体，生成的PDF则会显示为`#`号

[data/rootData.json](resources/data/rootData.json)：rootData.json为数据模型文件名称为固定写法

[temp/test.ftl](resources/temp/test.ftl)：该文件为ftl模板文件

[html/new.html](resources/html/new.html)：该文件为ftl模板文件转PDF的中间文件，程序运行会自动创建删除覆盖

[pdf](resources/pdf)：pdf为生成的PDF文件目录



## [Release](Release)目录工具说明
Release目录下提供了Html直接转Pdf的工具，具体请查看:[PDF转换工具说明.md](Release/PDF转换工具说明.md)