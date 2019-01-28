<?php
$filename="1";
$myfile = fopen("../../src/post/$filename.html", "w") or die("Unable to open file!");
$fp=fopen("../post_model.html","r"); //只读打开模板 
$str=fread($fp,filesize("../post_model.html"));//读取模板中内容 
$title="hedlinenain";
$content="textdadadd";
$str=str_replace("{headline}",$title,$str); 
$str=str_replace("{main_text}",$content,$str);//替换内容 
fclose($fp);  
fwrite($myfile,$str); //把刚才替换的内容写进生成的HTML文件 
fclose($myfile); 

?>
