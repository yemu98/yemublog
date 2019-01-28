<?php
$servername = "localhost";
$username = "root";
$password = "sogood.123";
$dbname='yemu_blog';
// 创建连接
try {
    @$conn = new mysqli($servername, $username, $password,$dbname);//@隐藏错误提示
    if(mysqli_connect_errno()){    //判断是否成功连接上MySQL数据库
        throw new Exception('数据库连接错误！');  //如果连接错误，则抛出异常
    }else{
        mysqli_set_charset($conn,'utf8');//设置编码方式为utf8
        return $conn;
    }
} catch (Exception $e) {
    echo $conn->connect_error;    //打印异常信息
}

// 检测连接
// if ($conn->connect_error) {
//     die("连接失败: " . $conn->connect_error);
// }
return $conn;
?>