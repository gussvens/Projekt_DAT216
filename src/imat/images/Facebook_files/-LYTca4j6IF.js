/*!CK:2820808874!*//*1456787461,*/

if (self.CavalryLogger) { CavalryLogger.start_js(["nGFja"]); }

__d('MessengerTokenizer.react',['cx','invariant','AbstractTokenizer.react','immutable','Keys','React','SearchableEntry','MessengerScrollableTypeaheadView.react','MessengerTypeaheadUtils','ReactComponentWithPureRenderMixin','joinClasses'],function a(b,c,d,e,f,g,h,i){'use strict';if(c.__markCompiled)c.__markCompiled();var j=c('React').PropTypes,k=30,l=100,m=c('React').createClass({displayName:'MessengerTokenizer',mixins:[c('ReactComponentWithPureRenderMixin')],propTypes:{autoFocus:j.bool,className:j.string,context:j.object,entries:j.instanceOf(c('immutable').List).isRequired,excludeGroups:j.bool,extraRendererProps:j.object,hasHoverState:j.bool,hideViewWithEntries:j.bool,onAddEntryAttempt:j.func.isRequired,onEntriesFound:j.func,onRemoveEntryAttempt:j.func.isRequired,onReorderEntryAttempt:j.func,originalEntryIDs:j.instanceOf(c('immutable').Set),placeholder:j.string,searchSource:j.object.isRequired,tabIndex:j.number,useLayer:j.bool,viewer:j.string.isRequired},getDefaultProps:function(){return {excludeGroups:false,hideViewWithEntries:true};},getInitialState:function(){return {isLoading:false,isLoaded:false,queryString:''};},componentDidMount:function(){if(this.props.autoFocus)this.focusInput();},render:function(){return (c('React').createElement('div',{className:c('joinClasses')(this.props.className,"_14-7")},c('React').createElement(c('AbstractTokenizer.react'),{autoHighlight:this.props.hasHoverState,context:this.props.context,entries:this.props.entries.toArray(),excludeTokenEntries:!!this.state.queryString,hideViewWithEntries:this.props.hideViewWithEntries,onAddEntryAttempt:this._handleAddEntryAttempt,onEntriesFound:this.props.onEntriesFound,onQueryStringChange:this._handleQueryStringChange,onRemoveEntryAttempt:this.props.onRemoveEntryAttempt,onReorderEntryAttempt:this.props.onReorderEntryAttempt,placeholder:this.props.placeholder,presenter:{ViewRenderer:c('MessengerScrollableTypeaheadView.react'),TokenRenderer:n,alwaysVisibleOnFocus:(this.state.isLoading||this.state.isLoaded)&&!!this.state.queryString,useLayer:this.props.useLayer,extraRendererProps:babelHelpers['extends']({hasHoverState:this.props.hasHoverState,isLoading:this.state.isLoading,originalEntryIDs:this.props.originalEntryIDs,selectedEntryIDs:c('immutable').Seq(this.props.entries).map(function(o){return o.getUniqueID();}).toSet(),viewer:this.props.viewer},this.props.extraRendererProps),maxEntries:k,sortEntries:this._sortEntries},queryString:this.state.queryString,ref:'tokenizer',searchSource:this.props.searchSource,searchSourceOptions:{onQueryFinished:this._handleQueryFinished,onQueryStarted:this._handleQueryStarted,showDefaultEntries:this.props.showDefaultEntries},showEntriesOnFocus:true,tabIndex:this.props.tabIndex&&this.props.tabIndex.toString()})));},_handleAddEntryAttempt:function(o){this.setState({isLoaded:false,queryString:''});var p=o.getUniqueID();if(this.props.entries.some(function(q){return (q.getUniqueID()===p);})){this.props.onRemoveEntryAttempt(o);return;}if(this.props.originalEntryIDs&&this.props.originalEntryIDs.contains(p))return;this.props.onAddEntryAttempt(o);},_sortEntries:function(o){if(this.props.entries.size||this.props.excludeGroups)return c('MessengerTypeaheadUtils').sortEntriesWithoutGroupsOrViewer(o,this.props.viewer);return c('MessengerTypeaheadUtils').sortEntries(o,this.state.queryString);},focusInput:function(){setTimeout(function(){this.refs.tokenizer&&this.refs.tokenizer.focusInput();}.bind(this));},_handleQueryStarted:function(o){if(o===this.state.queryString)this.setState({isLoading:true});},_handleQueryFinished:function(o){if(o===this.state.queryString)this.setState({isLoaded:true,isLoading:false});},_handleQueryStringChange:function(event){!(event.target instanceof HTMLInputElement)?i(0):undefined;this.setState({isLoaded:false,queryString:event.target.value});}}),n=c('React').createClass({displayName:'MessengerTokenizerToken',propTypes:{className:j.string,entry:j.instanceOf(c('SearchableEntry')),index:j.number,label:j.string.isRequired,onRemove:j.func},render:function(){return (c('React').createElement('span',{className:c('joinClasses')(this.props.className,"_14-8"),label:null,onClick:this._handleClick,onKeyDown:this._handleKeyDown,tabIndex:this.props.index+l},c('React').createElement('span',{'aria-label':this.props.label,title:this.props.label,className:"_14-9"},this.props.label)));},_handleClick:function(event){event.stopPropagation();},_handleKeyDown:function(event){if(event.keyCode===c('Keys').BACKSPACE){event.preventDefault();this.props.onRemove&&this.props.onRemove(this.props.entry);}}});f.exports=m;},null);