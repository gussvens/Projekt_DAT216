/*!CK:946694289!*//*1456853148,*/

if (self.CavalryLogger) { CavalryLogger.start_js(["GmYqn"]); }

__d('MessengerComposeViewHeader.react',['cx','fbt','immutable','MessengerSearchSource','MessengerTabIndices','MessengerTokenizer.react','ReactComponentWithPureRenderMixin','React','clearImmediate','joinClasses','setImmediate'],function a(b,c,d,e,f,g,h,i){'use strict';if(c.__markCompiled)c.__markCompiled();var j=c('React').PropTypes,k=320,l=c('React').createClass({displayName:'MessengerComposeViewHeader',mixins:[c('ReactComponentWithPureRenderMixin')],propTypes:{onAddRecipient:j.func.isRequired,onRemoveRecipient:j.func.isRequired,onResize:j.func.isRequired,recipients:j.instanceOf(c('immutable').List).isRequired,viewer:j.string.isRequired},_timer:null,getInitialState:function(){return {tokenizerContext:null};},componentDidMount:function(){this._focusTokenizer();this.setState({tokenizerContext:this.refs.tokenizerContext});},componentDidUpdate:function(m){if(this.props.recipients!==m.recipients)this.props.onResize();},componentWillUnmount:function(){this._timer&&c('clearImmediate')(this._timer);},render:function(){return (c('React').createElement('div',{className:c('joinClasses')("_2y8y _5l-3",this.props.className)},c('React').createElement('div',{className:"_2y8z"},i._("To:")),c('React').createElement('div',null,c('React').createElement(c('MessengerTokenizer.react'),{className:"_2y8-",context:this.state.tokenizerContext,entries:this.props.recipients,extraRendererProps:{className:"_2y8_",width:k},hasHoverState:true,onAddEntryAttempt:this.props.onAddRecipient,onRemoveEntryAttempt:this.props.onRemoveRecipient,placeholder:i._("Type the name of a person or group"),ref:'tokenizer',searchSource:c('MessengerSearchSource').getForFBID(this.props.viewer),tabIndex:c('MessengerTabIndices').NEW_MESSAGE_TOKENIZER,useLayer:true,viewer:this.props.viewer}),c('React').createElement('div',{ref:'tokenizerContext'}))));},_focusTokenizer:function(){this._timer=c('setImmediate')(function(){this.refs.tokenizer&&this.refs.tokenizer.focusInput();}.bind(this));}});f.exports=l;},null);