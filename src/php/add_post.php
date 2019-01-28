<?php
$data = $_POST['data'];
// echo $data['headline'];
// echo $data['main_text'];
// echo json_encode($data,JSON_UNESCAPED_UNICODE);

if (strlen($data['main_text'])>300){//取前100个中文字符或300个英文数字字符 utf8中文占3个字节
$brief=substr($data['main_text'],0,300);
}
else{
    $brief=$data['main_text'];
}
// echo($brief);
include("conn.php");
$sql="insert into post (title,text,brief) values ('$data[headline]','$data[main_text]','$brief');";
if ($conn->query($sql) === TRUE) {
    // echo "插入成功";
    $id=mysqli_insert_id($conn);
    $date=get_date($id);
    $filename=create_post($data['headline'],$date,$data['main_text'],$id);
    $link="http://localhost/yemublog/src/post/".$filename;
    insert_link($link,$id);
    echo($link);
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}
$conn->close();
?>
<?php
function create_post($headline,$date,$text,$id){
    $filename=$id.".html";//命名文件
    $model=fopen("../post/post_model.html","r");//只读打开模板文件
    $html=fread($model,filesize("../post/post_model.html"));//读取模板文件
    fclose($model);//关闭模板文件
    $html=str_replace("{headline}",$headline,$html);//替换post信息
    $html=str_replace("{date}",$date,$html);
    $html=str_replace("{text}",$text,$html);
    $new_file=fopen("../post/$filename","w") or die("Unable to create file!");//创建文件
    fwrite($new_file,$html);//写入新文件
    fclose($new_file);
    return $filename;
}
function get_date($id){
include("conn.php");
$sql="select date from post where id=$id";
$result = $conn->query($sql);
if ($result->num_rows > 0) {
    while($row = $result->fetch_assoc()) {
        return $row["date"];
    }
} else {
    return "0000-00-00";
}
$conn->close();
}
function insert_link($link,$id){
    include("conn.php");
    $sql=" update post set link='$link' where id=$id;";
    if ($conn->query($sql) === TRUE) {
        return true;
    } else {
        echo "Error: " . $sql . "<br>" . $conn->error;
    }
    $conn->close();
}
?>