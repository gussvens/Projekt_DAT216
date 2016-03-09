/*!CK:1000808491!*//*1457535616,*/

if (self.CavalryLogger) { CavalryLogger.start_js(["LlAey"]); }

__d('FBRTCIncomingCallController',['fbt','Bootloader','Cache','DocumentTitle','FBRTCCallBlockingStore','FBRTCCallSummary','FBRTCCallSummaryStore','FBRTCCallUI','FBRTCConstants','FBRTCLocalMessageQueue','FBRTCLogger','FBRTCMessage','FBRTCMessageDedup','FBRTCMessageSender','FBRTCScreenSharingActions','FBRTCUtils','Map','MercuryIDs','MercuryParticipants','UserActivity','FBRTCSupport'],function a(b,c,d,e,f,g,h){if(c.__markCompiled)c.__markCompiled();var i=30;function j(k,l,m){'use strict';this.$FBRTCIncomingCallController1=k;this.$FBRTCIncomingCallController2=l;this.$FBRTCIncomingCallController3=m;this.$FBRTCIncomingCallController4=null;this.$FBRTCIncomingCallController5=null;this.$FBRTCIncomingCallController6=new (c('FBRTCMessageSender'))();this.$FBRTCIncomingCallController7=new (c('FBRTCLocalMessageQueue'))();this.$FBRTCIncomingCallController8=c('FBRTCCallSummaryStore').getInstance();this.$FBRTCIncomingCallController9=c('FBRTCLogger').getInstance();this.$FBRTCIncomingCallController10=null;this.$FBRTCIncomingCallController11=new (c('Cache'))();}j.prototype.onMessageReceived=function(k){'use strict';var l;try{l=new (c('FBRTCMessage'))(k);}catch(m){this.$FBRTCIncomingCallController9.logErrorWithoutID('Unknown data: '+JSON.stringify(k));return;}if(!c('FBRTCMessageDedup').check(l.peerID,l.callID,l.msgID)){if(l.isOffer()&&this.$FBRTCIncomingCallController12(l))this.$FBRTCIncomingCallController6.sendOfferAck(l.peerID,l.callID,l.msg);this.$FBRTCIncomingCallController9.logInfo(l.peerID,l.callID,'Ignoring message (duplicate): '+l.msgID);return;}this.$FBRTCIncomingCallController9.logReceivedMessage(l.peerID,l.callID,l.msg);if(c('FBRTCSupport').isWebrtcSupported()&&l.msgType!==c('FBRTCConstants').PayloadType.OFFER)this.$FBRTCIncomingCallController7.enqueueMessage(l.peerID,l.callID,l.msgID,k);switch(l.msgType){case c('FBRTCConstants').PayloadType.OFFER:this.$FBRTCIncomingCallController13(l);break;case c('FBRTCConstants').PayloadType.HANGUP:this.$FBRTCIncomingCallController14(l);break;case c('FBRTCConstants').PayloadType.OTHER_DISMISS:this.$FBRTCIncomingCallController15(l);break;case c('FBRTCConstants').PayloadType.SDP_UPDATE:this.$FBRTCIncomingCallController16(l);break;case c('FBRTCConstants').PayloadType.OFFER_ACK:this.$FBRTCIncomingCallController17(l);break;case c('FBRTCConstants').PayloadType.ICE_CANDIDATE:case c('FBRTCConstants').PayloadType.ANSWER:case c('FBRTCConstants').PayloadType.MSG_ACK:case c('FBRTCConstants').PayloadType.OK:case c('FBRTCConstants').PayloadType.PING:case c('FBRTCConstants').PayloadType.PRANSWER:case c('FBRTCConstants').PayloadType.ICERESTART_OFFER:case c('FBRTCConstants').PayloadType.ICERESTART_ANSWER:case c('FBRTCConstants').PayloadType.PCRESTART_ANSWER:case c('FBRTCConstants').PayloadType.PCRESTART_OFFER:case c('FBRTCConstants').PayloadType.ANSWER_ACK:case c('FBRTCConstants').PayloadType.SET_VIDEO:case c('FBRTCConstants').PayloadType.OFFER_NACK:case c('FBRTCConstants').PayloadType.VIDEO_REQUEST:case c('FBRTCConstants').PayloadType.ACK:break;default:break;}};j.prototype.$FBRTCIncomingCallController13=function(k){'use strict';var l=k.callID,m,n=k.msg,o=k.peerID,p=n.attributes&&n.attributes.hasOwnProperty('isDirectScreenShareFromRecipient');if(this.$FBRTCIncomingCallController18(l))return;if(this.$FBRTCIncomingCallController12(k)){var q=p?new (c('Map'))([['isDirectScreenShareFromRecipient',true]]):new (c('Map'))();this.$FBRTCIncomingCallController6.sendOfferAck(o,l,n,q);}else if(!c('FBRTCSupport').isWebrtcSupported()&&!p)this.$FBRTCIncomingCallController6.sendOfferNack(o,l,n);this.$FBRTCIncomingCallController9.logCallAction(o,l,c('FBRTCLogger').CallAction.RECEIVED_CALL);var r=this.$FBRTCIncomingCallController19(o,l,k);if(c('FBRTCCallBlockingStore').getBlockingStatus()){this.$FBRTCIncomingCallController20(o,l,r,c('FBRTCConstants').CallEndReason.IGNORE_CALL,false,false,false,'calling disabled');return;}if(this.$FBRTCIncomingCallController4!==null&&!this.$FBRTCIncomingCallController4.isForPeer(o)){this.$FBRTCIncomingCallController20(o,l,r,c('FBRTCConstants').CallEndReason.IN_ANOTHER_CALL,false,false,true);this.$FBRTCIncomingCallController3.onCallMissed(o);return;}if(!c('FBRTCSupport').isWebrtcSupported()){this.$FBRTCIncomingCallController20(o,l,r,c('FBRTCConstants').CallEndReason.UNSUPPORTED_VERSION,false,false,false);this.$FBRTCIncomingCallController2.showForIncomingCall(l,o);return;}this.$FBRTCIncomingCallController4=new this.$FBRTCIncomingCallController1(o,l);this.$FBRTCIncomingCallController5=r;this.$FBRTCIncomingCallController4.addListener('answerCall',function(s){this.$FBRTCIncomingCallController21(o,l,k,r,s);}.bind(this));this.$FBRTCIncomingCallController4.addListener('ignoreCall',function(){this.$FBRTCIncomingCallController22(o,l,r);}.bind(this));this.$FBRTCIncomingCallController4.addListener('timeout',function(){this.$FBRTCIncomingCallController23(o,l,r,p,!p);}.bind(this));this.$FBRTCIncomingCallController3.hideDialog();if(p){m=c('FBRTCConstants').IncomingCallDialogTypes.SCREEN_SHARE;}else m=n.videoon?c('FBRTCConstants').IncomingCallDialogTypes.VIDEO:c('FBRTCConstants').IncomingCallDialogTypes.AUDIO;this.$FBRTCIncomingCallController4.showIncomingDialog(m);this.$FBRTCIncomingCallController9.logEvent(o,l,'Incoming call dialog shown');this.$FBRTCIncomingCallController24(o);};j.prototype.$FBRTCIncomingCallController17=function(k){'use strict';var l=k.msg.hasOwnProperty('isDirectScreenShareFromRecipient');if(l||c('FBRTCUtils').isCollabCallable(k.peerID))c('FBRTCScreenSharingActions').offerAckReceived();};j.prototype.$FBRTCIncomingCallController24=function(k){'use strict';if(!document.hasFocus())this.$FBRTCIncomingCallController25(k);};j.prototype.$FBRTCIncomingCallController26=function(){'use strict';this.$FBRTCIncomingCallController27();};j.prototype.$FBRTCIncomingCallController25=function(k){'use strict';var l=c('MercuryIDs').getParticipantIDFromUserID(k);c('MercuryParticipants').get(l,function(m){if(!this.$FBRTCIncomingCallController4)return;this.$FBRTCIncomingCallController10=c('DocumentTitle').blink(h._("{name} is calling",[h.param('name',m.short_name)]));}.bind(this));c('UserActivity').subscribeOnce(this.$FBRTCIncomingCallController27.bind(this));};j.prototype.$FBRTCIncomingCallController27=function(){'use strict';if(this.$FBRTCIncomingCallController10){this.$FBRTCIncomingCallController10.stop();this.$FBRTCIncomingCallController10=null;}};j.prototype.$FBRTCIncomingCallController28=function(){'use strict';if(this.$FBRTCIncomingCallController4)this.$FBRTCIncomingCallController4.cancel();this.$FBRTCIncomingCallController4=null;this.$FBRTCIncomingCallController5=null;this.$FBRTCIncomingCallController26();};j.prototype.$FBRTCIncomingCallController14=function(k){'use strict';var l=k.peerID,m=k.callID,n=k.msg;this.$FBRTCIncomingCallController29(m);if(this.$FBRTCIncomingCallController4&&this.$FBRTCIncomingCallController4.isForPeerAndCall(l,m)){var o=n.reason;if(typeof o=='string'||o instanceof String)o=c('FBRTCConstants').endCallReasonFromString(o);this.$FBRTCIncomingCallController20(l,m,this.$FBRTCIncomingCallController5,o,true,true,false);this.$FBRTCIncomingCallController28();if(o!==c('FBRTCConstants').CallEndReason.OTHER_INSTANCE_HANDLED){this.$FBRTCIncomingCallController3.onCallMissed(l);this.$FBRTCIncomingCallController3.showDialog();}}if(this.$FBRTCIncomingCallController30)this.$FBRTCIncomingCallController30.onDirectScreenSharingEndSession();};j.prototype.$FBRTCIncomingCallController12=function(k){'use strict';return c('FBRTCSupport').isWebrtcSupported();};j.prototype.$FBRTCIncomingCallController15=function(k){'use strict';var l=k.callID;this.$FBRTCIncomingCallController29(l);this.$FBRTCIncomingCallController2.dismiss();if(this.$FBRTCIncomingCallController4&&this.$FBRTCIncomingCallController4.isForCall(l))this.$FBRTCIncomingCallController28();};j.prototype.$FBRTCIncomingCallController29=function(k){'use strict';this.$FBRTCIncomingCallController11.set(k,true,null,i);};j.prototype.$FBRTCIncomingCallController18=function(k){'use strict';return this.$FBRTCIncomingCallController11.has(k);};j.prototype.$FBRTCIncomingCallController19=function(k,l,m){'use strict';var n=new (c('FBRTCCallSummary'))({peerID:k,callID:l,isCaller:false});n.onFullMessageReceived(m);n.onOfferAckSent(m.msg);return n;};j.prototype.$FBRTCIncomingCallController21=function(k,l,m,n,o){'use strict';if(m.msg.attributes&&m.msg.attributes.hasOwnProperty('isDirectScreenShareFromRecipient')){this.$FBRTCIncomingCallController31(k,l,m.msg.sdp);}else{var p=!m.msg.videoon;this.$FBRTCIncomingCallController9.logCallAction(k,l,c('FBRTCLogger').CallAction.ANSWER_CALL);this.$FBRTCIncomingCallController6.sendOtherDismiss(l);this.$FBRTCIncomingCallController7.enqueueOffer(k,m.originalMessageData);c('FBRTCCallUI').openAsCallee(k,l,n,o,p);}this.$FBRTCIncomingCallController28();};j.prototype.$FBRTCIncomingCallController31=function(k,l,m){'use strict';c('Bootloader').loadModules(["FBRTCScreenSharingController"],function(n){this.$FBRTCIncomingCallController30=new n(k,l,true);this.$FBRTCIncomingCallController30.initPromise.done(function(){this.$FBRTCIncomingCallController30.initializeAsReceiver(m,this.$FBRTCIncomingCallController7);}.bind(this));}.bind(this));};j.prototype.$FBRTCIncomingCallController22=function(k,l,m){'use strict';this.$FBRTCIncomingCallController20(k,l,m,c('FBRTCConstants').CallEndReason.IGNORE_CALL,false,true,true);this.$FBRTCIncomingCallController28();};j.prototype.$FBRTCIncomingCallController23=function(k,l,m,n,o){'use strict';this.$FBRTCIncomingCallController20(k,l,m,c('FBRTCConstants').CallEndReason.NO_ANSWER_TIMEOUT,false,false,n);this.$FBRTCIncomingCallController28();if(o){this.$FBRTCIncomingCallController3.onCallMissed(k);this.$FBRTCIncomingCallController3.showDialog();}};j.prototype.$FBRTCIncomingCallController20=function(k,l,m,n,o,p,q){var r=arguments.length<=7||arguments[7]===undefined?null:arguments[7];'use strict';if(q)this.$FBRTCIncomingCallController6.sendHangup(k,l,n);if(p)this.$FBRTCIncomingCallController6.sendOtherDismiss(l);m.onCallEnded(n,o,null,r);m.save(this.$FBRTCIncomingCallController8);var s=c('FBRTCConstants').fullCallEndReasonString(n,o);this.$FBRTCIncomingCallController9.logCallAction(k,l,c('FBRTCLogger').CallAction.END_CALL,s);};j.prototype.$FBRTCIncomingCallController16=function(k){'use strict';var l=k.msg,m=l.attributes&&l.attributes.hasOwnProperty('isDirectScreenShareFromRecipient');if(m&&this.$FBRTCIncomingCallController30)this.$FBRTCIncomingCallController30.onSdpUpdateReceived(l.sdp);};f.exports=j;},null);