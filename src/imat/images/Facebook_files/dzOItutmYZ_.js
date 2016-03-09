/*!CK:152860210!*//*1457384802,*/

if (self.CavalryLogger) { CavalryLogger.start_js(["NAD6x"]); }

__d('FBRTCSoundController',['RTCConfig','Sound'],function a(b,c,d,e,f,g){if(c.__markCompiled)c.__markCompiled();var h=[c('RTCConfig').ringtone_mp3_url,c('RTCConfig').ringtone_ogg_url],i={playIncomingRingtone:function(j,k,l){var m=['incoming_ringtone',j.toString(),k.toString()].join('_');c('Sound').play(h,m,l);},stopIncomingRingtone:function(){c('Sound').stop(h);}};f.exports=i;},null);