<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Title</title>
    </head>
    <style type="text/css">
        .box{
            text-align: center;
            width: 100%;
            height: 100%;

        }
        .logo{
            width: 200px;
            height: 200px;
            margin: 0 auto;
        }
        .zh-msg{
            font-family: "阿里巴巴普惠体";
            font-size: 50px;
        }
        .en-msg{
            font-family: "HarmonyOS Sans Light";
            font-size: 50px;
        }
    </style>
    <body>
        <div class="box">
            <div class="zh-msg">${zhMsg}</div>
            <div class="en-msg">${enMsg}</div>
            <img class="logo" src="${basePath}/resources/assets/logo.png" alt="">
        </div>
    </body>
</html>